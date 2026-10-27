package org.chromium.chrome.browser.feedback;

import J.N;
import android.util.Log;
import defpackage.i9;
import defpackage.rh3;
import defpackage.yl1;
import java.net.MalformedURLException;
import java.net.URL;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feedback.d;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityChecker {
    public static final yl1 a = new yl1("android_feedback_connectivity_checker");

    public interface a {
        void a(int i);
    }

    public static void a(boolean z, d.c cVar) {
        String str = z ? "https://clients4.google.com/generate_204" : "http://clients4.google.com/generate_204";
        if (!N.MGhgrVHC(str)) {
            Log.w("cr_feedback", "Predefined URL invalid.");
            PostTask.c(rh3.a, new org.chromium.chrome.browser.feedback.a(cVar));
            return;
        }
        try {
            new b(new URL(str), cVar).c(i9.e);
        } catch (MalformedURLException e) {
            Log.w("cr_feedback", "Failed to parse predefined URL: " + e);
            PostTask.c(rh3.a, new org.chromium.chrome.browser.feedback.a(cVar));
        }
    }

    @CalledByNative
    public static void executeCallback(Object obj, int i) {
        ((a) obj).a(i);
    }
}
