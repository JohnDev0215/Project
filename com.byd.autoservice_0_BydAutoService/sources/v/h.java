package v;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.Arrays;
import java.util.Comparator;
import v.b;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends v.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5395g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SolverVariable[] f5396h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SolverVariable[] f5397i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5398j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public b f5399k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f5400l;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class a implements Comparator<SolverVariable> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
            return solverVariable.f813d - solverVariable2.f813d;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class b implements Comparable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public SolverVariable f5402b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public h f5403c;

        public b(h hVar) {
            this.f5403c = hVar;
        }

        public boolean a(SolverVariable solverVariable, float f3) {
            boolean z3 = true;
            if (!this.f5402b.f811b) {
                for (int i3 = 0; i3 < 9; i3++) {
                    float f4 = solverVariable.f819j[i3];
                    if (f4 != 0.0f) {
                        float f5 = f4 * f3;
                        if (Math.abs(f5) < 1.0E-4f) {
                            f5 = 0.0f;
                        }
                        this.f5402b.f819j[i3] = f5;
                    } else {
                        this.f5402b.f819j[i3] = 0.0f;
                    }
                }
                return true;
            }
            for (int i4 = 0; i4 < 9; i4++) {
                float[] fArr = this.f5402b.f819j;
                fArr[i4] = fArr[i4] + (solverVariable.f819j[i4] * f3);
                if (Math.abs(fArr[i4]) < 1.0E-4f) {
                    this.f5402b.f819j[i4] = 0.0f;
                } else {
                    z3 = false;
                }
            }
            if (z3) {
                h.this.G(this.f5402b);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f5402b = solverVariable;
        }

        public final boolean c() {
            for (int i3 = 8; i3 >= 0; i3--) {
                float f3 = this.f5402b.f819j[i3];
                if (f3 > 0.0f) {
                    return false;
                }
                if (f3 < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.f5402b.f813d - ((SolverVariable) obj).f813d;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i3 = 8;
            while (true) {
                if (i3 < 0) {
                    break;
                }
                float f3 = solverVariable.f819j[i3];
                float f4 = this.f5402b.f819j[i3];
                if (f4 == f3) {
                    i3--;
                } else if (f4 < f3) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f5402b.f819j, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f5402b != null) {
                for (int i3 = 0; i3 < 9; i3++) {
                    str = str + this.f5402b.f819j[i3] + " ";
                }
            }
            return str + "] " + this.f5402b;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.f5395g = 128;
        this.f5396h = new SolverVariable[128];
        this.f5397i = new SolverVariable[128];
        this.f5398j = 0;
        this.f5399k = new b(this);
        this.f5400l = cVar;
    }

    @Override // v.b
    public void C(v.b bVar, boolean z3) {
        SolverVariable solverVariable = bVar.f5361a;
        if (solverVariable == null) {
            return;
        }
        b.a aVar = bVar.f5365e;
        int iK = aVar.k();
        for (int i3 = 0; i3 < iK; i3++) {
            SolverVariable solverVariableF = aVar.f(i3);
            float fA = aVar.a(i3);
            this.f5399k.b(solverVariableF);
            if (this.f5399k.a(solverVariable, fA)) {
                F(solverVariableF);
            }
            this.f5362b += bVar.f5362b * fA;
        }
        G(solverVariable);
    }

    public final void F(SolverVariable solverVariable) {
        int i3;
        int i4 = this.f5398j + 1;
        SolverVariable[] solverVariableArr = this.f5396h;
        if (i4 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.f5396h = solverVariableArr2;
            this.f5397i = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.f5396h;
        int i5 = this.f5398j;
        solverVariableArr3[i5] = solverVariable;
        int i6 = i5 + 1;
        this.f5398j = i6;
        if (i6 > 1 && solverVariableArr3[i6 - 1].f813d > solverVariable.f813d) {
            int i7 = 0;
            while (true) {
                i3 = this.f5398j;
                if (i7 >= i3) {
                    break;
                }
                this.f5397i[i7] = this.f5396h[i7];
                i7++;
            }
            Arrays.sort(this.f5397i, 0, i3, new a());
            for (int i8 = 0; i8 < this.f5398j; i8++) {
                this.f5396h[i8] = this.f5397i[i8];
            }
        }
        solverVariable.f811b = true;
        solverVariable.a(this);
    }

    public final void G(SolverVariable solverVariable) {
        int i3 = 0;
        while (i3 < this.f5398j) {
            if (this.f5396h[i3] == solverVariable) {
                while (true) {
                    int i4 = this.f5398j;
                    if (i3 >= i4 - 1) {
                        this.f5398j = i4 - 1;
                        solverVariable.f811b = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.f5396h;
                        int i5 = i3 + 1;
                        solverVariableArr[i3] = solverVariableArr[i5];
                        i3 = i5;
                    }
                }
            } else {
                i3++;
            }
        }
    }

    @Override // v.b, v.d.a
    public void b(SolverVariable solverVariable) {
        this.f5399k.b(solverVariable);
        this.f5399k.e();
        solverVariable.f819j[solverVariable.f815f] = 1.0f;
        F(solverVariable);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // v.b, v.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.solver.SolverVariable c(v.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.f5398j
            if (r0 >= r2) goto L32
            androidx.constraintlayout.solver.SolverVariable[] r2 = r4.f5396h
            r2 = r2[r0]
            int r3 = r2.f813d
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            v.h$b r3 = r4.f5399k
            r3.b(r2)
            if (r1 != r5) goto L22
            v.h$b r2 = r4.f5399k
            boolean r2 = r2.c()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            v.h$b r2 = r4.f5399k
            androidx.constraintlayout.solver.SolverVariable[] r3 = r4.f5396h
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
            androidx.constraintlayout.solver.SolverVariable[] r5 = r4.f5396h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: v.h.c(v.d, boolean[]):androidx.constraintlayout.solver.SolverVariable");
    }

    @Override // v.b, v.d.a
    public void clear() {
        this.f5398j = 0;
        this.f5362b = 0.0f;
    }

    @Override // v.b
    public String toString() {
        String str = " goal -> (" + this.f5362b + ") : ";
        for (int i3 = 0; i3 < this.f5398j; i3++) {
            this.f5399k.b(this.f5396h[i3]);
            str = str + this.f5399k + " ";
        }
        return str;
    }
}
