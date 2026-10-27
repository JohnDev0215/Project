package p;

import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class lx1 implements s91 {
    public static final lx1 q = new lx1();
    public int c;
    public int e;
    public Handler m;
    public boolean k = true;
    public boolean l = true;
    public final androidx.lifecycle.a n = new androidx.lifecycle.a(this);
    public final nv o = new nv(7, this);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final kx1 f151p = new kx1(this);

    public final void b() {
        int i = this.e + 1;
        this.e = i;
        if (i == 1) {
            if (this.k) {
                this.n.f(Lifecycle$Event.ON_RESUME);
                this.k = false;
            } else {
                Handler handler = this.m;
                v03.h(handler);
                handler.removeCallbacks(this.o);
            }
        }
    }

    @Override // p.s91
    public final l91 l() {
        return this.n;
    }
}
