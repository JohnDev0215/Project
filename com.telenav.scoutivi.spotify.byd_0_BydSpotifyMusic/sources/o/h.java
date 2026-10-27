package o;

import java.util.Arrays;
import java.util.Comparator;
import o.b;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends o.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f8597g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private i[] f8598h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private i[] f8599i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f8600j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    b f8601k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    c f8602l;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class a implements Comparator<i> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(i iVar, i iVar2) {
            return iVar.f8610h - iVar2.f8610h;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        i f8604a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        h f8605b;

        public b(h hVar) {
            this.f8605b = hVar;
        }

        public boolean a(i iVar, float f7) {
            boolean z6 = true;
            if (!this.f8604a.f8608e) {
                for (int i7 = 0; i7 < 9; i7++) {
                    float f8 = iVar.f8616n[i7];
                    if (f8 != 0.0f) {
                        float f9 = f8 * f7;
                        if (Math.abs(f9) < 1.0E-4f) {
                            f9 = 0.0f;
                        }
                        this.f8604a.f8616n[i7] = f9;
                    } else {
                        this.f8604a.f8616n[i7] = 0.0f;
                    }
                }
                return true;
            }
            for (int i8 = 0; i8 < 9; i8++) {
                float[] fArr = this.f8604a.f8616n;
                fArr[i8] = fArr[i8] + (iVar.f8616n[i8] * f7);
                if (Math.abs(fArr[i8]) < 1.0E-4f) {
                    this.f8604a.f8616n[i8] = 0.0f;
                } else {
                    z6 = false;
                }
            }
            if (z6) {
                h.this.G(this.f8604a);
            }
            return false;
        }

        public void b(i iVar) {
            this.f8604a = iVar;
        }

        public final boolean c() {
            for (int i7 = 8; i7 >= 0; i7--) {
                float f7 = this.f8604a.f8616n[i7];
                if (f7 > 0.0f) {
                    return false;
                }
                if (f7 < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean d(i iVar) {
            int i7 = 8;
            while (true) {
                if (i7 < 0) {
                    break;
                }
                float f7 = iVar.f8616n[i7];
                float f8 = this.f8604a.f8616n[i7];
                if (f8 == f7) {
                    i7--;
                } else if (f8 < f7) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f8604a.f8616n, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f8604a != null) {
                for (int i7 = 0; i7 < 9; i7++) {
                    str = str + this.f8604a.f8616n[i7] + " ";
                }
            }
            return str + "] " + this.f8604a;
        }
    }

    public h(c cVar) {
        super(cVar);
        this.f8597g = 128;
        this.f8598h = new i[128];
        this.f8599i = new i[128];
        this.f8600j = 0;
        this.f8601k = new b(this);
        this.f8602l = cVar;
    }

    private final void F(i iVar) {
        int i7;
        int i8 = this.f8600j + 1;
        i[] iVarArr = this.f8598h;
        if (i8 > iVarArr.length) {
            i[] iVarArr2 = (i[]) Arrays.copyOf(iVarArr, iVarArr.length * 2);
            this.f8598h = iVarArr2;
            this.f8599i = (i[]) Arrays.copyOf(iVarArr2, iVarArr2.length * 2);
        }
        i[] iVarArr3 = this.f8598h;
        int i9 = this.f8600j;
        iVarArr3[i9] = iVar;
        int i10 = i9 + 1;
        this.f8600j = i10;
        if (i10 > 1 && iVarArr3[i10 - 1].f8610h > iVar.f8610h) {
            int i11 = 0;
            while (true) {
                i7 = this.f8600j;
                if (i11 >= i7) {
                    break;
                }
                this.f8599i[i11] = this.f8598h[i11];
                i11++;
            }
            Arrays.sort(this.f8599i, 0, i7, new a());
            for (int i12 = 0; i12 < this.f8600j; i12++) {
                this.f8598h[i12] = this.f8599i[i12];
            }
        }
        iVar.f8608e = true;
        iVar.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G(i iVar) {
        int i7 = 0;
        while (i7 < this.f8600j) {
            if (this.f8598h[i7] == iVar) {
                while (true) {
                    int i8 = this.f8600j;
                    if (i7 >= i8 - 1) {
                        this.f8600j = i8 - 1;
                        iVar.f8608e = false;
                        return;
                    } else {
                        i[] iVarArr = this.f8598h;
                        int i9 = i7 + 1;
                        iVarArr[i7] = iVarArr[i9];
                        i7 = i9;
                    }
                }
            } else {
                i7++;
            }
        }
    }

    @Override // o.b
    public void B(d dVar, o.b bVar, boolean z6) {
        i iVar = bVar.f8559a;
        if (iVar == null) {
            return;
        }
        b.a aVar = bVar.f8563e;
        int iK = aVar.k();
        for (int i7 = 0; i7 < iK; i7++) {
            i iVarG = aVar.g(i7);
            float fA = aVar.a(i7);
            this.f8601k.b(iVarG);
            if (this.f8601k.a(iVar, fA)) {
                F(iVarG);
            }
            this.f8560b += bVar.f8560b * fA;
        }
        G(iVar);
    }

    @Override // o.b, o.d.a
    public void a(i iVar) {
        this.f8601k.b(iVar);
        this.f8601k.e();
        iVar.f8616n[iVar.f8612j] = 1.0f;
        F(iVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // o.b, o.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public o.i b(o.d r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = r5
        L3:
            int r2 = r4.f8600j
            if (r0 >= r2) goto L32
            o.i[] r2 = r4.f8598h
            r2 = r2[r0]
            int r3 = r2.f8610h
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            o.h$b r3 = r4.f8601k
            r3.b(r2)
            if (r1 != r5) goto L22
            o.h$b r2 = r4.f8601k
            boolean r2 = r2.c()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            o.h$b r2 = r4.f8601k
            o.i[] r3 = r4.f8598h
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
            o.i[] r5 = r4.f8598h
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: o.h.b(o.d, boolean[]):o.i");
    }

    @Override // o.b, o.d.a
    public void clear() {
        this.f8600j = 0;
        this.f8560b = 0.0f;
    }

    @Override // o.b, o.d.a
    public boolean isEmpty() {
        return this.f8600j == 0;
    }

    @Override // o.b
    public String toString() {
        String str = " goal -> (" + this.f8560b + ") : ";
        for (int i7 = 0; i7 < this.f8600j; i7++) {
            this.f8601k.b(this.f8598h[i7]);
            str = str + this.f8601k + " ";
        }
        return str;
    }
}
