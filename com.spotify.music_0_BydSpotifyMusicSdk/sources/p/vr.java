package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class vr implements y4 {
    public static final List c = v03.C("name", "uri", "coverArt", "releaseDate", "duration", "contentRating", "playability", "playedState");

    public static kr b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        String str2 = null;
        hr hrVar = null;
        or orVar = null;
        jr jrVar = null;
        gr grVar = null;
        mr mrVar = null;
        nr nrVar = null;
        while (true) {
            switch (u71Var.W(c)) {
                case 0:
                    str = (String) c5.a.a(u71Var, cVar);
                    break;
                case 1:
                    str2 = (String) c5.a.a(u71Var, cVar);
                    break;
                case 2:
                    hrVar = (hr) c5.a(new wo1(sr.c, true)).a(u71Var, cVar);
                    break;
                case 3:
                    orVar = (or) c5.a(new wo1(zr.c, false)).a(u71Var, cVar);
                    break;
                case 4:
                    ur urVar = ur.c;
                    w1 w1Var = c5.a;
                    jrVar = (jr) new wo1(urVar, false).a(u71Var, cVar);
                    break;
                case 5:
                    grVar = (gr) c5.a(new wo1(rr.c, true)).a(u71Var, cVar);
                    break;
                case 6:
                    xr xrVar = xr.c;
                    w1 w1Var2 = c5.a;
                    mrVar = (mr) new wo1(xrVar, false).a(u71Var, cVar);
                    break;
                case 7:
                    yr yrVar = yr.c;
                    w1 w1Var3 = c5.a;
                    nrVar = (nr) new wo1(yrVar, false).a(u71Var, cVar);
                    break;
                default:
                    v03.h(str);
                    v03.h(str2);
                    v03.h(jrVar);
                    v03.h(mrVar);
                    v03.h(nrVar);
                    return new kr(str, str2, hrVar, orVar, jrVar, grVar, mrVar, nrVar);
            }
        }
    }
}
