package com.kamakarov.musicshelf.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamakarov.musicshelf.R;
import com.kamakarov.musicshelf.gui.adapters.SingerAdapter;
import com.kamakarov.musicshelf.model.Singer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public final class SingerListFragment extends FragmentBase implements SwipeRefreshLayout.OnRefreshListener {
    public static SingerListFragment newInstance() {
        Bundle args = new Bundle();

        SingerListFragment fragment = new SingerListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.swipe_refresh_layout_singer_lst)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recycler_view_singer_list)
    RecyclerView recyclerView;

    private final List<Singer> singerList = new ArrayList<>();
    private SingerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        fetchData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_singer_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SingerAdapter(getContext(), singerList);
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary);
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    private void fetchData() {
        api.getSingers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> Collections.emptyList()) // TODO: 28.03.16 improve handling 
                .subscribe(this::showData);
    }

    private void showData(List<Singer> singers) {
        // FIXME: 27.03.16 it can be doing before onCreateView, just after onCreate.
        Log.d("eee", "data is fetched");
        singerList.addAll(singers);
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
