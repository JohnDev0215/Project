package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class xn0 implements y4 {
    public static final xn0 c = new xn0();
    public static final List e = v03.C("topTracks", "popularReleasesAlbums");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        sn0 sn0Var = null;
        rn0 rn0Var = null;
        while (true) {
            int iW = u71Var.W(e);
            if (iW == 0) {
                eo0 eo0Var = eo0.c;
                w1 w1Var = c5.a;
                sn0Var = (sn0) new wo1(eo0Var, true).a(u71Var, cVar);
            } else {
                if (iW != 1) {
                    v03.h(sn0Var);
                    v03.h(rn0Var);
                    return new ln0(sn0Var, rn0Var);
                }
                do0 do0Var = do0.c;
                w1 w1Var2 = c5.a;
                rn0Var = (rn0) new wo1(do0Var, true).a(u71Var, cVar);
            }
        }
    }
}
