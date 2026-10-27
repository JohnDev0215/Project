package p;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.WorkInfo$State;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class z01 implements b72, rp1, uk0 {
    public final Context c;
    public final r80 k;
    public boolean l;
    public final qx1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final tx2 f211p;
    public final sw q;
    public Boolean s;
    public final androidx.work.impl.constraints.a t;
    public final yx2 u;
    public final uk2 v;
    public final HashMap e = new HashMap();
    public final Object m = new Object();
    public final g90 n = new g90(3);
    public final HashMap r = new HashMap();

    static {
        vc1.d("GreedyScheduler");
    }

    public z01(Context context, sw swVar, nn2 nn2Var, qx1 qx1Var, tx2 tx2Var, yx2 yx2Var) {
        this.c = context;
        zb0 zb0Var = swVar.c;
        q70 q70Var = swVar.f;
        this.k = new r80(this, q70Var, zb0Var);
        this.v = new uk2(q70Var, tx2Var);
        this.u = yx2Var;
        this.t = new androidx.work.impl.constraints.a(nn2Var);
        this.q = swVar;
        this.o = qx1Var;
        this.f211p = tx2Var;
    }

    @Override // p.b72
    public final void a(String str) {
        Runnable runnable;
        if (this.s == null) {
            this.s = Boolean.valueOf(nx1.a(this.c, this.q));
        }
        if (!this.s.booleanValue()) {
            vc1.c().getClass();
            return;
        }
        if (!this.l) {
            this.o.a(this);
            this.l = true;
        }
        vc1.c().getClass();
        r80 r80Var = this.k;
        if (r80Var != null && (runnable = (Runnable) r80Var.d.remove(str)) != null) {
            r80Var.b.a.removeCallbacks(runnable);
        }
        for (xf2 xf2Var : this.n.k(str)) {
            this.v.a(xf2Var);
            tx2 tx2Var = this.f211p;
            tx2Var.getClass();
            tx2Var.a(xf2Var, -512);
        }
    }

    @Override // p.uk0
    public final void b(rx2 rx2Var, boolean z) {
        p61 p61Var;
        xf2 xf2VarL = this.n.l(rx2Var);
        if (xf2VarL != null) {
            this.v.a(xf2VarL);
        }
        synchronized (this.m) {
            p61Var = (p61) this.e.remove(rx2Var);
        }
        if (p61Var != null) {
            vc1 vc1VarC = vc1.c();
            Objects.toString(rx2Var);
            vc1VarC.getClass();
            p61Var.i(null);
        }
        if (z) {
            return;
        }
        synchronized (this.m) {
            this.r.remove(rx2Var);
        }
    }

    @Override // p.rp1
    public final void c(ky2 ky2Var, bz bzVar) {
        rx2 rx2VarU = ci1.u(ky2Var);
        boolean z = bzVar instanceof zy;
        tx2 tx2Var = this.f211p;
        uk2 uk2Var = this.v;
        g90 g90Var = this.n;
        if (z) {
            if (g90Var.c(rx2VarU)) {
                return;
            }
            vc1 vc1VarC = vc1.c();
            rx2VarU.toString();
            vc1VarC.getClass();
            xf2 xf2VarM = g90Var.m(rx2VarU);
            uk2Var.b(xf2VarM);
            tx2Var.b.a(new xf1(tx2Var.a, xf2VarM, (kk0) null));
            return;
        }
        vc1 vc1VarC2 = vc1.c();
        rx2VarU.toString();
        vc1VarC2.getClass();
        xf2 xf2VarL = g90Var.l(rx2VarU);
        if (xf2VarL != null) {
            uk2Var.a(xf2VarL);
            int i = ((az) bzVar).a;
            tx2Var.getClass();
            tx2Var.a(xf2VarL, i);
        }
    }

    @Override // p.b72
    public final boolean d() {
        return false;
    }

    @Override // p.b72
    public final void e(ky2... ky2VarArr) {
        long jMax;
        if (this.s == null) {
            this.s = Boolean.valueOf(nx1.a(this.c, this.q));
        }
        if (!this.s.booleanValue()) {
            vc1.c().getClass();
            return;
        }
        if (!this.l) {
            this.o.a(this);
            this.l = true;
        }
        HashSet<ky2> hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (ky2 ky2Var : ky2VarArr) {
            if (!this.n.c(ci1.u(ky2Var))) {
                synchronized (this.m) {
                    try {
                        rx2 rx2VarU = ci1.u(ky2Var);
                        y01 y01Var = (y01) this.r.get(rx2VarU);
                        if (y01Var == null) {
                            int i = ky2Var.k;
                            this.q.c.getClass();
                            y01Var = new y01(i, System.currentTimeMillis());
                            this.r.put(rx2VarU, y01Var);
                        }
                        jMax = (((long) Math.max((ky2Var.k - y01Var.a) - 5, 0)) * 30000) + y01Var.b;
                    } finally {
                    }
                }
                long jMax2 = Math.max(ky2Var.a(), jMax);
                this.q.c.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (ky2Var.b == WorkInfo$State.c) {
                    if (jCurrentTimeMillis < jMax2) {
                        r80 r80Var = this.k;
                        if (r80Var != null) {
                            HashMap map = r80Var.d;
                            Runnable runnable = (Runnable) map.remove(ky2Var.a);
                            q70 q70Var = r80Var.b;
                            if (runnable != null) {
                                q70Var.a.removeCallbacks(runnable);
                            }
                            yf1 yf1Var = new yf1(r80Var, 9, ky2Var);
                            map.put(ky2Var.a, yf1Var);
                            r80Var.c.getClass();
                            q70Var.a.postDelayed(yf1Var, jMax2 - System.currentTimeMillis());
                        }
                    } else if (ky2Var.b()) {
                        if (ky2Var.j.c) {
                            vc1 vc1VarC = vc1.c();
                            ky2Var.toString();
                            vc1VarC.getClass();
                        } else if (!r7.h.isEmpty()) {
                            vc1 vc1VarC2 = vc1.c();
                            ky2Var.toString();
                            vc1VarC2.getClass();
                        } else {
                            hashSet.add(ky2Var);
                            hashSet2.add(ky2Var.a);
                        }
                    } else if (!this.n.c(ci1.u(ky2Var))) {
                        vc1.c().getClass();
                        g90 g90Var = this.n;
                        g90Var.getClass();
                        xf2 xf2VarM = g90Var.m(ci1.u(ky2Var));
                        this.v.b(xf2VarM);
                        tx2 tx2Var = this.f211p;
                        tx2Var.b.a(new xf1(tx2Var.a, xf2VarM, (kk0) null));
                    }
                }
            }
        }
        synchronized (this.m) {
            try {
                if (!hashSet.isEmpty()) {
                    TextUtils.join(",", hashSet2);
                    vc1.c().getClass();
                    for (ky2 ky2Var2 : hashSet) {
                        rx2 rx2VarU2 = ci1.u(ky2Var2);
                        if (!this.e.containsKey(rx2VarU2)) {
                            this.e.put(rx2VarU2, androidx.work.impl.constraints.b.a(this.t, ky2Var2, this.u.b, this));
                        }
                    }
                }
            } finally {
            }
        }
    }
}
