package com.byd.spi.service;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SingletonCreator implements ServiceCreator {
    private Object mInstance;

    public abstract Object create(Context context);

    @Override // com.byd.spi.service.ServiceCreator
    public Object createService(Context context) {
        return get(context);
    }

    public final Object get(Context context) {
        if (this.mInstance == null) {
            synchronized (SingletonCreator.class) {
                if (this.mInstance == null) {
                    this.mInstance = create(context);
                }
            }
        }
        return this.mInstance;
    }
}
