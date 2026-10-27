package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class kv1 implements y4 {
    public static final List c = v03.B("data");

    public static av1 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        su1 su1Var = null;
        while (u71Var.W(c) == 0) {
            cv1 cv1Var = cv1.c;
            w1 w1Var = c5.a;
            su1Var = (su1) new wo1(cv1Var, true).a(u71Var, cVar);
        }
        v03.h(su1Var);
        return new av1(su1Var);
    }
}
