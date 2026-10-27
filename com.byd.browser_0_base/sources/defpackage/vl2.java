package defpackage;

import J.N;
import defpackage.xl2;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.profiles.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class vl2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;
    public final /* synthetic */ Tab g;
    public final /* synthetic */ xl2.b h;

    public /* synthetic */ vl2(Callback callback, Tab tab, xl2.b bVar, int i) {
        this.e = i;
        this.f = callback;
        this.g = tab;
        this.h = bVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = 0;
        int i2 = this.e;
        xl2 xl2Var = null;
        int i3 = 1;
        xl2.b bVar = this.h;
        Tab tab = this.g;
        Callback callback = this.f;
        switch (i2) {
            case 0:
                int i4 = xl2.t;
                if (!((Boolean) obj).booleanValue()) {
                    xl2 xl2VarN = xl2.n(tab);
                    xl2VarN.u();
                    callback.onResult(xl2VarN);
                } else if (!xl2.r()) {
                    xl2.w.getClass();
                    Profile profileD = Profile.d();
                    b<cu1> bVar2 = du1.a;
                    HashMap map = bVar2.a;
                    Object cu1Var = map.get(profileD);
                    if (cu1Var == null) {
                        cu1Var = new cu1(new a41(3, profileD));
                        map.put(profileD, cu1Var);
                    }
                    if (bVar2.c == null) {
                        b.a aVar = new b.a();
                        bVar2.c = aVar;
                        ProfileManager.a(aVar);
                    }
                    cu1 cu1Var2 = (cu1) cu1Var;
                    GURL url = tab.getUrl();
                    vl2 vl2Var = new vl2(callback, tab, bVar, i3);
                    if (url == null || url.k()) {
                        vl2Var.onResult(new LinkedList());
                    } else {
                        wy2 wy2Var = new wy2(vl2Var, 4);
                        cu1Var2.a.getClass();
                        wy2Var.onResult(null);
                    }
                } else {
                    OptimizationGuideBridge optimizationGuideBridgeA = xl2.a.a.a();
                    GURL url2 = tab.getUrl();
                    k6 k6Var = new k6(tab, callback, bVar);
                    Object obj2 = ThreadUtils.a;
                    long j = optimizationGuideBridgeA.a;
                    if (j != 0) {
                        N.MqwRdGjQ(j, url2, 18, k6Var);
                    } else {
                        k6Var.j(0, null);
                    }
                }
                break;
            default:
                List list = (List) obj;
                xl2 xl2VarN2 = xl2.n(tab);
                dk dkVar = (dk) bu1.a(list, dk.class);
                e62 e62Var = (e62) bu1.a(list, e62.class);
                if (dkVar != null && e62Var != null) {
                    xl2VarN2.o.a = 0L;
                    xl2VarN2.l();
                    xl2VarN2.o.b = 0L;
                    xl2VarN2.l();
                    xl2VarN2.o.c = null;
                    xl2VarN2.l();
                    xl2VarN2.h = System.currentTimeMillis();
                    xl2VarN2.o.d = null;
                    xl2VarN2.l();
                    xl2VarN2.o.e = tab.getUrl();
                    xl2VarN2.l();
                    i = 2;
                } else if (dkVar != null) {
                    xl2VarN2.v(0L, bVar);
                    xl2VarN2.o.c = null;
                    xl2VarN2.l();
                    xl2VarN2.h = System.currentTimeMillis();
                    xl2VarN2.o.d = null;
                    xl2VarN2.l();
                    xl2VarN2.o.e = tab.getUrl();
                    xl2VarN2.l();
                    i = 1;
                }
                oj2.U(i, 3, "Tabs.ShoppingPersistedTabData.FoundBuyableProductAnnotation");
                if (i == 1 || i == 2) {
                    xl2VarN2.r.r(Boolean.TRUE);
                    xl2Var = xl2VarN2;
                }
                callback.onResult(xl2Var);
                break;
        }
    }
}
