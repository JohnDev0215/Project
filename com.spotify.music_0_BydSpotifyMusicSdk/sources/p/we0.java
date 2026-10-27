package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class we0 implements y4 {
    public static final we0 c = new we0();
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
        return new le0(bool.booleanValue());
    }
}
