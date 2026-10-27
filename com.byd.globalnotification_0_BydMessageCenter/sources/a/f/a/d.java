package a.f.a;

import a.f.a.b;
import a.f.a.g;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static boolean r = false;
    public static int s = 1000;
    public static long t;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f515d;
    public b[] g;
    public final c n;
    public a q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f512a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f513b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HashMap<String, g> f514c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f516e = 32;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f517f = 32;
    public boolean h = false;
    public boolean i = false;
    public boolean[] j = new boolean[32];
    public int k = 1;
    public int l = 0;
    public int m = 32;
    public g[] o = new g[s];
    public int p = 0;

    public interface a {
        g a(d dVar, boolean[] zArr);

        void b(g gVar);

        void clear();

        boolean isEmpty();
    }

    public d() {
        this.g = null;
        this.g = new b[32];
        t();
        c cVar = new c();
        this.n = cVar;
        this.f515d = new f(cVar);
        this.q = new b(this.n);
    }

    public final g a(g.a aVar, String str) {
        g gVarA = this.n.f510c.a();
        if (gVarA == null) {
            gVarA = new g(aVar);
        } else {
            gVarA.c();
        }
        gVarA.j = aVar;
        int i = this.p;
        int i2 = s;
        if (i >= i2) {
            int i3 = i2 * 2;
            s = i3;
            this.o = (g[]) Arrays.copyOf(this.o, i3);
        }
        g[] gVarArr = this.o;
        int i4 = this.p;
        this.p = i4 + 1;
        gVarArr[i4] = gVarA;
        return gVarA;
    }

    public void b(g gVar, g gVar2, int i, float f2, g gVar3, g gVar4, int i2, int i3) {
        int i4;
        float f3;
        b bVarM = m();
        if (gVar2 == gVar3) {
            bVarM.f506e.j(gVar, 1.0f);
            bVarM.f506e.j(gVar4, 1.0f);
            bVarM.f506e.j(gVar2, -2.0f);
        } else {
            if (f2 == 0.5f) {
                bVarM.f506e.j(gVar, 1.0f);
                bVarM.f506e.j(gVar2, -1.0f);
                bVarM.f506e.j(gVar3, -1.0f);
                bVarM.f506e.j(gVar4, 1.0f);
                if (i > 0 || i2 > 0) {
                    i4 = (-i) + i2;
                    f3 = i4;
                }
            } else if (f2 <= 0.0f) {
                bVarM.f506e.j(gVar, -1.0f);
                bVarM.f506e.j(gVar2, 1.0f);
                f3 = i;
            } else if (f2 >= 1.0f) {
                bVarM.f506e.j(gVar4, -1.0f);
                bVarM.f506e.j(gVar3, 1.0f);
                i4 = -i2;
                f3 = i4;
            } else {
                float f4 = 1.0f - f2;
                bVarM.f506e.j(gVar, f4 * 1.0f);
                bVarM.f506e.j(gVar2, f4 * (-1.0f));
                bVarM.f506e.j(gVar3, (-1.0f) * f2);
                bVarM.f506e.j(gVar4, 1.0f * f2);
                if (i > 0 || i2 > 0) {
                    bVarM.f503b = (i2 * f2) + ((-i) * f4);
                }
            }
            bVarM.f503b = f3;
        }
        if (i3 != 8) {
            bVarM.c(this, i3);
        }
        c(bVarM);
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01ea A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(a.f.a.b r17) {
        /*
            Method dump skipped, instruction units count: 500
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.d.c(a.f.a.b):void");
    }

    public b d(g gVar, g gVar2, int i, int i2) {
        if (i2 == 8 && gVar2.g && gVar.f525d == -1) {
            gVar.d(this, gVar2.f527f + i);
            return null;
        }
        b bVarM = m();
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            bVarM.f503b = i;
        }
        if (z) {
            bVarM.f506e.j(gVar, 1.0f);
            bVarM.f506e.j(gVar2, -1.0f);
        } else {
            bVarM.f506e.j(gVar, -1.0f);
            bVarM.f506e.j(gVar2, 1.0f);
        }
        if (i2 != 8) {
            bVarM.c(this, i2);
        }
        c(bVarM);
        return bVarM;
    }

    public void e(g gVar, int i) {
        b bVarM;
        b.a aVar;
        float f2;
        int i2 = gVar.f525d;
        if (i2 == -1) {
            float f3 = i;
            gVar.d(this, f3);
            for (int i3 = 0; i3 < this.f513b + 1; i3++) {
                g gVar2 = this.n.f511d[i3];
                if (gVar2 != null && gVar2.n && gVar2.o == gVar.f524c) {
                    gVar2.d(this, gVar2.p + f3);
                }
            }
            return;
        }
        if (i2 != -1) {
            b bVar = this.g[i2];
            if (!bVar.f507f) {
                if (bVar.f506e.k() == 0) {
                    bVar.f507f = true;
                } else {
                    bVarM = m();
                    if (i < 0) {
                        bVarM.f503b = i * (-1);
                        aVar = bVarM.f506e;
                        f2 = 1.0f;
                    } else {
                        bVarM.f503b = i;
                        aVar = bVarM.f506e;
                        f2 = -1.0f;
                    }
                    aVar.j(gVar, f2);
                }
            }
            bVar.f503b = i;
            return;
        }
        bVarM = m();
        bVarM.f502a = gVar;
        float f4 = i;
        gVar.f527f = f4;
        bVarM.f503b = f4;
        bVarM.f507f = true;
        c(bVarM);
    }

    public void f(g gVar, g gVar2, int i, int i2) {
        b bVarM = m();
        g gVarN = n();
        gVarN.f526e = 0;
        bVarM.e(gVar, gVar2, gVarN, i);
        if (i2 != 8) {
            bVarM.f506e.j(k(i2, null), (int) (bVarM.f506e.b(gVarN) * (-1.0f)));
        }
        c(bVarM);
    }

    public void g(g gVar, g gVar2, int i, int i2) {
        b bVarM = m();
        g gVarN = n();
        gVarN.f526e = 0;
        bVarM.f(gVar, gVar2, gVarN, i);
        if (i2 != 8) {
            bVarM.f506e.j(k(i2, null), (int) (bVarM.f506e.b(gVarN) * (-1.0f)));
        }
        c(bVarM);
    }

    public void h(g gVar, g gVar2, g gVar3, g gVar4, float f2, int i) {
        b bVarM = m();
        bVarM.d(gVar, gVar2, gVar3, gVar4, f2);
        if (i != 8) {
            bVarM.c(this, i);
        }
        c(bVarM);
    }

    public final void i(b bVar) {
        int i;
        if (bVar.f507f) {
            bVar.f502a.d(this, bVar.f503b);
        } else {
            b[] bVarArr = this.g;
            int i2 = this.l;
            bVarArr[i2] = bVar;
            g gVar = bVar.f502a;
            gVar.f525d = i2;
            this.l = i2 + 1;
            gVar.e(this, bVar);
        }
        if (this.f512a) {
            int i3 = 0;
            while (i3 < this.l) {
                if (this.g[i3] == null) {
                    System.out.println("WTF");
                }
                b[] bVarArr2 = this.g;
                if (bVarArr2[i3] != null && bVarArr2[i3].f507f) {
                    b bVar2 = bVarArr2[i3];
                    bVar2.f502a.d(this, bVar2.f503b);
                    this.n.f509b.b(bVar2);
                    this.g[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.l;
                        if (i4 >= i) {
                            break;
                        }
                        b[] bVarArr3 = this.g;
                        int i6 = i4 - 1;
                        bVarArr3[i6] = bVarArr3[i4];
                        if (bVarArr3[i6].f502a.f525d == i4) {
                            bVarArr3[i6].f502a.f525d = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        this.g[i5] = null;
                    }
                    this.l--;
                    i3--;
                }
                i3++;
            }
            this.f512a = false;
        }
    }

    public final void j() {
        for (int i = 0; i < this.l; i++) {
            b bVar = this.g[i];
            bVar.f502a.f527f = bVar.f503b;
        }
    }

    public g k(int i, String str) {
        if (this.k + 1 >= this.f517f) {
            p();
        }
        g gVarA = a(g.a.ERROR, str);
        int i2 = this.f513b + 1;
        this.f513b = i2;
        this.k++;
        gVarA.f524c = i2;
        gVarA.f526e = i;
        this.n.f511d[i2] = gVarA;
        this.f515d.b(gVarA);
        return gVarA;
    }

    public g l(Object obj) {
        g gVar = null;
        if (obj == null) {
            return null;
        }
        if (this.k + 1 >= this.f517f) {
            p();
        }
        if (obj instanceof a.f.a.i.c) {
            a.f.a.i.c cVar = (a.f.a.i.c) obj;
            gVar = cVar.i;
            if (gVar == null) {
                cVar.i();
                gVar = cVar.i;
            }
            int i = gVar.f524c;
            if (i == -1 || i > this.f513b || this.n.f511d[i] == null) {
                if (gVar.f524c != -1) {
                    gVar.c();
                }
                int i2 = this.f513b + 1;
                this.f513b = i2;
                this.k++;
                gVar.f524c = i2;
                gVar.j = g.a.UNRESTRICTED;
                this.n.f511d[i2] = gVar;
            }
        }
        return gVar;
    }

    public b m() {
        b bVarA = this.n.f509b.a();
        if (bVarA == null) {
            bVarA = new b(this.n);
            t++;
        } else {
            bVarA.f502a = null;
            bVarA.f506e.clear();
            bVarA.f503b = 0.0f;
            bVarA.f507f = false;
        }
        g.q++;
        return bVarA;
    }

    public g n() {
        if (this.k + 1 >= this.f517f) {
            p();
        }
        g gVarA = a(g.a.SLACK, null);
        int i = this.f513b + 1;
        this.f513b = i;
        this.k++;
        gVarA.f524c = i;
        this.n.f511d[i] = gVarA;
        return gVarA;
    }

    public int o(Object obj) {
        g gVar = ((a.f.a.i.c) obj).i;
        if (gVar != null) {
            return (int) (gVar.f527f + 0.5f);
        }
        return 0;
    }

    public final void p() {
        int i = this.f516e * 2;
        this.f516e = i;
        this.g = (b[]) Arrays.copyOf(this.g, i);
        c cVar = this.n;
        cVar.f511d = (g[]) Arrays.copyOf(cVar.f511d, this.f516e);
        int i2 = this.f516e;
        this.j = new boolean[i2];
        this.f517f = i2;
        this.m = i2;
    }

    public void q() {
        if (this.f515d.isEmpty()) {
            j();
            return;
        }
        if (this.h || this.i) {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.l) {
                    z = true;
                    break;
                } else if (!this.g[i].f507f) {
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                j();
                return;
            }
        }
        r(this.f515d);
    }

    public void r(a aVar) {
        float f2;
        int i;
        boolean z;
        g.a aVar2 = g.a.UNRESTRICTED;
        int i2 = 0;
        while (true) {
            f2 = 0.0f;
            i = 1;
            if (i2 >= this.l) {
                z = false;
                break;
            }
            b[] bVarArr = this.g;
            if (bVarArr[i2].f502a.j != aVar2 && bVarArr[i2].f503b < 0.0f) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            boolean z2 = false;
            int i3 = 0;
            while (!z2) {
                i3 += i;
                float f3 = Float.MAX_VALUE;
                int i4 = 0;
                int i5 = -1;
                int i6 = -1;
                int i7 = 0;
                while (i4 < this.l) {
                    b bVar = this.g[i4];
                    if (bVar.f502a.j != aVar2 && !bVar.f507f && bVar.f503b < f2) {
                        int iK = bVar.f506e.k();
                        int i8 = 0;
                        while (i8 < iK) {
                            g gVarD = bVar.f506e.d(i8);
                            float fB = bVar.f506e.b(gVarD);
                            if (fB > f2) {
                                for (int i9 = 0; i9 < 9; i9++) {
                                    float f4 = gVarD.h[i9] / fB;
                                    if ((f4 < f3 && i9 == i7) || i9 > i7) {
                                        i6 = gVarD.f524c;
                                        i7 = i9;
                                        f3 = f4;
                                        i5 = i4;
                                    }
                                }
                            }
                            i8++;
                            f2 = 0.0f;
                        }
                    }
                    i4++;
                    f2 = 0.0f;
                }
                if (i5 != -1) {
                    b bVar2 = this.g[i5];
                    bVar2.f502a.f525d = -1;
                    bVar2.j(this.n.f511d[i6]);
                    g gVar = bVar2.f502a;
                    gVar.f525d = i5;
                    gVar.e(this, bVar2);
                } else {
                    z2 = true;
                }
                if (i3 > this.k / 2) {
                    z2 = true;
                }
                f2 = 0.0f;
                i = 1;
            }
        }
        s(aVar);
        j();
    }

    public final int s(a aVar) {
        for (int i = 0; i < this.k; i++) {
            this.j[i] = false;
        }
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2++;
            if (i2 >= this.k * 2) {
                return i2;
            }
            g gVar = ((b) aVar).f502a;
            if (gVar != null) {
                this.j[gVar.f524c] = true;
            }
            g gVarA = aVar.a(this, this.j);
            if (gVarA != null) {
                boolean[] zArr = this.j;
                int i3 = gVarA.f524c;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (gVarA != null) {
                float f2 = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.l; i5++) {
                    b bVar = this.g[i5];
                    if (bVar.f502a.j != g.a.UNRESTRICTED && !bVar.f507f && bVar.f506e.f(gVarA)) {
                        float fB = bVar.f506e.b(gVarA);
                        if (fB < 0.0f) {
                            float f3 = (-bVar.f503b) / fB;
                            if (f3 < f2) {
                                i4 = i5;
                                f2 = f3;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    b bVar2 = this.g[i4];
                    bVar2.f502a.f525d = -1;
                    bVar2.j(gVarA);
                    g gVar2 = bVar2.f502a;
                    gVar2.f525d = i4;
                    gVar2.e(this, bVar2);
                }
            } else {
                z = true;
            }
        }
        return i2;
    }

    public final void t() {
        for (int i = 0; i < this.l; i++) {
            b bVar = this.g[i];
            if (bVar != null) {
                this.n.f509b.b(bVar);
            }
            this.g[i] = null;
        }
    }

    public void u() {
        c cVar;
        int i = 0;
        while (true) {
            cVar = this.n;
            g[] gVarArr = cVar.f511d;
            if (i >= gVarArr.length) {
                break;
            }
            g gVar = gVarArr[i];
            if (gVar != null) {
                gVar.c();
            }
            i++;
        }
        e<g> eVar = cVar.f510c;
        g[] gVarArr2 = this.o;
        int length = this.p;
        if (eVar == null) {
            throw null;
        }
        if (length > gVarArr2.length) {
            length = gVarArr2.length;
        }
        for (int i2 = 0; i2 < length; i2++) {
            g gVar2 = gVarArr2[i2];
            int i3 = eVar.f519b;
            Object[] objArr = eVar.f518a;
            if (i3 < objArr.length) {
                objArr[i3] = gVar2;
                eVar.f519b = i3 + 1;
            }
        }
        this.p = 0;
        Arrays.fill(this.n.f511d, (Object) null);
        HashMap<String, g> map = this.f514c;
        if (map != null) {
            map.clear();
        }
        this.f513b = 0;
        this.f515d.clear();
        this.k = 1;
        for (int i4 = 0; i4 < this.l; i4++) {
            b[] bVarArr = this.g;
            if (bVarArr[i4] != null) {
                bVarArr[i4].f504c = false;
            }
        }
        t();
        this.l = 0;
        this.q = new b(this.n);
    }
}
