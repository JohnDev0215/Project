package defpackage;

/* JADX INFO: renamed from: Ho, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0140Ho {
    public static Boolean a;

    public static boolean a(boolean z) {
        boolean z2 = true;
        if (z) {
            return true;
        }
        Boolean bool = a;
        if (bool != null) {
            return bool.booleanValue();
        }
        if ("com.google.android.gms".equals(Is.a.getPackageName())) {
            if (Is.a.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode < 15000000) {
                z2 = false;
            }
        }
        Boolean boolValueOf = Boolean.valueOf(z2);
        a = boolValueOf;
        return boolValueOf.booleanValue();
    }
}
