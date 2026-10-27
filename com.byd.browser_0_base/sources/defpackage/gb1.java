package defpackage;

import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: MainIntentBehaviorMetrics.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gb1 {
    public static boolean b;
    public static boolean c;
    public final qm0 a = new qm0(10, this);

    public final void a(boolean z) {
        if (!c) {
            c = true;
            ApplicationStatus.e(new fb1());
        }
        if (b) {
            return;
        }
        b = true;
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jE = sharedPreferencesManager.e(0L, "MainIntent.LaunchTimestamp");
        int i = 0;
        int iD = sharedPreferencesManager.d(0, "MainIntent.LaunchCount");
        if (jCurrentTimeMillis - jE > 86400000) {
            if (jE != 0) {
                oj2.R(iD, "MobileStartup.DailyLaunchCount");
            }
            sharedPreferencesManager.n(jCurrentTimeMillis, "MainIntent.LaunchTimestamp");
        } else {
            i = iD;
        }
        sharedPreferencesManager.m(i + 1, "MainIntent.LaunchCount");
        oj2.U(!z ? 1 : 0, 2, "MobileStartup.LaunchType");
        SharedPreferencesManager.getInstance().c("Chrome.DefaultBrowserPromo.SessionCount");
        ThreadUtils.c().removeCallbacks(this.a);
    }
}
