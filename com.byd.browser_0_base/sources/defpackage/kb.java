package defpackage;

import android.app.Activity;

/* JADX INFO: compiled from: AutofillSessionLifetimeController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kb implements v80 {
    public Activity e;
    public final jb f;

    public kb(Activity activity, r2 r2Var, z2 z2Var) {
        this.e = activity;
        this.f = new jb(this, z2Var, r2Var);
        r2Var.f(this);
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.f.destroy();
        this.e = null;
    }
}
