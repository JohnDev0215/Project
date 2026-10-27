package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ef0 implements y4 {
    public static final ef0 c = new ef0();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        cf0 cf0VarB;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        bf0 bf0VarB = null;
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
            cf0VarB = hf0.b(u71Var, cVar);
        } else {
            cf0VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("Episode"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            bf0VarB = gf0.b(u71Var, cVar);
        }
        return new af0(str, cf0VarB, bf0VarB);
    }
}
