package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class tr implements y4 {
    public static final tr c = new tr();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        lr lrVarB;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        kr krVarB = null;
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
            lrVarB = wr.b(u71Var, cVar);
        } else {
            lrVarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("Chapter"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            krVarB = vr.b(u71Var, cVar);
        }
        return new ir(str, lrVarB, krVarB);
    }
}
