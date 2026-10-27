package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ot0 implements y4 {
    public static final ot0 c = new ot0();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        ud udVarB;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        qv1 qv1VarB = null;
        String str = null;
        while (u71Var.W(e) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        if (str == null) {
            throw new IllegalStateException("__typename was not found".toString());
        }
        lm lmVarC = com.apollographql.apollo3.api.a.c("Audiobook");
        z4 z4Var = cVar.b;
        if (com.apollographql.apollo3.api.a.a(lmVarC, z4Var.a(), str, z4Var)) {
            u71Var.g();
            udVarB = wd.b(u71Var, cVar);
        } else {
            udVarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("Podcast"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            qv1VarB = xv1.b(u71Var, cVar);
        }
        return new lt0(str, udVarB, qv1VarB);
    }
}
