package com.byd.foundation.utils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Singleton<T> {
    private T mInstance;

    protected abstract T create();

    public final T get() {
        if (this.mInstance == null) {
            synchronized (Singleton.class) {
                if (this.mInstance == null) {
                    this.mInstance = create();
                }
            }
        }
        return this.mInstance;
    }
}
