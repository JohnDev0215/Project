package defpackage;

import defpackage.qx;

/* JADX INFO: compiled from: WidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class gt3 implements m80 {
    public int a;
    public qx b;
    public yc2 c;
    public qx.a d;
    public final ca0 e = new ca0(this);
    public int f = 0;
    public boolean g = false;
    public final o80 h = new o80(this);
    public final o80 i = new o80(this);
    public int j = 1;

    public gt3(qx qxVar) {
        this.b = qxVar;
    }

    public static void b(o80 o80Var, o80 o80Var2, int i) {
        o80Var.l.add(o80Var2);
        o80Var.f = i;
        o80Var2.k.add(o80Var);
    }

    public static o80 h(nx nxVar) {
        nx nxVar2 = nxVar.f;
        if (nxVar2 == null) {
            return null;
        }
        int iOrdinal = nxVar2.e.ordinal();
        qx qxVar = nxVar2.d;
        if (iOrdinal == 1) {
            return qxVar.d.h;
        }
        if (iOrdinal == 2) {
            return qxVar.e.h;
        }
        if (iOrdinal == 3) {
            return qxVar.d.i;
        }
        if (iOrdinal == 4) {
            return qxVar.e.i;
        }
        if (iOrdinal != 5) {
            return null;
        }
        return qxVar.e.k;
    }

    public static o80 i(nx nxVar, int i) {
        nx nxVar2 = nxVar.f;
        if (nxVar2 == null) {
            return null;
        }
        qx qxVar = nxVar2.d;
        gt3 gt3Var = i == 0 ? qxVar.d : qxVar.e;
        int iOrdinal = nxVar2.e.ordinal();
        if (iOrdinal == 1 || iOrdinal == 2) {
            return gt3Var.h;
        }
        if (iOrdinal == 3 || iOrdinal == 4) {
            return gt3Var.i;
        }
        return null;
    }

    public final void c(o80 o80Var, o80 o80Var2, int i, ca0 ca0Var) {
        o80Var.l.add(o80Var2);
        o80Var.l.add(this.e);
        o80Var.h = i;
        o80Var.i = ca0Var;
        o80Var2.k.add(o80Var);
        ca0Var.k.add(o80Var);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i, int i2) {
        int iMax;
        if (i2 == 0) {
            qx qxVar = this.b;
            int i3 = qxVar.w;
            iMax = Math.max(qxVar.v, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            qx qxVar2 = this.b;
            int i4 = qxVar2.z;
            iMax = Math.max(qxVar2.y, i);
            if (i4 > 0) {
                iMax = Math.min(i4, i);
            }
            if (iMax == i) {
                return i;
            }
        }
        return iMax;
    }

    public long j() {
        if (this.e.j) {
            return r2.g;
        }
        return 0L;
    }

    public abstract boolean k();

    /* JADX WARN: Removed duplicated region for block: B:29:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(defpackage.nx r13, defpackage.nx r14, int r15) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gt3.l(nx, nx, int):void");
    }

    @Override // defpackage.m80
    public void a(m80 m80Var) {
    }
}
