package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.b;
import java.util.Arrays;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f1165a = 0.001f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f1166b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1167c = 16;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1168d = 16;
    int[] e = new int[16];
    int[] f = new int[16];
    int[] g = new int[16];
    float[] h = new float[16];
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
        int i2 = solverVariable.f1141d % this.f1168d;
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

    private void m(int i, SolverVariable solverVariable, float f) {
        this.g[i] = solverVariable.f1141d;
        this.h[i] = f;
        this.i[i] = -1;
        this.j[i] = -1;
        solverVariable.a(this.m);
        solverVariable.n++;
        this.k++;
    }

    private int n() {
        for (int i = 0; i < this.f1167c; i++) {
            if (this.g[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    private void o() {
        int i = this.f1167c * 2;
        this.g = Arrays.copyOf(this.g, i);
        this.h = Arrays.copyOf(this.h, i);
        this.i = Arrays.copyOf(this.i, i);
        this.j = Arrays.copyOf(this.j, i);
        this.f = Arrays.copyOf(this.f, i);
        for (int i2 = this.f1167c; i2 < i; i2++) {
            this.g[i2] = -1;
            this.f[i2] = -1;
        }
        this.f1167c = i;
    }

    private void q(int i, SolverVariable solverVariable, float f) {
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

    private void r(SolverVariable solverVariable) {
        int[] iArr;
        int i = solverVariable.f1141d;
        int i2 = i % this.f1168d;
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

    @Override // androidx.constraintlayout.solver.b.a
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

    @Override // androidx.constraintlayout.solver.b.a
    public void b(SolverVariable solverVariable, float f, boolean z) {
        float f2 = f1165a;
        if (f <= (-f2) || f >= f2) {
            int iP = p(solverVariable);
            if (iP == -1) {
                g(solverVariable, f);
                return;
            }
            float[] fArr = this.h;
            fArr[iP] = fArr[iP] + f;
            float f3 = fArr[iP];
            float f4 = f1165a;
            if (f3 <= (-f4) || fArr[iP] >= f4) {
                return;
            }
            fArr[iP] = 0.0f;
            h(solverVariable, z);
        }
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float c(SolverVariable solverVariable) {
        int iP = p(solverVariable);
        if (iP != -1) {
            return this.h[iP];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void clear() {
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable solverVariableI = i(i2);
            if (solverVariableI != null) {
                solverVariableI.c(this.m);
            }
        }
        for (int i3 = 0; i3 < this.f1167c; i3++) {
            this.g[i3] = -1;
            this.f[i3] = -1;
        }
        for (int i4 = 0; i4 < this.f1168d; i4++) {
            this.e[i4] = -1;
        }
        this.k = 0;
        this.l = -1;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public boolean d(SolverVariable solverVariable) {
        return p(solverVariable) != -1;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public int e() {
        return this.k;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public float f(b bVar, boolean z) {
        float fC = c(bVar.f1147a);
        h(bVar.f1147a, z);
        i iVar = (i) bVar.e;
        int iE = iVar.e();
        int i = 0;
        int i2 = 0;
        while (i < iE) {
            int[] iArr = iVar.g;
            if (iArr[i2] != -1) {
                b(this.n.f1154d[iArr[i2]], iVar.h[i2] * fC, z);
                i++;
            }
            i2++;
        }
        return fC;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void g(SolverVariable solverVariable, float f) {
        float f2 = f1165a;
        if (f > (-f2) && f < f2) {
            h(solverVariable, true);
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
        if (this.k + 1 >= this.f1167c) {
            o();
        }
        int i = this.k;
        int i2 = this.l;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            int[] iArr = this.g;
            int i5 = iArr[i2];
            int i6 = solverVariable.f1141d;
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

    @Override // androidx.constraintlayout.solver.b.a
    public float h(SolverVariable solverVariable, boolean z) {
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

    @Override // androidx.constraintlayout.solver.b.a
    public SolverVariable i(int i) {
        int i2 = this.k;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return this.n.f1154d[this.g[i3]];
            }
            i3 = this.j[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.b.a
    public void j(float f) {
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

    @Override // androidx.constraintlayout.solver.b.a
    public void k() {
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

    public int p(SolverVariable solverVariable) {
        int[] iArr;
        if (this.k == 0) {
            return -1;
        }
        int i = solverVariable.f1141d;
        int i2 = this.e[i % this.f1168d];
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

    public String toString() {
        String str = hashCode() + " { ";
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable solverVariableI = i(i2);
            if (solverVariableI != null) {
                String str2 = str + solverVariableI + " = " + a(i2) + " ";
                int iP = p(solverVariableI);
                String str3 = str2 + "[p: ";
                String str4 = (this.i[iP] != -1 ? str3 + this.n.f1154d[this.g[this.i[iP]]] : str3 + "none") + ", n: ";
                str = (this.j[iP] != -1 ? str4 + this.n.f1154d[this.g[this.j[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
