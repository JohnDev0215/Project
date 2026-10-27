package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class kp0 implements y4 {
    public static final kp0 c = new kp0();
    public static final List e = v03.B("artists");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        yo0 yo0Var = null;
        while (u71Var.W(e) == 0) {
            yo0Var = (yo0) c5.a(c5.b(hp0.c, true)).a(u71Var, cVar);
        }
        return new bp0(yo0Var);
    }
}
