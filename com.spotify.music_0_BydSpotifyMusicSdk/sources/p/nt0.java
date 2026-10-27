package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class nt0 implements y4 {
    public static final nt0 c = new nt0();
    public static final List e = v03.B("podcastUnionV2");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        lt0 lt0Var = null;
        while (u71Var.W(e) == 0) {
            ot0 ot0Var = ot0.c;
            w1 w1Var = c5.a;
            lt0Var = (lt0) new wo1(ot0Var, true).a(u71Var, cVar);
        }
        v03.h(lt0Var);
        return new kt0(lt0Var);
    }
}
