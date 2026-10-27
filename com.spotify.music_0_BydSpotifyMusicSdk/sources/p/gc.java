package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class gc implements y4 {
    public static final List c = v03.B("data");

    public static fc b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        cc ccVar = null;
        while (u71Var.W(c) == 0) {
            hc hcVar = hc.c;
            w1 w1Var = c5.a;
            ccVar = (cc) new wo1(hcVar, true).a(u71Var, cVar);
        }
        v03.h(ccVar);
        return new fc(ccVar);
    }
}
