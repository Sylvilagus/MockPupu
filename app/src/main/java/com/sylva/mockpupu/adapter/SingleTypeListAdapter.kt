package com.sylva.mockpupu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class SingleTypeListAdapter<in E>(
        private val context: Context,
        private val itemLayoutId: Int,
        list: List<E>?,
        private val headerViewCreator: ((LayoutInflater, ViewGroup, Int) -> RecyclerView.ViewHolder)? = null
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_ITEM = 2
        const val TYPE_FOOTER = 3
    }
    private val dataList = ArrayList<E>()
    private var headerViewHolder: RecyclerView.ViewHolder? = null
    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }
    init {
        list?.let {
            dataList += it
        }
    }

    fun getHeaderCount() = if(headerViewCreator == null) 0 else 1

    fun getFooterCount() = 0

    fun getPureItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int{
        val headerCount = getHeaderCount()
        return when(position){
            in 0 until headerCount -> TYPE_HEADER
            in headerCount until headerCount + getPureItemCount() -> TYPE_ITEM
            else -> TYPE_FOOTER
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_HEADER) {
            headerViewCreator?.apply {
                headerViewHolder = invoke(inflater, parent, viewType)
                return headerViewHolder!!
            }
        }
        return CommonViewHolder(inflater.inflate(itemLayoutId, parent, false))
    }


    override fun getItemCount(): Int {
//        Log.e("getItemCount", "data count is ${dataList.size}, headerCount is ${getHeaderCount()}, footer is ${getFooterCount()}")
        return dataList.size + getHeaderCount() + getFooterCount()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) != TYPE_ITEM)
            return
        val itemPosition = if(headerViewCreator == null) position else position - 1
        bindViewHolder(holder as CommonViewHolder, dataList[itemPosition])
    }

    abstract fun bindViewHolder(holder: CommonViewHolder, entity: E)

    fun setDataList(list: List<E>, append: Boolean = false){
        if(!append)
            dataList.clear()
        dataList += list
        notifyDataSetChanged()
    }

}

class CommonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val viewCache = SparseArray<View>()
    fun <V: View> getView(id: Int): V{
        var view = viewCache[id]
        if(view == null){
            view = itemView.findViewById(id)
            viewCache.put(id, view)
        }
        return view as V
    }
}