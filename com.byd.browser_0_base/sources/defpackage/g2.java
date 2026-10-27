package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataTabsFragment;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.history.HistoryActivity;
import org.chromium.chrome.browser.omnibox.c;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.chrome.browser.safety_check.SafetyCheckSettingsFragment;
import org.chromium.chrome.browser.tasks.tab_management.h;
import org.chromium.components.browser_ui.accessibility.AccessibilitySettings;
import org.chromium.components.browser_ui.site_settings.SiteSettings;
import org.chromium.components.omnibox.action.OmniboxPedal;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class g2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ g2(Object obj, int i, int i2, Object obj2) {
        this.e = i2;
        this.g = obj;
        this.h = obj2;
        this.f = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        int i2 = this.f;
        Object obj2 = this.h;
        Object obj3 = this.g;
        switch (i) {
            case 0:
                h2 h2Var = (h2) obj3;
                OmniboxPedal omniboxPedal = (OmniboxPedal) obj2;
                h2Var.getClass();
                int i3 = omniboxPedal.a;
                if ((i3 >= 10000 && i3 < 10002) && i3 == 10001) {
                    oj2.U(i2, 30, "Omnibox.SuggestionUsed.ResumeJourney");
                }
                ((c) ((d) h2Var.c).g).k.s(null, 12, false);
                f2 f2Var = (f2) h2Var.b;
                f2Var.getClass();
                int i4 = omniboxPedal.a;
                if (i4 >= 10000 && i4 < 10002) {
                    if (i3 == 10001 && f2Var.b != null) {
                        throw null;
                    }
                    return;
                }
                n3 n3Var = new n3();
                Activity activity = f2Var.a;
                if (i3 == 1) {
                    n3Var.b(activity, ClearBrowsingDataTabsFragment.class, null);
                } else if (i3 == 2) {
                    qx1.b(activity, 0, new n3(), false);
                } else if (i3 == 3) {
                    n3Var.b(activity, AutofillPaymentMethodsFragment.class, null);
                } else if (i3 != 4) {
                    if (i3 == 7) {
                        int i5 = SafetyCheckSettingsFragment.k0;
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("SafetyCheckSettingsFragment.safetyCheckImmediateRun", true);
                        n3Var.b(activity, SafetyCheckSettingsFragment.class, bundle);
                    } else if (i3 == 12) {
                        n3Var.b(activity, SiteSettings.class, null);
                    } else if (i3 != 27) {
                        if (i3 == 31) {
                            n3Var.b(activity, null, null);
                        } else if (i3 != 33) {
                            if (i3 == 35) {
                                n3Var.b(activity, AccessibilitySettings.class, null);
                            }
                        } else if (activity instanceof ChromeActivity) {
                            ((ChromeActivity) activity).h1(R$id.open_history_menu_id, false);
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(activity.getApplicationContext(), HistoryActivity.class);
                            intent.putExtra("org.chromium.chrome.browser.incognito_mode", false);
                            q11.a(intent);
                            activity.startActivity(intent);
                        }
                    } else if (activity instanceof ChromeActivity) {
                        ((ChromeActivity) activity).v2().b(new LoadUrlParams("chrome://dino", 5));
                    } else {
                        Context applicationContext = activity.getApplicationContext();
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("chrome://dino/"));
                        intent2.setComponent(new ComponentName(applicationContext, (Class<?>) ChromeLauncherActivity.class));
                        intent2.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
                        intent2.setFlags(268959744);
                        q11.a(intent2);
                        activity.startActivity(intent2);
                    }
                } else if (activity instanceof ChromeActivity) {
                    ((ChromeActivity) activity).h1(R$id.new_incognito_tab_menu_id, false);
                } else {
                    Intent intentB = b.b(activity.getApplicationContext(), true);
                    q11.a(intentB);
                    activity.startActivity(intentB);
                }
                oj2.U(i3, 41, "Omnibox.SuggestionUsed.Pedal");
                return;
            default:
                h.b bVar = (h.b) obj3;
                Drawable drawable = (Drawable) obj;
                bVar.getClass();
                ((AtomicReference) obj2).set(drawable);
                bVar.a(i2, drawable);
                return;
        }
    }
}
