package p;

import android.os.SystemClock;
import android.util.Log;
import com.google.android.datatransport.Priority;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class l32 {
    public final double a;
    public final double b;
    public final long c;
    public final long d;
    public final int e;
    public final ArrayBlockingQueue f;
    public final ThreadPoolExecutor g;
    public final fo2 h;
    public final ww1 i;
    public int j;
    public long k;

    public l32(fo2 fo2Var, cc2 cc2Var, ww1 ww1Var) {
        double d = cc2Var.d;
        long j = ((long) cc2Var.f) * 1000;
        this.a = d;
        this.b = cc2Var.e;
        this.c = j;
        this.h = fo2Var;
        this.i = ww1Var;
        this.d = SystemClock.elapsedRealtime();
        int i = (int) d;
        this.e = i;
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(i);
        this.f = arrayBlockingQueue;
        this.g = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, arrayBlockingQueue);
        this.j = 0;
        this.k = 0L;
    }

    public final int a() {
        if (this.k == 0) {
            this.k = System.currentTimeMillis();
        }
        int iCurrentTimeMillis = (int) ((System.currentTimeMillis() - this.k) / this.c);
        int iMin = this.f.size() == this.e ? Math.min(100, this.j + iCurrentTimeMillis) : Math.max(0, this.j - iCurrentTimeMillis);
        if (this.j != iMin) {
            this.j = iMin;
            this.k = System.currentTimeMillis();
        }
        return iMin;
    }

    public final void b(ch chVar, bj2 bj2Var) {
        String str = chVar.b;
        Log.isLoggable("FirebaseCrashlytics", 3);
        boolean z = SystemClock.elapsedRealtime() - this.d < 2000;
        Priority priority = Priority.k;
        g40 g40Var = chVar.a;
        if (g40Var == null) {
            throw new NullPointerException("Null payload");
        }
        k32 k32Var = new k32(this, bj2Var, z, chVar);
        fo2 fo2Var = this.h;
        wi wiVar = fo2Var.a;
        if (wiVar == null) {
            throw new NullPointerException("Null transportContext");
        }
        String str2 = fo2Var.b;
        if (str2 == null) {
            throw new NullPointerException("Null transportName");
        }
        iy2 iy2Var = fo2Var.d;
        if (iy2Var == null) {
            throw new NullPointerException("Null transformer");
        }
        ae0 ae0Var = fo2Var.c;
        if (ae0Var == null) {
            throw new NullPointerException("Null encoding");
        }
        ho2 ho2Var = (ho2) fo2Var.e;
        ho2Var.getClass();
        wi wiVarC = wiVar.c(priority);
        gu0 gu0Var = new gu0(4);
        gu0Var.f = new HashMap();
        gu0Var.d = Long.valueOf(((lr2) ho2Var.a).a());
        gu0Var.e = Long.valueOf(((lr2) ho2Var.b).a());
        gu0Var.a = str2;
        gu0Var.r(new xd0(ae0Var, (byte[]) iy2Var.apply(g40Var)));
        gu0Var.b = null;
        ai aiVarD = gu0Var.d();
        t70 t70Var = (t70) ho2Var.c;
        t70Var.getClass();
        t70Var.b.execute(new g72(t70Var, wiVarC, k32Var, aiVarD, 1));
    }
}
