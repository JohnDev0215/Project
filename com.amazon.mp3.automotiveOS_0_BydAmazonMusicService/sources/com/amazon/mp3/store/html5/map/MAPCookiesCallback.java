package com.amazon.mp3.store.html5.map;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.store.html5.util.StoreUtil;
import com.amazon.mp3.util.Log;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes4.dex */
public class MAPCookiesCallback implements Callback {
    protected final Context mContext;
    protected final Handler mHandler;
    private final String mUrl;
    private static final String TAG = MAPCookiesUtil.class.getSimpleName();
    private static final List<String> AMAZON_DOMAINS = EndpointsProvider.get().getSupportedDomains();

    public MAPCookiesCallback(final Context context) {
        this.mUrl = Environment.WEBVIEW.get().toURL();
        this.mContext = context;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public MAPCookiesCallback(final Context context, final String url) {
        this.mUrl = url;
        this.mContext = context;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle errorBundle) {
        Log.warning(TAG, "Failed to fetch cookies. Error %s: %s", Integer.valueOf(errorBundle.getInt("com.amazon.dcp.sso.ErrorCode")), errorBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle successBundle) {
        CookieSyncManager cookieSyncManagerCreateInstance;
        long timeInMillis = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();
        Log.verbose(TAG, "prefetchCookies complete");
        try {
            cookieSyncManagerCreateInstance = CookieSyncManager.getInstance();
        } catch (IllegalStateException unused) {
            Log.verbose(TAG, "Creating CookieSyncManager");
            cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(this.mContext);
        }
        String[] stringArray = successBundle.getStringArray(CookieKeys.KEY_COOKIES);
        if (stringArray != null && stringArray.length > 0) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            for (String str : stringArray) {
                cookieManager.setCookie(this.mUrl, replaceDomain(null, str));
                if (MAPCookiesUtil.cookieExists(str)) {
                    AccountCredentialStorage.get().setAtMainCookieExpirationDate((MAPCookiesUtil.getCookieExpirationPeriod() + timeInMillis) - MAPCookiesUtil.getCookiePreExpirationPeriod());
                }
            }
            cookieSyncManagerCreateInstance.sync();
            final JSONArray jSONArrayCookiesToJson = MAPCookiesUtil.cookiesToJson(stringArray);
            BootstrapSingletonTask.get(this.mContext).registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.store.html5.map.MAPCookiesCallback.1
                @Override // rx.functions.Action1
                public void call(final Object o) {
                    StoreUtil.broadcastCredentials(MAPCookiesCallback.this.mContext, jSONArrayCookiesToJson);
                }
            }, new Action1<Throwable>() { // from class: com.amazon.mp3.store.html5.map.MAPCookiesCallback.2
                @Override // rx.functions.Action1
                public void call(final Throwable throwable) {
                    Log.warning(MAPCookiesCallback.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
                }
            });
            return;
        }
        Log.warning(TAG, "Failed to retrieve cookies");
    }

    private String replaceDomain(String domain, String cookie) {
        if (domain == null) {
            return cookie;
        }
        return cookie.replaceAll("domain=.*?;", "domain=" + domain + ";");
    }
}
