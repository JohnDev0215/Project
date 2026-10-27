package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zd implements y4 {
    public static final zd c = new zd();
    public static final List e = v03.B("entity");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        qd qdVar = null;
        while (u71Var.W(e) == 0) {
            yd ydVar = yd.c;
            w1 w1Var = c5.a;
            qdVar = (qd) new wo1(ydVar, true).a(u71Var, cVar);
        }
        v03.h(qdVar);
        return new rd(qdVar);
    }
}
