package com.ts.car.core.provider;

import android.content.Context;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public interface IServiceProvider {
    <T> T getService(Class<T> cls, Context context, IBinder iBinder);
}
