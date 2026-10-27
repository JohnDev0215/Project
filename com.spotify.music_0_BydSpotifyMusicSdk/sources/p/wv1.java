package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class wv1 implements y4 {
    public static final List c = v03.B("entity");

    public static pv1 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        lv1 lv1Var = null;
        while (u71Var.W(c) == 0) {
            sv1 sv1Var = sv1.c;
            w1 w1Var = c5.a;
            lv1Var = (lv1) new wo1(sv1Var, true).a(u71Var, cVar);
        }
        v03.h(lv1Var);
        return new pv1(lv1Var);
    }
}
