package org.chromium.net;

import J.N;
import java.util.Iterator;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* JADX INFO: compiled from: NetworkChangeNotifier.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a implements NetworkChangeNotifierAutoDetect.g {
    public final /* synthetic */ NetworkChangeNotifier a;

    public a(NetworkChangeNotifier networkChangeNotifier) {
        this.a = networkChangeNotifier;
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void a(int i) {
        this.a.b(i);
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void b(long j) {
        NetworkChangeNotifier networkChangeNotifier = this.a;
        Iterator<Long> it = networkChangeNotifier.a.iterator();
        while (it.hasNext()) {
            N.MiJIMrTb(it.next().longValue(), networkChangeNotifier, j);
        }
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void c(int i, long j) {
        this.a.e(i, j);
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void d(int i) {
        NetworkChangeNotifier networkChangeNotifier = this.a;
        networkChangeNotifier.d = i;
        networkChangeNotifier.d(i, networkChangeNotifier.getCurrentDefaultNetId());
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void e(int i) {
        this.a.c(i);
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void f(long[] jArr) {
        NetworkChangeNotifier networkChangeNotifier = this.a;
        Iterator<Long> it = networkChangeNotifier.a.iterator();
        while (it.hasNext()) {
            N.MpF$179U(it.next().longValue(), networkChangeNotifier, jArr);
        }
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void g(long j) {
        NetworkChangeNotifier networkChangeNotifier = this.a;
        Iterator<Long> it = networkChangeNotifier.a.iterator();
        while (it.hasNext()) {
            N.MDpuHJTB(it.next().longValue(), networkChangeNotifier, j);
        }
    }
}
