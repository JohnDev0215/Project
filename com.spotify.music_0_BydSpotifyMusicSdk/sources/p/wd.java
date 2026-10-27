package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class wd implements y4 {
    public static final List c = v03.C("name", "accessInfo", "chaptersV2", "trailerV2");

    public static ud b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        od odVar = null;
        pd pdVar = null;
        td tdVar = null;
        while (true) {
            int iW = u71Var.W(c);
            if (iW == 0) {
                str = (String) c5.a.a(u71Var, cVar);
            } else if (iW == 1) {
                odVar = (od) c5.a(new wo1(vd.c, false)).a(u71Var, cVar);
            } else if (iW == 2) {
                pdVar = (pd) c5.a(new wo1(xd.c, true)).a(u71Var, cVar);
            } else {
                if (iW != 3) {
                    v03.h(str);
                    return new ud(str, odVar, pdVar, tdVar);
                }
                tdVar = (td) c5.a(new wo1(be.c, true)).a(u71Var, cVar);
            }
        }
    }
}
