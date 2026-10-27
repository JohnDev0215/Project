package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: compiled from: WidgetGroup.java */
/* JADX INFO: loaded from: classes.dex */
public final class ft3 {
    public static int f;
    public final int b;
    public int c;
    public final ArrayList<qx> a = new ArrayList<>();
    public ArrayList<a> d = null;
    public int e = -1;

    /* JADX INFO: compiled from: WidgetGroup.java */
    public static class a {
        public a(qx qxVar, q71 q71Var) {
            new WeakReference(qxVar);
            nx nxVar = qxVar.K;
            q71Var.getClass();
            q71.n(nxVar);
            q71.n(qxVar.L);
            q71.n(qxVar.M);
            q71.n(qxVar.N);
            q71.n(qxVar.O);
        }
    }

    public ft3(int i) {
        this.b = -1;
        int i2 = f;
        f = i2 + 1;
        this.b = i2;
        this.c = i;
    }

    public final void a(ArrayList<ft3> arrayList) {
        int size = this.a.size();
        if (this.e != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                ft3 ft3Var = arrayList.get(i);
                if (this.e == ft3Var.b) {
                    c(this.c, ft3Var);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int b(q71 q71Var, int i) {
        int iN;
        int iN2;
        ArrayList<qx> arrayList = this.a;
        if (arrayList.size() == 0) {
            return 0;
        }
        rx rxVar = (rx) arrayList.get(0).W;
        q71Var.t();
        rxVar.e(q71Var, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).e(q71Var, false);
        }
        if (i == 0 && rxVar.F0 > 0) {
            um.a(rxVar, q71Var, arrayList, 0);
        }
        if (i == 1 && rxVar.G0 > 0) {
            um.a(rxVar, q71Var, arrayList, 1);
        }
        try {
            q71Var.p();
        } catch (Exception e) {
            System.err.println(e.toString() + "\n" + Arrays.toString(e.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.d = new ArrayList<>();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.d.add(new a(arrayList.get(i3), q71Var));
        }
        if (i == 0) {
            iN = q71.n(rxVar.K);
            iN2 = q71.n(rxVar.M);
            q71Var.t();
        } else {
            iN = q71.n(rxVar.L);
            iN2 = q71.n(rxVar.N);
            q71Var.t();
        }
        return iN2 - iN;
    }

    public final void c(int i, ft3 ft3Var) {
        Iterator<qx> it = this.a.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            int i2 = ft3Var.b;
            if (!zHasNext) {
                this.e = i2;
                return;
            }
            qx next = it.next();
            ArrayList<qx> arrayList = ft3Var.a;
            if (!arrayList.contains(next)) {
                arrayList.add(next);
            }
            if (i == 0) {
                next.u0 = i2;
            } else {
                next.v0 = i2;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.c;
        sb.append(i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown");
        sb.append(" [");
        sb.append(this.b);
        sb.append("] <");
        String string = sb.toString();
        for (qx qxVar : this.a) {
            StringBuilder sbK = C0173a.k(string, " ");
            sbK.append(qxVar.l0);
            string = sbK.toString();
        }
        return gq2.f(string, " >");
    }
}
