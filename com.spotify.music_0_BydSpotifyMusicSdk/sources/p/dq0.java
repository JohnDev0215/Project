package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class dq0 implements y4 {
    public static final List c = v03.B("items");

    public static up0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ArrayList arrayList = null;
        while (u71Var.W(c) == 0) {
            so1 so1VarA = c5.a(new wo1(aq0.c, true));
            u71Var.j();
            ArrayList arrayList2 = new ArrayList();
            while (u71Var.hasNext()) {
                arrayList2.add(so1VarA.a(u71Var, cVar));
            }
            u71Var.h();
            arrayList = arrayList2;
        }
        v03.h(arrayList);
        return new up0(arrayList);
    }
}
