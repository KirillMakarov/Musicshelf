package com.kamakarov.musicshelf.screens.fragments;

import android.support.v4.app.Fragment;

import com.kamakarov.musicshelf.core.MainApplication;
import com.squareup.leakcanary.RefWatcher;

public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MainApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
