package com.android.car.developeroptions.system;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemUpdateManager;
import android.os.UserManager;
import android.telephony.CarrierConfigManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class SystemUpdatePreferenceController extends BasePreferenceController {
    private static final String KEY_SYSTEM_UPDATE_SETTINGS = "system_update_settings";
    private static final String TAG = "SysUpdatePrefContr";
    private final UserManager mUm;
    private final SystemUpdateManager mUpdateManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SystemUpdatePreferenceController(Context context) {
        super(context, KEY_SYSTEM_UPDATE_SETTINGS);
        this.mUm = UserManager.get(context);
        this.mUpdateManager = (SystemUpdateManager) context.getSystemService("system_update");
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (this.mContext.getResources().getBoolean(R.bool.config_show_system_update_settings) && this.mUm.isAdminUser()) ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            Utils.updatePreferenceToSpecificActivityOrRemove(this.mContext, preferenceScreen, getPreferenceKey(), 1);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        PersistableBundle config;
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey()) || (config = ((CarrierConfigManager) this.mContext.getSystemService("carrier_config")).getConfig()) == null || !config.getBoolean("ci_action_on_sys_update_bool")) {
            return false;
        }
        ciActionOnSysUpdate(config);
        return false;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        String string = this.mContext.getString(R.string.android_version_summary, Build.VERSION.RELEASE_OR_CODENAME);
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.android.car.developeroptions.system.-$$Lambda$SystemUpdatePreferenceController$flPEW7ayxQqCFKJImukJkL_Ckc4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$getSummary$0$SystemUpdatePreferenceController();
            }
        });
        try {
            futureTask.run();
            Bundle bundle = (Bundle) futureTask.get();
            int i = bundle.getInt("status");
            if (i == 0) {
                Log.d(TAG, "Update statue unknown");
            } else if (i != 1) {
                return (i == 2 || i == 3 || i == 4 || i == 5) ? this.mContext.getText(R.string.android_version_pending_update_summary) : string;
            }
            String string2 = bundle.getString("title");
            return !TextUtils.isEmpty(string2) ? this.mContext.getString(R.string.android_version_summary, string2) : string;
        } catch (InterruptedException | ExecutionException unused) {
            Log.w(TAG, "Error getting system update info.");
            return string;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getSummary$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Bundle lambda$getSummary$0$SystemUpdatePreferenceController() throws Exception {
        return this.mUpdateManager.retrieveSystemUpdateInfo();
    }

    private void ciActionOnSysUpdate(PersistableBundle persistableBundle) {
        String string = persistableBundle.getString("ci_action_on_sys_update_intent_string");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String string2 = persistableBundle.getString("ci_action_on_sys_update_extra_string");
        String string3 = persistableBundle.getString("ci_action_on_sys_update_extra_val_string");
        Intent intent = new Intent(string);
        if (!TextUtils.isEmpty(string2)) {
            intent.putExtra(string2, string3);
        }
        Log.d(TAG, "ciActionOnSysUpdate: broadcasting intent " + string + " with extra " + string2 + ", " + string3);
        this.mContext.getApplicationContext().sendBroadcast(intent);
    }
}
