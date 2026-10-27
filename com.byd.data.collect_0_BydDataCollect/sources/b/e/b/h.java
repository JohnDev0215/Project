package b.e.b;

import b.e.b.b;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends b.e.b.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2382g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private i[] f2383h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private i[] f2384i;
    private int j;
    b k;
    c l;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class a implements Comparator<i> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(i iVar, i iVar2) {
            return iVar.f2392d - iVar2.f2392d;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class b implements Comparable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        i f2386b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        h f2387c;

        public b(h hVar) {
            this.f2387c = hVar;
        }

        public boolean a(i iVar, float f2) {
            boolean z = true;
            if (!this.f2386b.f2390b) {
                for (int i2 = 0; i2 < 9; i2++) {
                    float f3 = iVar.j[i2];
                    if (f3 != 0.0f) {
                        float f4 = f3 * f2;
                        if (Math.abs(f4) < 1.0E-4f) {
                            f4 = 0.0f;
                        }
                        this.f2386b.j[i2] = f4;
                    } else {
                        this.f2386b.j[i2] = 0.0f;
                    }
                }
                return true;
            }
            for (int i3 = 0; i3 < 9; i3++) {
                float[] fArr = this.f2386b.j;
                fArr[i3] = fArr[i3] + (iVar.j[i3] * f2);
                if (Math.abs(fArr[i3]) < 1.0E-4f) {
                    this.f2386b.j[i3] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                h.this.G(this.f2386b);
            }
            return false;
        }

        public void b(i iVar) {
            this.f2386b = iVar;
        }

        public final boolean c() {
            for (int i2 = 8; i2 >= 0; i2--) {
                float f2 = this.f2386b.j[i2];
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
            return this.f2386b.f2392d - ((i) obj).f2392d;
        }

        public final boolean d(i iVar) {
            int i2 = 8;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                float f2 = iVar.j[i2];
                float f3 = this.f2386b.j[i2];
                if (f3 == f2) {
                    i2--;
                } else if (f3 < f2) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f2386b.j, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f2386b != null) {
                for (int i2 = 0; i2 < 9; i2++) {
                    str = str + this.f2386b.j[i2] + " ";
                }
            }
            return str + "] " + this.f2386b;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.f2382g = 128;
        this.f2383h = new i[128];
        this.f2384i = new i[128];
        this.j = 0;
        this.k = new b(this);
        this.l = cVar;
    }

    private final void F(i iVar) {
        int i2;
        int i3 = this.j + 1;
        i[] iVarArr = this.f2383h;
        if (i3 > iVarArr.length) {
            i[] iVarArr2 = (i[]) Arrays.copyOf(iVarArr, iVarArr.length * 2);
            this.f2383h = iVarArr2;
            this.f2384i = (i[]) Arrays.copyOf(iVarArr2, iVarArr2.length * 2);
        }
        i[] iVarArr3 = this.f2383h;
        int i4 = this.j;
        iVarArr3[i4] = iVar;
        int i5 = i4 + 1;
        this.j = i5;
        if (i5 > 1 && iVarArr3[i5 - 1].f2392d > iVar.f2392d) {
            int i6 = 0;
            while (true) {
                i2 = this.j;
                if (i6 >= i2) {
                    break;
                }
                this.f2384i[i6] = this.f2383h[i6];
                i6++;
            }
            Arrays.sort(this.f2384i, 0, i2, new a());
            for (int i7 = 0; i7 < this.j; i7++) {
                this.f2383h[i7] = this.f2384i[i7];
            }
        }
        iVar.f2390b = true;
        iVar.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G(i iVar) {
        int i2 = 0;
        while (i2 < this.j) {
            if (this.f2383h[i2] == iVar) {
                while (true) {
                    int i3 = this.j;
                    if (i2 >= i3 - 1) {
                        this.j = i3 - 1;
                        iVar.f2390b = false;
                        return;
                    } else {
                        i[] iVarArr = this.f2383h;
                        int i4 = i2 + 1;
                        iVarArr[i2] = iVarArr[i4];
                        i2 = i4;
                    }
                }
            } else {
                i2++;
            }
        }
    }

    @Override // b.e.b.b
    public void C(b.e.b.b bVar, boolean z) {
        i iVar = bVar.f2360a;
        if (iVar == null) {
            return;
        }
        b.a aVar = bVar.f2364e;
        int iK = aVar.k();
        for (int i2 = 0; i2 < iK; i2++) {
            i iVarF = aVar.f(i2);
            float fA = aVar.a(i2);
            this.k.b(iVarF);
            if (this.k.a(iVar, fA)) {
                F(iVarF);
            }
            this.f2361b += bVar.f2361b * fA;
        }
        G(iVar);
    }

    @Override // b.e.b.b, b.e.b.d.a
    public void b(i iVar) {
        this.k.b(iVar);
        this.k.e();
        iVar.j[iVar.f2394f] = 1.0f;
        F(iVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // b.e.b.b, b.e.b.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b.e.b.i c(b.e.b.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.j
            if (r0 >= r2) goto L32
            b.e.b.i[] r2 = r4.f2383h
            r2 = r2[r0]
            int r3 = r2.f2392d
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            b.e.b.h$b r3 = r4.k
            r3.b(r2)
            if (r1 != r5) goto L22
            b.e.b.h$b r2 = r4.k
            boolean r2 = r2.c()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            b.e.b.h$b r2 = r4.k
            b.e.b.i[] r3 = r4.f2383h
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
            b.e.b.i[] r5 = r4.f2383h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.h.c(b.e.b.d, boolean[]):b.e.b.i");
    }

    @Override // b.e.b.b, b.e.b.d.a
    public void clear() {
        this.j = 0;
        this.f2361b = 0.0f;
    }

    @Override // b.e.b.b
    public String toString() {
        String str = " goal -> (" + this.f2361b + ") : ";
        for (int i2 = 0; i2 < this.j; i2++) {
            this.k.b(this.f2383h[i2]);
            str = str + this.k + " ";
        }
        return str;
    }
}
