package defpackage;

import J.N;
import android.net.ParseException;
import android.net.WebAddress;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import java.util.Objects;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: d8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0644d8 extends CookieManager {
    public C0104Fo a;

    public C0644d8(C0104Fo c0104Fo) {
        this.a = c0104Fo;
    }

    public static String a(String str) {
        return new WebAddress(str).toString();
    }

    @Override // android.webkit.CookieManager
    public synchronized boolean acceptCookie() {
        C0104Fo c0104Fo;
        c0104Fo = this.a;
        return N.MIaWBQxt(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        return webView.getSettings().getAcceptThirdPartyCookies();
    }

    public boolean allowFileSchemeCookiesImpl() {
        C0104Fo c0104Fo = this.a;
        return N.MjZje8ZY(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public void flush() {
        C0104Fo c0104Fo = this.a;
        N.MgWRfeHz(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public String getCookie(String str) {
        try {
            return this.a.b(a(str));
        } catch (ParseException e) {
            Ts.a("CookieManager", "Unable to get cookies due to error parsing URL: %s", str, e);
            return null;
        }
    }

    @Override // android.webkit.CookieManager
    public synchronized boolean hasCookies() {
        C0104Fo c0104Fo;
        c0104Fo = this.a;
        return N.MLEl9vQp(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public void removeAllCookie() {
        C0104Fo c0104Fo = this.a;
        N.M8tXWfBc(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public void removeAllCookies(ValueCallback valueCallback) {
        C0104Fo c0104Fo = this.a;
        Callback callbackA = AbstractC0532b8.a(valueCallback);
        Objects.requireNonNull(c0104Fo);
        try {
            N.MYNMnyIh(c0104Fo.a, c0104Fo, new C0068Do(callbackA));
        } catch (IllegalStateException unused) {
            throw new IllegalStateException("removeAllCookies must be called on a thread with a running Looper.");
        }
    }

    @Override // android.webkit.CookieManager
    public void removeExpiredCookie() {
        C0104Fo c0104Fo = this.a;
        N.MtpZW_Jk(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public void removeSessionCookie() {
        C0104Fo c0104Fo = this.a;
        N.MCvO0Hcd(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public void removeSessionCookies(ValueCallback valueCallback) {
        C0104Fo c0104Fo = this.a;
        Callback callbackA = AbstractC0532b8.a(valueCallback);
        Objects.requireNonNull(c0104Fo);
        try {
            N.MPH4p3lP(c0104Fo.a, c0104Fo, new C0068Do(callbackA));
        } catch (IllegalStateException unused) {
            throw new IllegalStateException("removeSessionCookies must be called on a thread with a running Looper.");
        }
    }

    @Override // android.webkit.CookieManager
    public synchronized void setAcceptCookie(boolean z) {
        C0104Fo c0104Fo = this.a;
        N.MxGz1CMI(c0104Fo.a, c0104Fo, z);
    }

    public void setAcceptFileSchemeCookiesImpl(boolean z) {
        C0104Fo c0104Fo = this.a;
        N.Mfo4YHeg(c0104Fo.a, c0104Fo, z);
    }

    @Override // android.webkit.CookieManager
    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        webView.getSettings().setAcceptThirdPartyCookies(z);
    }

    @Override // android.webkit.CookieManager
    public void setCookie(String str, String str2) {
        if (str2 == null) {
            Ts.a("CookieManager", "Not setting cookie with null value for URL: %s", str);
            return;
        }
        try {
            C0104Fo c0104Fo = this.a;
            String strA = a(str);
            Objects.requireNonNull(c0104Fo);
            C0086Eo c0086EoA = C0104Fo.a(strA, str2);
            N.M521ruQI(c0104Fo.a, c0104Fo, c0086EoA.a, c0086EoA.b);
        } catch (ParseException e) {
            Ts.a("CookieManager", "Not setting cookie due to error parsing URL: %s", str, e);
        }
    }

    public String getCookie(String str, boolean z) {
        return getCookie(str);
    }

    public synchronized String getCookie(WebAddress webAddress) {
        return this.a.b(webAddress.toString());
    }

    public synchronized boolean hasCookies(boolean z) {
        C0104Fo c0104Fo;
        c0104Fo = this.a;
        return N.MLEl9vQp(c0104Fo.a, c0104Fo);
    }

    @Override // android.webkit.CookieManager
    public void setCookie(String str, String str2, ValueCallback valueCallback) {
        if (str2 == null) {
            Ts.a("CookieManager", "Not setting cookie with null value for URL: %s", str);
            return;
        }
        try {
            C0104Fo c0104Fo = this.a;
            String strA = a(str);
            Callback callbackA = AbstractC0532b8.a(valueCallback);
            Objects.requireNonNull(c0104Fo);
            try {
                C0086Eo c0086EoA = C0104Fo.a(strA, str2);
                N.MqWxZVFj(c0104Fo.a, c0104Fo, c0086EoA.a, c0086EoA.b, new C0068Do(callbackA));
            } catch (IllegalStateException unused) {
                throw new IllegalStateException("SetCookie must be called on a thread with a running Looper.");
            }
        } catch (ParseException e) {
            Ts.a("CookieManager", "Not setting cookie due to error parsing URL: %s", str, e);
        }
    }
}
