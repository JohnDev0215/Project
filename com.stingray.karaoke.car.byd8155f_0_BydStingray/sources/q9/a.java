package q9;

import java.io.Closeable;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import r9.AbstractC2312b;
import r9.C2319i;
import r9.t;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Closeable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f21352m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C2319i f21353n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Closeable f21354p;

    public a(int i7, boolean z6) {
        this.l = i7;
        switch (i7) {
            case 1:
                this.f21352m = z6;
                C2319i c2319i = new C2319i();
                this.f21353n = c2319i;
                Inflater inflater = new Inflater(true);
                this.o = inflater;
                this.f21354p = new t(AbstractC2312b.c(c2319i), inflater);
                break;
            default:
                this.f21352m = z6;
                C2319i c2319i2 = new C2319i();
                this.f21353n = c2319i2;
                Deflater deflater = new Deflater(-1, true);
                this.o = deflater;
                this.f21354p = new i9.e(c2319i2, deflater);
                break;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        switch (this.l) {
            case 0:
                ((i9.e) this.f21354p).close();
                break;
            default:
                ((t) this.f21354p).close();
                break;
        }
    }
}
