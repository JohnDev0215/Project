package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class he implements y4 {
    public static final List c = v03.C("accessInfo", "authors", "coverArt", "name", "uri");

    public static fe b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ce ceVar = null;
        List list = null;
        ee eeVar = null;
        String str = null;
        String str2 = null;
        while (true) {
            int iW = u71Var.W(c);
            if (iW == 0) {
                ceVar = (ce) c5.a(new wo1(ge.c, false)).a(u71Var, cVar);
            } else if (iW == 1) {
                list = (List) c5.a(new ua1(c5.a(new wo1(ie.c, false)))).a(u71Var, cVar);
            } else if (iW == 2) {
                eeVar = (ee) c5.a(new wo1(je.c, true)).a(u71Var, cVar);
            } else if (iW == 3) {
                str = (String) c5.a.a(u71Var, cVar);
            } else {
                if (iW != 4) {
                    v03.h(str);
                    v03.h(str2);
                    return new fe(ceVar, list, eeVar, str, str2);
                }
                str2 = (String) c5.a.a(u71Var, cVar);
            }
        }
    }
}
