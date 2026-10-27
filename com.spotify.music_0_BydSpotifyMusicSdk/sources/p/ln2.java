package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ln2 implements y4 {
    public static final List c = v03.B("data");

    public static hn2 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        en2 en2Var = null;
        while (u71Var.W(c) == 0) {
            in2 in2Var = in2.c;
            w1 w1Var = c5.a;
            en2Var = (en2) new wo1(in2Var, true).a(u71Var, cVar);
        }
        v03.h(en2Var);
        return new hn2(en2Var);
    }
}
