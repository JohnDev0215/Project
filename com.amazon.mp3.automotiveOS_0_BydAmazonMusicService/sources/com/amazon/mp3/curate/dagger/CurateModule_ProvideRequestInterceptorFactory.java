package com.amazon.mp3.curate.dagger;

import android.content.Context;
import com.amazon.hermes.RequestInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideRequestInterceptorFactory implements Factory<RequestInterceptor> {
    public static RequestInterceptor provideRequestInterceptor(CurateModule instance, Context context) {
        return (RequestInterceptor) Preconditions.checkNotNullFromProvides(instance.provideRequestInterceptor(context));
    }
}
