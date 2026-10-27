package p;

import android.os.SystemClock;
import com.spotify.contexts.MonotonicClock;

/* JADX INFO: loaded from: classes.dex */
public final class al1 implements di0 {
    public final zb0 a;
    public final jv b;

    public al1(zb0 zb0Var, jv jvVar) {
        this.a = zb0Var;
        this.b = jvVar;
    }

    @Override // p.di0
    public final String a() {
        return "context_monotonic_clock";
    }

    @Override // p.di0
    public final com.google.protobuf.i getData() {
        int i;
        yk1 yk1VarG = MonotonicClock.g();
        jv jvVar = this.b;
        synchronized (jvVar) {
            i = jvVar.b;
            if (i == -1) {
                i = ((ww1) ((wg1) jvVar.c).a).t().getInt("monotonic_clock_id", -1);
                if (i == -1) {
                    no1 no1Var = (no1) ((nm) jvVar.d);
                    no1Var.a.e(((ay2) no1Var.b).d(), "boot_count");
                    ((wg1) jvVar.c).e(1, "monotonic_clock_id");
                    jvVar.b = 1;
                    i = 1;
                } else {
                    no1 no1Var2 = (no1) ((nm) jvVar.d);
                    wg1 wg1Var = no1Var2.a;
                    int i2 = ((ww1) wg1Var.a).t().getInt("boot_count", -1);
                    int iD = ((ay2) no1Var2.b).d();
                    if (i2 == -1 || i2 < iD) {
                        wg1Var.e(iD, "boot_count");
                        i++;
                        ((wg1) jvVar.c).e(i, "monotonic_clock_id");
                    }
                    jvVar.b = i;
                }
            }
        }
        yk1VarG.d(i);
        this.a.getClass();
        yk1VarG.e(SystemClock.elapsedRealtime());
        return yk1VarG.build();
    }
}
