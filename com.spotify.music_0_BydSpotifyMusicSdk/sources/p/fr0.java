package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class fr0 implements y4 {
    public static final fr0 c = new fr0();
    public static final List e = v03.B("me");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        zq0 zq0Var = null;
        while (u71Var.W(e) == 0) {
            zq0Var = (zq0) c5.a(new wo1(ir0.c, false)).a(u71Var, cVar);
        }
        return new wq0(zq0Var);
    }
}
