package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.b;
import java.util.Arrays;
import java.util.Comparator;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends androidx.constraintlayout.solver.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f943g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private SolverVariable[] f944h;
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
            return solverVariable.f908d - solverVariable2.f908d;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class b implements Comparable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        SolverVariable f946b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        h f947c;

        public b(h hVar) {
            this.f947c = hVar;
        }

        public boolean a(SolverVariable solverVariable, float f2) {
            boolean z = true;
            if (!this.f946b.f906b) {
                for (int i = 0; i < 9; i++) {
                    float f3 = solverVariable.j[i];
                    if (f3 != 0.0f) {
                        float f4 = f3 * f2;
                        if (Math.abs(f4) < 1.0E-4f) {
                            f4 = 0.0f;
                        }
                        this.f946b.j[i] = f4;
                    } else {
                        this.f946b.j[i] = 0.0f;
                    }
                }
                return true;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f946b.j;
                fArr[i2] = fArr[i2] + (solverVariable.j[i2] * f2);
                if (Math.abs(fArr[i2]) < 1.0E-4f) {
                    this.f946b.j[i2] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                h.this.G(this.f946b);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f946b = solverVariable;
        }

        public final boolean c() {
            for (int i = 8; i >= 0; i--) {
                float f2 = this.f946b.j[i];
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
            return this.f946b.f908d - ((SolverVariable) obj).f908d;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i = 8;
            while (true) {
                if (i < 0) {
                    break;
                }
                float f2 = solverVariable.j[i];
                float f3 = this.f946b.j[i];
                if (f3 == f2) {
                    i--;
                } else if (f3 < f2) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f946b.j, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f946b != null) {
                for (int i = 0; i < 9; i++) {
                    str = str + this.f946b.j[i] + " ";
                }
            }
            return str + "] " + this.f946b;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.f943g = 128;
        this.f944h = new SolverVariable[128];
        this.i = new SolverVariable[128];
        this.j = 0;
        this.k = new b(this);
        this.l = cVar;
    }

    private final void F(SolverVariable solverVariable) {
        int i;
        int i2 = this.j + 1;
        SolverVariable[] solverVariableArr = this.f944h;
        if (i2 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.f944h = solverVariableArr2;
            this.i = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.f944h;
        int i3 = this.j;
        solverVariableArr3[i3] = solverVariable;
        int i4 = i3 + 1;
        this.j = i4;
        if (i4 > 1 && solverVariableArr3[i4 - 1].f908d > solverVariable.f908d) {
            int i5 = 0;
            while (true) {
                i = this.j;
                if (i5 >= i) {
                    break;
                }
                this.i[i5] = this.f944h[i5];
                i5++;
            }
            Arrays.sort(this.i, 0, i, new a());
            for (int i6 = 0; i6 < this.j; i6++) {
                this.f944h[i6] = this.i[i6];
            }
        }
        solverVariable.f906b = true;
        solverVariable.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G(SolverVariable solverVariable) {
        int i = 0;
        while (i < this.j) {
            if (this.f944h[i] == solverVariable) {
                while (true) {
                    int i2 = this.j;
                    if (i >= i2 - 1) {
                        this.j = i2 - 1;
                        solverVariable.f906b = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.f944h;
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
        SolverVariable solverVariable = bVar.f922a;
        if (solverVariable == null) {
            return;
        }
        b.a aVar = bVar.f926e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            SolverVariable solverVariableF = aVar.f(i);
            float fA = aVar.a(i);
            this.k.b(solverVariableF);
            if (this.k.a(solverVariable, fA)) {
                F(solverVariableF);
            }
            this.f923b += bVar.f923b * fA;
        }
        G(solverVariable);
    }

    @Override // androidx.constraintlayout.solver.b, androidx.constraintlayout.solver.d.a
    public void b(SolverVariable solverVariable) {
        this.k.b(solverVariable);
        this.k.e();
        solverVariable.j[solverVariable.f910f] = 1.0f;
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
            androidx.constraintlayout.solver.SolverVariable[] r2 = r4.f944h
            r2 = r2[r0]
            int r3 = r2.f908d
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
            androidx.constraintlayout.solver.SolverVariable[] r3 = r4.f944h
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
            androidx.constraintlayout.solver.SolverVariable[] r5 = r4.f944h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.h.c(androidx.constraintlayout.solver.d, boolean[]):androidx.constraintlayout.solver.SolverVariable");
    }

    @Override // androidx.constraintlayout.solver.b, androidx.constraintlayout.solver.d.a
    public void clear() {
        this.j = 0;
        this.f923b = 0.0f;
    }

    @Override // androidx.constraintlayout.solver.b
    public String toString() {
        String str = HttpUrl.FRAGMENT_ENCODE_SET + " goal -> (" + this.f923b + ") : ";
        for (int i = 0; i < this.j; i++) {
            this.k.b(this.f944h[i]);
            str = str + this.k + " ";
        }
        return str;
    }
}
