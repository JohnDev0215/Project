package defpackage;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: JankTrackerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class j31 implements i31 {
    public final e31 a;
    public final h31 b;

    public j31(Activity activity) {
        iq0 iq0Var = new iq0();
        hq0 hq0Var = new hq0(iq0Var);
        h31 h31Var = new h31(iq0Var);
        this.b = h31Var;
        e31 e31Var = new e31(activity, hq0Var, h31Var);
        this.a = e31Var;
        e31Var.a();
        Activity activity2 = e31Var.i.get();
        if (activity2 != null) {
            ApplicationStatus.f(e31Var, activity2);
            e31Var.d(activity2, ApplicationStatus.c(activity2));
            activity2.getWindow().addOnFrameMetricsAvailableListener(e31Var.e, e31Var.f.b());
        }
    }

    @Override // defpackage.i31
    public final void a(int i) {
        this.b.c(i);
    }

    @Override // defpackage.i31
    public final void b(int i) {
        this.b.a(i);
    }
}
