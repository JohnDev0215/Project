package p;

import com.spotify.webapi.service.models.Search;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class kr0 implements y4 {
    public static final List c = v03.B(Search.Type.TRACK);

    public static br0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        cr0 cr0Var = null;
        while (u71Var.W(c) == 0) {
            cr0Var = (cr0) c5.a(new wo1(lr0.c, true)).a(u71Var, cVar);
        }
        return new br0(cr0Var);
    }
}
