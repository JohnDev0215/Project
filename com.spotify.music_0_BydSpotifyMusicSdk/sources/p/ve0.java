package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ve0 implements y4 {
    public static final List c = v03.B("name");

    public static ke0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        while (u71Var.W(c) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        v03.h(str);
        return new ke0(str);
    }
}
