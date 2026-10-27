package org.chromium.chrome.browser.payments;

import J.N;
import android.graphics.Bitmap;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceWorkerPaymentAppBridge {

    public interface GetServiceWorkerPaymentAppsInfoCallback {
        void a(Map<String, Pair<String, Bitmap>> map);
    }

    public interface HasServiceWorkerPaymentAppsCallback {
        void a(boolean z);
    }

    public static void a(WebContents webContents, int i) {
        if (webContents == null || webContents.isDestroyed()) {
            return;
        }
        N.MAan0VNK(webContents, i);
    }

    @CalledByNative
    public static void addPaymentAppInfo(Object obj, String str, String str2, Bitmap bitmap) {
        ((Map) obj).put(str, new Pair(str2, bitmap));
    }

    @CalledByNative
    public static Object createPaymentAppsInfo() {
        return new HashMap();
    }

    @CalledByNative
    public static void onGetServiceWorkerPaymentAppsInfo(GetServiceWorkerPaymentAppsInfoCallback getServiceWorkerPaymentAppsInfoCallback, Object obj) {
        Object obj2 = ThreadUtils.a;
        getServiceWorkerPaymentAppsInfoCallback.a((Map) obj);
    }

    @CalledByNative
    public static void onHasServiceWorkerPaymentApps(HasServiceWorkerPaymentAppsCallback hasServiceWorkerPaymentAppsCallback, boolean z) {
        Object obj = ThreadUtils.a;
        hasServiceWorkerPaymentAppsCallback.a(z);
    }
}
