package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ie implements y4 {
    public static final ie c = new ie();
    public static final List e = v03.B("name");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        while (u71Var.W(e) == 0) {
            str = (String) c5.e.a(u71Var, cVar);
        }
        return new de(str);
    }
}
