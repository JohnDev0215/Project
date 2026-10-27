package androidx.work.impl.utils.futures;

import p.j1;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final a b = new a(new AbstractFuture$Failure$1("Failure occurred while trying to finish a future."));
    public final Throwable a;

    public a(Throwable th) {
        boolean z = j1.l;
        th.getClass();
        this.a = th;
    }
}
