package com.android.car.developeroptions.development;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class SystemServerHeapDumpPreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    private Handler mHandler;
    private final UserManager mUserManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "system_server_heap_dump";
    }

    public SystemServerHeapDumpPreferenceController(Context context) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        this.mHandler = new Handler();
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return Build.IS_DEBUGGABLE && !this.mUserManager.hasUserRestriction("no_debugging_features");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(final Preference preference) {
        if (!"system_server_heap_dump".equals(preference.getKey())) {
            return false;
        }
        try {
            preference.setEnabled(false);
            Toast.makeText(this.mContext, R.string.capturing_system_heap_dump_message, 0).show();
            ActivityManager.getService().requestSystemServerHeapDump();
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.developeroptions.development.-$$Lambda$SystemServerHeapDumpPreferenceController$uGKSx5pqOYoV7jff-wZs7DiJRSI
                @Override // java.lang.Runnable
                public final void run() {
                    preference.setEnabled(true);
                }
            }, 5000L);
            return true;
        } catch (RemoteException e) {
            Log.e("PrefControllerMixin", "error taking system heap dump", e);
            Toast.makeText(this.mContext, R.string.error_capturing_system_heap_dump_message, 0).show();
            return false;
        }
    }
}
