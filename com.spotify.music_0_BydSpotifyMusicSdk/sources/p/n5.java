package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class n5 implements y4 {
    public static final List c = v03.C("uri", "name", "coverArt", "artists", "date");

    public static m5 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        String str2 = null;
        i5 i5Var = null;
        h5 h5Var = null;
        j5 j5Var = null;
        while (true) {
            int iW = u71Var.W(c);
            if (iW == 0) {
                str = (String) c5.a.a(u71Var, cVar);
            } else if (iW == 1) {
                str2 = (String) c5.a.a(u71Var, cVar);
            } else if (iW == 2) {
                i5Var = (i5) c5.a(new wo1(p5.c, true)).a(u71Var, cVar);
            } else if (iW == 3) {
                o5 o5Var = o5.c;
                w1 w1Var = c5.a;
                h5Var = (h5) new wo1(o5Var, false).a(u71Var, cVar);
            } else {
                if (iW != 4) {
                    v03.h(str);
                    v03.h(str2);
                    v03.h(h5Var);
                    return new m5(str, str2, i5Var, h5Var, j5Var);
                }
                j5Var = (j5) c5.a(new wo1(q5.c, false)).a(u71Var, cVar);
            }
        }
    }
}
