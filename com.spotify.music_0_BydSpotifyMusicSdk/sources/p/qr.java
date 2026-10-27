package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class qr implements y4 {
    public static final List c = v03.B("data");

    public static pr b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ir irVar = null;
        while (u71Var.W(c) == 0) {
            tr trVar = tr.c;
            w1 w1Var = c5.a;
            irVar = (ir) new wo1(trVar, true).a(u71Var, cVar);
        }
        v03.h(irVar);
        return new pr(irVar);
    }
}
