package org.chromium.chrome.browser.supervised_user;

import android.app.Activity;
import defpackage.cq;
import defpackage.n3;
import defpackage.ps;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.feedback.ScreenshotTask;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public final class ChildAccountFeedbackReporter {
    public static n3 a;

    @CalledByNative
    public static void reportFeedbackWithWindow(WindowAndroid windowAndroid, String str, String str2, Profile profile) {
        Activity activity = (Activity) windowAndroid.k().get();
        Object obj = ThreadUtils.a;
        if (a == null) {
            AppHooks.get().getClass();
            a = new n3();
        }
        new cq(activity, new ScreenshotTask(activity), new cq.a(str2, null, profile), new ps(15), profile);
    }
}
