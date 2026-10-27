package com.android.car.developeroptions.datausage;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.NetworkTemplate;
import android.text.format.Formatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.net.DataUsageController;

/* JADX INFO: loaded from: classes.dex */
public class WifiDataUsageSummaryPreferenceController extends DataUsageSummaryPreferenceController {
    final String mNetworkId;

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public WifiDataUsageSummaryPreferenceController(Activity activity, Lifecycle lifecycle, PreferenceFragmentCompat preferenceFragmentCompat, CharSequence charSequence) {
        super(activity, lifecycle, preferenceFragmentCompat, -1);
        if (charSequence == null) {
            this.mNetworkId = null;
        } else {
            this.mNetworkId = String.valueOf(charSequence);
        }
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference == null) {
            return;
        }
        DataUsageSummaryPreference dataUsageSummaryPreference = (DataUsageSummaryPreference) preference;
        NetworkTemplate networkTemplateBuildTemplateWifi = NetworkTemplate.buildTemplateWifi(this.mNetworkId);
        DataUsageController.DataUsageInfo dataUsageInfo = this.mDataUsageController.getDataUsageInfo(networkTemplateBuildTemplateWifi);
        this.mDataInfoController.updateDataLimit(dataUsageInfo, this.mPolicyEditor.getPolicy(networkTemplateBuildTemplateWifi));
        dataUsageSummaryPreference.setWifiMode(true, dataUsageInfo.period, true);
        dataUsageSummaryPreference.setChartEnabled(true);
        long j = dataUsageInfo.usageLevel;
        dataUsageSummaryPreference.setUsageNumbers(j, j, false);
        dataUsageSummaryPreference.setProgress(100.0f);
        dataUsageSummaryPreference.setLabels(Formatter.formatFileSize(this.mContext, 0L), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.usageLevel));
    }
}
