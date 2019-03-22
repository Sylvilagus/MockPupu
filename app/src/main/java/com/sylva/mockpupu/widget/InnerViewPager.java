package com.sylva.mockpupu.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Adapter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InnerViewPager extends ViewPager
{

    private boolean isAuto = true;

    public InnerViewPager(Context context)
    {
        super(context);
    }

    public InnerViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        autoSwitch();
    }
    
    private void autoSwitch(){
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
    	schedule.scheduleWithFixedDelay(new AutoSwitchTask(), 4, 4, TimeUnit.SECONDS);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction())
        {
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_MOVE:
        	
            getParent().requestDisallowInterceptTouchEvent(false);
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:

            getParent().requestDisallowInterceptTouchEvent(true);
            break;
        default:
            break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
    	switch (ev.getAction())
        {
        case MotionEvent.ACTION_MOVE:
        case MotionEvent.ACTION_DOWN:
        	isAuto=false;
        	break;

        case MotionEvent.ACTION_UP:
        	isAuto=true;
        	break;
        default:
            break;
        }
        return super.onTouchEvent(ev);
    }
    private class AutoSwitchTask implements Runnable {

		@Override
		public void run() {
			if(isAuto)
				handler.obtainMessage().sendToTarget();
		}
    	
    }
    private Handler handler = new MyHandler(this);
    private static class MyHandler extends android.os.Handler{
        private InnerViewPager pager;

        MyHandler(InnerViewPager pager) {
            this.pager = pager;
        }

        public void handleMessage(android.os.Message msg) {
            PagerAdapter adapter = pager.getAdapter();
            if(adapter == null|| adapter.getCount()==0)
                return;
            pager.setCurrentItem((pager.getCurrentItem()+1)% adapter.getCount());
        }
    }

}
