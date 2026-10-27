package b.e.b;

import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: compiled from: SolverVariable.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f2389a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2390b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2391c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2395g;
    a k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2392d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2393e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2394f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2396h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float[] f2397i = new float[9];
    float[] j = new float[9];
    b[] l = new b[16];
    int m = 0;
    public int n = 0;
    HashSet<b> o = null;

    /* JADX INFO: compiled from: SolverVariable.java */
    public enum a {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public i(a aVar, String str) {
        this.k = aVar;
    }

    static void b() {
        f2389a++;
    }

    public final void a(b bVar) {
        int i2 = 0;
        while (true) {
            int i3 = this.m;
            if (i2 >= i3) {
                b[] bVarArr = this.l;
                if (i3 >= bVarArr.length) {
                    this.l = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.l;
                int i4 = this.m;
                bVarArr2[i4] = bVar;
                this.m = i4 + 1;
                return;
            }
            if (this.l[i2] == bVar) {
                return;
            } else {
                i2++;
            }
        }
    }

    public final void c(b bVar) {
        int i2 = this.m;
        int i3 = 0;
        while (i3 < i2) {
            if (this.l[i3] == bVar) {
                while (i3 < i2 - 1) {
                    b[] bVarArr = this.l;
                    int i4 = i3 + 1;
                    bVarArr[i3] = bVarArr[i4];
                    i3 = i4;
                }
                this.m--;
                return;
            }
            i3++;
        }
    }

    public void d() {
        this.f2391c = null;
        this.k = a.UNKNOWN;
        this.f2394f = 0;
        this.f2392d = -1;
        this.f2393e = -1;
        this.f2395g = 0.0f;
        this.f2396h = false;
        int i2 = this.m;
        for (int i3 = 0; i3 < i2; i3++) {
            this.l[i3] = null;
        }
        this.m = 0;
        this.n = 0;
        this.f2390b = false;
        Arrays.fill(this.j, 0.0f);
    }

    public void e(d dVar, float f2) {
        this.f2395g = f2;
        this.f2396h = true;
        int i2 = this.m;
        for (int i3 = 0; i3 < i2; i3++) {
            this.l[i3].B(dVar, this, false);
        }
        this.m = 0;
    }

    public void f(a aVar, String str) {
        this.k = aVar;
    }

    public final void g(b bVar) {
        int i2 = this.m;
        for (int i3 = 0; i3 < i2; i3++) {
            this.l[i3].C(bVar, false);
        }
        this.m = 0;
    }

    public String toString() {
        if (this.f2391c != null) {
            return "" + this.f2391c;
        }
        return "" + this.f2392d;
    }
}
