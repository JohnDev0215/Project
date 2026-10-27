package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import p.e90;
import p.ty;
import p.u52;
import p.xx;

/* JADX INFO: loaded from: classes.dex */
public abstract class f implements e90 {
    public int a;
    public ty b;
    public u52 c;
    public ConstraintWidget$DimensionBehaviour d;
    public final b e = new b(this);
    public int f = 0;
    public boolean g = false;
    public final a h = new a(this);
    public final a i = new a(this);
    public WidgetRun$RunType j = WidgetRun$RunType.c;

    public f(ty tyVar) {
        this.b = tyVar;
    }

    public static void b(a aVar, a aVar2, int i) {
        aVar.l.add(aVar2);
        aVar.f = i;
        aVar2.k.add(aVar);
    }

    public static a h(xx xxVar) {
        xx xxVar2 = xxVar.f;
        if (xxVar2 == null) {
            return null;
        }
        int iOrdinal = xxVar2.e.ordinal();
        ty tyVar = xxVar2.d;
        if (iOrdinal == 1) {
            return tyVar.d.h;
        }
        if (iOrdinal == 2) {
            return tyVar.e.h;
        }
        if (iOrdinal == 3) {
            return tyVar.d.i;
        }
        if (iOrdinal == 4) {
            return tyVar.e.i;
        }
        if (iOrdinal != 5) {
            return null;
        }
        return tyVar.e.k;
    }

    public static a i(xx xxVar, int i) {
        xx xxVar2 = xxVar.f;
        if (xxVar2 == null) {
            return null;
        }
        ty tyVar = xxVar2.d;
        f fVar = i == 0 ? tyVar.d : tyVar.e;
        int iOrdinal = xxVar2.e.ordinal();
        if (iOrdinal == 1 || iOrdinal == 2) {
            return fVar.h;
        }
        if (iOrdinal == 3 || iOrdinal == 4) {
            return fVar.i;
        }
        return null;
    }

    public final void c(a aVar, a aVar2, int i, b bVar) {
        aVar.l.add(aVar2);
        aVar.l.add(this.e);
        aVar.h = i;
        aVar.i = bVar;
        aVar2.k.add(aVar);
        bVar.k.add(aVar);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i, int i2) {
        int iMax;
        if (i2 == 0) {
            ty tyVar = this.b;
            int i3 = tyVar.v;
            iMax = Math.max(tyVar.u, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ty tyVar2 = this.b;
            int i4 = tyVar2.y;
            iMax = Math.max(tyVar2.x, i);
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
            return r0.g;
        }
        return 0L;
    }

    public abstract boolean k();

    /* JADX WARN: Removed duplicated region for block: B:29:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(p.xx r13, p.xx r14, int r15) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.f.l(p.xx, p.xx, int):void");
    }
}
