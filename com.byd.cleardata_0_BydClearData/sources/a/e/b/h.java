package a.e.b;

import a.e.b.b;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class h extends a.e.b.b {
    private int g;
    private i[] h;
    private i[] i;
    private int j;
    b k;
    c l;

    class a implements Comparator<i> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(i iVar, i iVar2) {
            return iVar.f192d - iVar2.f192d;
        }
    }

    class b implements Comparable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        i f186b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        h f187c;

        public b(h hVar) {
            this.f187c = hVar;
        }

        public boolean a(i iVar, float f) {
            boolean z = true;
            if (!this.f186b.f190b) {
                for (int i = 0; i < 9; i++) {
                    float f2 = iVar.j[i];
                    if (f2 != 0.0f) {
                        float f3 = f2 * f;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        this.f186b.j[i] = f3;
                    } else {
                        this.f186b.j[i] = 0.0f;
                    }
                }
                return true;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f186b.j;
                fArr[i2] = fArr[i2] + (iVar.j[i2] * f);
                if (Math.abs(fArr[i2]) < 1.0E-4f) {
                    this.f186b.j[i2] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                h.this.G(this.f186b);
            }
            return false;
        }

        public void b(i iVar) {
            this.f186b = iVar;
        }

        public final boolean c() {
            for (int i = 8; i >= 0; i--) {
                float f = this.f186b.j[i];
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
            return this.f186b.f192d - ((i) obj).f192d;
        }

        public final boolean d(i iVar) {
            int i = 8;
            while (true) {
                if (i < 0) {
                    break;
                }
                float f = iVar.j[i];
                float f2 = this.f186b.j[i];
                if (f2 == f) {
                    i--;
                } else if (f2 < f) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f186b.j, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f186b != null) {
                for (int i = 0; i < 9; i++) {
                    str = str + this.f186b.j[i] + " ";
                }
            }
            return str + "] " + this.f186b;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.g = 128;
        this.h = new i[128];
        this.i = new i[128];
        this.j = 0;
        this.k = new b(this);
        this.l = cVar;
    }

    private final void F(i iVar) {
        int i;
        int i2 = this.j + 1;
        i[] iVarArr = this.h;
        if (i2 > iVarArr.length) {
            i[] iVarArr2 = (i[]) Arrays.copyOf(iVarArr, iVarArr.length * 2);
            this.h = iVarArr2;
            this.i = (i[]) Arrays.copyOf(iVarArr2, iVarArr2.length * 2);
        }
        i[] iVarArr3 = this.h;
        int i3 = this.j;
        iVarArr3[i3] = iVar;
        int i4 = i3 + 1;
        this.j = i4;
        if (i4 > 1 && iVarArr3[i4 - 1].f192d > iVar.f192d) {
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
        iVar.f190b = true;
        iVar.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G(i iVar) {
        int i = 0;
        while (i < this.j) {
            if (this.h[i] == iVar) {
                while (true) {
                    int i2 = this.j;
                    if (i >= i2 - 1) {
                        this.j = i2 - 1;
                        iVar.f190b = false;
                        return;
                    } else {
                        i[] iVarArr = this.h;
                        int i3 = i + 1;
                        iVarArr[i] = iVarArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // a.e.b.b
    public void C(a.e.b.b bVar, boolean z) {
        i iVar = bVar.f171a;
        if (iVar == null) {
            return;
        }
        b.a aVar = bVar.e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            i iVarF = aVar.f(i);
            float fA = aVar.a(i);
            this.k.b(iVarF);
            if (this.k.a(iVar, fA)) {
                F(iVarF);
            }
            this.f172b += bVar.f172b * fA;
        }
        G(iVar);
    }

    @Override // a.e.b.b, a.e.b.d.a
    public void b(i iVar) {
        this.k.b(iVar);
        this.k.e();
        iVar.j[iVar.f] = 1.0f;
        F(iVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    @Override // a.e.b.b, a.e.b.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public a.e.b.i c(a.e.b.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.j
            if (r0 >= r2) goto L30
            a.e.b.i[] r2 = r4.h
            r2 = r2[r0]
            int r3 = r2.f192d
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2d
        L12:
            a.e.b.h$b r3 = r4.k
            r3.b(r2)
            a.e.b.h$b r2 = r4.k
            if (r1 != r5) goto L22
            boolean r2 = r2.c()
            if (r2 == 0) goto L2d
            goto L2c
        L22:
            a.e.b.i[] r3 = r4.h
            r3 = r3[r1]
            boolean r2 = r2.d(r3)
            if (r2 == 0) goto L2d
        L2c:
            r1 = r0
        L2d:
            int r0 = r0 + 1
            goto L3
        L30:
            if (r1 != r5) goto L34
            r5 = 0
            return r5
        L34:
            a.e.b.i[] r5 = r4.h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.h.c(a.e.b.d, boolean[]):a.e.b.i");
    }

    @Override // a.e.b.b, a.e.b.d.a
    public void clear() {
        this.j = 0;
        this.f172b = 0.0f;
    }

    @Override // a.e.b.b
    public String toString() {
        String str = " goal -> (" + this.f172b + ") : ";
        for (int i = 0; i < this.j; i++) {
            this.k.b(this.h[i]);
            str = str + this.k + " ";
        }
        return str;
    }
}
