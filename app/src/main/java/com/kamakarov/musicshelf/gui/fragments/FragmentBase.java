package com.kamakarov.musicshelf.gui.fragments;

import android.support.v4.app.Fragment;

import com.kamakarov.musicshelf.core.MainApplication;
import com.kamakarov.musicshelf.gui.IIntentManager;
import com.kamakarov.musicshelf.store.DbManager;
import com.kamakarov.musicshelf.web.IMusicApi;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

public class FragmentBase extends Fragment {

    @Inject
    protected IIntentManager intentManager;

    @Inject
    protected IMusicApi api;

    @Inject
    protected DbManager dbManager;

    @Inject
    protected BriteDatabase mSqlBrite;

    public FragmentBase() {
        MainApplication.getComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MainApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
