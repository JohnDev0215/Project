package j9;

import Q5.C0606p0;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import r9.D;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Closeable {
    public static final Logger o;
    public final D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final p f17571m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final R0.b f17572n;

    static {
        Logger logger = Logger.getLogger(e.class.getName());
        A7.m.e(logger, "getLogger(Http2::class.java.name)");
        o = logger;
    }

    public q(D d) {
        A7.m.f(d, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.l = d;
        p pVar = new p(d);
        this.f17571m = pVar;
        this.f17572n = new R0.b(pVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:137:0x024f, code lost:
    
        throw new java.io.IOException(android.hardware.bydauto.ac.tsimpl.a.e(r10, "PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: "));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(boolean r20, Q5.C0606p0 r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.q.b(boolean, Q5.p0):boolean");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.l.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0133, code lost:
    
        if (r7 == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0135, code lost:
    
        r5.i(d9.b.f13646b, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(Q5.C0606p0 r19, int r20, int r21, int r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.q.g(Q5.p0, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        throw new java.io.IOException(android.hardware.bydauto.ac.tsimpl.a.e(r7, "Header index too large "));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List i(int r6, int r7, int r8, int r9) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.q.i(int, int, int, int):java.util.List");
    }

    public final void j(C0606p0 c0606p0, int i7, int i10, int i11) throws IOException {
        int i12;
        int i13 = 1;
        if (i11 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        boolean z6 = false;
        boolean z10 = (i10 & 1) != 0;
        if ((i10 & 8) != 0) {
            byte bK = this.l.k();
            byte[] bArr = d9.b.f13645a;
            i12 = bK & 255;
        } else {
            i12 = 0;
        }
        if ((i10 & 32) != 0) {
            D d = this.l;
            d.q();
            d.k();
            byte[] bArr2 = d9.b.f13645a;
            c0606p0.getClass();
            i7 -= 5;
        }
        List listI = i(o.a(i7, i10, i12), i12, i10, i11);
        c0606p0.getClass();
        ((m) c0606p0.f7010n).getClass();
        if (i11 != 0 && (i11 & 1) == 0) {
            z6 = true;
        }
        if (z6) {
            m mVar = (m) c0606p0.f7010n;
            mVar.getClass();
            mVar.f17554t.c(new k(mVar.f17549n + '[' + i11 + "] onHeaders", mVar, i11, listI, z10), 0L);
            return;
        }
        m mVar2 = (m) c0606p0.f7010n;
        synchronized (mVar2) {
            u uVarG = mVar2.g(i11);
            if (uVarG != null) {
                uVarG.i(d9.b.v(listI), z10);
                return;
            }
            if (mVar2.f17551q) {
                return;
            }
            if (i11 <= mVar2.o) {
                return;
            }
            if (i11 % 2 == mVar2.f17550p % 2) {
                return;
            }
            u uVar = new u(i11, mVar2, false, z10, d9.b.v(listI));
            mVar2.o = i11;
            mVar2.f17548m.put(Integer.valueOf(i11), uVar);
            mVar2.f17552r.e().c(new h(i13, mVar2, uVar, mVar2.f17549n + '[' + i11 + "] onStream"), 0L);
        }
    }

    public final void k(C0606p0 c0606p0, int i7, int i10, int i11) throws IOException {
        int i12;
        if (i11 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        if ((i10 & 8) != 0) {
            byte bK = this.l.k();
            byte[] bArr = d9.b.f13645a;
            i12 = bK & 255;
        } else {
            i12 = 0;
        }
        int iQ = this.l.q() & Integer.MAX_VALUE;
        List listI = i(o.a(i7 - 4, i10, i12), i12, i10, i11);
        c0606p0.getClass();
        m mVar = (m) c0606p0.f7010n;
        mVar.getClass();
        synchronized (mVar) {
            if (mVar.f17547J.contains(Integer.valueOf(iQ))) {
                mVar.o(iQ, 2);
                return;
            }
            mVar.f17547J.add(Integer.valueOf(iQ));
            mVar.f17554t.c(new k(mVar.f17549n + '[' + iQ + "] onRequest", mVar, iQ, listI), 0L);
        }
    }
}
