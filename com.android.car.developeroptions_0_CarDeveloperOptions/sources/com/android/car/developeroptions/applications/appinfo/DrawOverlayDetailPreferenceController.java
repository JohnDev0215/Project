package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.UserManager;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class DrawOverlayDetailPreferenceController extends AppInfoPreferenceControllerBase {
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

    public DrawOverlayDetailPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        PackageInfo packageInfo;
        if (!UserManager.get(this.mContext).isManagedProfile() && (packageInfo = this.mParent.getPackageInfo()) != null && packageInfo.requestedPermissions != null) {
            int i = 0;
            while (true) {
                String[] strArr = packageInfo.requestedPermissions;
                if (i >= strArr.length) {
                    break;
                }
                if (strArr[i].equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                    return 0;
                }
                i++;
            }
        }
        return 4;
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase
    protected Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return DrawOverlayDetails.class;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return DrawOverlayDetails.getSummary(this.mContext, this.mParent.getAppEntry());
    }
}
