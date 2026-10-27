package p;

import com.spotify.webapi.service.models.Search;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class np0 implements y4 {
    public static final List c = v03.B(Search.Type.ARTIST);

    public static ep0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        xo0 xo0Var = null;
        while (u71Var.W(c) == 0) {
            xo0Var = (xo0) c5.a(new wo1(gp0.c, true)).a(u71Var, cVar);
        }
        return new ep0(xo0Var);
    }
}
