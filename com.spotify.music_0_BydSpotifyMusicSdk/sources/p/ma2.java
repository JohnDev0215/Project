package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ma2 implements y4 {
    public static final ma2 c = new ma2();
    public static final List e = v03.C("topResults", "albums", "artists", "audiobooks", "episodes", "playlists", "podcasts", "tracksV2");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        j92 j92Var = null;
        i82 i82Var = null;
        j82 j82Var = null;
        k82 k82Var = null;
        m82 m82Var = null;
        g92 g92Var = null;
        h92 h92Var = null;
        k92 k92Var = null;
        while (true) {
            switch (u71Var.W(e)) {
                case 0:
                    j92Var = (j92) c5.a(new wo1(na2.c, false)).a(u71Var, cVar);
                    break;
                case 1:
                    i82Var = (i82) c5.a(new wo1(m92.c, false)).a(u71Var, cVar);
                    break;
                case 2:
                    j82Var = (j82) c5.a(new wo1(n92.c, false)).a(u71Var, cVar);
                    break;
                case 3:
                    k82Var = (k82) c5.a(new wo1(o92.c, false)).a(u71Var, cVar);
                    break;
                case 4:
                    m82Var = (m82) c5.a(new wo1(q92.c, false)).a(u71Var, cVar);
                    break;
                case 5:
                    g92Var = (g92) c5.a(new wo1(ka2.c, false)).a(u71Var, cVar);
                    break;
                case 6:
                    h92Var = (h92) c5.a(new wo1(la2.c, false)).a(u71Var, cVar);
                    break;
                case 7:
                    k92Var = (k92) c5.a(new wo1(oa2.c, false)).a(u71Var, cVar);
                    break;
                default:
                    return new i92(j92Var, i82Var, j82Var, k82Var, m82Var, g92Var, h92Var, k92Var);
            }
        }
    }
}
