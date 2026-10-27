package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class wn0 implements y4 {
    public static final wn0 c = new wn0();
    public static final List e = v03.B("artistUnion");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        jn0 jn0Var = null;
        while (u71Var.W(e) == 0) {
            vn0 vn0Var = vn0.c;
            w1 w1Var = c5.a;
            jn0Var = (jn0) new wo1(vn0Var, true).a(u71Var, cVar);
        }
        v03.h(jn0Var);
        return new kn0(jn0Var);
    }
}
