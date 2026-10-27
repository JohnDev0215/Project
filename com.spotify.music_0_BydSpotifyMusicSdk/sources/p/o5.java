package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class o5 implements y4 {
    public static final o5 c = new o5();
    public static final List e = v03.B("items");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ArrayList arrayList = null;
        while (u71Var.W(e) == 0) {
            r5 r5Var = r5.c;
            w1 w1Var = c5.a;
            wo1 wo1Var = new wo1(r5Var, false);
            u71Var.j();
            arrayList = new ArrayList();
            while (u71Var.hasNext()) {
                arrayList.add(wo1Var.a(u71Var, cVar));
            }
            u71Var.h();
        }
        v03.h(arrayList);
        return new h5(arrayList);
    }
}
