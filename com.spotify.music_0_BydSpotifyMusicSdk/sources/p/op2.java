package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class op2 extends ap2 {
    public ep2 d;

    @Override // p.ap2
    public final bp2 b(ip2 ip2Var, cp2 cp2Var, ArrayList arrayList) {
        v03.j(ip2Var, "location");
        v03.j(cp2Var, "parentAbsoluteLocation");
        v03.j(arrayList, "errors");
        ArrayList arrayListO0 = ou.o0(arrayList);
        ep2 ep2Var = this.d;
        if (ep2Var != null) {
            arrayListO0.addAll(ep2Var.a());
            return new pp2(ip2Var, cp2Var, arrayListO0, ep2Var);
        }
        arrayListO0.add("No action!");
        return new pp2(ip2Var, cp2Var, arrayList, ep2.e);
    }
}
