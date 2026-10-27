package k2;

import android.net.Uri;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class C implements h {
    public final h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f17631m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Uri f17632n;

    public C(h hVar) {
        hVar.getClass();
        this.l = hVar;
        this.f17632n = Uri.EMPTY;
        Collections.emptyMap();
    }

    @Override // k2.h
    public final void b(E e10) {
        e10.getClass();
        this.l.b(e10);
    }

    @Override // k2.h
    public final void close() {
        this.l.close();
    }

    @Override // k2.h
    public final Map f() {
        return this.l.f();
    }

    @Override // k2.h
    public final long g(m mVar) {
        h hVar = this.l;
        this.f17632n = mVar.f17675a;
        Collections.emptyMap();
        try {
            return hVar.g(mVar);
        } finally {
            Uri uriJ = hVar.j();
            if (uriJ != null) {
                this.f17632n = uriJ;
            }
            hVar.f();
        }
    }

    @Override // k2.h
    public final Uri j() {
        return this.l.j();
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) {
        int iN = this.l.n(bArr, i7, i10);
        if (iN != -1) {
            this.f17631m += (long) iN;
        }
        return iN;
    }
}
