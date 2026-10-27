package p;

import com.spotify.webapi.service.models.Search;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ym0 implements y4 {
    public static final ym0 c = new ym0();
    public static final List e = v03.B(Search.Type.TRACK);

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        tm0 tm0Var = null;
        while (u71Var.W(e) == 0) {
            an0 an0Var = an0.c;
            w1 w1Var = c5.a;
            tm0Var = (tm0) new wo1(an0Var, true).a(u71Var, cVar);
        }
        v03.h(tm0Var);
        return new rm0(tm0Var);
    }
}
