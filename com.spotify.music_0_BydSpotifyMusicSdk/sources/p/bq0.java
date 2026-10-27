package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bq0 implements y4 {
    public static final bq0 c = new bq0();
    public static final List e = v03.B("audiobooks");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        pp0 pp0Var = null;
        while (u71Var.W(e) == 0) {
            pp0Var = (pp0) c5.a(c5.b(yp0.c, true)).a(u71Var, cVar);
        }
        return new sp0(pp0Var);
    }
}
