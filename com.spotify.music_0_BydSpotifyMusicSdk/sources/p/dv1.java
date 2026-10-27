package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class dv1 implements y4 {
    public static final dv1 c = new dv1();
    public static final List e = v03.B("items");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ArrayList arrayList = null;
        while (u71Var.W(e) == 0) {
            ev1 ev1Var = ev1.c;
            w1 w1Var = c5.a;
            wo1 wo1Var = new wo1(ev1Var, true);
            u71Var.j();
            arrayList = new ArrayList();
            while (u71Var.hasNext()) {
                arrayList.add(wo1Var.a(u71Var, cVar));
            }
            u71Var.h();
        }
        v03.h(arrayList);
        return new tu1(arrayList);
    }
}
