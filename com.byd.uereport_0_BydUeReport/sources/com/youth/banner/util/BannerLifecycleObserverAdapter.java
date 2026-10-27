package com.youth.banner.util;

import androidx.lifecycle.Lifecycle;
import x0.e;
import x0.f;
import x0.m;

/* JADX INFO: loaded from: classes2.dex */
public class BannerLifecycleObserverAdapter implements e {
    private final f mLifecycleOwner;
    private final BannerLifecycleObserver mObserver;

    public BannerLifecycleObserverAdapter(f fVar, BannerLifecycleObserver bannerLifecycleObserver) {
        this.mLifecycleOwner = fVar;
        this.mObserver = bannerLifecycleObserver;
    }

    @m(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LogUtils.i("onDestroy");
        this.mObserver.onDestroy(this.mLifecycleOwner);
    }

    @m(Lifecycle.Event.ON_START)
    public void onStart() {
        LogUtils.i("onStart");
        this.mObserver.onStart(this.mLifecycleOwner);
    }

    @m(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LogUtils.i("onStop");
        this.mObserver.onStop(this.mLifecycleOwner);
    }
}
