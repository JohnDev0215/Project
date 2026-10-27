package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Hz extends BroadcastReceiver {
    public final /* synthetic */ TracingControllerAndroidImpl a;

    public Hz(TracingControllerAndroidImpl tracingControllerAndroidImpl) {
        this.a = tracingControllerAndroidImpl;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().endsWith("GPU_PROFILER_START")) {
            String stringExtra = intent.getStringExtra("categories");
            String strMy9pNx9O = TextUtils.isEmpty(stringExtra) ? N.My9pNx9O(this.a) : stringExtra.replaceFirst("_DEFAULT_CHROME_CATEGORIES", N.My9pNx9O(this.a));
            String str = intent.getStringExtra("continuous") == null ? "record-until-full" : "record-continuously";
            String stringExtra2 = intent.getStringExtra("file");
            if (stringExtra2 != null) {
                this.a.b(stringExtra2, true, strMy9pNx9O, str, false);
                return;
            } else {
                this.a.b(null, true, strMy9pNx9O, str, false);
                return;
            }
        }
        if (intent.getAction().endsWith("GPU_PROFILER_STOP")) {
            TracingControllerAndroidImpl tracingControllerAndroidImpl = this.a;
            if (tracingControllerAndroidImpl.d) {
                N.M$HKWu8q(tracingControllerAndroidImpl.h, tracingControllerAndroidImpl, tracingControllerAndroidImpl.f, tracingControllerAndroidImpl.g, null);
                return;
            }
            return;
        }
        if (!intent.getAction().endsWith("GPU_PROFILER_LIST_CATEGORIES")) {
            Ts.a("TracingController", "Unexpected intent: %s", intent);
            return;
        }
        TracingControllerAndroidImpl tracingControllerAndroidImpl2 = this.a;
        if (tracingControllerAndroidImpl2.h == 0) {
            tracingControllerAndroidImpl2.h = N.MWlLnA$6(tracingControllerAndroidImpl2);
        }
        if (N.MdRNuqnW(tracingControllerAndroidImpl2.h, tracingControllerAndroidImpl2, null)) {
            return;
        }
        Ts.a("TracingController", "Unable to fetch tracing category list.", new Object[0]);
    }
}
