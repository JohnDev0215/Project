package p;

import java.util.concurrent.Executor;
import okhttp3.Request;

/* JADX INFO: loaded from: classes.dex */
public final class k60 implements jp {
    public final Executor c;
    public final jp e;

    public k60(Executor executor, jp jpVar) {
        this.c = executor;
        this.e = jpVar;
    }

    @Override // p.jp
    public final void E(pp ppVar) {
        this.e.E(new cv(this, 2, ppVar));
    }

    @Override // p.jp
    public final u42 b() {
        return this.e.b();
    }

    @Override // p.jp
    public final Request c() {
        return this.e.c();
    }

    @Override // p.jp
    public final void cancel() {
        this.e.cancel();
    }

    @Override // p.jp
    public final boolean i() {
        return this.e.i();
    }

    @Override // p.jp
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final jp clone() {
        return new k60(this.c, this.e.clone());
    }
}
