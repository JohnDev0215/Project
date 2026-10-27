package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ue0 implements y4 {
    public static final List c = v03.C("name", "uri", "coverArt", "releaseDate", "duration", "contentRating", "playability", "playedState", "podcastV2");

    public static pe0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        String str2 = null;
        he0 he0Var = null;
        oe0 oe0Var = null;
        je0 je0Var = null;
        ge0 ge0Var = null;
        le0 le0Var = null;
        me0 me0Var = null;
        ne0 ne0Var = null;
        while (true) {
            switch (u71Var.W(c)) {
                case 0:
                    str = (String) c5.a.a(u71Var, cVar);
                    break;
                case 1:
                    str2 = (String) c5.a.a(u71Var, cVar);
                    break;
                case 2:
                    he0Var = (he0) c5.a(new wo1(re0.c, true)).a(u71Var, cVar);
                    break;
                case 3:
                    oe0Var = (oe0) c5.a(new wo1(ze0.c, false)).a(u71Var, cVar);
                    break;
                case 4:
                    te0 te0Var = te0.c;
                    w1 w1Var = c5.a;
                    je0Var = (je0) new wo1(te0Var, false).a(u71Var, cVar);
                    break;
                case 5:
                    ge0Var = (ge0) c5.a(new wo1(qe0.c, true)).a(u71Var, cVar);
                    break;
                case 6:
                    we0 we0Var = we0.c;
                    w1 w1Var2 = c5.a;
                    le0Var = (le0) new wo1(we0Var, false).a(u71Var, cVar);
                    break;
                case 7:
                    xe0 xe0Var = xe0.c;
                    w1 w1Var3 = c5.a;
                    me0Var = (me0) new wo1(xe0Var, false).a(u71Var, cVar);
                    break;
                case 8:
                    ye0 ye0Var = ye0.c;
                    w1 w1Var4 = c5.a;
                    ne0Var = (ne0) new wo1(ye0Var, false).a(u71Var, cVar);
                    break;
                default:
                    v03.h(str);
                    v03.h(str2);
                    v03.h(je0Var);
                    v03.h(le0Var);
                    v03.h(me0Var);
                    v03.h(ne0Var);
                    return new pe0(str, str2, he0Var, oe0Var, je0Var, ge0Var, le0Var, me0Var, ne0Var);
            }
        }
    }
}
