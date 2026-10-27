package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: OfflineIndicatorMetricsDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oq1 {
    public static final qh1 i = new qh1(8);
    public boolean a;
    public boolean b;
    public long c;
    public long d;
    public long e;
    public long f;
    public long g;
    public int h;

    public oq1() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        if (sharedPreferencesManager.contains("Chrome.OfflineIndicatorV2.WallTimeShownMs")) {
            this.c = sharedPreferencesManager.f("Chrome.OfflineIndicatorV2.WallTimeShownMs");
            this.a = true;
        }
        if (sharedPreferencesManager.contains("Chrome.OfflineIndicatorV2.LastUpdateWallTimeMs")) {
            this.d = sharedPreferencesManager.f("Chrome.OfflineIndicatorV2.LastUpdateWallTimeMs");
        }
        if (sharedPreferencesManager.contains("Chrome.OfflineIndicatorV2.TimeInForegroundMs")) {
            this.e = sharedPreferencesManager.f("Chrome.OfflineIndicatorV2.TimeInForegroundMs");
        }
        if (sharedPreferencesManager.contains("Chrome.OfflineIndicatorV2.TimeInBackgroundMs")) {
            this.f = sharedPreferencesManager.f("Chrome.OfflineIndicatorV2.TimeInBackgroundMs");
        }
        if (sharedPreferencesManager.contains("Chrome.OfflineIndicatorV2.FirstTimeInForegroundMs")) {
            this.g = sharedPreferencesManager.f("Chrome.OfflineIndicatorV2.FirstTimeInForegroundMs");
        }
        if (sharedPreferencesManager.contains("Chrome.OfflineIndicatorV2.NumTimesBackgrounded")) {
            this.h = sharedPreferencesManager.d(0, "Chrome.OfflineIndicatorV2.NumTimesBackgrounded");
        }
    }

    public final void a() {
        if (this.a) {
            if (this.b) {
                c();
            } else {
                b();
            }
            if (i.d() - this.c >= 0) {
                oj2.T("OfflineIndicator.ShownDurationV2", this.e + this.f, 1L, 3600000L, 100);
                oj2.X(this.e, "OfflineIndicator.ShownDurationV2.InForeground");
                oj2.Q(this.h, "OfflineIndicator.ShownDurationV2.NumTimesBackgrounded");
                if (SharedPreferencesManager.getInstance().contains("Chrome.OfflineIndicatorV2.TimeInBackgroundMs")) {
                    oj2.X(this.f, "OfflineIndicator.ShownDurationV2.InBackground");
                    oj2.X(this.g, "OfflineIndicator.ShownDurationV2.UntilFirstTimeBackgrounded");
                } else {
                    oj2.X(this.e, "OfflineIndicator.ShownDurationV2.InForegroundWithoutBeingBackgrounded");
                }
            }
            this.c = 0L;
            this.d = 0L;
            this.e = 0L;
            this.f = 0L;
            this.g = 0L;
            this.h = 0;
            this.a = false;
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            sharedPreferencesManager.removeKey("Chrome.OfflineIndicatorV2.WallTimeShownMs");
            sharedPreferencesManager.removeKey("Chrome.OfflineIndicatorV2.LastUpdateWallTimeMs");
            sharedPreferencesManager.removeKey("Chrome.OfflineIndicatorV2.TimeInForegroundMs");
            sharedPreferencesManager.removeKey("Chrome.OfflineIndicatorV2.TimeInBackgroundMs");
            sharedPreferencesManager.removeKey("Chrome.OfflineIndicatorV2.FirstTimeInForegroundMs");
            sharedPreferencesManager.removeKey("Chrome.OfflineIndicatorV2.NumTimesBackgrounded");
        }
    }

    public final void b() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        long jD = i.d();
        long j = this.f + (jD - this.d);
        this.f = j;
        sharedPreferencesManager.n(j, "Chrome.OfflineIndicatorV2.TimeInBackgroundMs");
        this.d = jD;
        SharedPreferencesManager.getInstance().n(this.d, "Chrome.OfflineIndicatorV2.LastUpdateWallTimeMs");
    }

    public final void c() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        long jD = i.d();
        long j = this.e + (jD - this.d);
        this.e = j;
        sharedPreferencesManager.n(j, "Chrome.OfflineIndicatorV2.TimeInForegroundMs");
        this.d = jD;
        sharedPreferencesManager.n(jD, "Chrome.OfflineIndicatorV2.LastUpdateWallTimeMs");
    }
}
