package b.a.b.d;

import android.content.Context;
import android.os.SystemProperties;

/* JADX INFO: compiled from: VehicleConfigUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f180a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f181b = -1;

    public static boolean a(Context context) {
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        f180a = i > 0;
        b.a.d.b.a.d("VehicleConfigUtils", "---isRightRudder---value: " + i + ", isRightRudder: " + f180a);
        return i > 0;
    }
}
