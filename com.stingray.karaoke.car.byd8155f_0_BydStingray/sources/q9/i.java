package q9;

import A7.k;
import A7.m;
import B5.RunnableC0079c;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import g9.l;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import k7.C1707a;
import r9.C2319i;
import r9.C2322l;
import r9.D;
import r9.J;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Closeable {
    public final D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g f21387m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f21388n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f21389p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21390q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f21391r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f21392s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f21393t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f21394u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C2319i f21395v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C2319i f21396w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public a f21397x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final byte[] f21398y;

    public i(D d, g gVar, boolean z6, boolean z10) {
        m.f(d, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.l = d;
        this.f21387m = gVar;
        this.f21388n = z6;
        this.o = z10;
        this.f21395v = new C2319i();
        this.f21396w = new C2319i();
        this.f21398y = null;
    }

    public final void b() throws ProtocolException, EOFException {
        String strQ;
        short sN;
        i iVar;
        j jVar;
        long j3 = this.f21391r;
        if (j3 > 0) {
            this.l.o(this.f21395v, j3);
        }
        switch (this.f21390q) {
            case 8:
                C2319i c2319i = this.f21395v;
                long j10 = c2319i.f21932m;
                if (j10 == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                l lVar = null;
                if (j10 != 0) {
                    sN = c2319i.N();
                    strQ = this.f21395v.Q();
                    String strE = (sN < 1000 || sN >= 5000) ? android.hardware.bydauto.ac.tsimpl.a.e(sN, "Code must be in range [1000,5000): ") : ((1004 > sN || sN >= 1007) && (1015 > sN || sN >= 3000)) ? null : k.g("Code ", sN, " is reserved and may not be used.");
                    if (strE != null) {
                        throw new ProtocolException(strE);
                    }
                } else {
                    strQ = "";
                    sN = 1005;
                }
                g gVar = this.f21387m;
                if (sN == -1) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                synchronized (gVar) {
                    if (gVar.f21378r != -1) {
                        throw new IllegalStateException("already closed");
                    }
                    gVar.f21378r = sN;
                    gVar.f21379s = strQ;
                    if (gVar.f21377q && gVar.o.isEmpty()) {
                        l lVar2 = gVar.f21374m;
                        gVar.f21374m = null;
                        iVar = gVar.f21371i;
                        gVar.f21371i = null;
                        jVar = gVar.f21372j;
                        gVar.f21372j = null;
                        gVar.f21373k.f();
                        lVar = lVar2;
                    } else {
                        iVar = null;
                        jVar = null;
                    }
                }
                try {
                    e7.f fVar = gVar.f21365a;
                    if (lVar != null) {
                        C1707a.a(new RunnableC0079c(fVar, 15));
                        break;
                    }
                    this.f21389p = true;
                    return;
                } finally {
                    if (lVar != null) {
                        d9.b.c(lVar);
                    }
                    if (iVar != null) {
                        d9.b.c(iVar);
                    }
                    if (jVar != null) {
                        d9.b.c(jVar);
                    }
                }
            case 9:
                g gVar2 = this.f21387m;
                C2319i c2319i2 = this.f21395v;
                C2322l c2322lF = c2319i2.F(c2319i2.f21932m);
                synchronized (gVar2) {
                    try {
                        m.f(c2322lF, "payload");
                        if (!gVar2.f21380t && (!gVar2.f21377q || !gVar2.o.isEmpty())) {
                            gVar2.f21375n.add(c2322lF);
                            gVar2.f();
                            return;
                        }
                        return;
                    } finally {
                    }
                }
            case 10:
                g gVar3 = this.f21387m;
                C2319i c2319i3 = this.f21395v;
                C2322l c2322lF2 = c2319i3.F(c2319i3.f21932m);
                synchronized (gVar3) {
                    m.f(c2322lF2, "payload");
                    gVar3.f21382v = false;
                }
                return;
            default:
                int i7 = this.f21390q;
                byte[] bArr = d9.b.f13645a;
                String hexString = Integer.toHexString(i7);
                m.e(hexString, "toHexString(this)");
                throw new ProtocolException("Unknown control opcode: ".concat(hexString));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        a aVar = this.f21397x;
        if (aVar != null) {
            aVar.close();
        }
    }

    public final void g() throws IOException {
        boolean z6;
        if (this.f21389p) {
            throw new IOException("closed");
        }
        D d = this.l;
        long jH = d.l.e().h();
        J j3 = d.l;
        j3.e().b();
        try {
            byte bK = d.k();
            byte[] bArr = d9.b.f13645a;
            j3.e().g(jH, TimeUnit.NANOSECONDS);
            int i7 = bK & 15;
            this.f21390q = i7;
            int i10 = 0;
            boolean z10 = (bK & 128) != 0;
            this.f21392s = z10;
            boolean z11 = (bK & 8) != 0;
            this.f21393t = z11;
            if (z11 && !z10) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z12 = (bK & 64) != 0;
            if (i7 == 1 || i7 == 2) {
                if (!z12) {
                    z6 = false;
                } else {
                    if (!this.f21388n) {
                        throw new ProtocolException("Unexpected rsv1 flag");
                    }
                    z6 = true;
                }
                this.f21394u = z6;
            } else if (z12) {
                throw new ProtocolException("Unexpected rsv1 flag");
            }
            if ((bK & 32) != 0) {
                throw new ProtocolException("Unexpected rsv2 flag");
            }
            if ((bK & 16) != 0) {
                throw new ProtocolException("Unexpected rsv3 flag");
            }
            byte bK2 = d.k();
            boolean z13 = (bK2 & 128) != 0;
            if (z13) {
                throw new ProtocolException("Server-sent frames must not be masked.");
            }
            long j10 = bK2 & 127;
            this.f21391r = j10;
            C2319i c2319i = d.f21897m;
            if (j10 == 126) {
                this.f21391r = d.z() & 65535;
            } else if (j10 == 127) {
                d.I(8L);
                long jM = c2319i.M();
                this.f21391r = jM;
                if (jM < 0) {
                    StringBuilder sb = new StringBuilder("Frame length 0x");
                    String hexString = Long.toHexString(this.f21391r);
                    m.e(hexString, "toHexString(this)");
                    sb.append(hexString);
                    sb.append(" > 0x7FFFFFFFFFFFFFFF");
                    throw new ProtocolException(sb.toString());
                }
            }
            if (this.f21393t && this.f21391r > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (!z13) {
                return;
            }
            byte[] bArr2 = this.f21398y;
            m.c(bArr2);
            try {
                d.I(bArr2.length);
                c2319i.I(bArr2);
            } catch (EOFException e10) {
                while (true) {
                    long j11 = c2319i.f21932m;
                    if (j11 <= 0) {
                        throw e10;
                    }
                    int iV = c2319i.v(bArr2, i10, (int) j11);
                    if (iV == -1) {
                        throw new AssertionError();
                    }
                    i10 += iV;
                }
            }
        } catch (Throwable th) {
            j3.e().g(jH, TimeUnit.NANOSECONDS);
            throw th;
        }
    }
}
