package k2;

import android.net.Uri;
import h2.AbstractC1343A;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class D implements h {
    public final h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final l2.d f17633m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f17634n;
    public long o;

    public D(h hVar, l2.d dVar) {
        hVar.getClass();
        this.l = hVar;
        dVar.getClass();
        this.f17633m = dVar;
    }

    @Override // k2.h
    public final void b(E e10) {
        e10.getClass();
        this.l.b(e10);
    }

    @Override // k2.h
    public final void close() throws l2.c {
        l2.d dVar = this.f17633m;
        try {
            this.l.close();
            if (this.f17634n) {
                this.f17634n = false;
                if (dVar.d == null) {
                    return;
                }
                try {
                    dVar.a();
                } catch (IOException e10) {
                    throw new l2.c(e10);
                }
            }
        } catch (Throwable th) {
            if (this.f17634n) {
                this.f17634n = false;
                if (dVar.d != null) {
                    try {
                        dVar.a();
                    } catch (IOException e11) {
                        throw new l2.c(e11);
                    }
                }
            }
            throw th;
        }
    }

    @Override // k2.h
    public final Map f() {
        return this.l.f();
    }

    @Override // k2.h
    public final long g(m mVar) throws l2.c {
        m mVar2 = mVar;
        long jG = this.l.g(mVar2);
        this.o = jG;
        if (jG == 0) {
            return 0L;
        }
        long j3 = mVar2.f17679g;
        if (j3 == -1 && jG != -1 && j3 != jG) {
            mVar2 = new m(mVar2.f17675a, mVar2.f17676b, mVar2.c, mVar2.d, mVar2.f17677e, mVar2.f17678f, jG, mVar2.f17680h, mVar2.f17681i);
        }
        this.f17634n = true;
        l2.d dVar = this.f17633m;
        dVar.getClass();
        mVar2.f17680h.getClass();
        long j10 = mVar2.f17679g;
        int i7 = mVar2.f17681i;
        if (j10 == -1 && (i7 & 2) == 2) {
            dVar.d = null;
        } else {
            dVar.d = mVar2;
            dVar.f18452e = (i7 & 4) == 4 ? dVar.f18451b : Long.MAX_VALUE;
            dVar.f18456i = 0L;
            try {
                dVar.b(mVar2);
            } catch (IOException e10) {
                throw new l2.c(e10);
            }
        }
        return this.o;
    }

    @Override // k2.h
    public final Uri j() {
        return this.l.j();
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) throws l2.c {
        if (this.o == 0) {
            return -1;
        }
        int iN = this.l.n(bArr, i7, i10);
        if (iN > 0) {
            l2.d dVar = this.f17633m;
            m mVar = dVar.d;
            if (mVar != null) {
                int i11 = 0;
                while (i11 < iN) {
                    try {
                        if (dVar.f18455h == dVar.f18452e) {
                            dVar.a();
                            dVar.b(mVar);
                        }
                        int iMin = (int) Math.min(iN - i11, dVar.f18452e - dVar.f18455h);
                        OutputStream outputStream = dVar.f18454g;
                        int i12 = AbstractC1343A.f15195a;
                        outputStream.write(bArr, i7 + i11, iMin);
                        i11 += iMin;
                        long j3 = iMin;
                        dVar.f18455h += j3;
                        dVar.f18456i += j3;
                    } catch (IOException e10) {
                        throw new l2.c(e10);
                    }
                }
            }
            long j10 = this.o;
            if (j10 != -1) {
                this.o = j10 - ((long) iN);
            }
        }
        return iN;
    }
}
