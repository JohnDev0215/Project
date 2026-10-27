package defpackage;

import J.N;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.view.View;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.components.signin.identitymanager.ProfileOAuth2TokenServiceDelegate;
import org.chromium.content.browser.TtsPlatformImpl;
import org.chromium.net.ProxyChangeListener;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ul2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ ul2(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        ProxyChangeListener.a aVar = null;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                int i2 = xl2.t;
                ((Callback) obj2).onResult((xl2) obj);
                break;
            case 1:
                Object obj3 = z23.A;
                ((z23) obj2).e((String) obj);
                break;
            case 2:
                ((rl3) obj2).d.onResult((Tutorial) obj);
                break;
            case 3:
                ProfileOAuth2TokenServiceDelegate profileOAuth2TokenServiceDelegate = (ProfileOAuth2TokenServiceDelegate) obj2;
                profileOAuth2TokenServiceDelegate.getClass();
                N.M0SOBbHG(profileOAuth2TokenServiceDelegate.a, (String) obj, (String[]) b.f(b.e(AccountManagerFacadeProvider.getInstance().b())).toArray(new String[0]));
                break;
            case 4:
                ka0 ka0Var = (ka0) obj2;
                ka0Var.s(null, null, (View) obj);
                ka0Var.j = null;
                break;
            case 5:
                String str = (String) obj;
                long j = ((TtsPlatformImpl) obj2).a;
                if (j != 0) {
                    N.M1Kw17GB(j, Integer.parseInt(str));
                }
                break;
            default:
                ProxyChangeListener proxyChangeListener = (ProxyChangeListener) obj2;
                Intent intent = (Intent) obj;
                proxyChangeListener.getClass();
                ProxyChangeListener.a aVarA = ProxyChangeListener.a.a(((ConnectivityManager) xz.a.getSystemService("connectivity")).getDefaultProxy());
                if (aVarA == null) {
                    aVar = ProxyChangeListener.a.e;
                } else if (aVarA.a.equals("localhost") && aVarA.b == -1) {
                    Bundle extras = intent.getExtras();
                    ProxyChangeListener.a aVarA2 = extras == null ? null : ProxyChangeListener.a.a((ProxyInfo) extras.get("android.intent.extra.PROXY_INFO"));
                    fa1.h("ProxyChangeListener", "configFromConnectivityManager = %s, configFromIntent = %s", aVarA, aVarA2);
                    if (aVarA2 != null) {
                        aVar = new ProxyChangeListener.a(aVarA2.a, aVarA2.b, aVarA.c, aVarA.d);
                    }
                } else {
                    aVar = aVarA;
                }
                long j2 = proxyChangeListener.c;
                if (j2 != 0) {
                    if (aVar == null) {
                        N.MCIk73GZ(j2, proxyChangeListener);
                    } else {
                        N.MyoFZt$2(j2, proxyChangeListener, aVar.a, aVar.b, aVar.c, aVar.d);
                    }
                    break;
                }
                break;
        }
    }
}
