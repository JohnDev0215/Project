package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import defpackage.j2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.omnibox.geo.GeolocationHeader;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ec2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ nc2 f;

    public /* synthetic */ ec2(nc2 nc2Var, int i) {
        this.e = i;
        this.f = nc2Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        cv1 cv1Var;
        int i = this.e;
        nc2 nc2Var = this.f;
        switch (i) {
            case 0:
                String str = (String) obj;
                if (nc2Var.T.get() != null) {
                    j2.a aVar = j2.e;
                    String strR = SelectionPopupControllerImpl.r(1000, str);
                    if (!TextUtils.isEmpty(strR)) {
                        Tab tab = (Tab) nc2Var.k.f;
                        oj2.t(Profile.b(tab.c())).notifyEvent("web_search_performed");
                        y13 y13Var = nc2Var.T.get();
                        String strD = oj2.q(Profile.b(tab.c())).d(strR, null);
                        Profile profileB = Profile.b(tab.c());
                        String strD2 = profileB != null ? GeolocationHeader.d(strD, profileB, tab) : null;
                        LoadUrlParams loadUrlParams = new LoadUrlParams(strD, 0);
                        loadUrlParams.g = strD2;
                        loadUrlParams.d = 5;
                        y13Var.x(loadUrlParams, 4, tab, tab.isIncognito());
                        break;
                    }
                }
                break;
            case 1:
                nc2Var.C = new wp2(nc2Var.g, (ViewGroup) ((View) obj).findViewById(R$id.bottom_sheet_snackbar_container), nc2Var.j);
                break;
            default:
                nc2Var.getClass();
                if (((Boolean) obj).booleanValue() && (cv1Var = nc2Var.D0) != null) {
                    cv1Var.a();
                    break;
                }
                break;
        }
    }
}
