package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.IUsbManager;
import android.os.ServiceManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.applications.AppLaunchSettings;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes.dex */
public class AppOpenByDefaultPreferenceController extends AppInfoPreferenceControllerBase {
    private PackageManager mPackageManager;
    private IUsbManager mUsbManager;

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppOpenByDefaultPreferenceController(Context context, String str) {
        super(context, str);
        this.mUsbManager = IUsbManager.Stub.asInterface(ServiceManager.getService("usb"));
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        ApplicationInfo applicationInfo;
        super.displayPreference(preferenceScreen);
        ApplicationsState.AppEntry appEntry = this.mParent.getAppEntry();
        if (appEntry == null || (applicationInfo = appEntry.info) == null) {
            this.mPreference.setEnabled(false);
        } else if ((applicationInfo.flags & 8388608) == 0 || !applicationInfo.enabled) {
            this.mPreference.setEnabled(false);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        PackageInfo packageInfo = this.mParent.getPackageInfo();
        if (packageInfo != null && !AppUtils.isInstant(packageInfo.applicationInfo)) {
            preference.setVisible(true);
            preference.setSummary(AppUtils.getLaunchByDefaultSummary(this.mParent.getAppEntry(), this.mUsbManager, this.mPackageManager, this.mContext));
        } else {
            preference.setVisible(false);
        }
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase
    protected Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return AppLaunchSettings.class;
    }
}
