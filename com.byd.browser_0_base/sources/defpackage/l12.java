package defpackage;

import defpackage.nx;

/* JADX INFO: compiled from: Placeholder.java */
/* JADX INFO: loaded from: classes.dex */
public final class l12 extends lo3 {
    @Override // defpackage.lo3
    public final void V(int i, int i2, int i3, int i4) {
        int iU = this.C0 + this.D0 + 0;
        int iO = this.y0 + this.z0 + 0;
        if (this.x0 > 0) {
            iU += this.w0[0].u();
            iO += this.w0[0].o();
        }
        int iMax = Math.max(this.e0, iU);
        int iMax2 = Math.max(this.f0, iO);
        if (i != 1073741824) {
            i2 = i == Integer.MIN_VALUE ? Math.min(iMax, i2) : i == 0 ? iMax : 0;
        }
        if (i3 != 1073741824) {
            i4 = i3 == Integer.MIN_VALUE ? Math.min(iMax2, i4) : i3 == 0 ? iMax2 : 0;
        }
        this.F0 = i2;
        this.G0 = i4;
        R(i2);
        O(i4);
        this.E0 = this.x0 > 0;
    }

    @Override // defpackage.qx
    public final void e(q71 q71Var, boolean z) {
        super.e(q71Var, z);
        if (this.x0 > 0) {
            qx qxVar = this.w0[0];
            qxVar.H();
            qxVar.h0 = 0.5f;
            qxVar.g0 = 0.5f;
            nx.a aVar = nx.a.LEFT;
            qxVar.h(aVar, this, aVar, 0);
            nx.a aVar2 = nx.a.RIGHT;
            qxVar.h(aVar2, this, aVar2, 0);
            nx.a aVar3 = nx.a.TOP;
            qxVar.h(aVar3, this, aVar3, 0);
            nx.a aVar4 = nx.a.BOTTOM;
            qxVar.h(aVar4, this, aVar4, 0);
        }
    }
}
