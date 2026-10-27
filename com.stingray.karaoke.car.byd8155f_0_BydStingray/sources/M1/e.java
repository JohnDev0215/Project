package m1;

import g1.q;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e extends l1.g {

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public int f18903A0;
    public int B0;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public int f18904C0;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public int f18905D0;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public int f18906E0;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public int f18907F0;
    public int G0;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public int f18908H0;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public float f18909I0;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public float f18910J0;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public float f18911K0;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public float f18912L0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public o1.g f18913n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public HashMap f18914o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public HashMap f18915p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public HashMap f18916q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f18917r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f18918s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f18919t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f18920u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f18921v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f18922w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f18923x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f18924y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f18925z0;

    public e(q qVar, int i7) {
        super(qVar, i7);
        this.f18917r0 = 0;
        this.f18918s0 = -1;
        this.f18919t0 = -1;
        this.f18920u0 = -1;
        this.f18921v0 = -1;
        this.f18922w0 = -1;
        this.f18923x0 = -1;
        this.f18924y0 = 2;
        this.f18925z0 = 2;
        this.f18903A0 = 0;
        this.B0 = 0;
        this.f18904C0 = 0;
        this.f18905D0 = 0;
        this.f18906E0 = 0;
        this.f18907F0 = 0;
        this.G0 = -1;
        this.f18908H0 = 0;
        this.f18909I0 = 0.5f;
        this.f18910J0 = 0.5f;
        this.f18911K0 = 0.5f;
        this.f18912L0 = 0.5f;
        if (i7 == 8) {
            this.f18908H0 = 1;
        }
    }

    @Override // l1.g, l1.C1731b, l1.h
    public final void a() {
        t();
        c(this.f18913n0);
        o1.g gVar = this.f18913n0;
        gVar.f20372U0 = this.f18908H0;
        gVar.f20370S0 = this.f18917r0;
        int i7 = this.G0;
        if (i7 != -1) {
            gVar.f20371T0 = i7;
        }
        int i10 = this.f18904C0;
        if (i10 != 0) {
            gVar.f20391v0 = i10;
        }
        int i11 = this.f18906E0;
        if (i11 != 0) {
            gVar.f20389t0 = i11;
        }
        int i12 = this.f18905D0;
        if (i12 != 0) {
            gVar.f20392w0 = i12;
        }
        int i13 = this.f18907F0;
        if (i13 != 0) {
            gVar.f20390u0 = i13;
        }
        int i14 = this.B0;
        if (i14 != 0) {
            gVar.f20366O0 = i14;
        }
        int i15 = this.f18903A0;
        if (i15 != 0) {
            gVar.f20367P0 = i15;
        }
        float f10 = this.f18396h;
        if (f10 != 0.5f) {
            gVar.f20360I0 = f10;
        }
        float f11 = this.f18911K0;
        if (f11 != 0.5f) {
            gVar.f20362K0 = f11;
        }
        float f12 = this.f18912L0;
        if (f12 != 0.5f) {
            gVar.f20364M0 = f12;
        }
        float f13 = this.f18398i;
        if (f13 != 0.5f) {
            gVar.f20361J0 = f13;
        }
        float f14 = this.f18909I0;
        if (f14 != 0.5f) {
            gVar.f20363L0 = f14;
        }
        float f15 = this.f18910J0;
        if (f15 != 0.5f) {
            gVar.f20365N0 = f15;
        }
        int i16 = this.f18925z0;
        if (i16 != 2) {
            gVar.f20368Q0 = i16;
        }
        int i17 = this.f18924y0;
        if (i17 != 2) {
            gVar.f20369R0 = i17;
        }
        int i18 = this.f18918s0;
        if (i18 != -1) {
            gVar.f20356D0 = i18;
        }
        int i19 = this.f18919t0;
        if (i19 != -1) {
            gVar.f20358F0 = i19;
        }
        int i20 = this.f18920u0;
        if (i20 != -1) {
            gVar.f20359H0 = i20;
        }
        int i21 = this.f18921v0;
        if (i21 != -1) {
            gVar.f20355C0 = i21;
        }
        int i22 = this.f18922w0;
        if (i22 != -1) {
            gVar.f20357E0 = i22;
        }
        int i23 = this.f18923x0;
        if (i23 != -1) {
            gVar.G0 = i23;
        }
        s();
    }

    @Override // l1.g
    public final o1.i t() {
        if (this.f18913n0 == null) {
            o1.g gVar = new o1.g();
            gVar.f20355C0 = -1;
            gVar.f20356D0 = -1;
            gVar.f20357E0 = -1;
            gVar.f20358F0 = -1;
            gVar.G0 = -1;
            gVar.f20359H0 = -1;
            gVar.f20360I0 = 0.5f;
            gVar.f20361J0 = 0.5f;
            gVar.f20362K0 = 0.5f;
            gVar.f20363L0 = 0.5f;
            gVar.f20364M0 = 0.5f;
            gVar.f20365N0 = 0.5f;
            gVar.f20366O0 = 0;
            gVar.f20367P0 = 0;
            gVar.f20368Q0 = 2;
            gVar.f20369R0 = 2;
            gVar.f20370S0 = 0;
            gVar.f20371T0 = -1;
            gVar.f20372U0 = 0;
            gVar.f20373V0 = new ArrayList();
            gVar.f20374W0 = null;
            gVar.f20375X0 = null;
            gVar.f20376Y0 = null;
            gVar.f20378a1 = 0;
            this.f18913n0 = gVar;
        }
        return this.f18913n0;
    }
}
