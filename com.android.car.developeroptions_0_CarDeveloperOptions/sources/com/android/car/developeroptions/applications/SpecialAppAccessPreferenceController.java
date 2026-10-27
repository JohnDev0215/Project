package com.android.car.developeroptions.applications;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.AppStateBaseBridge;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.datausage.AppStateDataUsageBridge;
import com.android.car.developeroptions.datausage.DataSaverBackend;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SpecialAppAccessPreferenceController extends BasePreferenceController implements AppStateBaseBridge.Callback, ApplicationsState.Callbacks, LifecycleObserver, OnStart, OnStop, OnDestroy {
    private final ApplicationsState mApplicationsState;
    private final DataSaverBackend mDataSaverBackend;
    private final AppStateDataUsageBridge mDataUsageBridge;
    private boolean mExtraLoaded;
    private Preference mPreference;
    ApplicationsState.Session mSession;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
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

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onAllSizesComputed() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLauncherInfoChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLoadEntriesCompleted() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageIconChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageListChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageSizeChanged(String str) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    /* JADX INFO: renamed from: onRebuildComplete */
    public void lambda$rebuild$2(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onRunningStateChanged(boolean z) {
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SpecialAppAccessPreferenceController(Context context, String str) {
        super(context, str);
        this.mApplicationsState = ApplicationsState.getInstance((Application) context.getApplicationContext());
        DataSaverBackend dataSaverBackend = new DataSaverBackend(context);
        this.mDataSaverBackend = dataSaverBackend;
        this.mDataUsageBridge = new AppStateDataUsageBridge(this.mApplicationsState, this, dataSaverBackend);
    }

    public void setSession(Lifecycle lifecycle) {
        this.mSession = this.mApplicationsState.newSession(this, lifecycle);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mDataUsageBridge.resume();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mDataUsageBridge.pause();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnDestroy
    public void onDestroy() {
        this.mDataUsageBridge.release();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updateSummary();
    }

    @Override // com.android.car.developeroptions.applications.AppStateBaseBridge.Callback
    public void onExtraInfoUpdated() {
        this.mExtraLoaded = true;
        updateSummary();
    }

    private void updateSummary() {
        if (!this.mExtraLoaded || this.mPreference == null) {
            return;
        }
        int i = 0;
        for (ApplicationsState.AppEntry appEntry : this.mSession.getAllApps()) {
            if (ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER.filterApp(appEntry)) {
                Object obj = appEntry.extraInfo;
                if ((obj instanceof AppStateDataUsageBridge.DataUsageState) && ((AppStateDataUsageBridge.DataUsageState) obj).isDataSaverWhitelisted) {
                    i++;
                }
            }
        }
        this.mPreference.setSummary(this.mContext.getResources().getQuantityString(R.plurals.special_access_summary, i, Integer.valueOf(i)));
    }
}
