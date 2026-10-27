package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class r5 implements y4 {
    public static final r5 c = new r5();
    public static final List e = v03.B("profile");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        l5 l5Var = null;
        while (u71Var.W(e) == 0) {
            s5 s5Var = s5.c;
            w1 w1Var = c5.a;
            l5Var = (l5) new wo1(s5Var, false).a(u71Var, cVar);
        }
        v03.h(l5Var);
        return new k5(l5Var);
    }
}
