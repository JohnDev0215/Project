package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class in2 implements y4 {
    public static final in2 c = new in2();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        fn2 fn2VarB;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        gn2 gn2VarB = null;
        String str = null;
        while (u71Var.W(e) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        if (str == null) {
            throw new IllegalStateException("__typename was not found".toString());
        }
        lm lmVarC = com.apollographql.apollo3.api.a.c("GenericError", "InvalidFilterIdError", "InvalidSortOrderIdError", "NotFound", "RestrictedContent");
        z4 z4Var = cVar.b;
        if (com.apollographql.apollo3.api.a.a(lmVarC, z4Var.a(), str, z4Var)) {
            u71Var.g();
            fn2VarB = jn2.b(u71Var, cVar);
        } else {
            fn2VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("Track"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            gn2VarB = kn2.b(u71Var, cVar);
        }
        return new en2(str, fn2VarB, gn2VarB);
    }
}
