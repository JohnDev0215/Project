package androidx.constraintlayout.core;

import androidx.constraintlayout.core.b;
import java.util.Arrays;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f705a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final c f708d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f706b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f709e = 8;
    private SolverVariable f = null;
    private int[] g = new int[8];
    private int[] h = new int[8];
    private float[] i = new float[8];
    private int j = -1;
    private int k = -1;
    private boolean l = false;

    a(b bVar, c cVar) {
        this.f707c = bVar;
        this.f708d = cVar;
    }

    @Override // androidx.constraintlayout.core.b.a
    public float a(int i) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f706b; i3++) {
            if (i3 == i) {
                return this.i[i2];
            }
            i2 = this.h[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.b.a
    public final float b(SolverVariable solverVariable) {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f706b; i2++) {
            if (this.g[i] == solverVariable.f703e) {
                return this.i[i];
            }
            i = this.h[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.b.a
    public final float c(SolverVariable solverVariable, boolean z) {
        if (this.f == solverVariable) {
            this.f = null;
        }
        int i = this.j;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.f706b) {
            if (this.g[i] == solverVariable.f703e) {
                if (i == this.j) {
                    this.j = this.h[i];
                } else {
                    int[] iArr = this.h;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.g(this.f707c);
                }
                solverVariable.o--;
                this.f706b--;
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

    @Override // androidx.constraintlayout.core.b.a
    public final void clear() {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f706b; i2++) {
            SolverVariable solverVariable = this.f708d.f718d[this.g[i]];
            if (solverVariable != null) {
                solverVariable.g(this.f707c);
            }
            i = this.h[i];
        }
        this.j = -1;
        this.k = -1;
        this.l = false;
        this.f706b = 0;
    }

    @Override // androidx.constraintlayout.core.b.a
    public boolean d(SolverVariable solverVariable) {
        int i = this.j;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.f706b; i2++) {
            if (this.g[i] == solverVariable.f703e) {
                return true;
            }
            i = this.h[i];
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.b.a
    public float e(b bVar, boolean z) {
        float fB = b(bVar.f710a);
        c(bVar.f710a, z);
        b.a aVar = bVar.f714e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            SolverVariable solverVariableG = aVar.g(i);
            h(solverVariableG, aVar.b(solverVariableG) * fB, z);
        }
        return fB;
    }

    @Override // androidx.constraintlayout.core.b.a
    public final void f(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            c(solverVariable, true);
            return;
        }
        int i = this.j;
        if (i == -1) {
            this.j = 0;
            this.i[0] = f;
            this.g[0] = solverVariable.f703e;
            this.h[0] = -1;
            solverVariable.o++;
            solverVariable.b(this.f707c);
            this.f706b++;
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
        for (int i4 = 0; i != -1 && i4 < this.f706b; i4++) {
            int[] iArr2 = this.g;
            int i5 = iArr2[i];
            int i6 = solverVariable.f703e;
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
        if (length >= iArr4.length && this.f706b < iArr4.length) {
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
            int i9 = this.f709e * 2;
            this.f709e = i9;
            this.l = false;
            this.k = length - 1;
            this.i = Arrays.copyOf(this.i, i9);
            this.g = Arrays.copyOf(this.g, this.f709e);
            this.h = Arrays.copyOf(this.h, this.f709e);
        }
        this.g[length] = solverVariable.f703e;
        this.i[length] = f;
        if (i3 != -1) {
            int[] iArr7 = this.h;
            iArr7[length] = iArr7[i3];
            iArr7[i3] = length;
        } else {
            this.h[length] = this.j;
            this.j = length;
        }
        solverVariable.o++;
        solverVariable.b(this.f707c);
        int i10 = this.f706b + 1;
        this.f706b = i10;
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

    @Override // androidx.constraintlayout.core.b.a
    public SolverVariable g(int i) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f706b; i3++) {
            if (i3 == i) {
                return this.f708d.f718d[this.g[i2]];
            }
            i2 = this.h[i2];
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.b.a
    public void h(SolverVariable solverVariable, float f, boolean z) {
        float f2 = f705a;
        if (f <= (-f2) || f >= f2) {
            int i = this.j;
            if (i == -1) {
                this.j = 0;
                this.i[0] = f;
                this.g[0] = solverVariable.f703e;
                this.h[0] = -1;
                solverVariable.o++;
                solverVariable.b(this.f707c);
                this.f706b++;
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
            for (int i4 = 0; i != -1 && i4 < this.f706b; i4++) {
                int[] iArr2 = this.g;
                int i5 = iArr2[i];
                int i6 = solverVariable.f703e;
                if (i5 == i6) {
                    float[] fArr = this.i;
                    float f3 = fArr[i] + f;
                    float f4 = f705a;
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
                            solverVariable.g(this.f707c);
                        }
                        if (this.l) {
                            this.k = i;
                        }
                        solverVariable.o--;
                        this.f706b--;
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
            if (length >= iArr5.length && this.f706b < iArr5.length) {
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
                int i9 = this.f709e * 2;
                this.f709e = i9;
                this.l = false;
                this.k = length - 1;
                this.i = Arrays.copyOf(this.i, i9);
                this.g = Arrays.copyOf(this.g, this.f709e);
                this.h = Arrays.copyOf(this.h, this.f709e);
            }
            this.g[length] = solverVariable.f703e;
            this.i[length] = f;
            if (i3 != -1) {
                int[] iArr8 = this.h;
                iArr8[length] = iArr8[i3];
                iArr8[i3] = length;
            } else {
                this.h[length] = this.j;
                this.j = length;
            }
            solverVariable.o++;
            solverVariable.b(this.f707c);
            this.f706b++;
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

    @Override // androidx.constraintlayout.core.b.a
    public void i(float f) {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f706b; i2++) {
            float[] fArr = this.i;
            fArr[i] = fArr[i] / f;
            i = this.h[i];
        }
    }

    @Override // androidx.constraintlayout.core.b.a
    public void j() {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f706b; i2++) {
            float[] fArr = this.i;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.h[i];
        }
    }

    @Override // androidx.constraintlayout.core.b.a
    public int k() {
        return this.f706b;
    }

    public String toString() {
        int i = this.j;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.f706b; i2++) {
            str = ((str + " -> ") + this.i[i] + " : ") + this.f708d.f718d[this.g[i]];
            i = this.h[i];
        }
        return str;
    }
}
