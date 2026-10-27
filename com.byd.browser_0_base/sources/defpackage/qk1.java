package defpackage;

import J.N;
import android.os.SystemClock;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: NativeUmaRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class qk1 implements yh3 {
    public final Map<String, Long> a = Collections.synchronizedMap(new HashMap());

    @Override // defpackage.yh3
    public final void a(String str, boolean z) {
        long jF = f(str);
        g(str, jF, N.MtKTTHie(str, jF, z));
    }

    @Override // defpackage.yh3
    public final void b(long j, String str) {
        N.MTDsfZGe(str, SystemClock.elapsedRealtime() - j);
    }

    @Override // defpackage.yh3
    public final void c(int i, int i2, int i3, int i4, String str) {
        long jF = f(str);
        g(str, jF, N.MILRV9Ch(str, jF, i, i2, i3, i4));
    }

    @Override // defpackage.yh3
    public final void d(int i, String str) {
        long jF = f(str);
        g(str, jF, N.Mk1ai9mx(str, jF, i));
    }

    @Override // defpackage.yh3
    public final void e(int i, int i2, int i3, int i4, String str) {
        long jF = f(str);
        g(str, jF, N.M$oMD214(str, jF, i, i2, i3, i4));
    }

    public final long f(String str) {
        Long l = this.a.get(str);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public final void g(String str, long j, long j2) {
        if (j != j2) {
            this.a.put(str, Long.valueOf(j2));
        }
    }
}
