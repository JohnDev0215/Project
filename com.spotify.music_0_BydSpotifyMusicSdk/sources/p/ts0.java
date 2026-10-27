package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ts0 implements y4 {
    public static final ts0 c = new ts0();
    public static final List e = v03.B("me");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ms0 ms0Var = null;
        while (u71Var.W(e) == 0) {
            ms0Var = (ms0) c5.a(new wo1(xs0.c, false)).a(u71Var, cVar);
        }
        return new is0(ms0Var);
    }
}
