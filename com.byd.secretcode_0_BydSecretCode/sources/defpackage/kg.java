package defpackage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import com.byd.secretcode.R;

/* JADX INFO: compiled from: AdbUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class kg {
    public static void a(Activity activity, Context context) {
        try {
            int i = 0;
            int i2 = Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0);
            Log.i("switchAdb", "adbEnable = " + i2);
            ContentResolver contentResolver = context.getContentResolver();
            if (i2 != 1) {
                i = 1;
            }
            Settings.Global.putInt(contentResolver, "adb_enabled", i);
            mg.a(activity, i2 == 1 ? context.getString(R.string.adb_text_1) : context.getString(R.string.adb_text_2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(Activity activity, Context context) {
        try {
            String str = SystemProperties.get("persist.sv.debug.adb_enable");
            Log.i("switchAdb", "adbEnable = " + str);
            if ("0".equals(str)) {
                SystemProperties.set("persist.sv.debug.adb_enable", "1");
            } else if ("1".equals(str)) {
                SystemProperties.set("persist.sv.debug.adb_enable", "0");
            }
            mg.a(activity, "1".equals(str) ? context.getString(R.string.adb_text_3) : context.getString(R.string.adb_text_2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
