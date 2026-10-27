package com.android.car.developeroptions.deviceinfo.firmwareversion;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class MainlineModuleVersionPreferenceController extends BasePreferenceController {
    static final Intent MODULE_UPDATE_INTENT = new Intent("android.settings.MODULE_UPDATE_SETTINGS");
    private static final String TAG = "MainlineModuleControl";
    private String mModuleVersion;
    private final PackageManager mPackageManager;

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

    public MainlineModuleVersionPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = this.mContext.getPackageManager();
        initModules();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return !TextUtils.isEmpty(this.mModuleVersion) ? 0 : 3;
    }

    private void initModules() {
        String string = this.mContext.getString(R.string.concurrent_display_notification_name);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            this.mModuleVersion = this.mPackageManager.getPackageInfo(string, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to get mainline version.", e);
            this.mModuleVersion = null;
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mPackageManager.resolveActivity(MODULE_UPDATE_INTENT, 0) != null) {
            preference.setIntent(MODULE_UPDATE_INTENT);
        } else {
            preference.setIntent(null);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mModuleVersion;
    }
}
