package y;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.Arrays;
import java.util.Comparator;
import y.b;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends y.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9140g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SolverVariable[] f9141h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SolverVariable[] f9142i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9143j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public b f9144k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f9145l;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class a implements Comparator<SolverVariable> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
            return solverVariable.f904d - solverVariable2.f904d;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class b implements Comparable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public SolverVariable f9147d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public h f9148e;

        public b(h hVar) {
            this.f9148e = hVar;
        }

        public boolean a(SolverVariable solverVariable, float f8) {
            boolean z7 = true;
            if (!this.f9147d.f902b) {
                for (int i8 = 0; i8 < 9; i8++) {
                    float f9 = solverVariable.f910j[i8];
                    if (f9 != 0.0f) {
                        float f10 = f9 * f8;
                        if (Math.abs(f10) < 1.0E-4f) {
                            f10 = 0.0f;
                        }
                        this.f9147d.f910j[i8] = f10;
                    } else {
                        this.f9147d.f910j[i8] = 0.0f;
                    }
                }
                return true;
            }
            for (int i9 = 0; i9 < 9; i9++) {
                float[] fArr = this.f9147d.f910j;
                fArr[i9] = fArr[i9] + (solverVariable.f910j[i9] * f8);
                if (Math.abs(fArr[i9]) < 1.0E-4f) {
                    this.f9147d.f910j[i9] = 0.0f;
                } else {
                    z7 = false;
                }
            }
            if (z7) {
                h.this.G(this.f9147d);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f9147d = solverVariable;
        }

        public final boolean c() {
            for (int i8 = 8; i8 >= 0; i8--) {
                float f8 = this.f9147d.f910j[i8];
                if (f8 > 0.0f) {
                    return false;
                }
                if (f8 < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.f9147d.f904d - ((SolverVariable) obj).f904d;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i8 = 8;
            while (true) {
                if (i8 < 0) {
                    break;
                }
                float f8 = solverVariable.f910j[i8];
                float f9 = this.f9147d.f910j[i8];
                if (f9 == f8) {
                    i8--;
                } else if (f9 < f8) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f9147d.f910j, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f9147d != null) {
                for (int i8 = 0; i8 < 9; i8++) {
                    str = str + this.f9147d.f910j[i8] + " ";
                }
            }
            return str + "] " + this.f9147d;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.f9140g = 128;
        this.f9141h = new SolverVariable[128];
        this.f9142i = new SolverVariable[128];
        this.f9143j = 0;
        this.f9144k = new b(this);
        this.f9145l = cVar;
    }

    @Override // y.b
    public void C(y.b bVar, boolean z7) {
        SolverVariable solverVariable = bVar.f9106a;
        if (solverVariable == null) {
            return;
        }
        b.a aVar = bVar.f9110e;
        int iE = aVar.e();
        for (int i8 = 0; i8 < iE; i8++) {
            SolverVariable solverVariableI = aVar.i(i8);
            float fA = aVar.a(i8);
            this.f9144k.b(solverVariableI);
            if (this.f9144k.a(solverVariable, fA)) {
                F(solverVariableI);
            }
            this.f9107b += bVar.f9107b * fA;
        }
        G(solverVariable);
    }

    public final void F(SolverVariable solverVariable) {
        int i8;
        int i9 = this.f9143j + 1;
        SolverVariable[] solverVariableArr = this.f9141h;
        if (i9 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.f9141h = solverVariableArr2;
            this.f9142i = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.f9141h;
        int i10 = this.f9143j;
        solverVariableArr3[i10] = solverVariable;
        int i11 = i10 + 1;
        this.f9143j = i11;
        if (i11 > 1 && solverVariableArr3[i11 - 1].f904d > solverVariable.f904d) {
            int i12 = 0;
            while (true) {
                i8 = this.f9143j;
                if (i12 >= i8) {
                    break;
                }
                this.f9142i[i12] = this.f9141h[i12];
                i12++;
            }
            Arrays.sort(this.f9142i, 0, i8, new a());
            for (int i13 = 0; i13 < this.f9143j; i13++) {
                this.f9141h[i13] = this.f9142i[i13];
            }
        }
        solverVariable.f902b = true;
        solverVariable.a(this);
    }

    public final void G(SolverVariable solverVariable) {
        int i8 = 0;
        while (i8 < this.f9143j) {
            if (this.f9141h[i8] == solverVariable) {
                while (true) {
                    int i9 = this.f9143j;
                    if (i8 >= i9 - 1) {
                        this.f9143j = i9 - 1;
                        solverVariable.f902b = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.f9141h;
                        int i10 = i8 + 1;
                        solverVariableArr[i8] = solverVariableArr[i10];
                        i8 = i10;
                    }
                }
            } else {
                i8++;
            }
        }
    }

    @Override // y.b, y.d.a
    public void b(SolverVariable solverVariable) {
        this.f9144k.b(solverVariable);
        this.f9144k.e();
        solverVariable.f910j[solverVariable.f906f] = 1.0f;
        F(solverVariable);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // y.b, y.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.solver.SolverVariable c(y.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.f9143j
            if (r0 >= r2) goto L32
            androidx.constraintlayout.solver.SolverVariable[] r2 = r4.f9141h
            r2 = r2[r0]
            int r3 = r2.f904d
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            y.h$b r3 = r4.f9144k
            r3.b(r2)
            if (r1 != r5) goto L22
            y.h$b r2 = r4.f9144k
            boolean r2 = r2.c()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            y.h$b r2 = r4.f9144k
            androidx.constraintlayout.solver.SolverVariable[] r3 = r4.f9141h
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
            androidx.constraintlayout.solver.SolverVariable[] r5 = r4.f9141h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: y.h.c(y.d, boolean[]):androidx.constraintlayout.solver.SolverVariable");
    }

    @Override // y.b, y.d.a
    public void clear() {
        this.f9143j = 0;
        this.f9107b = 0.0f;
    }

    @Override // y.b
    public String toString() {
        String str = " goal -> (" + this.f9107b + ") : ";
        for (int i8 = 0; i8 < this.f9143j; i8++) {
            this.f9144k.b(this.f9141h[i8]);
            str = str + this.f9144k + " ";
        }
        return str;
    }
}
