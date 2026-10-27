package androidx.constraintlayout.solver;

import java.util.Arrays;
import java.util.HashSet;
import y.b;
import y.d;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f901a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f902b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f903c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f907g;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Type f911k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f904d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f905e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f906f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f908h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f909i = new float[9];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float[] f910j = new float[9];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public b[] f912l = new b[16];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f913m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f914n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HashSet<b> f915o = null;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.f911k = type;
    }

    public static void b() {
        f901a++;
    }

    public final void a(b bVar) {
        int i8 = 0;
        while (true) {
            int i9 = this.f913m;
            if (i8 >= i9) {
                b[] bVarArr = this.f912l;
                if (i9 >= bVarArr.length) {
                    this.f912l = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.f912l;
                int i10 = this.f913m;
                bVarArr2[i10] = bVar;
                this.f913m = i10 + 1;
                return;
            }
            if (this.f912l[i8] == bVar) {
                return;
            } else {
                i8++;
            }
        }
    }

    public final void c(b bVar) {
        int i8 = this.f913m;
        int i9 = 0;
        while (i9 < i8) {
            if (this.f912l[i9] == bVar) {
                while (i9 < i8 - 1) {
                    b[] bVarArr = this.f912l;
                    int i10 = i9 + 1;
                    bVarArr[i9] = bVarArr[i10];
                    i9 = i10;
                }
                this.f913m--;
                return;
            }
            i9++;
        }
    }

    public void d() {
        this.f903c = null;
        this.f911k = Type.UNKNOWN;
        this.f906f = 0;
        this.f904d = -1;
        this.f905e = -1;
        this.f907g = 0.0f;
        this.f908h = false;
        int i8 = this.f913m;
        for (int i9 = 0; i9 < i8; i9++) {
            this.f912l[i9] = null;
        }
        this.f913m = 0;
        this.f914n = 0;
        this.f902b = false;
        Arrays.fill(this.f910j, 0.0f);
    }

    public void e(d dVar, float f8) {
        this.f907g = f8;
        this.f908h = true;
        int i8 = this.f913m;
        for (int i9 = 0; i9 < i8; i9++) {
            this.f912l[i9].B(dVar, this, false);
        }
        this.f913m = 0;
    }

    public void f(Type type, String str) {
        this.f911k = type;
    }

    public final void g(b bVar) {
        int i8 = this.f913m;
        for (int i9 = 0; i9 < i8; i9++) {
            this.f912l[i9].C(bVar, false);
        }
        this.f913m = 0;
    }

    public String toString() {
        if (this.f903c != null) {
            return "" + this.f903c;
        }
        return "" + this.f904d;
    }
}
