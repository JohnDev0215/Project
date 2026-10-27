package com.amazon.avod.media.ads.internal;

import android.webkit.CookieManager;
import com.amazon.avod.ads.http.HttpCookieStore;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.util.HostNameMatcher;
import com.amazon.avod.util.DLog;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class PersistentAndroidCookieStore implements HttpCookieStore {
    private final HostNameMatcher mAllowedCookiesMatcher;
    private final CookieManager mCookieManager;

    public PersistentAndroidCookieStore() {
        this(CookieManager.getInstance(), new HostNameMatcher(AdsConfig.getInstance().getAllowedCookieDomains()));
    }

    PersistentAndroidCookieStore(CookieManager cookieManager, HostNameMatcher hostNameMatcher) {
        this.mCookieManager = cookieManager;
        cookieManager.setAcceptCookie(true);
        this.mAllowedCookiesMatcher = hostNameMatcher;
    }

    @Override // com.amazon.avod.ads.http.HttpCookieStore
    public String getCookie(URL url) {
        if (this.mAllowedCookiesMatcher.matches(url)) {
            DLog.logf("Getting cookies for host %s.", url.getHost());
            return this.mCookieManager.getCookie(url.toString());
        }
        DLog.warnf("Getting cookies for host %s failed because it is not whitelisted.", url.getHost());
        return null;
    }

    @Override // com.amazon.avod.ads.http.HttpCookieStore
    public void setCookie(URL url, String str) {
        if (!this.mAllowedCookiesMatcher.matches(url)) {
            DLog.warnf("Setting cookies for host %s failed because it is not whitelisted.", url.getHost());
        } else {
            DLog.logf("Setting cookies for host %s.", url.getHost());
            this.mCookieManager.setCookie(url.toString(), str);
        }
    }
}
