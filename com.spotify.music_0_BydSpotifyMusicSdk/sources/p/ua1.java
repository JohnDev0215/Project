package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ua1 implements y4 {
    public final y4 c;

    public ua1(y4 y4Var) {
        this.c = y4Var;
    }

    @Override // p.y4
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final ArrayList a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        u71Var.j();
        ArrayList arrayList = new ArrayList();
        while (u71Var.hasNext()) {
            arrayList.add(this.c.a(u71Var, cVar));
        }
        u71Var.h();
        return arrayList;
    }
}
