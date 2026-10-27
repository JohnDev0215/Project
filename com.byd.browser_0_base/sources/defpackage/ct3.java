package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.view.View;
import defpackage.ax0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.supervised_user.WebsiteParentApproval;
import org.chromium.chrome.browser.supervised_user.a;
import org.chromium.chrome.browser.supervised_user.website_approval.b;
import org.chromium.chrome.browser.supervised_user.website_approval.c;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.t0;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ct3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ ct3(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        Object obj2 = this.h;
        Object obj3 = this.g;
        Object obj4 = this.f;
        switch (i) {
            case 0:
                WindowAndroid windowAndroid = (WindowAndroid) obj4;
                GURL gurl = (GURL) obj3;
                WebsiteParentApproval.a aVar = (WebsiteParentApproval.a) obj2;
                if (!((Boolean) obj).booleanValue()) {
                    N.MEedSkgE(2);
                    break;
                } else {
                    aVar.getClass();
                    c cVar = new b(windowAndroid, gurl, new a(), null).a;
                    cVar.getClass();
                    PropertyModel.r<View.OnClickListener> rVar = us3.d;
                    by1 by1Var = new by1(21, cVar);
                    PropertyModel propertyModel = cVar.d;
                    propertyModel.p(rVar, by1Var);
                    propertyModel.p(us3.e, new vt(23, cVar));
                    z7 z7VarM = z7.m();
                    Profile profileD = Profile.d();
                    z7VarM.getClass();
                    IdentityManager identityManagerV = z7.v(profileD);
                    String strB = CoreAccountInfo.b(identityManagerV.a(0));
                    if (strB != null) {
                        AccountInfo accountInfo = (AccountInfo) N.MAwvRw4K(identityManagerV.a, strB);
                        if (accountInfo != null) {
                            String str = accountInfo.e;
                            if (!str.isEmpty()) {
                                strB = str;
                            }
                        }
                        propertyModel.p(us3.a, strB);
                        cVar.b.d(cVar.c, true);
                        break;
                    }
                }
                break;
            case 1:
                Tab tab = (Tab) obj4;
                Class cls = (Class) obj3;
                String str2 = (String) obj2;
                l02 l02Var = (l02) obj;
                HashMap map = l02.l;
                if (l02Var != null) {
                    l02Var.h = System.currentTimeMillis();
                }
                l02.k(cls, str2, tab, l02Var);
                break;
            case 2:
                View view = (View) obj2;
                h0.c cVar2 = (h0.c) obj;
                if (((h0.d) obj4) == ((PropertyModel) obj3).j(u0.f)) {
                    t0.b(view, cVar2.a);
                    break;
                }
                break;
            case 3:
                gi0 gi0Var = (gi0) obj4;
                List list = (List) obj3;
                gi0Var.getClass();
                String[] strArr = (String[]) list.toArray(new String[list.size()]);
                int i2 = 4;
                ct3 ct3Var = new ct3(list, i2, (List) obj, (s62) obj2);
                UsageStatsBridge usageStatsBridge = gi0Var.a;
                N.M67g7Hwt(usageStatsBridge.b, usageStatsBridge, strArr, ct3Var);
                break;
            case 4:
                List list2 = (List) obj4;
                List list3 = (List) obj3;
                s62 s62Var = (s62) obj2;
                if (!((Boolean) obj).booleanValue()) {
                    s62Var.e(null);
                } else {
                    Iterator it = list3.iterator();
                    while (it.hasNext()) {
                        if (list2.contains(((xs3) it.next()).b)) {
                            it.remove();
                        }
                    }
                    s62Var.b(null);
                }
                break;
            default:
                qx0 qx0Var = (qx0) obj4;
                ax0.a aVar2 = (ax0.a) obj3;
                Callback callback = (Callback) obj2;
                Bitmap bitmap = (Bitmap) obj;
                qx0Var.getClass();
                String str3 = aVar2.a;
                if (bitmap != null && qx0Var.c != null) {
                    qx0Var.c.c(bitmap, qx0.f(aVar2.c, aVar2.d, aVar2.e, str3));
                }
                callback.onResult(bitmap);
                break;
        }
    }
}
