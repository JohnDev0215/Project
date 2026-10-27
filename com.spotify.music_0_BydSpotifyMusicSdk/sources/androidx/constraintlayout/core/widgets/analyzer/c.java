package androidx.constraintlayout.core.widgets.analyzer;

import java.util.Iterator;
import p.al;
import p.e90;
import p.ty;

/* JADX INFO: loaded from: classes.dex */
public final class c extends f {
    @Override // p.e90
    public final void a(e90 e90Var) {
        al alVar = (al) this.b;
        int i = alVar.r0;
        a aVar = this.h;
        Iterator it = aVar.l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = ((a) it.next()).g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (i == 0 || i == 2) {
            aVar.d(i3 + alVar.t0);
        } else {
            aVar.d(i2 + alVar.t0);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void d() {
        ty tyVar = this.b;
        if (tyVar instanceof al) {
            a aVar = this.h;
            aVar.b = true;
            al alVar = (al) tyVar;
            int i = alVar.r0;
            boolean z = alVar.s0;
            int i2 = 0;
            if (i == 0) {
                aVar.e = DependencyNode$Type.l;
                while (i2 < alVar.q0) {
                    ty tyVar2 = alVar.p0[i2];
                    if (z || tyVar2.g0 != 8) {
                        a aVar2 = tyVar2.d.h;
                        aVar2.k.add(aVar);
                        aVar.l.add(aVar2);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 1) {
                aVar.e = DependencyNode$Type.m;
                while (i2 < alVar.q0) {
                    ty tyVar3 = alVar.p0[i2];
                    if (z || tyVar3.g0 != 8) {
                        a aVar3 = tyVar3.d.i;
                        aVar3.k.add(aVar);
                        aVar.l.add(aVar3);
                    }
                    i2++;
                }
                m(this.b.d.h);
                m(this.b.d.i);
                return;
            }
            if (i == 2) {
                aVar.e = DependencyNode$Type.n;
                while (i2 < alVar.q0) {
                    ty tyVar4 = alVar.p0[i2];
                    if (z || tyVar4.g0 != 8) {
                        a aVar4 = tyVar4.e.h;
                        aVar4.k.add(aVar);
                        aVar.l.add(aVar4);
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
            aVar.e = DependencyNode$Type.o;
            while (i2 < alVar.q0) {
                ty tyVar5 = alVar.p0[i2];
                if (z || tyVar5.g0 != 8) {
                    a aVar5 = tyVar5.e.i;
                    aVar5.k.add(aVar);
                    aVar.l.add(aVar5);
                }
                i2++;
            }
            m(this.b.e.h);
            m(this.b.e.i);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void e() {
        ty tyVar = this.b;
        if (tyVar instanceof al) {
            int i = ((al) tyVar).r0;
            a aVar = this.h;
            if (i == 0 || i == 1) {
                tyVar.Y = aVar.g;
            } else {
                tyVar.Z = aVar.g;
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void f() {
        this.c = null;
        this.h.c();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final boolean k() {
        return false;
    }

    public final void m(a aVar) {
        a aVar2 = this.h;
        aVar2.k.add(aVar);
        aVar.l.add(aVar2);
    }
}
