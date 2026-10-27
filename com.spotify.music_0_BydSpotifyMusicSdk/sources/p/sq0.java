package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class sq0 implements y4 {
    public static final sq0 c = new sq0();
    public static final List e = v03.B("episodes");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        hq0 hq0Var = null;
        while (u71Var.W(e) == 0) {
            hq0Var = (hq0) c5.a(c5.b(qq0.c, true)).a(u71Var, cVar);
        }
        return new jq0(hq0Var);
    }
}
