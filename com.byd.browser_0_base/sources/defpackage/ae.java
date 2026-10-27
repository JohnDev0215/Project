package defpackage;

import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.qx;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public final class ae {
    public final ArrayList<qx> a = new ArrayList<>();
    public final a b = new a();
    public final rx c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {
        public qx.a a;
        public qx.a b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public boolean h;
        public boolean i;
        public int j;
    }

    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface b {
    }

    public ae(rx rxVar) {
        this.c = rxVar;
    }

    public final boolean a(int i, qx qxVar, b bVar) {
        qx.a[] aVarArr = qxVar.V;
        qx.a aVar = aVarArr[0];
        a aVar2 = this.b;
        aVar2.a = aVar;
        aVar2.b = aVarArr[1];
        aVar2.c = qxVar.u();
        aVar2.d = qxVar.o();
        aVar2.i = false;
        aVar2.j = i;
        qx.a aVar3 = aVar2.a;
        qx.a aVar4 = qx.a.MATCH_CONSTRAINT;
        boolean z = aVar3 == aVar4;
        boolean z2 = aVar2.b == aVar4;
        boolean z3 = z && qxVar.Z > 0.0f;
        boolean z4 = z2 && qxVar.Z > 0.0f;
        qx.a aVar5 = qx.a.FIXED;
        int[] iArr = qxVar.u;
        if (z3 && iArr[0] == 4) {
            aVar2.a = aVar5;
        }
        if (z4 && iArr[1] == 4) {
            aVar2.b = aVar5;
        }
        ((ConstraintLayout.a) bVar).b(qxVar, aVar2);
        qxVar.R(aVar2.e);
        qxVar.O(aVar2.f);
        qxVar.F = aVar2.h;
        int i2 = aVar2.g;
        qxVar.d0 = i2;
        qxVar.F = i2 > 0;
        aVar2.j = 0;
        return aVar2.i;
    }

    public final void b(rx rxVar, int i, int i2, int i3) {
        rxVar.getClass();
        int i4 = rxVar.e0;
        int i5 = rxVar.f0;
        rxVar.e0 = 0;
        rxVar.f0 = 0;
        rxVar.R(i2);
        rxVar.O(i3);
        if (i4 < 0) {
            rxVar.e0 = 0;
        } else {
            rxVar.e0 = i4;
        }
        if (i5 < 0) {
            rxVar.f0 = 0;
        } else {
            rxVar.f0 = i5;
        }
        rx rxVar2 = this.c;
        rxVar2.z0 = i;
        rxVar2.U();
    }

    public final void c(rx rxVar) {
        ArrayList<qx> arrayList = this.a;
        arrayList.clear();
        int size = rxVar.w0.size();
        for (int i = 0; i < size; i++) {
            qx qxVar = rxVar.w0.get(i);
            qx.a[] aVarArr = qxVar.V;
            qx.a aVar = aVarArr[0];
            qx.a aVar2 = qx.a.MATCH_CONSTRAINT;
            if (aVar == aVar2 || aVarArr[1] == aVar2) {
                arrayList.add(qxVar);
            }
        }
        rxVar.y0.b = true;
    }
}
