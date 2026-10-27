package defpackage;

import J.N;
import android.app.Activity;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.n92;
import defpackage.wu1;
import org.chromium.base.BundleUtils;
import org.chromium.chrome.browser.base.SplitChromeApplication;
import org.chromium.chrome.browser.base.SplitCompatApplication;
import org.chromium.chrome.browser.base.SplitPreloader;
import org.chromium.chrome.browser.creator.CreatorCoordinator;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.keyboard_accessory.d;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.components.messages.MessageWrapper;
import org.chromium.components.paintpreview.player.b;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yq2 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ yq2(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                SplitChromeApplication splitChromeApplication = (SplitChromeApplication) obj;
                SplitPreloader splitPreloader = SplitChromeApplication.j;
                splitChromeApplication.getClass();
                return (SplitCompatApplication.b) BundleUtils.e(SplitCompatApplication.b(splitChromeApplication), splitChromeApplication.h);
            case 1:
                return ((yc3) obj).n.getResourceManager();
            case 2:
                return ((CreatorCoordinator) obj).o;
            case 3:
                return ((BaseCustomTabActivity) obj).d1;
            case 4:
                return (wu1.a) ((nw2) obj).get();
            case 5:
                return ((k30) obj).k;
            case 6:
                return ((o30) obj).r.get();
            case 7:
                oj ojVar = ((s30) obj).g;
                ojVar.getClass();
                return Boolean.valueOf(ojVar instanceof xx0);
            case 8:
                return (jz2) ((p61) obj).get();
            case 9:
                n92 n92Var = (n92) obj;
                n92.c cVar = n92Var.k;
                if (cVar != null) {
                    return Integer.valueOf(n92Var.g ? cVar.h : 0);
                }
                return null;
            case 10:
                return Integer.valueOf(ow1.a((Activity) obj, new gg3(3)).d());
            case 11:
                return ((qq0) obj).l;
            case 12:
                Tab tab = ((iv0) obj).l;
                return Boolean.valueOf(tab != null && tab.isNativePage());
            case 13:
                return Boolean.valueOf(((d) obj).e);
            case 14:
                WebContents webContents = ((nf1) obj).i.i;
                return Integer.valueOf(webContents != null ? ((WebContentsImpl) webContents).l.b() : 0);
            case 15:
                nw2<ck2> nw2Var = ((ge0) obj).f;
                if (nw2Var == null) {
                    return null;
                }
                return nw2Var.get();
            case 16:
                return Boolean.valueOf(org.chromium.chrome.browser.offlinepages.d.e((Tab) obj));
            case 17:
                ViewGroup viewGroup = (ViewGroup) obj;
                SettingsActivity settingsActivity = SettingsActivity.P;
                return viewGroup;
            case 18:
                return Integer.valueOf(((dt2) obj).r);
            case 19:
                return ((q1) obj).D.n(false);
            case 20:
                return ((bc3) obj).d();
            case 21:
                return Boolean.valueOf(!((t6) obj).l);
            case 22:
                sg1 sg1Var = (sg1) obj;
                return Integer.valueOf(sg1Var.a() + sg1Var.e.getMessageShadowTopMargin() + sg1Var.e.getMessageBannerHeight());
            case 23:
                return Integer.valueOf(((RecyclerView) obj).computeVerticalScrollOffset());
            case 24:
                long j = ((MessageWrapper) obj).e;
                if (j != 0) {
                    N.MVTCdx$k(j);
                }
                return 1;
            default:
                return Boolean.valueOf(((b.InterfaceC0137b) obj).a());
        }
    }
}
