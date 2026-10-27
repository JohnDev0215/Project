package defpackage;

import J.N;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.res.Resources;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import com.google.protobuf.d0;
import defpackage.m60;
import defpackage.tq0;
import java.util.HashSet;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.download.e;
import org.chromium.chrome.browser.download.home.toolbar.DownloadHomeToolbar;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.feed.sections.SectionHeaderView;
import org.chromium.chrome.browser.gesturenav.a;
import org.chromium.chrome.browser.keyboard_accessory.AutofillKeyboardAccessoryViewBridge;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.h;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.omnibox.suggestions.SuggestionLayout;
import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zc0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ zc0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        int i = this.e;
        int i2 = 3;
        kj3 kj3VarB0 = null;
        Object obj = this.f;
        switch (i) {
            case 0:
                e eVar = (e) obj;
                eVar.getClass();
                a8.z().k(eVar);
                return;
            case 1:
                ((m60.a) obj).e.i.h.l(t81.m, true);
                return;
            case 2:
                DownloadHomeToolbar downloadHomeToolbar = (DownloadHomeToolbar) obj;
                int i3 = DownloadHomeToolbar.B0;
                downloadHomeToolbar.getClass();
                qh3 qh3Var = new qh3(downloadHomeToolbar);
                downloadHomeToolbar.A0 = qh3Var;
                downloadHomeToolbar.u0 = downloadHomeToolbar.getResources().getDimensionPixelSize(R$dimen.toolbar_wide_display_start_offset);
                downloadHomeToolbar.t0 = qh3Var;
                qh3Var.a(downloadHomeToolbar);
                return;
            case 3:
                c cVar = (c) obj;
                int i4 = cVar.t;
                org.chromium.chrome.browser.feed.e eVar2 = cVar.j;
                if (eVar2.h() && !eVar2.g(i4)) {
                    eVar2.e.D.g0(i4);
                }
                pk3 pk3Var = new pk3(cVar.e, cVar.n);
                SectionHeaderView sectionHeaderView = cVar.x;
                Resources resources = sectionHeaderView.getContext().getResources();
                int i5 = R$string.feature_notification_guide_tooltip_message_ntp_suggestion_card;
                hw0 hw0Var = new hw0(resources, "IPH_FeatureNotificationGuideNTPSuggestionCardHelpBubble", i5, i5);
                hw0Var.j = sectionHeaderView.g;
                pk3Var.a(hw0Var.a());
                return;
            case 4:
                int i6 = org.chromium.chrome.browser.feed.webfeed.e.n;
                ((org.chromium.chrome.browser.feed.webfeed.e) obj).getClass();
                Log.i("cr_WFFollowIntroCtrl", "No intro: not allowed by feature engagement tracker");
                return;
            case 5:
                ar3 ar3Var = (ar3) obj;
                ar3Var.c.postDelayed(new ql0(i2, ar3Var), 200L);
                ar3Var.f.run();
                return;
            case 6:
                ((tq0.b) obj).f();
                return;
            case 7:
                ((a) obj).h.f();
                return;
            case 8:
                ((fy0) obj).getClass();
                HashSet hashSet = new HashSet();
                for (Activity activity : ApplicationStatus.b()) {
                    int iC = ApplicationStatus.c(activity);
                    if (iC != 5 && iC != 6) {
                        hashSet.add(Integer.valueOf(activity.getTaskId()));
                    }
                }
                HashSet hashSet2 = new HashSet(ChromeTabbedActivity.T1);
                hashSet2.add(ChromeLauncherActivity.class.getName());
                for (Pair pair : k5.a(xz.a, hashSet2)) {
                    if (!hashSet.contains(Integer.valueOf(((ActivityManager.RecentTaskInfo) pair.second).id))) {
                        ((ActivityManager.AppTask) pair.first).finishAndRemoveTask();
                    }
                }
                return;
            case 9:
                oy0 oy0Var = (oy0) obj;
                oy0Var.b.c(oy0Var.c);
                return;
            case 10:
                ly0 ly0Var = (ly0) obj;
                ly0Var.a.startActivity(ly0Var.j);
                return;
            case 11:
                sp spVar = (sp) obj;
                if (spVar.g) {
                    return;
                }
                spVar.g = true;
                Object obj2 = ThreadUtils.a;
                TraceEvent.q("NetworkChangeNotifier.init", null);
                NetworkChangeNotifier.init();
                NetworkChangeNotifier.setAutoDetectConnectivityState(true);
                TraceEvent.C("NetworkChangeNotifier.init");
                return;
            case 12:
                nj njVar = (nj) obj;
                if (njVar.d()) {
                    return;
                }
                njVar.c0();
                return;
            case 13:
                tt ttVar = (tt) obj;
                ttVar.getClass();
                ku2 ku2VarT = ku2.t();
                try {
                    ttVar.a("twa_dialog_number_of_dismissals_on_uninstall", true);
                    ttVar.a("twa_dialog_number_of_dismissals_on_clear_data", false);
                    ku2VarT.close();
                    return;
                } catch (Throwable th) {
                    try {
                        ku2VarT.close();
                        break;
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            case 14:
                AutofillKeyboardAccessoryViewBridge autofillKeyboardAccessoryViewBridge = (AutofillKeyboardAccessoryViewBridge) obj;
                long j = autofillKeyboardAccessoryViewBridge.e;
                if (j == 0) {
                    return;
                }
                N.MRSuTxnn(j, autofillKeyboardAccessoryViewBridge);
                return;
            case 15:
                nf1 nf1Var = (nf1) obj;
                nf1Var.f.c(nf1Var.k, true);
                return;
            case 16:
                ((i7) ((h7) obj)).c(null);
                return;
            case 17:
                ((nj1) obj).p("Android.WindowManager.NewWindow");
                return;
            case 18:
                int i7 = NewTabPageLayout.L;
                ((NewTabPageLayout) obj).e();
                return;
            case 19:
                int i8 = NewTabPageLayout.L;
                wm3.a((View) obj);
                return;
            case 20:
                h7 h7Var = ((nq1) obj).g;
                if (h7Var == null) {
                    return;
                }
                ((i7) h7Var).c(null);
                return;
            case 21:
                s62 s62Var = (s62) obj;
                String string = xz.a.getSharedPreferences("com.google.android.apps.chrome.omaha", 0).getString("UpdateProtos_Tracking", null);
                if (string != null) {
                    try {
                        kj3VarB0 = kj3.b0(Base64.decode(string, 0));
                        break;
                    } catch (d0 unused2) {
                    }
                }
                PostTask.c(rh3.a, new z8(s62Var, 17, kj3VarB0));
                return;
            case 22:
                UrlBar urlBar = (UrlBar) obj;
                if (urlBar.u == null || zb.b()) {
                    return;
                }
                urlBar.u.P();
                return;
            case 23:
                h hVar = (h) obj;
                hVar.j.e(hVar.f);
                hVar.m = h.p;
                return;
            case 24:
                OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = (OmniboxSuggestionsDropdown) obj;
                if (omniboxSuggestionsDropdown.d1 != 2) {
                    return;
                }
                ao3.e(omniboxSuggestionsDropdown, "OmniboxSuggestionsDropdown.onMeasure");
                omniboxSuggestionsDropdown.d1 = 3;
                return;
            case 25:
                SuggestionLayout suggestionLayout = (SuggestionLayout) obj;
                if (suggestionLayout.k != 2) {
                    return;
                }
                ao3.e(suggestionLayout, "OmniboxSuggestionsDropdown.onMeasure");
                suggestionLayout.k = 3;
                return;
            case 26:
                gu1 gu1Var = (gu1) obj;
                gu1Var.g(29, gu1Var.i.O().Q().Q());
                return;
            case 27:
                e91 e91Var = (e91) obj;
                if (e91Var == null) {
                    return;
                }
                h91 h91Var = e91Var.a.c;
                h91Var.c = 0.0f;
                PropertyModel.p pVar = g91.a;
                PropertyModel propertyModel = h91Var.a;
                propertyModel.n(pVar, 0);
                propertyModel.m(g91.b, h91Var.c);
                h91Var.b.sendEmptyMessage(1);
                return;
            case 28:
                PageLoadMetrics.a aVar = (PageLoadMetrics.a) obj;
                b<PageLoadMetrics.a> bVar = PageLoadMetrics.a;
                Object obj3 = ThreadUtils.a;
                PageLoadMetrics.a.c(aVar);
                PageLoadMetrics.b.c(aVar);
                return;
            default:
                PaintPreviewTabService paintPreviewTabService = (PaintPreviewTabService) obj;
                paintPreviewTabService.a.run();
                paintPreviewTabService.a = null;
                return;
        }
    }
}
