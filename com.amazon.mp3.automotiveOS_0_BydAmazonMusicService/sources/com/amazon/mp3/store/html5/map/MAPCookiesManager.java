package com.amazon.mp3.store.html5.map;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.util.Log;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public class MAPCookiesManager implements CookiesManager {
    private static final String TAG = "MAPCookiesManager";
    private static CookiesManager manager;
    private boolean mPreviousForceRefresh = false;
    private MAPCallbackSimpleQueue mQueue;

    @Override // com.amazon.mp3.store.html5.map.CookiesManager
    public MAPFuture<Bundle> fetchCookies(Callback callback, boolean forceRefresh) {
        return fetchCookies(EndpointsProvider.get().getDomain(), callback, forceRefresh);
    }

    @Override // com.amazon.mp3.store.html5.map.CookiesManager
    public MAPFuture<Bundle> fetchCookies(final String domain, final Callback callback, final boolean forceRefresh) {
        Log.verbose(TAG, "prefetchCookies starting... domain = %s", domain);
        TokenManagement tokenManagement = new TokenManagement(AmazonApplication.getContext());
        String accountId = AccountCredentialStorage.get().getAccountId();
        Bundle bundle = new Bundle();
        bundle.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, forceRefresh);
        MAPCallbackSimpleQueue mAPCallbackSimpleQueue = this.mQueue;
        if (mAPCallbackSimpleQueue == null) {
            this.mQueue = new MAPCallbackSimpleQueue();
        } else if (forceRefresh && !this.mPreviousForceRefresh) {
            this.mQueue = mAPCallbackSimpleQueue.drainToNewQueue();
        }
        if (this.mQueue.getFuture() == null) {
            this.mQueue.setFuture(tokenManagement.getCookies(accountId, domain, bundle, this.mQueue));
            this.mPreviousForceRefresh = forceRefresh;
        }
        this.mQueue.add(callback);
        return this.mQueue.getFuture();
    }

    @Override // com.amazon.mp3.store.html5.map.CookiesManager
    public String getToken() throws ExecutionException, MAPCallbackErrorException, InterruptedException {
        try {
            return new TokenManagement(AmazonApplication.getContext()).getToken(AccountCredentialStorage.get().getAccountId(), TokenKeys.getAccessTokenKeyForPackage(AmazonApplication.getContext().getPackageName()), null, null).get().getString("value_key");
        } catch (MAPCallbackErrorException e) {
            Log.warning(TAG, "MAPCallbackErrorException", e);
            throw e;
        } catch (InterruptedException e2) {
            Log.warning(TAG, "InterruptedException", e2);
            throw e2;
        } catch (ExecutionException e3) {
            Log.warning(TAG, "ExecutionException", e3);
            throw e3;
        }
    }

    public static synchronized CookiesManager getCookiesManager() {
        if (manager == null) {
            manager = new MAPCookiesManager();
        }
        return manager;
    }
}
