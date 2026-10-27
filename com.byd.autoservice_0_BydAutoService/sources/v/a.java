package v;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.Arrays;
import v.b;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f5349a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f5351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f5352d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5350b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5353e = 8;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SolverVariable f5354f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f5355g = new int[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5356h = new int[8];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f5357i = new float[8];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5358j = -1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f5359k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5360l = false;

    public a(b bVar, c cVar) {
        this.f5351c = bVar;
        this.f5352d = cVar;
    }

    @Override // v.b.a
    public float a(int i3) {
        int i4 = this.f5358j;
        for (int i5 = 0; i4 != -1 && i5 < this.f5350b; i5++) {
            if (i5 == i3) {
                return this.f5357i[i4];
            }
            i4 = this.f5356h[i4];
        }
        return 0.0f;
    }

    @Override // v.b.a
    public float b(b bVar, boolean z3) {
        float fG = g(bVar.f5361a);
        d(bVar.f5361a, z3);
        b.a aVar = bVar.f5365e;
        int iK = aVar.k();
        for (int i3 = 0; i3 < iK; i3++) {
            SolverVariable solverVariableF = aVar.f(i3);
            e(solverVariableF, aVar.g(solverVariableF) * fG, z3);
        }
        return fG;
    }

    @Override // v.b.a
    public final void c(SolverVariable solverVariable, float f3) {
        if (f3 == 0.0f) {
            d(solverVariable, true);
            return;
        }
        int i3 = this.f5358j;
        if (i3 == -1) {
            this.f5358j = 0;
            this.f5357i[0] = f3;
            this.f5355g[0] = solverVariable.f813d;
            this.f5356h[0] = -1;
            solverVariable.f823n++;
            solverVariable.a(this.f5351c);
            this.f5350b++;
            if (this.f5360l) {
                return;
            }
            int i4 = this.f5359k + 1;
            this.f5359k = i4;
            int[] iArr = this.f5355g;
            if (i4 >= iArr.length) {
                this.f5360l = true;
                this.f5359k = iArr.length - 1;
                return;
            }
            return;
        }
        int i5 = -1;
        for (int i6 = 0; i3 != -1 && i6 < this.f5350b; i6++) {
            int[] iArr2 = this.f5355g;
            int i7 = iArr2[i3];
            int i8 = solverVariable.f813d;
            if (i7 == i8) {
                this.f5357i[i3] = f3;
                return;
            }
            if (iArr2[i3] < i8) {
                i5 = i3;
            }
            i3 = this.f5356h[i3];
        }
        int length = this.f5359k;
        int i9 = length + 1;
        if (this.f5360l) {
            int[] iArr3 = this.f5355g;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i9;
        }
        int[] iArr4 = this.f5355g;
        if (length >= iArr4.length && this.f5350b < iArr4.length) {
            int i10 = 0;
            while (true) {
                int[] iArr5 = this.f5355g;
                if (i10 >= iArr5.length) {
                    break;
                }
                if (iArr5[i10] == -1) {
                    length = i10;
                    break;
                }
                i10++;
            }
        }
        int[] iArr6 = this.f5355g;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i11 = this.f5353e * 2;
            this.f5353e = i11;
            this.f5360l = false;
            this.f5359k = length - 1;
            this.f5357i = Arrays.copyOf(this.f5357i, i11);
            this.f5355g = Arrays.copyOf(this.f5355g, this.f5353e);
            this.f5356h = Arrays.copyOf(this.f5356h, this.f5353e);
        }
        this.f5355g[length] = solverVariable.f813d;
        this.f5357i[length] = f3;
        if (i5 != -1) {
            int[] iArr7 = this.f5356h;
            iArr7[length] = iArr7[i5];
            iArr7[i5] = length;
        } else {
            this.f5356h[length] = this.f5358j;
            this.f5358j = length;
        }
        solverVariable.f823n++;
        solverVariable.a(this.f5351c);
        int i12 = this.f5350b + 1;
        this.f5350b = i12;
        if (!this.f5360l) {
            this.f5359k++;
        }
        int[] iArr8 = this.f5355g;
        if (i12 >= iArr8.length) {
            this.f5360l = true;
        }
        if (this.f5359k >= iArr8.length) {
            this.f5360l = true;
            this.f5359k = iArr8.length - 1;
        }
    }

    @Override // v.b.a
    public final void clear() {
        int i3 = this.f5358j;
        for (int i4 = 0; i3 != -1 && i4 < this.f5350b; i4++) {
            SolverVariable solverVariable = this.f5352d.f5370d[this.f5355g[i3]];
            if (solverVariable != null) {
                solverVariable.c(this.f5351c);
            }
            i3 = this.f5356h[i3];
        }
        this.f5358j = -1;
        this.f5359k = -1;
        this.f5360l = false;
        this.f5350b = 0;
    }

    @Override // v.b.a
    public final float d(SolverVariable solverVariable, boolean z3) {
        if (this.f5354f == solverVariable) {
            this.f5354f = null;
        }
        int i3 = this.f5358j;
        if (i3 == -1) {
            return 0.0f;
        }
        int i4 = 0;
        int i5 = -1;
        while (i3 != -1 && i4 < this.f5350b) {
            if (this.f5355g[i3] == solverVariable.f813d) {
                if (i3 == this.f5358j) {
                    this.f5358j = this.f5356h[i3];
                } else {
                    int[] iArr = this.f5356h;
                    iArr[i5] = iArr[i3];
                }
                if (z3) {
                    solverVariable.c(this.f5351c);
                }
                solverVariable.f823n--;
                this.f5350b--;
                this.f5355g[i3] = -1;
                if (this.f5360l) {
                    this.f5359k = i3;
                }
                return this.f5357i[i3];
            }
            i4++;
            i5 = i3;
            i3 = this.f5356h[i3];
        }
        return 0.0f;
    }

    @Override // v.b.a
    public void e(SolverVariable solverVariable, float f3, boolean z3) {
        float f4 = f5349a;
        if (f3 <= (-f4) || f3 >= f4) {
            int i3 = this.f5358j;
            if (i3 == -1) {
                this.f5358j = 0;
                this.f5357i[0] = f3;
                this.f5355g[0] = solverVariable.f813d;
                this.f5356h[0] = -1;
                solverVariable.f823n++;
                solverVariable.a(this.f5351c);
                this.f5350b++;
                if (this.f5360l) {
                    return;
                }
                int i4 = this.f5359k + 1;
                this.f5359k = i4;
                int[] iArr = this.f5355g;
                if (i4 >= iArr.length) {
                    this.f5360l = true;
                    this.f5359k = iArr.length - 1;
                    return;
                }
                return;
            }
            int i5 = -1;
            for (int i6 = 0; i3 != -1 && i6 < this.f5350b; i6++) {
                int[] iArr2 = this.f5355g;
                int i7 = iArr2[i3];
                int i8 = solverVariable.f813d;
                if (i7 == i8) {
                    float[] fArr = this.f5357i;
                    float f5 = fArr[i3] + f3;
                    float f6 = f5349a;
                    if (f5 > (-f6) && f5 < f6) {
                        f5 = 0.0f;
                    }
                    fArr[i3] = f5;
                    if (f5 == 0.0f) {
                        if (i3 == this.f5358j) {
                            this.f5358j = this.f5356h[i3];
                        } else {
                            int[] iArr3 = this.f5356h;
                            iArr3[i5] = iArr3[i3];
                        }
                        if (z3) {
                            solverVariable.c(this.f5351c);
                        }
                        if (this.f5360l) {
                            this.f5359k = i3;
                        }
                        solverVariable.f823n--;
                        this.f5350b--;
                        return;
                    }
                    return;
                }
                if (iArr2[i3] < i8) {
                    i5 = i3;
                }
                i3 = this.f5356h[i3];
            }
            int length = this.f5359k;
            int i9 = length + 1;
            if (this.f5360l) {
                int[] iArr4 = this.f5355g;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i9;
            }
            int[] iArr5 = this.f5355g;
            if (length >= iArr5.length && this.f5350b < iArr5.length) {
                int i10 = 0;
                while (true) {
                    int[] iArr6 = this.f5355g;
                    if (i10 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i10] == -1) {
                        length = i10;
                        break;
                    }
                    i10++;
                }
            }
            int[] iArr7 = this.f5355g;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i11 = this.f5353e * 2;
                this.f5353e = i11;
                this.f5360l = false;
                this.f5359k = length - 1;
                this.f5357i = Arrays.copyOf(this.f5357i, i11);
                this.f5355g = Arrays.copyOf(this.f5355g, this.f5353e);
                this.f5356h = Arrays.copyOf(this.f5356h, this.f5353e);
            }
            this.f5355g[length] = solverVariable.f813d;
            this.f5357i[length] = f3;
            if (i5 != -1) {
                int[] iArr8 = this.f5356h;
                iArr8[length] = iArr8[i5];
                iArr8[i5] = length;
            } else {
                this.f5356h[length] = this.f5358j;
                this.f5358j = length;
            }
            solverVariable.f823n++;
            solverVariable.a(this.f5351c);
            this.f5350b++;
            if (!this.f5360l) {
                this.f5359k++;
            }
            int i12 = this.f5359k;
            int[] iArr9 = this.f5355g;
            if (i12 >= iArr9.length) {
                this.f5360l = true;
                this.f5359k = iArr9.length - 1;
            }
        }
    }

    @Override // v.b.a
    public SolverVariable f(int i3) {
        int i4 = this.f5358j;
        for (int i5 = 0; i4 != -1 && i5 < this.f5350b; i5++) {
            if (i5 == i3) {
                return this.f5352d.f5370d[this.f5355g[i4]];
            }
            i4 = this.f5356h[i4];
        }
        return null;
    }

    @Override // v.b.a
    public final float g(SolverVariable solverVariable) {
        int i3 = this.f5358j;
        for (int i4 = 0; i3 != -1 && i4 < this.f5350b; i4++) {
            if (this.f5355g[i3] == solverVariable.f813d) {
                return this.f5357i[i3];
            }
            i3 = this.f5356h[i3];
        }
        return 0.0f;
    }

    @Override // v.b.a
    public boolean h(SolverVariable solverVariable) {
        int i3 = this.f5358j;
        if (i3 == -1) {
            return false;
        }
        for (int i4 = 0; i3 != -1 && i4 < this.f5350b; i4++) {
            if (this.f5355g[i3] == solverVariable.f813d) {
                return true;
            }
            i3 = this.f5356h[i3];
        }
        return false;
    }

    @Override // v.b.a
    public void i(float f3) {
        int i3 = this.f5358j;
        for (int i4 = 0; i3 != -1 && i4 < this.f5350b; i4++) {
            float[] fArr = this.f5357i;
            fArr[i3] = fArr[i3] / f3;
            i3 = this.f5356h[i3];
        }
    }

    @Override // v.b.a
    public void j() {
        int i3 = this.f5358j;
        for (int i4 = 0; i3 != -1 && i4 < this.f5350b; i4++) {
            float[] fArr = this.f5357i;
            fArr[i3] = fArr[i3] * (-1.0f);
            i3 = this.f5356h[i3];
        }
    }

    @Override // v.b.a
    public int k() {
        return this.f5350b;
    }

    public String toString() {
        int i3 = this.f5358j;
        String str = "";
        for (int i4 = 0; i3 != -1 && i4 < this.f5350b; i4++) {
            str = ((str + " -> ") + this.f5357i[i3] + " : ") + this.f5352d.f5370d[this.f5355g[i3]];
            i3 = this.f5356h[i3];
        }
        return str;
    }
}
