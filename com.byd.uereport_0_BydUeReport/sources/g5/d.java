package g5;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.byd.systemsettings.SettingsCallDispatcher;

/* JADX INFO: compiled from: CarSettingsDialogUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    public static void a(Context context) {
    }

    public static void b(Context context) {
        a.i("finish screen saver .");
        SettingsCallDispatcher.Singleton.get().finishScreensaver();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        if (packageManager.queryIntentServices(intent, 0).isEmpty()) {
            return;
        }
        context.startService(intent);
    }

    public static void c(Context context) {
        a.i("show bluetooth settings dialog.");
        SettingsCallDispatcher.Singleton.get().showBtSettings();
    }

    public static void d(Context context) {
        a.i("show devices list dialog.");
        SettingsCallDispatcher.Singleton.get().showCpaaSettings();
    }
}
