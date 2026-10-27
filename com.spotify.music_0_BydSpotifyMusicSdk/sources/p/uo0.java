package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class uo0 implements y4 {
    public static final uo0 c = new uo0();
    public static final List e = v03.B("library");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ko0 ko0Var = null;
        while (u71Var.W(e) == 0) {
            ko0Var = (ko0) c5.a(new wo1(to0.c, false)).a(u71Var, cVar);
        }
        return new lo0(ko0Var);
    }
}
