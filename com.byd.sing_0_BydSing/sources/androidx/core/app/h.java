package androidx.core.app;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: NotificationManagerCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f1387a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Set<String> f1388b = new HashSet();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f1389c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f1390d;
    private final NotificationManager e;

    private h(Context context) {
        this.f1390d = context;
        this.e = (NotificationManager) context.getSystemService("notification");
    }

    public static h b(Context context) {
        return new h(context);
    }

    public boolean a() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return this.e.areNotificationsEnabled();
        }
        if (i < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.f1390d.getSystemService("appops");
        ApplicationInfo applicationInfo = this.f1390d.getApplicationInfo();
        String packageName = this.f1390d.getApplicationContext().getPackageName();
        int i2 = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            Class<?> cls2 = Integer.TYPE;
            return ((Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i2), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }
}
