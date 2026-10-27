package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import defpackage.a10;
import defpackage.jk2;
import defpackage.kp1;
import defpackage.to;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.endpoint_fetcher.EndpointResponse;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.chrome.browser.offlinepages.ClientId;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.b;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.password_check.CompromisedCredential;
import org.chromium.chrome.browser.password_check.f;
import org.chromium.chrome.browser.password_check.g;
import org.chromium.chrome.browser.password_check.h;
import org.chromium.chrome.browser.privacy_sandbox.LearnMoreFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_engines.settings.SearchEngineSettings;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.adaptive.settings.AdaptiveToolbarPreferenceFragment;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.browser_ui.share.a;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n62 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ n62(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        byte[] bArr;
        int i = this.e;
        int i2 = 3;
        a10Var = null;
        a10 a10Var = null;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i) {
            case 0:
                s62 s62Var = (s62) obj3;
                try {
                    s62Var.b(((Function) obj2).apply(obj));
                } catch (Exception e) {
                    s62Var.e(e);
                    return;
                }
                break;
            case 1:
                to.a aVar = (to.a) obj3;
                String str = (String) obj2;
                Boolean bool = (Boolean) obj;
                int i3 = to.a.e;
                aVar.getClass();
                if (bool != null && bool.booleanValue()) {
                    n3.X0("MobileActionMode.WebSearch");
                    aVar.c.onResult(str);
                    break;
                }
                break;
            case 2:
                ((PersonalDataManager) obj3).c.put(((PersonalDataManager.CreditCard) obj2).getCardArtUrl().j(), (Bitmap) obj);
                break;
            case 3:
                BookmarkItem bookmarkItem = (BookmarkItem) obj3;
                Tab tab = (Tab) obj2;
                BookmarkId bookmarkId = (BookmarkId) obj;
                BookmarkId bookmarkId2 = bookmarkItem != null ? bookmarkItem.c : null;
                if (bookmarkId != null && !bookmarkId.equals(bookmarkId2)) {
                    d.c cVar = d.a;
                    WebContents webContentsC = tab.c();
                    if (((tab.m() || hd2.z1(tab) || webContentsC == null || webContentsC.isDestroyed() || webContentsC.isIncognito()) ? 1 : 0) == 0) {
                        d.a aVarA = d.a();
                        Profile profileB = Profile.b(tab.c());
                        ((d.c) aVarA).getClass();
                        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(profileB);
                        if (offlinePageBridgeA != null) {
                            offlinePageBridgeA.b(tab.c(), new ClientId("bookmark", bookmarkId.toString()), new b());
                            break;
                        }
                    }
                }
                break;
            case 4:
                h20 h20Var = (h20) obj3;
                String str2 = (String) obj2;
                h20Var.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                Tab tab2 = h20Var.e.b;
                if (tab2 != null && str2.equals(h20Var.g.a(tab2.getUrl().j()))) {
                    i = 1;
                }
                if (i != 0) {
                    h20Var.a(zBooleanValue ? 1 : 2, str2, tab2.getUrl().j());
                }
                break;
            case 5:
                t80 t80Var = (t80) obj3;
                Profile profile = (Profile) obj2;
                Tab tab3 = (Tab) obj;
                t80Var.getClass();
                if (!t80.b(tab3, oj2.t(profile), "IPH_RequestDesktopSiteExceptionsGeneric")) {
                    t80Var.d.getClass();
                    if (WebsitePreferenceBridge.a(profile, 73).size() <= 1) {
                        t80Var.c("IPH_RequestDesktopSiteExceptionsGeneric", tab3.c().m().l() ? R$string.rds_site_settings_generic_iph_text_mobile : R$string.rds_site_settings_generic_iph_text_desktop, new Object[]{tab3.getUrl().e()});
                        break;
                    }
                }
                break;
            case 6:
                om omVar = (om) obj3;
                int i4 = om.z;
                omVar.getClass();
                ((ImageView) obj2).setImageDrawable(new BitmapDrawable(omVar.e.getResources(), (Bitmap) obj));
                break;
            case 7:
                e.a aVar2 = (e.a) obj3;
                e.a.C0085a c0085a = (e.a.C0085a) obj2;
                WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata = (WebFeedBridge$WebFeedMetadata) obj;
                aVar2.getClass();
                if (webFeedBridge$WebFeedMetadata != null && (bArr = webFeedBridge$WebFeedMetadata.a) != null && bArr.length > 0 && webFeedBridge$WebFeedMetadata.f && webFeedBridge$WebFeedMetadata.d == 2) {
                    e.b bVar = new e.b();
                    bVar.a = bArr;
                    bVar.c = webFeedBridge$WebFeedMetadata.b;
                    bVar.b = c0085a.b;
                    aVar2.b(c0085a, bVar);
                } else {
                    if (webFeedBridge$WebFeedMetadata != null) {
                        fa1.h("WFFollowIntroCtrl", "No intro: Web Feed exists, but not suitable. recommended=%s status=%s", Boolean.valueOf(webFeedBridge$WebFeedMetadata.f), Integer.valueOf(webFeedBridge$WebFeedMetadata.d));
                    } else {
                        Log.i("cr_WFFollowIntroCtrl", "No intro: No web feed metadata found");
                    }
                    aVar2.b(c0085a, null);
                }
                break;
            case 8:
                NotificationPlatformBridge notificationPlatformBridge = (NotificationPlatformBridge) obj3;
                lp1 lp1Var = (lp1) obj2;
                notificationPlatformBridge.getClass();
                if (!((Boolean) obj).booleanValue()) {
                    try {
                        notificationPlatformBridge.b.c(lp1Var);
                        kp1.a.a.b(7, lp1Var.a);
                    } catch (RuntimeException unused) {
                        Log.e("cr_NotificationPlatformBridge", "Failed to send notification, the IPC message might be corrupted.");
                        return;
                    }
                    break;
                }
                break;
            case 9:
                f fVar = (f) obj3;
                CompromisedCredential compromisedCredential = (CompromisedCredential) obj2;
                fVar.getClass();
                if (((Boolean) obj).booleanValue()) {
                    z7.b0(7);
                    fVar.c.p(h.d, compromisedCredential);
                    fVar.c.p(h.e, new g(fVar));
                }
                break;
            case 10:
            default:
                PostTask.c(rh3.a, new tf3((Uri) obj, i2, (jk2.e) obj3, (String) obj2));
                break;
            case 11:
                ((x42) obj3).h.g((Context) obj2, LearnMoreFragment.class);
                break;
            case 12:
                eq2 eq2Var = (eq2) obj3;
                eq2Var.getClass();
                ((SettingsLauncher) obj2).g(eq2Var.getContext(), SearchEngineSettings.class);
                eq2Var.dismiss();
                break;
            case 13:
                s71 s71Var = (s71) obj3;
                String str3 = (String) obj2;
                String str4 = (String) obj;
                s71Var.getClass();
                if (!str4.isEmpty()) {
                    s71Var.i = str4;
                }
                if (s71Var.n != 3) {
                    s71Var.n = 2;
                    if (s71Var.g.g) {
                        N.MwdUorUl(0);
                    } else {
                        N.MNJqVjyt(s71Var.f.c());
                    }
                    s71Var.A1(str3);
                    f83 f83Var = s71Var.j;
                    if (f83Var != null) {
                        f83Var.close();
                    }
                    s71Var.f.t(s71Var);
                    break;
                }
                break;
            case 14:
                we2 we2Var = (we2) obj3;
                String str5 = (String) obj2;
                Uri uri = (Uri) obj;
                WindowAndroid windowAndroid = we2Var.g;
                ArrayList arrayList = new ArrayList(Collections.singletonList(uri));
                we2Var.g.getClass();
                String type = xz.a.getContentResolver().getType(uri);
                String strJ = "";
                if (!TextUtils.isEmpty("") && !TextUtils.isEmpty("")) {
                    strJ = ((GURL) N.M1WDPiaY("")).j();
                }
                we2Var.f.a(new a(windowAndroid, str5, null, null, strJ, type, arrayList, null, null, null, null, null, null, null), new jr(false, false, false, null, new GURL(we2Var.h), false, false, null, 4), System.currentTimeMillis());
                break;
            case 15:
                Callback callback = (Callback) obj3;
                Tab tab4 = (Tab) obj2;
                String[] strArr = a10.q;
                a10.a aVarO = a10.o(((EndpointResponse) obj).a);
                if (aVarO != null) {
                    if (aVarO.a != null && aVarO.b != null) {
                        i = 1;
                    }
                    if (i != 0) {
                        a10Var = new a10(tab4);
                        a10Var.p = aVarO;
                        a10Var.o.r(Boolean.TRUE);
                    }
                }
                callback.onResult(a10Var);
                break;
            case 16:
                t93 t93Var = (t93) obj3;
                Object obj4 = t93.h;
                t93Var.getClass();
                t93Var.b((Bitmap) obj, ((s93) obj2).c());
                break;
            case 17:
                ((org.chromium.chrome.browser.toolbar.g) obj3).getClass();
                zb2.b((LoadUrlParams) obj, false, Boolean.FALSE, (Tab) ((nw2) obj2).get());
                break;
            case 18:
                SettingsLauncher settingsLauncher = (SettingsLauncher) obj3;
                Context context = (Context) obj2;
                if (((Integer) obj).intValue() == R$id.customize_adaptive_button_menu_id) {
                    n3.X0("MobileAdaptiveMenuCustomize");
                    settingsLauncher.g(context, AdaptiveToolbarPreferenceFragment.class);
                }
                break;
            case 19:
                org.chromium.chrome.browser.toolbar.top.d.a((org.chromium.chrome.browser.toolbar.top.d) obj3, (pk3) obj2, (hw0) obj);
                break;
            case 20:
                Tutorial tutorial = (Tutorial) obj3;
                Callback callback2 = (Callback) obj2;
                List list = (List) obj;
                int i5 = 0;
                while (true) {
                    if (i5 < list.size()) {
                        if (((Tutorial) list.get(i5)).a == tutorial.a) {
                            i = i5;
                        } else {
                            i5++;
                        }
                    }
                }
                callback2.onResult(i < list.size() + (-1) ? (Tutorial) list.get(i + 1) : null);
                break;
            case 21:
                rl3 rl3Var = (rl3) obj3;
                rl3Var.getClass();
                ((Callback) obj2).onResult(new BitmapDrawable(rl3Var.a.getResources(), (Bitmap) obj));
                break;
        }
    }
}
