package C0;

import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: C0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0110q implements M, InterfaceC0108o {
    public final a1.k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0108o f911m;

    public C0110q(InterfaceC0108o interfaceC0108o, a1.k kVar) {
        this.l = kVar;
        this.f911m = interfaceC0108o;
    }

    @Override // C0.M
    public final L C(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k) {
        if (i7 < 0) {
            i7 = 0;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        if ((i7 & (-16777216)) == 0 && ((-16777216) & i10) == 0) {
            return new C0109p(i7, i10, map);
        }
        l9.d.h0("Size(" + i7 + " x " + i10 + ") is out of range. Each dimension must be between 0 and 16777215.");
        throw null;
    }

    @Override // a1.b
    public final float K(long j3) {
        return this.f911m.K(j3);
    }

    @Override // a1.b
    public final int O(float f10) {
        return this.f911m.O(f10);
    }

    @Override // a1.b
    public final long Y(long j3) {
        return this.f911m.Y(j3);
    }

    @Override // a1.b
    public final float b0(long j3) {
        return this.f911m.b0(j3);
    }

    @Override // a1.b
    public final float getDensity() {
        return this.f911m.getDensity();
    }

    @Override // C0.InterfaceC0108o
    public final a1.k getLayoutDirection() {
        return this.l;
    }

    @Override // a1.b
    public final long j0(float f10) {
        return this.f911m.j0(f10);
    }

    @Override // a1.b
    public final float p() {
        return this.f911m.p();
    }

    @Override // a1.b
    public final float p0(int i7) {
        return this.f911m.p0(i7);
    }

    @Override // a1.b
    public final float r0(float f10) {
        return this.f911m.r0(f10);
    }

    @Override // C0.InterfaceC0108o
    public final boolean v() {
        return this.f911m.v();
    }

    @Override // a1.b
    public final long w(float f10) {
        return this.f911m.w(f10);
    }

    @Override // a1.b
    public final long x(long j3) {
        return this.f911m.x(j3);
    }

    @Override // a1.b
    public final float y(float f10) {
        return this.f911m.y(f10);
    }
}
