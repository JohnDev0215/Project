package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.ArraySet;
import androidx.preference.Preference;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.AppDomainsPreference;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public class InstantAppDomainsPreferenceController extends AppInfoPreferenceControllerBase {
    private PackageManager mPackageManager;

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

    public InstantAppDomainsPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = this.mContext.getPackageManager();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return AppUtils.isInstant(this.mParent.getPackageInfo().applicationInfo) ? 0 : 4;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        AppDomainsPreference appDomainsPreference = (AppDomainsPreference) preference;
        ArraySet<String> handledDomains = Utils.getHandledDomains(this.mPackageManager, this.mParent.getPackageInfo().packageName);
        String[] strArr = (String[]) handledDomains.toArray(new String[handledDomains.size()]);
        appDomainsPreference.setTitles(strArr);
        appDomainsPreference.setValues(new int[strArr.length]);
    }
}
