package com.amazon.music.skyfire.ui.providers.impl;

import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import com.amazon.music.skyfire.core.network.NetworkTaskError;
import com.amazon.music.skyfire.core.network.ValidationResult;
import kotlin.Metadata;
import okhttp3.Headers;

/* JADX INFO: compiled from: DefaultNetworkInterceptorProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultNetworkInterceptorProvider;", "Lcom/amazon/music/skyfire/core/network/NetworkInterceptor;", "()V", "forceAuthentication", "", "skillPath", "", "onError", "", "error", "Lcom/amazon/music/skyfire/core/network/NetworkTaskError;", "validateRequest", "Lcom/amazon/music/skyfire/core/network/ValidationResult;", "headers", "Lokhttp3/Headers;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultNetworkInterceptorProvider implements NetworkInterceptor {
    @Override // com.amazon.music.skyfire.core.network.NetworkInterceptor
    public boolean forceAuthentication(String skillPath) {
        return false;
    }

    @Override // com.amazon.music.skyfire.core.network.NetworkInterceptor
    public void onError(String skillPath, NetworkTaskError error) {
    }

    @Override // com.amazon.music.skyfire.core.network.NetworkInterceptor
    public ValidationResult validateRequest(String skillPath, Headers headers) {
        return new ValidationResult(true, null);
    }
}
