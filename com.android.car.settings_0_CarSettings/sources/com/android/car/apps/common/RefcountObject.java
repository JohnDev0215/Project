package com.android.car.apps.common;

/* JADX INFO: loaded from: classes5.dex */
public class RefcountObject<T> {
    private T mObject;
    private int mRefcount;
    private RefcountListener mRefcountListener;

    public interface RefcountListener {
        void onRefcountZero(RefcountObject<?> refcountObject);
    }

    public RefcountObject(T object) {
        this.mObject = object;
    }

    public void setRefcountListener(RefcountListener listener) {
        this.mRefcountListener = listener;
    }

    public synchronized int addRef() {
        int i;
        i = this.mRefcount + 1;
        this.mRefcount = i;
        return i;
    }

    public synchronized int releaseRef() {
        int i = this.mRefcount - 1;
        this.mRefcount = i;
        if (i == 0 && this.mRefcountListener != null) {
            this.mRefcountListener.onRefcountZero(this);
        }
        return this.mRefcount;
    }

    public synchronized int getRef() {
        return this.mRefcount;
    }

    public T getObject() {
        return this.mObject;
    }
}
