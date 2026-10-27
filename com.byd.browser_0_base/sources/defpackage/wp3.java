package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: WebApkSplashNetworkErrorObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wp3 implements NetworkChangeNotifier.a {
    public final /* synthetic */ Tab e;
    public final /* synthetic */ xp3 f;

    public wp3(xp3 xp3Var, TabImpl tabImpl) {
        this.f = xp3Var;
        this.e = tabImpl;
    }

    @Override // org.chromium.net.NetworkChangeNotifier.a
    public final void d(int i) {
        if (NetworkChangeNotifier.a()) {
            NetworkChangeNotifier.f(this);
            this.e.z();
            this.f.i = true;
        }
    }
}
