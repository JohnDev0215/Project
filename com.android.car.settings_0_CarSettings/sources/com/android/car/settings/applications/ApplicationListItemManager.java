package com.android.car.settings.applications;

import android.os.Handler;
import android.os.storage.VolumeInfo;
import androidx.lifecycle.Lifecycle;
import com.android.car.settings.common.Logger;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationListItemManager implements ApplicationsState.Callbacks {
    private static final String APP_NAME_UNKNOWN = "APP NAME UNKNOWN";
    private static final Logger LOG = new Logger((Class<?>) ApplicationListItemManager.class);
    private Comparator<ApplicationsState.AppEntry> mAppEntryComparator;
    private ApplicationsState.AppFilter mAppFilter;
    private final ApplicationsState mAppState;
    private ArrayList<ApplicationsState.AppEntry> mDeferredAppsToUpload;
    private final Lifecycle mLifecycle;
    private final int mMaxAppLoadWaitInterval;
    private final int mMillisecondUpdateInterval;
    private ApplicationsState.Session mSession;
    private final VolumeInfo mVolumeInfo;
    private final List<AppListItemListener> mAppListItemListeners = new ArrayList();
    private Set<ApplicationsState.AppEntry> mAppsToLoad = new HashSet();
    private ArrayList<ApplicationsState.AppEntry> mLoadedApps = new ArrayList<>();
    private boolean mReadyToRenderUpdates = true;
    private final Handler mHandler = new Handler();

    public interface AppListItemListener {
        void onDataLoaded(ArrayList<ApplicationsState.AppEntry> arrayList);
    }

    public ApplicationListItemManager(VolumeInfo volumeInfo, Lifecycle lifecycle, ApplicationsState appState, int millisecondUpdateInterval, int maxWaitIntervalToFinishLoading) {
        this.mVolumeInfo = volumeInfo;
        this.mLifecycle = lifecycle;
        this.mAppState = appState;
        this.mMillisecondUpdateInterval = millisecondUpdateInterval;
        this.mMaxAppLoadWaitInterval = maxWaitIntervalToFinishLoading;
    }

    public void registerListener(AppListItemListener appListItemListener) {
        if (!this.mAppListItemListeners.contains(appListItemListener) && appListItemListener != null) {
            this.mAppListItemListeners.add(appListItemListener);
        }
    }

    public void unregisterlistener(AppListItemListener appListItemListener) {
        this.mAppListItemListeners.remove(appListItemListener);
    }

    public void onFragmentStart() {
        this.mSession.onResume();
        warnIfNotAllLoadedInTime();
    }

    public void onFragmentStop() {
        this.mSession.onPause();
    }

    public void startLoading(ApplicationsState.AppFilter appFilter, Comparator<ApplicationsState.AppEntry> appEntryComparator) {
        if (this.mSession != null) {
            LOG.w("Loading already started but restart attempted.");
            return;
        }
        this.mAppFilter = appFilter;
        this.mAppEntryComparator = appEntryComparator;
        this.mSession = this.mAppState.newSession(this, this.mLifecycle);
    }

    public void rebuildWithFilter(ApplicationsState.AppFilter appFilter) {
        this.mAppFilter = appFilter;
        rebuild();
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageIconChanged() {
        rebuild();
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageSizeChanged(String packageName) {
        rebuild();
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onAllSizesComputed() {
        rebuild();
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLauncherInfoChanged() {
        rebuild();
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLoadEntriesCompleted() {
        rebuild();
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onRunningStateChanged(boolean running) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageListChanged() {
        rebuild();
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> apps) {
        if (apps.size() == 0) {
            return;
        }
        if (this.mReadyToRenderUpdates) {
            this.mReadyToRenderUpdates = false;
            this.mLoadedApps = new ArrayList<>();
            for (ApplicationsState.AppEntry app : apps) {
                if (isLoaded(app)) {
                    this.mLoadedApps.add(app);
                }
            }
            for (AppListItemListener appListItemListener : this.mAppListItemListeners) {
                appListItemListener.onDataLoaded(this.mLoadedApps);
            }
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.settings.applications.-$$Lambda$ApplicationListItemManager$M8p5yntJccJdT5Yq-nsB1633bmo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRebuildComplete$0$ApplicationListItemManager();
                }
            }, this.mMillisecondUpdateInterval);
        } else {
            this.mDeferredAppsToUpload = apps;
        }
        this.mAppsToLoad.addAll(apps);
    }

    public /* synthetic */ void lambda$onRebuildComplete$0$ApplicationListItemManager() {
        this.mReadyToRenderUpdates = true;
        ArrayList<ApplicationsState.AppEntry> arrayList = this.mDeferredAppsToUpload;
        if (arrayList != null) {
            onRebuildComplete(arrayList);
            this.mDeferredAppsToUpload = null;
        }
    }

    private boolean isLoaded(ApplicationsState.AppEntry app) {
        return (app.label == null || app.sizeStr == null || app.icon == null) ? false : true;
    }

    private void warnIfNotAllLoadedInTime() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.settings.applications.-$$Lambda$ApplicationListItemManager$6FPevpsUOlJBylUB4rDPfCXqzDg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$warnIfNotAllLoadedInTime$1$ApplicationListItemManager();
            }
        }, this.mMaxAppLoadWaitInterval);
    }

    public /* synthetic */ void lambda$warnIfNotAllLoadedInTime$1$ApplicationListItemManager() {
        if (this.mLoadedApps.size() < this.mAppsToLoad.size()) {
            LOG.w("Expected to load " + this.mAppsToLoad.size() + " apps but only loaded " + this.mLoadedApps.size());
            Set<ApplicationsState.AppEntry> appsToLoadCopy = new HashSet<>(this.mAppsToLoad);
            for (ApplicationsState.AppEntry loadedApp : this.mLoadedApps) {
                appsToLoadCopy.remove(loadedApp);
            }
            for (ApplicationsState.AppEntry appEntry : appsToLoadCopy) {
                String appName = appEntry.label == null ? APP_NAME_UNKNOWN : appEntry.label;
                LOG.w("App failed to load: " + appName);
            }
        }
    }

    ApplicationsState.AppFilter getCompositeFilter(String volumeUuid) {
        if (this.mAppFilter == null) {
            return null;
        }
        ApplicationsState.AppFilter filter = new ApplicationsState.VolumeFilter(volumeUuid);
        return new ApplicationsState.CompoundFilter(this.mAppFilter, filter);
    }

    private void rebuild() {
        ApplicationsState.AppFilter filterObj = new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_EVERYTHING, ApplicationsState.FILTER_NOT_HIDE);
        ApplicationsState.AppFilter compositeFilter = getCompositeFilter(this.mVolumeInfo.getFsUuid());
        if (compositeFilter != null) {
            filterObj = new ApplicationsState.CompoundFilter(filterObj, compositeFilter);
        }
        ApplicationsState.AppFilter finalFilterObj = filterObj;
        this.mSession.rebuild(finalFilterObj, this.mAppEntryComparator, false);
    }
}
