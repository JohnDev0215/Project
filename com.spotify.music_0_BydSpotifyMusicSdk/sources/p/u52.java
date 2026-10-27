package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class u52 {
    public androidx.constraintlayout.core.widgets.analyzer.f a;
    public ArrayList b;

    public static long a(androidx.constraintlayout.core.widgets.analyzer.a aVar, long j) {
        androidx.constraintlayout.core.widgets.analyzer.f fVar = aVar.d;
        if (fVar instanceof androidx.constraintlayout.core.widgets.analyzer.c) {
            return j;
        }
        ArrayList arrayList = aVar.k;
        int size = arrayList.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            e90 e90Var = (e90) arrayList.get(i);
            if (e90Var instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                androidx.constraintlayout.core.widgets.analyzer.a aVar2 = (androidx.constraintlayout.core.widgets.analyzer.a) e90Var;
                if (aVar2.d != fVar) {
                    jMin = Math.min(jMin, a(aVar2, ((long) aVar2.f) + j));
                }
            }
        }
        if (aVar != fVar.i) {
            return jMin;
        }
        long j2 = fVar.j();
        androidx.constraintlayout.core.widgets.analyzer.a aVar3 = fVar.h;
        long j3 = j - j2;
        return Math.min(Math.min(jMin, a(aVar3, j3)), j3 - ((long) aVar3.f));
    }

    public static long b(androidx.constraintlayout.core.widgets.analyzer.a aVar, long j) {
        androidx.constraintlayout.core.widgets.analyzer.f fVar = aVar.d;
        if (fVar instanceof androidx.constraintlayout.core.widgets.analyzer.c) {
            return j;
        }
        ArrayList arrayList = aVar.k;
        int size = arrayList.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            e90 e90Var = (e90) arrayList.get(i);
            if (e90Var instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                androidx.constraintlayout.core.widgets.analyzer.a aVar2 = (androidx.constraintlayout.core.widgets.analyzer.a) e90Var;
                if (aVar2.d != fVar) {
                    jMax = Math.max(jMax, b(aVar2, ((long) aVar2.f) + j));
                }
            }
        }
        if (aVar != fVar.h) {
            return jMax;
        }
        long j2 = fVar.j();
        androidx.constraintlayout.core.widgets.analyzer.a aVar3 = fVar.i;
        long j3 = j + j2;
        return Math.max(Math.max(jMax, b(aVar3, j3)), j3 - ((long) aVar3.f));
    }
}
