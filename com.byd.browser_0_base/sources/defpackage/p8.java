package defpackage;

import defpackage.q71;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class p8 implements q71.a {
    public a d;
    public hq2 a = null;
    public float b = 0.0f;
    public final ArrayList<hq2> c = new ArrayList<>();
    public boolean e = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(hq2 hq2Var);

        boolean b(hq2 hq2Var);

        int c();

        void clear();

        hq2 d(int i);

        void e(hq2 hq2Var, float f, boolean z);

        void f();

        float g(int i);

        float h(hq2 hq2Var, boolean z);

        float i(p8 p8Var, boolean z);

        void j(float f);

        void k(hq2 hq2Var, float f);
    }

    public p8() {
    }

    @Override // q71.a
    public hq2 a(boolean[] zArr) {
        return f(zArr, null);
    }

    public final void b(q71 q71Var, int i) {
        this.d.k(q71Var.j(i), 1.0f);
        this.d.k(q71Var.j(i), -1.0f);
    }

    public final void c(hq2 hq2Var, hq2 hq2Var2, hq2 hq2Var3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.d.k(hq2Var, 1.0f);
            this.d.k(hq2Var2, -1.0f);
            this.d.k(hq2Var3, -1.0f);
        } else {
            this.d.k(hq2Var, -1.0f);
            this.d.k(hq2Var2, 1.0f);
            this.d.k(hq2Var3, 1.0f);
        }
    }

    public final void d(hq2 hq2Var, hq2 hq2Var2, hq2 hq2Var3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.d.k(hq2Var, 1.0f);
            this.d.k(hq2Var2, -1.0f);
            this.d.k(hq2Var3, 1.0f);
        } else {
            this.d.k(hq2Var, -1.0f);
            this.d.k(hq2Var2, 1.0f);
            this.d.k(hq2Var3, -1.0f);
        }
    }

    public boolean e() {
        return this.a == null && this.b == 0.0f && this.d.c() == 0;
    }

    public final hq2 f(boolean[] zArr, hq2 hq2Var) {
        int i;
        int iC = this.d.c();
        hq2 hq2Var2 = null;
        float f = 0.0f;
        for (int i2 = 0; i2 < iC; i2++) {
            float fG = this.d.g(i2);
            if (fG < 0.0f) {
                hq2 hq2VarD = this.d.d(i2);
                if ((zArr == null || !zArr[hq2VarD.f]) && hq2VarD != hq2Var && (((i = hq2VarD.m) == 3 || i == 4) && fG < f)) {
                    f = fG;
                    hq2Var2 = hq2VarD;
                }
            }
        }
        return hq2Var2;
    }

    public final void g(hq2 hq2Var) {
        hq2 hq2Var2 = this.a;
        if (hq2Var2 != null) {
            this.d.k(hq2Var2, -1.0f);
            this.a.g = -1;
            this.a = null;
        }
        float fH = this.d.h(hq2Var, true) * (-1.0f);
        this.a = hq2Var;
        if (fH == 1.0f) {
            return;
        }
        this.b /= fH;
        this.d.j(fH);
    }

    public final void h(q71 q71Var, hq2 hq2Var, boolean z) {
        if (hq2Var == null || !hq2Var.j) {
            return;
        }
        float fA = this.d.a(hq2Var);
        this.b = (hq2Var.i * fA) + this.b;
        this.d.h(hq2Var, z);
        if (z) {
            hq2Var.e(this);
        }
        if (this.d.c() == 0) {
            this.e = true;
            q71Var.a = true;
        }
    }

    public void i(q71 q71Var, p8 p8Var, boolean z) {
        float fI = this.d.i(p8Var, z);
        this.b = (p8Var.b * fI) + this.b;
        if (z) {
            p8Var.a.e(this);
        }
        if (this.a == null || this.d.c() != 0) {
            return;
        }
        this.e = true;
        q71Var.a = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r9 = this;
            hq2 r0 = r9.a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            hq2 r1 = r9.a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = defpackage.gq2.f(r0, r1)
            float r1 = r9.b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L35
            java.lang.StringBuilder r0 = defpackage.C0173a.j(r0)
            float r1 = r9.b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = r3
            goto L36
        L35:
            r1 = r4
        L36:
            p8$a r5 = r9.d
            int r5 = r5.c()
        L3c:
            if (r4 >= r5) goto L9b
            p8$a r6 = r9.d
            hq2 r6 = r6.d(r4)
            if (r6 != 0) goto L47
            goto L98
        L47:
            p8$a r7 = r9.d
            float r7 = r7.g(r4)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L52
            goto L98
        L52:
            java.lang.String r6 = r6.toString()
            if (r1 != 0) goto L63
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L75
            java.lang.String r1 = "- "
            java.lang.String r0 = defpackage.gq2.f(r0, r1)
            goto L72
        L63:
            if (r8 <= 0) goto L6c
            java.lang.String r1 = " + "
            java.lang.String r0 = defpackage.gq2.f(r0, r1)
            goto L75
        L6c:
            java.lang.String r1 = " - "
            java.lang.String r0 = defpackage.gq2.f(r0, r1)
        L72:
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r7 = r7 * r1
        L75:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L80
            java.lang.String r0 = defpackage.gq2.f(r0, r6)
            goto L97
        L80:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L97:
            r1 = r3
        L98:
            int r4 = r4 + 1
            goto L3c
        L9b:
            if (r1 != 0) goto La3
            java.lang.String r9 = "0.0"
            java.lang.String r0 = defpackage.gq2.f(r0, r9)
        La3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.p8.toString():java.lang.String");
    }

    public p8(pk pkVar) {
        this.d = new k8(this, pkVar);
    }
}
