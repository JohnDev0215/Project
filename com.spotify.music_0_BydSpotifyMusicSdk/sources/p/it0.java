package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class it0 implements y4 {
    public static final it0 c = new it0();
    public static final List e = v03.B("podcastUnionV2");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        gt0 gt0Var = null;
        while (u71Var.W(e) == 0) {
            jt0 jt0Var = jt0.c;
            w1 w1Var = c5.a;
            gt0Var = (gt0) new wo1(jt0Var, true).a(u71Var, cVar);
        }
        v03.h(gt0Var);
        return new ft0(gt0Var);
    }
}
