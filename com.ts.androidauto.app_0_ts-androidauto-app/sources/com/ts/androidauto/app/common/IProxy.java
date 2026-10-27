package com.ts.androidauto.app.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IProxy extends Handler {
    public abstract void init(Context context);

    public abstract void uninit();

    public IProxy(Looper looper) {
        super(looper);
    }
}
