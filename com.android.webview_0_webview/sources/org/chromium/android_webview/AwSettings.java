package org.chromium.android_webview;

import J.N;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import defpackage.AbstractC1565tp;
import defpackage.C1510sp;
import defpackage.HandlerC1455rp;
import defpackage.InterfaceC1620up;
import defpackage.Is;
import java.util.Objects;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwSettings;
import org.chromium.android_webview.safe_browsing.AwSafeBrowsingConfigHelper;
import org.chromium.base.BuildInfo;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwSettings {
    public static final Object n0 = new Object();
    public static boolean o0;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public String f27J;
    public float K;
    public boolean L;
    public boolean M;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean T;
    public Boolean U;
    public final boolean V;
    public final boolean W;
    public final boolean X;
    public final boolean Y;
    public final boolean Z;
    public boolean a0;
    public final boolean c;
    public boolean c0;
    public InterfaceC1620up d;
    public boolean g0;
    public int h;
    public boolean h0;
    public boolean i0;
    public boolean j0;
    public boolean k0;
    public long l0;
    public final C1510sp m0;
    public String p;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;
    public int a = 1;
    public int b = 2;
    public double e = 1.0d;
    public final Object f = new Object();
    public int g = 2;
    public String i = "sans-serif";
    public String j = "monospace";
    public String k = "sans-serif";
    public String l = "serif";
    public String m = "cursive";
    public String n = "fantasy";
    public String o = "UTF-8";
    public int q = 8;
    public int r = 8;
    public int s = 16;
    public int t = 13;
    public boolean u = true;
    public boolean v = true;
    public boolean I = true;
    public int N = 1;
    public int S = 0;
    public boolean b0 = true;
    public int d0 = -1;
    public boolean e0 = true;
    public boolean f0 = true;

    public AwSettings(Context context, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6 = true;
        this.h = 100;
        this.g0 = Build.VERSION.SDK_INT < 26;
        this.i0 = true;
        this.k0 = true;
        boolean z7 = context.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) == 0;
        synchronized (this.f) {
            this.c = z7;
            this.a0 = !z7;
            this.m0 = new C1510sp(this);
            if (z) {
                this.x = true;
                this.y = true;
            }
            this.p = AbstractC1565tp.a;
            this.L = !context.getPackageManager().hasSystemFeature("android.hardware.touchscreen");
            this.Z = Settings.System.getInt(context.getContentResolver(), "show_password", 1) == 1;
            this.h = (int) (this.h * context.getResources().getConfiguration().fontScale);
            this.V = z2;
            this.W = z3;
            this.X = z4;
            this.Y = z5;
            if (BuildInfo.e()) {
                z6 = false;
            }
            this.c0 = z6;
        }
    }

    public static void a(AwSettings awSettings) {
        Objects.requireNonNull(awSettings);
        ThreadUtils.b();
        long j = awSettings.l0;
        if (j != 0) {
            N.M_GO18v7(j, awSettings);
        }
    }

    public static void b(AwSettings awSettings) {
        Objects.requireNonNull(awSettings);
        ThreadUtils.b();
        long j = awSettings.l0;
        if (j != 0) {
            N.M2EmJ2$z(j, awSettings);
        }
    }

    public static void c(AwSettings awSettings) {
        Objects.requireNonNull(awSettings);
        ThreadUtils.b();
        long j = awSettings.l0;
        if (j != 0) {
            N.MuI9Jt0j(j, awSettings);
        }
    }

    private boolean getAcceptThirdPartyCookiesLocked() {
        return this.T;
    }

    private boolean getAllowEmptyDocumentPersistenceLocked() {
        return this.W;
    }

    private boolean getAllowFileAccessFromFileURLsLocked() {
        return this.y;
    }

    private boolean getAllowGeolocationOnInsecureOrigins() {
        return this.X;
    }

    private boolean getAllowMixedContentAutoupgradesLocked() {
        return this.N == 2;
    }

    private boolean getAllowRunningInsecureContentLocked() {
        return this.N == 0;
    }

    public static boolean getAllowSniffingFileUrls() {
        return Is.a.getApplicationInfo().targetSdkVersion < 28;
    }

    private boolean getAllowUniversalAccessFromFileURLsLocked() {
        return this.x;
    }

    private boolean getAppCacheEnabledLocked() {
        boolean z;
        if (!this.B) {
            return false;
        }
        synchronized (n0) {
            z = o0;
        }
        return z;
    }

    private boolean getCSSHexAlphaColorEnabledLocked() {
        return this.O;
    }

    private String getCursiveFontFamilyLocked() {
        return this.m;
    }

    private double getDIPScaleLocked() {
        return this.e;
    }

    private boolean getDatabaseEnabledLocked() {
        return this.D;
    }

    private int getDefaultFixedFontSizeLocked() {
        return this.t;
    }

    private int getDefaultFontSizeLocked() {
        return this.s;
    }

    private String getDefaultTextEncodingLocked() {
        return this.o;
    }

    private String getDefaultVideoPosterURLLocked() {
        return this.f27J;
    }

    private boolean getDoNotUpdateSelectionOnMutatingSelectionRange() {
        return this.Y;
    }

    private boolean getDomStorageEnabledLocked() {
        return this.C;
    }

    private boolean getEnableSupportedHardwareAcceleratedFeaturesLocked() {
        return this.M;
    }

    private String getFantasyFontFamilyLocked() {
        return this.n;
    }

    private String getFixedFontFamilyLocked() {
        return this.j;
    }

    private boolean getForceZeroLayoutHeightLocked() {
        return this.G;
    }

    private boolean getFullscreenSupportedLocked() {
        return this.h0;
    }

    private boolean getImagesEnabledLocked() {
        return this.v;
    }

    private float getInitialPageScalePercentLocked() {
        return this.K;
    }

    private boolean getJavaScriptCanOpenWindowsAutomaticallyLocked() {
        return this.z;
    }

    private boolean getJavaScriptEnabledLocked() {
        return this.w;
    }

    private boolean getLoadWithOverviewModeLocked() {
        return this.H;
    }

    private boolean getLoadsImagesAutomaticallyLocked() {
        return this.u;
    }

    private boolean getMediaPlaybackRequiresUserGestureLocked() {
        return this.I;
    }

    private int getMinimumFontSizeLocked() {
        return this.q;
    }

    private int getMinimumLogicalFontSizeLocked() {
        return this.r;
    }

    private boolean getOffscreenPreRasterLocked() {
        return this.R;
    }

    private boolean getPasswordEchoEnabledLocked() {
        return this.Z;
    }

    private boolean getRecordFullDocument() {
        return AwContentsStatics.c;
    }

    private String getSansSerifFontFamilyLocked() {
        return this.k;
    }

    private boolean getSaveFormDataLocked() {
        return this.g0;
    }

    private boolean getScrollTopLeftInteropEnabledLocked() {
        return this.P;
    }

    private String getSerifFontFamilyLocked() {
        return this.l;
    }

    private boolean getSpatialNavigationLocked() {
        return this.L;
    }

    private String getStandardFontFamilyLocked() {
        return this.i;
    }

    private boolean getSupportLegacyQuirksLocked() {
        return this.V;
    }

    private boolean getSupportMultipleWindowsLocked() {
        return this.A;
    }

    private boolean getTextAutosizingEnabledLocked() {
        return this.g == 3;
    }

    private int getTextSizePercentLocked() {
        return this.h;
    }

    private boolean getUseStricMixedContentCheckingLocked() {
        return this.N == 1;
    }

    private boolean getUseWideViewportLocked() {
        return this.E;
    }

    private String getUserAgentLocked() {
        return this.p;
    }

    private boolean getWillSuppressErrorPageLocked() {
        return this.Q;
    }

    private boolean getZeroLayoutHeightDisablesViewportQuirkLocked() {
        return this.F;
    }

    public static String l() {
        return AbstractC1565tp.a;
    }

    private void nativeAwSettingsGone(long j) {
        this.l0 = 0L;
    }

    private void populateWebPreferences(long j) {
        synchronized (this.f) {
            N.MrAp8rc4(this.l0, this, j);
        }
    }

    private boolean supportsDoubleTapZoomLocked() {
        return this.i0 && this.j0 && this.E;
    }

    private void updateEverything() {
        synchronized (this.f) {
            N.Mq8l4Aae(this.l0, this);
            F(supportsDoubleTapZoomLocked(), R());
        }
    }

    public String A() {
        String standardFontFamilyLocked;
        synchronized (this.f) {
            standardFontFamilyLocked = getStandardFontFamilyLocked();
        }
        return standardFontFamilyLocked;
    }

    public int B() {
        int textSizePercentLocked;
        synchronized (this.f) {
            textSizePercentLocked = getTextSizePercentLocked();
        }
        return textSizePercentLocked;
    }

    public boolean C() {
        boolean useWideViewportLocked;
        synchronized (this.f) {
            useWideViewportLocked = getUseWideViewportLocked();
        }
        return useWideViewportLocked;
    }

    public String D() {
        String userAgentLocked;
        synchronized (this.f) {
            userAgentLocked = getUserAgentLocked();
        }
        return userAgentLocked;
    }

    public boolean E() {
        boolean willSuppressErrorPageLocked;
        synchronized (this.f) {
            willSuppressErrorPageLocked = getWillSuppressErrorPageLocked();
        }
        return willSuppressErrorPageLocked;
    }

    public final void F(final boolean z, final boolean z2) {
        C1510sp c1510sp = this.m0;
        Runnable runnable = new Runnable(this, z, z2) { // from class: lp
            public final AwSettings s;
            public final boolean t;
            public final boolean u;

            {
                this.s = this;
                this.t = z;
                this.u = z2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AwSettings awSettings = this.s;
                boolean z3 = this.t;
                boolean z4 = this.u;
                synchronized (awSettings.f) {
                    if (awSettings.d != null) {
                        AwContents awContents = ((C1453rn) awSettings.d).a;
                        if (!awContents.t(0)) {
                            GestureListenerManagerImpl gestureListenerManagerImplO = GestureListenerManagerImpl.O(awContents.A);
                            long j = gestureListenerManagerImplO.x;
                            if (j != 0) {
                                N.MrZmTSS9(j, gestureListenerManagerImplO, z3);
                            }
                            long j2 = gestureListenerManagerImplO.x;
                            if (j2 != 0) {
                                N.M6a5zchR(j2, gestureListenerManagerImplO, z4);
                            }
                        }
                    }
                }
            }
        };
        Handler handler = c1510sp.a;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    public void G(String str) {
        boolean z;
        synchronized (n0) {
            z = true;
            if (o0 || str == null || str.isEmpty()) {
                z = false;
            } else {
                o0 = true;
            }
        }
        if (z) {
            synchronized (this.f) {
                this.m0.b();
            }
        }
    }

    public void H(boolean z) {
        synchronized (this.f) {
            if (this.j0 != z) {
                this.j0 = z;
                F(supportsDoubleTapZoomLocked(), R());
            }
        }
    }

    public void I(int i) {
        synchronized (this.f) {
            if (this.b != i) {
                this.b = i;
                this.m0.b();
            }
        }
    }

    public void J(int i) {
        synchronized (this.f) {
            if (this.a != i) {
                this.a = i;
                this.m0.b();
            }
        }
    }

    public void K(int i) {
        synchronized (this.f) {
            if (this.g != i) {
                this.g = i;
                this.m0.b();
            }
        }
    }

    public void L(boolean z) {
        synchronized (this.f) {
            if (z != this.R) {
                this.R = z;
                this.m0.a(new Runnable(this) { // from class: mp
                    public final AwSettings s;

                    {
                        this.s = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AwSettings awSettings = this.s;
                        long j = awSettings.l0;
                        if (j != 0) {
                            N.ML5KB9Yq(j, awSettings);
                        }
                    }
                });
            }
        }
    }

    public void M(boolean z) {
        synchronized (this.f) {
            if (this.L != z) {
                this.L = z;
                this.m0.b();
            }
        }
    }

    public void N(boolean z) {
        synchronized (this.f) {
            if (this.i0 != z) {
                this.i0 = z;
                F(supportsDoubleTapZoomLocked(), R());
            }
        }
    }

    public void O(boolean z) {
        synchronized (this.f) {
            if (this.E != z) {
                this.E = z;
                F(supportsDoubleTapZoomLocked(), R());
                this.m0.b();
            }
        }
    }

    public void P(String str) {
        synchronized (this.f) {
            String str2 = this.p;
            if (str == null || str.length() == 0) {
                this.p = AbstractC1565tp.a;
            } else {
                this.p = str;
            }
            if (!str2.equals(this.p)) {
                this.m0.a(new Runnable(this) { // from class: ip
                    public final AwSettings s;

                    {
                        this.s = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AwSettings awSettings = this.s;
                        long j = awSettings.l0;
                        if (j != 0) {
                            N.MShmU87b(j, awSettings);
                        }
                    }
                });
            }
        }
    }

    public void Q(WebContents webContents) {
        synchronized (this.f) {
            if (this.l0 != 0) {
                N.MtdU4vpL(this.l0, this);
            }
            if (webContents != null) {
                C1510sp c1510sp = this.m0;
                if (c1510sp.a == null) {
                    c1510sp.a = new HandlerC1455rp(c1510sp, ThreadUtils.d());
                }
                long jM4GPw_sx = N.M4GPw_sx(this, webContents);
                this.l0 = jM4GPw_sx;
                N.Mq8l4Aae(jM4GPw_sx, this);
                F(supportsDoubleTapZoomLocked(), R());
            }
        }
    }

    public final boolean R() {
        return this.i0 && this.j0;
    }

    public final int d(int i) {
        if (i < 1) {
            return 1;
        }
        if (i > 72) {
            return 72;
        }
        return i;
    }

    public boolean e() {
        boolean allowFileAccessFromFileURLsLocked;
        synchronized (this.f) {
            allowFileAccessFromFileURLsLocked = getAllowFileAccessFromFileURLsLocked();
        }
        return allowFileAccessFromFileURLsLocked;
    }

    public boolean f() {
        boolean allowUniversalAccessFromFileURLsLocked;
        synchronized (this.f) {
            allowUniversalAccessFromFileURLsLocked = getAllowUniversalAccessFromFileURLsLocked();
        }
        return allowUniversalAccessFromFileURLsLocked;
    }

    public boolean g() {
        boolean z;
        synchronized (this.f) {
            z = this.a0;
        }
        return z;
    }

    public boolean getAllowFileAccess() {
        boolean z;
        synchronized (this.f) {
            z = this.c0;
        }
        return z;
    }

    public int getForceDarkBehaviorLocked() {
        return this.b;
    }

    public int getForceDarkModeLocked() {
        return this.a;
    }

    public String h() {
        String cursiveFontFamilyLocked;
        synchronized (this.f) {
            cursiveFontFamilyLocked = getCursiveFontFamilyLocked();
        }
        return cursiveFontFamilyLocked;
    }

    public int i() {
        int defaultFixedFontSizeLocked;
        synchronized (this.f) {
            defaultFixedFontSizeLocked = getDefaultFixedFontSizeLocked();
        }
        return defaultFixedFontSizeLocked;
    }

    public int j() {
        int defaultFontSizeLocked;
        synchronized (this.f) {
            defaultFontSizeLocked = getDefaultFontSizeLocked();
        }
        return defaultFontSizeLocked;
    }

    public String k() {
        String defaultTextEncodingLocked;
        synchronized (this.f) {
            defaultTextEncodingLocked = getDefaultTextEncodingLocked();
        }
        return defaultTextEncodingLocked;
    }

    public int m() {
        int i;
        synchronized (this.f) {
            i = this.S;
        }
        return i;
    }

    public String n() {
        String fantasyFontFamilyLocked;
        synchronized (this.f) {
            fantasyFontFamilyLocked = getFantasyFontFamilyLocked();
        }
        return fantasyFontFamilyLocked;
    }

    public String o() {
        String fixedFontFamilyLocked;
        synchronized (this.f) {
            fixedFontFamilyLocked = getFixedFontFamilyLocked();
        }
        return fixedFontFamilyLocked;
    }

    public boolean p() {
        boolean javaScriptCanOpenWindowsAutomaticallyLocked;
        synchronized (this.f) {
            javaScriptCanOpenWindowsAutomaticallyLocked = getJavaScriptCanOpenWindowsAutomaticallyLocked();
        }
        return javaScriptCanOpenWindowsAutomaticallyLocked;
    }

    public boolean q() {
        boolean loadWithOverviewModeLocked;
        synchronized (this.f) {
            loadWithOverviewModeLocked = getLoadWithOverviewModeLocked();
        }
        return loadWithOverviewModeLocked;
    }

    public boolean r() {
        boolean loadsImagesAutomaticallyLocked;
        synchronized (this.f) {
            loadsImagesAutomaticallyLocked = getLoadsImagesAutomaticallyLocked();
        }
        return loadsImagesAutomaticallyLocked;
    }

    public boolean s() {
        boolean mediaPlaybackRequiresUserGestureLocked;
        synchronized (this.f) {
            mediaPlaybackRequiresUserGestureLocked = getMediaPlaybackRequiresUserGestureLocked();
        }
        return mediaPlaybackRequiresUserGestureLocked;
    }

    public int t() {
        int minimumFontSizeLocked;
        synchronized (this.f) {
            minimumFontSizeLocked = getMinimumFontSizeLocked();
        }
        return minimumFontSizeLocked;
    }

    public int u() {
        int minimumLogicalFontSizeLocked;
        synchronized (this.f) {
            minimumLogicalFontSizeLocked = getMinimumLogicalFontSizeLocked();
        }
        return minimumLogicalFontSizeLocked;
    }

    public boolean v() {
        boolean offscreenPreRasterLocked;
        synchronized (this.f) {
            offscreenPreRasterLocked = getOffscreenPreRasterLocked();
        }
        return offscreenPreRasterLocked;
    }

    public boolean w() {
        synchronized (this.f) {
            if (this.U == null) {
                return AwSafeBrowsingConfigHelper.b;
            }
            return this.U.booleanValue();
        }
    }

    public String x() {
        String sansSerifFontFamilyLocked;
        synchronized (this.f) {
            sansSerifFontFamilyLocked = getSansSerifFontFamilyLocked();
        }
        return sansSerifFontFamilyLocked;
    }

    public boolean y() {
        boolean saveFormDataLocked;
        synchronized (this.f) {
            saveFormDataLocked = getSaveFormDataLocked();
        }
        return saveFormDataLocked;
    }

    public String z() {
        String serifFontFamilyLocked;
        synchronized (this.f) {
            serifFontFamilyLocked = getSerifFontFamilyLocked();
        }
        return serifFontFamilyLocked;
    }
}
