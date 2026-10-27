package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class na2 implements y4 {
    public static final na2 c = new na2();
    public static final List e = v03.C("featured", "itemsV2");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        while (true) {
            int iW = u71Var.W(e);
            if (iW == 0) {
                r92 r92Var = r92.c;
                w1 w1Var = c5.a;
                wo1 wo1Var = new wo1(r92Var, true);
                u71Var.j();
                arrayList = new ArrayList();
                while (u71Var.hasNext()) {
                    arrayList.add(wo1Var.a(u71Var, cVar));
                }
                u71Var.h();
            } else {
                if (iW != 1) {
                    v03.h(arrayList);
                    v03.h(arrayList2);
                    return new j92(arrayList, arrayList2);
                }
                ba2 ba2Var = ba2.c;
                w1 w1Var2 = c5.a;
                wo1 wo1Var2 = new wo1(ba2Var, false);
                u71Var.j();
                arrayList2 = new ArrayList();
                while (u71Var.hasNext()) {
                    arrayList2.add(wo1Var2.a(u71Var, cVar));
                }
                u71Var.h();
            }
        }
    }
}
