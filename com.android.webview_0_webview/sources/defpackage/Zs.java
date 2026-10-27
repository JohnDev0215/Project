package defpackage;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.TransactionTooLargeException;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Zs {
    public static void a(RuntimeException runtimeException, Intent intent) {
        if (!(runtimeException instanceof NullPointerException) && !(runtimeException.getCause() instanceof TransactionTooLargeException)) {
            throw runtimeException;
        }
        StringBuilder sbF = Z7.f("Could not resolve Activity for intent ");
        sbF.append(intent.toString());
        Ts.a("PackageManagerUtils", sbF.toString(), runtimeException);
    }

    public static List b(Intent intent, int i) {
        try {
            C0683dt c0683dtX = C0683dt.x();
            try {
                List<ResolveInfo> listQueryIntentActivities = Is.a.getPackageManager().queryIntentActivities(intent, i);
                c0683dtX.close();
                return listQueryIntentActivities;
            } finally {
            }
        } catch (RuntimeException e) {
            a(e, intent);
            return Collections.emptyList();
        }
    }
}
