package p;

import com.spotify.webapi.service.models.Search;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zn0 implements y4 {
    public static final zn0 c = new zn0();
    public static final List e = v03.B(Search.Type.TRACK);

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        tn0 tn0Var = null;
        while (u71Var.W(e) == 0) {
            fo0 fo0Var = fo0.c;
            w1 w1Var = c5.a;
            tn0Var = (tn0) new wo1(fo0Var, true).a(u71Var, cVar);
        }
        v03.h(tn0Var);
        return new nn0(tn0Var);
    }
}
