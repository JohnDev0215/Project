package com.amazon.music.skyfire.core.network;

import okhttp3.Headers;

/* JADX INFO: loaded from: classes4.dex */
public interface NetworkInterceptor {
    boolean forceAuthentication(String str);

    void onError(String str, NetworkTaskError networkTaskError);

    ValidationResult validateRequest(String str, Headers headers);
}
