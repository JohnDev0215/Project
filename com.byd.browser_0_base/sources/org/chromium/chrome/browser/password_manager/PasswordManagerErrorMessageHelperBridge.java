package org.chromium.chrome.browser.password_manager;

import J.N;
import android.app.Activity;
import defpackage.bq;
import defpackage.oj2;
import defpackage.sk;
import defpackage.xo;
import defpackage.z7;
import java.util.concurrent.TimeUnit;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordManagerErrorMessageHelperBridge {
    public static final long a;
    public static final long b;
    public static final /* synthetic */ int c = 0;

    static {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        a = timeUnit.convert(24L, TimeUnit.HOURS);
        b = timeUnit.convert(30L, TimeUnit.MINUTES);
    }

    @CalledByNative
    public static void saveErrorUiShownTimestamp() {
        oj2.k(Profile.d()).f("profile.upm_error_ui_shown_timestamp", Long.toString(System.currentTimeMillis()));
    }

    @CalledByNative
    public static boolean shouldShowErrorUi() {
        Profile profileD = Profile.d();
        z7.m().getClass();
        if (z7.v(profileD).a(0) == null) {
            return false;
        }
        sk skVar = bq.a;
        if (N.M6bsIDpc("UnifiedPasswordManagerErrorMessages", "ignore_auth_error_message_timeouts", false)) {
            return true;
        }
        long jLongValue = Long.valueOf(oj2.k(Profile.d()).c("profile.upm_error_ui_shown_timestamp")).longValue();
        long jE = SharedPreferencesManager.getInstance().e(0L, "sync_error_infobar_shown_shown_at_time");
        long jCurrentTimeMillis = System.currentTimeMillis();
        return jCurrentTimeMillis - jLongValue > a && jCurrentTimeMillis - jE > b;
    }

    @CalledByNative
    public static void startUpdateAccountCredentialsFlow(WindowAndroid windowAndroid) {
        Profile profileD = Profile.d();
        z7.m().getClass();
        CoreAccountInfo coreAccountInfoA = z7.v(profileD).a(0);
        if (coreAccountInfoA == null) {
            return;
        }
        AccountManagerFacadeProvider.getInstance().f(CoreAccountInfo.a(coreAccountInfoA), (Activity) windowAndroid.k().get(), new xo(4));
    }
}
