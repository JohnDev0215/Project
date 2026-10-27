package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class xv1 implements y4 {
    public static final List c = v03.B("episodesV2");

    public static qv1 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        mv1 mv1Var = null;
        while (u71Var.W(c) == 0) {
            mv1Var = (mv1) c5.a(new wo1(tv1.c, true)).a(u71Var, cVar);
        }
        return new qv1(mv1Var);
    }
}
