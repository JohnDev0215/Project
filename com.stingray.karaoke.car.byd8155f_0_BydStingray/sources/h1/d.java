package h1;

import E5.y;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d extends C1342b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e[] f15181f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e[] f15182g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15183h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public io.sentry.internal.debugmeta.c f15184i;

    @Override // h1.C1342b
    public final e d(boolean[] zArr) {
        int i7 = -1;
        for (int i10 = 0; i10 < this.f15183h; i10++) {
            e[] eVarArr = this.f15181f;
            e eVar = eVarArr[i10];
            if (!zArr[eVar.f15185m]) {
                io.sentry.internal.debugmeta.c cVar = this.f15184i;
                cVar.f16569m = eVar;
                int i11 = 8;
                if (i7 == -1) {
                    while (i11 >= 0) {
                        float f10 = ((e) cVar.f16569m).f15190s[i11];
                        if (f10 <= 0.0f) {
                            if (f10 < 0.0f) {
                                i7 = i10;
                                break;
                            }
                            i11--;
                        }
                    }
                } else {
                    e eVar2 = eVarArr[i7];
                    while (true) {
                        if (i11 >= 0) {
                            float f11 = eVar2.f15190s[i11];
                            float f12 = ((e) cVar.f16569m).f15190s[i11];
                            if (f12 == f11) {
                                i11--;
                            } else if (f12 < f11) {
                            }
                        }
                    }
                }
            }
        }
        if (i7 == -1) {
            return null;
        }
        return this.f15181f[i7];
    }

    @Override // h1.C1342b
    public final boolean e() {
        return this.f15183h == 0;
    }

    @Override // h1.C1342b
    public final void i(c cVar, C1342b c1342b, boolean z6) {
        e eVar = c1342b.f15165a;
        if (eVar == null) {
            return;
        }
        C1341a c1341a = c1342b.d;
        int iD = c1341a.d();
        for (int i7 = 0; i7 < iD; i7++) {
            e eVarE = c1341a.e(i7);
            float f10 = c1341a.f(i7);
            io.sentry.internal.debugmeta.c cVar2 = this.f15184i;
            cVar2.f16569m = eVarE;
            boolean z10 = eVarE.l;
            float[] fArr = eVar.f15190s;
            if (z10) {
                boolean z11 = true;
                for (int i10 = 0; i10 < 9; i10++) {
                    float[] fArr2 = ((e) cVar2.f16569m).f15190s;
                    float f11 = (fArr[i10] * f10) + fArr2[i10];
                    fArr2[i10] = f11;
                    if (Math.abs(f11) < 1.0E-4f) {
                        ((e) cVar2.f16569m).f15190s[i10] = 0.0f;
                    } else {
                        z11 = false;
                    }
                }
                if (z11) {
                    ((d) cVar2.f16570n).k((e) cVar2.f16569m);
                }
            } else {
                for (int i11 = 0; i11 < 9; i11++) {
                    float f12 = fArr[i11];
                    if (f12 != 0.0f) {
                        float f13 = f12 * f10;
                        if (Math.abs(f13) < 1.0E-4f) {
                            f13 = 0.0f;
                        }
                        ((e) cVar2.f16569m).f15190s[i11] = f13;
                    } else {
                        ((e) cVar2.f16569m).f15190s[i11] = 0.0f;
                    }
                }
                j(eVarE);
            }
            this.f15166b = (c1342b.f15166b * f10) + this.f15166b;
        }
        k(eVar);
    }

    public final void j(e eVar) {
        int i7;
        int i10 = this.f15183h + 1;
        e[] eVarArr = this.f15181f;
        if (i10 > eVarArr.length) {
            e[] eVarArr2 = (e[]) Arrays.copyOf(eVarArr, eVarArr.length * 2);
            this.f15181f = eVarArr2;
            this.f15182g = (e[]) Arrays.copyOf(eVarArr2, eVarArr2.length * 2);
        }
        e[] eVarArr3 = this.f15181f;
        int i11 = this.f15183h;
        eVarArr3[i11] = eVar;
        int i12 = i11 + 1;
        this.f15183h = i12;
        if (i12 > 1 && eVarArr3[i11].f15185m > eVar.f15185m) {
            int i13 = 0;
            while (true) {
                i7 = this.f15183h;
                if (i13 >= i7) {
                    break;
                }
                this.f15182g[i13] = this.f15181f[i13];
                i13++;
            }
            Arrays.sort(this.f15182g, 0, i7, new y(4));
            for (int i14 = 0; i14 < this.f15183h; i14++) {
                this.f15181f[i14] = this.f15182g[i14];
            }
        }
        eVar.l = true;
        eVar.a(this);
    }

    public final void k(e eVar) {
        int i7 = 0;
        while (i7 < this.f15183h) {
            if (this.f15181f[i7] == eVar) {
                while (true) {
                    int i10 = this.f15183h;
                    if (i7 >= i10 - 1) {
                        this.f15183h = i10 - 1;
                        eVar.l = false;
                        return;
                    } else {
                        e[] eVarArr = this.f15181f;
                        int i11 = i7 + 1;
                        eVarArr[i7] = eVarArr[i11];
                        i7 = i11;
                    }
                }
            } else {
                i7++;
            }
        }
    }

    @Override // h1.C1342b
    public final String toString() {
        String str = " goal -> (" + this.f15166b + ") : ";
        for (int i7 = 0; i7 < this.f15183h; i7++) {
            e eVar = this.f15181f[i7];
            io.sentry.internal.debugmeta.c cVar = this.f15184i;
            cVar.f16569m = eVar;
            str = str + cVar + " ";
        }
        return str;
    }
}
