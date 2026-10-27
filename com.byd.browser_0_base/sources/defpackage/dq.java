package defpackage;

import android.util.Log;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: ChromeInactivityTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class dq implements kr2, hy1, v80 {
    public q2 e;

    public static void a(long j) {
        long jE = SharedPreferencesManager.getInstance().e(-1L, "Chrome.StartSurface.LastVisibleTimeMs");
        SharedPreferencesManager.getInstance().n(j, "Chrome.StartSurface.LastVisibleTimeMs");
        Log.i("cr_InactivityTracker", "Last visible time read from the SharedPreference is:" + jE + ".");
        oj2.O("Startup.Android.IsLastVisibleTimeLogged", jE != -1);
    }

    @Override // defpackage.hy1
    public final void f() {
        long jE = SharedPreferencesManager.getInstance().e(-1L, "ChromeTabbedActivity.BackgroundTimeMs");
        SharedPreferencesManager.getInstance().n(-1L, "ChromeTabbedActivity.BackgroundTimeMs");
        Log.i("cr_InactivityTracker", "Last background time read from the SharedPreference is:" + jE + ".");
        oj2.O("Startup.Android.IsLastBackgroundTimeLogged", jE != -1);
        if (jE != -1) {
            oj2.T("Startup.Android.DurationSinceLastBackgroundTime", System.currentTimeMillis() - jE, 1L, 3600000L, 100);
        }
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.e.e(this);
    }

    @Override // defpackage.hy1
    public final void h() {
    }

    @Override // defpackage.kr2
    public final void j() {
    }

    @Override // defpackage.kr2
    public final void l() {
    }
}
