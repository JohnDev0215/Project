package n1;

import C1.b;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import o1.d;
import o1.e;
import o1.k;

/* JADX INFO: renamed from: n1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1898a extends k {

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public e f19458C0;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public d[] f19459D0;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public boolean f19460E0;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public int f19461F0;
    public int G0;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public int f19462H0;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public int f19463I0;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public float f19464J0;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public float f19465K0;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public String f19466L0;

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public String f19467M0;

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public String f19468N0;

    /* JADX INFO: renamed from: O0, reason: collision with root package name */
    public String f19469O0;

    /* JADX INFO: renamed from: P0, reason: collision with root package name */
    public int f19470P0;

    /* JADX INFO: renamed from: Q0, reason: collision with root package name */
    public int f19471Q0;

    /* JADX INFO: renamed from: R0, reason: collision with root package name */
    public boolean[][] f19472R0;

    /* JADX INFO: renamed from: S0, reason: collision with root package name */
    public HashSet f19473S0;

    /* JADX INFO: renamed from: T0, reason: collision with root package name */
    public int[][] f19474T0;

    /* JADX INFO: renamed from: U0, reason: collision with root package name */
    public int f19475U0;

    /* JADX INFO: renamed from: V0, reason: collision with root package name */
    public int[][] f19476V0;

    /* JADX INFO: renamed from: W0, reason: collision with root package name */
    public int f19477W0;

    public static void Z(d dVar) {
        dVar.f20302l0[1] = -1.0f;
        dVar.f20266K.j();
        dVar.f20268M.j();
        dVar.f20269N.j();
    }

    public static float[] i0(int i7, String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        String[] strArrSplit = str.split(",");
        float[] fArr = new float[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            if (i10 < strArrSplit.length) {
                try {
                    fArr[i10] = Float.parseFloat(strArrSplit[i10]);
                } catch (Exception e10) {
                    System.err.println("Error parsing `" + strArrSplit[i10] + "`: " + e10.getMessage());
                    fArr[i10] = 1.0f;
                }
            } else {
                fArr[i10] = 1.0f;
            }
        }
        return fArr;
    }

    @Override // o1.k
    public final void X(int i7, int i10, int i11, int i12) {
        int[][] iArrH0;
        this.f19458C0 = this.f20276U;
        if (this.f19461F0 >= 1 && this.f19462H0 >= 1) {
            this.f19471Q0 = 0;
            String str = this.f19469O0;
            if (str != null && !str.trim().isEmpty() && (iArrH0 = h0(this.f19469O0, false)) != null) {
                d0(iArrH0);
            }
            String str2 = this.f19468N0;
            if (str2 != null && !str2.trim().isEmpty()) {
                this.f19476V0 = h0(this.f19468N0, true);
            }
            int iMax = Math.max(this.f19461F0, this.f19462H0);
            d[] dVarArr = this.f19459D0;
            if (dVarArr == null) {
                this.f19459D0 = new d[iMax];
                int i13 = 0;
                while (true) {
                    d[] dVarArr2 = this.f19459D0;
                    if (i13 >= dVarArr2.length) {
                        break;
                    }
                    d dVar = new d();
                    int[] iArr = dVar.f20311q0;
                    iArr[0] = 3;
                    iArr[1] = 3;
                    dVar.f20300k = String.valueOf(dVar.hashCode());
                    dVarArr2[i13] = dVar;
                    i13++;
                }
            } else if (iMax != dVarArr.length) {
                d[] dVarArr3 = new d[iMax];
                for (int i14 = 0; i14 < iMax; i14++) {
                    d[] dVarArr4 = this.f19459D0;
                    if (i14 < dVarArr4.length) {
                        dVarArr3[i14] = dVarArr4[i14];
                    } else {
                        d dVar2 = new d();
                        int[] iArr2 = dVar2.f20311q0;
                        iArr2[0] = 3;
                        iArr2[1] = 3;
                        dVar2.f20300k = String.valueOf(dVar2.hashCode());
                        dVarArr3[i14] = dVar2;
                    }
                }
                while (true) {
                    d[] dVarArr5 = this.f19459D0;
                    if (iMax >= dVarArr5.length) {
                        break;
                    }
                    d dVar3 = dVarArr5[iMax];
                    this.f19458C0.f20332r0.remove(dVar3);
                    dVar3.D();
                    iMax++;
                }
                this.f19459D0 = dVarArr3;
            }
            int[][] iArr3 = this.f19476V0;
            if (iArr3 != null) {
                e0(iArr3);
            }
        }
        e eVar = this.f19458C0;
        d[] dVarArr6 = this.f19459D0;
        eVar.getClass();
        for (d dVar4 : dVarArr6) {
            eVar.V(dVar4);
        }
    }

    public final void a0(int i7, int i10, int i11, int i12, d dVar) {
        dVar.f20265J.a(this.f19459D0[i10].f20265J, 0);
        dVar.f20266K.a(this.f19459D0[i7].f20266K, 0);
        dVar.f20267L.a(this.f19459D0[(i10 + i12) - 1].f20267L, 0);
        dVar.f20268M.a(this.f19459D0[(i7 + i11) - 1].f20268M, 0);
    }

    public final int b0(int i7) {
        return this.f19470P0 == 1 ? i7 / this.f19461F0 : i7 % this.f19462H0;
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x01c0  */
    @Override // o1.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(h1.c r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 463
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.C1898a.c(h1.c, boolean):void");
    }

    public final int c0(int i7) {
        return this.f19470P0 == 1 ? i7 % this.f19461F0 : i7 / this.f19462H0;
    }

    public final void d0(int[][] iArr) {
        for (int[] iArr2 : iArr) {
            if (!g0(c0(iArr2[0]), b0(iArr2[0]), iArr2[1], iArr2[2])) {
                return;
            }
        }
    }

    public final void e0(int[][] iArr) {
        if ((this.f19475U0 & 2) > 0) {
            return;
        }
        for (int i7 = 0; i7 < iArr.length; i7++) {
            int iC0 = c0(iArr[i7][0]);
            int iB0 = b0(iArr[i7][0]);
            int[] iArr2 = iArr[i7];
            if (!g0(iC0, iB0, iArr2[1], iArr2[2])) {
                return;
            }
            d dVar = this.f20385r0[i7];
            int[] iArr3 = iArr[i7];
            a0(iC0, iB0, iArr3[1], iArr3[2], dVar);
            this.f19473S0.add(this.f20385r0[i7].f20300k);
        }
    }

    public final void f0() {
        boolean[][] zArr = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, this.f19461F0, this.f19462H0);
        this.f19472R0 = zArr;
        for (boolean[] zArr2 : zArr) {
            Arrays.fill(zArr2, true);
        }
        int i7 = this.f20386s0;
        if (i7 > 0) {
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i7, 4);
            this.f19474T0 = iArr;
            for (int[] iArr2 : iArr) {
                Arrays.fill(iArr2, -1);
            }
        }
    }

    public final boolean g0(int i7, int i10, int i11, int i12) {
        for (int i13 = i7; i13 < i7 + i11; i13++) {
            for (int i14 = i10; i14 < i10 + i12; i14++) {
                boolean[][] zArr = this.f19472R0;
                if (i13 < zArr.length && i14 < zArr[0].length) {
                    boolean[] zArr2 = zArr[i13];
                    if (zArr2[i14]) {
                        zArr2[i14] = false;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public final int[][] h0(String str, boolean z6) {
        int i7;
        int i10;
        try {
            String[] strArrSplit = str.split(",");
            Arrays.sort(strArrSplit, new b(12));
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, strArrSplit.length, 3);
            if (this.f19461F0 == 1 || this.f19462H0 == 1) {
                int i11 = 0;
                int i12 = 0;
                for (int i13 = 0; i13 < strArrSplit.length; i13++) {
                    String[] strArrSplit2 = strArrSplit[i13].trim().split(":");
                    iArr[i13][0] = Integer.parseInt(strArrSplit2[0]);
                    int[] iArr2 = iArr[i13];
                    iArr2[1] = 1;
                    iArr2[2] = 1;
                    if (this.f19462H0 == 1) {
                        iArr2[1] = Integer.parseInt(strArrSplit2[1]);
                        i11 += iArr[i13][1];
                        if (z6) {
                            i11--;
                        }
                    }
                    if (this.f19461F0 == 1) {
                        iArr[i13][2] = Integer.parseInt(strArrSplit2[1]);
                        i12 += iArr[i13][2];
                        if (z6) {
                            i12--;
                        }
                    }
                }
                if (i11 != 0 && !this.f19460E0 && (i10 = this.f19461F0 + i11) <= 50 && this.G0 != i10) {
                    this.G0 = i10;
                    j0();
                    f0();
                }
                if (i12 != 0 && !this.f19460E0 && (i7 = this.f19462H0 + i12) <= 50 && this.f19463I0 != i7) {
                    this.f19463I0 = i7;
                    j0();
                    f0();
                }
                this.f19460E0 = true;
            } else {
                for (int i14 = 0; i14 < strArrSplit.length; i14++) {
                    String[] strArrSplit3 = strArrSplit[i14].trim().split(":");
                    String[] strArrSplit4 = strArrSplit3[1].split("x");
                    iArr[i14][0] = Integer.parseInt(strArrSplit3[0]);
                    if ((this.f19475U0 & 1) > 0) {
                        iArr[i14][1] = Integer.parseInt(strArrSplit4[1]);
                        iArr[i14][2] = Integer.parseInt(strArrSplit4[0]);
                    } else {
                        iArr[i14][1] = Integer.parseInt(strArrSplit4[0]);
                        iArr[i14][2] = Integer.parseInt(strArrSplit4[1]);
                    }
                }
            }
            return iArr;
        } catch (Exception unused) {
            return null;
        }
    }

    public final void j0() {
        int i7;
        int i10 = this.G0;
        if (i10 != 0 && (i7 = this.f19463I0) != 0) {
            this.f19461F0 = i10;
            this.f19462H0 = i7;
            return;
        }
        int i11 = this.f19463I0;
        if (i11 > 0) {
            this.f19462H0 = i11;
            this.f19461F0 = ((this.f20386s0 + i11) - 1) / i11;
        } else if (i10 > 0) {
            this.f19461F0 = i10;
            this.f19462H0 = ((this.f20386s0 + i10) - 1) / i10;
        } else {
            int iSqrt = (int) (Math.sqrt(this.f20386s0) + 1.5d);
            this.f19461F0 = iSqrt;
            this.f19462H0 = ((this.f20386s0 + iSqrt) - 1) / iSqrt;
        }
    }
}
