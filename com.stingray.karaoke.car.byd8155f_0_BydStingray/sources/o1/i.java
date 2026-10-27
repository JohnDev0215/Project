package o1;

import java.util.ArrayList;
import java.util.Arrays;
import p1.AbstractC2081i;
import p1.o;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends d {

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public d[] f20385r0 = new d[4];

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f20386s0 = 0;

    public final void V(d dVar) {
        if (dVar == this || dVar == null) {
            return;
        }
        int i7 = this.f20386s0 + 1;
        d[] dVarArr = this.f20385r0;
        if (i7 > dVarArr.length) {
            this.f20385r0 = (d[]) Arrays.copyOf(dVarArr, dVarArr.length * 2);
        }
        d[] dVarArr2 = this.f20385r0;
        int i10 = this.f20386s0;
        dVarArr2[i10] = dVar;
        this.f20386s0 = i10 + 1;
    }

    public final void W(int i7, ArrayList arrayList, o oVar) {
        for (int i10 = 0; i10 < this.f20386s0; i10++) {
            d dVar = this.f20385r0[i10];
            ArrayList arrayList2 = oVar.f20676a;
            if (!arrayList2.contains(dVar)) {
                arrayList2.add(dVar);
            }
        }
        for (int i11 = 0; i11 < this.f20386s0; i11++) {
            AbstractC2081i.b(this.f20385r0[i11], i7, arrayList, oVar);
        }
    }
}
