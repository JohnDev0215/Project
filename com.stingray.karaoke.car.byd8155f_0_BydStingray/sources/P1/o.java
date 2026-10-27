package p1;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import n0.C1886o;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f20675f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList f20676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20677b;
    public int c;
    public ArrayList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f20678e;

    public final void a(ArrayList arrayList) {
        int size = this.f20676a.size();
        if (this.f20678e != -1 && size > 0) {
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                o oVar = (o) arrayList.get(i7);
                if (this.f20678e == oVar.f20677b) {
                    c(this.c, oVar);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int b(h1.c cVar, int i7) {
        int iN;
        int iN2;
        ArrayList arrayList = this.f20676a;
        if (arrayList.size() == 0) {
            return 0;
        }
        o1.e eVar = ((o1.d) arrayList.get(0)).f20276U;
        cVar.t();
        eVar.c(cVar, false);
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            ((o1.d) arrayList.get(i10)).c(cVar, false);
        }
        if (i7 == 0 && eVar.f20321A0 > 0) {
            o1.j.a(eVar, cVar, arrayList, 0);
        }
        if (i7 == 1 && eVar.B0 > 0) {
            o1.j.a(eVar, cVar, arrayList, 1);
        }
        try {
            cVar.p();
        } catch (Exception e10) {
            System.err.println(e10.toString() + "\n" + Arrays.toString(e10.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.d = new ArrayList();
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            o1.d dVar = (o1.d) arrayList.get(i11);
            C1886o c1886o = new C1886o(4);
            new WeakReference(dVar);
            h1.c.n(dVar.f20265J);
            h1.c.n(dVar.f20266K);
            h1.c.n(dVar.f20267L);
            h1.c.n(dVar.f20268M);
            h1.c.n(dVar.f20269N);
            this.d.add(c1886o);
        }
        if (i7 == 0) {
            iN = h1.c.n(eVar.f20265J);
            iN2 = h1.c.n(eVar.f20267L);
            cVar.t();
        } else {
            iN = h1.c.n(eVar.f20266K);
            iN2 = h1.c.n(eVar.f20268M);
            cVar.t();
        }
        return iN2 - iN;
    }

    public final void c(int i7, o oVar) {
        for (o1.d dVar : this.f20676a) {
            ArrayList arrayList = oVar.f20676a;
            if (!arrayList.contains(dVar)) {
                arrayList.add(dVar);
            }
            int i10 = oVar.f20677b;
            if (i7 == 0) {
                dVar.f20307o0 = i10;
            } else {
                dVar.f20309p0 = i10;
            }
        }
        this.f20678e = oVar.f20677b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i7 = this.c;
        sb.append(i7 == 0 ? "Horizontal" : i7 == 1 ? "Vertical" : i7 == 2 ? "Both" : "Unknown");
        sb.append(" [");
        String strH = M5.b.h(sb, this.f20677b, "] <");
        for (o1.d dVar : this.f20676a) {
            StringBuilder sbL = A7.k.l(strH, " ");
            sbL.append(dVar.f20297i0);
            strH = sbL.toString();
        }
        return android.hardware.bydauto.ac.tsimpl.a.h(strH, " >");
    }
}
