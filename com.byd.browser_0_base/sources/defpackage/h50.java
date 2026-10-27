package defpackage;

import J.N;
import android.os.SystemClock;
import java.util.function.BooleanSupplier;

/* JADX INFO: compiled from: CustomTabsOpenTimeRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class h50 implements kr2 {
    public final w20 e;
    public final BooleanSupplier f;
    public final oj g;
    public final String h;
    public long i;
    public int j;

    public h50(r2 r2Var, w20 w20Var, t20 t20Var, oj ojVar) {
        r2Var.f(this);
        this.e = w20Var;
        this.f = t20Var;
        this.g = ojVar;
        this.h = ojVar.j();
    }

    @Override // defpackage.kr2
    public final void j() {
        this.i = SystemClock.elapsedRealtime();
        this.j = 2;
    }

    @Override // defpackage.kr2
    public final void l() {
        oj2.U(this.j, 3, "CustomTabs.CloseCause");
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.i;
        int i = this.j;
        BooleanSupplier booleanSupplier = this.f;
        if (i == 2 && booleanSupplier.getAsBoolean()) {
            oj2.X(jElapsedRealtime, "CustomTabs.AutoclosedSessionDuration");
        }
        if (booleanSupplier.getAsBoolean()) {
            long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
            boolean z = this.j != 2;
            boolean zO = this.g.O();
            long jMin = Math.min(jElapsedRealtime / 1000, 300L);
            String str = this.h;
            if (str == null) {
                str = "";
            }
            N.MGdXu4nu(jCurrentTimeMillis, str, jMin, z, zO);
        }
        this.i = 0L;
    }
}
