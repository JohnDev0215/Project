package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class fx1 extends pb {
    public ce2[] f;
    public ce2[] g;
    public int h;
    public kk0 i;

    @Override // p.pb
    public final ce2 d(boolean[] zArr) {
        int i = -1;
        for (int i2 = 0; i2 < this.h; i2++) {
            ce2[] ce2VarArr = this.f;
            ce2 ce2Var = ce2VarArr[i2];
            if (!zArr[ce2Var.e]) {
                kk0 kk0Var = this.i;
                kk0Var.e = ce2Var;
                int i3 = 8;
                if (i == -1) {
                    while (i3 >= 0) {
                        float f = ((ce2) kk0Var.e).f111p[i3];
                        if (f <= 0.0f) {
                            if (f < 0.0f) {
                                i = i2;
                                break;
                            }
                            i3--;
                        }
                    }
                } else {
                    ce2 ce2Var2 = ce2VarArr[i];
                    while (true) {
                        if (i3 >= 0) {
                            float f2 = ce2Var2.f111p[i3];
                            float f3 = ((ce2) kk0Var.e).f111p[i3];
                            if (f3 == f2) {
                                i3--;
                            } else if (f3 < f2) {
                            }
                        }
                    }
                }
            }
        }
        if (i == -1) {
            return null;
        }
        return this.f[i];
    }

    @Override // p.pb
    public final boolean e() {
        return this.h == 0;
    }

    @Override // p.pb
    public final void i(ra1 ra1Var, pb pbVar, boolean z) {
        ce2 ce2Var = pbVar.a;
        if (ce2Var == null) {
            return;
        }
        ob obVar = pbVar.d;
        int iG = obVar.g();
        for (int i = 0; i < iG; i++) {
            ce2 ce2VarH = obVar.h(i);
            float fA = obVar.a(i);
            kk0 kk0Var = this.i;
            kk0Var.e = ce2VarH;
            boolean z2 = ce2VarH.c;
            float[] fArr = ce2Var.f111p;
            if (z2) {
                boolean z3 = true;
                for (int i2 = 0; i2 < 9; i2++) {
                    float[] fArr2 = ((ce2) kk0Var.e).f111p;
                    float f = (fArr[i2] * fA) + fArr2[i2];
                    fArr2[i2] = f;
                    if (Math.abs(f) < 1.0E-4f) {
                        ((ce2) kk0Var.e).f111p[i2] = 0.0f;
                    } else {
                        z3 = false;
                    }
                }
                if (z3) {
                    ((fx1) kk0Var.l).k((ce2) kk0Var.e);
                }
            } else {
                for (int i3 = 0; i3 < 9; i3++) {
                    float f2 = fArr[i3];
                    if (f2 != 0.0f) {
                        float f3 = f2 * fA;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        ((ce2) kk0Var.e).f111p[i3] = f3;
                    } else {
                        ((ce2) kk0Var.e).f111p[i3] = 0.0f;
                    }
                }
                j(ce2VarH);
            }
            this.b = (pbVar.b * fA) + this.b;
        }
        k(ce2Var);
    }

    public final void j(ce2 ce2Var) {
        int i;
        int i2 = this.h + 1;
        ce2[] ce2VarArr = this.f;
        if (i2 > ce2VarArr.length) {
            ce2[] ce2VarArr2 = (ce2[]) Arrays.copyOf(ce2VarArr, ce2VarArr.length * 2);
            this.f = ce2VarArr2;
            this.g = (ce2[]) Arrays.copyOf(ce2VarArr2, ce2VarArr2.length * 2);
        }
        ce2[] ce2VarArr3 = this.f;
        int i3 = this.h;
        ce2VarArr3[i3] = ce2Var;
        int i4 = i3 + 1;
        this.h = i4;
        if (i4 > 1 && ce2VarArr3[i3].e > ce2Var.e) {
            int i5 = 0;
            while (true) {
                i = this.h;
                if (i5 >= i) {
                    break;
                }
                this.g[i5] = this.f[i5];
                i5++;
            }
            Arrays.sort(this.g, 0, i, new ex1(0, this));
            for (int i6 = 0; i6 < this.h; i6++) {
                this.f[i6] = this.g[i6];
            }
        }
        ce2Var.c = true;
        ce2Var.a(this);
    }

    public final void k(ce2 ce2Var) {
        int i = 0;
        while (i < this.h) {
            if (this.f[i] == ce2Var) {
                while (true) {
                    int i2 = this.h;
                    if (i >= i2 - 1) {
                        this.h = i2 - 1;
                        ce2Var.c = false;
                        return;
                    } else {
                        ce2[] ce2VarArr = this.f;
                        int i3 = i + 1;
                        ce2VarArr[i] = ce2VarArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // p.pb
    public final String toString() {
        String str = " goal -> (" + this.b + ") : ";
        for (int i = 0; i < this.h; i++) {
            ce2 ce2Var = this.f[i];
            kk0 kk0Var = this.i;
            kk0Var.e = ce2Var;
            str = str + kk0Var + " ";
        }
        return str;
    }
}
