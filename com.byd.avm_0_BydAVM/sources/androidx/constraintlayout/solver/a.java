package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.b;
import java.util.Arrays;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f624a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f626c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final c f627d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f625b = 0;
    private int e = 8;
    private SolverVariable f = null;
    private int[] g = new int[8];
    private int[] h = new int[8];
    private float[] i = new float[8];
    private int j = -1;
    private int k = -1;
    private boolean l = false;

    a(b bVar, c cVar) {
        this.f626c = bVar;
        this.f627d = cVar;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float a(int i) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f625b; i3++) {
            if (i3 == i) {
                return this.i[i2];
            }
            i2 = this.h[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f628a);
        d(bVar.f628a, z);
        b.a aVar = bVar.e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            SolverVariable solverVariableF = aVar.f(i);
            e(solverVariableF, aVar.g(solverVariableF) * fG, z);
        }
        return fG;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public final void c(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            d(solverVariable, true);
            return;
        }
        int i = this.j;
        if (i == -1) {
            this.j = 0;
            this.i[0] = f;
            this.g[0] = solverVariable.f622d;
            this.h[0] = -1;
            solverVariable.n++;
            solverVariable.a(this.f626c);
            this.f625b++;
            if (this.l) {
                return;
            }
            int i2 = this.k + 1;
            this.k = i2;
            int[] iArr = this.g;
            if (i2 >= iArr.length) {
                this.l = true;
                this.k = iArr.length - 1;
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.f625b; i4++) {
            int[] iArr2 = this.g;
            int i5 = iArr2[i];
            int i6 = solverVariable.f622d;
            if (i5 == i6) {
                this.i[i] = f;
                return;
            }
            if (iArr2[i] < i6) {
                i3 = i;
            }
            i = this.h[i];
        }
        int length = this.k;
        int i7 = length + 1;
        if (this.l) {
            int[] iArr3 = this.g;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i7;
        }
        int[] iArr4 = this.g;
        if (length >= iArr4.length && this.f625b < iArr4.length) {
            int i8 = 0;
            while (true) {
                int[] iArr5 = this.g;
                if (i8 >= iArr5.length) {
                    break;
                }
                if (iArr5[i8] == -1) {
                    length = i8;
                    break;
                }
                i8++;
            }
        }
        int[] iArr6 = this.g;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i9 = this.e * 2;
            this.e = i9;
            this.l = false;
            this.k = length - 1;
            this.i = Arrays.copyOf(this.i, i9);
            this.g = Arrays.copyOf(this.g, this.e);
            this.h = Arrays.copyOf(this.h, this.e);
        }
        this.g[length] = solverVariable.f622d;
        this.i[length] = f;
        if (i3 != -1) {
            int[] iArr7 = this.h;
            iArr7[length] = iArr7[i3];
            iArr7[i3] = length;
        } else {
            this.h[length] = this.j;
            this.j = length;
        }
        solverVariable.n++;
        solverVariable.a(this.f626c);
        int i10 = this.f625b + 1;
        this.f625b = i10;
        if (!this.l) {
            this.k++;
        }
        int[] iArr8 = this.g;
        if (i10 >= iArr8.length) {
            this.l = true;
        }
        if (this.k >= iArr8.length) {
            this.l = true;
            this.k = iArr8.length - 1;
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public final void clear() {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f625b; i2++) {
            SolverVariable solverVariable = this.f627d.f635d[this.g[i]];
            if (solverVariable != null) {
                solverVariable.c(this.f626c);
            }
            i = this.h[i];
        }
        this.j = -1;
        this.k = -1;
        this.l = false;
        this.f625b = 0;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public final float d(SolverVariable solverVariable, boolean z) {
        if (this.f == solverVariable) {
            this.f = null;
        }
        int i = this.j;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.f625b) {
            if (this.g[i] == solverVariable.f622d) {
                if (i == this.j) {
                    this.j = this.h[i];
                } else {
                    int[] iArr = this.h;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.c(this.f626c);
                }
                solverVariable.n--;
                this.f625b--;
                this.g[i] = -1;
                if (this.l) {
                    this.k = i;
                }
                return this.i[i];
            }
            i2++;
            i3 = i;
            i = this.h[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void e(SolverVariable solverVariable, float f, boolean z) {
        float f2 = f624a;
        if (f <= (-f2) || f >= f2) {
            int i = this.j;
            if (i == -1) {
                this.j = 0;
                this.i[0] = f;
                this.g[0] = solverVariable.f622d;
                this.h[0] = -1;
                solverVariable.n++;
                solverVariable.a(this.f626c);
                this.f625b++;
                if (this.l) {
                    return;
                }
                int i2 = this.k + 1;
                this.k = i2;
                int[] iArr = this.g;
                if (i2 >= iArr.length) {
                    this.l = true;
                    this.k = iArr.length - 1;
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.f625b; i4++) {
                int[] iArr2 = this.g;
                int i5 = iArr2[i];
                int i6 = solverVariable.f622d;
                if (i5 == i6) {
                    float[] fArr = this.i;
                    float f3 = fArr[i] + f;
                    float f4 = f624a;
                    if (f3 > (-f4) && f3 < f4) {
                        f3 = 0.0f;
                    }
                    fArr[i] = f3;
                    if (f3 == 0.0f) {
                        if (i == this.j) {
                            this.j = this.h[i];
                        } else {
                            int[] iArr3 = this.h;
                            iArr3[i3] = iArr3[i];
                        }
                        if (z) {
                            solverVariable.c(this.f626c);
                        }
                        if (this.l) {
                            this.k = i;
                        }
                        solverVariable.n--;
                        this.f625b--;
                        return;
                    }
                    return;
                }
                if (iArr2[i] < i6) {
                    i3 = i;
                }
                i = this.h[i];
            }
            int length = this.k;
            int i7 = length + 1;
            if (this.l) {
                int[] iArr4 = this.g;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i7;
            }
            int[] iArr5 = this.g;
            if (length >= iArr5.length && this.f625b < iArr5.length) {
                int i8 = 0;
                while (true) {
                    int[] iArr6 = this.g;
                    if (i8 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i8] == -1) {
                        length = i8;
                        break;
                    }
                    i8++;
                }
            }
            int[] iArr7 = this.g;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i9 = this.e * 2;
                this.e = i9;
                this.l = false;
                this.k = length - 1;
                this.i = Arrays.copyOf(this.i, i9);
                this.g = Arrays.copyOf(this.g, this.e);
                this.h = Arrays.copyOf(this.h, this.e);
            }
            this.g[length] = solverVariable.f622d;
            this.i[length] = f;
            if (i3 != -1) {
                int[] iArr8 = this.h;
                iArr8[length] = iArr8[i3];
                iArr8[i3] = length;
            } else {
                this.h[length] = this.j;
                this.j = length;
            }
            solverVariable.n++;
            solverVariable.a(this.f626c);
            this.f625b++;
            if (!this.l) {
                this.k++;
            }
            int i10 = this.k;
            int[] iArr9 = this.g;
            if (i10 >= iArr9.length) {
                this.l = true;
                this.k = iArr9.length - 1;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public SolverVariable f(int i) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f625b; i3++) {
            if (i3 == i) {
                return this.f627d.f635d[this.g[i2]];
            }
            i2 = this.h[i2];
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public final float g(SolverVariable solverVariable) {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f625b; i2++) {
            if (this.g[i] == solverVariable.f622d) {
                return this.i[i];
            }
            i = this.h[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public boolean h(SolverVariable solverVariable) {
        int i = this.j;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.f625b; i2++) {
            if (this.g[i] == solverVariable.f622d) {
                return true;
            }
            i = this.h[i];
        }
        return false;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void i(float f) {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f625b; i2++) {
            float[] fArr = this.i;
            fArr[i] = fArr[i] / f;
            i = this.h[i];
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void j() {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f625b; i2++) {
            float[] fArr = this.i;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.h[i];
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public int k() {
        return this.f625b;
    }

    public String toString() {
        int i = this.j;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.f625b; i2++) {
            str = ((str + " -> ") + this.i[i] + " : ") + this.f627d.f635d[this.g[i]];
            i = this.h[i];
        }
        return str;
    }
}
