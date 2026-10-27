package org.chromium.base;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface Callback {

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public abstract class Helper {
        public static void onBooleanResultFromNative(Callback callback, boolean z) {
            callback.a(Boolean.valueOf(z));
        }

        public static void onIntResultFromNative(Callback callback, int i) {
            callback.a(Integer.valueOf(i));
        }

        public static void onObjectResultFromNative(Callback callback, Object obj) {
            callback.a(obj);
        }

        public static void runRunnable(Runnable runnable) {
            runnable.run();
        }
    }

    void a(Object obj);
}
