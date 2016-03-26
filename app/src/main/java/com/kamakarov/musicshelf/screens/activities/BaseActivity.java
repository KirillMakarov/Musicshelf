package com.kamakarov.musicshelf.screens.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {
    protected CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeSubscription.unsubscribe();
        ButterKnife.unbind(this);
    }
}
