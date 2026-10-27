package defpackage;

import J.N;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.b;
import androidx.fragment.app.j;
import androidx.mediarouter.media.MediaRouteProviderService;
import defpackage.an;
import defpackage.iq2;
import defpackage.l53;
import defpackage.s4;
import defpackage.v4;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import org.chromium.base.BuildInfo;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.base.c;
import org.chromium.base.library_loader.a;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedPageInformation;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.notifications.channels.LocaleChangedBroadcastReceiver;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.chrome.browser.tab.R$id;
import org.chromium.chrome.browser.tab.R$layout;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.browser_ui.contacts_picker.PickerCategoryView;
import org.chromium.components.permissions.PermissionDialogController;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content.app.ContentChildProcessServiceDelegate;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y70 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ y70(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        td1 td1Var;
        SpannableString spannableStringA;
        int i = 21;
        int i2 = 20;
        int i3 = 8;
        int i4 = 1;
        switch (this.e) {
            case 0:
                b.c cVar = (b.c) this.f;
                j.b bVar = (j.b) this.g;
                i21.e(cVar, "$transitionInfo");
                i21.e(bVar, "$operation");
                cVar.a();
                return;
            case 1:
                MediaRouteProviderService.b.a aVar = (MediaRouteProviderService.b.a) this.f;
                if (aVar.k.remove((String) this.g) == null || (td1Var = MediaRouteProviderService.b.this.a.h.k) == null) {
                    return;
                }
                MediaRouteProviderService.e(aVar.a, 5, 0, 0, aVar.a(td1Var), null);
                return;
            case 2:
                c cVar2 = (c) this.f;
                zi3 zi3Var = (zi3) this.g;
                cVar2.getClass();
                zi3Var.O();
                return;
            case 3:
                ((nn) this.f).a.h((IBinder) this.g);
                return;
            case 4:
                ((ContentChildProcessServiceDelegate) ((zn) this.f).a).getClass();
                a aVar2 = a.l;
                synchronized (aVar2.h) {
                    if (aVar2.d) {
                        return;
                    }
                    TraceEvent traceEventH0 = TraceEvent.H0("LibraryLoader.preloadAlreadyLocked", null);
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    return;
                }
            case 5:
                q30 q30Var = (q30) this.f;
                Tab tab = (Tab) this.g;
                Activity activity = q30Var.e;
                if (activity.isFinishing() || activity.isDestroyed() || tab.isDestroyed()) {
                    return;
                }
                DownloadManagerService.g().m.j.add(tab.v());
                return;
            case 6:
                e.a aVar3 = (e.a) this.f;
                e.a.C0085a c0085a = (e.a.C0085a) this.g;
                if (!aVar3.c.a("webfeed_follow_intro_debug.enable")) {
                    if (aVar3.a(c0085a)) {
                        N.Mtbhx3K5(c0085a.b, new t02(new xm1(aVar3, 5, c0085a), i4));
                        return;
                    }
                    return;
                } else {
                    Log.i("cr_WFFollowIntroCtrl", "Intro debug mode is enabled: some checks will be skipped");
                    if (aVar3.a(c0085a)) {
                        N.MSWj76M1(new WebFeedBridge$WebFeedPageInformation(c0085a.a, c0085a.b), 1, new n62(aVar3, 7, c0085a));
                        return;
                    } else {
                        aVar3.b(c0085a, null);
                        return;
                    }
                }
            case 7:
                sp spVar = (sp) this.f;
                nj njVar = (nj) this.g;
                spVar.getClass();
                if (njVar.d()) {
                    return;
                }
                spVar.d = true;
                njVar.e1();
                return;
            case 8:
                LocaleChangedBroadcastReceiver localeChangedBroadcastReceiver = (LocaleChangedBroadcastReceiver) this.f;
                BroadcastReceiver.PendingResult pendingResult = (BroadcastReceiver.PendingResult) this.g;
                int i5 = LocaleChangedBroadcastReceiver.a;
                localeChangedBroadcastReceiver.getClass();
                Object obj = an.e;
                an anVar = an.a.a;
                anVar.getClass();
                synchronized (an.e) {
                    if (anVar.c) {
                        anVar.a.c(xz.a.getResources());
                    }
                }
                pendingResult.finish();
                Log.i("language", " language_change ".concat(localeChangedBroadcastReceiver.getClass().getSimpleName()));
                new Handler(Looper.getMainLooper()).post(new o90(i3));
                return;
            case 9:
                ((g) this.f).c0((String) this.g);
                return;
            case 10:
                vq vqVar = (vq) this.f;
                Consumer consumer = (Consumer) this.g;
                vqVar.getClass();
                consumer.accept(new ql0(23, vqVar));
                return;
            case 11:
                l80 l80Var = (l80) this.f;
                Boolean bool = (Boolean) this.g;
                l80Var.getClass();
                boolean zBooleanValue = bool.booleanValue();
                boolean zC = zBooleanValue ? l80Var.b.c(l80Var) : false;
                Toast.c(l80Var.a.getContext(), zC ? R$string.paint_preview_demo_capture_success : R$string.paint_preview_demo_capture_failure, 1).e();
                if (zBooleanValue && zC) {
                    return;
                }
                N.M9gwtxem();
                l80Var.a.t(l80Var.c);
                l80Var.a = null;
                l80Var.b = null;
                return;
            case 12:
                PartnerBrowserCustomizations partnerBrowserCustomizations = (PartnerBrowserCustomizations) this.f;
                Runnable runnable = (Runnable) this.g;
                if (partnerBrowserCustomizations.e.remove(runnable)) {
                    if (!partnerBrowserCustomizations.d) {
                        Log.w("cr_PartnerCustomize", "mInitializeAsyncCallbacks executed as timeout expired.");
                    }
                    runnable.run();
                    return;
                }
                return;
            case 13:
                od2 od2Var = (od2) this.f;
                Exception exc = (Exception) this.g;
                if (od2Var.a == null) {
                    return;
                }
                oj2.U(pd2.a(8), 10, "Settings.SafetyCheck.PasswordsResult");
                if ((exc instanceof dx1) && ((dx1) exc).e == 4) {
                    od2Var.a.n(pd2.a, 9);
                } else {
                    od2Var.a.n(pd2.a, 8);
                }
                od2Var.k();
                return;
            case 14:
                SigninFirstRunFragment signinFirstRunFragment = (SigninFirstRunFragment) this.f;
                Configuration configuration = (Configuration) this.g;
                FrameLayout frameLayout = signinFirstRunFragment.Z;
                if (frameLayout != null) {
                    frameLayout.removeAllViews();
                    signinFirstRunFragment.Z.addView(signinFirstRunFragment.I0((LayoutInflater) signinFirstRunFragment.D().getSystemService("layout_inflater"), configuration));
                    return;
                }
                return;
            case 15:
                l53.a aVar4 = (l53.a) this.f;
                hd2 hd2Var = (hd2) this.g;
                ContextThemeWrapper contextThemeWrapper = l53.this.f.d;
                final eu1 eu1Var = new eu1(i2, aVar4);
                z8 z8Var = new z8(aVar4, 22, hd2Var);
                Tab tab2 = hd2Var.e;
                if (tab2.c() == null) {
                    return;
                }
                int i6 = hd2Var.g + 1;
                hd2Var.g = i6;
                final boolean z = i6 >= 2;
                boolean zIsIncognito = tab2.isIncognito();
                View viewInflate = ((LayoutInflater) contextThemeWrapper.getSystemService("layout_inflater")).inflate(R$layout.sad_tab, (ViewGroup) null);
                viewInflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                ((TextView) viewInflate.findViewById(R$id.sad_tab_title)).setText(z ? org.chromium.chrome.browser.tab.R$string.sad_tab_reload_title : org.chromium.chrome.browser.tab.R$string.sad_tab_title);
                if (z) {
                    TextView textView = (TextView) viewInflate.findViewById(R$id.sad_tab_suggestions_title);
                    textView.setVisibility(0);
                    textView.setText(org.chromium.chrome.browser.tab.R$string.sad_tab_reload_try);
                    TextView textView2 = (TextView) viewInflate.findViewById(R$id.sad_tab_suggestions);
                    textView2.setVisibility(0);
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    if (!zIsIncognito) {
                        spannableStringBuilder.append((CharSequence) hd2.y1(contextThemeWrapper, org.chromium.chrome.browser.tab.R$string.sad_tab_reload_incognito)).append((CharSequence) "\n");
                    }
                    spannableStringBuilder.append((CharSequence) hd2.y1(contextThemeWrapper, org.chromium.chrome.browser.tab.R$string.sad_tab_reload_restart_browser)).append((CharSequence) "\n").append((CharSequence) hd2.y1(contextThemeWrapper, org.chromium.chrome.browser.tab.R$string.sad_tab_reload_restart_device)).append((CharSequence) "\n");
                    textView2.setText(spannableStringBuilder);
                }
                TextView textView3 = (TextView) viewInflate.findViewById(R$id.sad_tab_message);
                un1 un1Var = new un1(contextThemeWrapper, new Callback() { // from class: fd2
                    @Override // org.chromium.base.Callback
                    public final void onResult(Object obj2) {
                        if (z) {
                            oj2.U(2, 3, "Tabs.SadTab.Feedback.Event");
                        } else {
                            oj2.U(2, 3, "Tabs.SadTab.Reload.Event");
                        }
                        eu1Var.run();
                    }
                });
                if (z) {
                    spannableStringA = new SpannableString(contextThemeWrapper.getString(org.chromium.chrome.browser.tab.R$string.sad_tab_reload_learn_more));
                    spannableStringA.setSpan(un1Var, 0, spannableStringA.length(), 0);
                } else {
                    spannableStringA = iq2.a(contextThemeWrapper.getString(org.chromium.chrome.browser.tab.R$string.sad_tab_message) + "\n\n" + contextThemeWrapper.getString(org.chromium.chrome.browser.tab.R$string.sad_tab_suggestions), new iq2.a(un1Var, "<link>", "</link>"));
                }
                textView3.setText(spannableStringA);
                textView3.setMovementMethod(LinkMovementMethod.getInstance());
                Button button = (Button) viewInflate.findViewById(R$id.sad_tab_button);
                button.setText(z ? org.chromium.chrome.browser.tab.R$string.sad_tab_send_feedback_label : org.chromium.chrome.browser.tab.R$string.sad_tab_reload_label);
                button.setOnClickListener(new gd2(z, z8Var));
                if (z) {
                    oj2.U(0, 3, "Tabs.SadTab.Feedback.Event");
                } else {
                    oj2.U(0, 3, "Tabs.SadTab.Reload.Event");
                }
                hd2Var.f = viewInflate;
                tab2.o().a(hd2Var);
                return;
            case 16:
                ek0 ek0Var = (ek0) this.f;
                ek0Var.d((FastCheckoutCreditCard) this.g);
                ek0Var.b(0);
                return;
            case 17:
                m51 m51Var = (m51) this.f;
                Runnable runnable2 = (Runnable) this.g;
                m51Var.getClass();
                oj2.U(2, 3, "VideoTutorials.LanguagePicker.Action");
                String str = m51Var.e;
                em3 em3Var = m51Var.a;
                em3Var.e(str);
                List<String> listA = em3Var.a(m51Var.f);
                int i7 = 0;
                while (true) {
                    if (i7 < listA.size()) {
                        if (TextUtils.equals(listA.get(i7), m51Var.e)) {
                            oj2.W(i7, 0, 20, 21, "VideoTutorials.LanguagePicker.LanguageSelected");
                        } else {
                            i7++;
                        }
                    }
                }
                runnable2.run();
                return;
            case 18:
                ug3 ug3Var = (ug3) this.f;
                Tutorial tutorial = (Tutorial) this.g;
                ug3Var.getClass();
                n3.a1(tutorial.a, 11);
                ug3Var.e.onResult(tutorial);
                return;
            case 19:
                ((PickerCategoryView) this.f).m.h((HashSet) this.g);
                return;
            case 20:
                ((Callback) this.f).onResult(Boolean.valueOf(((Integer) this.g).intValue() == 0));
                return;
            case 21:
                org.chromium.components.payments.a aVar5 = (org.chromium.components.payments.a) this.f;
                ((org.chromium.components.payments.e) aVar5.s).o((String) this.g);
                aVar5.s = null;
                return;
            case 22:
                kh2 kh2Var = (kh2) this.f;
                ji jiVar = (ji) this.g;
                jiVar.e(kh2Var.g);
                jiVar.c(kh2Var.h, true);
                return;
            case 23:
                PermissionDialogController permissionDialogController = (PermissionDialogController) this.f;
                Context context = (Context) this.g;
                if (permissionDialogController.g != null) {
                    return;
                }
                ym2 ym2Var = new ym2(permissionDialogController.i, new l51(i, permissionDialogController));
                PropertyModel.b bVar2 = new PropertyModel.b(di1.B);
                bVar2.e(di1.a, ym2Var);
                bVar2.e(di1.c, context.getString(org.chromium.components.permissions.R$string.overlay_detected_dialog_title, BuildInfo.a.a.a));
                bVar2.e(di1.f, context.getResources().getString(org.chromium.components.permissions.R$string.overlay_detected_dialog_message));
                bVar2.d(di1.j, context.getResources(), org.chromium.components.permissions.R$string.cancel);
                bVar2.d(di1.m, context.getResources(), org.chromium.components.permissions.R$string.try_again);
                bVar2.b(di1.q, true);
                PropertyModel propertyModelA = bVar2.a();
                permissionDialogController.g = propertyModelA;
                permissionDialogController.i.j(propertyModelA, 1, true);
                return;
            case 24:
                TemplateUrlService templateUrlService = (TemplateUrlService) this.f;
                TemplateUrlService.b bVar3 = (TemplateUrlService.b) this.g;
                if (templateUrlService.a.b(bVar3)) {
                    bVar3.g();
                    return;
                }
                return;
            case 25:
                ((v4.m) ((s4.c) this.f)).c((c92) this.g);
                return;
            default:
                NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = (NetworkChangeNotifierAutoDetect) this.f;
                Runnable runnable3 = (Runnable) this.g;
                if (networkChangeNotifierAutoDetect.j) {
                    runnable3.run();
                    return;
                }
                return;
        }
    }
}
