package b.f.b;

import androidx.constraintlayout.solver.SolverVariable;
import b.f.b.b;
import java.util.Arrays;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f2167a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f2169c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f2170d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2168b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2171e = 8;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SolverVariable f2172f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f2173g = new int[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f2174h = new int[8];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f2175i = new float[8];
    public int j = -1;
    public int k = -1;
    public boolean l = false;

    public a(b bVar, c cVar) {
        this.f2169c = bVar;
        this.f2170d = cVar;
    }

    @Override // b.f.b.b.a
    public float a(int i2) {
        int i3 = this.j;
        for (int i4 = 0; i3 != -1 && i4 < this.f2168b; i4++) {
            if (i4 == i2) {
                return this.f2175i[i3];
            }
            i3 = this.f2174h[i3];
        }
        return 0.0f;
    }

    @Override // b.f.b.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f2176a);
        d(bVar.f2176a, z);
        b.a aVar = bVar.f2180e;
        int iK = aVar.k();
        for (int i2 = 0; i2 < iK; i2++) {
            SolverVariable solverVariableF = aVar.f(i2);
            e(solverVariableF, aVar.g(solverVariableF) * fG, z);
        }
        return fG;
    }

    @Override // b.f.b.b.a
    public final void c(SolverVariable solverVariable, float f2) {
        if (f2 == 0.0f) {
            d(solverVariable, true);
            return;
        }
        int i2 = this.j;
        if (i2 == -1) {
            this.j = 0;
            this.f2175i[0] = f2;
            this.f2173g[0] = solverVariable.f611d;
            this.f2174h[0] = -1;
            solverVariable.n++;
            solverVariable.a(this.f2169c);
            this.f2168b++;
            if (this.l) {
                return;
            }
            int i3 = this.k + 1;
            this.k = i3;
            int[] iArr = this.f2173g;
            if (i3 >= iArr.length) {
                this.l = true;
                this.k = iArr.length - 1;
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i2 != -1 && i5 < this.f2168b; i5++) {
            int[] iArr2 = this.f2173g;
            int i6 = iArr2[i2];
            int i7 = solverVariable.f611d;
            if (i6 == i7) {
                this.f2175i[i2] = f2;
                return;
            }
            if (iArr2[i2] < i7) {
                i4 = i2;
            }
            i2 = this.f2174h[i2];
        }
        int length = this.k;
        int i8 = length + 1;
        if (this.l) {
            int[] iArr3 = this.f2173g;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i8;
        }
        int[] iArr4 = this.f2173g;
        if (length >= iArr4.length && this.f2168b < iArr4.length) {
            int i9 = 0;
            while (true) {
                int[] iArr5 = this.f2173g;
                if (i9 >= iArr5.length) {
                    break;
                }
                if (iArr5[i9] == -1) {
                    length = i9;
                    break;
                }
                i9++;
            }
        }
        int[] iArr6 = this.f2173g;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i10 = this.f2171e * 2;
            this.f2171e = i10;
            this.l = false;
            this.k = length - 1;
            this.f2175i = Arrays.copyOf(this.f2175i, i10);
            this.f2173g = Arrays.copyOf(this.f2173g, this.f2171e);
            this.f2174h = Arrays.copyOf(this.f2174h, this.f2171e);
        }
        this.f2173g[length] = solverVariable.f611d;
        this.f2175i[length] = f2;
        if (i4 != -1) {
            int[] iArr7 = this.f2174h;
            iArr7[length] = iArr7[i4];
            iArr7[i4] = length;
        } else {
            this.f2174h[length] = this.j;
            this.j = length;
        }
        solverVariable.n++;
        solverVariable.a(this.f2169c);
        int i11 = this.f2168b + 1;
        this.f2168b = i11;
        if (!this.l) {
            this.k++;
        }
        int[] iArr8 = this.f2173g;
        if (i11 >= iArr8.length) {
            this.l = true;
        }
        if (this.k >= iArr8.length) {
            this.l = true;
            this.k = iArr8.length - 1;
        }
    }

    @Override // b.f.b.b.a
    public final void clear() {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2168b; i3++) {
            SolverVariable solverVariable = this.f2170d.f2185d[this.f2173g[i2]];
            if (solverVariable != null) {
                solverVariable.c(this.f2169c);
            }
            i2 = this.f2174h[i2];
        }
        this.j = -1;
        this.k = -1;
        this.l = false;
        this.f2168b = 0;
    }

    @Override // b.f.b.b.a
    public final float d(SolverVariable solverVariable, boolean z) {
        if (this.f2172f == solverVariable) {
            this.f2172f = null;
        }
        int i2 = this.j;
        if (i2 == -1) {
            return 0.0f;
        }
        int i3 = 0;
        int i4 = -1;
        while (i2 != -1 && i3 < this.f2168b) {
            if (this.f2173g[i2] == solverVariable.f611d) {
                if (i2 == this.j) {
                    this.j = this.f2174h[i2];
                } else {
                    int[] iArr = this.f2174h;
                    iArr[i4] = iArr[i2];
                }
                if (z) {
                    solverVariable.c(this.f2169c);
                }
                solverVariable.n--;
                this.f2168b--;
                this.f2173g[i2] = -1;
                if (this.l) {
                    this.k = i2;
                }
                return this.f2175i[i2];
            }
            i3++;
            i4 = i2;
            i2 = this.f2174h[i2];
        }
        return 0.0f;
    }

    @Override // b.f.b.b.a
    public void e(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = f2167a;
        if (f2 <= (-f3) || f2 >= f3) {
            int i2 = this.j;
            if (i2 == -1) {
                this.j = 0;
                this.f2175i[0] = f2;
                this.f2173g[0] = solverVariable.f611d;
                this.f2174h[0] = -1;
                solverVariable.n++;
                solverVariable.a(this.f2169c);
                this.f2168b++;
                if (this.l) {
                    return;
                }
                int i3 = this.k + 1;
                this.k = i3;
                int[] iArr = this.f2173g;
                if (i3 >= iArr.length) {
                    this.l = true;
                    this.k = iArr.length - 1;
                    return;
                }
                return;
            }
            int i4 = -1;
            for (int i5 = 0; i2 != -1 && i5 < this.f2168b; i5++) {
                int[] iArr2 = this.f2173g;
                int i6 = iArr2[i2];
                int i7 = solverVariable.f611d;
                if (i6 == i7) {
                    float[] fArr = this.f2175i;
                    float f4 = fArr[i2] + f2;
                    float f5 = f2167a;
                    if (f4 > (-f5) && f4 < f5) {
                        f4 = 0.0f;
                    }
                    fArr[i2] = f4;
                    if (f4 == 0.0f) {
                        if (i2 == this.j) {
                            this.j = this.f2174h[i2];
                        } else {
                            int[] iArr3 = this.f2174h;
                            iArr3[i4] = iArr3[i2];
                        }
                        if (z) {
                            solverVariable.c(this.f2169c);
                        }
                        if (this.l) {
                            this.k = i2;
                        }
                        solverVariable.n--;
                        this.f2168b--;
                        return;
                    }
                    return;
                }
                if (iArr2[i2] < i7) {
                    i4 = i2;
                }
                i2 = this.f2174h[i2];
            }
            int length = this.k;
            int i8 = length + 1;
            if (this.l) {
                int[] iArr4 = this.f2173g;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i8;
            }
            int[] iArr5 = this.f2173g;
            if (length >= iArr5.length && this.f2168b < iArr5.length) {
                int i9 = 0;
                while (true) {
                    int[] iArr6 = this.f2173g;
                    if (i9 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i9] == -1) {
                        length = i9;
                        break;
                    }
                    i9++;
                }
            }
            int[] iArr7 = this.f2173g;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i10 = this.f2171e * 2;
                this.f2171e = i10;
                this.l = false;
                this.k = length - 1;
                this.f2175i = Arrays.copyOf(this.f2175i, i10);
                this.f2173g = Arrays.copyOf(this.f2173g, this.f2171e);
                this.f2174h = Arrays.copyOf(this.f2174h, this.f2171e);
            }
            this.f2173g[length] = solverVariable.f611d;
            this.f2175i[length] = f2;
            if (i4 != -1) {
                int[] iArr8 = this.f2174h;
                iArr8[length] = iArr8[i4];
                iArr8[i4] = length;
            } else {
                this.f2174h[length] = this.j;
                this.j = length;
            }
            solverVariable.n++;
            solverVariable.a(this.f2169c);
            this.f2168b++;
            if (!this.l) {
                this.k++;
            }
            int i11 = this.k;
            int[] iArr9 = this.f2173g;
            if (i11 >= iArr9.length) {
                this.l = true;
                this.k = iArr9.length - 1;
            }
        }
    }

    @Override // b.f.b.b.a
    public SolverVariable f(int i2) {
        int i3 = this.j;
        for (int i4 = 0; i3 != -1 && i4 < this.f2168b; i4++) {
            if (i4 == i2) {
                return this.f2170d.f2185d[this.f2173g[i3]];
            }
            i3 = this.f2174h[i3];
        }
        return null;
    }

    @Override // b.f.b.b.a
    public final float g(SolverVariable solverVariable) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2168b; i3++) {
            if (this.f2173g[i2] == solverVariable.f611d) {
                return this.f2175i[i2];
            }
            i2 = this.f2174h[i2];
        }
        return 0.0f;
    }

    @Override // b.f.b.b.a
    public boolean h(SolverVariable solverVariable) {
        int i2 = this.j;
        if (i2 == -1) {
            return false;
        }
        for (int i3 = 0; i2 != -1 && i3 < this.f2168b; i3++) {
            if (this.f2173g[i2] == solverVariable.f611d) {
                return true;
            }
            i2 = this.f2174h[i2];
        }
        return false;
    }

    @Override // b.f.b.b.a
    public void i(float f2) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2168b; i3++) {
            float[] fArr = this.f2175i;
            fArr[i2] = fArr[i2] / f2;
            i2 = this.f2174h[i2];
        }
    }

    @Override // b.f.b.b.a
    public void j() {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2168b; i3++) {
            float[] fArr = this.f2175i;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.f2174h[i2];
        }
    }

    @Override // b.f.b.b.a
    public int k() {
        return this.f2168b;
    }

    public String toString() {
        int i2 = this.j;
        String str = "";
        for (int i3 = 0; i2 != -1 && i3 < this.f2168b; i3++) {
            str = ((str + " -> ") + this.f2175i[i2] + " : ") + this.f2170d.f2185d[this.f2173g[i2]];
            i2 = this.f2174h[i2];
        }
        return str;
    }
}
