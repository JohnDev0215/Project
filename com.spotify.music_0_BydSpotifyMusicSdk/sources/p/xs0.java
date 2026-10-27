package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class xs0 implements y4 {
    public static final xs0 c = new xs0();
    public static final List e = v03.B("library");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ls0 ls0Var = null;
        while (u71Var.W(e) == 0) {
            ls0Var = (ls0) c5.a(new wo1(ws0.c, false)).a(u71Var, cVar);
        }
        return new ms0(ls0Var);
    }
}
