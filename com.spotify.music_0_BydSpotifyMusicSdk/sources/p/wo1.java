package p;

import com.apollographql.apollo3.api.json.JsonReader$Token;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class wo1 implements y4 {
    public final y4 c;
    public final boolean e;

    public wo1(y4 y4Var, boolean z) {
        this.c = y4Var;
        this.e = z;
    }

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        if (this.e) {
            if (u71Var instanceof re1) {
                u71Var = (re1) u71Var;
            } else {
                JsonReader$Token jsonReader$TokenPeek = u71Var.peek();
                if (jsonReader$TokenPeek != JsonReader$Token.k) {
                    throw new IllegalStateException(("Failed to buffer json reader, expected `BEGIN_OBJECT` but found `" + jsonReader$TokenPeek + "` json token").toString());
                }
                ArrayList path = u71Var.getPath();
                Object objJ = he1.J(u71Var);
                if (objJ == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
                }
                u71Var = new re1(path, (Map) objJ);
            }
        }
        u71Var.f();
        Object objA = this.c.a(u71Var, cVar);
        u71Var.k();
        return objA;
    }
}
