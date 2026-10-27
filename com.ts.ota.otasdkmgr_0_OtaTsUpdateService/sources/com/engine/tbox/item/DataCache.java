package com.engine.tbox.item;

/* JADX INFO: loaded from: classes.dex */
public class DataCache<T> {
    private boolean isCached = false;
    private T t;

    public synchronized void cache(T t) {
        this.t = t;
        this.isCached = true;
    }

    public synchronized boolean isCached() {
        return this.isCached;
    }

    public synchronized T getValue() {
        return this.t;
    }

    public synchronized void clear() {
        this.isCached = false;
        this.t = null;
    }
}
