package androidx.constraintlayout.solver;

import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f609a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f610b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f611c;
    public float g;
    Type k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f612d = -1;
    int e = -1;
    public int f = 0;
    public boolean h = false;
    float[] i = new float[9];
    float[] j = new float[9];
    b[] l = new b[16];
    int m = 0;
    public int n = 0;
    HashSet<b> o = null;

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

    static void b() {
        f609a++;
    }

    public final void a(b bVar) {
        int i = 0;
        while (true) {
            int i2 = this.m;
            if (i >= i2) {
                b[] bVarArr = this.l;
                if (i2 >= bVarArr.length) {
                    this.l = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.l;
                int i3 = this.m;
                bVarArr2[i3] = bVar;
                this.m = i3 + 1;
                return;
            }
            if (this.l[i] == bVar) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void c(b bVar) {
        int i = this.m;
        int i2 = 0;
        while (i2 < i) {
            if (this.l[i2] == bVar) {
                while (i2 < i - 1) {
                    b[] bVarArr = this.l;
                    int i3 = i2 + 1;
                    bVarArr[i2] = bVarArr[i3];
                    i2 = i3;
                }
                this.m--;
                return;
            }
            i2++;
        }
    }

    public void d() {
        this.f611c = null;
        this.k = Type.UNKNOWN;
        this.f = 0;
        this.f612d = -1;
        this.e = -1;
        this.g = 0.0f;
        this.h = false;
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2] = null;
        }
        this.m = 0;
        this.n = 0;
        this.f610b = false;
        Arrays.fill(this.j, 0.0f);
    }

    public void e(d dVar, float f) {
        this.g = f;
        this.h = true;
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2].B(dVar, this, false);
        }
        this.m = 0;
    }

    public void f(Type type, String str) {
        this.k = type;
    }

    public final void g(b bVar) {
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2].C(bVar, false);
        }
        this.m = 0;
    }

    public String toString() {
        if (this.f611c != null) {
            return "" + this.f611c;
        }
        return "" + this.f612d;
    }
}
