package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class y92 implements y4 {
    public static final y92 c = new y92();
    public static final List e = v03.B("item");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        v82 v82Var = null;
        while (u71Var.W(e) == 0) {
            z92 z92Var = z92.c;
            w1 w1Var = c5.a;
            v82Var = (v82) new wo1(z92Var, true).a(u71Var, cVar);
        }
        v03.h(v82Var);
        return new u82(v82Var);
    }
}
