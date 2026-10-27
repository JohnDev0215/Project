package g9;

import java.io.IOException;
import java.net.ProtocolException;
import r9.C2319i;
import r9.J;
import r9.q;

/* JADX INFO: loaded from: classes.dex */
public final class d extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f15037m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f15038n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f15039p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f15040q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ e f15041r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar, J j3, long j10) {
        super(j3);
        A7.m.f(j3, "delegate");
        this.f15041r = eVar;
        this.f15037m = j10;
        this.o = true;
        if (j10 == 0) {
            b(null);
        }
    }

    public final IOException b(IOException iOException) {
        if (this.f15039p) {
            return iOException;
        }
        this.f15039p = true;
        if (iOException == null && this.o) {
            this.o = false;
            e eVar = this.f15041r;
            ((c9.q) eVar.c).w((j) eVar.f15043b);
        }
        return this.f15041r.a(this.f15038n, true, false, iOException);
    }

    @Override // r9.q, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f15040q) {
            return;
        }
        this.f15040q = true;
        try {
            super.close();
            b(null);
        } catch (IOException e10) {
            throw b(e10);
        }
    }

    @Override // r9.q, r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        A7.m.f(c2319i, "sink");
        if (this.f15040q) {
            throw new IllegalStateException("closed");
        }
        try {
            long jP = this.l.p(c2319i, j3);
            if (this.o) {
                this.o = false;
                e eVar = this.f15041r;
                ((c9.q) eVar.c).w((j) eVar.f15043b);
            }
            if (jP == -1) {
                b(null);
                return -1L;
            }
            long j10 = this.f15038n + jP;
            long j11 = this.f15037m;
            if (j11 == -1 || j10 <= j11) {
                this.f15038n = j10;
                if (j10 == j11) {
                    b(null);
                }
                return jP;
            }
            throw new ProtocolException("expected " + j11 + " bytes but received " + j10);
        } catch (IOException e10) {
            throw b(e10);
        }
    }
}
