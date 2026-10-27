package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
public final class ju0 extends gt3 {
    public ju0(qx qxVar) {
        super(qxVar);
    }

    @Override // defpackage.gt3, defpackage.m80
    public final void a(m80 m80Var) {
        yc ycVar = (yc) this.b;
        int i = ycVar.y0;
        o80 o80Var = this.h;
        Iterator it = o80Var.l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = ((o80) it.next()).g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (i == 0 || i == 2) {
            o80Var.d(i3 + ycVar.A0);
        } else {
            o80Var.d(i2 + ycVar.A0);
        }
    }

    @Override // defpackage.gt3
    public final void d() {
        qx qxVar = this.b;
        if (qxVar instanceof yc) {
            o80 o80Var = this.h;
            o80Var.b = true;
            yc ycVar = (yc) qxVar;
            int i = ycVar.y0;
            boolean z = ycVar.z0;
            ArrayList arrayList = o80Var.l;
            int i2 = 0;
            if (i == 0) {
                o80Var.e = 4;
                while (i2 < ycVar.x0) {
                    qx qxVar2 = ycVar.w0[i2];
                    if (z || qxVar2.j0 != 8) {
                        o80 o80Var2 = qxVar2.d.h;
                        o80Var2.k.add(o80Var);
                        arrayList.add(o80Var2);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 1) {
                o80Var.e = 5;
                while (i2 < ycVar.x0) {
                    qx qxVar3 = ycVar.w0[i2];
                    if (z || qxVar3.j0 != 8) {
                        o80 o80Var3 = qxVar3.d.i;
                        o80Var3.k.add(o80Var);
                        arrayList.add(o80Var3);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 2) {
                o80Var.e = 6;
                while (i2 < ycVar.x0) {
                    qx qxVar4 = ycVar.w0[i2];
                    if (z || qxVar4.j0 != 8) {
                        o80 o80Var4 = qxVar4.e.h;
                        o80Var4.k.add(o80Var);
                        arrayList.add(o80Var4);
                    }
                    i2++;
                }
                m(this.b.e.h);
                m(this.b.e.i);
                return;
            }
            if (i != 3) {
                return;
            }
            o80Var.e = 7;
            while (i2 < ycVar.x0) {
                qx qxVar5 = ycVar.w0[i2];
                if (z || qxVar5.j0 != 8) {
                    o80 o80Var5 = qxVar5.e.i;
                    o80Var5.k.add(o80Var);
                    arrayList.add(o80Var5);
                }
                i2++;
            }
            m(this.b.e.h);
            m(this.b.e.i);
        }
    }

    @Override // defpackage.gt3
    public final void e() {
        qx qxVar = this.b;
        if (qxVar instanceof yc) {
            int i = ((yc) qxVar).y0;
            o80 o80Var = this.h;
            if (i == 0 || i == 1) {
                qxVar.b0 = o80Var.g;
            } else {
                qxVar.c0 = o80Var.g;
            }
        }
    }

    @Override // defpackage.gt3
    public final void f() {
        this.c = null;
        this.h.c();
    }

    @Override // defpackage.gt3
    public final boolean k() {
        return false;
    }

    public final void m(o80 o80Var) {
        o80 o80Var2 = this.h;
        o80Var2.k.add(o80Var);
        o80Var.l.add(o80Var2);
    }
}
