package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class cs0 implements y4 {
    public static final cs0 c = new cs0();
    public static final List e = v03.B("page");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        vr0 vr0Var = null;
        while (u71Var.W(e) == 0) {
            vr0Var = (vr0) c5.a(c5.b(gs0.c, true)).a(u71Var, cVar);
        }
        return new rr0(vr0Var);
    }
}
