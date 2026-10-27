package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class eq0 implements y4 {
    public static final List c = v03.B("audiobook");

    public static vp0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        op0 op0Var = null;
        while (u71Var.W(c) == 0) {
            op0Var = (op0) c5.a(new wo1(xp0.c, true)).a(u71Var, cVar);
        }
        return new vp0(op0Var);
    }
}
