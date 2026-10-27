package G;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class M implements C0.M {
    public final G l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0.g0 f2515m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final I f2516n;
    public final HashMap o = new HashMap();

    public M(G g7, C0.g0 g0Var) {
        this.l = g7;
        this.f2515m = g0Var;
        this.f2516n = (I) g7.f2504b.a();
    }

    @Override // C0.M
    public final C0.L C(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k) {
        return this.f2515m.C(i7, i10, map, interfaceC2782k);
    }

    @Override // a1.b
    public final float K(long j3) {
        return this.f2515m.K(j3);
    }

    @Override // a1.b
    public final int O(float f10) {
        return this.f2515m.O(f10);
    }

    @Override // a1.b
    public final long Y(long j3) {
        return this.f2515m.Y(j3);
    }

    public final List a(long j3, int i7) {
        HashMap map = this.o;
        List list = (List) map.get(Integer.valueOf(i7));
        if (list != null) {
            return list;
        }
        I i10 = this.f2516n;
        Object objC = i10.c(i7);
        List listW = this.f2515m.W(objC, this.l.a(objC, i7, i10.d(i7)));
        int size = listW.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(((C0.J) listW.get(i11)).b(j3));
        }
        map.put(Integer.valueOf(i7), arrayList);
        return arrayList;
    }

    @Override // a1.b
    public final float b0(long j3) {
        return this.f2515m.b0(j3);
    }

    @Override // a1.b
    public final float getDensity() {
        return this.f2515m.getDensity();
    }

    @Override // C0.InterfaceC0108o
    public final a1.k getLayoutDirection() {
        return this.f2515m.getLayoutDirection();
    }

    @Override // a1.b
    public final long j0(float f10) {
        return this.f2515m.j0(f10);
    }

    @Override // C0.M
    public final C0.L m0(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k) {
        return this.f2515m.m0(i7, i10, map, interfaceC2782k);
    }

    @Override // a1.b
    public final float p() {
        return this.f2515m.p();
    }

    @Override // a1.b
    public final float p0(int i7) {
        return this.f2515m.p0(i7);
    }

    @Override // a1.b
    public final float r0(float f10) {
        return this.f2515m.r0(f10);
    }

    @Override // C0.InterfaceC0108o
    public final boolean v() {
        return this.f2515m.v();
    }

    @Override // a1.b
    public final long w(float f10) {
        return this.f2515m.w(f10);
    }

    @Override // a1.b
    public final long x(long j3) {
        return this.f2515m.x(j3);
    }

    @Override // a1.b
    public final float y(float f10) {
        return this.f2515m.y(f10);
    }
}
