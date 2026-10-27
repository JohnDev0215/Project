package org.chromium.android_webview.common.crash;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwCrashReporterClient {
    public static boolean stackTraceContainsWebViewCode(Throwable th) {
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (stackTraceElement.getClassName().startsWith("android.webkit.") || stackTraceElement.getClassName().startsWith("com.android.webview.") || stackTraceElement.getClassName().startsWith("org.chromium.")) {
                return true;
            }
            if (stackTraceElement.getFileName() != null && stackTraceElement.getFileName().startsWith("chromium-")) {
                return true;
            }
        }
        return false;
    }
}
