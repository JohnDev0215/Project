package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class vm2 implements y4 {
    public static final vm2 c = new vm2();
    public static final List e = v03.B("coverArt");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        pm2 pm2Var = null;
        while (u71Var.W(e) == 0) {
            pm2Var = (pm2) c5.a(c5.b(ym2.c, true)).a(u71Var, cVar);
        }
        return new mm2(pm2Var);
    }
}
