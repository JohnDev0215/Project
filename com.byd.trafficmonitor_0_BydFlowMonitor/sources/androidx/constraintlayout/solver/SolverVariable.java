package androidx.constraintlayout.solver;

import b.f.b.b;
import b.f.b.d;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f608a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f609b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f610c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f614g;
    public Type k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f611d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f612e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f613f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f615h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f616i = new float[9];
    public float[] j = new float[9];
    public b[] l = new b[16];
    public int m = 0;
    public int n = 0;
    public HashSet<b> o = null;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.k = type;
    }

    public static void b() {
        f608a++;
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
        this.f610c = null;
        this.k = Type.UNKNOWN;
        this.f613f = 0;
        this.f611d = -1;
        this.f612e = -1;
        this.f614g = 0.0f;
        this.f615h = false;
        int i2 = this.m;
        for (int i3 = 0; i3 < i2; i3++) {
            this.l[i3] = null;
        }
        this.m = 0;
        this.n = 0;
        this.f609b = false;
        Arrays.fill(this.j, 0.0f);
    }

    public void e(d dVar, float f2) {
        this.f614g = f2;
        this.f615h = true;
        int i2 = this.m;
        for (int i3 = 0; i3 < i2; i3++) {
            this.l[i3].B(dVar, this, false);
        }
        this.m = 0;
    }

    public void f(Type type, String str) {
        this.k = type;
    }

    public final void g(b bVar) {
        int i2 = this.m;
        for (int i3 = 0; i3 < i2; i3++) {
            this.l[i3].C(bVar, false);
        }
        this.m = 0;
    }

    public String toString() {
        if (this.f610c != null) {
            return "" + this.f610c;
        }
        return "" + this.f611d;
    }
}
