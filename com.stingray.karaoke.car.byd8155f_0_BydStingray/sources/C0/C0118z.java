package C0;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: C0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0118z implements g0, M {
    public final /* synthetic */ B l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ G f925m;

    public C0118z(G g7) {
        this.f925m = g7;
        this.l = g7.f854s;
    }

    @Override // C0.M
    public final L C(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k) {
        return this.l.C(i7, i10, map, interfaceC2782k);
    }

    @Override // a1.b
    public final float K(long j3) {
        return this.l.K(j3);
    }

    @Override // a1.b
    public final int O(float f10) {
        return this.l.O(f10);
    }

    @Override // C0.g0
    public final List W(Object obj, InterfaceC2785n interfaceC2785n) {
        G g7 = this.f925m;
        E0.F f10 = (E0.F) g7.f853r.get(obj);
        List listO = f10 != null ? f10.o() : null;
        if (listO != null) {
            return listO;
        }
        V.d dVar = g7.f859x;
        int i7 = dVar.f9279n;
        int i10 = g7.f851p;
        if (i7 < i10) {
            throw new IllegalArgumentException("Error: currentPostLookaheadIndex cannot be greater than the size of thepostLookaheadComposedSlotIds list.");
        }
        if (i7 == i10) {
            dVar.b(obj);
        } else {
            Object[] objArr = dVar.l;
            Object obj2 = objArr[i10];
            objArr[i10] = obj;
        }
        g7.f851p++;
        HashMap map = g7.f856u;
        if (!map.containsKey(obj)) {
            g7.f858w.put(obj, g7.g(obj, interfaceC2785n));
            E0.F f11 = g7.l;
            if (f11.v() == 3) {
                f11.p0(true);
            } else {
                E0.F.q0(f11, true, 6);
            }
        }
        E0.F f12 = (E0.F) map.get(obj);
        if (f12 == null) {
            return n7.u.l;
        }
        List listQ0 = f12.z().q0();
        V.a aVar = (V.a) listQ0;
        int i11 = aVar.l.f9279n;
        for (int i12 = 0; i12 < i11; i12++) {
            ((E0.L) aVar.get(i12)).f1542S.f1556b = true;
        }
        return listQ0;
    }

    @Override // a1.b
    public final long Y(long j3) {
        return this.l.Y(j3);
    }

    @Override // a1.b
    public final float b0(long j3) {
        return this.l.b0(j3);
    }

    @Override // a1.b
    public final float getDensity() {
        return this.l.f840m;
    }

    @Override // C0.InterfaceC0108o
    public final a1.k getLayoutDirection() {
        return this.l.l;
    }

    @Override // a1.b
    public final long j0(float f10) {
        return this.l.j0(f10);
    }

    @Override // C0.M
    public final L m0(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k) {
        return this.l.C(i7, i10, map, interfaceC2782k);
    }

    @Override // a1.b
    public final float p() {
        return this.l.f841n;
    }

    @Override // a1.b
    public final float p0(int i7) {
        return this.l.p0(i7);
    }

    @Override // a1.b
    public final float r0(float f10) {
        return f10 / this.l.getDensity();
    }

    @Override // C0.InterfaceC0108o
    public final boolean v() {
        return this.l.v();
    }

    @Override // a1.b
    public final long w(float f10) {
        return this.l.w(f10);
    }

    @Override // a1.b
    public final long x(long j3) {
        return this.l.x(j3);
    }

    @Override // a1.b
    public final float y(float f10) {
        return this.l.getDensity() * f10;
    }
}
