package com.byd.spi;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.spi.factory.ServiceFactory;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceInfo<S, P> {
    public Class<P> mProvider;
    public Class<S> mService;

    public ServiceInfo(Class<S> cls, Class<P> cls2) {
        this.mService = cls;
        this.mProvider = cls2;
    }

    public S createService(@NonNull Context context) {
        return (S) ServiceFactory.with().createService(context, this.mProvider);
    }
}
