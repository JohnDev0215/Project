package defpackage;

import androidx.constraintlayout.solver.SolverVariable;
import defpackage.l5;
import java.util.Arrays;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class s5 implements l5.a {
    public static float a = 0.001f;
    public final int b = -1;
    public int c = 16;
    public int d = 16;
    public int[] e = new int[16];
    public int[] f = new int[16];
    public int[] g = new int[16];
    public float[] h = new float[16];
    public int[] i = new int[16];
    public int[] j = new int[16];
    public int k = 0;
    public int l = -1;
    public final l5 m;
    public final m5 n;

    public s5(l5 l5Var, m5 m5Var) {
        this.m = l5Var;
        this.n = m5Var;
        clear();
    }

    @Override // l5.a
    public float a(int i) {
        int i2 = this.k;
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i) {
                return this.h[i3];
            }
            i3 = this.j[i3];
            if (i3 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // l5.a
    public float b(l5 l5Var, boolean z) {
        float fG = g(l5Var.a);
        d(l5Var.a, z);
        s5 s5Var = (s5) l5Var.e;
        int iK = s5Var.k();
        int i = 0;
        int i2 = 0;
        while (i < iK) {
            int[] iArr = s5Var.g;
            if (iArr[i2] != -1) {
                e(this.n.d[iArr[i2]], s5Var.h[i2] * fG, z);
                i++;
            }
            i2++;
        }
        return fG;
    }

    @Override // l5.a
    public void c(SolverVariable solverVariable, float f) {
        float f2 = a;
        if (f > (-f2) && f < f2) {
            d(solverVariable, true);
            return;
        }
        if (this.k == 0) {
            m(0, solverVariable, f);
            l(solverVariable, 0);
            this.l = 0;
            return;
        }
        int iP = p(solverVariable);
        if (iP != -1) {
            this.h[iP] = f;
            return;
        }
        if (this.k + 1 >= this.c) {
            o();
        }
        int i = this.k;
        int i2 = this.l;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            int[] iArr = this.g;
            int i5 = iArr[i2];
            int i6 = solverVariable.d;
            if (i5 == i6) {
                this.h[i2] = f;
                return;
            }
            if (iArr[i2] < i6) {
                i3 = i2;
            }
            i2 = this.j[i2];
            if (i2 == -1) {
                break;
            }
        }
        q(i3, solverVariable, f);
    }

    @Override // l5.a
    public void clear() {
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable solverVariableF = f(i2);
            if (solverVariableF != null) {
                solverVariableF.c(this.m);
            }
        }
        for (int i3 = 0; i3 < this.c; i3++) {
            this.g[i3] = -1;
            this.f[i3] = -1;
        }
        for (int i4 = 0; i4 < this.d; i4++) {
            this.e[i4] = -1;
        }
        this.k = 0;
        this.l = -1;
    }

    @Override // l5.a
    public float d(SolverVariable solverVariable, boolean z) {
        int iP = p(solverVariable);
        if (iP == -1) {
            return 0.0f;
        }
        r(solverVariable);
        float f = this.h[iP];
        if (this.l == iP) {
            this.l = this.j[iP];
        }
        this.g[iP] = -1;
        int[] iArr = this.i;
        if (iArr[iP] != -1) {
            int[] iArr2 = this.j;
            iArr2[iArr[iP]] = iArr2[iP];
        }
        int[] iArr3 = this.j;
        if (iArr3[iP] != -1) {
            iArr[iArr3[iP]] = iArr[iP];
        }
        this.k--;
        solverVariable.n--;
        if (z) {
            solverVariable.c(this.m);
        }
        return f;
    }

    @Override // l5.a
    public void e(SolverVariable solverVariable, float f, boolean z) {
        float f2 = a;
        if (f <= (-f2) || f >= f2) {
            int iP = p(solverVariable);
            if (iP == -1) {
                c(solverVariable, f);
                return;
            }
            float[] fArr = this.h;
            fArr[iP] = fArr[iP] + f;
            float f3 = fArr[iP];
            float f4 = a;
            if (f3 <= (-f4) || fArr[iP] >= f4) {
                return;
            }
            fArr[iP] = 0.0f;
            d(solverVariable, z);
        }
    }

    @Override // l5.a
    public SolverVariable f(int i) {
        int i2 = this.k;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return this.n.d[this.g[i3]];
            }
            i3 = this.j[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // l5.a
    public float g(SolverVariable solverVariable) {
        int iP = p(solverVariable);
        if (iP != -1) {
            return this.h[iP];
        }
        return 0.0f;
    }

    @Override // l5.a
    public boolean h(SolverVariable solverVariable) {
        return p(solverVariable) != -1;
    }

    @Override // l5.a
    public void i(float f) {
        int i = this.k;
        int i2 = this.l;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.h;
            fArr[i2] = fArr[i2] / f;
            i2 = this.j[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // l5.a
    public void j() {
        int i = this.k;
        int i2 = this.l;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.h;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.j[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // l5.a
    public int k() {
        return this.k;
    }

    public final void l(SolverVariable solverVariable, int i) {
        int[] iArr;
        int i2 = solverVariable.d % this.d;
        int[] iArr2 = this.e;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            iArr2[i2] = i;
        } else {
            while (true) {
                iArr = this.f;
                if (iArr[i3] == -1) {
                    break;
                } else {
                    i3 = iArr[i3];
                }
            }
            iArr[i3] = i;
        }
        this.f[i] = -1;
    }

    public final void m(int i, SolverVariable solverVariable, float f) {
        this.g[i] = solverVariable.d;
        this.h[i] = f;
        this.i[i] = -1;
        this.j[i] = -1;
        solverVariable.a(this.m);
        solverVariable.n++;
        this.k++;
    }

    public final int n() {
        for (int i = 0; i < this.c; i++) {
            if (this.g[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public final void o() {
        int i = this.c * 2;
        this.g = Arrays.copyOf(this.g, i);
        this.h = Arrays.copyOf(this.h, i);
        this.i = Arrays.copyOf(this.i, i);
        this.j = Arrays.copyOf(this.j, i);
        this.f = Arrays.copyOf(this.f, i);
        for (int i2 = this.c; i2 < i; i2++) {
            this.g[i2] = -1;
            this.f[i2] = -1;
        }
        this.c = i;
    }

    public int p(SolverVariable solverVariable) {
        int[] iArr;
        if (this.k == 0) {
            return -1;
        }
        int i = solverVariable.d;
        int i2 = this.e[i % this.d];
        if (i2 == -1) {
            return -1;
        }
        if (this.g[i2] == i) {
            return i2;
        }
        while (true) {
            iArr = this.f;
            if (iArr[i2] == -1 || this.g[iArr[i2]] == i) {
                break;
            }
            i2 = iArr[i2];
        }
        if (iArr[i2] != -1 && this.g[iArr[i2]] == i) {
            return iArr[i2];
        }
        return -1;
    }

    public final void q(int i, SolverVariable solverVariable, float f) {
        int iN = n();
        m(iN, solverVariable, f);
        if (i != -1) {
            this.i[iN] = i;
            int[] iArr = this.j;
            iArr[iN] = iArr[i];
            iArr[i] = iN;
        } else {
            this.i[iN] = -1;
            if (this.k > 0) {
                this.j[iN] = this.l;
                this.l = iN;
            } else {
                this.j[iN] = -1;
            }
        }
        int[] iArr2 = this.j;
        if (iArr2[iN] != -1) {
            this.i[iArr2[iN]] = iN;
        }
        l(solverVariable, iN);
    }

    public final void r(SolverVariable solverVariable) {
        int[] iArr;
        int i = solverVariable.d;
        int i2 = i % this.d;
        int[] iArr2 = this.e;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            return;
        }
        if (this.g[i3] == i) {
            int[] iArr3 = this.f;
            iArr2[i2] = iArr3[i3];
            iArr3[i3] = -1;
            return;
        }
        while (true) {
            iArr = this.f;
            if (iArr[i3] == -1 || this.g[iArr[i3]] == i) {
                break;
            } else {
                i3 = iArr[i3];
            }
        }
        int i4 = iArr[i3];
        if (i4 == -1 || this.g[i4] != i) {
            return;
        }
        iArr[i3] = iArr[i4];
        iArr[i4] = -1;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable solverVariableF = f(i2);
            if (solverVariableF != null) {
                String str2 = str + solverVariableF + " = " + a(i2) + " ";
                int iP = p(solverVariableF);
                String str3 = str2 + "[p: ";
                String str4 = (this.i[iP] != -1 ? str3 + this.n.d[this.g[this.i[iP]]] : str3 + "none") + ", n: ";
                str = (this.j[iP] != -1 ? str4 + this.n.d[this.g[this.j[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
