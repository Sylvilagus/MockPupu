package com.sylva.mockpupu.fragment

import android.widget.TextView
import com.sylva.mockpupu.R
import com.sylva.mockpupu.adapter.CommonViewHolder
import com.sylva.mockpupu.adapter.IndexHeaderHolder
import com.sylva.mockpupu.adapter.SingleTypeListAdapter

class IndexListFragment: CommonListFragment<String>(){
    override fun createAdapter(): SingleTypeListAdapter<String> {
        val mockDataList = (0..100).map { "这是演示数据$it" }
        return object : SingleTypeListAdapter<String>(activity!!, R.layout.item_mocklist, mockDataList,{
            inflater, parent, _ ->
            IndexHeaderHolder(inflater.inflate(R.layout.header_index_view_pager, parent, false))
        }){
            override fun bindViewHolder(holder: CommonViewHolder, entity: String) {
                holder.getView<TextView>(R.id.textView).text = entity
            }

        }
    }

    override fun refetchDataFromRepository(adapter: SingleTypeListAdapter<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreDataFromRepository(adapter: SingleTypeListAdapter<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}