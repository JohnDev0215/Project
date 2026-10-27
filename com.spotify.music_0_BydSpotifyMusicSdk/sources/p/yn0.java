package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class yn0 implements y4 {
    public static final yn0 c = new yn0();
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
        m5 m5VarB = n5.b(u71Var, cVar);
        v03.h(str);
        return new mn0(str, m5VarB);
    }
}
