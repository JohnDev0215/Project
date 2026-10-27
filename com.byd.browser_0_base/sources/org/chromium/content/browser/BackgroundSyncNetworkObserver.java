package org.chromium.content.browser;

import J.N;
import android.annotation.SuppressLint;
import android.os.Process;
import defpackage.c6;
import defpackage.oj2;
import defpackage.xz;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.b;

/* JADX INFO: loaded from: classes3.dex */
public class BackgroundSyncNetworkObserver implements NetworkChangeNotifierAutoDetect.g {

    @SuppressLint({"StaticFieldLeak"})
    public static BackgroundSyncNetworkObserver e;
    public NetworkChangeNotifierAutoDetect a;
    public final ArrayList b;
    public int c;
    public boolean d;

    public BackgroundSyncNetworkObserver() {
        Object obj = ThreadUtils.a;
        this.b = new ArrayList();
    }

    @CalledByNative
    public static BackgroundSyncNetworkObserver createObserver(long j) {
        Object obj = ThreadUtils.a;
        if (e == null) {
            e = new BackgroundSyncNetworkObserver();
        }
        BackgroundSyncNetworkObserver backgroundSyncNetworkObserver = e;
        backgroundSyncNetworkObserver.getClass();
        if (c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_NETWORK_STATE") == 0) {
            if (backgroundSyncNetworkObserver.a == null) {
                backgroundSyncNetworkObserver.a = new NetworkChangeNotifierAutoDetect(backgroundSyncNetworkObserver, new b());
                oj2.O("BackgroundSync.NetworkObserver.HasPermission", true);
            }
            backgroundSyncNetworkObserver.b.add(Long.valueOf(j));
            N.MJIG3QvD(j, backgroundSyncNetworkObserver, backgroundSyncNetworkObserver.a.d().b());
        } else {
            oj2.O("BackgroundSync.NetworkObserver.HasPermission", false);
        }
        return e;
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void c(int i, long j) {
        Object obj = ThreadUtils.a;
        h(i);
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void d(int i) {
        Object obj = ThreadUtils.a;
        h(i);
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void g(long j) {
        Object obj = ThreadUtils.a;
        h(this.a.d().b());
    }

    public final void h(int i) {
        if (this.d && i == this.c) {
            return;
        }
        this.d = true;
        this.c = i;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            N.MJIG3QvD(((Long) it.next()).longValue(), this, i);
        }
    }

    @CalledByNative
    public final void removeObserver(long j) {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect;
        Object obj = ThreadUtils.a;
        ArrayList arrayList = this.b;
        arrayList.remove(Long.valueOf(j));
        if (arrayList.size() != 0 || (networkChangeNotifierAutoDetect = this.a) == null) {
            return;
        }
        networkChangeNotifierAutoDetect.e.a();
        networkChangeNotifierAutoDetect.f();
        this.a = null;
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void a(int i) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void b(long j) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void e(int i) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void f(long[] jArr) {
    }
}
