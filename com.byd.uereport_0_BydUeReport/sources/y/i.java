package y;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.Arrays;
import y.b;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f9150a = 0.001f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f9151b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f9152c = 16;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f9153d = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f9154e = new int[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f9155f = new int[16];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f9156g = new int[16];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f9157h = new float[16];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f9158i = new int[16];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f9159j = new int[16];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f9160k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f9161l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f9162m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f9163n;

    public i(b bVar, c cVar) {
        this.f9162m = bVar;
        this.f9163n = cVar;
        clear();
    }

    @Override // y.b.a
    public float a(int i8) {
        int i9 = this.f9160k;
        int i10 = this.f9161l;
        for (int i11 = 0; i11 < i9; i11++) {
            if (i11 == i8) {
                return this.f9157h[i10];
            }
            i10 = this.f9159j[i10];
            if (i10 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // y.b.a
    public void b(SolverVariable solverVariable, float f8, boolean z7) {
        float f9 = f9150a;
        if (f8 <= (-f9) || f8 >= f9) {
            int iP = p(solverVariable);
            if (iP == -1) {
                g(solverVariable, f8);
                return;
            }
            float[] fArr = this.f9157h;
            fArr[iP] = fArr[iP] + f8;
            float f10 = fArr[iP];
            float f11 = f9150a;
            if (f10 <= (-f11) || fArr[iP] >= f11) {
                return;
            }
            fArr[iP] = 0.0f;
            h(solverVariable, z7);
        }
    }

    @Override // y.b.a
    public float c(SolverVariable solverVariable) {
        int iP = p(solverVariable);
        if (iP != -1) {
            return this.f9157h[iP];
        }
        return 0.0f;
    }

    @Override // y.b.a
    public void clear() {
        int i8 = this.f9160k;
        for (int i9 = 0; i9 < i8; i9++) {
            SolverVariable solverVariableI = i(i9);
            if (solverVariableI != null) {
                solverVariableI.c(this.f9162m);
            }
        }
        for (int i10 = 0; i10 < this.f9152c; i10++) {
            this.f9156g[i10] = -1;
            this.f9155f[i10] = -1;
        }
        for (int i11 = 0; i11 < this.f9153d; i11++) {
            this.f9154e[i11] = -1;
        }
        this.f9160k = 0;
        this.f9161l = -1;
    }

    @Override // y.b.a
    public boolean d(SolverVariable solverVariable) {
        return p(solverVariable) != -1;
    }

    @Override // y.b.a
    public int e() {
        return this.f9160k;
    }

    @Override // y.b.a
    public float f(b bVar, boolean z7) {
        float fC = c(bVar.f9106a);
        h(bVar.f9106a, z7);
        i iVar = (i) bVar.f9110e;
        int iE = iVar.e();
        int i8 = 0;
        int i9 = 0;
        while (i8 < iE) {
            int[] iArr = iVar.f9156g;
            if (iArr[i9] != -1) {
                b(this.f9163n.f9115d[iArr[i9]], iVar.f9157h[i9] * fC, z7);
                i8++;
            }
            i9++;
        }
        return fC;
    }

    @Override // y.b.a
    public void g(SolverVariable solverVariable, float f8) {
        float f9 = f9150a;
        if (f8 > (-f9) && f8 < f9) {
            h(solverVariable, true);
            return;
        }
        if (this.f9160k == 0) {
            m(0, solverVariable, f8);
            l(solverVariable, 0);
            this.f9161l = 0;
            return;
        }
        int iP = p(solverVariable);
        if (iP != -1) {
            this.f9157h[iP] = f8;
            return;
        }
        if (this.f9160k + 1 >= this.f9152c) {
            o();
        }
        int i8 = this.f9160k;
        int i9 = this.f9161l;
        int i10 = -1;
        for (int i11 = 0; i11 < i8; i11++) {
            int[] iArr = this.f9156g;
            int i12 = iArr[i9];
            int i13 = solverVariable.f904d;
            if (i12 == i13) {
                this.f9157h[i9] = f8;
                return;
            }
            if (iArr[i9] < i13) {
                i10 = i9;
            }
            i9 = this.f9159j[i9];
            if (i9 == -1) {
                break;
            }
        }
        q(i10, solverVariable, f8);
    }

    @Override // y.b.a
    public float h(SolverVariable solverVariable, boolean z7) {
        int iP = p(solverVariable);
        if (iP == -1) {
            return 0.0f;
        }
        r(solverVariable);
        float f8 = this.f9157h[iP];
        if (this.f9161l == iP) {
            this.f9161l = this.f9159j[iP];
        }
        this.f9156g[iP] = -1;
        int[] iArr = this.f9158i;
        if (iArr[iP] != -1) {
            int[] iArr2 = this.f9159j;
            iArr2[iArr[iP]] = iArr2[iP];
        }
        int[] iArr3 = this.f9159j;
        if (iArr3[iP] != -1) {
            iArr[iArr3[iP]] = iArr[iP];
        }
        this.f9160k--;
        solverVariable.f914n--;
        if (z7) {
            solverVariable.c(this.f9162m);
        }
        return f8;
    }

    @Override // y.b.a
    public SolverVariable i(int i8) {
        int i9 = this.f9160k;
        if (i9 == 0) {
            return null;
        }
        int i10 = this.f9161l;
        for (int i11 = 0; i11 < i9; i11++) {
            if (i11 == i8 && i10 != -1) {
                return this.f9163n.f9115d[this.f9156g[i10]];
            }
            i10 = this.f9159j[i10];
            if (i10 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // y.b.a
    public void j(float f8) {
        int i8 = this.f9160k;
        int i9 = this.f9161l;
        for (int i10 = 0; i10 < i8; i10++) {
            float[] fArr = this.f9157h;
            fArr[i9] = fArr[i9] / f8;
            i9 = this.f9159j[i9];
            if (i9 == -1) {
                return;
            }
        }
    }

    @Override // y.b.a
    public void k() {
        int i8 = this.f9160k;
        int i9 = this.f9161l;
        for (int i10 = 0; i10 < i8; i10++) {
            float[] fArr = this.f9157h;
            fArr[i9] = fArr[i9] * (-1.0f);
            i9 = this.f9159j[i9];
            if (i9 == -1) {
                return;
            }
        }
    }

    public final void l(SolverVariable solverVariable, int i8) {
        int[] iArr;
        int i9 = solverVariable.f904d % this.f9153d;
        int[] iArr2 = this.f9154e;
        int i10 = iArr2[i9];
        if (i10 == -1) {
            iArr2[i9] = i8;
        } else {
            while (true) {
                iArr = this.f9155f;
                if (iArr[i10] == -1) {
                    break;
                } else {
                    i10 = iArr[i10];
                }
            }
            iArr[i10] = i8;
        }
        this.f9155f[i8] = -1;
    }

    public final void m(int i8, SolverVariable solverVariable, float f8) {
        this.f9156g[i8] = solverVariable.f904d;
        this.f9157h[i8] = f8;
        this.f9158i[i8] = -1;
        this.f9159j[i8] = -1;
        solverVariable.a(this.f9162m);
        solverVariable.f914n++;
        this.f9160k++;
    }

    public final int n() {
        for (int i8 = 0; i8 < this.f9152c; i8++) {
            if (this.f9156g[i8] == -1) {
                return i8;
            }
        }
        return -1;
    }

    public final void o() {
        int i8 = this.f9152c * 2;
        this.f9156g = Arrays.copyOf(this.f9156g, i8);
        this.f9157h = Arrays.copyOf(this.f9157h, i8);
        this.f9158i = Arrays.copyOf(this.f9158i, i8);
        this.f9159j = Arrays.copyOf(this.f9159j, i8);
        this.f9155f = Arrays.copyOf(this.f9155f, i8);
        for (int i9 = this.f9152c; i9 < i8; i9++) {
            this.f9156g[i9] = -1;
            this.f9155f[i9] = -1;
        }
        this.f9152c = i8;
    }

    public int p(SolverVariable solverVariable) {
        int[] iArr;
        if (this.f9160k == 0) {
            return -1;
        }
        int i8 = solverVariable.f904d;
        int i9 = this.f9154e[i8 % this.f9153d];
        if (i9 == -1) {
            return -1;
        }
        if (this.f9156g[i9] == i8) {
            return i9;
        }
        while (true) {
            iArr = this.f9155f;
            if (iArr[i9] == -1 || this.f9156g[iArr[i9]] == i8) {
                break;
            }
            i9 = iArr[i9];
        }
        if (iArr[i9] != -1 && this.f9156g[iArr[i9]] == i8) {
            return iArr[i9];
        }
        return -1;
    }

    public final void q(int i8, SolverVariable solverVariable, float f8) {
        int iN = n();
        m(iN, solverVariable, f8);
        if (i8 != -1) {
            this.f9158i[iN] = i8;
            int[] iArr = this.f9159j;
            iArr[iN] = iArr[i8];
            iArr[i8] = iN;
        } else {
            this.f9158i[iN] = -1;
            if (this.f9160k > 0) {
                this.f9159j[iN] = this.f9161l;
                this.f9161l = iN;
            } else {
                this.f9159j[iN] = -1;
            }
        }
        int[] iArr2 = this.f9159j;
        if (iArr2[iN] != -1) {
            this.f9158i[iArr2[iN]] = iN;
        }
        l(solverVariable, iN);
    }

    public final void r(SolverVariable solverVariable) {
        int[] iArr;
        int i8 = solverVariable.f904d;
        int i9 = i8 % this.f9153d;
        int[] iArr2 = this.f9154e;
        int i10 = iArr2[i9];
        if (i10 == -1) {
            return;
        }
        if (this.f9156g[i10] == i8) {
            int[] iArr3 = this.f9155f;
            iArr2[i9] = iArr3[i10];
            iArr3[i10] = -1;
            return;
        }
        while (true) {
            iArr = this.f9155f;
            if (iArr[i10] == -1 || this.f9156g[iArr[i10]] == i8) {
                break;
            } else {
                i10 = iArr[i10];
            }
        }
        int i11 = iArr[i10];
        if (i11 == -1 || this.f9156g[i11] != i8) {
            return;
        }
        iArr[i10] = iArr[i11];
        iArr[i11] = -1;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i8 = this.f9160k;
        for (int i9 = 0; i9 < i8; i9++) {
            SolverVariable solverVariableI = i(i9);
            if (solverVariableI != null) {
                String str2 = str + solverVariableI + " = " + a(i9) + " ";
                int iP = p(solverVariableI);
                String str3 = str2 + "[p: ";
                String str4 = (this.f9158i[iP] != -1 ? str3 + this.f9163n.f9115d[this.f9156g[this.f9158i[iP]]] : str3 + "none") + ", n: ";
                str = (this.f9159j[iP] != -1 ? str4 + this.f9163n.f9115d[this.f9156g[this.f9159j[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
