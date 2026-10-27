package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class q5 implements y4 {
    public static final q5 c = new q5();
    public static final List e = v03.B("year");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        Integer num = null;
        while (u71Var.W(e) == 0) {
            num = (Integer) c5.b.a(u71Var, cVar);
        }
        v03.h(num);
        return new j5(num.intValue());
    }
}
