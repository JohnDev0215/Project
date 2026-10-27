package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ip0 implements y4 {
    public static final ip0 c = new ip0();
    public static final List e = v03.B("me");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        cp0 cp0Var = null;
        while (u71Var.W(e) == 0) {
            cp0Var = (cp0) c5.a(new wo1(lp0.c, false)).a(u71Var, cVar);
        }
        return new zo0(cp0Var);
    }
}
