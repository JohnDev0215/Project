package androidx.work.impl.constraints;

import java.util.ArrayList;
import java.util.List;
import p.an1;
import p.ky2;
import p.nn2;
import p.ol;
import p.ou;
import p.qy;
import p.ry0;
import p.v03;
import p.vc1;
import p.zm1;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final List a;

    public a(nn2 nn2Var) {
        v03.j(nn2Var, "trackers");
        qy qyVar = nn2Var.c;
        List listC = v03.C(new ol(nn2Var.a, 0), new ol(nn2Var.b), new ol(nn2Var.d, 4), new ol(qyVar, 2), new ol(qyVar, 3), new an1(qyVar), new zm1(qyVar));
        v03.j(listC, "controllers");
        this.a = listC;
    }

    public final boolean a(ky2 ky2Var) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.a) {
            androidx.work.impl.constraints.controllers.a aVar = (androidx.work.impl.constraints.controllers.a) obj;
            aVar.getClass();
            if (aVar.b(ky2Var) && aVar.c(aVar.a.a())) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            vc1 vc1VarC = vc1.c();
            int i = b.a;
            ou.h0(arrayList, null, null, null, new ry0() { // from class: androidx.work.impl.constraints.WorkConstraintsTracker$areAllConstraintsMet$1
                @Override // p.ry0
                public final Object invoke(Object obj2) {
                    androidx.work.impl.constraints.controllers.a aVar2 = (androidx.work.impl.constraints.controllers.a) obj2;
                    v03.j(aVar2, "it");
                    return aVar2.getClass().getSimpleName();
                }
            }, 31);
            vc1VarC.getClass();
        }
        return arrayList.isEmpty();
    }
}
