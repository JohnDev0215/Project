package defpackage;

import defpackage.qx;
import java.util.ArrayList;

/* JADX INFO: compiled from: Grouping.java */
/* JADX INFO: loaded from: classes.dex */
public final class lt0 {
    public static ft3 a(qx qxVar, int i, ArrayList<ft3> arrayList, ft3 ft3Var) {
        boolean z;
        int i2;
        int i3 = i == 0 ? qxVar.u0 : qxVar.v0;
        if (i3 != -1 && (ft3Var == null || i3 != ft3Var.b)) {
            int i4 = 0;
            while (true) {
                if (i4 >= arrayList.size()) {
                    break;
                }
                ft3 ft3Var2 = arrayList.get(i4);
                if (ft3Var2.b == i3) {
                    if (ft3Var != null) {
                        ft3Var.c(i, ft3Var2);
                        arrayList.remove(ft3Var);
                    }
                    ft3Var = ft3Var2;
                } else {
                    i4++;
                }
            }
        } else if (i3 != -1) {
            return ft3Var;
        }
        if (ft3Var == null) {
            if (qxVar instanceof ku0) {
                ku0 ku0Var = (ku0) qxVar;
                int i5 = 0;
                while (true) {
                    if (i5 >= ku0Var.x0) {
                        i2 = -1;
                        break;
                    }
                    qx qxVar2 = ku0Var.w0[i5];
                    if ((i == 0 && (i2 = qxVar2.u0) != -1) || (i == 1 && (i2 = qxVar2.v0) != -1)) {
                        break;
                    }
                    i5++;
                }
                if (i2 != -1) {
                    int i6 = 0;
                    while (true) {
                        if (i6 >= arrayList.size()) {
                            break;
                        }
                        ft3 ft3Var3 = arrayList.get(i6);
                        if (ft3Var3.b == i2) {
                            ft3Var = ft3Var3;
                            break;
                        }
                        i6++;
                    }
                }
            }
            if (ft3Var == null) {
                ft3Var = new ft3(i);
            }
            arrayList.add(ft3Var);
        }
        ArrayList<qx> arrayList2 = ft3Var.a;
        if (arrayList2.contains(qxVar)) {
            z = false;
        } else {
            arrayList2.add(qxVar);
            z = true;
        }
        if (z) {
            if (qxVar instanceof rt0) {
                rt0 rt0Var = (rt0) qxVar;
                rt0Var.z0.c(rt0Var.A0 == 0 ? 1 : 0, ft3Var, arrayList);
            }
            int i7 = ft3Var.b;
            if (i == 0) {
                qxVar.u0 = i7;
                qxVar.K.c(i, ft3Var, arrayList);
                qxVar.M.c(i, ft3Var, arrayList);
            } else {
                qxVar.v0 = i7;
                qxVar.L.c(i, ft3Var, arrayList);
                qxVar.O.c(i, ft3Var, arrayList);
                qxVar.N.c(i, ft3Var, arrayList);
            }
            qxVar.R.c(i, ft3Var, arrayList);
        }
        return ft3Var;
    }

    public static boolean b(qx.a aVar, qx.a aVar2, qx.a aVar3, qx.a aVar4) {
        qx.a aVar5 = qx.a.FIXED;
        qx.a aVar6 = qx.a.MATCH_PARENT;
        qx.a aVar7 = qx.a.WRAP_CONTENT;
        return (aVar3 == aVar5 || aVar3 == aVar7 || (aVar3 == aVar6 && aVar != aVar7)) || (aVar4 == aVar5 || aVar4 == aVar7 || (aVar4 == aVar6 && aVar2 != aVar7));
    }
}
