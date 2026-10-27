package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import p.be1;
import p.de1;
import p.ha0;
import p.j11;
import p.lq2;
import p.oa0;
import p.p61;
import p.qo;
import p.rn1;
import p.ry0;
import p.s70;
import p.u4;
import p.ul2;
import p.v03;
import p.y10;
import p.z13;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public final class a extends j11 {
    private volatile a _immediate;
    public final Handler k;
    public final String l;
    public final boolean m;
    public final a n;

    public a(Handler handler, String str, boolean z) {
        this.k = handler;
        this.l = str;
        this.m = z;
        this._immediate = z ? this : null;
        a aVar = this._immediate;
        if (aVar == null) {
            aVar = new a(handler, str, true);
            this._immediate = aVar;
        }
        this.n = aVar;
    }

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        if (this.k.post(runnable)) {
            return;
        }
        K0(y10Var, runnable);
    }

    @Override // kotlinx.coroutines.b
    public final boolean I0() {
        return (this.m && v03.b(Looper.myLooper(), this.k.getLooper())) ? false : true;
    }

    @Override // p.o80
    public final oa0 J(long j, final ul2 ul2Var, y10 y10Var) {
        if (j > 4611686018427387903L) {
            j = 4611686018427387903L;
        }
        if (this.k.postDelayed(ul2Var, j)) {
            return new oa0() { // from class: p.i11
                @Override // p.oa0
                public final void dispose() {
                    this.c.k.removeCallbacks(ul2Var);
                }
            };
        }
        K0(y10Var, ul2Var);
        return rn1.c;
    }

    public final void K0(y10 y10Var, Runnable runnable) {
        CancellationException cancellationException = new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed");
        p61 p61Var = (p61) y10Var.E0(qo.o);
        if (p61Var != null) {
            p61Var.i(cancellationException);
        }
        ha0.c.G0(y10Var, runnable);
    }

    @Override // p.o80
    public final void X(long j, zp zpVar) {
        final z13 z13Var = new z13(zpVar, this, 6);
        if (j > 4611686018427387903L) {
            j = 4611686018427387903L;
        }
        if (this.k.postDelayed(z13Var, j)) {
            zpVar.t(new ry0() { // from class: kotlinx.coroutines.android.HandlerContext$scheduleResumeAfterDelay$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // p.ry0
                public final Object invoke(Object obj) {
                    this.this$0.k.removeCallbacks(z13Var);
                    return lq2.a;
                }
            });
        } else {
            K0(zpVar.m, z13Var);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && ((a) obj).k == this.k;
    }

    public final int hashCode() {
        return System.identityHashCode(this.k);
    }

    @Override // kotlinx.coroutines.b
    public final String toString() {
        a aVar;
        String str;
        s70 s70Var = ha0.a;
        be1 be1Var = de1.a;
        if (this == be1Var) {
            str = "Dispatchers.Main";
        } else {
            try {
                aVar = ((a) be1Var).n;
            } catch (UnsupportedOperationException unused) {
                aVar = null;
            }
            str = this == aVar ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.l;
        if (string == null) {
            string = this.k.toString();
        }
        return this.m ? u4.h(string, ".immediate") : string;
    }

    public a(Handler handler) {
        this(handler, null, false);
    }
}
