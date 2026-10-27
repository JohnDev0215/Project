package p;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class iw2 {
    public static int f;
    public ArrayList a;
    public int b;
    public int c;
    public ArrayList d;
    public int e;

    public final void a(ArrayList arrayList) {
        int size = this.a.size();
        if (this.e != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                iw2 iw2Var = (iw2) arrayList.get(i);
                if (this.e == iw2Var.b) {
                    c(this.c, iw2Var);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int b(ra1 ra1Var, int i) {
        int iN;
        int iN2;
        ArrayList arrayList = this.a;
        if (arrayList.size() == 0) {
            return 0;
        }
        uy uyVar = (uy) ((ty) arrayList.get(0)).T;
        ra1Var.t();
        uyVar.b(ra1Var, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ((ty) arrayList.get(i2)).b(ra1Var, false);
        }
        if (i == 0 && uyVar.y0 > 0) {
            ci1.f(uyVar, ra1Var, arrayList, 0);
        }
        if (i == 1 && uyVar.z0 > 0) {
            ci1.f(uyVar, ra1Var, arrayList, 1);
        }
        try {
            ra1Var.p();
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + Arrays.toString(e.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.d = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ty tyVar = (ty) arrayList.get(i3);
            u11 u11Var = new u11();
            new WeakReference(tyVar);
            ra1.n(tyVar.H);
            ra1.n(tyVar.I);
            ra1.n(tyVar.J);
            ra1.n(tyVar.K);
            ra1.n(tyVar.L);
            this.d.add(u11Var);
        }
        if (i == 0) {
            iN = ra1.n(uyVar.H);
            iN2 = ra1.n(uyVar.J);
            ra1Var.t();
        } else {
            iN = ra1.n(uyVar.I);
            iN2 = ra1.n(uyVar.K);
            ra1Var.t();
        }
        return iN2 - iN;
    }

    public final void c(int i, iw2 iw2Var) {
        for (ty tyVar : this.a) {
            ArrayList arrayList = iw2Var.a;
            if (!arrayList.contains(tyVar)) {
                arrayList.add(tyVar);
            }
            int i2 = iw2Var.b;
            if (i == 0) {
                tyVar.n0 = i2;
            } else {
                tyVar.o0 = i2;
            }
        }
        this.e = iw2Var.b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.c;
        sb.append(i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown");
        sb.append(" [");
        String strL = u4.l(sb, this.b, "] <");
        for (ty tyVar : this.a) {
            StringBuilder sbF = hf2.f(strL, " ");
            sbF.append(tyVar.h0);
            strL = sbF.toString();
        }
        return u4.h(strL, " >");
    }
}
