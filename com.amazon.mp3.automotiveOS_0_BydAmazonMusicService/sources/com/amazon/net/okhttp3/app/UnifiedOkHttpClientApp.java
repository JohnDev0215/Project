package com.amazon.net.okhttp3.app;

import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnifiedOkHttpClientApp.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"injectImplementation", "", "injectedImplementation", "Lcom/amazon/net/okhttp3/app/UnifiedImplementation;", "UnifiedOkHttpClient"}, k = 2, mv = {1, 6, 0}, xi = 48)
@JvmName(name = "UnifiedOkHttpClientApp")
public final class UnifiedOkHttpClientApp {
    public static final void injectImplementation(UnifiedImplementation injectedImplementation) {
        Intrinsics.checkNotNullParameter(injectedImplementation, "injectedImplementation");
        UnifiedOkHttpClient.injectImplementationInternal(injectedImplementation);
    }
}
