package p;

import androidx.constraintlayout.core.SolverVariable$Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ce2 implements Comparable {
    public boolean c;
    public float m;
    public SolverVariable$Type q;
    public int e = -1;
    public int k = -1;
    public int l = 0;
    public boolean n = false;
    public final float[] o = new float[9];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float[] f111p = new float[9];
    public pb[] r = new pb[16];
    public int s = 0;
    public int t = 0;

    public ce2(SolverVariable$Type solverVariable$Type) {
        this.q = solverVariable$Type;
    }

    public final void a(pb pbVar) {
        int i = 0;
        while (true) {
            int i2 = this.s;
            if (i >= i2) {
                pb[] pbVarArr = this.r;
                if (i2 >= pbVarArr.length) {
                    this.r = (pb[]) Arrays.copyOf(pbVarArr, pbVarArr.length * 2);
                }
                pb[] pbVarArr2 = this.r;
                int i3 = this.s;
                pbVarArr2[i3] = pbVar;
                this.s = i3 + 1;
                return;
            }
            if (this.r[i] == pbVar) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void b(pb pbVar) {
        int i = this.s;
        int i2 = 0;
        while (i2 < i) {
            if (this.r[i2] == pbVar) {
                while (i2 < i - 1) {
                    pb[] pbVarArr = this.r;
                    int i3 = i2 + 1;
                    pbVarArr[i2] = pbVarArr[i3];
                    i2 = i3;
                }
                this.s--;
                return;
            }
            i2++;
        }
    }

    public final void c() {
        this.q = SolverVariable$Type.l;
        this.l = 0;
        this.e = -1;
        this.k = -1;
        this.m = 0.0f;
        this.n = false;
        int i = this.s;
        for (int i2 = 0; i2 < i; i2++) {
            this.r[i2] = null;
        }
        this.s = 0;
        this.t = 0;
        this.c = false;
        Arrays.fill(this.f111p, 0.0f);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.e - ((ce2) obj).e;
    }

    public final void d(ra1 ra1Var, float f) {
        this.m = f;
        this.n = true;
        int i = this.s;
        this.k = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.r[i2].h(ra1Var, this, false);
        }
        this.s = 0;
    }

    public final void e(ra1 ra1Var, pb pbVar) {
        int i = this.s;
        for (int i2 = 0; i2 < i; i2++) {
            this.r[i2].i(ra1Var, pbVar, false);
        }
        this.s = 0;
    }

    public final String toString() {
        return "" + this.e;
    }
}
