package com.sylva.mockpupu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sylva.mockpupu.R;

import java.util.List;

/**
 * Created by sylva on 2018/5/18.
 */

public class ImagePagerAdapter extends PagerAdapter {
    private List<String> urls;
    private Context context;
    public ImagePagerAdapter(Context context, List<String> urls){
        this.context = context;
        this.urls = urls;
    }
    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, container, false);
        container.addView(view);
        ImageView iv = view.findViewById(R.id.item_image);
        Glide.with(context).load(urls.get(position)).into(iv);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
