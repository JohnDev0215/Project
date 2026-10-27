package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ir0 implements y4 {
    public static final ir0 c = new ir0();
    public static final List e = v03.B("library");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        yq0 yq0Var = null;
        while (u71Var.W(e) == 0) {
            yq0Var = (yq0) c5.a(new wo1(hr0.c, false)).a(u71Var, cVar);
        }
        return new zq0(yq0Var);
    }
}
