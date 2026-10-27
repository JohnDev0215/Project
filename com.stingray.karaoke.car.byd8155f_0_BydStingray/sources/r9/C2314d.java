package r9;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: r9.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2314d implements J {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f21917m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f21918n;

    public C2314d(InputStream inputStream, L l) {
        A7.m.f(inputStream, "input");
        this.f21917m = inputStream;
        this.f21918n = l;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.l) {
            case 0:
                C2314d c2314d = (C2314d) this.f21918n;
                I i7 = (I) this.f21917m;
                i7.i();
                try {
                    c2314d.close();
                    if (i7.j()) {
                        throw i7.l(null);
                    }
                    return;
                } catch (IOException e10) {
                    if (!i7.j()) {
                        throw e10;
                    }
                    throw i7.l(e10);
                } finally {
                    i7.j();
                }
            default:
                ((InputStream) this.f21917m).close();
                return;
        }
    }

    @Override // r9.J
    public final L e() {
        switch (this.l) {
            case 0:
                return (I) this.f21917m;
            default:
                return (L) this.f21918n;
        }
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) {
        switch (this.l) {
            case 0:
                A7.m.f(c2319i, "sink");
                C2314d c2314d = (C2314d) this.f21918n;
                I i7 = (I) this.f21917m;
                i7.i();
                try {
                    long jP = c2314d.p(c2319i, j3);
                    if (i7.j()) {
                        throw i7.l(null);
                    }
                    return jP;
                } catch (IOException e10) {
                    if (i7.j()) {
                        throw i7.l(e10);
                    }
                    throw e10;
                } finally {
                    i7.j();
                }
            default:
                A7.m.f(c2319i, "sink");
                if (j3 == 0) {
                    return 0L;
                }
                if (j3 < 0) {
                    throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
                }
                try {
                    ((L) this.f21918n).f();
                    E eT = c2319i.T(1);
                    int i10 = ((InputStream) this.f21917m).read(eT.f21899a, eT.c, (int) Math.min(j3, 8192 - eT.c));
                    if (i10 == -1) {
                        if (eT.f21900b == eT.c) {
                            c2319i.l = eT.a();
                            F.a(eT);
                        }
                        return -1L;
                    }
                    eT.c += i10;
                    long j10 = i10;
                    c2319i.f21932m += j10;
                    return j10;
                } catch (AssertionError e11) {
                    if (AbstractC2312b.f(e11)) {
                        throw new IOException(e11);
                    }
                    throw e11;
                }
        }
    }

    public final String toString() {
        switch (this.l) {
            case 0:
                return "AsyncTimeout.source(" + ((C2314d) this.f21918n) + ')';
            default:
                return "source(" + ((InputStream) this.f21917m) + ')';
        }
    }

    public C2314d(I i7, C2314d c2314d) {
        this.f21917m = i7;
        this.f21918n = c2314d;
    }
}
