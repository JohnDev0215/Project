package o0;

import java.util.Arrays;
import k3.X0;
import n0.AbstractC1861K;

/* JADX INFO: renamed from: o0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2021q extends AbstractC2007c {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final X0 f20214r = new X0(26);
    public final C2023s d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f20215e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f20216f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C2022r f20217g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f20218h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f20219i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f20220j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final InterfaceC2013i f20221k;
    public final C2020p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2017m f20222m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC2013i f20223n;
    public final C2020p o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2017m f20224p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f20225q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0259 A[EDGE_INSN: B:53:0x0259->B:71:0x02b1 BREAK  A[LOOP:0: B:63:0x0278->B:69:0x02a9], EDGE_INSN: B:81:0x0259->B:53:0x0259 BREAK  A[LOOP:1: B:48:0x023d->B:54:0x025b]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x027e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C2021q(java.lang.String r33, float[] r34, o0.C2023s r35, float[] r36, o0.InterfaceC2013i r37, o0.InterfaceC2013i r38, float r39, float r40, o0.C2022r r41, int r42) {
        /*
            Method dump skipped, instruction units count: 746
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o0.C2021q.<init>(java.lang.String, float[], o0.s, float[], o0.i, o0.i, float, float, o0.r, int):void");
    }

    @Override // o0.AbstractC2007c
    public final float a(int i7) {
        return this.f20216f;
    }

    @Override // o0.AbstractC2007c
    public final float b(int i7) {
        return this.f20215e;
    }

    @Override // o0.AbstractC2007c
    public final boolean c() {
        return this.f20225q;
    }

    @Override // o0.AbstractC2007c
    public final long d(float f10, float f11, float f12) {
        double d = f10;
        C2017m c2017m = this.f20224p;
        float fC = (float) c2017m.c(d);
        float fC2 = (float) c2017m.c(f11);
        float fC3 = (float) c2017m.c(f12);
        float[] fArr = this.f20219i;
        return (((long) Float.floatToRawIntBits((fArr[6] * fC3) + ((fArr[3] * fC2) + (fArr[0] * fC)))) << 32) | (4294967295L & ((long) Float.floatToRawIntBits((fArr[7] * fC3) + (fArr[4] * fC2) + (fArr[1] * fC))));
    }

    @Override // o0.AbstractC2007c
    public final float e(float f10, float f11, float f12) {
        double d = f10;
        C2017m c2017m = this.f20224p;
        float fC = (float) c2017m.c(d);
        float fC2 = (float) c2017m.c(f11);
        float fC3 = (float) c2017m.c(f12);
        float[] fArr = this.f20219i;
        return (fArr[8] * fC3) + (fArr[5] * fC2) + (fArr[2] * fC);
    }

    @Override // o0.AbstractC2007c
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2021q.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        C2021q c2021q = (C2021q) obj;
        if (Float.compare(c2021q.f20215e, this.f20215e) != 0 || Float.compare(c2021q.f20216f, this.f20216f) != 0 || !A7.m.a(this.d, c2021q.d) || !Arrays.equals(this.f20218h, c2021q.f20218h)) {
            return false;
        }
        C2022r c2022r = c2021q.f20217g;
        C2022r c2022r2 = this.f20217g;
        if (c2022r2 != null) {
            return A7.m.a(c2022r2, c2022r);
        }
        if (c2022r == null) {
            return true;
        }
        if (A7.m.a(this.f20221k, c2021q.f20221k)) {
            return A7.m.a(this.f20223n, c2021q.f20223n);
        }
        return false;
    }

    @Override // o0.AbstractC2007c
    public final long f(float f10, float f11, float f12, float f13, AbstractC2007c abstractC2007c) {
        float[] fArr = this.f20220j;
        float f14 = (fArr[6] * f12) + (fArr[3] * f11) + (fArr[0] * f10);
        float f15 = (fArr[7] * f12) + (fArr[4] * f11) + (fArr[1] * f10);
        float f16 = (fArr[8] * f12) + (fArr[5] * f11) + (fArr[2] * f10);
        C2017m c2017m = this.f20222m;
        return AbstractC1861K.b((float) c2017m.c(f14), (float) c2017m.c(f15), (float) c2017m.c(f16), f13, abstractC2007c);
    }

    @Override // o0.AbstractC2007c
    public final int hashCode() {
        int iHashCode = (Arrays.hashCode(this.f20218h) + ((this.d.hashCode() + (super.hashCode() * 31)) * 31)) * 31;
        float f10 = this.f20215e;
        int iFloatToIntBits = (iHashCode + (f10 == 0.0f ? 0 : Float.floatToIntBits(f10))) * 31;
        float f11 = this.f20216f;
        int iFloatToIntBits2 = (iFloatToIntBits + (f11 == 0.0f ? 0 : Float.floatToIntBits(f11))) * 31;
        C2022r c2022r = this.f20217g;
        int iHashCode2 = iFloatToIntBits2 + (c2022r != null ? c2022r.hashCode() : 0);
        if (c2022r != null) {
            return iHashCode2;
        }
        return this.f20223n.hashCode() + ((this.f20221k.hashCode() + (iHashCode2 * 31)) * 31);
    }

    public C2021q(String str, float[] fArr, C2023s c2023s, final C2022r c2022r, int i7) {
        InterfaceC2013i interfaceC2013i;
        InterfaceC2013i interfaceC2013i2;
        double d = c2022r.f20229f;
        double d10 = c2022r.f20230g;
        if (d == 0.0d && d10 == 0.0d) {
            final int i10 = 0;
            interfaceC2013i = new InterfaceC2013i() { // from class: o0.o
                @Override // o0.InterfaceC2013i
                public final double c(double d11) {
                    switch (i10) {
                        case 0:
                            C2022r c2022r2 = c2022r;
                            double d12 = c2022r2.f20228e;
                            double d13 = c2022r2.d;
                            return d11 >= d12 * d13 ? (Math.pow(d11, 1.0d / c2022r2.f20226a) - c2022r2.c) / c2022r2.f20227b : d11 / d13;
                        case 1:
                            C2022r c2022r3 = c2022r;
                            double d14 = c2022r3.f20227b;
                            double d15 = c2022r3.f20228e;
                            double d16 = c2022r3.d;
                            return d11 >= d15 * d16 ? (Math.pow(d11 - c2022r3.f20229f, 1.0d / c2022r3.f20226a) - c2022r3.c) / d14 : (d11 - c2022r3.f20230g) / d16;
                        case 2:
                            C2022r c2022r4 = c2022r;
                            return d11 >= c2022r4.f20228e ? Math.pow((c2022r4.f20227b * d11) + c2022r4.c, c2022r4.f20226a) : c2022r4.d * d11;
                        default:
                            C2022r c2022r5 = c2022r;
                            return d11 >= c2022r5.f20228e ? Math.pow((c2022r5.f20227b * d11) + c2022r5.c, c2022r5.f20226a) + c2022r5.f20229f : (c2022r5.d * d11) + c2022r5.f20230g;
                    }
                }
            };
        } else {
            final int i11 = 1;
            interfaceC2013i = new InterfaceC2013i() { // from class: o0.o
                @Override // o0.InterfaceC2013i
                public final double c(double d11) {
                    switch (i11) {
                        case 0:
                            C2022r c2022r2 = c2022r;
                            double d12 = c2022r2.f20228e;
                            double d13 = c2022r2.d;
                            return d11 >= d12 * d13 ? (Math.pow(d11, 1.0d / c2022r2.f20226a) - c2022r2.c) / c2022r2.f20227b : d11 / d13;
                        case 1:
                            C2022r c2022r3 = c2022r;
                            double d14 = c2022r3.f20227b;
                            double d15 = c2022r3.f20228e;
                            double d16 = c2022r3.d;
                            return d11 >= d15 * d16 ? (Math.pow(d11 - c2022r3.f20229f, 1.0d / c2022r3.f20226a) - c2022r3.c) / d14 : (d11 - c2022r3.f20230g) / d16;
                        case 2:
                            C2022r c2022r4 = c2022r;
                            return d11 >= c2022r4.f20228e ? Math.pow((c2022r4.f20227b * d11) + c2022r4.c, c2022r4.f20226a) : c2022r4.d * d11;
                        default:
                            C2022r c2022r5 = c2022r;
                            return d11 >= c2022r5.f20228e ? Math.pow((c2022r5.f20227b * d11) + c2022r5.c, c2022r5.f20226a) + c2022r5.f20229f : (c2022r5.d * d11) + c2022r5.f20230g;
                    }
                }
            };
        }
        InterfaceC2013i interfaceC2013i3 = interfaceC2013i;
        if (d == 0.0d && d10 == 0.0d) {
            final int i12 = 2;
            interfaceC2013i2 = new InterfaceC2013i() { // from class: o0.o
                @Override // o0.InterfaceC2013i
                public final double c(double d11) {
                    switch (i12) {
                        case 0:
                            C2022r c2022r2 = c2022r;
                            double d12 = c2022r2.f20228e;
                            double d13 = c2022r2.d;
                            return d11 >= d12 * d13 ? (Math.pow(d11, 1.0d / c2022r2.f20226a) - c2022r2.c) / c2022r2.f20227b : d11 / d13;
                        case 1:
                            C2022r c2022r3 = c2022r;
                            double d14 = c2022r3.f20227b;
                            double d15 = c2022r3.f20228e;
                            double d16 = c2022r3.d;
                            return d11 >= d15 * d16 ? (Math.pow(d11 - c2022r3.f20229f, 1.0d / c2022r3.f20226a) - c2022r3.c) / d14 : (d11 - c2022r3.f20230g) / d16;
                        case 2:
                            C2022r c2022r4 = c2022r;
                            return d11 >= c2022r4.f20228e ? Math.pow((c2022r4.f20227b * d11) + c2022r4.c, c2022r4.f20226a) : c2022r4.d * d11;
                        default:
                            C2022r c2022r5 = c2022r;
                            return d11 >= c2022r5.f20228e ? Math.pow((c2022r5.f20227b * d11) + c2022r5.c, c2022r5.f20226a) + c2022r5.f20229f : (c2022r5.d * d11) + c2022r5.f20230g;
                    }
                }
            };
        } else {
            final int i13 = 3;
            interfaceC2013i2 = new InterfaceC2013i() { // from class: o0.o
                @Override // o0.InterfaceC2013i
                public final double c(double d11) {
                    switch (i13) {
                        case 0:
                            C2022r c2022r2 = c2022r;
                            double d12 = c2022r2.f20228e;
                            double d13 = c2022r2.d;
                            return d11 >= d12 * d13 ? (Math.pow(d11, 1.0d / c2022r2.f20226a) - c2022r2.c) / c2022r2.f20227b : d11 / d13;
                        case 1:
                            C2022r c2022r3 = c2022r;
                            double d14 = c2022r3.f20227b;
                            double d15 = c2022r3.f20228e;
                            double d16 = c2022r3.d;
                            return d11 >= d15 * d16 ? (Math.pow(d11 - c2022r3.f20229f, 1.0d / c2022r3.f20226a) - c2022r3.c) / d14 : (d11 - c2022r3.f20230g) / d16;
                        case 2:
                            C2022r c2022r4 = c2022r;
                            return d11 >= c2022r4.f20228e ? Math.pow((c2022r4.f20227b * d11) + c2022r4.c, c2022r4.f20226a) : c2022r4.d * d11;
                        default:
                            C2022r c2022r5 = c2022r;
                            return d11 >= c2022r5.f20228e ? Math.pow((c2022r5.f20227b * d11) + c2022r5.c, c2022r5.f20226a) + c2022r5.f20229f : (c2022r5.d * d11) + c2022r5.f20230g;
                    }
                }
            };
        }
        this(str, fArr, c2023s, null, interfaceC2013i3, interfaceC2013i2, 0.0f, 1.0f, c2022r, i7);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C2021q(String str, float[] fArr, C2023s c2023s, final double d, float f10, float f11, int i7) {
        InterfaceC2013i interfaceC2013i;
        InterfaceC2013i interfaceC2013i2 = f20214r;
        if (d == 1.0d) {
            interfaceC2013i = interfaceC2013i2;
        } else {
            final int i10 = 0;
            interfaceC2013i = new InterfaceC2013i() { // from class: o0.n
                @Override // o0.InterfaceC2013i
                public final double c(double d10) {
                    switch (i10) {
                        case 0:
                            if (d10 < 0.0d) {
                                d10 = 0.0d;
                            }
                            return Math.pow(d10, 1.0d / d);
                        default:
                            if (d10 < 0.0d) {
                                d10 = 0.0d;
                            }
                            return Math.pow(d10, d);
                    }
                }
            };
        }
        if (d != 1.0d) {
            final int i11 = 1;
            interfaceC2013i2 = new InterfaceC2013i() { // from class: o0.n
                @Override // o0.InterfaceC2013i
                public final double c(double d10) {
                    switch (i11) {
                        case 0:
                            if (d10 < 0.0d) {
                                d10 = 0.0d;
                            }
                            return Math.pow(d10, 1.0d / d);
                        default:
                            if (d10 < 0.0d) {
                                d10 = 0.0d;
                            }
                            return Math.pow(d10, d);
                    }
                }
            };
        }
        this(str, fArr, c2023s, null, interfaceC2013i, interfaceC2013i2, f10, f11, new C2022r(d, 1.0d, 0.0d, 0.0d, 0.0d), i7);
    }
}
