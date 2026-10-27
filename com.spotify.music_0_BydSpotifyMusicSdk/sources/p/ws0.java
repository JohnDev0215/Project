package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ws0 implements y4 {
    public static final ws0 c = new ws0();
    public static final List e = v03.C("podcasts", "episodes");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        rs0 rs0Var = null;
        js0 js0Var = null;
        while (true) {
            int iW = u71Var.W(e);
            if (iW == 0) {
                rs0Var = (rs0) c5.a(c5.b(ct0.c, true)).a(u71Var, cVar);
            } else {
                if (iW != 1) {
                    return new ls0(rs0Var, js0Var);
                }
                js0Var = (js0) c5.a(c5.b(us0.c, true)).a(u71Var, cVar);
            }
        }
    }
}
