package e9;

import java.io.IOException;
import r9.C2314d;
import r9.q;

/* JADX INFO: loaded from: classes.dex */
public final class c extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f14493m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f14494n;
    public final /* synthetic */ d o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(C2314d c2314d, g gVar, d dVar) {
        super(c2314d);
        this.f14494n = gVar;
        this.o = dVar;
    }

    @Override // r9.q, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        if (this.f14493m) {
            return;
        }
        this.f14493m = true;
        g gVar = this.f14494n;
        d dVar = this.o;
        synchronized (gVar) {
            int i7 = dVar.f14500h - 1;
            dVar.f14500h = i7;
            if (i7 == 0 && dVar.f14498f) {
                gVar.C(dVar);
            }
        }
    }
}
