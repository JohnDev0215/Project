package defpackage;

import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.ae;
import defpackage.qx;

/* JADX INFO: compiled from: VirtualLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class lo3 extends ku0 {
    public int y0 = 0;
    public int z0 = 0;
    public int A0 = 0;
    public int B0 = 0;
    public int C0 = 0;
    public int D0 = 0;
    public boolean E0 = false;
    public int F0 = 0;
    public int G0 = 0;
    public final ae.a H0 = new ae.a();
    public ae.b I0 = null;

    public final void W(qx qxVar, qx.a aVar, int i, qx.a aVar2, int i2) {
        ae.b bVar;
        qx qxVar2;
        while (true) {
            bVar = this.I0;
            if (bVar != null || (qxVar2 = this.W) == null) {
                break;
            } else {
                this.I0 = ((rx) qxVar2).A0;
            }
        }
        ae.a aVar3 = this.H0;
        aVar3.a = aVar;
        aVar3.b = aVar2;
        aVar3.c = i;
        aVar3.d = i2;
        ((ConstraintLayout.a) bVar).b(qxVar, aVar3);
        qxVar.R(aVar3.e);
        qxVar.O(aVar3.f);
        qxVar.F = aVar3.h;
        int i3 = aVar3.g;
        qxVar.d0 = i3;
        qxVar.F = i3 > 0;
    }

    @Override // defpackage.ku0, defpackage.iu0
    public final void c() {
        for (int i = 0; i < this.x0; i++) {
            qx qxVar = this.w0[i];
            if (qxVar != null) {
                qxVar.H = true;
            }
        }
    }

    public void V(int i, int i2, int i3, int i4) {
    }
}
