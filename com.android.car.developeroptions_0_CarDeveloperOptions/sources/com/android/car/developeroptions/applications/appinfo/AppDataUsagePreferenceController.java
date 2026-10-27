package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.datausage.AppDataUsage;
import com.android.car.developeroptions.datausage.DataUsageUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.AppItem;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.net.NetworkCycleDataForUid;
import com.android.settingslib.net.NetworkCycleDataForUidLoader;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppDataUsagePreferenceController extends AppInfoPreferenceControllerBase implements LoaderManager.LoaderCallbacks<List<NetworkCycleDataForUid>>, LifecycleObserver, OnResume, OnPause {
    private List<NetworkCycleDataForUid> mAppUsageData;

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

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<List<NetworkCycleDataForUid>> loader) {
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppDataUsagePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return isBandwidthControlEnabled() ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase, com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(getDataSummary());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        if (isAvailable()) {
            int i = this.mParent.getAppEntry().info.uid;
            new AppItem(i).addUid(i);
            this.mParent.getLoaderManager().restartLoader(2, null, this);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        if (isAvailable()) {
            this.mParent.getLoaderManager().destroyLoader(2);
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.loader.content.Loader<java.util.List<com.android.settingslib.net.NetworkCycleDataForUid>>, com.android.settingslib.net.NetworkCycleDataLoader] */
    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<List<NetworkCycleDataForUid>> onCreateLoader(int i, Bundle bundle) {
        NetworkTemplate template = getTemplate(this.mContext);
        NetworkCycleDataForUidLoader.Builder<?> builder = NetworkCycleDataForUidLoader.builder(this.mContext);
        builder.addUid(this.mParent.getAppEntry().info.uid);
        builder.setRetrieveDetail(false);
        builder.setNetworkTemplate(template);
        return builder.build();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<List<NetworkCycleDataForUid>> loader, List<NetworkCycleDataForUid> list) {
        this.mAppUsageData = list;
        updateState(this.mPreference);
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoPreferenceControllerBase
    protected Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return AppDataUsage.class;
    }

    private CharSequence getDataSummary() {
        if (this.mAppUsageData != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long totalUsage = 0;
            for (NetworkCycleDataForUid networkCycleDataForUid : this.mAppUsageData) {
                totalUsage += networkCycleDataForUid.getTotalUsage();
                long startTime = networkCycleDataForUid.getStartTime();
                if (startTime < jCurrentTimeMillis) {
                    jCurrentTimeMillis = startTime;
                }
            }
            if (totalUsage == 0) {
                return this.mContext.getString(R.string.no_data_usage);
            }
            Context context = this.mContext;
            return context.getString(R.string.data_summary_format, Formatter.formatFileSize(context, totalUsage), DateUtils.formatDateTime(this.mContext, jCurrentTimeMillis, 65552));
        }
        return this.mContext.getString(R.string.computing_size);
    }

    private static NetworkTemplate getTemplate(Context context) {
        if (DataUsageUtils.hasReadyMobileRadio(context)) {
            return NetworkTemplate.buildTemplateMobileWildcard();
        }
        if (DataUsageUtils.hasWifiRadio(context)) {
            return NetworkTemplate.buildTemplateWifiWildcard();
        }
        return NetworkTemplate.buildTemplateEthernet();
    }

    boolean isBandwidthControlEnabled() {
        return Utils.isBandwidthControlEnabled();
    }
}
