package p;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class q80 implements rp1, qy2 {
    public final Context c;
    public final int e;
    public final rx2 k;
    public final zh2 l;
    public final androidx.work.impl.constraints.a m;
    public final Object n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ob2 f171p;
    public final xx2 q;
    public PowerManager.WakeLock r;
    public boolean s;
    public final xf2 t;
    public final wk0 u;
    public volatile r61 v;

    static {
        vc1.d("DelayMetCommandHandler");
    }

    public q80(Context context, int i, zh2 zh2Var, xf2 xf2Var) {
        this.c = context;
        this.e = i;
        this.l = zh2Var;
        this.k = xf2Var.a;
        this.t = xf2Var;
        nn2 nn2Var = zh2Var.m.f206p;
        yx2 yx2Var = zh2Var.e;
        this.f171p = yx2Var.a;
        this.q = yx2Var.d;
        this.u = yx2Var.b;
        this.m = new androidx.work.impl.constraints.a(nn2Var);
        this.s = false;
        this.o = 0;
        this.n = new Object();
    }

    public static void a(q80 q80Var) {
        if (q80Var.o != 0) {
            vc1 vc1VarC = vc1.c();
            Objects.toString(q80Var.k);
            vc1VarC.getClass();
            return;
        }
        q80Var.o = 1;
        vc1 vc1VarC2 = vc1.c();
        Objects.toString(q80Var.k);
        vc1VarC2.getClass();
        if (!q80Var.l.l.g(q80Var.t, null)) {
            q80Var.d();
            return;
        }
        sy2 sy2Var = q80Var.l.k;
        rx2 rx2Var = q80Var.k;
        synchronized (sy2Var.d) {
            vc1 vc1VarC3 = vc1.c();
            Objects.toString(rx2Var);
            vc1VarC3.getClass();
            sy2Var.a(rx2Var);
            ry2 ry2Var = new ry2(sy2Var, rx2Var);
            sy2Var.b.put(rx2Var, ry2Var);
            sy2Var.c.put(rx2Var, q80Var);
            sy2Var.a.a.postDelayed(ry2Var, 600000L);
        }
    }

    public static void b(q80 q80Var) {
        boolean z;
        rx2 rx2Var = q80Var.k;
        String str = rx2Var.a;
        if (q80Var.o >= 2) {
            vc1.c().getClass();
            return;
        }
        q80Var.o = 2;
        vc1.c().getClass();
        Context context = q80Var.c;
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        tu.d(intent, rx2Var);
        xx2 xx2Var = q80Var.q;
        int i = q80Var.e;
        zh2 zh2Var = q80Var.l;
        xx2Var.execute(new a52(i, intent, zh2Var));
        qx1 qx1Var = zh2Var.l;
        String str2 = rx2Var.a;
        synchronized (qx1Var.k) {
            z = qx1Var.c(str2) != null;
        }
        if (!z) {
            vc1.c().getClass();
            return;
        }
        vc1.c().getClass();
        Intent intent2 = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent2.setAction("ACTION_SCHEDULE_WORK");
        tu.d(intent2, rx2Var);
        xx2Var.execute(new a52(i, intent2, zh2Var));
    }

    @Override // p.rp1
    public final void c(ky2 ky2Var, bz bzVar) {
        boolean z = bzVar instanceof zy;
        ob2 ob2Var = this.f171p;
        if (z) {
            ob2Var.execute(new p80(this, 2));
        } else {
            ob2Var.execute(new p80(this, 3));
        }
    }

    public final void d() {
        synchronized (this.n) {
            try {
                if (this.v != null) {
                    this.v.i(null);
                }
                this.l.k.a(this.k);
                PowerManager.WakeLock wakeLock = this.r;
                if (wakeLock != null && wakeLock.isHeld()) {
                    vc1 vc1VarC = vc1.c();
                    Objects.toString(this.r);
                    Objects.toString(this.k);
                    vc1VarC.getClass();
                    this.r.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void e() {
        String str = this.k.a;
        Context context = this.c;
        StringBuilder sbF = hf2.f(str, " (");
        sbF.append(this.e);
        sbF.append(")");
        this.r = sv2.a(context, sbF.toString());
        vc1 vc1VarC = vc1.c();
        Objects.toString(this.r);
        vc1VarC.getClass();
        this.r.acquire();
        ky2 ky2VarH = this.l.m.i.v().h(str);
        if (ky2VarH == null) {
            this.f171p.execute(new p80(this, 0));
            return;
        }
        boolean zB = ky2VarH.b();
        this.s = zB;
        if (zB) {
            this.v = androidx.work.impl.constraints.b.a(this.m, ky2VarH, this.u, this);
        } else {
            vc1.c().getClass();
            this.f171p.execute(new p80(this, 1));
        }
    }

    public final void f(boolean z) {
        vc1 vc1VarC = vc1.c();
        rx2 rx2Var = this.k;
        Objects.toString(rx2Var);
        vc1VarC.getClass();
        d();
        int i = this.e;
        zh2 zh2Var = this.l;
        xx2 xx2Var = this.q;
        Context context = this.c;
        if (z) {
            Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
            intent.setAction("ACTION_SCHEDULE_WORK");
            tu.d(intent, rx2Var);
            xx2Var.execute(new a52(i, intent, zh2Var));
        }
        if (this.s) {
            Intent intent2 = new Intent(context, (Class<?>) SystemAlarmService.class);
            intent2.setAction("ACTION_CONSTRAINTS_CHANGED");
            xx2Var.execute(new a52(i, intent2, zh2Var));
        }
    }
}
