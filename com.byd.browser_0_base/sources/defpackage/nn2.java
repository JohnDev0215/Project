package defpackage;

import J.N;
import android.os.Handler;

/* JADX INFO: compiled from: SingleThreadTaskRunnerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class nn2 extends c73 implements mn2 {
    public final Handler l;

    public nn2(Handler handler, d73 d73Var) {
        super(d73Var, "SingleThreadTaskRunnerImpl", 2);
        this.l = handler;
    }

    @Override // defpackage.mn2
    public final boolean c() {
        synchronized (this.f) {
            f();
        }
        Boolean boolValueOf = this.d == 0 ? null : Boolean.valueOf(N.MdFi6sVQ(this.d));
        return boolValueOf != null ? boolValueOf.booleanValue() : this.l.getLooper().getThread() == Thread.currentThread();
    }

    @Override // defpackage.c73
    public final void h() {
        Handler handler = this.l;
        if (handler == null) {
            return;
        }
        handler.post(this.e);
    }
}
