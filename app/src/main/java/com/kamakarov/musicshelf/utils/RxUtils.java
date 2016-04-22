package com.kamakarov.musicshelf.utils;

import rx.Subscription;

public class RxUtils {
    public static void unsubscribeNullSafe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
