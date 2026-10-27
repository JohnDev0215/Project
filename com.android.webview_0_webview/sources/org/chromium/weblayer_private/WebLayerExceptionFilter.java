package org.chromium.weblayer_private;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class WebLayerExceptionFilter {
    public static boolean stackTraceContainsWebLayerCode(Throwable th) {
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (stackTraceElement.getClassName().startsWith("org.chromium.")) {
                return true;
            }
            if (stackTraceElement.getFileName() != null && stackTraceElement.getFileName().startsWith("chromium-")) {
                return true;
            }
        }
        return false;
    }
}
