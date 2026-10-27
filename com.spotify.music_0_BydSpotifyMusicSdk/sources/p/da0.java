package p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class da0 extends hu0 {
    public final hu0 b;

    public da0(c81 c81Var) {
        v03.j(c81Var, "delegate");
        this.b = c81Var;
    }

    @Override // p.hu0
    public final pd2 a(os1 os1Var) {
        return this.b.a(os1Var);
    }

    @Override // p.hu0
    public final void b(os1 os1Var, os1 os1Var2) {
        v03.j(os1Var, "source");
        v03.j(os1Var2, "target");
        this.b.b(os1Var, os1Var2);
    }

    @Override // p.hu0
    public final void c(os1 os1Var) {
        this.b.c(os1Var);
    }

    @Override // p.hu0
    public final void d(os1 os1Var) {
        v03.j(os1Var, "path");
        this.b.d(os1Var);
    }

    @Override // p.hu0
    public final List g(os1 os1Var) {
        v03.j(os1Var, "dir");
        List<os1> listG = this.b.g(os1Var);
        ArrayList arrayList = new ArrayList();
        for (os1 os1Var2 : listG) {
            v03.j(os1Var2, "path");
            arrayList.add(os1Var2);
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    @Override // p.hu0
    public final x50 i(os1 os1Var) {
        v03.j(os1Var, "path");
        x50 x50VarI = this.b.i(os1Var);
        if (x50VarI == null) {
            return null;
        }
        os1 os1Var2 = (os1) x50VarI.d;
        if (os1Var2 == null) {
            return x50VarI;
        }
        boolean z = x50VarI.b;
        boolean z2 = x50VarI.c;
        Long l = (Long) x50VarI.e;
        Long l2 = (Long) x50VarI.f;
        Long l3 = (Long) x50VarI.g;
        Long l4 = (Long) x50VarI.h;
        Map map = (Map) x50VarI.i;
        v03.j(map, "extras");
        return new x50(z, z2, os1Var2, l, l2, l3, l4, map);
    }

    @Override // p.hu0
    public final b81 j(os1 os1Var) {
        v03.j(os1Var, "file");
        return this.b.j(os1Var);
    }

    @Override // p.hu0
    public final pd2 k(os1 os1Var) {
        os1 os1VarB = os1Var.b();
        hu0 hu0Var = this.b;
        if (os1VarB != null) {
            gb<os1> gbVar = new gb();
            while (os1VarB != null && !f(os1VarB)) {
                gbVar.d(gbVar.k + 1);
                int length = gbVar.c;
                if (length == 0) {
                    Object[] objArr = gbVar.e;
                    v03.j(objArr, "<this>");
                    length = objArr.length;
                }
                int i = length - 1;
                gbVar.c = i;
                gbVar.e[i] = os1VarB;
                gbVar.k++;
                os1VarB = os1VarB.b();
            }
            for (os1 os1Var2 : gbVar) {
                v03.j(os1Var2, "dir");
                hu0Var.c(os1Var2);
            }
        }
        return hu0Var.k(os1Var);
    }

    @Override // p.hu0
    public final de2 l(os1 os1Var) {
        v03.j(os1Var, "file");
        return this.b.l(os1Var);
    }

    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        return r22.a(da0.class).b() + '(' + this.b + ')';
    }
}
