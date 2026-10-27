package p1;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: p1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2076d extends p {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f20652k;
    public int l;

    public C2076d(o1.d dVar, int i7) {
        o1.d dVar2;
        super(dVar);
        this.f20652k = new ArrayList();
        this.f20682f = i7;
        o1.d dVar3 = this.f20680b;
        o1.d dVarN = dVar3.n(i7);
        while (true) {
            o1.d dVar4 = dVarN;
            dVar2 = dVar3;
            dVar3 = dVar4;
            if (dVar3 == null) {
                break;
            } else {
                dVarN = dVar3.n(this.f20682f);
            }
        }
        this.f20680b = dVar2;
        int i10 = this.f20682f;
        p pVar = i10 == 0 ? dVar2.d : i10 == 1 ? dVar2.f20288e : null;
        ArrayList<p> arrayList = this.f20652k;
        arrayList.add(pVar);
        o1.d dVarM = dVar2.m(this.f20682f);
        while (dVarM != null) {
            int i11 = this.f20682f;
            arrayList.add(i11 == 0 ? dVarM.d : i11 == 1 ? dVarM.f20288e : null);
            dVarM = dVarM.m(this.f20682f);
        }
        for (p pVar2 : arrayList) {
            int i12 = this.f20682f;
            if (i12 == 0) {
                pVar2.f20680b.f20284b = this;
            } else if (i12 == 1) {
                pVar2.f20680b.c = this;
            }
        }
        if (this.f20682f == 0 && this.f20680b.f20276U.f20337w0 && arrayList.size() > 1) {
            this.f20680b = ((p) AbstractC1347d.e(1, arrayList)).f20680b;
        }
        this.l = this.f20682f == 0 ? this.f20680b.f20299j0 : this.f20680b.f20301k0;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0151  */
    @Override // p1.InterfaceC2077e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(p1.InterfaceC2077e r27) {
        /*
            Method dump skipped, instruction units count: 967
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p1.C2076d.a(p1.e):void");
    }

    @Override // p1.p
    public final void d() {
        ArrayList arrayList = this.f20652k;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((p) it.next()).d();
        }
        int size = arrayList.size();
        if (size < 1) {
            return;
        }
        o1.d dVar = ((p) arrayList.get(0)).f20680b;
        o1.d dVar2 = ((p) arrayList.get(size - 1)).f20680b;
        int i7 = this.f20682f;
        C2079g c2079g = this.f20685i;
        C2079g c2079g2 = this.f20684h;
        if (i7 == 0) {
            o1.c cVar = dVar.f20265J;
            o1.c cVar2 = dVar2.f20267L;
            C2079g c2079gI = p.i(cVar, 0);
            int iE = cVar.e();
            o1.d dVarM = m();
            if (dVarM != null) {
                iE = dVarM.f20265J.e();
            }
            if (c2079gI != null) {
                p.b(c2079g2, c2079gI, iE);
            }
            C2079g c2079gI2 = p.i(cVar2, 0);
            int iE2 = cVar2.e();
            o1.d dVarN = n();
            if (dVarN != null) {
                iE2 = dVarN.f20267L.e();
            }
            if (c2079gI2 != null) {
                p.b(c2079g, c2079gI2, -iE2);
            }
        } else {
            o1.c cVar3 = dVar.f20266K;
            o1.c cVar4 = dVar2.f20268M;
            C2079g c2079gI3 = p.i(cVar3, 1);
            int iE3 = cVar3.e();
            o1.d dVarM2 = m();
            if (dVarM2 != null) {
                iE3 = dVarM2.f20266K.e();
            }
            if (c2079gI3 != null) {
                p.b(c2079g2, c2079gI3, iE3);
            }
            C2079g c2079gI4 = p.i(cVar4, 1);
            int iE4 = cVar4.e();
            o1.d dVarN2 = n();
            if (dVarN2 != null) {
                iE4 = dVarN2.f20268M.e();
            }
            if (c2079gI4 != null) {
                p.b(c2079g, c2079gI4, -iE4);
            }
        }
        c2079g2.f20660a = this;
        c2079g.f20660a = this;
    }

    @Override // p1.p
    public final void e() {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f20652k;
            if (i7 >= arrayList.size()) {
                return;
            }
            ((p) arrayList.get(i7)).e();
            i7++;
        }
    }

    @Override // p1.p
    public final void f() {
        this.c = null;
        Iterator it = this.f20652k.iterator();
        while (it.hasNext()) {
            ((p) it.next()).f();
        }
    }

    @Override // p1.p
    public final long j() {
        ArrayList arrayList = this.f20652k;
        int size = arrayList.size();
        long j3 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            p pVar = (p) arrayList.get(i7);
            j3 = ((long) pVar.f20685i.f20663f) + pVar.j() + j3 + ((long) pVar.f20684h.f20663f);
        }
        return j3;
    }

    @Override // p1.p
    public final boolean k() {
        ArrayList arrayList = this.f20652k;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (!((p) arrayList.get(i7)).k()) {
                return false;
            }
        }
        return true;
    }

    public final o1.d m() {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f20652k;
            if (i7 >= arrayList.size()) {
                return null;
            }
            o1.d dVar = ((p) arrayList.get(i7)).f20680b;
            if (dVar.f20295h0 != 8) {
                return dVar;
            }
            i7++;
        }
    }

    public final o1.d n() {
        ArrayList arrayList = this.f20652k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            o1.d dVar = ((p) arrayList.get(size)).f20680b;
            if (dVar.f20295h0 != 8) {
                return dVar;
            }
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f20682f == 0 ? "horizontal : " : "vertical : ");
        for (p pVar : this.f20652k) {
            sb.append("<");
            sb.append(pVar);
            sb.append("> ");
        }
        return sb.toString();
    }
}
