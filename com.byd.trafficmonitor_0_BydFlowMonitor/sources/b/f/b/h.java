package b.f.b;

import androidx.constraintlayout.solver.SolverVariable;
import b.f.b.b;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends b.f.b.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2198g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SolverVariable[] f2199h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SolverVariable[] f2200i;
    public int j;
    public b k;
    public c l;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class a implements Comparator<SolverVariable> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
            return solverVariable.f611d - solverVariable2.f611d;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class b implements Comparable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public SolverVariable f2202b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public h f2203c;

        public b(h hVar) {
            this.f2203c = hVar;
        }

        public boolean a(SolverVariable solverVariable, float f2) {
            boolean z = true;
            if (!this.f2202b.f609b) {
                for (int i2 = 0; i2 < 9; i2++) {
                    float f3 = solverVariable.j[i2];
                    if (f3 != 0.0f) {
                        float f4 = f3 * f2;
                        if (Math.abs(f4) < 1.0E-4f) {
                            f4 = 0.0f;
                        }
                        this.f2202b.j[i2] = f4;
                    } else {
                        this.f2202b.j[i2] = 0.0f;
                    }
                }
                return true;
            }
            for (int i3 = 0; i3 < 9; i3++) {
                float[] fArr = this.f2202b.j;
                fArr[i3] = fArr[i3] + (solverVariable.j[i3] * f2);
                if (Math.abs(fArr[i3]) < 1.0E-4f) {
                    this.f2202b.j[i3] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                h.this.G(this.f2202b);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f2202b = solverVariable;
        }

        public final boolean c() {
            for (int i2 = 8; i2 >= 0; i2--) {
                float f2 = this.f2202b.j[i2];
                if (f2 > 0.0f) {
                    return false;
                }
                if (f2 < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.f2202b.f611d - ((SolverVariable) obj).f611d;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i2 = 8;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                float f2 = solverVariable.j[i2];
                float f3 = this.f2202b.j[i2];
                if (f3 == f2) {
                    i2--;
                } else if (f3 < f2) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f2202b.j, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f2202b != null) {
                for (int i2 = 0; i2 < 9; i2++) {
                    str = str + this.f2202b.j[i2] + " ";
                }
            }
            return str + "] " + this.f2202b;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.f2198g = 128;
        this.f2199h = new SolverVariable[128];
        this.f2200i = new SolverVariable[128];
        this.j = 0;
        this.k = new b(this);
        this.l = cVar;
    }

    @Override // b.f.b.b
    public void C(b.f.b.b bVar, boolean z) {
        SolverVariable solverVariable = bVar.f2176a;
        if (solverVariable == null) {
            return;
        }
        b.a aVar = bVar.f2180e;
        int iK = aVar.k();
        for (int i2 = 0; i2 < iK; i2++) {
            SolverVariable solverVariableF = aVar.f(i2);
            float fA = aVar.a(i2);
            this.k.b(solverVariableF);
            if (this.k.a(solverVariable, fA)) {
                F(solverVariableF);
            }
            this.f2177b += bVar.f2177b * fA;
        }
        G(solverVariable);
    }

    public final void F(SolverVariable solverVariable) {
        int i2;
        int i3 = this.j + 1;
        SolverVariable[] solverVariableArr = this.f2199h;
        if (i3 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.f2199h = solverVariableArr2;
            this.f2200i = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.f2199h;
        int i4 = this.j;
        solverVariableArr3[i4] = solverVariable;
        int i5 = i4 + 1;
        this.j = i5;
        if (i5 > 1 && solverVariableArr3[i5 - 1].f611d > solverVariable.f611d) {
            int i6 = 0;
            while (true) {
                i2 = this.j;
                if (i6 >= i2) {
                    break;
                }
                this.f2200i[i6] = this.f2199h[i6];
                i6++;
            }
            Arrays.sort(this.f2200i, 0, i2, new a());
            for (int i7 = 0; i7 < this.j; i7++) {
                this.f2199h[i7] = this.f2200i[i7];
            }
        }
        solverVariable.f609b = true;
        solverVariable.a(this);
    }

    public final void G(SolverVariable solverVariable) {
        int i2 = 0;
        while (i2 < this.j) {
            if (this.f2199h[i2] == solverVariable) {
                while (true) {
                    int i3 = this.j;
                    if (i2 >= i3 - 1) {
                        this.j = i3 - 1;
                        solverVariable.f609b = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.f2199h;
                        int i4 = i2 + 1;
                        solverVariableArr[i2] = solverVariableArr[i4];
                        i2 = i4;
                    }
                }
            } else {
                i2++;
            }
        }
    }

    @Override // b.f.b.b, b.f.b.d.a
    public void b(SolverVariable solverVariable) {
        this.k.b(solverVariable);
        this.k.e();
        solverVariable.j[solverVariable.f613f] = 1.0f;
        F(solverVariable);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // b.f.b.b, b.f.b.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.solver.SolverVariable c(b.f.b.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.j
            if (r0 >= r2) goto L32
            androidx.constraintlayout.solver.SolverVariable[] r2 = r4.f2199h
            r2 = r2[r0]
            int r3 = r2.f611d
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            b.f.b.h$b r3 = r4.k
            r3.b(r2)
            if (r1 != r5) goto L22
            b.f.b.h$b r2 = r4.k
            boolean r2 = r2.c()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            b.f.b.h$b r2 = r4.k
            androidx.constraintlayout.solver.SolverVariable[] r3 = r4.f2199h
            r3 = r3[r1]
            boolean r2 = r2.d(r3)
            if (r2 == 0) goto L2f
        L2e:
            r1 = r0
        L2f:
            int r0 = r0 + 1
            goto L3
        L32:
            if (r1 != r5) goto L36
            r5 = 0
            return r5
        L36:
            androidx.constraintlayout.solver.SolverVariable[] r5 = r4.f2199h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: b.f.b.h.c(b.f.b.d, boolean[]):androidx.constraintlayout.solver.SolverVariable");
    }

    @Override // b.f.b.b, b.f.b.d.a
    public void clear() {
        this.j = 0;
        this.f2177b = 0.0f;
    }

    @Override // b.f.b.b
    public String toString() {
        String str = " goal -> (" + this.f2177b + ") : ";
        for (int i2 = 0; i2 < this.j; i2++) {
            this.k.b(this.f2199h[i2]);
            str = str + this.k + " ";
        }
        return str;
    }
}
