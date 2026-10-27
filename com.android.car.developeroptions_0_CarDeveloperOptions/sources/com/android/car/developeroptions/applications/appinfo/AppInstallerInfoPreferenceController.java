package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.AppStoreUtil;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public class AppInstallerInfoPreferenceController extends AppInfoPreferenceControllerBase {
    private CharSequence mInstallerLabel;
    private String mInstallerPackage;
    private String mPackageName;

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

    public AppInstallerInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (UserManager.get(this.mContext).isManagedProfile() || this.mInstallerLabel == null) ? 4 : 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(this.mContext.getString(AppUtils.isInstant(this.mParent.getPackageInfo().applicationInfo) ? R.string.instant_app_details_summary : R.string.app_install_details_summary, this.mInstallerLabel));
        Intent appStoreLink = AppStoreUtil.getAppStoreLink(this.mContext, this.mInstallerPackage, this.mPackageName);
        if (appStoreLink != null) {
            preference.setIntent(appStoreLink);
        } else {
            preference.setEnabled(false);
        }
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        String installerPackageName = AppStoreUtil.getInstallerPackageName(this.mContext, str);
        this.mInstallerPackage = installerPackageName;
        this.mInstallerLabel = Utils.getApplicationLabel(this.mContext, installerPackageName);
    }
}
