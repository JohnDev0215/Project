package b.b.a.i;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.SystemProperties;
import android.text.TextUtils;
import b.b.a.d.b;
import java.util.Locale;

/* JADX INFO: compiled from: VehicleConfigUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f531a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f532b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f533c = -1;

    public static int a() {
        return -1;
    }

    public static int b() {
        return BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).getAutoType();
    }

    public static boolean c(Context context) {
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        f531a = i > 0;
        b.b.m.c.a.d("VehicleConfigUtils", "---isRightRudder---value: " + i + ", isRightRudder: " + f531a);
        return i > 0;
    }

    public static boolean d() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    public static boolean e() {
        int iB = b();
        return iB == 208 || iB == 182;
    }

    public static boolean f() {
        int iB = b();
        boolean zC = c(b.b.a.a.a.b().a());
        if (iB == 60 || iB == 123 || iB == 143) {
            return !zC;
        }
        if (iB == 182 || iB == 196 || iB == 208) {
            return zC;
        }
        return false;
    }

    public static b g(Context context) {
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        f532b = SystemProperties.getInt("persist.sys.AutoType", 0);
        int iB = a.a.a.a(context).b();
        f533c = iB;
        boolean z = i > 0;
        f531a = z;
        b bVar = new b(f532b, iB, z);
        b.b.m.c.a.d("VehicleConfigUtils", "---readVehicleConfig---value: " + i + ", config: " + bVar);
        return bVar;
    }
}
