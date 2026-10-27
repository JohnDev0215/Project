package v;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.Arrays;
import v.b;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f5405a = 0.001f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5406b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5407c = 16;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5408d = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f5409e = new int[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f5410f = new int[16];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f5411g = new int[16];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f5412h = new float[16];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f5413i = new int[16];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f5414j = new int[16];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f5415k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5416l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f5417m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f5418n;

    public i(b bVar, c cVar) {
        this.f5417m = bVar;
        this.f5418n = cVar;
        clear();
    }

    @Override // v.b.a
    public float a(int i3) {
        int i4 = this.f5415k;
        int i5 = this.f5416l;
        for (int i6 = 0; i6 < i4; i6++) {
            if (i6 == i3) {
                return this.f5412h[i5];
            }
            i5 = this.f5414j[i5];
            if (i5 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // v.b.a
    public float b(b bVar, boolean z3) {
        float fG = g(bVar.f5361a);
        d(bVar.f5361a, z3);
        i iVar = (i) bVar.f5365e;
        int iK = iVar.k();
        int i3 = 0;
        int i4 = 0;
        while (i3 < iK) {
            int[] iArr = iVar.f5411g;
            if (iArr[i4] != -1) {
                e(this.f5418n.f5370d[iArr[i4]], iVar.f5412h[i4] * fG, z3);
                i3++;
            }
            i4++;
        }
        return fG;
    }

    @Override // v.b.a
    public void c(SolverVariable solverVariable, float f3) {
        float f4 = f5405a;
        if (f3 > (-f4) && f3 < f4) {
            d(solverVariable, true);
            return;
        }
        if (this.f5415k == 0) {
            m(0, solverVariable, f3);
            l(solverVariable, 0);
            this.f5416l = 0;
            return;
        }
        int iP = p(solverVariable);
        if (iP != -1) {
            this.f5412h[iP] = f3;
            return;
        }
        if (this.f5415k + 1 >= this.f5407c) {
            o();
        }
        int i3 = this.f5415k;
        int i4 = this.f5416l;
        int i5 = -1;
        for (int i6 = 0; i6 < i3; i6++) {
            int[] iArr = this.f5411g;
            int i7 = iArr[i4];
            int i8 = solverVariable.f813d;
            if (i7 == i8) {
                this.f5412h[i4] = f3;
                return;
            }
            if (iArr[i4] < i8) {
                i5 = i4;
            }
            i4 = this.f5414j[i4];
            if (i4 == -1) {
                break;
            }
        }
        q(i5, solverVariable, f3);
    }

    @Override // v.b.a
    public void clear() {
        int i3 = this.f5415k;
        for (int i4 = 0; i4 < i3; i4++) {
            SolverVariable solverVariableF = f(i4);
            if (solverVariableF != null) {
                solverVariableF.c(this.f5417m);
            }
        }
        for (int i5 = 0; i5 < this.f5407c; i5++) {
            this.f5411g[i5] = -1;
            this.f5410f[i5] = -1;
        }
        for (int i6 = 0; i6 < this.f5408d; i6++) {
            this.f5409e[i6] = -1;
        }
        this.f5415k = 0;
        this.f5416l = -1;
    }

    @Override // v.b.a
    public float d(SolverVariable solverVariable, boolean z3) {
        int iP = p(solverVariable);
        if (iP == -1) {
            return 0.0f;
        }
        r(solverVariable);
        float f3 = this.f5412h[iP];
        if (this.f5416l == iP) {
            this.f5416l = this.f5414j[iP];
        }
        this.f5411g[iP] = -1;
        int[] iArr = this.f5413i;
        if (iArr[iP] != -1) {
            int[] iArr2 = this.f5414j;
            iArr2[iArr[iP]] = iArr2[iP];
        }
        int[] iArr3 = this.f5414j;
        if (iArr3[iP] != -1) {
            iArr[iArr3[iP]] = iArr[iP];
        }
        this.f5415k--;
        solverVariable.f823n--;
        if (z3) {
            solverVariable.c(this.f5417m);
        }
        return f3;
    }

    @Override // v.b.a
    public void e(SolverVariable solverVariable, float f3, boolean z3) {
        float f4 = f5405a;
        if (f3 <= (-f4) || f3 >= f4) {
            int iP = p(solverVariable);
            if (iP == -1) {
                c(solverVariable, f3);
                return;
            }
            float[] fArr = this.f5412h;
            fArr[iP] = fArr[iP] + f3;
            float f5 = fArr[iP];
            float f6 = f5405a;
            if (f5 <= (-f6) || fArr[iP] >= f6) {
                return;
            }
            fArr[iP] = 0.0f;
            d(solverVariable, z3);
        }
    }

    @Override // v.b.a
    public SolverVariable f(int i3) {
        int i4 = this.f5415k;
        if (i4 == 0) {
            return null;
        }
        int i5 = this.f5416l;
        for (int i6 = 0; i6 < i4; i6++) {
            if (i6 == i3 && i5 != -1) {
                return this.f5418n.f5370d[this.f5411g[i5]];
            }
            i5 = this.f5414j[i5];
            if (i5 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // v.b.a
    public float g(SolverVariable solverVariable) {
        int iP = p(solverVariable);
        if (iP != -1) {
            return this.f5412h[iP];
        }
        return 0.0f;
    }

    @Override // v.b.a
    public boolean h(SolverVariable solverVariable) {
        return p(solverVariable) != -1;
    }

    @Override // v.b.a
    public void i(float f3) {
        int i3 = this.f5415k;
        int i4 = this.f5416l;
        for (int i5 = 0; i5 < i3; i5++) {
            float[] fArr = this.f5412h;
            fArr[i4] = fArr[i4] / f3;
            i4 = this.f5414j[i4];
            if (i4 == -1) {
                return;
            }
        }
    }

    @Override // v.b.a
    public void j() {
        int i3 = this.f5415k;
        int i4 = this.f5416l;
        for (int i5 = 0; i5 < i3; i5++) {
            float[] fArr = this.f5412h;
            fArr[i4] = fArr[i4] * (-1.0f);
            i4 = this.f5414j[i4];
            if (i4 == -1) {
                return;
            }
        }
    }

    @Override // v.b.a
    public int k() {
        return this.f5415k;
    }

    public final void l(SolverVariable solverVariable, int i3) {
        int[] iArr;
        int i4 = solverVariable.f813d % this.f5408d;
        int[] iArr2 = this.f5409e;
        int i5 = iArr2[i4];
        if (i5 == -1) {
            iArr2[i4] = i3;
        } else {
            while (true) {
                iArr = this.f5410f;
                if (iArr[i5] == -1) {
                    break;
                } else {
                    i5 = iArr[i5];
                }
            }
            iArr[i5] = i3;
        }
        this.f5410f[i3] = -1;
    }

    public final void m(int i3, SolverVariable solverVariable, float f3) {
        this.f5411g[i3] = solverVariable.f813d;
        this.f5412h[i3] = f3;
        this.f5413i[i3] = -1;
        this.f5414j[i3] = -1;
        solverVariable.a(this.f5417m);
        solverVariable.f823n++;
        this.f5415k++;
    }

    public final int n() {
        for (int i3 = 0; i3 < this.f5407c; i3++) {
            if (this.f5411g[i3] == -1) {
                return i3;
            }
        }
        return -1;
    }

    public final void o() {
        int i3 = this.f5407c * 2;
        this.f5411g = Arrays.copyOf(this.f5411g, i3);
        this.f5412h = Arrays.copyOf(this.f5412h, i3);
        this.f5413i = Arrays.copyOf(this.f5413i, i3);
        this.f5414j = Arrays.copyOf(this.f5414j, i3);
        this.f5410f = Arrays.copyOf(this.f5410f, i3);
        for (int i4 = this.f5407c; i4 < i3; i4++) {
            this.f5411g[i4] = -1;
            this.f5410f[i4] = -1;
        }
        this.f5407c = i3;
    }

    public int p(SolverVariable solverVariable) {
        int[] iArr;
        if (this.f5415k == 0) {
            return -1;
        }
        int i3 = solverVariable.f813d;
        int i4 = this.f5409e[i3 % this.f5408d];
        if (i4 == -1) {
            return -1;
        }
        if (this.f5411g[i4] == i3) {
            return i4;
        }
        while (true) {
            iArr = this.f5410f;
            if (iArr[i4] == -1 || this.f5411g[iArr[i4]] == i3) {
                break;
            }
            i4 = iArr[i4];
        }
        if (iArr[i4] != -1 && this.f5411g[iArr[i4]] == i3) {
            return iArr[i4];
        }
        return -1;
    }

    public final void q(int i3, SolverVariable solverVariable, float f3) {
        int iN = n();
        m(iN, solverVariable, f3);
        if (i3 != -1) {
            this.f5413i[iN] = i3;
            int[] iArr = this.f5414j;
            iArr[iN] = iArr[i3];
            iArr[i3] = iN;
        } else {
            this.f5413i[iN] = -1;
            if (this.f5415k > 0) {
                this.f5414j[iN] = this.f5416l;
                this.f5416l = iN;
            } else {
                this.f5414j[iN] = -1;
            }
        }
        int[] iArr2 = this.f5414j;
        if (iArr2[iN] != -1) {
            this.f5413i[iArr2[iN]] = iN;
        }
        l(solverVariable, iN);
    }

    public final void r(SolverVariable solverVariable) {
        int[] iArr;
        int i3 = solverVariable.f813d;
        int i4 = i3 % this.f5408d;
        int[] iArr2 = this.f5409e;
        int i5 = iArr2[i4];
        if (i5 == -1) {
            return;
        }
        if (this.f5411g[i5] == i3) {
            int[] iArr3 = this.f5410f;
            iArr2[i4] = iArr3[i5];
            iArr3[i5] = -1;
            return;
        }
        while (true) {
            iArr = this.f5410f;
            if (iArr[i5] == -1 || this.f5411g[iArr[i5]] == i3) {
                break;
            } else {
                i5 = iArr[i5];
            }
        }
        int i6 = iArr[i5];
        if (i6 == -1 || this.f5411g[i6] != i3) {
            return;
        }
        iArr[i5] = iArr[i6];
        iArr[i6] = -1;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i3 = this.f5415k;
        for (int i4 = 0; i4 < i3; i4++) {
            SolverVariable solverVariableF = f(i4);
            if (solverVariableF != null) {
                String str2 = str + solverVariableF + " = " + a(i4) + " ";
                int iP = p(solverVariableF);
                String str3 = str2 + "[p: ";
                String str4 = (this.f5413i[iP] != -1 ? str3 + this.f5418n.f5370d[this.f5411g[this.f5413i[iP]]] : str3 + "none") + ", n: ";
                str = (this.f5414j[iP] != -1 ? str4 + this.f5418n.f5370d[this.f5411g[this.f5414j[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
