package spi;

import java.util.Objects;
import spi.pp;

/* JADX INFO: loaded from: classes.dex */
public final class qp implements Runnable {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ pp.c.a b;

    public qp(pp.c.a aVar, boolean z) {
        this.b = aVar;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        pp.c.a aVar = this.b;
        boolean z = this.a;
        Objects.requireNonNull(aVar);
        tu.a();
        pp.c cVar = pp.c.this;
        boolean z2 = cVar.a;
        cVar.a = z;
        if (z2 != z) {
            cVar.b.a(z);
        }
    }
}
