package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class ap2 {
    public ip2 a;
    public cp2 b;
    public final ArrayList c = new ArrayList();

    public final bp2 a() {
        cp2 cp2Var = this.b;
        if (cp2Var == null) {
            cp2Var = cp2.b;
        }
        ip2 ip2Var = this.a;
        ArrayList arrayList = this.c;
        if (ip2Var != null) {
            arrayList.addAll(ip2Var.a());
            ArrayList arrayListH = ci1.H(cp2Var.a);
            if (!arrayListH.isEmpty()) {
                arrayList.addAll(arrayListH);
            }
            bp2 bp2VarB = b(ip2Var, cp2Var, arrayList);
            if (bp2VarB != null) {
                return bp2VarB;
            }
        }
        ip2 ip2Var2 = ip2.i;
        arrayList.add("No location!");
        return b(ip2Var2, cp2Var, arrayList);
    }

    public abstract bp2 b(ip2 ip2Var, cp2 cp2Var, ArrayList arrayList);

    public final void c(ip2 ip2Var) {
        v03.j(ip2Var, "location");
        this.a = ip2Var;
    }
}
