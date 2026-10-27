package p;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class su0 implements Callable {
    public final /* synthetic */ boolean c;
    public final /* synthetic */ i30 e;
    public final /* synthetic */ com.google.firebase.crashlytics.internal.settings.a k;

    public su0(boolean z, i30 i30Var, com.google.firebase.crashlytics.internal.settings.a aVar) {
        this.c = z;
        this.e = i30Var;
        this.k = aVar;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        if (!this.c) {
            return null;
        }
        i30 i30Var = this.e;
        i30Var.getClass();
        a30 a30Var = new a30(i30Var, 3, this.k);
        ExecutorService executorService = ks2.a;
        bj2 bj2Var = new bj2();
        ExecutorService executorService2 = i30Var.m;
        executorService2.execute(new kc0(a30Var, executorService2, bj2Var, 6));
        return null;
    }
}
