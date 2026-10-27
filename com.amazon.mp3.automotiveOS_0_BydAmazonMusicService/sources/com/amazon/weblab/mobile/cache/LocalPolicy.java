package com.amazon.weblab.mobile.cache;

import com.amazon.weblab.mobile.settings.Interval;

/* JADX INFO: loaded from: classes5.dex */
abstract class LocalPolicy extends CachePolicy {
    Interval mTtl;

    public LocalPolicy(Interval interval) {
        if (interval == null) {
            throw new IllegalArgumentException("ttl can't be null.");
        }
        this.mTtl = interval;
    }
}
