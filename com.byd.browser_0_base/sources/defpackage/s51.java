package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import org.chromium.base.ApplicationStatus;
import org.chromium.ui.display.DisplayAndroidManager;

/* JADX INFO: compiled from: LaunchCauseMetrics.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s51 implements ApplicationStatus.d, ApplicationStatus.c {
    public static boolean h;

    @SuppressLint({"StaticFieldLeak"})
    public static Activity i;
    public static a j;
    public c e = new c();
    public b f = new b();
    public final Activity g;

    /* JADX INFO: compiled from: LaunchCauseMetrics.java */
    public class a implements ApplicationStatus.c {
        @Override // org.chromium.base.ApplicationStatus.c
        public final void d(Activity activity, int i) {
            if (i == 3) {
                s51.i = activity;
            }
            if (i == 6 && activity == s51.i) {
                s51.i = null;
            }
        }
    }

    /* JADX INFO: compiled from: LaunchCauseMetrics.java */
    public static class b {
        public boolean a;
        public boolean b;
    }

    /* JADX INFO: compiled from: LaunchCauseMetrics.java */
    public static class c {
        public boolean a;
        public boolean b;
        public boolean c;
    }

    public s51(Activity activity) {
        this.g = activity;
        if (j == null) {
            a aVar = new a();
            j = aVar;
            ApplicationStatus.g(aVar);
            if (ApplicationStatus.getStateForApplication() == 1) {
                i = ApplicationStatus.c;
            }
        }
        ApplicationStatus.e(this);
        ApplicationStatus.f(this, activity);
    }

    public abstract int a();

    public int b() {
        return 0;
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i2) {
        if (i2 == 6) {
            org.chromium.base.b<ApplicationStatus.d> bVar = ApplicationStatus.f;
            if (bVar != null) {
                bVar.c(this);
            }
            ApplicationStatus.i(this);
        }
        if (i2 == 4) {
            this.f.b = DisplayAndroidManager.b(this.g).getState() != 2;
        }
    }

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i2) {
        if (i2 == 3) {
            h = false;
            this.e = new c();
        }
    }
}
