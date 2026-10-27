package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class kn2 implements y4 {
    public static final List c = v03.B("__typename");

    public static gn2 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        while (u71Var.W(c) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        u71Var.g();
        um2 um2VarB = dn2.b(u71Var, cVar);
        v03.h(str);
        return new gn2(str, um2VarB);
    }
}
