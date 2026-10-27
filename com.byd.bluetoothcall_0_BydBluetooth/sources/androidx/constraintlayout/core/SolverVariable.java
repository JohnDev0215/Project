package androidx.constraintlayout.core;

import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariable implements Comparable<SolverVariable> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f700b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f701c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f702d;
    public float h;
    Type l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f703e = -1;
    int f = -1;
    public int g = 0;
    public boolean i = false;
    float[] j = new float[9];
    float[] k = new float[9];
    b[] m = new b[16];
    int n = 0;
    public int o = 0;
    boolean p = false;
    int q = -1;
    float r = 0.0f;
    HashSet<b> s = null;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.l = type;
    }

    static void e() {
        f700b++;
    }

    public final void b(b bVar) {
        int i = 0;
        while (true) {
            int i2 = this.n;
            if (i >= i2) {
                b[] bVarArr = this.m;
                if (i2 >= bVarArr.length) {
                    this.m = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.m;
                int i3 = this.n;
                bVarArr2[i3] = bVar;
                this.n = i3 + 1;
                return;
            }
            if (this.m[i] == bVar) {
                return;
            } else {
                i++;
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(SolverVariable solverVariable) {
        return this.f703e - solverVariable.f703e;
    }

    public final void g(b bVar) {
        int i = this.n;
        int i2 = 0;
        while (i2 < i) {
            if (this.m[i2] == bVar) {
                while (i2 < i - 1) {
                    b[] bVarArr = this.m;
                    int i3 = i2 + 1;
                    bVarArr[i2] = bVarArr[i3];
                    i2 = i3;
                }
                this.n--;
                return;
            }
            i2++;
        }
    }

    public void h() {
        this.f702d = null;
        this.l = Type.UNKNOWN;
        this.g = 0;
        this.f703e = -1;
        this.f = -1;
        this.h = 0.0f;
        this.i = false;
        this.p = false;
        this.q = -1;
        this.r = 0.0f;
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            this.m[i2] = null;
        }
        this.n = 0;
        this.o = 0;
        this.f701c = false;
        Arrays.fill(this.k, 0.0f);
    }

    public void i(d dVar, float f) {
        this.h = f;
        this.i = true;
        this.p = false;
        this.q = -1;
        this.r = 0.0f;
        int i = this.n;
        this.f = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.m[i2].A(dVar, this, false);
        }
        this.n = 0;
    }

    public void j(Type type, String str) {
        this.l = type;
    }

    public final void k(d dVar, b bVar) {
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            this.m[i2].B(dVar, bVar, false);
        }
        this.n = 0;
    }

    public String toString() {
        if (this.f702d != null) {
            return "" + this.f702d;
        }
        return "" + this.f703e;
    }
}
