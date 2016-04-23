package com.kamakarov.musicshelf.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.kamakarov.musicshelf.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
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

    @Bind(R.id.report_empty)
    View emptyPlaceholder;

    @Bind(R.id.root_view_singer_list)
    View rootView;

    private final List<Singer> singerList = new ArrayList<>();
    private SingerAdapter mAdapter;
    private boolean isFirstTimeCreated;
    private AtomicBoolean isFromSnackBar = new AtomicBoolean();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isFirstTimeCreated = true;
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
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        showEmptyPlaceholder(singerList.isEmpty());
        isFromSnackBar.set(false);
        fetchData();
    }

    @Override
    public void onPause() {
        super.onPause();
        isFirstTimeCreated = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    Subscription databaseSubsctiption;
    Subscription apiSubscription;

    private void fetchData() {
        RxUtils.unsubscribeNullSafe(apiSubscription);
        if (databaseSubsctiption == null) {
            //first time: try to get from database, after that, show only when table was changed
            databaseSubsctiption = dbManager
                    .getSingers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::showData);
        }


        apiSubscription = api.getSingers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(list -> {
                    if (swipeRefreshLayout != null) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.d("eee", "cancel refresh in next");
                    }
                })
                .doOnError(this::onErrorInternetConnection)
                .observeOn(Schedulers.io())
                .subscribe(this::saveData, throwable -> {/*do nothing, because we handle in doOnError*/});
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtils.unsubscribeNullSafe(databaseSubsctiption);
        RxUtils.unsubscribeNullSafe(apiSubscription);
    }

    private void saveData(List<Singer> singers) {
        isFromSnackBar.set(false);
        dbManager.addSingers(singers, false);
    }

    private void onErrorInternetConnection(Throwable throwable) {
        Log.d("eee", throwable.getMessage());

        if (singerList == null || singerList.isEmpty()) {
            showEmptyPlaceholder(true);
        } else {
            showEmptyPlaceholder(false);
        }

        if (swipeRefreshLayout != null && (swipeRefreshLayout.isRefreshing() || isFromSnackBar.get())) {
            swipeRefreshLayout.setRefreshing(false);
            Log.d("eee", "cancel refreshing in error");
            showSnackBarRetry();
        }

        isFromSnackBar.set(false);
    }

    private void showSnackBarRetry() {
        Snackbar.make(rootView, R.string.no_connection, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry_title, view -> {
                    isFromSnackBar.set(true);
                    fetchData();
                })
                .show();
    }

    private void showEmptyPlaceholder(boolean needShow) {
        if (emptyPlaceholder != null) {
            if (needShow && !isFirstTimeCreated) {
                emptyPlaceholder.setVisibility(View.VISIBLE);
            } else {
                emptyPlaceholder.setVisibility(View.GONE);
            }
        }
    }

    private void showData(List<Singer> singers) {
        Log.d("eee", "data is fetched");
        singerList.clear();
        singerList.addAll(singers);
        showEmptyPlaceholder(singerList.isEmpty()); // if it is first time, that we will update from internet
        isFirstTimeCreated = false;//
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
        Log.d("eee", "count in list: " + singerList.size());
    }
}
