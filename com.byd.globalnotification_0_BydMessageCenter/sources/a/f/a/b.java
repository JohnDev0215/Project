package a.f.a;

import a.f.a.d;
import a.f.a.g;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f504c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f506e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f502a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f503b = 0.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<g> f505d = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f507f = false;

    public interface a {
        float a(int i);

        float b(g gVar);

        float c(g gVar, boolean z);

        void clear();

        g d(int i);

        void e(g gVar, float f2, boolean z);

        boolean f(g gVar);

        void g(float f2);

        float h(b bVar, boolean z);

        void i();

        void j(g gVar, float f2);

        int k();
    }

    public b() {
    }

    public b(c cVar) {
        this.f506e = new a.f.a.a(this, cVar);
    }

    @Override // a.f.a.d.a
    public g a(d dVar, boolean[] zArr) {
        return i(zArr, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0021  */
    @Override // a.f.a.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(a.f.a.g r3) {
        /*
            r2 = this;
            int r0 = r3.f526e
            r1 = 1
            if (r0 != r1) goto L6
            goto L21
        L6:
            r1 = 2
            if (r0 != r1) goto Lc
            r0 = 1148846080(0x447a0000, float:1000.0)
            goto L23
        Lc:
            r1 = 3
            if (r0 != r1) goto L13
            r0 = 1232348160(0x49742400, float:1000000.0)
            goto L23
        L13:
            r1 = 4
            if (r0 != r1) goto L1a
            r0 = 1315859240(0x4e6e6b28, float:1.0E9)
            goto L23
        L1a:
            r1 = 5
            if (r0 != r1) goto L21
            r0 = 1399379109(0x5368d4a5, float:1.0E12)
            goto L23
        L21:
            r0 = 1065353216(0x3f800000, float:1.0)
        L23:
            a.f.a.b$a r2 = r2.f506e
            r2.j(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.b.b(a.f.a.g):void");
    }

    public b c(d dVar, int i) {
        this.f506e.j(dVar.k(i, "ep"), 1.0f);
        this.f506e.j(dVar.k(i, "em"), -1.0f);
        return this;
    }

    @Override // a.f.a.d.a
    public void clear() {
        this.f506e.clear();
        this.f502a = null;
        this.f503b = 0.0f;
    }

    public b d(g gVar, g gVar2, g gVar3, g gVar4, float f2) {
        this.f506e.j(gVar, -1.0f);
        this.f506e.j(gVar2, 1.0f);
        this.f506e.j(gVar3, f2);
        this.f506e.j(gVar4, -f2);
        return this;
    }

    public b e(g gVar, g gVar2, g gVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f503b = i;
        }
        if (z) {
            this.f506e.j(gVar, 1.0f);
            this.f506e.j(gVar2, -1.0f);
            this.f506e.j(gVar3, -1.0f);
        } else {
            this.f506e.j(gVar, -1.0f);
            this.f506e.j(gVar2, 1.0f);
            this.f506e.j(gVar3, 1.0f);
        }
        return this;
    }

    public b f(g gVar, g gVar2, g gVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f503b = i;
        }
        if (z) {
            this.f506e.j(gVar, 1.0f);
            this.f506e.j(gVar2, -1.0f);
            this.f506e.j(gVar3, 1.0f);
        } else {
            this.f506e.j(gVar, -1.0f);
            this.f506e.j(gVar2, 1.0f);
            this.f506e.j(gVar3, -1.0f);
        }
        return this;
    }

    public b g(g gVar, g gVar2, g gVar3, g gVar4, float f2) {
        this.f506e.j(gVar3, 0.5f);
        this.f506e.j(gVar4, 0.5f);
        this.f506e.j(gVar, -0.5f);
        this.f506e.j(gVar2, -0.5f);
        this.f503b = -f2;
        return this;
    }

    public final boolean h(g gVar) {
        return gVar.m <= 1;
    }

    public final g i(boolean[] zArr, g gVar) {
        g.a aVar;
        int iK = this.f506e.k();
        g gVar2 = null;
        float f2 = 0.0f;
        for (int i = 0; i < iK; i++) {
            float fA = this.f506e.a(i);
            if (fA < 0.0f) {
                g gVarD = this.f506e.d(i);
                if ((zArr == null || !zArr[gVarD.f524c]) && gVarD != gVar && (((aVar = gVarD.j) == g.a.SLACK || aVar == g.a.ERROR) && fA < f2)) {
                    f2 = fA;
                    gVar2 = gVarD;
                }
            }
        }
        return gVar2;
    }

    @Override // a.f.a.d.a
    public boolean isEmpty() {
        return this.f502a == null && this.f503b == 0.0f && this.f506e.k() == 0;
    }

    public void j(g gVar) {
        g gVar2 = this.f502a;
        if (gVar2 != null) {
            this.f506e.j(gVar2, -1.0f);
            this.f502a.f525d = -1;
            this.f502a = null;
        }
        float fC = this.f506e.c(gVar, true) * (-1.0f);
        this.f502a = gVar;
        if (fC == 1.0f) {
            return;
        }
        this.f503b /= fC;
        this.f506e.g(fC);
    }

    public void k(d dVar, g gVar, boolean z) {
        if (gVar.g) {
            float fB = this.f506e.b(gVar);
            this.f503b = (gVar.f527f * fB) + this.f503b;
            this.f506e.c(gVar, z);
            if (z) {
                gVar.b(this);
            }
            if (this.f506e.k() == 0) {
                this.f507f = true;
                dVar.f512a = true;
            }
        }
    }

    public void l(d dVar, b bVar, boolean z) {
        float fH = this.f506e.h(bVar, z);
        this.f503b = (bVar.f503b * fH) + this.f503b;
        if (z) {
            bVar.f502a.b(this);
        }
        if (this.f502a == null || this.f506e.k() != 0) {
            return;
        }
        this.f507f = true;
        dVar.f512a = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            a.f.a.g r0 = r10.f502a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L16
        L7:
            java.lang.String r0 = ""
            java.lang.StringBuilder r0 = b.a.a.a.a.d(r0)
            a.f.a.g r1 = r10.f502a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L16:
            java.lang.String r1 = " = "
            java.lang.String r0 = b.a.a.a.a.c(r0, r1)
            float r1 = r10.f503b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L34
            java.lang.StringBuilder r0 = b.a.a.a.a.d(r0)
            float r1 = r10.f503b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = r4
            goto L35
        L34:
            r1 = r3
        L35:
            a.f.a.b$a r5 = r10.f506e
            int r5 = r5.k()
        L3b:
            if (r3 >= r5) goto La5
            a.f.a.b$a r6 = r10.f506e
            a.f.a.g r6 = r6.d(r3)
            if (r6 != 0) goto L46
            goto La2
        L46:
            a.f.a.b$a r7 = r10.f506e
            float r7 = r7.a(r3)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L51
            goto La2
        L51:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L64
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L7e
            java.lang.StringBuilder r0 = b.a.a.a.a.d(r0)
            java.lang.String r1 = "- "
            goto L76
        L64:
            java.lang.StringBuilder r0 = b.a.a.a.a.d(r0)
            if (r8 <= 0) goto L74
            java.lang.String r1 = " + "
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            goto L7e
        L74:
            java.lang.String r1 = " - "
        L76:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            float r7 = r7 * r9
        L7e:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L8a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto L97
        L8a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
        L97:
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            r1 = r4
        La2:
            int r3 = r3 + 1
            goto L3b
        La5:
            if (r1 != 0) goto Lad
            java.lang.String r10 = "0.0"
            java.lang.String r0 = b.a.a.a.a.c(r0, r10)
        Lad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.b.toString():java.lang.String");
    }
}
