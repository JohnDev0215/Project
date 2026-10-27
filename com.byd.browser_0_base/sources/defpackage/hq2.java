package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: SolverVariable.java */
/* JADX INFO: loaded from: classes.dex */
public final class hq2 implements Comparable<hq2> {
    public boolean e;
    public float i;
    public int m;
    public int f = -1;
    public int g = -1;
    public int h = 0;
    public boolean j = false;
    public final float[] k = new float[9];
    public final float[] l = new float[9];
    public p8[] n = new p8[16];
    public int o = 0;
    public int p = 0;

    public hq2(int i) {
        this.m = i;
    }

    public final void a(p8 p8Var) {
        int i = 0;
        while (true) {
            int i2 = this.o;
            if (i >= i2) {
                p8[] p8VarArr = this.n;
                if (i2 >= p8VarArr.length) {
                    this.n = (p8[]) Arrays.copyOf(p8VarArr, p8VarArr.length * 2);
                }
                p8[] p8VarArr2 = this.n;
                int i3 = this.o;
                p8VarArr2[i3] = p8Var;
                this.o = i3 + 1;
                return;
            }
            if (this.n[i] == p8Var) {
                return;
            } else {
                i++;
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(hq2 hq2Var) {
        return this.f - hq2Var.f;
    }

    public final void e(p8 p8Var) {
        int i = this.o;
        int i2 = 0;
        while (i2 < i) {
            if (this.n[i2] == p8Var) {
                while (i2 < i - 1) {
                    p8[] p8VarArr = this.n;
                    int i3 = i2 + 1;
                    p8VarArr[i2] = p8VarArr[i3];
                    i2 = i3;
                }
                this.o--;
                return;
            }
            i2++;
        }
    }

    public final void f() {
        this.m = 5;
        this.h = 0;
        this.f = -1;
        this.g = -1;
        this.i = 0.0f;
        this.j = false;
        int i = this.o;
        for (int i2 = 0; i2 < i; i2++) {
            this.n[i2] = null;
        }
        this.o = 0;
        this.p = 0;
        this.e = false;
        Arrays.fill(this.l, 0.0f);
    }

    public final void g(q71 q71Var, float f) {
        this.i = f;
        this.j = true;
        int i = this.o;
        this.g = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.n[i2].h(q71Var, this, false);
        }
        this.o = 0;
    }

    public final void h(q71 q71Var, p8 p8Var) {
        int i = this.o;
        for (int i2 = 0; i2 < i; i2++) {
            this.n[i2].i(q71Var, p8Var, false);
        }
        this.o = 0;
    }

    public final String toString() {
        return "" + this.f;
    }
}
