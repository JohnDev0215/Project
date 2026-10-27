package com.amazon.mp3.net;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.account.credentials.RequestInterceptorImpl;

/* JADX INFO: loaded from: classes3.dex */
public class RequestInterceptorFactory {
    private final Context context;

    public RequestInterceptorFactory(@NonNull final Context context) {
        this.context = context;
    }

    public RequestInterceptor provideRequestInterceptor() {
        return new RequestInterceptorImpl(this.context);
    }
}
