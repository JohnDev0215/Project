package p;

import androidx.constraintlayout.core.SolverVariable$Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class pb {
    public final ob d;
    public ce2 a = null;
    public float b = 0.0f;
    public final ArrayList c = new ArrayList();
    public boolean e = false;

    public pb(gh2 gh2Var) {
        this.d = new hb(this, gh2Var);
    }

    public final void a(ra1 ra1Var, int i) {
        this.d.d(ra1Var.j(i), 1.0f);
        this.d.d(ra1Var.j(i), -1.0f);
    }

    public final void b(ce2 ce2Var, ce2 ce2Var2, ce2 ce2Var3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            } else {
                z = false;
            }
            this.b = i;
            if (z) {
                this.d.d(ce2Var, 1.0f);
                this.d.d(ce2Var2, -1.0f);
                this.d.d(ce2Var3, -1.0f);
                return;
            }
        }
        this.d.d(ce2Var, -1.0f);
        this.d.d(ce2Var2, 1.0f);
        this.d.d(ce2Var3, 1.0f);
    }

    public final void c(ce2 ce2Var, ce2 ce2Var2, ce2 ce2Var3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            } else {
                z = false;
            }
            this.b = i;
            if (z) {
                this.d.d(ce2Var, 1.0f);
                this.d.d(ce2Var2, -1.0f);
                this.d.d(ce2Var3, 1.0f);
                return;
            }
        }
        this.d.d(ce2Var, -1.0f);
        this.d.d(ce2Var2, 1.0f);
        this.d.d(ce2Var3, -1.0f);
    }

    public ce2 d(boolean[] zArr) {
        return f(zArr, null);
    }

    public boolean e() {
        return this.a == null && this.b == 0.0f && this.d.g() == 0;
    }

    public final ce2 f(boolean[] zArr, ce2 ce2Var) {
        SolverVariable$Type solverVariable$Type;
        int iG = this.d.g();
        ce2 ce2Var2 = null;
        float f = 0.0f;
        for (int i = 0; i < iG; i++) {
            float fA = this.d.a(i);
            if (fA < 0.0f) {
                ce2 ce2VarH = this.d.h(i);
                if ((zArr == null || !zArr[ce2VarH.e]) && ce2VarH != ce2Var && (((solverVariable$Type = ce2VarH.q) == SolverVariable$Type.e || solverVariable$Type == SolverVariable$Type.k) && fA < f)) {
                    f = fA;
                    ce2Var2 = ce2VarH;
                }
            }
        }
        return ce2Var2;
    }

    public final void g(ce2 ce2Var) {
        ce2 ce2Var2 = this.a;
        if (ce2Var2 != null) {
            this.d.d(ce2Var2, -1.0f);
            this.a.k = -1;
            this.a = null;
        }
        float fC = this.d.c(ce2Var, true) * (-1.0f);
        this.a = ce2Var;
        if (fC == 1.0f) {
            return;
        }
        this.b /= fC;
        this.d.j(fC);
    }

    public final void h(ra1 ra1Var, ce2 ce2Var, boolean z) {
        if (ce2Var == null || !ce2Var.n) {
            return;
        }
        float fI = this.d.i(ce2Var);
        this.b = (ce2Var.m * fI) + this.b;
        this.d.c(ce2Var, z);
        if (z) {
            ce2Var.b(this);
        }
        if (this.d.g() == 0) {
            this.e = true;
            ra1Var.a = true;
        }
    }

    public void i(ra1 ra1Var, pb pbVar, boolean z) {
        float fB = this.d.b(pbVar, z);
        this.b = (pbVar.b * fB) + this.b;
        if (z) {
            pbVar.a.b(this);
        }
        if (this.a == null || this.d.g() != 0) {
            return;
        }
        this.e = true;
        ra1Var.a = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            p.ce2 r0 = r10.a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            p.ce2 r1 = r10.a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = p.u4.h(r0, r1)
            float r1 = r10.b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L39
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            float r0 = r10.b
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = r3
            goto L3a
        L39:
            r1 = r4
        L3a:
            p.ob r5 = r10.d
            int r5 = r5.g()
        L40:
            if (r4 >= r5) goto La0
            p.ob r6 = r10.d
            p.ce2 r6 = r6.h(r4)
            if (r6 != 0) goto L4b
            goto L9d
        L4b:
            p.ob r7 = r10.d
            float r7 = r7.a(r4)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L56
            goto L9d
        L56:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L6a
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L7a
            java.lang.String r1 = "- "
            java.lang.String r0 = p.u4.h(r0, r1)
        L68:
            float r7 = r7 * r9
            goto L7a
        L6a:
            if (r8 <= 0) goto L73
            java.lang.String r1 = " + "
            java.lang.String r0 = p.u4.h(r0, r1)
            goto L7a
        L73:
            java.lang.String r1 = " - "
            java.lang.String r0 = p.u4.h(r0, r1)
            goto L68
        L7a:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L85
            java.lang.String r0 = p.u4.h(r0, r6)
            goto L9c
        L85:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L9c:
            r1 = r3
        L9d:
            int r4 = r4 + 1
            goto L40
        La0:
            if (r1 != 0) goto La8
            java.lang.String r1 = "0.0"
            java.lang.String r0 = p.u4.h(r0, r1)
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.pb.toString():java.lang.String");
    }
}
