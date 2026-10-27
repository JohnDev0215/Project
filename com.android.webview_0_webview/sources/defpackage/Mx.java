package defpackage;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Mx {
    public static Mx a;

    public static Mx a() {
        ThreadUtils.b();
        if (a == null) {
            a = new Mx();
        }
        return a;
    }

    public boolean b() {
        return c("android.permission.ACCESS_COARSE_LOCATION") || c("android.permission.ACCESS_FINE_LOCATION");
    }

    public final boolean c(String str) {
        return AbstractC1623us.a(Is.a, str, Process.myPid(), Process.myUid()) == 0;
    }

    public boolean d() {
        Context context = Is.a;
        if (Build.VERSION.SDK_INT < 28) {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager != null && C1405qt.c(locationManager);
    }
}
