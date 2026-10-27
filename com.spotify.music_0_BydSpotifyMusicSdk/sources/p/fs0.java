package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class fs0 implements y4 {
    public static final List c = v03.B("totalCount");

    public static ur0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        Integer num = null;
        while (u71Var.W(c) == 0) {
            num = (Integer) c5.b.a(u71Var, cVar);
        }
        v03.h(num);
        return new ur0(num.intValue());
    }
}
