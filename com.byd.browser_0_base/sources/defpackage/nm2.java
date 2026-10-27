package defpackage;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.signin.SigninManagerImpl;
import org.chromium.chrome.browser.signin.services.SigninManager;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class nm2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ SigninManagerImpl f;

    public /* synthetic */ nm2(SigninManagerImpl signinManagerImpl, int i) {
        this.e = i;
        this.f = signinManagerImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        SigninManagerImpl signinManagerImpl = this.f;
        switch (i) {
            case 0:
                Iterator<SigninManager.b> it = signinManagerImpl.i.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (aVar.hasNext()) {
                        ((SigninManager.b) aVar.next()).getClass();
                    }
                    break;
                }
                break;
            default:
                Iterator<SigninManager.b> it2 = signinManagerImpl.i.iterator();
                while (true) {
                    b.a aVar2 = (b.a) it2;
                    if (aVar2.hasNext()) {
                        ((SigninManager.b) aVar2.next()).w();
                    }
                    break;
                }
                break;
        }
    }
}
