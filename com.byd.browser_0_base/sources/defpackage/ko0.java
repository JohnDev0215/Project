package defpackage;

import J.N;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.kp1;
import defpackage.lk1;
import defpackage.om3;
import defpackage.q6;
import defpackage.q82;
import java.util.Arrays;
import java.util.HashSet;
import java.util.WeakHashMap;
import org.chromium.base.BundleUtils;
import org.chromium.base.Callback;
import org.chromium.base.LocaleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$FollowResults;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata;
import org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem;
import org.chromium.chrome.browser.firstrun.LightweightFirstRunActivity;
import org.chromium.chrome.browser.firstrun.a;
import org.chromium.chrome.browser.infobar.AutofillVirtualCardEnrollmentInfoBar;
import org.chromium.chrome.browser.infobar.FramebustBlockInfoBar;
import org.chromium.chrome.browser.infobar.PermissionInfoBar;
import org.chromium.chrome.browser.keyboard_accessory.ManualFillingComponentBridge;
import org.chromium.chrome.browser.logo.LogoBridge;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.metrics.a;
import org.chromium.chrome.browser.metrics.a.C0094a;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.omnibox.LocationBarLayout;
import org.chromium.chrome.browser.omnibox.c;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.omnibox.geo.GeolocationHeader;
import org.chromium.chrome.browser.omnibox.i;
import org.chromium.chrome.browser.omnibox.j;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.omnibox.suggestions.SuggestionLayout;
import org.chromium.chrome.browser.omnibox.suggestions.e;
import org.chromium.chrome.browser.password_manager.PasswordGenerationDialogBridge;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.privacy_sandbox.AdMeasurementFragment;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.ResourceBundle;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ko0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ko0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = 6;
        int i2 = this.e;
        int i3 = 1;
        Object obj2 = this.f;
        switch (i2) {
            case 0:
                lo0 lo0Var = (lo0) obj2;
                ((Boolean) obj).booleanValue();
                lo0Var.getClass();
                N.M0KK$hz_(new ad0(new wb(25, lo0Var), i3));
                break;
            case 1:
                WebFeedMainMenuItem webFeedMainMenuItem = (WebFeedMainMenuItem) obj2;
                WebFeedBridge$FollowResults webFeedBridge$FollowResults = (WebFeedBridge$FollowResults) obj;
                int i4 = WebFeedMainMenuItem.s;
                webFeedMainMenuItem.getClass();
                WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata = webFeedBridge$FollowResults.b;
                webFeedMainMenuItem.r.a(webFeedMainMenuItem.g, webFeedBridge$FollowResults, webFeedBridge$WebFeedMetadata != null ? webFeedBridge$WebFeedMetadata.a : null, webFeedMainMenuItem.f, webFeedMainMenuItem.h, 1);
                PrefService prefServiceB = zk0.b();
                if (!prefServiceB.a("ntp_snippets.list_visible")) {
                    prefServiceB.e("ntp_snippets.list_visible", true);
                    zk0.e(1);
                }
                break;
            case 2:
                em0 em0Var = (em0) obj2;
                em0Var.getClass();
                ((w8) obj).b(em0Var);
                break;
            case 3:
                LightweightFirstRunActivity lightweightFirstRunActivity = (LightweightFirstRunActivity) obj2;
                int i5 = LightweightFirstRunActivity.y0;
                CustomTabActivity.i3(lightweightFirstRunActivity, lightweightFirstRunActivity.getString(R$string.google_terms_of_service_url).replace("$LOCALE", LocaleUtils.getDefaultLocaleString().replace('-', '_')));
                break;
            case 4:
                ((a) obj2).c(((Boolean) obj).booleanValue());
                break;
            case 5:
                dv0 dv0Var = ((org.chromium.chrome.browser.history.a) obj2).j;
                dv0Var.getClass();
                dv0Var.e(new GURL("https://myactivity.google.com/myactivity/?utm_source=chrome_h"), null, true);
                break;
            case 6:
                ((gv0) obj2).D();
                break;
            case 7:
                iy0 iy0Var = (iy0) obj2;
                l61 l61Var = (l61) obj;
                iy0Var.u = l61Var;
                l61Var.j(iy0Var.i);
                iy0Var.k();
                break;
            case 8:
                AutofillVirtualCardEnrollmentInfoBar autofillVirtualCardEnrollmentInfoBar = (AutofillVirtualCardEnrollmentInfoBar) obj2;
                N.ML4fygsm(autofillVirtualCardEnrollmentInfoBar.r, autofillVirtualCardEnrollmentInfoBar, "https://support.google.com/googlepay/answer/11234179", 2);
                break;
            case 9:
                ((FramebustBlockInfoBar) obj2).d();
                break;
            case 10:
                ((PermissionInfoBar) obj2).d();
                break;
            case 11:
                ManualFillingComponentBridge manualFillingComponentBridge = (ManualFillingComponentBridge) obj2;
                manualFillingComponentBridge.getClass();
                oj2.U(0, 8, "KeyboardAccessory.AccessoryActionSelected");
                N.MmIaCnPe(manualFillingComponentBridge.e, manualFillingComponentBridge, 0);
                break;
            case 12:
                q6 q6Var = (q6) obj2;
                int iIntValue = ((Integer) obj).intValue();
                if (iIntValue == 1) {
                    if (TextUtils.equals(u6.a(), q6Var.e.j.a)) {
                        oj2.U(3, 5, "LanguageSettings.AppLanguagePrompt.Action");
                    } else {
                        oj2.U(2, 5, "LanguageSettings.AppLanguagePrompt.Action");
                    }
                    Activity activity = q6Var.a;
                    View viewInflate = LayoutInflater.from(activity).inflate(R$layout.app_language_confirm_content, (ViewGroup) null, false);
                    f51 f51Var = q6Var.e.j;
                    ((TextView) viewInflate.findViewById(R$id.message)).setText(activity.getResources().getString(R$string.languages_srp_loading_text, f51Var.b));
                    PropertyModel propertyModel = q6Var.d;
                    propertyModel.p(di1.h, viewInflate);
                    if (a8.s == null) {
                        a8.s = new a8();
                    }
                    a8.s.getClass();
                    Object obj3 = BundleUtils.a;
                    HashSet hashSet = new HashSet(Arrays.asList(ResourceBundle.a));
                    String str = f51Var.a;
                    if (!(hashSet.contains(str) || TextUtils.equals(str, null))) {
                        q6Var.b.j(propertyModel, 1, false);
                    }
                    boolean zEquals = TextUtils.equals(u6.a(), str);
                    if (!zEquals) {
                        q6.b bVar = q6Var.e;
                        oj2.O("LanguageSettings.AppLanguagePrompt.IsTopLanguageSelected", bVar.h.contains(bVar.j));
                    }
                    N.MUccXkXt("LanguageSettings.AppLanguagePrompt.Language", TextUtils.equals(str, null) ? "" : str);
                    if (!yj.a()) {
                        SharedPreferencesManager.getInstance().o("Chrome.Language.ApplicationOverrideLanguage", str);
                    }
                    if (!zEquals) {
                        ((qh1) q6Var.f).h();
                    }
                } else {
                    q6Var.getClass();
                    if (iIntValue == 2) {
                        oj2.U(0, 5, "LanguageSettings.AppLanguagePrompt.Action");
                    } else if (iIntValue == 5) {
                        oj2.U(1, 5, "LanguageSettings.AppLanguagePrompt.Action");
                    } else {
                        oj2.U(4, 5, "LanguageSettings.AppLanguagePrompt.Action");
                    }
                }
                oj2.O("LanguageSettings.AppLanguagePrompt.OtherLanguagesShown", q6Var.e.k);
                N.M_jSkroX();
                break;
            case 13:
                ob1 ob1Var = (ob1) obj2;
                ob1Var.getClass();
                ((lk1.b) ob1Var.a).b(new LoadUrlParams("https://support.google.com/chrome/?p=is_chrome_managed", 0), false);
                break;
            case 14:
                org.chromium.chrome.browser.metrics.a aVar = (org.chromium.chrome.browser.metrics.a) obj2;
                y13 y13Var = (y13) obj;
                if (aVar.f) {
                    aVar.d = new d3(aVar, y13Var);
                    a.C0094a c0094a = aVar.new C0094a();
                    aVar.e = c0094a;
                    b<PageLoadMetrics.a> bVar2 = PageLoadMetrics.a;
                    Object obj4 = ThreadUtils.a;
                    PageLoadMetrics.a.a(c0094a);
                    UmaUtils.a = new gx(16, aVar);
                    break;
                }
                break;
            case 15:
                nj1 nj1Var = (nj1) obj2;
                nj1Var.getClass();
                int i6 = ((b11) obj).a;
                nj1.w(i6);
                y13 y13VarF = ((o53) oj2.r()).f(i6);
                if (y13VarF != null) {
                    y13VarF.e(true);
                }
                nj1Var.h.get().a(i6);
                Activity activityR = nj1.r(i6);
                if (activityR != null) {
                    activityR.finishAndRemoveTask();
                }
                break;
            case 16:
                to1 to1Var = (to1) obj2;
                Integer num = (Integer) obj;
                aj3<to1> aj3Var = to1.g;
                to1Var.getClass();
                if (num.intValue() != 2) {
                    SharedPreferencesManager.getInstance().c("Chrome.NotificationPermission.RequestCount");
                    oj2.U(kp1.a.a.a.d(0, "Chrome.NotificationPermission.RequestCount"), 50, "Mobile.SystemNotification.Permission.StartupRequestCount");
                    SharedPreferencesManager.getInstance().n(System.currentTimeMillis(), "Chrome.NotificationPermission.RationaleTimestamp");
                }
                if (num.intValue() == 0) {
                    to1Var.e.b(new String[]{"android.permission.POST_NOTIFICATIONS"}, new i83(i));
                }
                break;
            case 17:
                q82.a aVar2 = (q82.a) obj;
                sr1 sr1Var = h.this.y;
                if (sr1Var != null) {
                    sr1Var.e(aVar2.a, aVar2.b);
                    break;
                }
                break;
            case 18:
                NewTabPageLayout newTabPageLayout = (NewTabPageLayout) obj2;
                LogoBridge.Logo logo = (LogoBridge.Logo) obj;
                newTabPageLayout.z = true;
                newTabPageLayout.u = logo != null;
                newTabPageLayout.e();
                break;
            case 19:
                mq1 mq1Var = (mq1) obj2;
                mq1Var.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (!mq1Var.o || mq1Var.n != zBooleanValue) {
                    Handler handler = mq1Var.c;
                    kq1 kq1Var = mq1Var.l;
                    handler.removeCallbacks(kq1Var);
                    long jElapsedRealtime = SystemClock.elapsedRealtime() - mq1Var.m;
                    if (jElapsedRealtime >= 5000) {
                        mq1Var.b(zBooleanValue);
                    } else {
                        handler.postDelayed(kq1Var, 5000 - jElapsedRealtime);
                    }
                }
                break;
            case 20:
                g gVar = (g) obj2;
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                gVar.h0(true);
                gVar.K = zBooleanValue2;
                gVar.P.r(Boolean.valueOf(zBooleanValue2));
                gVar.n0();
                gVar.p0();
                gVar.I();
                f fVar = gVar.h;
                if (zBooleanValue2) {
                    if (gVar.D) {
                        n3.X0("FocusLocation");
                    }
                    rj3 rj3VarB = fVar.b();
                    if (rj3VarB.c != null) {
                        gVar.m.g(rj3VarB, 0, 0);
                    }
                } else {
                    gVar.E = false;
                }
                et2 et2Var = gVar.j;
                wt2 wt2Var = et2Var.f;
                if (wt2Var.j != zBooleanValue2) {
                    wt2Var.j = zBooleanValue2;
                    wt2Var.h();
                    wt2Var.g();
                    wt2Var.f(0);
                    if (!wt2Var.j && true != wt2Var.I) {
                        wt2Var.I = true;
                        wt2Var.f(0);
                    }
                }
                et2Var.k = zBooleanValue2;
                et2Var.b();
                gVar.V(zBooleanValue2);
                if (zBooleanValue2 && fVar.q() && !fVar.isIncognito()) {
                    qs1<TemplateUrlService> qs1Var = gVar.r;
                    if (!qs1Var.f()) {
                        qs1Var.h(new lj1(i, gVar));
                    } else if (qs1Var.get().e()) {
                        GeolocationHeader.f(gVar.n.get(), qs1Var.get());
                    }
                }
                if (!zBooleanValue2 && fVar.q()) {
                    gVar.e0(fVar.n(), fVar.b());
                    break;
                }
                break;
            case 21:
                c cVar = (c) obj2;
                LocationBarLayout locationBarLayout = cVar.e;
                int iIntValue2 = ((Integer) obj).intValue();
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.e.h(locationBarLayout, iIntValue2);
                cVar.i.c();
                break;
            case 22:
                i iVar = (i) obj2;
                boolean zBooleanValue3 = ((Boolean) obj).booleanValue();
                iVar.g = zBooleanValue3;
                PropertyModel.n nVar = j.b;
                PropertyModel propertyModel2 = iVar.e;
                if (propertyModel2.k(nVar)) {
                    propertyModel2.l(j.f, iVar.g);
                }
                PropertyModel.r<j.b> rVar = j.h;
                j.b bVar3 = (j.b) propertyModel2.j(rVar);
                iVar.f.onResult(Boolean.valueOf(zBooleanValue3));
                boolean z = propertyModel2.j(rVar) != bVar3;
                if (iVar.h != null && !z) {
                    iVar.d();
                    break;
                }
                break;
            case 23:
                OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = (OmniboxSuggestionsDropdown) obj2;
                e.a aVar3 = (e.a) obj;
                boolean zA = aVar3.a(omniboxSuggestionsDropdown.Z0);
                e.a aVar4 = omniboxSuggestionsDropdown.Z0;
                boolean z2 = aVar4 == null || aVar3.c != aVar4.c;
                omniboxSuggestionsDropdown.Z0 = aVar3;
                if (!zA) {
                    if (z2) {
                        tr1 tr1Var = omniboxSuggestionsDropdown.T0;
                        omniboxSuggestionsDropdown.setLayoutFrozen(false);
                        omniboxSuggestionsDropdown.h0(tr1Var, true);
                        omniboxSuggestionsDropdown.X(true);
                        omniboxSuggestionsDropdown.requestLayout();
                    }
                    ao3.e(omniboxSuggestionsDropdown, "OmniboxSuggestionsDropdown.onOmniboxAlignmentChanged");
                } else {
                    omniboxSuggestionsDropdown.o0();
                }
                break;
            case 24:
                SuggestionLayout suggestionLayout = (SuggestionLayout) obj2;
                e.a aVar5 = (e.a) obj;
                boolean zA2 = aVar5.a(suggestionLayout.g);
                suggestionLayout.g = aVar5;
                if (!zA2) {
                    ao3.e(suggestionLayout, "OmniboxSuggestionsDropdown.onOmniboxAlignmentChanged");
                } else {
                    suggestionLayout.a();
                }
                break;
            case 25:
                l80 l80Var = (l80) obj2;
                l80Var.getClass();
                PostTask.d(rh3.c, new y70(l80Var, 11, (Boolean) obj));
                break;
            case 26:
                org.chromium.chrome.browser.password_edit_dialog.c cVar2 = (org.chromium.chrome.browser.password_edit_dialog.c) obj2;
                String str2 = (String) obj;
                cVar2.e.p(fx1.b, str2);
                PropertyModel propertyModel3 = cVar2.f;
                PropertyModel.r<String> rVar2 = di1.j;
                boolean zContains = cVar2.g.contains(str2);
                Resources resources = cVar2.i;
                propertyModel3.p(rVar2, zContains ? resources.getString(R$string.password_manager_update_button) : resources.getString(R$string.password_manager_save_button));
                break;
            case 27:
                PasswordGenerationDialogBridge passwordGenerationDialogBridge = (PasswordGenerationDialogBridge) obj2;
                boolean zBooleanValue4 = ((Boolean) obj).booleanValue();
                long j = passwordGenerationDialogBridge.a;
                if (j != 0) {
                    if (zBooleanValue4) {
                        N.M1W3B6UT(j, passwordGenerationDialogBridge, passwordGenerationDialogBridge.c);
                    } else {
                        N.Ml5BeqqW(j, passwordGenerationDialogBridge);
                    }
                    gx1 gx1Var = passwordGenerationDialogBridge.b;
                    gx1Var.a.b(3, gx1Var.d);
                    break;
                }
                break;
            default:
                ((AdMeasurementFragment) obj2).l0.run();
                break;
        }
    }
}
