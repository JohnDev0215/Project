package m9;

import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public final class m implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f19350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public n f19351b;

    public m(l lVar) {
        this.f19350a = lVar;
    }

    @Override // m9.n
    public final boolean a(SSLSocket sSLSocket) {
        return this.f19350a.a(sSLSocket);
    }

    @Override // m9.n
    public final String b(SSLSocket sSLSocket) {
        n nVarE = e(sSLSocket);
        if (nVarE != null) {
            return nVarE.b(sSLSocket);
        }
        return null;
    }

    @Override // m9.n
    public final boolean c() {
        return true;
    }

    @Override // m9.n
    public final void d(SSLSocket sSLSocket, String str, List list) {
        A7.m.f(list, "protocols");
        n nVarE = e(sSLSocket);
        if (nVarE != null) {
            nVarE.d(sSLSocket, str, list);
        }
    }

    public final synchronized n e(SSLSocket sSLSocket) {
        try {
            if (this.f19351b == null && this.f19350a.a(sSLSocket)) {
                this.f19351b = this.f19350a.b(sSLSocket);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f19351b;
    }
}
