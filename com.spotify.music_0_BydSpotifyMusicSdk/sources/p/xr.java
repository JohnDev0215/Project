package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class xr implements y4 {
    public static final xr c = new xr();
    public static final List e = v03.B("playable");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        Boolean bool = null;
        while (u71Var.W(e) == 0) {
            bool = (Boolean) c5.c.a(u71Var, cVar);
        }
        v03.h(bool);
        return new mr(bool.booleanValue());
    }
}
