package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: renamed from: r9.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2313c implements H {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f21915m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f21916n;

    public /* synthetic */ C2313c(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f21915m = obj;
        this.f21916n = obj2;
    }

    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.l) {
            case 0:
                C2313c c2313c = (C2313c) this.f21916n;
                I i7 = (I) this.f21915m;
                i7.i();
                try {
                    c2313c.close();
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
                ((OutputStream) this.f21915m).close();
                return;
        }
    }

    @Override // r9.H
    public final L e() {
        switch (this.l) {
            case 0:
                return (I) this.f21915m;
            default:
                return (L) this.f21916n;
        }
    }

    @Override // r9.H, java.io.Flushable
    public final void flush() throws IOException {
        switch (this.l) {
            case 0:
                C2313c c2313c = (C2313c) this.f21916n;
                I i7 = (I) this.f21915m;
                i7.i();
                try {
                    c2313c.flush();
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
                ((OutputStream) this.f21915m).flush();
                return;
        }
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) throws IOException {
        switch (this.l) {
            case 0:
                A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
                AbstractC2312b.e(c2319i.f21932m, 0L, j3);
                while (true) {
                    long j10 = 0;
                    if (j3 <= 0) {
                        return;
                    }
                    E e10 = c2319i.l;
                    A7.m.c(e10);
                    while (true) {
                        if (j10 < 65536) {
                            j10 += (long) (e10.c - e10.f21900b);
                            if (j10 >= j3) {
                                j10 = j3;
                            } else {
                                e10 = e10.f21902f;
                                A7.m.c(e10);
                            }
                        }
                    }
                    C2313c c2313c = (C2313c) this.f21916n;
                    I i7 = (I) this.f21915m;
                    i7.i();
                    try {
                        c2313c.t(c2319i, j10);
                        if (i7.j()) {
                            throw i7.l(null);
                        }
                        j3 -= j10;
                    } catch (IOException e11) {
                        if (!i7.j()) {
                            throw e11;
                        }
                        throw i7.l(e11);
                    } finally {
                        i7.j();
                    }
                }
                break;
            default:
                A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
                AbstractC2312b.e(c2319i.f21932m, 0L, j3);
                while (j3 > 0) {
                    ((L) this.f21916n).f();
                    E e12 = c2319i.l;
                    A7.m.c(e12);
                    int iMin = (int) Math.min(j3, e12.c - e12.f21900b);
                    ((OutputStream) this.f21915m).write(e12.f21899a, e12.f21900b, iMin);
                    int i10 = e12.f21900b + iMin;
                    e12.f21900b = i10;
                    long j11 = iMin;
                    j3 -= j11;
                    c2319i.f21932m -= j11;
                    if (i10 == e12.c) {
                        c2319i.l = e12.a();
                        F.a(e12);
                    }
                }
                return;
        }
    }

    public final String toString() {
        switch (this.l) {
            case 0:
                return "AsyncTimeout.sink(" + ((C2313c) this.f21916n) + ')';
            default:
                return "sink(" + ((OutputStream) this.f21915m) + ')';
        }
    }
}
