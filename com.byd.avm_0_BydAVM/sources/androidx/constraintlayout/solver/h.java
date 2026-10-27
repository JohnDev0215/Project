package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.b;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends androidx.constraintlayout.solver.b {
    private int g;
    private SolverVariable[] h;
    private SolverVariable[] i;
    private int j;
    b k;
    c l;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class a implements Comparator<SolverVariable> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
            return solverVariable.f622d - solverVariable2.f622d;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class b implements Comparable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        SolverVariable f643b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        h f644c;

        public b(h hVar) {
            this.f644c = hVar;
        }

        public boolean a(SolverVariable solverVariable, float f) {
            boolean z = true;
            if (!this.f643b.f620b) {
                for (int i = 0; i < 9; i++) {
                    float f2 = solverVariable.j[i];
                    if (f2 != 0.0f) {
                        float f3 = f2 * f;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        this.f643b.j[i] = f3;
                    } else {
                        this.f643b.j[i] = 0.0f;
                    }
                }
                return true;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f643b.j;
                fArr[i2] = fArr[i2] + (solverVariable.j[i2] * f);
                if (Math.abs(fArr[i2]) < 1.0E-4f) {
                    this.f643b.j[i2] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                h.this.G(this.f643b);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f643b = solverVariable;
        }

        public final boolean c() {
            for (int i = 8; i >= 0; i--) {
                float f = this.f643b.j[i];
                if (f > 0.0f) {
                    return false;
                }
                if (f < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.f643b.f622d - ((SolverVariable) obj).f622d;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i = 8;
            while (true) {
                if (i < 0) {
                    break;
                }
                float f = solverVariable.j[i];
                float f2 = this.f643b.j[i];
                if (f2 == f) {
                    i--;
                } else if (f2 < f) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f643b.j, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f643b != null) {
                for (int i = 0; i < 9; i++) {
                    str = str + this.f643b.j[i] + " ";
                }
            }
            return str + "] " + this.f643b;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.g = 128;
        this.h = new SolverVariable[128];
        this.i = new SolverVariable[128];
        this.j = 0;
        this.k = new b(this);
        this.l = cVar;
    }

    private final void F(SolverVariable solverVariable) {
        int i;
        int i2 = this.j + 1;
        SolverVariable[] solverVariableArr = this.h;
        if (i2 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.h = solverVariableArr2;
            this.i = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.h;
        int i3 = this.j;
        solverVariableArr3[i3] = solverVariable;
        int i4 = i3 + 1;
        this.j = i4;
        if (i4 > 1 && solverVariableArr3[i4 - 1].f622d > solverVariable.f622d) {
            int i5 = 0;
            while (true) {
                i = this.j;
                if (i5 >= i) {
                    break;
                }
                this.i[i5] = this.h[i5];
                i5++;
            }
            Arrays.sort(this.i, 0, i, new a());
            for (int i6 = 0; i6 < this.j; i6++) {
                this.h[i6] = this.i[i6];
            }
        }
        solverVariable.f620b = true;
        solverVariable.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G(SolverVariable solverVariable) {
        int i = 0;
        while (i < this.j) {
            if (this.h[i] == solverVariable) {
                while (true) {
                    int i2 = this.j;
                    if (i >= i2 - 1) {
                        this.j = i2 - 1;
                        solverVariable.f620b = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.h;
                        int i3 = i + 1;
                        solverVariableArr[i] = solverVariableArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.b
    public void C(androidx.constraintlayout.solver.b bVar, boolean z) {
        SolverVariable solverVariable = bVar.f628a;
        if (solverVariable == null) {
            return;
        }
        b.a aVar = bVar.e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            SolverVariable solverVariableF = aVar.f(i);
            float fA = aVar.a(i);
            this.k.b(solverVariableF);
            if (this.k.a(solverVariable, fA)) {
                F(solverVariableF);
            }
            this.f629b += bVar.f629b * fA;
        }
        G(solverVariable);
    }

    @Override // androidx.constraintlayout.solver.b, androidx.constraintlayout.solver.d.a
    public void b(SolverVariable solverVariable) {
        this.k.b(solverVariable);
        this.k.e();
        solverVariable.j[solverVariable.f] = 1.0f;
        F(solverVariable);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // androidx.constraintlayout.solver.b, androidx.constraintlayout.solver.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.solver.SolverVariable c(androidx.constraintlayout.solver.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.j
            if (r0 >= r2) goto L32
            androidx.constraintlayout.solver.SolverVariable[] r2 = r4.h
            r2 = r2[r0]
            int r3 = r2.f622d
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            androidx.constraintlayout.solver.h$b r3 = r4.k
            r3.b(r2)
            if (r1 != r5) goto L22
            androidx.constraintlayout.solver.h$b r2 = r4.k
            boolean r2 = r2.c()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            androidx.constraintlayout.solver.h$b r2 = r4.k
            androidx.constraintlayout.solver.SolverVariable[] r3 = r4.h
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
            r4 = 0
            return r4
        L36:
            androidx.constraintlayout.solver.SolverVariable[] r4 = r4.h
            r4 = r4[r1]
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.h.c(androidx.constraintlayout.solver.d, boolean[]):androidx.constraintlayout.solver.SolverVariable");
    }

    @Override // androidx.constraintlayout.solver.b, androidx.constraintlayout.solver.d.a
    public void clear() {
        this.j = 0;
        this.f629b = 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b
    public String toString() {
        String str = " goal -> (" + this.f629b + ") : ";
        for (int i = 0; i < this.j; i++) {
            this.k.b(this.h[i]);
            str = str + this.k + " ";
        }
        return str;
    }
}
