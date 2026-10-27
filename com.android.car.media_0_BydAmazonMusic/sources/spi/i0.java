package spi;

import android.car.utils.Logs;

/* JADX INFO: compiled from: BrowseViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ com.android.car.media.a b;

    public i0(com.android.car.media.a aVar, int i) {
        this.b = aVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.l.setScrollPosition(this.a, 0.0f, true);
        Logs.d("BrowseViewController", "update setScrollPosition:" + this.a);
    }
}
