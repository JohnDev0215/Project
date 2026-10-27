package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class cq0 implements y4 {
    public static final cq0 c = new cq0();
    public static final List e = v03.B("library");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        sp0 sp0Var = null;
        while (u71Var.W(e) == 0) {
            sp0Var = (sp0) c5.a(new wo1(bq0.c, false)).a(u71Var, cVar);
        }
        return new tp0(sp0Var);
    }
}
