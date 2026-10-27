package b.e.b;

import androidx.constraintlayout.solver.SolverVariable;
import b.e.b.b;
import java.util.Arrays;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f1356a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f1358c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f1359d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1357b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1360e = 8;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SolverVariable f1361f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f1362g = new int[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f1363h = new int[8];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f1364i = new float[8];
    public int j = -1;
    public int k = -1;
    public boolean l = false;

    public a(b bVar, c cVar) {
        this.f1358c = bVar;
        this.f1359d = cVar;
    }

    @Override // b.e.b.b.a
    public float a(int i2) {
        int i3 = this.j;
        for (int i4 = 0; i3 != -1 && i4 < this.f1357b; i4++) {
            if (i4 == i2) {
                return this.f1364i[i3];
            }
            i3 = this.f1363h[i3];
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f1365a);
        d(bVar.f1365a, z);
        b.a aVar = bVar.f1369e;
        int iK = aVar.k();
        for (int i2 = 0; i2 < iK; i2++) {
            SolverVariable solverVariableF = aVar.f(i2);
            e(solverVariableF, aVar.g(solverVariableF) * fG, z);
        }
        return fG;
    }

    @Override // b.e.b.b.a
    public final void c(SolverVariable solverVariable, float f2) {
        if (f2 == 0.0f) {
            d(solverVariable, true);
            return;
        }
        int i2 = this.j;
        if (i2 == -1) {
            this.j = 0;
            this.f1364i[0] = f2;
            this.f1362g[0] = solverVariable.f322d;
            this.f1363h[0] = -1;
            solverVariable.n++;
            solverVariable.a(this.f1358c);
            this.f1357b++;
            if (this.l) {
                return;
            }
            int i3 = this.k + 1;
            this.k = i3;
            int[] iArr = this.f1362g;
            if (i3 >= iArr.length) {
                this.l = true;
                this.k = iArr.length - 1;
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i2 != -1 && i5 < this.f1357b; i5++) {
            int[] iArr2 = this.f1362g;
            int i6 = iArr2[i2];
            int i7 = solverVariable.f322d;
            if (i6 == i7) {
                this.f1364i[i2] = f2;
                return;
            }
            if (iArr2[i2] < i7) {
                i4 = i2;
            }
            i2 = this.f1363h[i2];
        }
        int length = this.k;
        int i8 = length + 1;
        if (this.l) {
            int[] iArr3 = this.f1362g;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i8;
        }
        int[] iArr4 = this.f1362g;
        if (length >= iArr4.length && this.f1357b < iArr4.length) {
            int i9 = 0;
            while (true) {
                int[] iArr5 = this.f1362g;
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
        int[] iArr6 = this.f1362g;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i10 = this.f1360e * 2;
            this.f1360e = i10;
            this.l = false;
            this.k = length - 1;
            this.f1364i = Arrays.copyOf(this.f1364i, i10);
            this.f1362g = Arrays.copyOf(this.f1362g, this.f1360e);
            this.f1363h = Arrays.copyOf(this.f1363h, this.f1360e);
        }
        this.f1362g[length] = solverVariable.f322d;
        this.f1364i[length] = f2;
        if (i4 != -1) {
            int[] iArr7 = this.f1363h;
            iArr7[length] = iArr7[i4];
            iArr7[i4] = length;
        } else {
            this.f1363h[length] = this.j;
            this.j = length;
        }
        solverVariable.n++;
        solverVariable.a(this.f1358c);
        int i11 = this.f1357b + 1;
        this.f1357b = i11;
        if (!this.l) {
            this.k++;
        }
        int[] iArr8 = this.f1362g;
        if (i11 >= iArr8.length) {
            this.l = true;
        }
        if (this.k >= iArr8.length) {
            this.l = true;
            this.k = iArr8.length - 1;
        }
    }

    @Override // b.e.b.b.a
    public final void clear() {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f1357b; i3++) {
            SolverVariable solverVariable = this.f1359d.f1374d[this.f1362g[i2]];
            if (solverVariable != null) {
                solverVariable.c(this.f1358c);
            }
            i2 = this.f1363h[i2];
        }
        this.j = -1;
        this.k = -1;
        this.l = false;
        this.f1357b = 0;
    }

    @Override // b.e.b.b.a
    public final float d(SolverVariable solverVariable, boolean z) {
        if (this.f1361f == solverVariable) {
            this.f1361f = null;
        }
        int i2 = this.j;
        if (i2 == -1) {
            return 0.0f;
        }
        int i3 = 0;
        int i4 = -1;
        while (i2 != -1 && i3 < this.f1357b) {
            if (this.f1362g[i2] == solverVariable.f322d) {
                if (i2 == this.j) {
                    this.j = this.f1363h[i2];
                } else {
                    int[] iArr = this.f1363h;
                    iArr[i4] = iArr[i2];
                }
                if (z) {
                    solverVariable.c(this.f1358c);
                }
                solverVariable.n--;
                this.f1357b--;
                this.f1362g[i2] = -1;
                if (this.l) {
                    this.k = i2;
                }
                return this.f1364i[i2];
            }
            i3++;
            i4 = i2;
            i2 = this.f1363h[i2];
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public void e(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = f1356a;
        if (f2 <= (-f3) || f2 >= f3) {
            int i2 = this.j;
            if (i2 == -1) {
                this.j = 0;
                this.f1364i[0] = f2;
                this.f1362g[0] = solverVariable.f322d;
                this.f1363h[0] = -1;
                solverVariable.n++;
                solverVariable.a(this.f1358c);
                this.f1357b++;
                if (this.l) {
                    return;
                }
                int i3 = this.k + 1;
                this.k = i3;
                int[] iArr = this.f1362g;
                if (i3 >= iArr.length) {
                    this.l = true;
                    this.k = iArr.length - 1;
                    return;
                }
                return;
            }
            int i4 = -1;
            for (int i5 = 0; i2 != -1 && i5 < this.f1357b; i5++) {
                int[] iArr2 = this.f1362g;
                int i6 = iArr2[i2];
                int i7 = solverVariable.f322d;
                if (i6 == i7) {
                    float[] fArr = this.f1364i;
                    float f4 = fArr[i2] + f2;
                    float f5 = f1356a;
                    if (f4 > (-f5) && f4 < f5) {
                        f4 = 0.0f;
                    }
                    fArr[i2] = f4;
                    if (f4 == 0.0f) {
                        if (i2 == this.j) {
                            this.j = this.f1363h[i2];
                        } else {
                            int[] iArr3 = this.f1363h;
                            iArr3[i4] = iArr3[i2];
                        }
                        if (z) {
                            solverVariable.c(this.f1358c);
                        }
                        if (this.l) {
                            this.k = i2;
                        }
                        solverVariable.n--;
                        this.f1357b--;
                        return;
                    }
                    return;
                }
                if (iArr2[i2] < i7) {
                    i4 = i2;
                }
                i2 = this.f1363h[i2];
            }
            int length = this.k;
            int i8 = length + 1;
            if (this.l) {
                int[] iArr4 = this.f1362g;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i8;
            }
            int[] iArr5 = this.f1362g;
            if (length >= iArr5.length && this.f1357b < iArr5.length) {
                int i9 = 0;
                while (true) {
                    int[] iArr6 = this.f1362g;
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
            int[] iArr7 = this.f1362g;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i10 = this.f1360e * 2;
                this.f1360e = i10;
                this.l = false;
                this.k = length - 1;
                this.f1364i = Arrays.copyOf(this.f1364i, i10);
                this.f1362g = Arrays.copyOf(this.f1362g, this.f1360e);
                this.f1363h = Arrays.copyOf(this.f1363h, this.f1360e);
            }
            this.f1362g[length] = solverVariable.f322d;
            this.f1364i[length] = f2;
            if (i4 != -1) {
                int[] iArr8 = this.f1363h;
                iArr8[length] = iArr8[i4];
                iArr8[i4] = length;
            } else {
                this.f1363h[length] = this.j;
                this.j = length;
            }
            solverVariable.n++;
            solverVariable.a(this.f1358c);
            this.f1357b++;
            if (!this.l) {
                this.k++;
            }
            int i11 = this.k;
            int[] iArr9 = this.f1362g;
            if (i11 >= iArr9.length) {
                this.l = true;
                this.k = iArr9.length - 1;
            }
        }
    }

    @Override // b.e.b.b.a
    public SolverVariable f(int i2) {
        int i3 = this.j;
        for (int i4 = 0; i3 != -1 && i4 < this.f1357b; i4++) {
            if (i4 == i2) {
                return this.f1359d.f1374d[this.f1362g[i3]];
            }
            i3 = this.f1363h[i3];
        }
        return null;
    }

    @Override // b.e.b.b.a
    public final float g(SolverVariable solverVariable) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f1357b; i3++) {
            if (this.f1362g[i2] == solverVariable.f322d) {
                return this.f1364i[i2];
            }
            i2 = this.f1363h[i2];
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public boolean h(SolverVariable solverVariable) {
        int i2 = this.j;
        if (i2 == -1) {
            return false;
        }
        for (int i3 = 0; i2 != -1 && i3 < this.f1357b; i3++) {
            if (this.f1362g[i2] == solverVariable.f322d) {
                return true;
            }
            i2 = this.f1363h[i2];
        }
        return false;
    }

    @Override // b.e.b.b.a
    public void i(float f2) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f1357b; i3++) {
            float[] fArr = this.f1364i;
            fArr[i2] = fArr[i2] / f2;
            i2 = this.f1363h[i2];
        }
    }

    @Override // b.e.b.b.a
    public void j() {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f1357b; i3++) {
            float[] fArr = this.f1364i;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.f1363h[i2];
        }
    }

    @Override // b.e.b.b.a
    public int k() {
        return this.f1357b;
    }

    public String toString() {
        int i2 = this.j;
        String str = "";
        for (int i3 = 0; i2 != -1 && i3 < this.f1357b; i3++) {
            str = ((str + " -> ") + this.f1364i[i2] + " : ") + this.f1359d.f1374d[this.f1362g[i2]];
            i2 = this.f1363h[i2];
        }
        return str;
    }
}
