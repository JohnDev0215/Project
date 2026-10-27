package l2;

import android.net.Uri;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.InterruptedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k2.D;
import k2.E;
import k2.z;

/* JADX INFO: loaded from: classes.dex */
public final class e implements k2.h {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f18458A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f18459C;
    public final InterfaceC1735b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k2.h f18460m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final D f18461n;
    public final k2.h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f18462p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f18463q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f18464r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Uri f18465s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public k2.m f18466t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k2.m f18467u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public k2.h f18468v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f18469w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f18470x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f18471y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public u f18472z;

    public e(InterfaceC1735b interfaceC1735b, k2.h hVar, k2.h hVar2, d dVar, int i7) {
        this.l = interfaceC1735b;
        this.f18460m = hVar2;
        this.f18463q = (i7 & 2) != 0;
        this.f18464r = false;
        if (hVar != null) {
            this.o = hVar;
            this.f18461n = dVar != null ? new D(hVar, dVar) : null;
        } else {
            this.o = z.l;
            this.f18461n = null;
        }
    }

    @Override // k2.h
    public final void b(E e10) {
        e10.getClass();
        this.f18460m.b(e10);
        this.o.b(e10);
    }

    @Override // k2.h
    public final void close() {
        this.f18466t = null;
        this.f18465s = null;
        this.f18470x = 0L;
        try {
            e();
        } catch (Throwable th) {
            if (this.f18468v == this.f18460m || (th instanceof C1734a)) {
                this.f18458A = true;
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e() {
        InterfaceC1735b interfaceC1735b = this.l;
        k2.h hVar = this.f18468v;
        if (hVar == null) {
            return;
        }
        try {
            hVar.close();
        } finally {
            this.f18467u = null;
            this.f18468v = null;
            u uVar = this.f18472z;
            if (uVar != null) {
                ((t) interfaceC1735b).j(uVar);
                this.f18472z = null;
            }
        }
    }

    @Override // k2.h
    public final Map f() {
        return !(this.f18468v == this.f18460m) ? this.o.f() : Collections.emptyMap();
    }

    @Override // k2.h
    public final long g(k2.m mVar) {
        InterfaceC1735b interfaceC1735b = this.l;
        try {
            String string = mVar.f17680h;
            long j3 = mVar.f17678f;
            if (string == null) {
                string = mVar.f17675a.toString();
            }
            k2.l lVarA = mVar.a();
            lVarA.f17672h = string;
            k2.m mVarA = lVarA.a();
            this.f18466t = mVarA;
            Uri uri = mVarA.f17675a;
            byte[] bArr = (byte[]) ((t) interfaceC1735b).g(string).f18491b.get("exo_redir");
            Uri uri2 = null;
            String str = bArr != null ? new String(bArr, StandardCharsets.UTF_8) : null;
            if (str != null) {
                uri2 = Uri.parse(str);
            }
            if (uri2 != null) {
                uri = uri2;
            }
            this.f18465s = uri;
            this.f18470x = j3;
            boolean z6 = this.f18463q;
            long j10 = mVar.f17679g;
            boolean z10 = (z6 && this.f18458A) || (this.f18464r && j10 == -1);
            this.B = z10;
            if (z10) {
                this.f18471y = -1L;
            } else {
                long jA = n.a(((t) interfaceC1735b).g(string));
                this.f18471y = jA;
                if (jA != -1) {
                    long j11 = jA - j3;
                    this.f18471y = j11;
                    if (j11 < 0) {
                        throw new k2.j(2008);
                    }
                }
            }
            if (j10 != -1) {
                long j12 = this.f18471y;
                this.f18471y = j12 == -1 ? j10 : Math.min(j12, j10);
            }
            long j13 = this.f18471y;
            if (j13 > 0 || j13 == -1) {
                p(mVarA, false);
            }
            return j10 != -1 ? j10 : this.f18471y;
        } catch (Throwable th) {
            if (this.f18468v == this.f18460m || (th instanceof C1734a)) {
                this.f18458A = true;
            }
            throw th;
        }
    }

    @Override // k2.h
    public final Uri j() {
        return this.f18465s;
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) {
        int i11;
        k2.h hVar = this.f18460m;
        if (i10 == 0) {
            return 0;
        }
        if (this.f18471y == 0) {
            return -1;
        }
        k2.m mVar = this.f18466t;
        mVar.getClass();
        k2.m mVar2 = this.f18467u;
        mVar2.getClass();
        try {
            if (this.f18470x >= this.f18459C) {
                p(mVar, true);
            }
            k2.h hVar2 = this.f18468v;
            hVar2.getClass();
            int iN = hVar2.n(bArr, i7, i10);
            if (iN != -1) {
                long j3 = iN;
                this.f18470x += j3;
                this.f18469w += j3;
                long j10 = this.f18471y;
                if (j10 != -1) {
                    this.f18471y = j10 - j3;
                }
                return iN;
            }
            k2.h hVar3 = this.f18468v;
            if (hVar3 == hVar) {
                i11 = iN;
            } else {
                i11 = iN;
                long j11 = mVar2.f17679g;
                if (j11 == -1 || this.f18469w < j11) {
                    String str = mVar.f17680h;
                    int i12 = AbstractC1343A.f15195a;
                    this.f18471y = 0L;
                    if (!(hVar3 == this.f18461n)) {
                        return i11;
                    }
                    g gVar = new g();
                    gVar.a(Long.valueOf(this.f18470x), "exo_len");
                    ((t) this.l).c(str, gVar);
                    return i11;
                }
            }
            long j12 = this.f18471y;
            if (j12 <= 0 && j12 != -1) {
                return i11;
            }
            e();
            p(mVar, false);
            return n(bArr, i7, i10);
        } catch (Throwable th) {
            if (this.f18468v == hVar || (th instanceof C1734a)) {
                this.f18458A = true;
            }
            throw th;
        }
    }

    public final void p(k2.m mVar, boolean z6) throws InterruptedIOException {
        u uVarM;
        k2.m mVarA;
        k2.h hVar;
        String str = mVar.f17680h;
        int i7 = AbstractC1343A.f15195a;
        if (this.B) {
            uVarM = null;
        } else if (this.f18462p) {
            try {
                InterfaceC1735b interfaceC1735b = this.l;
                long j3 = this.f18470x;
                long j10 = this.f18471y;
                t tVar = (t) interfaceC1735b;
                synchronized (tVar) {
                    tVar.d();
                    while (true) {
                        uVarM = tVar.m(j3, j10, str);
                        if (uVarM != null) {
                            break;
                        } else {
                            tVar.wait();
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            uVarM = ((t) this.l).m(this.f18470x, this.f18471y, str);
        }
        if (uVarM == null) {
            hVar = this.o;
            k2.l lVarA = mVar.a();
            lVarA.f17670f = this.f18470x;
            lVarA.f17671g = this.f18471y;
            mVarA = lVarA.a();
        } else if (uVarM.o) {
            Uri uriFromFile = Uri.fromFile(uVarM.f18480p);
            long j11 = uVarM.f18478m;
            long j12 = this.f18470x - j11;
            long jMin = uVarM.f18479n - j12;
            long j13 = this.f18471y;
            if (j13 != -1) {
                jMin = Math.min(jMin, j13);
            }
            k2.l lVarA2 = mVar.a();
            lVarA2.f17667a = uriFromFile;
            lVarA2.f17668b = j11;
            lVarA2.f17670f = j12;
            lVarA2.f17671g = jMin;
            mVarA = lVarA2.a();
            hVar = this.f18460m;
        } else {
            long jMin2 = uVarM.f18479n;
            if (jMin2 == -1) {
                jMin2 = this.f18471y;
            } else {
                long j14 = this.f18471y;
                if (j14 != -1) {
                    jMin2 = Math.min(jMin2, j14);
                }
            }
            k2.l lVarA3 = mVar.a();
            lVarA3.f17670f = this.f18470x;
            lVarA3.f17671g = jMin2;
            mVarA = lVarA3.a();
            hVar = this.f18461n;
            if (hVar == null) {
                hVar = this.o;
                ((t) this.l).j(uVarM);
                uVarM = null;
            }
        }
        this.f18459C = (this.B || hVar != this.o) ? Long.MAX_VALUE : this.f18470x + 102400;
        if (z6) {
            AbstractC1346c.j(this.f18468v == this.o);
            if (hVar == this.o) {
                return;
            }
            try {
                e();
            } finally {
            }
        }
        if (uVarM != null && !uVarM.o) {
            this.f18472z = uVarM;
        }
        this.f18468v = hVar;
        this.f18467u = mVarA;
        this.f18469w = 0L;
        long jG = hVar.g(mVarA);
        g gVar = new g();
        if (mVarA.f17679g == -1 && jG != -1) {
            this.f18471y = jG;
            gVar.a(Long.valueOf(this.f18470x + jG), "exo_len");
        }
        if (!(this.f18468v == this.f18460m)) {
            Uri uriJ = hVar.j();
            this.f18465s = uriJ;
            Uri uri = mVar.f17675a.equals(uriJ) ? null : this.f18465s;
            if (uri == null) {
                ((ArrayList) gVar.f18476b).add("exo_redir");
                ((HashMap) gVar.f18475a).remove("exo_redir");
            } else {
                gVar.a(uri.toString(), "exo_redir");
            }
        }
        if (this.f18468v == this.f18461n) {
            ((t) this.l).c(str, gVar);
        }
    }
}
