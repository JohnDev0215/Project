package org.libpag;

import java.util.ArrayList;
import java.util.Iterator;
import org.libpag.PAGView;

/* JADX INFO: loaded from: classes3.dex */
public class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGView f4085a;

    public q(PAGView pAGView) {
        this.f4085a = pAGView;
    }

    @Override // java.lang.Runnable
    public void run() {
        ArrayList arrayList;
        synchronized (this.f4085a) {
            arrayList = new ArrayList(this.f4085a.v);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGView.PAGFlushListener) it.next()).onFlush();
        }
    }
}
