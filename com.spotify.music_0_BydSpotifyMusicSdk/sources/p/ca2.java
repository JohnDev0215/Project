package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ca2 implements y4 {
    public static final List c = v03.B("__typename");

    public static y82 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        while (u71Var.W(c) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        u71Var.g();
        z5 z5VarB = a6.b(u71Var, cVar);
        v03.h(str);
        return new y82(str, z5VarB);
    }
}
