package com.android.car.developeroptions.applications;

import android.app.usage.UsageStats;
import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.RecentAppStatsMixin;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AllAppsInfoPreferenceController extends BasePreferenceController implements RecentAppStatsMixin.RecentAppStatsListener {
    Preference mPreference;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
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

    public AllAppsInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = preferenceFindPreference;
        preferenceFindPreference.setVisible(false);
    }

    @Override // com.android.car.developeroptions.applications.RecentAppStatsMixin.RecentAppStatsListener
    public void onReloadDataCompleted(List<UsageStats> list) {
        if (!list.isEmpty()) {
            this.mPreference.setVisible(false);
            return;
        }
        this.mPreference.setVisible(true);
        Context context = this.mContext;
        new InstalledAppCounter(context, -1, context.getPackageManager()) { // from class: com.android.car.developeroptions.applications.AllAppsInfoPreferenceController.1
            @Override // com.android.car.developeroptions.applications.AppCounter
            protected void onCountComplete(int i) {
                AllAppsInfoPreferenceController allAppsInfoPreferenceController = AllAppsInfoPreferenceController.this;
                allAppsInfoPreferenceController.mPreference.setSummary(((AbstractPreferenceController) allAppsInfoPreferenceController).mContext.getString(R.string.apps_summary, Integer.valueOf(i)));
            }
        }.execute(new Void[0]);
    }
}
