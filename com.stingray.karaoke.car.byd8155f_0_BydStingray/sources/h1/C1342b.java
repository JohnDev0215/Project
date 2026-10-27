package h1;

import java.util.ArrayList;
import l3.P;

/* JADX INFO: renamed from: h1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1342b {
    public final C1341a d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f15165a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f15166b = 0.0f;
    public final ArrayList c = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f15167e = false;

    public C1342b(P p10) {
        this.d = new C1341a(this, p10);
    }

    public final void a(c cVar, int i7) {
        this.d.g(cVar.j(i7), 1.0f);
        this.d.g(cVar.j(i7), -1.0f);
    }

    public final void b(e eVar, e eVar2, e eVar3, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.f15166b = i7;
        }
        if (z6) {
            this.d.g(eVar, 1.0f);
            this.d.g(eVar2, -1.0f);
            this.d.g(eVar3, -1.0f);
        } else {
            this.d.g(eVar, -1.0f);
            this.d.g(eVar2, 1.0f);
            this.d.g(eVar3, 1.0f);
        }
    }

    public final void c(e eVar, e eVar2, e eVar3, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.f15166b = i7;
        }
        if (z6) {
            this.d.g(eVar, 1.0f);
            this.d.g(eVar2, -1.0f);
            this.d.g(eVar3, 1.0f);
        } else {
            this.d.g(eVar, -1.0f);
            this.d.g(eVar2, 1.0f);
            this.d.g(eVar3, -1.0f);
        }
    }

    public e d(boolean[] zArr) {
        return f(zArr, null);
    }

    public boolean e() {
        return this.f15165a == null && this.f15166b == 0.0f && this.d.d() == 0;
    }

    public final e f(boolean[] zArr, e eVar) {
        int i7;
        int iD = this.d.d();
        e eVar2 = null;
        float f10 = 0.0f;
        for (int i10 = 0; i10 < iD; i10++) {
            float f11 = this.d.f(i10);
            if (f11 < 0.0f) {
                e eVarE = this.d.e(i10);
                if ((zArr == null || !zArr[eVarE.f15185m]) && eVarE != eVar && (((i7 = eVarE.f15194w) == 3 || i7 == 4) && f11 < f10)) {
                    f10 = f11;
                    eVar2 = eVarE;
                }
            }
        }
        return eVar2;
    }

    public final void g(e eVar) {
        e eVar2 = this.f15165a;
        if (eVar2 != null) {
            this.d.g(eVar2, -1.0f);
            this.f15165a.f15186n = -1;
            this.f15165a = null;
        }
        float fH = this.d.h(eVar, true) * (-1.0f);
        this.f15165a = eVar;
        if (fH == 1.0f) {
            return;
        }
        this.f15166b /= fH;
        C1341a c1341a = this.d;
        int i7 = c1341a.f15162h;
        for (int i10 = 0; i7 != -1 && i10 < c1341a.f15157a; i10++) {
            float[] fArr = c1341a.f15161g;
            fArr[i7] = fArr[i7] / fH;
            i7 = c1341a.f15160f[i7];
        }
    }

    public final void h(c cVar, e eVar, boolean z6) {
        if (eVar == null || !eVar.f15188q) {
            return;
        }
        float fC = this.d.c(eVar);
        this.f15166b = (eVar.f15187p * fC) + this.f15166b;
        this.d.h(eVar, z6);
        if (z6) {
            eVar.b(this);
        }
        if (this.d.d() == 0) {
            this.f15167e = true;
            cVar.f15170b = true;
        }
    }

    public void i(c cVar, C1342b c1342b, boolean z6) {
        C1341a c1341a = this.d;
        c1341a.getClass();
        float fC = c1341a.c(c1342b.f15165a);
        c1341a.h(c1342b.f15165a, z6);
        C1341a c1341a2 = c1342b.d;
        int iD = c1341a2.d();
        for (int i7 = 0; i7 < iD; i7++) {
            e eVarE = c1341a2.e(i7);
            c1341a.a(eVarE, c1341a2.c(eVarE) * fC, z6);
        }
        this.f15166b = (c1342b.f15166b * fC) + this.f15166b;
        if (z6) {
            c1342b.f15165a.b(this);
        }
        if (this.f15165a == null || this.d.d() != 0) {
            return;
        }
        this.f15167e = true;
        cVar.f15170b = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            h1.e r0 = r10.f15165a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            h1.e r1 = r10.f15165a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = android.hardware.bydauto.ac.tsimpl.a.h(r0, r1)
            float r1 = r10.f15166b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L35
            java.lang.StringBuilder r0 = M5.b.i(r0)
            float r1 = r10.f15166b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = r3
            goto L36
        L35:
            r1 = r4
        L36:
            h1.a r5 = r10.d
            int r5 = r5.d()
        L3c:
            if (r4 >= r5) goto L9c
            h1.a r6 = r10.d
            h1.e r6 = r6.e(r4)
            if (r6 != 0) goto L47
            goto L99
        L47:
            h1.a r7 = r10.d
            float r7 = r7.f(r4)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L52
            goto L99
        L52:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L66
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L76
            java.lang.String r1 = "- "
            java.lang.String r0 = android.hardware.bydauto.ac.tsimpl.a.h(r0, r1)
        L64:
            float r7 = r7 * r9
            goto L76
        L66:
            if (r8 <= 0) goto L6f
            java.lang.String r1 = " + "
            java.lang.String r0 = android.hardware.bydauto.ac.tsimpl.a.h(r0, r1)
            goto L76
        L6f:
            java.lang.String r1 = " - "
            java.lang.String r0 = android.hardware.bydauto.ac.tsimpl.a.h(r0, r1)
            goto L64
        L76:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L81
            java.lang.String r0 = android.hardware.bydauto.ac.tsimpl.a.h(r0, r6)
            goto L98
        L81:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L98:
            r1 = r3
        L99:
            int r4 = r4 + 1
            goto L3c
        L9c:
            if (r1 != 0) goto La4
            java.lang.String r10 = "0.0"
            java.lang.String r0 = android.hardware.bydauto.ac.tsimpl.a.h(r0, r10)
        La4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.C1342b.toString():java.lang.String");
    }
}
