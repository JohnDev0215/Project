package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ba2 implements y4 {
    public static final ba2 c = new ba2();
    public static final List e = v03.B("item");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        w82 w82Var = null;
        while (u71Var.W(e) == 0) {
            aa2 aa2Var = aa2.c;
            w1 w1Var = c5.a;
            w82Var = (w82) new wo1(aa2Var, true).a(u71Var, cVar);
        }
        v03.h(w82Var);
        return new x82(w82Var);
    }
}
