package p;

import com.apollographql.apollo3.api.json.JsonReader$Token;

/* JADX INFO: loaded from: classes.dex */
public final class so1 implements y4 {
    public final y4 c;

    public so1(y4 y4Var) {
        v03.j(y4Var, "wrappedAdapter");
        this.c = y4Var;
        if (!(!(y4Var instanceof so1))) {
            throw new IllegalStateException("The adapter is already nullable".toString());
        }
    }

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        if (u71Var.peek() != JsonReader$Token.r) {
            return this.c.a(u71Var, cVar);
        }
        u71Var.D();
        return null;
    }
}
