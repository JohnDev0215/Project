package androidx.constraintlayout.core;

import androidx.constraintlayout.core.b;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends androidx.constraintlayout.core.b {
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
            return solverVariable.f703e - solverVariable2.f703e;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        SolverVariable f727a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        h f728b;

        public b(h hVar) {
            this.f728b = hVar;
        }

        public boolean a(SolverVariable solverVariable, float f) {
            boolean z = true;
            if (!this.f727a.f701c) {
                for (int i = 0; i < 9; i++) {
                    float f2 = solverVariable.k[i];
                    if (f2 != 0.0f) {
                        float f3 = f2 * f;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        this.f727a.k[i] = f3;
                    } else {
                        this.f727a.k[i] = 0.0f;
                    }
                }
                return true;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f727a.k;
                fArr[i2] = fArr[i2] + (solverVariable.k[i2] * f);
                if (Math.abs(fArr[i2]) < 1.0E-4f) {
                    this.f727a.k[i2] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                h.this.G(this.f727a);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f727a = solverVariable;
        }

        public final boolean c() {
            for (int i = 8; i >= 0; i--) {
                float f = this.f727a.k[i];
                if (f > 0.0f) {
                    return false;
                }
                if (f < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i = 8;
            while (true) {
                if (i < 0) {
                    break;
                }
                float f = solverVariable.k[i];
                float f2 = this.f727a.k[i];
                if (f2 == f) {
                    i--;
                } else if (f2 < f) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f727a.k, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f727a != null) {
                for (int i = 0; i < 9; i++) {
                    str = str + this.f727a.k[i] + " ";
                }
            }
            return str + "] " + this.f727a;
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
        if (i4 > 1 && solverVariableArr3[i4 - 1].f703e > solverVariable.f703e) {
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
        solverVariable.f701c = true;
        solverVariable.b(this);
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
                        solverVariable.f701c = false;
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

    @Override // androidx.constraintlayout.core.b
    public void B(d dVar, androidx.constraintlayout.core.b bVar, boolean z) {
        SolverVariable solverVariable = bVar.f710a;
        if (solverVariable == null) {
            return;
        }
        b.a aVar = bVar.f714e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            SolverVariable solverVariableG = aVar.g(i);
            float fA = aVar.a(i);
            this.k.b(solverVariableG);
            if (this.k.a(solverVariable, fA)) {
                F(solverVariableG);
            }
            this.f711b += bVar.f711b * fA;
        }
        G(solverVariable);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // androidx.constraintlayout.core.b, androidx.constraintlayout.core.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.core.SolverVariable a(androidx.constraintlayout.core.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.j
            if (r0 >= r2) goto L32
            androidx.constraintlayout.core.SolverVariable[] r2 = r4.h
            r2 = r2[r0]
            int r3 = r2.f703e
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            androidx.constraintlayout.core.h$b r3 = r4.k
            r3.b(r2)
            if (r1 != r5) goto L22
            androidx.constraintlayout.core.h$b r2 = r4.k
            boolean r2 = r2.c()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            androidx.constraintlayout.core.h$b r2 = r4.k
            androidx.constraintlayout.core.SolverVariable[] r3 = r4.h
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
            androidx.constraintlayout.core.SolverVariable[] r5 = r4.h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.h.a(androidx.constraintlayout.core.d, boolean[]):androidx.constraintlayout.core.SolverVariable");
    }

    @Override // androidx.constraintlayout.core.b, androidx.constraintlayout.core.d.a
    public void b(SolverVariable solverVariable) {
        this.k.b(solverVariable);
        this.k.e();
        solverVariable.k[solverVariable.g] = 1.0f;
        F(solverVariable);
    }

    @Override // androidx.constraintlayout.core.b, androidx.constraintlayout.core.d.a
    public void clear() {
        this.j = 0;
        this.f711b = 0.0f;
    }

    @Override // androidx.constraintlayout.core.b, androidx.constraintlayout.core.d.a
    public boolean isEmpty() {
        return this.j == 0;
    }

    @Override // androidx.constraintlayout.core.b
    public String toString() {
        String str = " goal -> (" + this.f711b + ") : ";
        for (int i = 0; i < this.j; i++) {
            this.k.b(this.h[i]);
            str = str + this.k + " ";
        }
        return str;
    }
}
