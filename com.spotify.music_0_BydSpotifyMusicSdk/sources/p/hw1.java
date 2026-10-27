package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hw1 implements y4 {
    public static final hw1 c = new hw1();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        fw1 fw1VarB;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        aw1 aw1VarB = null;
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
            fw1VarB = iw1.b(u71Var, cVar);
        } else {
            fw1VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("Podcast"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            aw1VarB = cw1.b(u71Var, cVar);
        }
        return new ew1(str, fw1VarB, aw1VarB);
    }
}
