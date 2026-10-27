package org.chromium.content.browser;

import J.N;
import android.os.Process;
import defpackage.AbstractC1623us;
import defpackage.Is;
import defpackage.Lt;
import defpackage.QK;
import defpackage.TK;
import defpackage.ZK;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BackgroundSyncNetworkObserver implements QK {
    public static boolean e;
    public static BackgroundSyncNetworkObserver f;
    public TK a;
    public List b;
    public int c;
    public boolean d;

    public BackgroundSyncNetworkObserver() {
        ThreadUtils.b();
        this.b = new ArrayList();
        e = false;
    }

    public static BackgroundSyncNetworkObserver createObserver(long j) {
        ThreadUtils.b();
        if (f == null) {
            f = new BackgroundSyncNetworkObserver();
        }
        BackgroundSyncNetworkObserver backgroundSyncNetworkObserver = f;
        Objects.requireNonNull(backgroundSyncNetworkObserver);
        ThreadUtils.b();
        if (AbstractC1623us.a(Is.a, "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0) {
            if (backgroundSyncNetworkObserver.a == null) {
                backgroundSyncNetworkObserver.a = new TK(backgroundSyncNetworkObserver, new ZK());
                Lt.a.b("BackgroundSync.NetworkObserver.HasPermission", true);
            }
            backgroundSyncNetworkObserver.b.add(Long.valueOf(j));
            N.MJIG3QvD(j, backgroundSyncNetworkObserver, backgroundSyncNetworkObserver.a.d().b());
        } else {
            Lt.a.b("BackgroundSync.NetworkObserver.HasPermission", false);
        }
        return f;
    }

    private void removeObserver(long j) {
        TK tk;
        ThreadUtils.b();
        this.b.remove(Long.valueOf(j));
        if (this.b.size() != 0 || (tk = this.a) == null) {
            return;
        }
        tk.e.c();
        tk.g();
        this.a = null;
    }

    @Override // defpackage.QK
    public void a(int i) {
        ThreadUtils.b();
        g(i);
    }

    @Override // defpackage.QK
    public void b(long[] jArr) {
    }

    @Override // defpackage.QK
    public void c(long j, int i) {
        ThreadUtils.b();
        g(this.a.d().b());
    }

    @Override // defpackage.QK
    public void d(long j) {
        ThreadUtils.b();
        g(this.a.d().b());
    }

    @Override // defpackage.QK
    public void e(int i) {
    }

    @Override // defpackage.QK
    public void f(long j) {
    }

    public final void g(int i) {
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
}
