package p;

import android.util.SparseArray;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class vc0 {
    public int a = 1;
    public final fk1 b;
    public fk1 c;
    public fk1 d;
    public int e;
    public int f;
    public final boolean g;
    public final int[] h;

    public vc0(fk1 fk1Var, boolean z, int[] iArr) {
        this.b = fk1Var;
        this.c = fk1Var;
        this.g = z;
        this.h = iArr;
    }

    public final int a(int i) {
        SparseArray sparseArray = this.c.a;
        fk1 fk1Var = sparseArray == null ? null : (fk1) sparseArray.get(i);
        int i2 = 1;
        int i3 = 2;
        if (this.a == 2) {
            if (fk1Var != null) {
                this.c = fk1Var;
                this.f++;
            } else if (i == 65038) {
                b();
            } else if (i != 65039) {
                fk1 fk1Var2 = this.c;
                if (fk1Var2.b != null) {
                    i3 = 3;
                    if (this.f != 1) {
                        this.d = fk1Var2;
                        b();
                    } else if (c()) {
                        this.d = this.c;
                        b();
                    } else {
                        b();
                    }
                } else {
                    b();
                }
            }
            i2 = i3;
        } else if (fk1Var == null) {
            b();
        } else {
            this.a = 2;
            this.c = fk1Var;
            this.f = 1;
            i2 = i3;
        }
        this.e = i;
        return i2;
    }

    public final void b() {
        this.a = 1;
        this.c = this.b;
        this.f = 0;
    }

    public final boolean c() {
        int[] iArr;
        dk1 dk1VarD = this.c.b.d();
        int iA = dk1VarD.a(6);
        if ((iA == 0 || dk1VarD.b.get(iA + dk1VarD.a) == 0) && this.e != 65039) {
            return this.g && ((iArr = this.h) == null || Arrays.binarySearch(iArr, this.c.b.a(0)) < 0);
        }
        return true;
    }
}
