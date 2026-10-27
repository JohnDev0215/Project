package o;

import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: compiled from: SolverVariable.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements Comparable<i> {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static int f8607w = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8608e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f8609g;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8613k;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    a f8617o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8610h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f8611i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8612j = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f8614l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    float[] f8615m = new float[9];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    float[] f8616n = new float[9];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    b[] f8618p = new b[16];

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    int f8619q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f8620r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    boolean f8621s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    int f8622t = -1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    float f8623u = 0.0f;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    HashSet<b> f8624v = null;

    /* JADX INFO: compiled from: SolverVariable.java */
    public enum a {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public i(a aVar, String str) {
        this.f8617o = aVar;
    }

    static void c() {
        f8607w++;
    }

    public final void a(b bVar) {
        int i7 = 0;
        while (true) {
            int i8 = this.f8619q;
            if (i7 >= i8) {
                b[] bVarArr = this.f8618p;
                if (i8 >= bVarArr.length) {
                    this.f8618p = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.f8618p;
                int i9 = this.f8619q;
                bVarArr2[i9] = bVar;
                this.f8619q = i9 + 1;
                return;
            }
            if (this.f8618p[i7] == bVar) {
                return;
            } else {
                i7++;
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(i iVar) {
        return this.f8610h - iVar.f8610h;
    }

    public final void d(b bVar) {
        int i7 = this.f8619q;
        int i8 = 0;
        while (i8 < i7) {
            if (this.f8618p[i8] == bVar) {
                while (i8 < i7 - 1) {
                    b[] bVarArr = this.f8618p;
                    int i9 = i8 + 1;
                    bVarArr[i8] = bVarArr[i9];
                    i8 = i9;
                }
                this.f8619q--;
                return;
            }
            i8++;
        }
    }

    public void e() {
        this.f8609g = null;
        this.f8617o = a.UNKNOWN;
        this.f8612j = 0;
        this.f8610h = -1;
        this.f8611i = -1;
        this.f8613k = 0.0f;
        this.f8614l = false;
        this.f8621s = false;
        this.f8622t = -1;
        this.f8623u = 0.0f;
        int i7 = this.f8619q;
        for (int i8 = 0; i8 < i7; i8++) {
            this.f8618p[i8] = null;
        }
        this.f8619q = 0;
        this.f8620r = 0;
        this.f8608e = false;
        Arrays.fill(this.f8616n, 0.0f);
    }

    public void f(d dVar, float f7) {
        this.f8613k = f7;
        this.f8614l = true;
        this.f8621s = false;
        this.f8622t = -1;
        this.f8623u = 0.0f;
        int i7 = this.f8619q;
        this.f8611i = -1;
        for (int i8 = 0; i8 < i7; i8++) {
            this.f8618p[i8].A(dVar, this, false);
        }
        this.f8619q = 0;
    }

    public void g(a aVar, String str) {
        this.f8617o = aVar;
    }

    public final void h(d dVar, b bVar) {
        int i7 = this.f8619q;
        for (int i8 = 0; i8 < i7; i8++) {
            this.f8618p[i8].B(dVar, bVar, false);
        }
        this.f8619q = 0;
    }

    public String toString() {
        if (this.f8609g != null) {
            return "" + this.f8609g;
        }
        return "" + this.f8610h;
    }
}
