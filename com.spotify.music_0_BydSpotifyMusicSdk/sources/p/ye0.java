package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ye0 implements y4 {
    public static final ye0 c = new ye0();
    public static final List e = v03.B("data");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ie0 ie0Var = null;
        while (u71Var.W(e) == 0) {
            se0 se0Var = se0.c;
            w1 w1Var = c5.a;
            ie0Var = (ie0) new wo1(se0Var, true).a(u71Var, cVar);
        }
        v03.h(ie0Var);
        return new ne0(ie0Var);
    }
}
