package p;

import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public final class dv extends CompletableFuture {
    public final jp c;

    public dv(ip1 ip1Var) {
        this.c = ip1Var;
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        if (z) {
            this.c.cancel();
        }
        return super.cancel(z);
    }
}
