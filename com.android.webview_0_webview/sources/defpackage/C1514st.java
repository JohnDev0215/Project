package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.telephony.TelephonyManager;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: st, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1514st {
    public static boolean a(Context context, Intent intent, int i, String str, Executor executor, ServiceConnection serviceConnection) {
        return context.bindIsolatedService(intent, i, str, executor, serviceConnection);
    }

    public static void b(TelephonyManager telephonyManager, Callback callback) {
        telephonyManager.requestCellInfoUpdate(Su.f, new C1459rt(callback));
    }

    public static void c(Context context, ServiceConnection serviceConnection, int i, int i2) {
        context.updateServiceGroup(serviceConnection, i, i2);
    }
}
