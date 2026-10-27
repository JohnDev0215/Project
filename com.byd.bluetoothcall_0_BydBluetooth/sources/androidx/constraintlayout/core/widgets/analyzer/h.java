package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: Grouping.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static n a(ConstraintWidget constraintWidget, int i, ArrayList<n> arrayList, n nVar) {
        int iV1;
        int i2 = i == 0 ? constraintWidget.S0 : constraintWidget.T0;
        if (i2 != -1 && (nVar == null || i2 != nVar.f868c)) {
            int i3 = 0;
            while (true) {
                if (i3 >= arrayList.size()) {
                    break;
                }
                n nVar2 = arrayList.get(i3);
                if (nVar2.c() == i2) {
                    if (nVar != null) {
                        nVar.g(i, nVar2);
                        arrayList.remove(nVar);
                    }
                    nVar = nVar2;
                } else {
                    i3++;
                }
            }
        } else if (i2 != -1) {
            return nVar;
        }
        if (nVar == null) {
            if ((constraintWidget instanceof androidx.constraintlayout.core.widgets.h) && (iV1 = ((androidx.constraintlayout.core.widgets.h) constraintWidget).v1(i)) != -1) {
                int i4 = 0;
                while (true) {
                    if (i4 >= arrayList.size()) {
                        break;
                    }
                    n nVar3 = arrayList.get(i4);
                    if (nVar3.c() == iV1) {
                        nVar = nVar3;
                        break;
                    }
                    i4++;
                }
            }
            if (nVar == null) {
                nVar = new n(i);
            }
            arrayList.add(nVar);
        }
        if (nVar.a(constraintWidget)) {
            if (constraintWidget instanceof androidx.constraintlayout.core.widgets.f) {
                androidx.constraintlayout.core.widgets.f fVar = (androidx.constraintlayout.core.widgets.f) constraintWidget;
                fVar.u1().c(fVar.v1() == 0 ? 1 : 0, arrayList, nVar);
            }
            if (i == 0) {
                constraintWidget.S0 = nVar.c();
                constraintWidget.R.c(i, arrayList, nVar);
                constraintWidget.T.c(i, arrayList, nVar);
            } else {
                constraintWidget.T0 = nVar.c();
                constraintWidget.S.c(i, arrayList, nVar);
                constraintWidget.V.c(i, arrayList, nVar);
                constraintWidget.U.c(i, arrayList, nVar);
            }
            constraintWidget.Y.c(i, arrayList, nVar);
        }
        return nVar;
    }

    private static n b(ArrayList<n> arrayList, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n nVar = arrayList.get(i2);
            if (i == nVar.f868c) {
                return nVar;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:178:0x034f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(androidx.constraintlayout.core.widgets.d r16, androidx.constraintlayout.core.widgets.analyzer.b.InterfaceC0016b r17) {
        /*
            Method dump skipped, instruction units count: 930
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.h.c(androidx.constraintlayout.core.widgets.d, androidx.constraintlayout.core.widgets.analyzer.b$b):boolean");
    }

    public static boolean d(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour6;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.FIXED;
        return (dimensionBehaviour3 == dimensionBehaviour7 || dimensionBehaviour3 == (dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != dimensionBehaviour6)) || (dimensionBehaviour4 == dimensionBehaviour7 || dimensionBehaviour4 == (dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != dimensionBehaviour5));
    }
}
