package b.f.b;

import androidx.constraintlayout.solver.SolverVariable;
import b.f.b.b;
import java.util.Arrays;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f2205a = 0.001f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2206b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2207c = 16;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2208d = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f2209e = new int[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f2210f = new int[16];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f2211g = new int[16];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f2212h = new float[16];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f2213i = new int[16];
    public int[] j = new int[16];
    public int k = 0;
    public int l = -1;
    public final b m;
    public final c n;

    public i(b bVar, c cVar) {
        this.m = bVar;
        this.n = cVar;
        clear();
    }

    @Override // b.f.b.b.a
    public float a(int i2) {
        int i3 = this.k;
        int i4 = this.l;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2) {
                return this.f2212h[i4];
            }
            i4 = this.j[i4];
            if (i4 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // b.f.b.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f2176a);
        d(bVar.f2176a, z);
        i iVar = (i) bVar.f2180e;
        int iK = iVar.k();
        int i2 = 0;
        int i3 = 0;
        while (i2 < iK) {
            int[] iArr = iVar.f2211g;
            if (iArr[i3] != -1) {
                e(this.n.f2185d[iArr[i3]], iVar.f2212h[i3] * fG, z);
                i2++;
            }
            i3++;
        }
        return fG;
    }

    @Override // b.f.b.b.a
    public void c(SolverVariable solverVariable, float f2) {
        float f3 = f2205a;
        if (f2 > (-f3) && f2 < f3) {
            d(solverVariable, true);
            return;
        }
        if (this.k == 0) {
            m(0, solverVariable, f2);
            l(solverVariable, 0);
            this.l = 0;
            return;
        }
        int iP = p(solverVariable);
        if (iP != -1) {
            this.f2212h[iP] = f2;
            return;
        }
        if (this.k + 1 >= this.f2207c) {
            o();
        }
        int i2 = this.k;
        int i3 = this.l;
        int i4 = -1;
        for (int i5 = 0; i5 < i2; i5++) {
            int[] iArr = this.f2211g;
            int i6 = iArr[i3];
            int i7 = solverVariable.f611d;
            if (i6 == i7) {
                this.f2212h[i3] = f2;
                return;
            }
            if (iArr[i3] < i7) {
                i4 = i3;
            }
            i3 = this.j[i3];
            if (i3 == -1) {
                break;
            }
        }
        q(i4, solverVariable, f2);
    }

    @Override // b.f.b.b.a
    public void clear() {
        int i2 = this.k;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable solverVariableF = f(i3);
            if (solverVariableF != null) {
                solverVariableF.c(this.m);
            }
        }
        for (int i4 = 0; i4 < this.f2207c; i4++) {
            this.f2211g[i4] = -1;
            this.f2210f[i4] = -1;
        }
        for (int i5 = 0; i5 < this.f2208d; i5++) {
            this.f2209e[i5] = -1;
        }
        this.k = 0;
        this.l = -1;
    }

    @Override // b.f.b.b.a
    public float d(SolverVariable solverVariable, boolean z) {
        int iP = p(solverVariable);
        if (iP == -1) {
            return 0.0f;
        }
        r(solverVariable);
        float f2 = this.f2212h[iP];
        if (this.l == iP) {
            this.l = this.j[iP];
        }
        this.f2211g[iP] = -1;
        int[] iArr = this.f2213i;
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
        return f2;
    }

    @Override // b.f.b.b.a
    public void e(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = f2205a;
        if (f2 <= (-f3) || f2 >= f3) {
            int iP = p(solverVariable);
            if (iP == -1) {
                c(solverVariable, f2);
                return;
            }
            float[] fArr = this.f2212h;
            fArr[iP] = fArr[iP] + f2;
            float f4 = fArr[iP];
            float f5 = f2205a;
            if (f4 <= (-f5) || fArr[iP] >= f5) {
                return;
            }
            fArr[iP] = 0.0f;
            d(solverVariable, z);
        }
    }

    @Override // b.f.b.b.a
    public SolverVariable f(int i2) {
        int i3 = this.k;
        if (i3 == 0) {
            return null;
        }
        int i4 = this.l;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2 && i4 != -1) {
                return this.n.f2185d[this.f2211g[i4]];
            }
            i4 = this.j[i4];
            if (i4 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // b.f.b.b.a
    public float g(SolverVariable solverVariable) {
        int iP = p(solverVariable);
        if (iP != -1) {
            return this.f2212h[iP];
        }
        return 0.0f;
    }

    @Override // b.f.b.b.a
    public boolean h(SolverVariable solverVariable) {
        return p(solverVariable) != -1;
    }

    @Override // b.f.b.b.a
    public void i(float f2) {
        int i2 = this.k;
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f2212h;
            fArr[i3] = fArr[i3] / f2;
            i3 = this.j[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // b.f.b.b.a
    public void j() {
        int i2 = this.k;
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f2212h;
            fArr[i3] = fArr[i3] * (-1.0f);
            i3 = this.j[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // b.f.b.b.a
    public int k() {
        return this.k;
    }

    public final void l(SolverVariable solverVariable, int i2) {
        int[] iArr;
        int i3 = solverVariable.f611d % this.f2208d;
        int[] iArr2 = this.f2209e;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            iArr2[i3] = i2;
        } else {
            while (true) {
                iArr = this.f2210f;
                if (iArr[i4] == -1) {
                    break;
                } else {
                    i4 = iArr[i4];
                }
            }
            iArr[i4] = i2;
        }
        this.f2210f[i2] = -1;
    }

    public final void m(int i2, SolverVariable solverVariable, float f2) {
        this.f2211g[i2] = solverVariable.f611d;
        this.f2212h[i2] = f2;
        this.f2213i[i2] = -1;
        this.j[i2] = -1;
        solverVariable.a(this.m);
        solverVariable.n++;
        this.k++;
    }

    public final int n() {
        for (int i2 = 0; i2 < this.f2207c; i2++) {
            if (this.f2211g[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }

    public final void o() {
        int i2 = this.f2207c * 2;
        this.f2211g = Arrays.copyOf(this.f2211g, i2);
        this.f2212h = Arrays.copyOf(this.f2212h, i2);
        this.f2213i = Arrays.copyOf(this.f2213i, i2);
        this.j = Arrays.copyOf(this.j, i2);
        this.f2210f = Arrays.copyOf(this.f2210f, i2);
        for (int i3 = this.f2207c; i3 < i2; i3++) {
            this.f2211g[i3] = -1;
            this.f2210f[i3] = -1;
        }
        this.f2207c = i2;
    }

    public int p(SolverVariable solverVariable) {
        int[] iArr;
        if (this.k == 0) {
            return -1;
        }
        int i2 = solverVariable.f611d;
        int i3 = this.f2209e[i2 % this.f2208d];
        if (i3 == -1) {
            return -1;
        }
        if (this.f2211g[i3] == i2) {
            return i3;
        }
        while (true) {
            iArr = this.f2210f;
            if (iArr[i3] == -1 || this.f2211g[iArr[i3]] == i2) {
                break;
            }
            i3 = iArr[i3];
        }
        if (iArr[i3] != -1 && this.f2211g[iArr[i3]] == i2) {
            return iArr[i3];
        }
        return -1;
    }

    public final void q(int i2, SolverVariable solverVariable, float f2) {
        int iN = n();
        m(iN, solverVariable, f2);
        if (i2 != -1) {
            this.f2213i[iN] = i2;
            int[] iArr = this.j;
            iArr[iN] = iArr[i2];
            iArr[i2] = iN;
        } else {
            this.f2213i[iN] = -1;
            if (this.k > 0) {
                this.j[iN] = this.l;
                this.l = iN;
            } else {
                this.j[iN] = -1;
            }
        }
        int[] iArr2 = this.j;
        if (iArr2[iN] != -1) {
            this.f2213i[iArr2[iN]] = iN;
        }
        l(solverVariable, iN);
    }

    public final void r(SolverVariable solverVariable) {
        int[] iArr;
        int i2 = solverVariable.f611d;
        int i3 = i2 % this.f2208d;
        int[] iArr2 = this.f2209e;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            return;
        }
        if (this.f2211g[i4] == i2) {
            int[] iArr3 = this.f2210f;
            iArr2[i3] = iArr3[i4];
            iArr3[i4] = -1;
            return;
        }
        while (true) {
            iArr = this.f2210f;
            if (iArr[i4] == -1 || this.f2211g[iArr[i4]] == i2) {
                break;
            } else {
                i4 = iArr[i4];
            }
        }
        int i5 = iArr[i4];
        if (i5 == -1 || this.f2211g[i5] != i2) {
            return;
        }
        iArr[i4] = iArr[i5];
        iArr[i5] = -1;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i2 = this.k;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable solverVariableF = f(i3);
            if (solverVariableF != null) {
                String str2 = str + solverVariableF + " = " + a(i3) + " ";
                int iP = p(solverVariableF);
                String str3 = str2 + "[p: ";
                String str4 = (this.f2213i[iP] != -1 ? str3 + this.n.f2185d[this.f2211g[this.f2213i[iP]]] : str3 + "none") + ", n: ";
                str = (this.j[iP] != -1 ? str4 + this.n.f2185d[this.f2211g[this.j[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
