package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class la1 {
    public cr1 a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;

    public la1() {
        d();
    }

    public final void a() {
        this.c = this.d ? this.a.e() : this.a.f();
    }

    public final void b(View view, int i) {
        if (this.d) {
            this.c = this.a.h() + this.a.b(view);
        } else {
            this.c = this.a.d(view);
        }
        this.b = i;
    }

    public final void c(View view, int i) {
        int iH = this.a.h();
        if (iH >= 0) {
            b(view, i);
            return;
        }
        this.b = i;
        if (!this.d) {
            int iD = this.a.d(view);
            int iF = iD - this.a.f();
            this.c = iD;
            if (iF > 0) {
                int iE = (this.a.e() - Math.min(0, (this.a.e() - iH) - this.a.b(view))) - (this.a.c(view) + iD);
                if (iE < 0) {
                    this.c -= Math.min(iF, -iE);
                    return;
                }
                return;
            }
            return;
        }
        int iE2 = (this.a.e() - iH) - this.a.b(view);
        this.c = this.a.e() - iE2;
        if (iE2 > 0) {
            int iC = this.c - this.a.c(view);
            int iF2 = this.a.f();
            int iMin = iC - (Math.min(this.a.d(view) - iF2, 0) + iF2);
            if (iMin < 0) {
                this.c = Math.min(iE2, -iMin) + this.c;
            }
        }
    }

    public final void d() {
        this.b = -1;
        this.c = Integer.MIN_VALUE;
        this.d = false;
        this.e = false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AnchorInfo{mPosition=");
        sb.append(this.b);
        sb.append(", mCoordinate=");
        sb.append(this.c);
        sb.append(", mLayoutFromEnd=");
        sb.append(this.d);
        sb.append(", mValid=");
        return u4.n(sb, this.e, '}');
    }
}
