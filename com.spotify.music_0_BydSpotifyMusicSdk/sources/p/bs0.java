package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bs0 implements y4 {
    public static final bs0 c = new bs0();
    public static final List e = v03.B("tracks");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        wr0 wr0Var = null;
        while (u71Var.W(e) == 0) {
            wr0Var = (wr0) c5.a(c5.b(hs0.c, true)).a(u71Var, cVar);
        }
        return new qr0(wr0Var);
    }
}
