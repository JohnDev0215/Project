package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class as0 implements y4 {
    public static final as0 c = new as0();
    public static final List e = v03.B("item");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        or0 or0Var = null;
        while (u71Var.W(e) == 0) {
            zr0 zr0Var = zr0.c;
            w1 w1Var = c5.a;
            or0Var = (or0) new wo1(zr0Var, true).a(u71Var, cVar);
        }
        v03.h(or0Var);
        return new pr0(or0Var);
    }
}
