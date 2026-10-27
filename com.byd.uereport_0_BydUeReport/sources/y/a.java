package y;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.Arrays;
import y.b;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f9094a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f9096c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f9097d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f9095b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9098e = 8;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SolverVariable f9099f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f9100g = new int[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f9101h = new int[8];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f9102i = new float[8];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9103j = -1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f9104k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f9105l = false;

    public a(b bVar, c cVar) {
        this.f9096c = bVar;
        this.f9097d = cVar;
    }

    @Override // y.b.a
    public float a(int i8) {
        int i9 = this.f9103j;
        for (int i10 = 0; i9 != -1 && i10 < this.f9095b; i10++) {
            if (i10 == i8) {
                return this.f9102i[i9];
            }
            i9 = this.f9101h[i9];
        }
        return 0.0f;
    }

    @Override // y.b.a
    public void b(SolverVariable solverVariable, float f8, boolean z7) {
        float f9 = f9094a;
        if (f8 <= (-f9) || f8 >= f9) {
            int i8 = this.f9103j;
            if (i8 == -1) {
                this.f9103j = 0;
                this.f9102i[0] = f8;
                this.f9100g[0] = solverVariable.f904d;
                this.f9101h[0] = -1;
                solverVariable.f914n++;
                solverVariable.a(this.f9096c);
                this.f9095b++;
                if (this.f9105l) {
                    return;
                }
                int i9 = this.f9104k + 1;
                this.f9104k = i9;
                int[] iArr = this.f9100g;
                if (i9 >= iArr.length) {
                    this.f9105l = true;
                    this.f9104k = iArr.length - 1;
                    return;
                }
                return;
            }
            int i10 = -1;
            for (int i11 = 0; i8 != -1 && i11 < this.f9095b; i11++) {
                int[] iArr2 = this.f9100g;
                int i12 = iArr2[i8];
                int i13 = solverVariable.f904d;
                if (i12 == i13) {
                    float[] fArr = this.f9102i;
                    float f10 = fArr[i8] + f8;
                    float f11 = f9094a;
                    if (f10 > (-f11) && f10 < f11) {
                        f10 = 0.0f;
                    }
                    fArr[i8] = f10;
                    if (f10 == 0.0f) {
                        if (i8 == this.f9103j) {
                            this.f9103j = this.f9101h[i8];
                        } else {
                            int[] iArr3 = this.f9101h;
                            iArr3[i10] = iArr3[i8];
                        }
                        if (z7) {
                            solverVariable.c(this.f9096c);
                        }
                        if (this.f9105l) {
                            this.f9104k = i8;
                        }
                        solverVariable.f914n--;
                        this.f9095b--;
                        return;
                    }
                    return;
                }
                if (iArr2[i8] < i13) {
                    i10 = i8;
                }
                i8 = this.f9101h[i8];
            }
            int length = this.f9104k;
            int i14 = length + 1;
            if (this.f9105l) {
                int[] iArr4 = this.f9100g;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i14;
            }
            int[] iArr5 = this.f9100g;
            if (length >= iArr5.length && this.f9095b < iArr5.length) {
                int i15 = 0;
                while (true) {
                    int[] iArr6 = this.f9100g;
                    if (i15 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i15] == -1) {
                        length = i15;
                        break;
                    }
                    i15++;
                }
            }
            int[] iArr7 = this.f9100g;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i16 = this.f9098e * 2;
                this.f9098e = i16;
                this.f9105l = false;
                this.f9104k = length - 1;
                this.f9102i = Arrays.copyOf(this.f9102i, i16);
                this.f9100g = Arrays.copyOf(this.f9100g, this.f9098e);
                this.f9101h = Arrays.copyOf(this.f9101h, this.f9098e);
            }
            this.f9100g[length] = solverVariable.f904d;
            this.f9102i[length] = f8;
            if (i10 != -1) {
                int[] iArr8 = this.f9101h;
                iArr8[length] = iArr8[i10];
                iArr8[i10] = length;
            } else {
                this.f9101h[length] = this.f9103j;
                this.f9103j = length;
            }
            solverVariable.f914n++;
            solverVariable.a(this.f9096c);
            this.f9095b++;
            if (!this.f9105l) {
                this.f9104k++;
            }
            int i17 = this.f9104k;
            int[] iArr9 = this.f9100g;
            if (i17 >= iArr9.length) {
                this.f9105l = true;
                this.f9104k = iArr9.length - 1;
            }
        }
    }

    @Override // y.b.a
    public final float c(SolverVariable solverVariable) {
        int i8 = this.f9103j;
        for (int i9 = 0; i8 != -1 && i9 < this.f9095b; i9++) {
            if (this.f9100g[i8] == solverVariable.f904d) {
                return this.f9102i[i8];
            }
            i8 = this.f9101h[i8];
        }
        return 0.0f;
    }

    @Override // y.b.a
    public final void clear() {
        int i8 = this.f9103j;
        for (int i9 = 0; i8 != -1 && i9 < this.f9095b; i9++) {
            SolverVariable solverVariable = this.f9097d.f9115d[this.f9100g[i8]];
            if (solverVariable != null) {
                solverVariable.c(this.f9096c);
            }
            i8 = this.f9101h[i8];
        }
        this.f9103j = -1;
        this.f9104k = -1;
        this.f9105l = false;
        this.f9095b = 0;
    }

    @Override // y.b.a
    public boolean d(SolverVariable solverVariable) {
        int i8 = this.f9103j;
        if (i8 == -1) {
            return false;
        }
        for (int i9 = 0; i8 != -1 && i9 < this.f9095b; i9++) {
            if (this.f9100g[i8] == solverVariable.f904d) {
                return true;
            }
            i8 = this.f9101h[i8];
        }
        return false;
    }

    @Override // y.b.a
    public int e() {
        return this.f9095b;
    }

    @Override // y.b.a
    public float f(b bVar, boolean z7) {
        float fC = c(bVar.f9106a);
        h(bVar.f9106a, z7);
        b.a aVar = bVar.f9110e;
        int iE = aVar.e();
        for (int i8 = 0; i8 < iE; i8++) {
            SolverVariable solverVariableI = aVar.i(i8);
            b(solverVariableI, aVar.c(solverVariableI) * fC, z7);
        }
        return fC;
    }

    @Override // y.b.a
    public final void g(SolverVariable solverVariable, float f8) {
        if (f8 == 0.0f) {
            h(solverVariable, true);
            return;
        }
        int i8 = this.f9103j;
        if (i8 == -1) {
            this.f9103j = 0;
            this.f9102i[0] = f8;
            this.f9100g[0] = solverVariable.f904d;
            this.f9101h[0] = -1;
            solverVariable.f914n++;
            solverVariable.a(this.f9096c);
            this.f9095b++;
            if (this.f9105l) {
                return;
            }
            int i9 = this.f9104k + 1;
            this.f9104k = i9;
            int[] iArr = this.f9100g;
            if (i9 >= iArr.length) {
                this.f9105l = true;
                this.f9104k = iArr.length - 1;
                return;
            }
            return;
        }
        int i10 = -1;
        for (int i11 = 0; i8 != -1 && i11 < this.f9095b; i11++) {
            int[] iArr2 = this.f9100g;
            int i12 = iArr2[i8];
            int i13 = solverVariable.f904d;
            if (i12 == i13) {
                this.f9102i[i8] = f8;
                return;
            }
            if (iArr2[i8] < i13) {
                i10 = i8;
            }
            i8 = this.f9101h[i8];
        }
        int length = this.f9104k;
        int i14 = length + 1;
        if (this.f9105l) {
            int[] iArr3 = this.f9100g;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i14;
        }
        int[] iArr4 = this.f9100g;
        if (length >= iArr4.length && this.f9095b < iArr4.length) {
            int i15 = 0;
            while (true) {
                int[] iArr5 = this.f9100g;
                if (i15 >= iArr5.length) {
                    break;
                }
                if (iArr5[i15] == -1) {
                    length = i15;
                    break;
                }
                i15++;
            }
        }
        int[] iArr6 = this.f9100g;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i16 = this.f9098e * 2;
            this.f9098e = i16;
            this.f9105l = false;
            this.f9104k = length - 1;
            this.f9102i = Arrays.copyOf(this.f9102i, i16);
            this.f9100g = Arrays.copyOf(this.f9100g, this.f9098e);
            this.f9101h = Arrays.copyOf(this.f9101h, this.f9098e);
        }
        this.f9100g[length] = solverVariable.f904d;
        this.f9102i[length] = f8;
        if (i10 != -1) {
            int[] iArr7 = this.f9101h;
            iArr7[length] = iArr7[i10];
            iArr7[i10] = length;
        } else {
            this.f9101h[length] = this.f9103j;
            this.f9103j = length;
        }
        solverVariable.f914n++;
        solverVariable.a(this.f9096c);
        int i17 = this.f9095b + 1;
        this.f9095b = i17;
        if (!this.f9105l) {
            this.f9104k++;
        }
        int[] iArr8 = this.f9100g;
        if (i17 >= iArr8.length) {
            this.f9105l = true;
        }
        if (this.f9104k >= iArr8.length) {
            this.f9105l = true;
            this.f9104k = iArr8.length - 1;
        }
    }

    @Override // y.b.a
    public final float h(SolverVariable solverVariable, boolean z7) {
        if (this.f9099f == solverVariable) {
            this.f9099f = null;
        }
        int i8 = this.f9103j;
        if (i8 == -1) {
            return 0.0f;
        }
        int i9 = 0;
        int i10 = -1;
        while (i8 != -1 && i9 < this.f9095b) {
            if (this.f9100g[i8] == solverVariable.f904d) {
                if (i8 == this.f9103j) {
                    this.f9103j = this.f9101h[i8];
                } else {
                    int[] iArr = this.f9101h;
                    iArr[i10] = iArr[i8];
                }
                if (z7) {
                    solverVariable.c(this.f9096c);
                }
                solverVariable.f914n--;
                this.f9095b--;
                this.f9100g[i8] = -1;
                if (this.f9105l) {
                    this.f9104k = i8;
                }
                return this.f9102i[i8];
            }
            i9++;
            i10 = i8;
            i8 = this.f9101h[i8];
        }
        return 0.0f;
    }

    @Override // y.b.a
    public SolverVariable i(int i8) {
        int i9 = this.f9103j;
        for (int i10 = 0; i9 != -1 && i10 < this.f9095b; i10++) {
            if (i10 == i8) {
                return this.f9097d.f9115d[this.f9100g[i9]];
            }
            i9 = this.f9101h[i9];
        }
        return null;
    }

    @Override // y.b.a
    public void j(float f8) {
        int i8 = this.f9103j;
        for (int i9 = 0; i8 != -1 && i9 < this.f9095b; i9++) {
            float[] fArr = this.f9102i;
            fArr[i8] = fArr[i8] / f8;
            i8 = this.f9101h[i8];
        }
    }

    @Override // y.b.a
    public void k() {
        int i8 = this.f9103j;
        for (int i9 = 0; i8 != -1 && i9 < this.f9095b; i9++) {
            float[] fArr = this.f9102i;
            fArr[i8] = fArr[i8] * (-1.0f);
            i8 = this.f9101h[i8];
        }
    }

    public String toString() {
        int i8 = this.f9103j;
        String str = "";
        for (int i9 = 0; i8 != -1 && i9 < this.f9095b; i9++) {
            str = ((str + " -> ") + this.f9102i[i8] + " : ") + this.f9097d.f9115d[this.f9100g[i8]];
            i8 = this.f9101h[i8];
        }
        return str;
    }
}
