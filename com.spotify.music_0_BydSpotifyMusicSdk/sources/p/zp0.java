package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zp0 implements y4 {
    public static final zp0 c = new zp0();
    public static final List e = v03.B("me");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        tp0 tp0Var = null;
        while (u71Var.W(e) == 0) {
            tp0Var = (tp0) c5.a(new wo1(cq0.c, false)).a(u71Var, cVar);
        }
        return new qp0(tp0Var);
    }
}
