package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class lr0 implements y4 {
    public static final lr0 c = new lr0();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        while (u71Var.W(e) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        u71Var.g();
        hn2 hn2VarB = ln2.b(u71Var, cVar);
        v03.h(str);
        return new cr0(str, hn2VarB);
    }
}
