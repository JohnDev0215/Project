package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hr0 implements y4 {
    public static final hr0 c = new hr0();
    public static final List e = v03.B("tracks");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        dr0 dr0Var = null;
        while (u71Var.W(e) == 0) {
            dr0Var = (dr0) c5.a(c5.b(mr0.c, true)).a(u71Var, cVar);
        }
        return new yq0(dr0Var);
    }
}
