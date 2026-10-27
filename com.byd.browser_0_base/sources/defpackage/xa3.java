package defpackage;

import android.os.SystemClock;

/* JADX INFO: compiled from: TimingMetric.java */
/* JADX INFO: loaded from: classes.dex */
public final class xa3 implements AutoCloseable {
    public final String e;
    public final int f;
    public long g;

    public xa3(String str, int i) {
        this.e = str;
        this.f = i;
    }

    public static xa3 q(String str) {
        xa3 xa3Var = new xa3(str, 1);
        xa3Var.g = SystemClock.uptimeMillis();
        return xa3Var;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        long j = this.g;
        if (j == 0) {
            return;
        }
        this.g = 0L;
        String str = this.e;
        int i = this.f;
        if (i == 0) {
            oj2.b0(SystemClock.uptimeMillis() - j, str);
        } else if (i == 1) {
            oj2.Y(SystemClock.uptimeMillis() - j, str);
        } else {
            if (i != 2) {
                return;
            }
            oj2.b0(SystemClock.currentThreadTimeMillis() - j, str);
        }
    }
}
