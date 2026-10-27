package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;

/* JADX INFO: loaded from: classes.dex */
public abstract class p implements H {
    public final H l;

    public p(H h10) {
        A7.m.f(h10, "delegate");
        this.l = h10;
    }

    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.l.close();
    }

    @Override // r9.H
    public final L e() {
        return this.l.e();
    }

    @Override // r9.H, java.io.Flushable
    public void flush() {
        this.l.flush();
    }

    @Override // r9.H
    public void t(C2319i c2319i, long j3) {
        A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.l.t(c2319i, j3);
    }

    public final String toString() {
        return getClass().getSimpleName() + '(' + this.l + ')';
    }
}
