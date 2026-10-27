package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k31 implements y4 {
    public static final k31 c = new k31();
    public static final List e = v03.C("url", "width", "height");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        String str = null;
        Integer num = null;
        Integer num2 = null;
        while (true) {
            int iW = u71Var.W(e);
            if (iW == 0) {
                str = (String) c5.a.a(u71Var, cVar);
            } else if (iW == 1) {
                num = (Integer) c5.f.a(u71Var, cVar);
            } else {
                if (iW != 2) {
                    v03.h(str);
                    return new h31(str, num, num2);
                }
                num2 = (Integer) c5.f.a(u71Var, cVar);
            }
        }
    }
}
