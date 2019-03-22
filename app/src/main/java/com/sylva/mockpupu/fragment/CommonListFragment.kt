package com.sylva.mockpupu.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sylva.mockpupu.R
import com.sylva.mockpupu.adapter.SingleTypeListAdapter
import kotlinx.android.synthetic.main.fragment_common_list.*

abstract class CommonListFragment<E>: BaseFragment(){
    private lateinit var adapter: SingleTypeListAdapter<E>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_common_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = createAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
    abstract fun createAdapter(): SingleTypeListAdapter<E>
    abstract fun refetchDataFromRepository(adapter: SingleTypeListAdapter<E>)
    abstract fun loadMoreDataFromRepository(adapter: SingleTypeListAdapter<E>)
}