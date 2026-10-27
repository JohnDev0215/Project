package com.android.webview.chromium;

import J.N;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import defpackage.AbstractC0588c8;
import defpackage.C1510sp;
import java.util.Objects;
import org.chromium.android_webview.AwSettings;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContentSettingsAdapter extends WebSettings {
    public AwSettings a;
    public WebSettings.PluginState b = WebSettings.PluginState.OFF;

    public ContentSettingsAdapter(AwSettings awSettings) {
        this.a = awSettings;
    }

    @Override // android.webkit.WebSettings
    public boolean enableSmoothTransition() {
        return false;
    }

    public boolean getAcceptThirdPartyCookies() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.T;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public boolean getAllowContentAccess() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.b0;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public boolean getAllowFileAccess() {
        return this.a.getAllowFileAccess();
    }

    @Override // android.webkit.WebSettings
    public boolean getAllowFileAccessFromFileURLs() {
        return this.a.e();
    }

    @Override // android.webkit.WebSettings
    public boolean getAllowUniversalAccessFromFileURLs() {
        return this.a.f();
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getBlockNetworkImage() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.v;
        }
        return !z;
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getBlockNetworkLoads() {
        return this.a.g();
    }

    @Override // android.webkit.WebSettings
    public boolean getBuiltInZoomControls() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.j0;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public int getCacheMode() {
        int i;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            i = awSettings.d0;
        }
        return i;
    }

    @Override // android.webkit.WebSettings
    public synchronized String getCursiveFontFamily() {
        return this.a.h();
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getDatabaseEnabled() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.D;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public synchronized String getDatabasePath() {
        return "";
    }

    @Override // android.webkit.WebSettings
    public synchronized int getDefaultFixedFontSize() {
        return this.a.i();
    }

    @Override // android.webkit.WebSettings
    public synchronized int getDefaultFontSize() {
        return this.a.j();
    }

    @Override // android.webkit.WebSettings
    public synchronized String getDefaultTextEncodingName() {
        return this.a.k();
    }

    @Override // android.webkit.WebSettings
    public WebSettings.ZoomDensity getDefaultZoom() {
        return WebSettings.ZoomDensity.MEDIUM;
    }

    @Override // android.webkit.WebSettings
    public int getDisabledActionModeMenuItems() {
        return this.a.m();
    }

    @Override // android.webkit.WebSettings
    public boolean getDisplayZoomControls() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.k0;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getDomStorageEnabled() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.C;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public synchronized String getFantasyFontFamily() {
        return this.a.n();
    }

    @Override // android.webkit.WebSettings
    public synchronized String getFixedFontFamily() {
        return this.a.o();
    }

    @Override // android.webkit.WebSettings
    public int getForceDark() {
        int forceDarkModeLocked;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            forceDarkModeLocked = awSettings.getForceDarkModeLocked();
        }
        if (forceDarkModeLocked != 0) {
            return forceDarkModeLocked != 2 ? 1 : 2;
        }
        return 0;
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        return this.a.p();
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getJavaScriptEnabled() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.w;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public synchronized WebSettings.LayoutAlgorithm getLayoutAlgorithm() {
        int i;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            i = awSettings.g;
        }
        if (i == 0) {
            return WebSettings.LayoutAlgorithm.NORMAL;
        }
        if (i == 1) {
            return WebSettings.LayoutAlgorithm.SINGLE_COLUMN;
        }
        if (i == 2) {
            return WebSettings.LayoutAlgorithm.NARROW_COLUMNS;
        }
        if (i == 3) {
            return WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING;
        }
        throw new IllegalArgumentException("Unsupported value: " + i);
    }

    @Override // android.webkit.WebSettings
    public boolean getLightTouchEnabled() {
        return false;
    }

    @Override // android.webkit.WebSettings
    public boolean getLoadWithOverviewMode() {
        return this.a.q();
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getLoadsImagesAutomatically() {
        return this.a.r();
    }

    @Override // android.webkit.WebSettings
    public boolean getMediaPlaybackRequiresUserGesture() {
        return this.a.s();
    }

    @Override // android.webkit.WebSettings
    public synchronized int getMinimumFontSize() {
        return this.a.t();
    }

    @Override // android.webkit.WebSettings
    public synchronized int getMinimumLogicalFontSize() {
        return this.a.u();
    }

    @Override // android.webkit.WebSettings
    public int getMixedContentMode() {
        int i;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            i = awSettings.N;
        }
        return i;
    }

    @Deprecated
    public boolean getNavDump() {
        return false;
    }

    @Override // android.webkit.WebSettings
    public boolean getOffscreenPreRaster() {
        return this.a.v();
    }

    @Override // android.webkit.WebSettings
    public synchronized WebSettings.PluginState getPluginState() {
        return this.b;
    }

    public synchronized boolean getPluginsEnabled() {
        return this.b == WebSettings.PluginState.ON;
    }

    @Override // android.webkit.WebSettings
    public boolean getSafeBrowsingEnabled() {
        return this.a.w();
    }

    @Override // android.webkit.WebSettings
    public synchronized String getSansSerifFontFamily() {
        return this.a.x();
    }

    @Override // android.webkit.WebSettings
    public boolean getSaveFormData() {
        if (Build.VERSION.SDK_INT >= 26) {
            return false;
        }
        return this.a.y();
    }

    @Override // android.webkit.WebSettings
    public boolean getSavePassword() {
        return false;
    }

    @Override // android.webkit.WebSettings
    public synchronized String getSerifFontFamily() {
        return this.a.z();
    }

    @Override // android.webkit.WebSettings
    public synchronized String getStandardFontFamily() {
        return this.a.A();
    }

    @Override // android.webkit.WebSettings
    public synchronized int getTextZoom() {
        return this.a.B();
    }

    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        return false;
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean getUseWideViewPort() {
        return this.a.C();
    }

    public synchronized int getUserAgent() {
        return AwSettings.l().equals(getUserAgentString()) ? 0 : -1;
    }

    @Override // android.webkit.WebSettings
    public synchronized String getUserAgentString() {
        return this.a.D();
    }

    public boolean getVideoOverlayForEmbeddedEncryptedVideoEnabled() {
        return false;
    }

    public void setAcceptThirdPartyCookies(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.T = z;
            final C1510sp c1510sp = awSettings.m0;
            c1510sp.a(new Runnable(c1510sp) { // from class: pp
                public final C1510sp s;

                {
                    this.s = c1510sp;
                }

                @Override // java.lang.Runnable
                public void run() {
                    AwSettings.b(this.s.c);
                }
            });
        }
    }

    @Override // android.webkit.WebSettings
    public void setAllowContentAccess(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.b0 = z;
        }
    }

    @Override // android.webkit.WebSettings
    public void setAllowFileAccess(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.c0 = z;
            final C1510sp c1510sp = awSettings.m0;
            c1510sp.a(new Runnable(c1510sp) { // from class: qp
                public final C1510sp s;

                {
                    this.s = c1510sp;
                }

                @Override // java.lang.Runnable
                public void run() {
                    AwSettings.a(this.s.c);
                }
            });
        }
    }

    @Override // android.webkit.WebSettings
    public void setAllowFileAccessFromFileURLs(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.y != z) {
                awSettings.y = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.x != z) {
                awSettings.x = z;
                awSettings.m0.b();
            }
        }
    }

    public synchronized void setAppCacheEnabled(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.B != z) {
                awSettings.B = z;
                awSettings.m0.b();
            }
        }
    }

    public synchronized void setAppCacheMaxSize(long j) {
    }

    public synchronized void setAppCachePath(String str) {
        this.a.G(str);
    }

    @Override // android.webkit.WebSettings
    public synchronized void setBlockNetworkImage(boolean z) {
        AwSettings awSettings = this.a;
        boolean z2 = !z;
        synchronized (awSettings.f) {
            if (awSettings.v != z2) {
                awSettings.v = z2;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setBlockNetworkLoads(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (!z) {
                if (!awSettings.c) {
                    throw new SecurityException("Permission denied - application missing INTERNET permission");
                }
            }
            awSettings.a0 = z;
        }
    }

    @Override // android.webkit.WebSettings
    public void setBuiltInZoomControls(boolean z) {
        this.a.H(z);
    }

    @Override // android.webkit.WebSettings
    public void setCacheMode(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.d0 = i;
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setCursiveFontFamily(String str) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (str != null) {
                if (!awSettings.m.equals(str)) {
                    awSettings.m = str;
                    awSettings.m0.b();
                }
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setDatabaseEnabled(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.D != z) {
                awSettings.D = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setDatabasePath(String str) {
    }

    @Override // android.webkit.WebSettings
    public synchronized void setDefaultFixedFontSize(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            int iD = awSettings.d(i);
            if (awSettings.t != iD) {
                awSettings.t = iD;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setDefaultFontSize(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            int iD = awSettings.d(i);
            if (awSettings.s != iD) {
                awSettings.s = iD;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setDefaultTextEncodingName(String str) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (str != null) {
                if (!awSettings.o.equals(str)) {
                    awSettings.o = str;
                    awSettings.m0.b();
                }
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setDefaultZoom(WebSettings.ZoomDensity zoomDensity) {
    }

    @Override // android.webkit.WebSettings
    public void setDisabledActionModeMenuItems(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.S = i;
        }
    }

    @Override // android.webkit.WebSettings
    public void setDisplayZoomControls(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.k0 = z;
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setDomStorageEnabled(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.C != z) {
                awSettings.C = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setEnableSmoothTransition(boolean z) {
    }

    @Override // android.webkit.WebSettings
    public synchronized void setFantasyFontFamily(String str) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (str != null) {
                if (!awSettings.n.equals(str)) {
                    awSettings.n = str;
                    awSettings.m0.b();
                }
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setFixedFontFamily(String str) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (str != null) {
                if (!awSettings.j.equals(str)) {
                    awSettings.j = str;
                    awSettings.m0.b();
                }
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setForceDark(int i) {
        if (i == 0) {
            this.a.J(0);
        } else if (i == 1) {
            this.a.J(1);
        } else {
            if (i != 2) {
                throw new IllegalArgumentException("Force dark mode is not one of FORCE_DARK_(ON|OFF|AUTO)");
            }
            this.a.J(2);
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setGeolocationDatabasePath(String str) {
    }

    @Override // android.webkit.WebSettings
    public synchronized void setGeolocationEnabled(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.f0 = z;
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.z != z) {
                awSettings.z = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setJavaScriptEnabled(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.w != z) {
                awSettings.w = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setLayoutAlgorithm(WebSettings.LayoutAlgorithm layoutAlgorithm) {
        int i = AbstractC0588c8.a[layoutAlgorithm.ordinal()];
        if (i == 1) {
            this.a.K(0);
            return;
        }
        if (i == 2) {
            this.a.K(1);
            return;
        }
        if (i == 3) {
            this.a.K(2);
        } else {
            if (i == 4) {
                this.a.K(3);
                return;
            }
            throw new IllegalArgumentException("Unsupported value: " + layoutAlgorithm);
        }
    }

    @Override // android.webkit.WebSettings
    public void setLightTouchEnabled(boolean z) {
    }

    @Override // android.webkit.WebSettings
    public void setLoadWithOverviewMode(boolean z) {
        final AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.H != z) {
                awSettings.H = z;
                awSettings.m0.a(new Runnable(awSettings) { // from class: jp
                    public final AwSettings s;

                    {
                        this.s = awSettings;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AwSettings awSettings2 = this.s;
                        if (awSettings2.l0 != 0) {
                            ThreadUtils.b();
                            long j = awSettings2.l0;
                            if (j != 0) {
                                N.MuI9Jt0j(j, awSettings2);
                            }
                            N.MYBohkOD(awSettings2.l0, awSettings2);
                        }
                    }
                });
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setLoadsImagesAutomatically(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.u != z) {
                awSettings.u = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.I != z) {
                awSettings.I = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setMinimumFontSize(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            int iD = awSettings.d(i);
            if (awSettings.q != iD) {
                awSettings.q = iD;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setMinimumLogicalFontSize(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            int iD = awSettings.d(i);
            if (awSettings.r != iD) {
                awSettings.r = iD;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setMixedContentMode(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.N != i) {
                awSettings.N = i;
                awSettings.m0.b();
            }
        }
    }

    @Deprecated
    public void setNavDump(boolean z) {
    }

    @Override // android.webkit.WebSettings
    public void setNeedInitialFocus(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.e0 = z;
        }
    }

    @Override // android.webkit.WebSettings
    public void setOffscreenPreRaster(boolean z) {
        this.a.L(z);
    }

    @Override // android.webkit.WebSettings
    public synchronized void setPluginState(WebSettings.PluginState pluginState) {
        this.b = pluginState;
    }

    public synchronized void setPluginsEnabled(boolean z) {
        this.b = z ? WebSettings.PluginState.ON : WebSettings.PluginState.OFF;
    }

    @Override // android.webkit.WebSettings
    public synchronized void setRenderPriority(WebSettings.RenderPriority renderPriority) {
    }

    @Override // android.webkit.WebSettings
    public void setSafeBrowsingEnabled(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            awSettings.U = Boolean.valueOf(z);
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setSansSerifFontFamily(String str) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (str != null) {
                if (!awSettings.k.equals(str)) {
                    awSettings.k = str;
                    awSettings.m0.b();
                }
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setSaveFormData(boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            return;
        }
        final AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.g0 != z) {
                awSettings.g0 = z;
                awSettings.m0.a(new Runnable(awSettings) { // from class: hp
                    public final AwSettings s;

                    {
                        this.s = awSettings;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AwSettings awSettings2 = this.s;
                        long j = awSettings2.l0;
                        if (j != 0) {
                            N.Mt45ai_h(j, awSettings2);
                        }
                    }
                });
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setSavePassword(boolean z) {
    }

    @Override // android.webkit.WebSettings
    public synchronized void setSerifFontFamily(String str) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (str != null) {
                if (!awSettings.l.equals(str)) {
                    awSettings.l = str;
                    awSettings.m0.b();
                }
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setStandardFontFamily(String str) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (str != null) {
                if (!awSettings.i.equals(str)) {
                    awSettings.i = str;
                    awSettings.m0.b();
                }
            }
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setSupportMultipleWindows(boolean z) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.A != z) {
                awSettings.A = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // android.webkit.WebSettings
    public void setSupportZoom(boolean z) {
        this.a.N(z);
    }

    @Override // android.webkit.WebSettings
    public synchronized void setTextZoom(int i) {
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            if (awSettings.h != i) {
                awSettings.h = i;
                awSettings.m0.b();
            }
        }
    }

    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
    }

    @Override // android.webkit.WebSettings
    public synchronized void setUseWideViewPort(boolean z) {
        this.a.O(z);
    }

    public synchronized void setUserAgent(int i) {
        AwSettings awSettings = this.a;
        Objects.requireNonNull(awSettings);
        if (i == 0) {
            awSettings.P(null);
        } else {
            Log.w("AwSettings", "setUserAgent not supported, ua=" + i);
        }
    }

    @Override // android.webkit.WebSettings
    public synchronized void setUserAgentString(String str) {
        this.a.P(str);
    }

    public void setVideoOverlayForEmbeddedEncryptedVideoEnabled(boolean z) {
    }

    @Override // android.webkit.WebSettings
    public synchronized boolean supportMultipleWindows() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.A;
        }
        return z;
    }

    @Override // android.webkit.WebSettings
    public boolean supportZoom() {
        boolean z;
        AwSettings awSettings = this.a;
        synchronized (awSettings.f) {
            z = awSettings.i0;
        }
        return z;
    }
}
