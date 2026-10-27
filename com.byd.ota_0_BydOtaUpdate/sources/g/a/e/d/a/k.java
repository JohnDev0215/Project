package g.a.e.d.a;

/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f5717a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f5718b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i[] f5719c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i[] f5720d;

    public k(b bVar, i iVar) {
        this.f5717a = bVar;
        this.f5718b = iVar;
        b();
        a();
    }

    public static void d(i[] iVarArr, int i2, int i3) {
        i iVar = iVarArr[i2];
        iVarArr[i2] = iVarArr[i3];
        iVarArr[i3] = iVar;
    }

    public final void a() {
        int iF;
        int iG = this.f5718b.g();
        i[] iVarArr = new i[iG];
        int i2 = iG - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            iVarArr[i3] = new i(this.f5719c[i3]);
        }
        this.f5720d = new i[iG];
        while (i2 >= 0) {
            this.f5720d[i2] = new i(this.f5717a, i2);
            i2--;
        }
        for (int i4 = 0; i4 < iG; i4++) {
            if (iVarArr[i4].f(i4) == 0) {
                int i5 = i4 + 1;
                boolean z = false;
                while (i5 < iG) {
                    if (iVarArr[i5].f(i4) != 0) {
                        d(iVarArr, i4, i5);
                        d(this.f5720d, i4, i5);
                        i5 = iG;
                        z = true;
                    }
                    i5++;
                }
                if (!z) {
                    throw new ArithmeticException("Squaring matrix is not invertible.");
                }
            }
            int iF2 = this.f5717a.f(iVarArr[i4].f(i4));
            iVarArr[i4].m(iF2);
            this.f5720d[i4].m(iF2);
            for (int i6 = 0; i6 < iG; i6++) {
                if (i6 != i4 && (iF = iVarArr[i6].f(i4)) != 0) {
                    i iVarN = iVarArr[i4].n(iF);
                    i iVarN2 = this.f5720d[i4].n(iF);
                    iVarArr[i6].b(iVarN);
                    this.f5720d[i6].b(iVarN2);
                }
            }
        }
    }

    public final void b() {
        int i2;
        int iG = this.f5718b.g();
        this.f5719c = new i[iG];
        int i3 = 0;
        while (true) {
            i2 = iG >> 1;
            if (i3 >= i2) {
                break;
            }
            int i4 = i3 << 1;
            int[] iArr = new int[i4 + 1];
            iArr[i4] = 1;
            this.f5719c[i3] = new i(this.f5717a, iArr);
            i3++;
        }
        while (i2 < iG) {
            int i5 = i2 << 1;
            int[] iArr2 = new int[i5 + 1];
            iArr2[i5] = 1;
            this.f5719c[i2] = new i(this.f5717a, iArr2).k(this.f5718b);
            i2++;
        }
    }

    public i[] c() {
        return this.f5720d;
    }
}
