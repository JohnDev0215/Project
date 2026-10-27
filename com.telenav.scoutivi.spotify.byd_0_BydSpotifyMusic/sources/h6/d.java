package h6;

import c6.d0;
import c6.r;
import c6.u;
import c6.x;
import h6.k;
import java.io.IOException;
import k6.n;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: ExchangeFinder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private k.b f6680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private k f6681b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f6682c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f6683d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f6684e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d0 f6685f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final h f6686g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final c6.a f6687h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final e f6688i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final r f6689j;

    public d(h connectionPool, c6.a address, e call, r eventListener) {
        l.e(connectionPool, "connectionPool");
        l.e(address, "address");
        l.e(call, "call");
        l.e(eventListener, "eventListener");
        this.f6686g = connectionPool;
        this.f6687h = address;
        this.f6688i = call;
        this.f6689j = eventListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0150  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final h6.f b(int r15, int r16, int r17, int r18, boolean r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.d.b(int, int, int, int, boolean):h6.f");
    }

    private final f c(int i7, int i8, int i9, int i10, boolean z6, boolean z7) throws IOException {
        while (true) {
            f fVarB = b(i7, i8, i9, i10, z6);
            if (fVarB.u(z7)) {
                return fVarB;
            }
            fVarB.y();
            if (this.f6685f == null) {
                k.b bVar = this.f6680a;
                if (bVar != null ? bVar.b() : true) {
                    continue;
                } else {
                    k kVar = this.f6681b;
                    if (!(kVar != null ? kVar.b() : true)) {
                        throw new IOException("exhausted all routes");
                    }
                }
            }
        }
    }

    private final d0 f() {
        f fVarK;
        if (this.f6682c > 1 || this.f6683d > 1 || this.f6684e > 0 || (fVarK = this.f6688i.k()) == null) {
            return null;
        }
        synchronized (fVarK) {
            if (fVarK.q() != 0) {
                return null;
            }
            if (d6.b.g(fVarK.z().a().l(), this.f6687h.l())) {
                return fVarK.z();
            }
            return null;
        }
    }

    public final i6.d a(x client, i6.g chain) {
        l.e(client, "client");
        l.e(chain, "chain");
        try {
            return c(chain.f(), chain.h(), chain.j(), client.v(), client.B(), !l.a(chain.i().g(), "GET")).w(client, chain);
        } catch (j e7) {
            h(e7.c());
            throw e7;
        } catch (IOException e8) {
            h(e8);
            throw new j(e8);
        }
    }

    public final c6.a d() {
        return this.f6687h;
    }

    public final boolean e() {
        k kVar;
        if (this.f6682c == 0 && this.f6683d == 0 && this.f6684e == 0) {
            return false;
        }
        if (this.f6685f != null) {
            return true;
        }
        d0 d0VarF = f();
        if (d0VarF != null) {
            this.f6685f = d0VarF;
            return true;
        }
        k.b bVar = this.f6680a;
        if ((bVar == null || !bVar.b()) && (kVar = this.f6681b) != null) {
            return kVar.b();
        }
        return true;
    }

    public final boolean g(u url) {
        l.e(url, "url");
        u uVarL = this.f6687h.l();
        return url.l() == uVarL.l() && l.a(url.h(), uVarL.h());
    }

    public final void h(IOException e7) {
        l.e(e7, "e");
        this.f6685f = null;
        if ((e7 instanceof n) && ((n) e7).f7483e == k6.b.REFUSED_STREAM) {
            this.f6682c++;
        } else if (e7 instanceof k6.a) {
            this.f6683d++;
        } else {
            this.f6684e++;
        }
    }
}
