package org.chromium.chrome.browser.signin;

import android.content.Context;
import defpackage.a8;
import defpackage.oj2;
import defpackage.z7;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public final class SigninBridge {
    @CalledByNative
    public static void launchSigninActivity(WindowAndroid windowAndroid, int i) {
        Context context = windowAndroid.i.get();
        if (context != null) {
            a8.y().getClass();
            a8.R(context, i);
        }
    }

    @CalledByNative
    public static void openAccountPickerBottomSheet(WindowAndroid windowAndroid, String str) {
        Object obj = ThreadUtils.a;
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        z7.D(profileD).j();
        oj2.K(7);
    }

    @CalledByNative
    public static void openAccountManagementScreen(WindowAndroid windowAndroid, int i) {
    }
}
