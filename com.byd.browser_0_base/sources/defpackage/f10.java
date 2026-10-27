package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: CrashUploadCountStore.java */
/* JADX INFO: loaded from: classes.dex */
public final class f10 {
    public static final f10 b = new f10();
    public final SharedPreferencesManager a = SharedPreferencesManager.getInstance();

    public static String a(String str) {
        switch (str) {
            case "Renderer":
                return "renderer_crash_failure_upload";
            case "GPU":
                return "gpu_crash_failure_upload";
            case "Other":
                return "other_crash_failure_upload";
            case "Browser":
                return "browser_crash_failure_upload";
            default:
                throw new IllegalArgumentException("Process type unknown: ".concat(str));
        }
    }

    public static String b(String str) {
        str.getClass();
        switch (str) {
            case "Renderer":
                return "renderer_crash_success_upload";
            case "GPU":
                return "gpu_crash_success_upload";
            case "Other":
                return "other_crash_success_upload";
            case "Browser":
                return "browser_crash_success_upload";
            default:
                throw new IllegalArgumentException("Process type unknown: ".concat(str));
        }
    }
}
