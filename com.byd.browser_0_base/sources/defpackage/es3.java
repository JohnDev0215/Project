package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.Rect;
import android.util.Size;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.chrome.browser.webapps.d;
import org.chromium.chrome.browser.webapps.e;
import org.chromium.chrome.browser.webapps.f;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.settings.R$string;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter;
import org.chromium.components.download.NetworkStatusListenerAndroid;
import org.chromium.components.download.a;
import org.chromium.components.messages.MessageWrapper;
import org.chromium.components.messages.b;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.paintpreview.player.b;
import org.chromium.components.paintpreview.player.frame.PlayerFrameView;
import org.chromium.components.permissions.AndroidPermissionRequester;
import org.chromium.components.subresource_filter.AdsBlockedDialog;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;
import org.chromium.ui.modelutil.l;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class es3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ es3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                e eVar = (e) obj;
                Activity activity = eVar.a;
                if (!activity.isFinishing() && !activity.isDestroyed()) {
                    WebappRegistry webappRegistry = WebappRegistry.b.a;
                    String str = eVar.b;
                    ds3 ds3VarC = webappRegistry.c(str);
                    if (ds3VarC == null && eVar.c) {
                        new f(webappRegistry, str, new d(eVar)).c(i9.e);
                    } else {
                        eVar.a(ds3VarC, false);
                    }
                    break;
                }
                break;
            case 1:
                ((f73) obj).a();
                break;
            case 2:
                l43 l43Var = l43.this;
                Tab tabI = l43Var.k.i();
                if (tabI != null) {
                    l43Var.l.a(tabI);
                }
                l43Var.o = null;
                break;
            case 3:
                o93.a((o93) obj);
                break;
            case 4:
                SingleWebsiteSettings singleWebsiteSettings = (SingleWebsiteSettings) obj;
                String[] strArr = SingleWebsiteSettings.u0;
                FragmentActivity fragmentActivityD = singleWebsiteSettings.D();
                if (fragmentActivityD != null && !fragmentActivityD.isFinishing()) {
                    singleWebsiteSettings.V0("clear_data");
                    if (!(singleWebsiteSettings.f("clear_data") != null)) {
                        singleWebsiteSettings.V0("site_usage");
                    }
                    Preference preferenceF = singleWebsiteSettings.f("chooser_permission_list");
                    if (preferenceF != null) {
                        ChromeImageViewPreference chromeImageViewPreference = (ChromeImageViewPreference) preferenceF;
                        mb1 mb1Var = chromeImageViewPreference.S;
                        if (!(mb1Var != null && (mb1Var.r(chromeImageViewPreference) || chromeImageViewPreference.S.b(chromeImageViewPreference)))) {
                            singleWebsiteSettings.I0().d0(preferenceF);
                        }
                    }
                    singleWebsiteSettings.n0 = 0;
                    if (singleWebsiteSettings.o0 > 0) {
                        nb1.h(singleWebsiteSettings.K(), R$string.managed_settings_cannot_be_reset);
                    }
                    if (!singleWebsiteSettings.S0()) {
                        if (!(singleWebsiteSettings.f("clear_data") != null) && singleWebsiteSettings.D() != null) {
                            singleWebsiteSettings.D().finish();
                            break;
                        }
                    }
                }
                break;
            case 5:
                DragReorderableListAdapter dragReorderableListAdapter = DragReorderableListAdapter.this;
                dragReorderableListAdapter.G(dragReorderableListAdapter.j);
                break;
            case 6:
                ((l) obj).o();
                break;
            case 7:
                N.MLlibBXh(false, (Throwable) obj);
                break;
            case 8:
                a aVar = ((NetworkStatusListenerAndroid.a) obj).b;
                aVar.getClass();
                Object obj2 = ThreadUtils.a;
                aVar.a.f();
                break;
            case 9:
                ((fh1) obj).f();
                break;
            case 10:
                long j = ((MessageWrapper) obj).e;
                if (j != 0) {
                    N.MoS27uC7(j);
                    break;
                }
                break;
            case 11:
                b bVar = (b) obj;
                bVar.e.a(4, bVar.d);
                break;
            case 12:
                org.chromium.components.page_info.b bVar2 = (org.chromium.components.page_info.b) obj;
                PageInfoController pageInfoController = (PageInfoController) bVar2.e;
                pageInfoController.f(10);
                pageInfoController.e(bVar2);
                break;
            case 13:
                PageInfoContainer pageInfoContainer = (PageInfoContainer) obj;
                boolean z = pageInfoContainer.e.getVisibility() != 0;
                pageInfoContainer.e.setVisibility(z ? 0 : 8);
                pageInfoContainer.f.setVisibility(z ? 8 : 0);
                pageInfoContainer.announceForAccessibility(pageInfoContainer.getResources().getString(z ? org.chromium.components.page_info.R$string.page_info_url_expanded : org.chromium.components.page_info.R$string.page_info_url_truncated));
                break;
            case 14:
                ((PageInfoController) ((tu1) obj)).d();
                break;
            case 15:
                nu1.a((nu1) obj);
                break;
            case 16:
                ((b.InterfaceC0137b) obj).h();
                break;
            case 17:
                org.chromium.components.paintpreview.player.b bVar3 = (org.chromium.components.paintpreview.player.b) obj;
                w12 w12Var = bVar3.d;
                b.InterfaceC0137b interfaceC0137b = bVar3.i;
                if (w12Var != null) {
                    y12 y12Var = w12Var.a;
                    if ((y12Var == null ? null : y12Var.i) != null) {
                        if (bVar3.j == 0) {
                            interfaceC0137b.b();
                        } else if (bVar3.c.a(false)) {
                            interfaceC0137b.b();
                        } else if (!bVar3.c.a(true)) {
                            WebContentsAccessibilityImpl webContentsAccessibilityImpl = new WebContentsAccessibilityImpl(new s12(bVar3.d, bVar3.j, null));
                            bVar3.k = webContentsAccessibilityImpl;
                            bVar3.d.d.setWebContentsAccessibility(webContentsAccessibilityImpl);
                        } else {
                            y12 y12Var2 = bVar3.d.a;
                            float fB = (y12Var2 == null ? null : y12Var2.i).b();
                            y12 y12Var3 = bVar3.d.a;
                            float height = (y12Var3 == null ? null : y12Var3.i).a.getHeight();
                            if (!((fB * ((float) bVar3.c.c)) - height > height * 0.1f)) {
                                y12 y12Var4 = bVar3.d.a;
                                Rect rectA = (y12Var4 == null ? null : y12Var4.i).a();
                                int i2 = -1;
                                boolean z2 = false;
                                while (true) {
                                    nv1 nv1Var = bVar3.c;
                                    nv1[] nv1VarArr = nv1Var.d;
                                    if (i < nv1VarArr.length) {
                                        nv1 nv1Var2 = nv1VarArr[i];
                                        Rect rect = nv1Var.e[i];
                                        if (nv1Var2.b <= rect.width()) {
                                            if (nv1Var2.c <= rect.width()) {
                                                continue;
                                            }
                                            i++;
                                        }
                                        if (i2 != -1) {
                                            i2 = -1;
                                        } else {
                                            if ((rect.height() * rect.width()) / (rectA.height() * rectA.width()) > 0.8f) {
                                                z2 = true;
                                            }
                                            i2 = i;
                                            i++;
                                        }
                                    }
                                }
                                if (!z2) {
                                    i2 = -1;
                                }
                                if (i2 != -1) {
                                    ArrayList arrayList = bVar3.d.e;
                                    w12 w12Var2 = i2 <= arrayList.size() ? (w12) arrayList.get(i2) : null;
                                    if (w12Var2 != null) {
                                        Rect rect2 = bVar3.c.e[i2];
                                        WebContentsAccessibilityImpl webContentsAccessibilityImpl2 = new WebContentsAccessibilityImpl(new s12(w12Var2, bVar3.j, new Size(rect2.left, rect2.top)));
                                        bVar3.k = webContentsAccessibilityImpl2;
                                        w12Var2.d.setWebContentsAccessibility(webContentsAccessibilityImpl2);
                                    } else {
                                        interfaceC0137b.b();
                                    }
                                } else {
                                    interfaceC0137b.b();
                                }
                            } else {
                                interfaceC0137b.b();
                            }
                        }
                    }
                }
                interfaceC0137b.b();
                break;
            case 18:
                ((PlayerFrameView) obj).postInvalidate();
                break;
            case 19:
                org.chromium.components.payments.e eVar2 = (org.chromium.components.payments.e) obj;
                eVar2.g.a(11);
                eVar2.l(7, "User opted out of the process.");
                eVar2.D = null;
                break;
            case 20:
                x21 x21Var = (x21) obj;
                if (!x21Var.h) {
                    x21Var.A();
                }
                break;
            case 21:
                hh2 hh2Var = (hh2) obj;
                if (hh2Var.f.a()) {
                    hh2Var.a();
                    hh2Var.c.onResult(Boolean.FALSE);
                }
                break;
            case 22:
                ((AndroidPermissionRequester.c) obj).j();
                break;
            case 23:
                ka0 ka0Var = (ka0) obj;
                ka0Var.g = false;
                ka0Var.n = null;
                ka0Var.o = null;
                ka0Var.i = null;
                break;
            case 24:
                AdsBlockedDialog adsBlockedDialog = (AdsBlockedDialog) obj;
                adsBlockedDialog.g.j(adsBlockedDialog.h, 0, false);
                break;
            case 25:
                long j2 = ((AppWebMessagePort) obj).a;
                if (j2 != 0) {
                    N.MJ$gNv5y(j2);
                    break;
                }
                break;
            case 26:
                ((BrowserStartupControllerImpl) obj).e(1);
                break;
            case 27:
                xn.a((xn) obj);
                break;
            case 28:
                WebContentsAccessibilityImpl webContentsAccessibilityImpl3 = (WebContentsAccessibilityImpl) obj;
                webContentsAccessibilityImpl3.handleScrollPositionChanged(webContentsAccessibilityImpl3.s);
                int i3 = webContentsAccessibilityImpl3.s;
                webContentsAccessibilityImpl3.A(i3, 65536);
                webContentsAccessibilityImpl3.s = -1;
                webContentsAccessibilityImpl3.q(i3);
                break;
            default:
                DialogOverlayImpl dialogOverlayImpl = (DialogOverlayImpl) obj;
                dialogOverlayImpl.getClass();
                Object obj3 = ThreadUtils.a;
                if (dialogOverlayImpl.g != null) {
                    dialogOverlayImpl.C();
                    dialogOverlayImpl.t();
                    break;
                }
                break;
        }
    }
}
