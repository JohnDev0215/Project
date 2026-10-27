package androidx.constraintlayout.solver;

import defpackage.l5;
import defpackage.n5;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariable {
    public static int a = 1;
    public boolean b;
    public String c;
    public float g;
    public Type k;
    public int d = -1;
    public int e = -1;
    public int f = 0;
    public boolean h = false;
    public float[] i = new float[9];
    public float[] j = new float[9];
    public l5[] l = new l5[16];
    public int m = 0;
    public int n = 0;
    public HashSet<l5> o = null;

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
        a++;
    }

    public final void a(l5 l5Var) {
        int i = 0;
        while (true) {
            int i2 = this.m;
            if (i >= i2) {
                l5[] l5VarArr = this.l;
                if (i2 >= l5VarArr.length) {
                    this.l = (l5[]) Arrays.copyOf(l5VarArr, l5VarArr.length * 2);
                }
                l5[] l5VarArr2 = this.l;
                int i3 = this.m;
                l5VarArr2[i3] = l5Var;
                this.m = i3 + 1;
                return;
            }
            if (this.l[i] == l5Var) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void c(l5 l5Var) {
        int i = this.m;
        int i2 = 0;
        while (i2 < i) {
            if (this.l[i2] == l5Var) {
                while (i2 < i - 1) {
                    l5[] l5VarArr = this.l;
                    int i3 = i2 + 1;
                    l5VarArr[i2] = l5VarArr[i3];
                    i2 = i3;
                }
                this.m--;
                return;
            }
            i2++;
        }
    }

    public void d() {
        this.c = null;
        this.k = Type.UNKNOWN;
        this.f = 0;
        this.d = -1;
        this.e = -1;
        this.g = 0.0f;
        this.h = false;
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2] = null;
        }
        this.m = 0;
        this.n = 0;
        this.b = false;
        Arrays.fill(this.j, 0.0f);
    }

    public void e(n5 n5Var, float f) {
        this.g = f;
        this.h = true;
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2].B(n5Var, this, false);
        }
        this.m = 0;
    }

    public void f(Type type, String str) {
        this.k = type;
    }

    public final void g(l5 l5Var) {
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2].C(l5Var, false);
        }
        this.m = 0;
    }

    public String toString() {
        if (this.c != null) {
            return "" + this.c;
        }
        return "" + this.d;
    }
}
