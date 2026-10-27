package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class zm0 implements y4 {
    public static final List c = v03.B("tracks");

    public static sm0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        um0 um0Var = null;
        while (u71Var.W(c) == 0) {
            bn0 bn0Var = bn0.c;
            w1 w1Var = c5.a;
            um0Var = (um0) new wo1(bn0Var, false).a(u71Var, cVar);
        }
        v03.h(um0Var);
        return new sm0(um0Var);
    }
}
