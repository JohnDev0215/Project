package com.amazon.mp3.store.html5.map;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public interface CookiesManager {
    MAPFuture<Bundle> fetchCookies(final Callback callback, final boolean forceRefresh);

    MAPFuture<Bundle> fetchCookies(final String domain, final Callback callback, final boolean forceRefresh);

    String getToken() throws ExecutionException, MAPCallbackErrorException, InterruptedException;
}
