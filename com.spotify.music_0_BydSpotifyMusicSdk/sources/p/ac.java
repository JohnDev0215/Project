package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ac implements y4 {
    public static final ac c = new ac();
    public static final List e = v03.B("avatarImage");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        tb tbVar = null;
        while (u71Var.W(e) == 0) {
            tbVar = (tb) c5.a(c5.b(yb.c, true)).a(u71Var, cVar);
        }
        return new vb(tbVar);
    }
}
