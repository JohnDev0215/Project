package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.b;
import java.util.Arrays;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f949a = 0.001f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f950b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f951c = 16;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f952d = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int[] f953e = new int[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int[] f954f = new int[16];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int[] f955g = new int[16];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float[] f956h = new float[16];
    int[] i = new int[16];
    int[] j = new int[16];
    int k = 0;
    int l = -1;
    private final b m;
    protected final c n;

    i(b bVar, c cVar) {
        this.m = bVar;
        this.n = cVar;
        clear();
    }

    private void l(SolverVariable solverVariable, int i) {
        int[] iArr;
        int i2 = solverVariable.f908d % this.f952d;
        int[] iArr2 = this.f953e;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            iArr2[i2] = i;
        } else {
            while (true) {
                iArr = this.f954f;
                if (iArr[i3] == -1) {
                    break;
                } else {
                    i3 = iArr[i3];
                }
            }
            iArr[i3] = i;
        }
        this.f954f[i] = -1;
    }

    private void m(int i, SolverVariable solverVariable, float f2) {
        this.f955g[i] = solverVariable.f908d;
        this.f956h[i] = f2;
        this.i[i] = -1;
        this.j[i] = -1;
        solverVariable.a(this.m);
        solverVariable.n++;
        this.k++;
    }

    private int n() {
        for (int i = 0; i < this.f951c; i++) {
            if (this.f955g[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    private void o() {
        int i = this.f951c * 2;
        this.f955g = Arrays.copyOf(this.f955g, i);
        this.f956h = Arrays.copyOf(this.f956h, i);
        this.i = Arrays.copyOf(this.i, i);
        this.j = Arrays.copyOf(this.j, i);
        this.f954f = Arrays.copyOf(this.f954f, i);
        for (int i2 = this.f951c; i2 < i; i2++) {
            this.f955g[i2] = -1;
            this.f954f[i2] = -1;
        }
        this.f951c = i;
    }

    private void q(int i, SolverVariable solverVariable, float f2) {
        int iN = n();
        m(iN, solverVariable, f2);
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

    private void r(SolverVariable solverVariable) {
        int[] iArr;
        int i = solverVariable.f908d;
        int i2 = i % this.f952d;
        int[] iArr2 = this.f953e;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            return;
        }
        if (this.f955g[i3] == i) {
            int[] iArr3 = this.f954f;
            iArr2[i2] = iArr3[i3];
            iArr3[i3] = -1;
            return;
        }
        while (true) {
            iArr = this.f954f;
            if (iArr[i3] == -1 || this.f955g[iArr[i3]] == i) {
                break;
            } else {
                i3 = iArr[i3];
            }
        }
        int i4 = iArr[i3];
        if (i4 == -1 || this.f955g[i4] != i) {
            return;
        }
        iArr[i3] = iArr[i4];
        iArr[i4] = -1;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float a(int i) {
        int i2 = this.k;
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i) {
                return this.f956h[i3];
            }
            i3 = this.j[i3];
            if (i3 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f922a);
        d(bVar.f922a, z);
        i iVar = (i) bVar.f926e;
        int iK = iVar.k();
        int i = 0;
        int i2 = 0;
        while (i < iK) {
            int[] iArr = iVar.f955g;
            if (iArr[i2] != -1) {
                e(this.n.f931d[iArr[i2]], iVar.f956h[i2] * fG, z);
                i++;
            }
            i2++;
        }
        return fG;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void c(SolverVariable solverVariable, float f2) {
        float f3 = f949a;
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
            this.f956h[iP] = f2;
            return;
        }
        if (this.k + 1 >= this.f951c) {
            o();
        }
        int i = this.k;
        int i2 = this.l;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            int[] iArr = this.f955g;
            int i5 = iArr[i2];
            int i6 = solverVariable.f908d;
            if (i5 == i6) {
                this.f956h[i2] = f2;
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
        q(i3, solverVariable, f2);
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void clear() {
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable solverVariableF = f(i2);
            if (solverVariableF != null) {
                solverVariableF.c(this.m);
            }
        }
        for (int i3 = 0; i3 < this.f951c; i3++) {
            this.f955g[i3] = -1;
            this.f954f[i3] = -1;
        }
        for (int i4 = 0; i4 < this.f952d; i4++) {
            this.f953e[i4] = -1;
        }
        this.k = 0;
        this.l = -1;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float d(SolverVariable solverVariable, boolean z) {
        int iP = p(solverVariable);
        if (iP == -1) {
            return 0.0f;
        }
        r(solverVariable);
        float f2 = this.f956h[iP];
        if (this.l == iP) {
            this.l = this.j[iP];
        }
        this.f955g[iP] = -1;
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
        return f2;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void e(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = f949a;
        if (f2 <= (-f3) || f2 >= f3) {
            int iP = p(solverVariable);
            if (iP == -1) {
                c(solverVariable, f2);
                return;
            }
            float[] fArr = this.f956h;
            fArr[iP] = fArr[iP] + f2;
            float f4 = fArr[iP];
            float f5 = f949a;
            if (f4 <= (-f5) || fArr[iP] >= f5) {
                return;
            }
            fArr[iP] = 0.0f;
            d(solverVariable, z);
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public SolverVariable f(int i) {
        int i2 = this.k;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return this.n.f931d[this.f955g[i3]];
            }
            i3 = this.j[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float g(SolverVariable solverVariable) {
        int iP = p(solverVariable);
        if (iP != -1) {
            return this.f956h[iP];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public boolean h(SolverVariable solverVariable) {
        return p(solverVariable) != -1;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void i(float f2) {
        int i = this.k;
        int i2 = this.l;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.f956h;
            fArr[i2] = fArr[i2] / f2;
            i2 = this.j[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void j() {
        int i = this.k;
        int i2 = this.l;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.f956h;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.j[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public int k() {
        return this.k;
    }

    public int p(SolverVariable solverVariable) {
        int[] iArr;
        if (this.k == 0) {
            return -1;
        }
        int i = solverVariable.f908d;
        int i2 = this.f953e[i % this.f952d];
        if (i2 == -1) {
            return -1;
        }
        if (this.f955g[i2] == i) {
            return i2;
        }
        while (true) {
            iArr = this.f954f;
            if (iArr[i2] == -1 || this.f955g[iArr[i2]] == i) {
                break;
            }
            i2 = iArr[i2];
        }
        if (iArr[i2] != -1 && this.f955g[iArr[i2]] == i) {
            return iArr[i2];
        }
        return -1;
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
                String str4 = (this.i[iP] != -1 ? str3 + this.n.f931d[this.f955g[this.i[iP]]] : str3 + "none") + ", n: ";
                str = (this.j[iP] != -1 ? str4 + this.n.f931d[this.f955g[this.j[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
