package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class p92 implements y4 {
    public static final p92 c = new p92();
    public static final List e = v03.B("searchV2");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        i92 i92Var = null;
        while (u71Var.W(e) == 0) {
            i92Var = (i92) c5.a(new wo1(ma2.c, false)).a(u71Var, cVar);
        }
        return new l82(i92Var);
    }
}
