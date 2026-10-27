package defpackage;

import android.content.Intent;
import android.os.TransactionTooLargeException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Os {
    public static void a(RuntimeException runtimeException, Intent intent) {
        if (!(runtimeException.getCause() instanceof TransactionTooLargeException)) {
            throw runtimeException;
        }
        StringBuilder sbF = Z7.f("Could not resolve Activity for intent ");
        sbF.append(intent.toString());
        Ts.a("IntentUtils", sbF.toString(), runtimeException);
    }

    public static String b(Intent intent, String str) {
        try {
            return intent.getStringExtra(str);
        } catch (Throwable unused) {
            Ts.a("IntentUtils", "getStringExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }
}
