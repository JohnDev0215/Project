package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b6 implements y4 {
    public static final b6 c = new b6();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        y5 y5VarB;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        x5 x5VarB = null;
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
            y5VarB = d6.b(u71Var, cVar);
        } else {
            y5VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("Album"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            x5VarB = c6.b(u71Var, cVar);
        }
        return new w5(str, y5VarB, x5VarB);
    }
}
