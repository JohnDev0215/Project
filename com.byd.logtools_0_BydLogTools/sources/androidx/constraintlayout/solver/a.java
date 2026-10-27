package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.b;
import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f914a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f916c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final c f917d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f915b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f918e = 8;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SolverVariable f919f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int[] f920g = new int[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int[] f921h = new int[8];
    private float[] i = new float[8];
    private int j = -1;
    private int k = -1;
    private boolean l = false;

    a(b bVar, c cVar) {
        this.f916c = bVar;
        this.f917d = cVar;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float a(int i) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f915b; i3++) {
            if (i3 == i) {
                return this.i[i2];
            }
            i2 = this.f921h[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f922a);
        d(bVar.f922a, z);
        b.a aVar = bVar.f926e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            SolverVariable solverVariableF = aVar.f(i);
            e(solverVariableF, aVar.g(solverVariableF) * fG, z);
        }
        return fG;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public final void c(SolverVariable solverVariable, float f2) {
        if (f2 == 0.0f) {
            d(solverVariable, true);
            return;
        }
        int i = this.j;
        if (i == -1) {
            this.j = 0;
            this.i[0] = f2;
            this.f920g[0] = solverVariable.f908d;
            this.f921h[0] = -1;
            solverVariable.n++;
            solverVariable.a(this.f916c);
            this.f915b++;
            if (this.l) {
                return;
            }
            int i2 = this.k + 1;
            this.k = i2;
            int[] iArr = this.f920g;
            if (i2 >= iArr.length) {
                this.l = true;
                this.k = iArr.length - 1;
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.f915b; i4++) {
            int[] iArr2 = this.f920g;
            int i5 = iArr2[i];
            int i6 = solverVariable.f908d;
            if (i5 == i6) {
                this.i[i] = f2;
                return;
            }
            if (iArr2[i] < i6) {
                i3 = i;
            }
            i = this.f921h[i];
        }
        int length = this.k;
        int i7 = length + 1;
        if (this.l) {
            int[] iArr3 = this.f920g;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i7;
        }
        int[] iArr4 = this.f920g;
        if (length >= iArr4.length && this.f915b < iArr4.length) {
            int i8 = 0;
            while (true) {
                int[] iArr5 = this.f920g;
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
        int[] iArr6 = this.f920g;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i9 = this.f918e * 2;
            this.f918e = i9;
            this.l = false;
            this.k = length - 1;
            this.i = Arrays.copyOf(this.i, i9);
            this.f920g = Arrays.copyOf(this.f920g, this.f918e);
            this.f921h = Arrays.copyOf(this.f921h, this.f918e);
        }
        this.f920g[length] = solverVariable.f908d;
        this.i[length] = f2;
        if (i3 != -1) {
            int[] iArr7 = this.f921h;
            iArr7[length] = iArr7[i3];
            iArr7[i3] = length;
        } else {
            this.f921h[length] = this.j;
            this.j = length;
        }
        solverVariable.n++;
        solverVariable.a(this.f916c);
        int i10 = this.f915b + 1;
        this.f915b = i10;
        if (!this.l) {
            this.k++;
        }
        int[] iArr8 = this.f920g;
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
        for (int i2 = 0; i != -1 && i2 < this.f915b; i2++) {
            SolverVariable solverVariable = this.f917d.f931d[this.f920g[i]];
            if (solverVariable != null) {
                solverVariable.c(this.f916c);
            }
            i = this.f921h[i];
        }
        this.j = -1;
        this.k = -1;
        this.l = false;
        this.f915b = 0;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public final float d(SolverVariable solverVariable, boolean z) {
        if (this.f919f == solverVariable) {
            this.f919f = null;
        }
        int i = this.j;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.f915b) {
            if (this.f920g[i] == solverVariable.f908d) {
                if (i == this.j) {
                    this.j = this.f921h[i];
                } else {
                    int[] iArr = this.f921h;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.c(this.f916c);
                }
                solverVariable.n--;
                this.f915b--;
                this.f920g[i] = -1;
                if (this.l) {
                    this.k = i;
                }
                return this.i[i];
            }
            i2++;
            i3 = i;
            i = this.f921h[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void e(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = f914a;
        if (f2 <= (-f3) || f2 >= f3) {
            int i = this.j;
            if (i == -1) {
                this.j = 0;
                this.i[0] = f2;
                this.f920g[0] = solverVariable.f908d;
                this.f921h[0] = -1;
                solverVariable.n++;
                solverVariable.a(this.f916c);
                this.f915b++;
                if (this.l) {
                    return;
                }
                int i2 = this.k + 1;
                this.k = i2;
                int[] iArr = this.f920g;
                if (i2 >= iArr.length) {
                    this.l = true;
                    this.k = iArr.length - 1;
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.f915b; i4++) {
                int[] iArr2 = this.f920g;
                int i5 = iArr2[i];
                int i6 = solverVariable.f908d;
                if (i5 == i6) {
                    float[] fArr = this.i;
                    float f4 = fArr[i] + f2;
                    float f5 = f914a;
                    if (f4 > (-f5) && f4 < f5) {
                        f4 = 0.0f;
                    }
                    fArr[i] = f4;
                    if (f4 == 0.0f) {
                        if (i == this.j) {
                            this.j = this.f921h[i];
                        } else {
                            int[] iArr3 = this.f921h;
                            iArr3[i3] = iArr3[i];
                        }
                        if (z) {
                            solverVariable.c(this.f916c);
                        }
                        if (this.l) {
                            this.k = i;
                        }
                        solverVariable.n--;
                        this.f915b--;
                        return;
                    }
                    return;
                }
                if (iArr2[i] < i6) {
                    i3 = i;
                }
                i = this.f921h[i];
            }
            int length = this.k;
            int i7 = length + 1;
            if (this.l) {
                int[] iArr4 = this.f920g;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i7;
            }
            int[] iArr5 = this.f920g;
            if (length >= iArr5.length && this.f915b < iArr5.length) {
                int i8 = 0;
                while (true) {
                    int[] iArr6 = this.f920g;
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
            int[] iArr7 = this.f920g;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i9 = this.f918e * 2;
                this.f918e = i9;
                this.l = false;
                this.k = length - 1;
                this.i = Arrays.copyOf(this.i, i9);
                this.f920g = Arrays.copyOf(this.f920g, this.f918e);
                this.f921h = Arrays.copyOf(this.f921h, this.f918e);
            }
            this.f920g[length] = solverVariable.f908d;
            this.i[length] = f2;
            if (i3 != -1) {
                int[] iArr8 = this.f921h;
                iArr8[length] = iArr8[i3];
                iArr8[i3] = length;
            } else {
                this.f921h[length] = this.j;
                this.j = length;
            }
            solverVariable.n++;
            solverVariable.a(this.f916c);
            this.f915b++;
            if (!this.l) {
                this.k++;
            }
            int i10 = this.k;
            int[] iArr9 = this.f920g;
            if (i10 >= iArr9.length) {
                this.l = true;
                this.k = iArr9.length - 1;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public SolverVariable f(int i) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f915b; i3++) {
            if (i3 == i) {
                return this.f917d.f931d[this.f920g[i2]];
            }
            i2 = this.f921h[i2];
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public final float g(SolverVariable solverVariable) {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f915b; i2++) {
            if (this.f920g[i] == solverVariable.f908d) {
                return this.i[i];
            }
            i = this.f921h[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public boolean h(SolverVariable solverVariable) {
        int i = this.j;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.f915b; i2++) {
            if (this.f920g[i] == solverVariable.f908d) {
                return true;
            }
            i = this.f921h[i];
        }
        return false;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void i(float f2) {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f915b; i2++) {
            float[] fArr = this.i;
            fArr[i] = fArr[i] / f2;
            i = this.f921h[i];
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void j() {
        int i = this.j;
        for (int i2 = 0; i != -1 && i2 < this.f915b; i2++) {
            float[] fArr = this.i;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.f921h[i];
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public int k() {
        return this.f915b;
    }

    public String toString() {
        int i = this.j;
        String str = HttpUrl.FRAGMENT_ENCODE_SET;
        for (int i2 = 0; i != -1 && i2 < this.f915b; i2++) {
            str = ((str + " -> ") + this.i[i] + " : ") + this.f917d.f931d[this.f920g[i]];
            i = this.f921h[i];
        }
        return str;
    }
}
