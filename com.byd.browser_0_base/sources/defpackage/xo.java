package defpackage;

import android.graphics.drawable.Animatable;
import android.util.Log;
import android.util.Pair;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.browsing_data.OtherFormsOfHistoryDialogFragment;
import org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter;
import org.chromium.chrome.browser.password_manager.PasswordManagerErrorMessageHelperBridge;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.segmentation_platform.SegmentSelectionResult;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xo implements Callback {
    public final /* synthetic */ int e;

    public /* synthetic */ xo(int i) {
        this.e = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = 2;
        switch (this.e) {
            case 0:
                k11 k11Var = ChromeActivity.Z0;
                break;
            case 1:
                int i2 = OtherFormsOfHistoryDialogFragment.p0;
                new iz2(false).g(2, "https://myactivity.google.com/myactivity/?utm_source=chrome_n");
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                int i3 = PasswordManagerErrorMessageHelperBridge.c;
                oj2.O("PasswordManager.UPMUpdateSignInCredentialsSucces", ((Boolean) obj).booleanValue());
                break;
            case 5:
                ChromePureJavaExceptionReporter.d((Throwable) obj);
                break;
            case 6:
                break;
            case 7:
                SegmentSelectionResult segmentSelectionResult = (SegmentSelectionResult) obj;
                if (!segmentSelectionResult.a) {
                    i = 0;
                } else if (segmentSelectionResult.b != 6) {
                    i = 1;
                }
                SharedPreferencesManager.getInstance().m(i, "Chrome.StartSurface.ShowSegmentationResult");
                break;
            case 8:
                oj2.U(((Integer) ((Pair) obj).second).intValue(), 8, "SegmentationPlatform.AdaptiveToolbar.SegmentSelected.Startup");
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                Log.e("cr_UsageStatsService", "Failed to clear all events for history deletion");
                break;
            default:
                int i4 = v9.g;
                ((Animatable) obj).start();
                break;
        }
    }
}
