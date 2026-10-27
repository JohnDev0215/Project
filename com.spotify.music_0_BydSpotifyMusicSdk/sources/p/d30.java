package p;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class d30 implements Callable {
    public final /* synthetic */ long c;
    public final /* synthetic */ String e;
    public final /* synthetic */ com.google.firebase.crashlytics.internal.common.a k;

    public d30(com.google.firebase.crashlytics.internal.common.a aVar, long j, String str) {
        this.k = aVar;
        this.c = j;
        this.e = str;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        com.google.firebase.crashlytics.internal.common.a aVar = this.k;
        k40 k40Var = aVar.m;
        if (k40Var != null && k40Var.e.get()) {
            return null;
        }
        aVar.i.b.f(this.e, this.c);
        return null;
    }
}
