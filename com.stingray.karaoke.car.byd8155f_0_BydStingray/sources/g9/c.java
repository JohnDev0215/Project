package g9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.IOException;
import java.net.ProtocolException;
import r9.C2319i;
import r9.H;
import r9.p;

/* JADX INFO: loaded from: classes.dex */
public final class c extends p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f15033m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f15034n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f15035p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ e f15036q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(e eVar, H h10, long j3) {
        super(h10);
        A7.m.f(h10, "delegate");
        this.f15036q = eVar;
        this.f15033m = j3;
    }

    public final IOException b(IOException iOException) {
        if (this.f15034n) {
            return iOException;
        }
        this.f15034n = true;
        return this.f15036q.a(this.o, false, true, iOException);
    }

    @Override // r9.p, r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f15035p) {
            return;
        }
        this.f15035p = true;
        long j3 = this.f15033m;
        if (j3 != -1 && this.o != j3) {
            throw new ProtocolException("unexpected end of stream");
        }
        try {
            super.close();
            b(null);
        } catch (IOException e10) {
            throw b(e10);
        }
    }

    @Override // r9.p, r9.H, java.io.Flushable
    public final void flush() throws IOException {
        try {
            super.flush();
        } catch (IOException e10) {
            throw b(e10);
        }
    }

    @Override // r9.p, r9.H
    public final void t(C2319i c2319i, long j3) throws IOException {
        A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (this.f15035p) {
            throw new IllegalStateException("closed");
        }
        long j10 = this.f15033m;
        if (j10 == -1 || this.o + j3 <= j10) {
            try {
                super.t(c2319i, j3);
                this.o += j3;
                return;
            } catch (IOException e10) {
                throw b(e10);
            }
        }
        throw new ProtocolException("expected " + j10 + " bytes but received " + (this.o + j3));
    }
}
