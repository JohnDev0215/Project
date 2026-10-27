package defpackage;

import android.os.SystemClock;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: SessionsInvalidationManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class gj2 implements ApplicationStatus.d {
    public static gj2 i;
    public final wb2 e;
    public final Profile f;
    public boolean g = false;
    public int h;

    public gj2(Profile profile, wb2 wb2Var) {
        this.f = profile;
        this.e = wb2Var;
        ApplicationStatus.e(this);
    }

    public final void a(long j, boolean z) {
        wb2 wb2Var = this.e;
        Runnable runnable = wb2Var.b;
        if (runnable != null) {
            wb2Var.a.removeCallbacks(runnable);
            wb2Var.b = null;
        }
        wb2Var.c = null;
        if (this.g == z) {
            return;
        }
        o40 o40Var = new o40(this, 2, z);
        Runnable runnable2 = wb2Var.b;
        if (runnable2 != null) {
            wb2Var.a.removeCallbacks(runnable2);
            wb2Var.b = null;
        }
        wb2Var.c = o40Var;
        wb2Var.d = SystemClock.elapsedRealtime() + j;
        wb2Var.a();
    }

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i2) {
        Runnable runnable;
        wb2 wb2Var = this.e;
        if (i2 == 1) {
            wb2Var.a();
        } else {
            if (i2 != 2 || (runnable = wb2Var.b) == null) {
                return;
            }
            wb2Var.a.removeCallbacks(runnable);
            wb2Var.b = null;
        }
    }
}
