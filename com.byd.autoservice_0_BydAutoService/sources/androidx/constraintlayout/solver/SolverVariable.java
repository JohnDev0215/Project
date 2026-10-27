package androidx.constraintlayout.solver;

import java.util.Arrays;
import java.util.HashSet;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f810a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f811b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f812c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f816g;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Type f820k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f813d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f814e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f815f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f817h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f818i = new float[9];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float[] f819j = new float[9];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public b[] f821l = new b[16];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f822m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f823n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HashSet<b> f824o = null;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.f820k = type;
    }

    public static void b() {
        f810a++;
    }

    public final void a(b bVar) {
        int i3 = 0;
        while (true) {
            int i4 = this.f822m;
            if (i3 >= i4) {
                b[] bVarArr = this.f821l;
                if (i4 >= bVarArr.length) {
                    this.f821l = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.f821l;
                int i5 = this.f822m;
                bVarArr2[i5] = bVar;
                this.f822m = i5 + 1;
                return;
            }
            if (this.f821l[i3] == bVar) {
                return;
            } else {
                i3++;
            }
        }
    }

    public final void c(b bVar) {
        int i3 = this.f822m;
        int i4 = 0;
        while (i4 < i3) {
            if (this.f821l[i4] == bVar) {
                while (i4 < i3 - 1) {
                    b[] bVarArr = this.f821l;
                    int i5 = i4 + 1;
                    bVarArr[i4] = bVarArr[i5];
                    i4 = i5;
                }
                this.f822m--;
                return;
            }
            i4++;
        }
    }

    public void d() {
        this.f812c = null;
        this.f820k = Type.UNKNOWN;
        this.f815f = 0;
        this.f813d = -1;
        this.f814e = -1;
        this.f816g = 0.0f;
        this.f817h = false;
        int i3 = this.f822m;
        for (int i4 = 0; i4 < i3; i4++) {
            this.f821l[i4] = null;
        }
        this.f822m = 0;
        this.f823n = 0;
        this.f811b = false;
        Arrays.fill(this.f819j, 0.0f);
    }

    public void e(d dVar, float f3) {
        this.f816g = f3;
        this.f817h = true;
        int i3 = this.f822m;
        for (int i4 = 0; i4 < i3; i4++) {
            this.f821l[i4].B(dVar, this, false);
        }
        this.f822m = 0;
    }

    public void f(Type type, String str) {
        this.f820k = type;
    }

    public final void g(b bVar) {
        int i3 = this.f822m;
        for (int i4 = 0; i4 < i3; i4++) {
            this.f821l[i4].C(bVar, false);
        }
        this.f822m = 0;
    }

    public String toString() {
        if (this.f812c != null) {
            return "" + this.f812c;
        }
        return "" + this.f813d;
    }
}
