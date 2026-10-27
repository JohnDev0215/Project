package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class aa2 implements y4 {
    public static final aa2 c = new aa2();
    public static final List e = v03.B("__typename");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        y82 y82VarB;
        z82 z82VarB;
        a92 a92VarB;
        b92 b92VarB;
        c92 c92VarB;
        e92 e92VarB;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        f92 f92VarB = null;
        String str = null;
        while (u71Var.W(e) == 0) {
            str = (String) c5.a.a(u71Var, cVar);
        }
        if (str == null) {
            throw new IllegalStateException("__typename was not found".toString());
        }
        lm lmVarC = com.apollographql.apollo3.api.a.c("AlbumResponseWrapper");
        z4 z4Var = cVar.b;
        if (com.apollographql.apollo3.api.a.a(lmVarC, z4Var.a(), str, z4Var)) {
            u71Var.g();
            y82VarB = ca2.b(u71Var, cVar);
        } else {
            y82VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("ArtistResponseWrapper"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            z82VarB = da2.b(u71Var, cVar);
        } else {
            z82VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("AudiobookResponseWrapper"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            a92VarB = ea2.b(u71Var, cVar);
        } else {
            a92VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("EpisodeResponseWrapper"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            b92VarB = fa2.b(u71Var, cVar);
        } else {
            b92VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("PlaylistResponseWrapper"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            c92VarB = ga2.b(u71Var, cVar);
        } else {
            c92VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("PodcastResponseWrapper"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            e92VarB = ia2.b(u71Var, cVar);
        } else {
            e92VarB = null;
        }
        if (com.apollographql.apollo3.api.a.a(com.apollographql.apollo3.api.a.c("TrackResponseWrapper"), z4Var.a(), str, z4Var)) {
            u71Var.g();
            f92VarB = ja2.b(u71Var, cVar);
        }
        return new w82(str, y82VarB, z82VarB, a92VarB, b92VarB, c92VarB, e92VarB, f92VarB);
    }
}
