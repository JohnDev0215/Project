package defpackage;

import defpackage.ki2;
import org.chromium.chrome.browser.signin.services.SigninManager;

/* JADX INFO: compiled from: SendTabToSelfCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ji2 implements SigninManager.a {
    public final /* synthetic */ ki2.a a;

    public ji2(ki2.a aVar) {
        this.a = aVar;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.a
    public final void a() {
        this.a.a.run();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.a
    public final void b() {
    }
}
