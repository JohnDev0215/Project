package defpackage;

/* JADX INFO: renamed from: uD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1590uD {
    public static String a(String[] strArr, String str) {
        if (strArr == null) {
            return null;
        }
        String strD = Z7.d("--", str, "=");
        for (String str2 : strArr) {
            if (str2 != null && str2.startsWith(strD)) {
                return str2.substring(strD.length());
            }
        }
        return null;
    }
}
