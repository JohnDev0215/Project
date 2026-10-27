package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class xm0 implements y4 {
    public static final xm0 c = new xm0();
    public static final List e = v03.B("albumUnion");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        pm0 pm0Var = null;
        while (u71Var.W(e) == 0) {
            wm0 wm0Var = wm0.c;
            w1 w1Var = c5.a;
            pm0Var = (pm0) new wo1(wm0Var, true).a(u71Var, cVar);
        }
        v03.h(pm0Var);
        return new qm0(pm0Var);
    }
}
