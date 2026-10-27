package a.f.a;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class g implements Comparable<g> {
    public static int q = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f522a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f523b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f527f;
    public a j;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f524c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f525d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f526e = 0;
    public boolean g = false;
    public float[] h = new float[9];
    public float[] i = new float[9];
    public b[] k = new b[16];
    public int l = 0;
    public int m = 0;
    public boolean n = false;
    public int o = -1;
    public float p = 0.0f;

    public enum a {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public g(a aVar) {
        this.j = aVar;
    }

    public final void a(b bVar) {
        int i = 0;
        while (true) {
            int i2 = this.l;
            if (i >= i2) {
                b[] bVarArr = this.k;
                if (i2 >= bVarArr.length) {
                    this.k = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.k;
                int i3 = this.l;
                bVarArr2[i3] = bVar;
                this.l = i3 + 1;
                return;
            }
            if (this.k[i] == bVar) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void b(b bVar) {
        int i = this.l;
        int i2 = 0;
        while (i2 < i) {
            if (this.k[i2] == bVar) {
                while (i2 < i - 1) {
                    b[] bVarArr = this.k;
                    int i3 = i2 + 1;
                    bVarArr[i2] = bVarArr[i3];
                    i2 = i3;
                }
                this.l--;
                return;
            }
            i2++;
        }
    }

    public void c() {
        this.f523b = null;
        this.j = a.UNKNOWN;
        this.f526e = 0;
        this.f524c = -1;
        this.f525d = -1;
        this.f527f = 0.0f;
        this.g = false;
        this.n = false;
        this.o = -1;
        this.p = 0.0f;
        int i = this.l;
        for (int i2 = 0; i2 < i; i2++) {
            this.k[i2] = null;
        }
        this.l = 0;
        this.m = 0;
        this.f522a = false;
        Arrays.fill(this.i, 0.0f);
    }

    @Override // java.lang.Comparable
    public int compareTo(g gVar) {
        return this.f524c - gVar.f524c;
    }

    public void d(d dVar, float f2) {
        this.f527f = f2;
        this.g = true;
        this.n = false;
        this.o = -1;
        this.p = 0.0f;
        int i = this.l;
        this.f525d = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.k[i2].k(dVar, this, false);
        }
        this.l = 0;
    }

    public final void e(d dVar, b bVar) {
        int i = this.l;
        for (int i2 = 0; i2 < i; i2++) {
            this.k[i2].l(dVar, bVar, false);
        }
        this.l = 0;
    }

    public String toString() {
        StringBuilder sbD;
        if (this.f523b != null) {
            sbD = b.a.a.a.a.d("");
            sbD.append(this.f523b);
        } else {
            sbD = b.a.a.a.a.d("");
            sbD.append(this.f524c);
        }
        return sbD.toString();
    }
}
