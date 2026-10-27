package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class oq0 implements y4 {
    public static final oq0 c = new oq0();
    public static final List e = v03.B("me");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        kq0 kq0Var = null;
        while (u71Var.W(e) == 0) {
            kq0Var = (kq0) c5.a(new wo1(tq0.c, false)).a(u71Var, cVar);
        }
        return new fq0(kq0Var);
    }
}
