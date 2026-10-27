package defpackage;

import android.os.Handler;
import android.os.Looper;
import defpackage.m31;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: HandlerDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class wt0 extends xt0 {
    private volatile wt0 _immediate;
    public final Handler f;
    public final String g;
    public final boolean h;
    public final wt0 i;

    public wt0(Handler handler, String str, boolean z) {
        this.f = handler;
        this.g = str;
        this.h = z;
        this._immediate = z ? this : null;
        wt0 wt0Var = this._immediate;
        if (wt0Var == null) {
            wt0Var = new wt0(handler, str, true);
            this._immediate = wt0Var;
        }
        this.i = wt0Var;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof wt0) && ((wt0) obj).f == this.f;
    }

    @Override // defpackage.p00
    public final void h1(m00 m00Var, Runnable runnable) {
        if (this.f.post(runnable)) {
            return;
        }
        CancellationException cancellationException = new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed");
        m31 m31Var = (m31) m00Var.get(m31.a.e);
        if (m31Var != null) {
            m31Var.Q0(cancellationException);
        }
        xa0.b.h1(m00Var, runnable);
    }

    public final int hashCode() {
        return System.identityHashCode(this.f);
    }

    @Override // defpackage.p00
    public final boolean i1() {
        return (this.h && i21.a(Looper.myLooper(), this.f.getLooper())) ? false : true;
    }

    @Override // defpackage.cb1
    public final cb1 j1() {
        return this.i;
    }

    @Override // defpackage.cb1, defpackage.p00
    public final String toString() {
        cb1 cb1VarJ1;
        String str;
        u70 u70Var = xa0.a;
        cb1 cb1Var = eb1.a;
        if (this == cb1Var) {
            str = "Dispatchers.Main";
        } else {
            try {
                cb1VarJ1 = cb1Var.j1();
            } catch (UnsupportedOperationException unused) {
                cb1VarJ1 = null;
            }
            str = this == cb1VarJ1 ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.g;
        if (string == null) {
            string = this.f.toString();
        }
        return this.h ? i21.g(".immediate", string) : string;
    }

    public wt0(Handler handler) {
        this(handler, null, false);
    }
}
