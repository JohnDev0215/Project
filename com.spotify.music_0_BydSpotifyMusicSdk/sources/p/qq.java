package p;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class qq extends androidx.constraintlayout.core.widgets.analyzer.f {
    public final ArrayList k;
    public int l;

    public qq(int i, ty tyVar) {
        ty tyVar2;
        super(tyVar);
        this.k = new ArrayList();
        this.f = i;
        ty tyVar3 = this.b;
        ty tyVarK = tyVar3.k(i);
        while (true) {
            tyVar2 = tyVar3;
            tyVar3 = tyVarK;
            if (tyVar3 == null) {
                break;
            } else {
                tyVarK = tyVar3.k(this.f);
            }
        }
        this.b = tyVar2;
        int i2 = this.f;
        Object obj = i2 == 0 ? tyVar2.d : i2 == 1 ? tyVar2.e : null;
        ArrayList<androidx.constraintlayout.core.widgets.analyzer.f> arrayList = this.k;
        arrayList.add(obj);
        ty tyVarJ = tyVar2.j(this.f);
        while (tyVarJ != null) {
            int i3 = this.f;
            arrayList.add(i3 == 0 ? tyVarJ.d : i3 == 1 ? tyVarJ.e : null);
            tyVarJ = tyVarJ.j(this.f);
        }
        for (androidx.constraintlayout.core.widgets.analyzer.f fVar : arrayList) {
            int i4 = this.f;
            if (i4 == 0) {
                fVar.b.b = this;
            } else if (i4 == 1) {
                fVar.b.c = this;
            }
        }
        if (this.f == 0 && ((uy) this.b.T).u0 && arrayList.size() > 1) {
            this.b = ((androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(arrayList.size() - 1)).b;
        }
        this.l = this.f == 0 ? this.b.i0 : this.b.j0;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0166  */
    @Override // p.e90
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(p.e90 r28) {
        /*
            Method dump skipped, instruction units count: 1007
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.qq.a(p.e90):void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void d() {
        ArrayList arrayList = this.k;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((androidx.constraintlayout.core.widgets.analyzer.f) it.next()).d();
        }
        int size = arrayList.size();
        if (size < 1) {
            return;
        }
        ty tyVar = ((androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(0)).b;
        ty tyVar2 = ((androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(size - 1)).b;
        int i = this.f;
        androidx.constraintlayout.core.widgets.analyzer.a aVar = this.i;
        androidx.constraintlayout.core.widgets.analyzer.a aVar2 = this.h;
        if (i == 0) {
            xx xxVar = tyVar.H;
            xx xxVar2 = tyVar2.J;
            androidx.constraintlayout.core.widgets.analyzer.a aVarI = androidx.constraintlayout.core.widgets.analyzer.f.i(xxVar, 0);
            int iD = xxVar.d();
            ty tyVarM = m();
            if (tyVarM != null) {
                iD = tyVarM.H.d();
            }
            if (aVarI != null) {
                androidx.constraintlayout.core.widgets.analyzer.f.b(aVar2, aVarI, iD);
            }
            androidx.constraintlayout.core.widgets.analyzer.a aVarI2 = androidx.constraintlayout.core.widgets.analyzer.f.i(xxVar2, 0);
            int iD2 = xxVar2.d();
            ty tyVarN = n();
            if (tyVarN != null) {
                iD2 = tyVarN.J.d();
            }
            if (aVarI2 != null) {
                androidx.constraintlayout.core.widgets.analyzer.f.b(aVar, aVarI2, -iD2);
            }
        } else {
            xx xxVar3 = tyVar.I;
            xx xxVar4 = tyVar2.K;
            androidx.constraintlayout.core.widgets.analyzer.a aVarI3 = androidx.constraintlayout.core.widgets.analyzer.f.i(xxVar3, 1);
            int iD3 = xxVar3.d();
            ty tyVarM2 = m();
            if (tyVarM2 != null) {
                iD3 = tyVarM2.I.d();
            }
            if (aVarI3 != null) {
                androidx.constraintlayout.core.widgets.analyzer.f.b(aVar2, aVarI3, iD3);
            }
            androidx.constraintlayout.core.widgets.analyzer.a aVarI4 = androidx.constraintlayout.core.widgets.analyzer.f.i(xxVar4, 1);
            int iD4 = xxVar4.d();
            ty tyVarN2 = n();
            if (tyVarN2 != null) {
                iD4 = tyVarN2.K.d();
            }
            if (aVarI4 != null) {
                androidx.constraintlayout.core.widgets.analyzer.f.b(aVar, aVarI4, -iD4);
            }
        }
        aVar2.a = this;
        aVar.a = this;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void e() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return;
            }
            ((androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(i)).e();
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void f() {
        this.c = null;
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            ((androidx.constraintlayout.core.widgets.analyzer.f) it.next()).f();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final long j() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            androidx.constraintlayout.core.widgets.analyzer.f fVar = (androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(i);
            j = ((long) fVar.i.f) + fVar.j() + j + ((long) fVar.h.f);
        }
        return j;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final boolean k() {
        ArrayList arrayList = this.k;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (!((androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(i)).k()) {
                return false;
            }
        }
        return true;
    }

    public final ty m() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return null;
            }
            ty tyVar = ((androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(i)).b;
            if (tyVar.g0 != 8) {
                return tyVar;
            }
            i++;
        }
    }

    public final ty n() {
        ArrayList arrayList = this.k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ty tyVar = ((androidx.constraintlayout.core.widgets.analyzer.f) arrayList.get(size)).b;
            if (tyVar.g0 != 8) {
                return tyVar;
            }
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f == 0 ? "horizontal : " : "vertical : ");
        for (androidx.constraintlayout.core.widgets.analyzer.f fVar : this.k) {
            sb.append("<");
            sb.append(fVar);
            sb.append("> ");
        }
        return sb.toString();
    }
}
