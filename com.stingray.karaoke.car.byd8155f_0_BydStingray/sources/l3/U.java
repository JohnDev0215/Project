package l3;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class U {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f18544b = Log.isLoggable("MediaSessionManager", 3);
    public static final Object c = new Object();
    public static volatile U d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public S f18545a;

    public static U a(Context context) {
        U u4;
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        synchronized (c) {
            try {
                if (d == null) {
                    Context applicationContext = context.getApplicationContext();
                    U u10 = new U();
                    S s6 = new S();
                    s6.f18541a = applicationContext;
                    s6.f18542b = applicationContext.getContentResolver();
                    s6.f18541a = applicationContext;
                    u10.f18545a = s6;
                    d = u10;
                }
                u4 = d;
            } catch (Throwable th) {
                throw th;
            }
        }
        return u4;
    }

    public final boolean b(T t10) {
        if (t10 == null) {
            throw new IllegalArgumentException("userInfo should not be null");
        }
        S s6 = this.f18545a;
        Q q4 = t10.f18543a;
        if (s6.f18541a.checkPermission("android.permission.MEDIA_CONTENT_CONTROL", q4.f18540b, q4.c) != 0) {
            try {
                if (s6.f18541a.getPackageManager().getApplicationInfo(q4.f18539a, 0) == null) {
                    return false;
                }
                if (!s6.a(q4, "android.permission.STATUS_BAR_SERVICE") && !s6.a(q4, "android.permission.MEDIA_CONTENT_CONTROL") && q4.c != 1000) {
                    String string = Settings.Secure.getString(s6.f18542b, "enabled_notification_listeners");
                    if (string == null) {
                        return false;
                    }
                    for (String str : string.split(":")) {
                        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                        if (componentNameUnflattenFromString == null || !componentNameUnflattenFromString.getPackageName().equals(q4.f18539a)) {
                        }
                    }
                    return false;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                if (!S.c) {
                    return false;
                }
                Log.d("MediaSessionManager", "Package " + q4.f18539a + " doesn't exist");
                return false;
            }
        }
        return true;
    }
}
