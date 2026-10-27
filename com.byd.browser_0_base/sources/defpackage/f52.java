package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.an;
import java.util.HashSet;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.chrome.browser.searchwidget.SearchActivityLocationBarLayout;
import org.chromium.chrome.browser.settings.MainSettings;
import org.chromium.chrome.browser.share.qrcode.QRCodeGenerationRequest;
import org.chromium.chrome.browser.signin.PrivacyFragment;
import org.chromium.chrome.browser.tasks.tab_management.g1;
import org.chromium.chrome.browser.toolbar.ToolbarProgressBar;
import org.chromium.chrome.browser.toolbar.top.ToolbarLayout;
import org.chromium.chrome.browser.toolbar.top.n;
import org.chromium.chrome.browser.ui.autofill.AutofillProgressDialogBridge;
import org.chromium.chrome.browser.ui.fast_checkout.FastCheckoutBridge;
import org.chromium.chrome.browser.upgrade.PackageReplacedBroadcastReceiver;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.components.messages.b;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.a;
import org.chromium.components.page_info.c;
import org.chromium.components.signin.identitymanager.AccountTrackerService;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class f52 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ f52(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int iIndexOfChild;
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((g52) obj).l.pageScroll(130);
                break;
            case 1:
                ((i7) ((b92) obj).b).c(null);
                break;
            case 2:
                SearchActivity searchActivity = (SearchActivity) obj;
                searchActivity.e0 = true;
                String str = searchActivity.f0;
                if (str != null) {
                    searchActivity.l2(str, searchActivity.g0, searchActivity.h0, searchActivity.i0);
                }
                CustomTabsConnection.e().u();
                so3 so3VarD = searchActivity.k0.d();
                int iK2 = SearchActivity.k2(searchActivity.getIntent().getAction());
                if (q11.i(searchActivity.getIntent(), "org.chromium.chrome.browser.ui.searchactivityutils.FROM_QUICK_ACTION_SEARCH_WIDGET", false)) {
                    if (iK2 == 1) {
                        n3.X0("QuickActionSearchWidget.VoiceQuery");
                    } else if (iK2 == 2) {
                        n3.X0("QuickActionSearchWidget.LensQuery");
                    } else if (iK2 == 0) {
                        n3.X0("QuickActionSearchWidget.TextQuery");
                    }
                }
                SearchActivityLocationBarLayout searchActivityLocationBarLayout = searchActivity.j0;
                g3 g3Var = searchActivity.O;
                searchActivityLocationBarLayout.l.a();
                zf2.e(searchActivityLocationBarLayout.getContext(), g3Var);
                searchActivityLocationBarLayout.r = false;
                searchActivityLocationBarLayout.l.h.f73J = false;
                String strB = searchActivityLocationBarLayout.k.b();
                if (!TextUtils.isEmpty(strB)) {
                    searchActivityLocationBarLayout.l.b(searchActivityLocationBarLayout.k.c(), strB);
                }
                if (searchActivityLocationBarLayout.s) {
                    searchActivityLocationBarLayout.d(iK2, so3VarD, g3Var);
                    searchActivityLocationBarLayout.s = false;
                }
                n3.X0("SearchWidget.WidgetSelected");
                SearchActivity.j2().getClass();
                break;
            case 3:
                int i2 = MainSettings.m0;
                ((MainSettings) obj).N0();
                break;
            case 4:
                s71 s71Var = (s71) obj;
                if (s71Var.n == 1) {
                    if (!s71Var.g.g) {
                        s71Var.y1(11);
                    } else {
                        s71Var.z1(5);
                    }
                }
                break;
            case 5:
                i82 i82Var = (i82) obj;
                String str2 = i82Var.f;
                if (!TextUtils.isEmpty(str2)) {
                    new QRCodeGenerationRequest(str2, new h82(i82Var, str2));
                } else {
                    i82Var.b.p(m82.b, i82Var.a.getResources().getString(R$string.qr_code_error_unknown));
                }
                break;
            case 6:
                ki2 ki2Var = (ki2) obj;
                ji jiVar = ki2Var.e;
                jiVar.c(jiVar.h(), true);
                ki2Var.a();
                break;
            case 7:
                PrivacyFragment privacyFragment = (PrivacyFragment) obj;
                privacyFragment.Z.removeAllViews();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) privacyFragment.Z.getLayoutParams();
                marginLayoutParams.setMarginEnd(vh3.a(vh3.f(privacyFragment.v0()) ? 19 : 9));
                privacyFragment.Z.setLayoutParams(marginLayoutParams);
                privacyFragment.Z.addView(((LayoutInflater) privacyFragment.Z.getContext().getSystemService("layout_inflater")).inflate(R$layout.privacy_content_large, (ViewGroup) null, false));
                break;
            case 8:
                ((AccountTrackerService) obj).F();
                break;
            case 9:
                ft2 ft2Var = (ft2) obj;
                if (!ft2Var.h) {
                    ft2Var.g.a().d(ft2Var.e, ft2Var.f);
                    ft2Var.h = true;
                    break;
                }
                break;
            case 10:
                pi1 pi1Var = (pi1) obj;
                l91 l91Var = pi1Var.b;
                pi1Var.a = l91Var;
                pi1Var.b = null;
                if (l91Var != null) {
                    Log.i("cr_TopSites", "Start a new task.");
                    pi1Var.a.run();
                }
                break;
            case 11:
            default:
                n3.m1(((vq) ((c) obj)).i, 8);
                break;
            case 12:
                p33 p33Var = ((n33) obj).e;
                if (p33Var.j == 1) {
                    p33Var.f(2);
                }
                p33Var.l = false;
                break;
            case 13:
                g1 g1Var = (g1) obj;
                HashSet hashSet = g1.f;
                g1Var.a.onResult(new g1.b(g1Var.b, g1Var.c.computeHorizontalScrollOffset()));
                break;
            case 14:
                ((ib3) obj).k = true;
                break;
            case 15:
                ToolbarLayout toolbarLayout = (ToolbarLayout) obj;
                ViewGroup viewGroup = (ViewGroup) toolbarLayout.getRootView().findViewById(R$id.view_url_bar);
                if (viewGroup == null) {
                    viewGroup = (ViewGroup) toolbarLayout.getRootView().findViewById(R$id.control_container);
                }
                ToolbarProgressBar toolbarProgressBar = toolbarLayout.l;
                View view = (View) toolbarLayout.getParent();
                if (viewGroup.indexOfChild(toolbarProgressBar) < 0 && (iIndexOfChild = ((ViewGroup) toolbarLayout.getRootView().findViewById(R$id.control_container)).indexOfChild(view)) >= 0) {
                    viewGroup.addView(toolbarProgressBar, iIndexOfChild + 1);
                }
                toolbarLayout.l.setProgressBarContainer(viewGroup);
                break;
            case 16:
                n nVar = (n) obj;
                boolean zB = nVar.b.b();
                n.a aVar = nVar.a;
                if (!zB) {
                    aVar.b(true);
                } else {
                    aVar.b(false);
                }
                break;
            case 17:
                t6 t6Var = (t6) obj;
                if (t6Var.m) {
                    t6Var.m = false;
                    oj2.b0(SystemClock.elapsedRealtime() - t6Var.o, "Android.AppLaunch.DurationDrawWasBlocked.OnIncognitoReauth");
                }
                break;
            case 18:
                ((nc2) obj).x.v(12, false);
                break;
            case 19:
                ((AutofillProgressDialogBridge) obj).dismiss();
                break;
            case 20:
                long j = ((FastCheckoutBridge) ((ek0) obj).b).a;
                if (j != 0) {
                    N.MzJ7c8YJ(j);
                }
                C0173a.a(6);
                break;
            case 21:
                km2 km2Var = (km2) obj;
                if (!km2Var.b()) {
                    km2Var.o = new e1(km2Var.e, km2Var, km2Var.f);
                    break;
                }
                break;
            case 22:
                BroadcastReceiver.PendingResult pendingResult = (BroadcastReceiver.PendingResult) obj;
                int i3 = PackageReplacedBroadcastReceiver.a;
                Object obj2 = an.e;
                an anVar = an.a.a;
                if (anVar.c && anVar.b.d(-1, "channels_version_key") != anVar.d) {
                    anVar.a();
                }
                if (!(Build.VERSION.SDK_INT > 29 || (xz.a.getApplicationInfo().flags & 1) != 0)) {
                    p90.a(Runtime.getRuntime());
                }
                pendingResult.finish();
                break;
            case 23:
                yr2 yr2Var = (yr2) obj;
                sr1 sr1Var = yr2Var.z;
                if (sr1Var != null) {
                    so3 so3VarD2 = sr1Var.d();
                    PropertyModel propertyModel = yr2Var.h;
                    if (so3VarD2 != null) {
                        propertyModel.l(n73.i, yr2Var.z.d().a());
                    }
                    PropertyModel.n nVar2 = n73.e;
                    yr2Var.z.t();
                    propertyModel.l(nVar2, false);
                }
                break;
            case 24:
                o93.a((o93) obj);
                break;
            case 25:
                boolean z = PickerVideoPlayer.E;
                ((PickerVideoPlayer) obj).h();
                break;
            case 26:
                ((ListMenuButton) obj).b();
                break;
            case 27:
                b bVar = (b) obj;
                if (!bVar.i) {
                    ((Runnable) bVar.d.j(ng1.e)).run();
                    break;
                }
                break;
            case 28:
                a aVar2 = (a) obj;
                PageInfoController pageInfoController = (PageInfoController) aVar2.g;
                pageInfoController.f(30);
                pageInfoController.e(aVar2);
                break;
        }
    }
}
