package r2;

import android.content.Context;
import android.os.SystemProperties;
import n2.b;

/* JADX INFO: compiled from: VehicleConfigUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f7788a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f7789b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f7790c = -1;

    public static int a() {
        return SystemProperties.getInt("persist.sys.AutoType", 0);
    }

    public static b b(Context context) {
        int i8 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        f7789b = a();
        int iB = a.a.a(context).b();
        f7790c = iB;
        boolean z7 = i8 > 0;
        f7788a = z7;
        b bVar = new b(f7789b, iB, z7);
        x3.a.c("VehicleConfigUtils", "---readVehicleConfig---value: " + i8 + ", config: " + bVar);
        return bVar;
    }
}
