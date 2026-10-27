package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class xb implements y4 {
    public static final List c = v03.C("uri", "profile", "visuals");

    public static wb b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        ub ubVar = null;
        vb vbVar = null;
        while (true) {
            int iW = u71Var.W(c);
            if (iW == 0) {
                str = (String) c5.a.a(u71Var, cVar);
            } else if (iW == 1) {
                zb zbVar = zb.c;
                w1 w1Var = c5.a;
                ubVar = (ub) new wo1(zbVar, false).a(u71Var, cVar);
            } else {
                if (iW != 2) {
                    v03.h(str);
                    v03.h(ubVar);
                    v03.h(vbVar);
                    return new wb(str, ubVar, vbVar);
                }
                ac acVar = ac.c;
                w1 w1Var2 = c5.a;
                vbVar = (vb) new wo1(acVar, false).a(u71Var, cVar);
            }
        }
    }
}
