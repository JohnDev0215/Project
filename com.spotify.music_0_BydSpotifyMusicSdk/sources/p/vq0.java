package p;

import com.spotify.webapi.service.models.Search;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class vq0 implements y4 {
    public static final List c = v03.B(Search.Type.EPISODE);

    public static mq0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        gq0 gq0Var = null;
        while (u71Var.W(c) == 0) {
            gq0Var = (gq0) c5.a(new wo1(pq0.c, true)).a(u71Var, cVar);
        }
        return new mq0(gq0Var);
    }
}
