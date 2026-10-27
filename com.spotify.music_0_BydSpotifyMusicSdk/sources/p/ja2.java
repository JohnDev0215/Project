package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ja2 implements y4 {
    public static final List c = v03.B("__typename");

    public static f92 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        hn2 hn2VarB = null;
        String str = null;
        while (u71Var.W(c) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        lm lmVar = new lm(new rk());
        z4 z4Var = cVar.b;
        if (com.apollographql.apollo3.api.a.a(lmVar, z4Var.a(), null, z4Var)) {
            u71Var.g();
            hn2VarB = ln2.b(u71Var, cVar);
        }
        v03.h(str);
        return new f92(str, hn2VarB);
    }
}
