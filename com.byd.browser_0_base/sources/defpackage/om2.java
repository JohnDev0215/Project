package defpackage;

import J.N;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.chrome.browser.signin.SigninManagerImpl;
import org.chromium.chrome.browser.signin.services.SigninManager;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class om2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ SigninManagerImpl f;

    public /* synthetic */ om2(SigninManagerImpl signinManagerImpl, int i) {
        this.e = i;
        this.f = signinManagerImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        SigninManagerImpl signinManagerImpl = this.f;
        switch (i) {
            case 0:
                signinManagerImpl.getClass();
                sk skVar = bq.a;
                if (N.M09VlOh_("SyncAndroidLimitNTPPromoImpressions")) {
                    SharedPreferencesManager.getInstance().m(0, ar.o.b("Ntp"));
                }
                SigninManager.c cVar = signinManagerImpl.m.a;
                signinManagerImpl.m = null;
                if (cVar != null) {
                    km2 km2Var = (km2) ((im2) cVar).e;
                    if (!km2Var.l) {
                        ((SigninFirstRunFragment) km2Var.h).H0();
                    }
                }
                signinManagerImpl.s();
                Iterator<SigninManager.b> it = signinManagerImpl.i.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (aVar.hasNext()) {
                        ((SigninManager.b) aVar.next()).j();
                    }
                    break;
                }
                break;
            default:
                signinManagerImpl.r();
                break;
        }
    }
}
