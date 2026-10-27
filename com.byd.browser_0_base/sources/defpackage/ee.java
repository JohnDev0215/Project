package defpackage;

import android.annotation.SuppressLint;

/* JADX INFO: compiled from: BatchingListUpdateCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class ee implements z81 {
    public final z81 a;
    public int b = 0;
    public int c = -1;
    public int d = -1;
    public Object e = null;

    public ee(z81 z81Var) {
        this.a = z81Var;
    }

    @Override // defpackage.z81
    public final void a(int i, int i2) {
        int i3;
        if (this.b == 1 && i >= (i3 = this.c)) {
            int i4 = this.d;
            if (i <= i3 + i4) {
                this.d = i4 + i2;
                this.c = Math.min(i, i3);
                return;
            }
        }
        d();
        this.c = i;
        this.d = i2;
        this.b = 1;
    }

    @Override // defpackage.z81
    public final void b(int i, int i2) {
        int i3;
        if (this.b == 2 && (i3 = this.c) >= i && i3 <= i + i2) {
            this.d += i2;
            this.c = i;
        } else {
            d();
            this.c = i;
            this.d = i2;
            this.b = 2;
        }
    }

    @Override // defpackage.z81
    @SuppressLint({"UnknownNullness"})
    public final void c(int i, int i2, Object obj) {
        int i3;
        if (this.b == 3) {
            int i4 = this.c;
            int i5 = this.d;
            if (i <= i4 + i5 && (i3 = i + i2) >= i4 && this.e == obj) {
                this.c = Math.min(i, i4);
                this.d = Math.max(i5 + i4, i3) - this.c;
                return;
            }
        }
        d();
        this.c = i;
        this.d = i2;
        this.e = obj;
        this.b = 3;
    }

    public final void d() {
        int i = this.b;
        if (i == 0) {
            return;
        }
        z81 z81Var = this.a;
        if (i == 1) {
            z81Var.a(this.c, this.d);
        } else if (i == 2) {
            z81Var.b(this.c, this.d);
        } else if (i == 3) {
            z81Var.c(this.c, this.d, this.e);
        }
        this.e = null;
        this.b = 0;
    }
}
