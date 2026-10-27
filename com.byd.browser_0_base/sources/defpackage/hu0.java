package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import defpackage.cq;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.feedback.ScreenshotTask;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: HelpAndFeedbackLauncherImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class hu0 implements fu0 {
    public static hu0 a;

    public static fu0 a() {
        Object obj = ThreadUtils.a;
        if (a == null) {
            AppHooks.get().getClass();
            a = new hu0();
        }
        return a;
    }

    public static void b(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://support.google.com/chrome/topic/6069782"));
        intent.putExtra("com.android.browser.application_id", activity.getPackageName());
        intent.putExtra("create_new_tab", true);
        intent.setPackage(activity.getPackageName());
        activity.startActivity(intent);
    }

    public final void c(Activity activity, String str, String str2, Profile profile) {
        n3.X0("MobileHelpAndFeedback");
        new cq(activity, new ScreenshotTask(activity), new cq.a(str2, str, profile), new yo1(this, 3, activity, str), profile);
    }
}
