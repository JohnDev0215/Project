package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class jv1 implements y4 {
    public static final jv1 c = new jv1();
    public static final List e = v03.B("data");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ru1 ru1Var = null;
        while (u71Var.W(e) == 0) {
            bv1 bv1Var = bv1.c;
            w1 w1Var = c5.a;
            ru1Var = (ru1) new wo1(bv1Var, true).a(u71Var, cVar);
        }
        v03.h(ru1Var);
        return new zu1(ru1Var);
    }
}
