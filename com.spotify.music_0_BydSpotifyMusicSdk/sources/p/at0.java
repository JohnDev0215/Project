package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class at0 implements y4 {
    public static final List c = v03.B("podcast");

    public static ps0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        qs0 qs0Var = null;
        while (u71Var.W(c) == 0) {
            qs0Var = (qs0) c5.a(new wo1(bt0.c, true)).a(u71Var, cVar);
        }
        return new ps0(qs0Var);
    }
}
