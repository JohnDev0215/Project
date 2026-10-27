package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;

/* JADX INFO: renamed from: r9.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2316f implements H {
    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // r9.H
    public final L e() {
        return L.d;
    }

    @Override // r9.H, java.io.Flushable
    public final void flush() {
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) {
        A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        c2319i.R(j3);
    }
}
