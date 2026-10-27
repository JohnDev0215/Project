package m1;

import g1.q;
import java.util.HashSet;
import n1.C1898a;

/* JADX INFO: loaded from: classes.dex */
public final class f extends l1.g {

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public int f18926A0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public C1898a f18927n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public int f18928o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public int f18929p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f18930q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f18931r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f18932s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f18933t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public float f18934u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public float f18935v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public String f18936w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public String f18937x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public String f18938y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public String f18939z0;

    public f(q qVar, int i7) {
        super(qVar, i7);
        this.f18928o0 = 0;
        this.f18929p0 = 0;
        this.f18930q0 = 0;
        if (i7 == 10) {
            this.f18932s0 = 1;
        } else if (i7 == 11) {
            this.f18933t0 = 1;
        }
    }

    @Override // l1.g, l1.C1731b, l1.h
    public final void a() {
        t();
        C1898a c1898a = this.f18927n0;
        int i7 = this.f18931r0;
        c1898a.getClass();
        if ((i7 == 0 || i7 == 1) && c1898a.f19470P0 != i7) {
            c1898a.f19470P0 = i7;
        }
        int i10 = this.f18932s0;
        if (i10 != 0) {
            C1898a c1898a2 = this.f18927n0;
            if (i10 > 50) {
                c1898a2.getClass();
            } else if (c1898a2.G0 != i10) {
                c1898a2.G0 = i10;
                c1898a2.j0();
                c1898a2.f0();
            }
        }
        int i11 = this.f18933t0;
        if (i11 != 0) {
            C1898a c1898a3 = this.f18927n0;
            if (i11 > 50) {
                c1898a3.getClass();
            } else if (c1898a3.f19463I0 != i11) {
                c1898a3.f19463I0 = i11;
                c1898a3.j0();
                c1898a3.f0();
            }
        }
        float f10 = this.f18934u0;
        if (f10 != 0.0f) {
            C1898a c1898a4 = this.f18927n0;
            if (f10 < 0.0f) {
                c1898a4.getClass();
            } else if (c1898a4.f19464J0 != f10) {
                c1898a4.f19464J0 = f10;
            }
        }
        float f11 = this.f18935v0;
        if (f11 != 0.0f) {
            C1898a c1898a5 = this.f18927n0;
            if (f11 < 0.0f) {
                c1898a5.getClass();
            } else if (c1898a5.f19465K0 != f11) {
                c1898a5.f19465K0 = f11;
            }
        }
        String str = this.f18936w0;
        if (str != null && !str.isEmpty()) {
            C1898a c1898a6 = this.f18927n0;
            String str2 = this.f18936w0;
            String str3 = c1898a6.f19466L0;
            if (str3 == null || !str3.equals(str2)) {
                c1898a6.f19466L0 = str2;
            }
        }
        String str4 = this.f18937x0;
        if (str4 != null && !str4.isEmpty()) {
            C1898a c1898a7 = this.f18927n0;
            String str5 = this.f18937x0;
            String str6 = c1898a7.f19467M0;
            if (str6 == null || !str6.equals(str5)) {
                c1898a7.f19467M0 = str5;
            }
        }
        String str7 = this.f18938y0;
        if (str7 != null && !str7.isEmpty()) {
            C1898a c1898a8 = this.f18927n0;
            String str8 = this.f18938y0;
            String str9 = c1898a8.f19468N0;
            if (str9 == null || !str9.equals(str8.toString())) {
                c1898a8.f19460E0 = false;
                c1898a8.f19468N0 = str8.toString();
            }
        }
        String str10 = this.f18939z0;
        if (str10 != null && !str10.isEmpty()) {
            C1898a c1898a9 = this.f18927n0;
            String str11 = this.f18939z0;
            String str12 = c1898a9.f19469O0;
            if (str12 == null || !str12.equals(str11)) {
                c1898a9.f19460E0 = false;
                c1898a9.f19469O0 = str11;
            }
        }
        C1898a c1898a10 = this.f18927n0;
        c1898a10.f19475U0 = this.f18926A0;
        int i12 = this.f18928o0;
        c1898a10.f20391v0 = i12;
        c1898a10.f20392w0 = i12;
        c1898a10.f20389t0 = this.f18929p0;
        c1898a10.f20390u0 = this.f18930q0;
        s();
    }

    @Override // l1.g
    public final o1.i t() {
        int[][] iArrH0;
        int[][] iArrH02;
        boolean[][] zArr;
        if (this.f18927n0 == null) {
            C1898a c1898a = new C1898a();
            c1898a.f19460E0 = false;
            c1898a.f19471Q0 = 0;
            c1898a.f19473S0 = new HashSet();
            c1898a.f19477W0 = 0;
            c1898a.j0();
            int[][] iArr = c1898a.f19474T0;
            boolean z6 = iArr != null && iArr.length == c1898a.f20386s0 && (zArr = c1898a.f19472R0) != null && zArr.length == c1898a.f19461F0 && zArr[0].length == c1898a.f19462H0;
            if (!z6) {
                c1898a.f0();
            }
            if (z6) {
                for (int i7 = 0; i7 < c1898a.f19472R0.length; i7++) {
                    int i10 = 0;
                    while (true) {
                        boolean[][] zArr2 = c1898a.f19472R0;
                        if (i10 < zArr2[0].length) {
                            zArr2[i7][i10] = true;
                            i10++;
                        }
                    }
                }
                for (int i11 = 0; i11 < c1898a.f19474T0.length; i11++) {
                    int i12 = 0;
                    while (true) {
                        int[][] iArr2 = c1898a.f19474T0;
                        if (i12 < iArr2[0].length) {
                            iArr2[i11][i12] = -1;
                            i12++;
                        }
                    }
                }
            }
            c1898a.f19471Q0 = 0;
            String str = c1898a.f19469O0;
            if (str != null && !str.trim().isEmpty() && (iArrH02 = c1898a.h0(c1898a.f19469O0, false)) != null) {
                c1898a.d0(iArrH02);
            }
            String str2 = c1898a.f19468N0;
            if (str2 != null && !str2.trim().isEmpty() && (iArrH0 = c1898a.h0(c1898a.f19468N0, true)) != null) {
                c1898a.e0(iArrH0);
            }
            this.f18927n0 = c1898a;
        }
        return this.f18927n0;
    }
}
