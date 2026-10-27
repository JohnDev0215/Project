package i9;

import A7.m;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import i8.C1417b;
import r9.C;
import r9.C2319i;
import r9.H;
import r9.L;
import r9.r;

/* JADX INFO: loaded from: classes.dex */
public final class b implements H {
    public final r l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15802m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1417b f15803n;

    public b(C1417b c1417b) {
        this.f15803n = c1417b;
        this.l = new r(((C) c1417b.f15786f).l.e());
    }

    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.f15802m) {
            return;
        }
        this.f15802m = true;
        ((C) this.f15803n.f15786f).D("0\r\n\r\n");
        C1417b c1417b = this.f15803n;
        r rVar = this.l;
        c1417b.getClass();
        L l = rVar.f21940e;
        rVar.f21940e = L.d;
        l.a();
        l.b();
        this.f15803n.f15784b = 3;
    }

    @Override // r9.H
    public final L e() {
        return this.l;
    }

    @Override // r9.H, java.io.Flushable
    public final synchronized void flush() {
        if (this.f15802m) {
            return;
        }
        ((C) this.f15803n.f15786f).flush();
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) {
        m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (this.f15802m) {
            throw new IllegalStateException("closed");
        }
        if (j3 == 0) {
            return;
        }
        C1417b c1417b = this.f15803n;
        C c = (C) c1417b.f15786f;
        if (c.f21896n) {
            throw new IllegalStateException("closed");
        }
        c.f21895m.Z(j3);
        c.b();
        C c10 = (C) c1417b.f15786f;
        c10.D("\r\n");
        c10.t(c2319i, j3);
        c10.D("\r\n");
    }
}
