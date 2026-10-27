package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class hv1 implements y4 {
    public static final List c = v03.C("description", "images", "name", "ownerV2", "uri");

    public static xu1 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        tu1 tu1Var = null;
        String str2 = null;
        zu1 zu1Var = null;
        String str3 = null;
        while (true) {
            int iW = u71Var.W(c);
            if (iW == 0) {
                str = (String) c5.e.a(u71Var, cVar);
            } else if (iW == 1) {
                dv1 dv1Var = dv1.c;
                w1 w1Var = c5.a;
                tu1Var = (tu1) new wo1(dv1Var, false).a(u71Var, cVar);
            } else if (iW == 2) {
                str2 = (String) c5.a.a(u71Var, cVar);
            } else if (iW == 3) {
                jv1 jv1Var = jv1.c;
                w1 w1Var2 = c5.a;
                zu1Var = (zu1) new wo1(jv1Var, false).a(u71Var, cVar);
            } else {
                if (iW != 4) {
                    v03.h(tu1Var);
                    v03.h(str2);
                    v03.h(zu1Var);
                    v03.h(str3);
                    return new xu1(str, tu1Var, str2, zu1Var, str3);
                }
                str3 = (String) c5.a.a(u71Var, cVar);
            }
        }
    }
}
