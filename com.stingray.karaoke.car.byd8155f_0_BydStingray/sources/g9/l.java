package g9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.Closeable;
import r9.C;
import r9.D;

/* JADX INFO: loaded from: classes.dex */
public final class l implements Closeable {
    public final D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C f15074m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ e f15075n;

    public l(D d, C c, e eVar) {
        this.f15075n = eVar;
        A7.m.f(d, BYDAutoMultimediaDevice.PARAM_SOURCE);
        A7.m.f(c, "sink");
        this.l = d;
        this.f15074m = c;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f15075n.a(-1L, true, true, null);
    }
}
