package defpackage;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.util.Log;
import defpackage.v9;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.supervised_user.ChildAccountFeedbackReporter;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.components.segmentation_platform.SegmentSelectionResult;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ps implements Callback {
    public final /* synthetic */ int e;

    public /* synthetic */ ps(int i) {
        this.e = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = 2;
        boolean zM6bsIDpc = false;
        switch (this.e) {
            case 0:
                Profile profile = (Profile) obj;
                n3 n3Var = ChromeTabbedActivity.S1;
                if (profile != null) {
                    oj2.t(profile).notifyEvent("started_from_main_intent");
                    break;
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                EnterpriseInfo.a aVar = (EnterpriseInfo.a) obj;
                if (aVar != null) {
                    N.M2UZBg4a(aVar.a, aVar.b);
                } else {
                    N.M2UZBg4a(false, false);
                }
                break;
            case 6:
                SegmentSelectionResult segmentSelectionResult = (SegmentSelectionResult) obj;
                if (!segmentSelectionResult.a) {
                    i = 0;
                } else if (segmentSelectionResult.b == 9) {
                    i = 1;
                }
                SharedPreferencesManager.getInstance().m(i, "Chrome.Segmentation.FeedActiveUser");
                break;
            case 7:
                break;
            case 8:
                Log.e("cr_PriceDropNotif", "Failed to remove subscriptions.");
                break;
            case 9:
                break;
            case 10:
                SegmentSelectionResult segmentSelectionResult2 = (SegmentSelectionResult) obj;
                if (!segmentSelectionResult2.a) {
                    i = 0;
                } else if (segmentSelectionResult2.b != 7) {
                    i = 1;
                }
                SharedPreferencesManager.getInstance().m(i, "Chrome.Segmentation.ShowQueryTiles");
                break;
            case 11:
                Intent intentA = b.a(((Tab) obj).getId(), 2);
                intentA.addFlags(268435456);
                try {
                    xz.a.startActivity(intentA, null);
                } catch (ActivityNotFoundException unused) {
                    return;
                }
                break;
            case 12:
                SettingsActivity settingsActivity = SettingsActivity.P;
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                ChildAccountFeedbackReporter.a.getClass();
                break;
            case 16:
                xl2 xl2Var = (xl2) obj;
                c13 c13Var = j0.I;
                if (xl2Var != null && xl2Var.q() != null) {
                    if (a8.P()) {
                        sk skVar = bq.a;
                        zM6bsIDpc = N.M6bsIDpc("CommercePriceTracking", "check_if_price_drop_is_seen", false);
                    }
                    if (zM6bsIDpc) {
                        xl2Var.o.f = true;
                        xl2Var.l();
                    }
                    break;
                }
                break;
            case 17:
                Intent intentA2 = b.a(((Tab) obj).getId(), 2);
                intentA2.addFlags(268435456);
                try {
                    xz.a.startActivity(intentA2, null);
                } catch (ActivityNotFoundException unused2) {
                    return;
                }
                break;
            case 18:
                ChromePureJavaExceptionReporter.d((Throwable) obj);
                break;
            case 19:
                ChromePureJavaExceptionReporter.d((Throwable) obj);
                break;
            case 20:
                break;
            case 21:
                Log.e("cr_UsageStatsService", "Failed to clear range of events for history deletion");
                break;
            default:
                Animatable animatable = (Animatable) obj;
                int i2 = v9.g;
                if (animatable instanceof l5) {
                    ((l5) animatable).a(v9.d.a);
                } else if (animatable instanceof Animatable2) {
                    ((Animatable2) animatable).registerAnimationCallback(v9.c.a);
                }
                break;
        }
    }
}
