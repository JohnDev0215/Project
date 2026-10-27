package i1;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import com.byd.autoservice.application.AutoApplication;

/* JADX INFO: compiled from: AutoSettings.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a(Context context, int i3) {
        if (i3 != 123 && i3 != 180) {
            return Settings.Global.getInt(context.getContentResolver(), "byd_userdefind_value", 0);
        }
        int i4 = Settings.Global.getInt(context.getContentResolver(), "byd_userdefind_value_7b", 0);
        if (i4 == 0) {
            return 4;
        }
        return i4;
    }

    public static int b(Context context, int i3) {
        if (i3 != 123 && i3 != 180) {
            return 0;
        }
        int i4 = Settings.Global.getInt(context.getContentResolver(), "byd_userdefind_value_mc_b6", 0);
        if (i4 == 0) {
            return 5;
        }
        return i4;
    }

    public static boolean c() {
        ContentResolver contentResolver = AutoApplication.a().getContentResolver();
        return (Settings.Global.getInt(contentResolver, "device_provisioned", 0) == 0 || Settings.Secure.getInt(contentResolver, "user_setup_complete", 0) == 0) ? false : true;
    }

    public static void d(Context context, int i3, int i4) {
        if (i3 == 123 || i3 == 180) {
            Settings.Global.putInt(context.getContentResolver(), "byd_userdefind_value_7b", i4);
        } else {
            Settings.Global.putInt(context.getContentResolver(), "byd_userdefind_value", i4);
        }
    }

    public static void e(Context context, int i3, int i4) {
        if (i3 == 123 || i3 == 180) {
            Settings.Global.putInt(context.getContentResolver(), "byd_userdefind_value_mc_b6", i4);
        }
    }
}
