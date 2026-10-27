package defpackage;

import android.os.SystemClock;

/* JADX INFO: compiled from: TrustedWebActivityOpenTimeRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class bg3 implements hy1 {
    public final h20 e;
    public final jg3 f;
    public final z2 g;
    public long h;
    public long i;
    public boolean j;
    public boolean k;

    public bg3(q2 q2Var, h20 h20Var, jg3 jg3Var, z2 z2Var) {
        this.e = h20Var;
        this.f = jg3Var;
        this.g = z2Var;
        q2Var.f(this);
        h20Var.i.a(new aw(13, this));
    }

    public final void a() {
        if (this.i == 0) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - Math.max(this.i, this.h);
        boolean z = this.j;
        jg3 jg3Var = this.f;
        if (z) {
            jg3Var.getClass();
            oj2.X(jElapsedRealtime, "TrustedWebActivity.TimeInVerifiedOrigin.V2");
        } else {
            jg3Var.getClass();
            oj2.X(jElapsedRealtime, "TrustedWebActivity.TimeOutOfVerifiedOrigin.V2");
        }
    }

    @Override // defpackage.hy1
    public final void f() {
        this.h = SystemClock.elapsedRealtime();
    }

    @Override // defpackage.hy1
    public final void h() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.h;
        this.f.getClass();
        oj2.X(jElapsedRealtime, "BrowserServices.TwaOpenTime.V2");
        a();
        this.h = 0L;
    }
}
