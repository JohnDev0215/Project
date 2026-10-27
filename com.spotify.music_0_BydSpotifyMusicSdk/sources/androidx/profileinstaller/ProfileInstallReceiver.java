package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import java.io.File;
import p.db;
import p.l90;
import p.ln1;
import p.oh1;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) throws Throwable {
        Bundle extras;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            ux2.B(context, new db(2), new oh1(10, this), true);
            return;
        }
        if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if (!"WRITE_SKIP_FILE".equals(string)) {
                    if ("DELETE_SKIP_FILE".equals(string)) {
                        oh1 oh1Var = new oh1(10, this);
                        new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
                        new l90(11, 1, oh1Var, null).run();
                        return;
                    }
                    return;
                }
                oh1 oh1Var2 = new oh1(10, this);
                try {
                    ux2.s(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
                    new l90(10, 1, oh1Var2, null).run();
                    return;
                } catch (PackageManager.NameNotFoundException e) {
                    new l90(7, 1, oh1Var2, e).run();
                    return;
                }
            }
            return;
        }
        if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            oh1 oh1Var3 = new oh1(10, this);
            Process.sendSignal(Process.myPid(), 10);
            oh1Var3.o(12, null);
        } else {
            if (!"androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) || (extras = intent.getExtras()) == null) {
                return;
            }
            String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
            oh1 oh1Var4 = new oh1(10, this);
            if (!"DROP_SHADER_CACHE".equals(string2)) {
                oh1Var4.o(16, null);
            } else if (ln1.i(context.createDeviceProtectedStorageContext().getCodeCacheDir())) {
                oh1Var4.o(14, null);
            } else {
                oh1Var4.o(15, null);
            }
        }
    }
}
