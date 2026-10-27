package defpackage;

import android.accounts.Account;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import android.util.LruCache;
import com.google.protobuf.d0;
import defpackage.m6;
import defpackage.mb0;
import defpackage.xl2;
import java.util.List;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.b;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k6 implements mb0.a, AccountManagerFacade.a, OptimizationGuideBridge.OptimizationGuideCallback, LargeIconBridge.LargeIconCallback {
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ k6(Object obj, Object obj2, Object obj3) {
        this.f = obj;
        this.g = obj2;
        this.e = obj3;
    }

    @Override // defpackage.sl
    public final void c(lr3 lr3Var) {
        m6 m6Var = (m6) this.f;
        mb0 mb0Var = (mb0) this.g;
        String str = (String) this.e;
        lr3 lr3Var2 = lr3Var;
        m6Var.getClass();
        mb0Var.close();
        boolean z = lr3Var2 != null;
        m6.a aVar = new m6.a();
        aVar.a = SystemClock.elapsedRealtime();
        aVar.b = z;
        if (m6Var.a == null) {
            m6Var.a = new LruCache<>(100);
        }
        m6Var.a.put(str, aVar);
        if (lr3Var2 == null) {
            return;
        }
        a8.C().getClass();
    }

    @Override // org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge.OptimizationGuideCallback
    public final void j(int i, nv nvVar) {
        Tab tab = (Tab) this.f;
        Callback callback = (Callback) this.g;
        xl2.b bVar = (xl2.b) this.e;
        int i2 = xl2.t;
        if (tab.isDestroyed()) {
            callback.onResult(null);
            return;
        }
        if (i != 1) {
            xl2 xl2VarN = xl2.n(tab);
            xl2VarN.u();
            t32 t32Var = xl2VarN.p;
            if (t32Var != null) {
                t32Var.a(System.currentTimeMillis() - CriticalPersistedTabData.m(xl2VarN.e).r, "NavigationComplete");
            }
            callback.onResult(xl2VarN);
            return;
        }
        try {
            d42 d42VarT = d42.T(nvVar.O());
            xl2 xl2VarN2 = xl2.n(tab);
            xl2VarN2.s(tab, d42VarT, bVar);
            t32 t32Var2 = xl2VarN2.p;
            if (t32Var2 != null) {
                t32Var2.a(System.currentTimeMillis() - CriticalPersistedTabData.m(xl2VarN2.e).r, "NavigationComplete");
            }
            callback.onResult(xl2VarN2);
        } catch (d0 e) {
            Log.i("cr_SPTD", String.format(Locale.US, "There was a problem parsing PriceTrackingDataProto. Details %s.", e));
            callback.onResult(null);
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade.a
    public final void k(boolean z) {
        List list = (List) this.f;
        String str = (String) this.e;
        if (z) {
            oc.a();
            return;
        }
        Account accountD = b.d(str, list);
        if (accountD == null) {
            oc.a();
            return;
        }
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        z7.D(profileD).d(40, accountD, new nc());
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        ke3 ke3Var = (ke3) this.f;
        GURL gurl = (GURL) this.g;
        LargeIconBridge.LargeIconCallback largeIconCallback = (LargeIconBridge.LargeIconCallback) this.e;
        if (bitmap == null) {
            ke3Var.d.b(gurl, ke3Var.e, largeIconCallback);
        } else {
            ke3Var.getClass();
            largeIconCallback.onLargeIconAvailable(bitmap, i, z, i2);
        }
    }

    public /* synthetic */ k6(List list, String str, Activity activity) {
        this.f = list;
        this.e = str;
        this.g = activity;
    }
}
