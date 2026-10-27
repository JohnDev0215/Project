package c.a.d.h;

import android.content.Context;
import android.os.SystemProperties;
import com.byd.ota.R;

/* JADX INFO: compiled from: UiStyleUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2504a = "e";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f2505b = false;

    public static void a() {
        int i2 = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", -1);
        c.a.b.d.e.b(f2504a, "isRightRudder steer = " + i2);
        if (1 == i2) {
            f2505b = true;
        } else {
            f2505b = false;
        }
    }

    public static boolean b() {
        return f2505b;
    }

    public static void c(Context context) {
        String str;
        int iB = a.a.a.a(context).b();
        if (iB == 0) {
            context.setTheme(R.style.DynastyTheme);
            str = "STYLE_DYNASTY";
        } else if (iB == 1) {
            context.setTheme(R.style.DenzaTheme);
            str = "STYLE_DENZA";
        } else if (iB != 2) {
            context.setTheme(R.style.DynastyTheme);
            str = "STYLE_UNDEFINED";
        } else {
            context.setTheme(R.style.DynastyTheme);
            str = "STYLE_OCEAN";
        }
        c.a.b.d.e.c(f2504a, "uiStyle = " + str);
    }
}
