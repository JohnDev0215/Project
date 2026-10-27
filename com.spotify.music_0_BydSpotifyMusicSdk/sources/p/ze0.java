package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ze0 implements y4 {
    public static final ze0 c = new ze0();
    public static final List e = v03.B("isoString");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        while (u71Var.W(e) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        v03.h(str);
        return new oe0(str);
    }
}
