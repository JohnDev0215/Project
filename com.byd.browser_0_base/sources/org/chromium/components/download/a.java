package org.chromium.components.download;

import android.os.Handler;
import defpackage.b50;
import defpackage.sb2;
import org.chromium.base.ThreadUtils;
import org.chromium.components.download.NetworkStatusListenerAndroid;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.b;

/* JADX INFO: compiled from: BackgroundNetworkStatusListener.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements NetworkChangeNotifierAutoDetect.g {
    public final NetworkChangeNotifierAutoDetect a;
    public final InterfaceC0130a b;
    public final Handler c;

    /* JADX INFO: renamed from: org.chromium.components.download.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BackgroundNetworkStatusListener.java */
    public interface InterfaceC0130a {
        void d(int i);

        void e(int i);
    }

    public a(NetworkStatusListenerAndroid.a aVar) {
        Handler handler = new Handler(ThreadUtils.d());
        this.c = handler;
        this.b = aVar;
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = new NetworkChangeNotifierAutoDetect(this, new b());
        this.a = networkChangeNotifierAutoDetect;
        handler.post(new b50(networkChangeNotifierAutoDetect.d().b(), 9, this));
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void d(int i) {
        sb2 sb2Var = new sb2(i, 5, this);
        Object obj = ThreadUtils.a;
        this.c.post(sb2Var);
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

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void g(long j) {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.g
    public final void c(int i, long j) {
    }
}
