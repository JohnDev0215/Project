package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class nm0 implements y4 {
    public static final nm0 c = new nm0();
    public static final List e = v03.B("albumUnion");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        im0 im0Var = null;
        while (u71Var.W(e) == 0) {
            mm0 mm0Var = mm0.c;
            w1 w1Var = c5.a;
            im0Var = (im0) new wo1(mm0Var, true).a(u71Var, cVar);
        }
        v03.h(im0Var);
        return new jm0(im0Var);
    }
}
