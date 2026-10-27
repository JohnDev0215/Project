package io.sentry;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/* JADX INFO: renamed from: io.sentry.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1475j extends TimerTask {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1478k f16582m;

    public /* synthetic */ C1475j(C1478k c1478k, int i7) {
        this.l = i7;
        this.f16582m = c1478k;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                Iterator it = this.f16582m.o.iterator();
                while (it.hasNext()) {
                    ((P) it.next()).b();
                }
                break;
            default:
                long jCurrentTimeMillis = System.currentTimeMillis();
                C1478k c1478k = this.f16582m;
                if (jCurrentTimeMillis - c1478k.f16595t >= 10) {
                    c1478k.f16595t = jCurrentTimeMillis;
                    E0 e02 = new E0();
                    e02.f15835a = null;
                    e02.f15836b = null;
                    Iterator it2 = c1478k.o.iterator();
                    while (it2.hasNext()) {
                        ((P) it2.next()).a(e02);
                    }
                    Iterator it3 = c1478k.f16590n.values().iterator();
                    while (it3.hasNext()) {
                        ((List) it3.next()).add(e02);
                    }
                    break;
                }
                break;
        }
    }
}
