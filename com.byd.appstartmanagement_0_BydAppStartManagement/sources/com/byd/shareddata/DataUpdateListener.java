package com.byd.shareddata;

import android.os.Handler;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DataUpdateListener<T> {
    private Handler mHandler;

    public abstract void dataValueChanged(String str, T t, T t2);

    public Handler getHandler() {
        return this.mHandler;
    }

    public DataUpdateListener<T> setHandler(Handler handler) {
        this.mHandler = handler;
        return this;
    }
}
