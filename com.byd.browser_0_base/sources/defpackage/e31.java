package defpackage;

import android.app.Activity;
import android.os.Handler;
import defpackage.h31;
import java.lang.ref.WeakReference;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: JankActivityTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class e31 implements ApplicationStatus.c {
    public final hq0 e;
    public final h31 f;
    public final ThreadUtils.a g = new ThreadUtils.a();
    public final u80 h = new u80();
    public final WeakReference<Activity> i;

    public e31(Activity activity, hq0 hq0Var, h31 h31Var) {
        this.i = new WeakReference<>(activity);
        this.e = hq0Var;
        this.f = h31Var;
    }

    public final void a() {
        this.g.getClass();
        this.h.getClass();
    }

    public final void b() {
        a();
        h31 h31Var = this.f;
        if (h31Var.e.getAndSet(true)) {
            return;
        }
        h31Var.c(1);
        h31Var.b().postDelayed(h31Var.b, 30000L);
    }

    public final void c() {
        a();
        h31 h31Var = this.f;
        if (h31Var.e.getAndSet(false)) {
            Handler handlerB = h31Var.b();
            h31.a aVar = h31Var.b;
            handlerB.removeCallbacks(aVar);
            h31Var.b().post(aVar);
        }
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        a();
        hq0 hq0Var = this.e;
        if (i == 2 || i == 3) {
            b();
            a();
            hq0Var.b.set(true);
        } else if (i == 4) {
            b();
            a();
            hq0Var.b.set(false);
        } else {
            if (i != 5) {
                return;
            }
            a();
            hq0Var.b.set(false);
            c();
        }
    }
}
