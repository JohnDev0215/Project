package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ds0 implements y4 {
    public static final ds0 c = new ds0();
    public static final List e = v03.C("library", "libraryV2");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        qr0 qr0Var = null;
        rr0 rr0Var = null;
        while (true) {
            int iW = u71Var.W(e);
            if (iW == 0) {
                qr0Var = (qr0) c5.a(new wo1(bs0.c, false)).a(u71Var, cVar);
            } else {
                if (iW != 1) {
                    return new sr0(qr0Var, rr0Var);
                }
                rr0Var = (rr0) c5.a(new wo1(cs0.c, false)).a(u71Var, cVar);
            }
        }
    }
}
