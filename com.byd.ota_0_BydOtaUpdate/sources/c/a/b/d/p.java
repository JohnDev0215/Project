package c.a.b.d;

import android.content.Context;
import android.provider.Settings;

/* JADX INFO: compiled from: UserAgreeUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2353a = "p";

    public static boolean a(Context context) {
        try {
            int i2 = Settings.Global.getInt(context.getContentResolver(), "device_provisioned");
            int i3 = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete");
            e.c(f2353a, "DEVICE_PROVISIONED = " + i2 + ", USER_SETUP_COMPLETE = " + i3);
            return i3 == 1;
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
