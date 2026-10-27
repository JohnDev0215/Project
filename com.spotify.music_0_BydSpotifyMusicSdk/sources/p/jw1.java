package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class jw1 implements y4 {
    public static final List c = v03.B("data");

    public static gw1 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ew1 ew1Var = null;
        while (u71Var.W(c) == 0) {
            hw1 hw1Var = hw1.c;
            w1 w1Var = c5.a;
            ew1Var = (ew1) new wo1(hw1Var, true).a(u71Var, cVar);
        }
        v03.h(ew1Var);
        return new gw1(ew1Var);
    }
}
