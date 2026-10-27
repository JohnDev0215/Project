package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class to0 implements y4 {
    public static final to0 c = new to0();
    public static final List e = v03.B("albums");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ho0 ho0Var = null;
        while (u71Var.W(e) == 0) {
            ho0Var = (ho0) c5.a(c5.b(qo0.c, true)).a(u71Var, cVar);
        }
        return new ko0(ho0Var);
    }
}
