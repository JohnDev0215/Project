package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class vv1 implements y4 {
    public static final List c = v03.B("items");

    public static ov1 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ArrayList arrayList = null;
        while (u71Var.W(c) == 0) {
            uv1 uv1Var = uv1.c;
            w1 w1Var = c5.a;
            wo1 wo1Var = new wo1(uv1Var, true);
            u71Var.j();
            arrayList = new ArrayList();
            while (u71Var.hasNext()) {
                arrayList.add(wo1Var.a(u71Var, cVar));
            }
            u71Var.h();
        }
        v03.h(arrayList);
        return new ov1(arrayList);
    }
}
