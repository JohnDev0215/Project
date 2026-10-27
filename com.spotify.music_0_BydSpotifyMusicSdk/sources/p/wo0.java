package p;

import com.spotify.webapi.service.models.Search;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class wo0 implements y4 {
    public static final List c = v03.B(Search.Type.ALBUM);

    public static no0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        go0 go0Var = null;
        while (u71Var.W(c) == 0) {
            go0Var = (go0) c5.a(new wo1(po0.c, true)).a(u71Var, cVar);
        }
        return new no0(go0Var);
    }
}
