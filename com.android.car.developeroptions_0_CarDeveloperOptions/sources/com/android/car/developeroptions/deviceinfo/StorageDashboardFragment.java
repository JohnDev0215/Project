package com.android.car.developeroptions.deviceinfo;

import android.app.Activity;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.provider.SearchIndexableResource;
import android.util.SparseArray;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.deviceinfo.StorageDashboardFragment;
import com.android.car.developeroptions.deviceinfo.storage.AutomaticStorageManagementSwitchPreferenceController;
import com.android.car.developeroptions.deviceinfo.storage.CachedStorageValuesHelper;
import com.android.car.developeroptions.deviceinfo.storage.SecondaryUserController;
import com.android.car.developeroptions.deviceinfo.storage.StorageAsyncLoader;
import com.android.car.developeroptions.deviceinfo.storage.StorageItemPreferenceController;
import com.android.car.developeroptions.deviceinfo.storage.StorageSummaryDonutPreferenceController;
import com.android.car.developeroptions.deviceinfo.storage.UserIconLoader;
import com.android.car.developeroptions.deviceinfo.storage.VolumeSizesLoader;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class StorageDashboardFragment extends DashboardFragment implements LoaderManager.LoaderCallbacks<SparseArray<StorageAsyncLoader.AppsStorageResult>> {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.deviceinfo.StorageDashboardFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.storage_dashboard_fragment;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private SparseArray<StorageAsyncLoader.AppsStorageResult> mAppsResult;
    private CachedStorageValuesHelper mCachedStorageValuesHelper;
    private PrivateVolumeOptionMenuController mOptionMenuController;
    private StorageItemPreferenceController mPreferenceController;
    private List<AbstractPreferenceController> mSecondaryUsers;
    private PrivateStorageInfo mStorageInfo;
    private StorageSummaryDonutPreferenceController mSummaryController;
    private VolumeInfo mVolume;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_storage_dashboard;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "StorageDashboardFrag";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 745;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.storage_dashboard_fragment;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader) {
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        VolumeInfo volumeInfoMaybeInitializeVolume = Utils.maybeInitializeVolume((StorageManager) activity.getSystemService(StorageManager.class), getArguments());
        this.mVolume = volumeInfoMaybeInitializeVolume;
        if (volumeInfoMaybeInitializeVolume == null) {
            activity.finish();
        } else {
            initializeOptionsMenu(activity);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((AutomaticStorageManagementSwitchPreferenceController) use(AutomaticStorageManagementSwitchPreferenceController.class)).setFragmentManager(getFragmentManager());
    }

    void initializeOptionsMenu(Activity activity) {
        this.mOptionMenuController = new PrivateVolumeOptionMenuController(activity, this.mVolume, activity.getPackageManager());
        getSettingsLifecycle().addObserver(this.mOptionMenuController);
        setHasOptionsMenu(true);
        activity.invalidateOptionsMenu();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initializeCacheProvider();
        maybeSetLoading(isQuotaSupported());
        FragmentActivity activity = getActivity();
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this, null);
        entityHeaderControllerNewInstance.setRecyclerView(getListView(), getSettingsLifecycle());
        entityHeaderControllerNewInstance.styleActionBar(activity);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, Bundle.EMPTY, this);
        getLoaderManager().restartLoader(2, Bundle.EMPTY, new VolumeSizeCallbacks());
        getLoaderManager().initLoader(1, Bundle.EMPTY, new IconLoaderCallbacks());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReceivedSizes() {
        PrivateStorageInfo privateStorageInfo = this.mStorageInfo;
        if (privateStorageInfo != null) {
            long j = privateStorageInfo.totalBytes;
            long j2 = j - privateStorageInfo.freeBytes;
            this.mSummaryController.updateBytes(j2, j);
            this.mPreferenceController.setVolume(this.mVolume);
            this.mPreferenceController.setUsedSize(j2);
            this.mPreferenceController.setTotalSize(this.mStorageInfo.totalBytes);
            int size = this.mSecondaryUsers.size();
            for (int i = 0; i < size; i++) {
                AbstractPreferenceController abstractPreferenceController = this.mSecondaryUsers.get(i);
                if (abstractPreferenceController instanceof SecondaryUserController) {
                    ((SecondaryUserController) abstractPreferenceController).setTotalSize(this.mStorageInfo.totalBytes);
                }
            }
        }
        SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray = this.mAppsResult;
        if (sparseArray == null) {
            return;
        }
        this.mPreferenceController.onLoadFinished(sparseArray, UserHandle.myUserId());
        updateSecondaryUserControllers(this.mSecondaryUsers, this.mAppsResult);
        if (getView().findViewById(R.id.loading_container).getVisibility() == 0) {
            setLoading(false, true);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        StorageSummaryDonutPreferenceController storageSummaryDonutPreferenceController = new StorageSummaryDonutPreferenceController(context);
        this.mSummaryController = storageSummaryDonutPreferenceController;
        arrayList.add(storageSummaryDonutPreferenceController);
        StorageItemPreferenceController storageItemPreferenceController = new StorageItemPreferenceController(context, this, this.mVolume, new StorageManagerVolumeProvider((StorageManager) context.getSystemService(StorageManager.class)));
        this.mPreferenceController = storageItemPreferenceController;
        arrayList.add(storageItemPreferenceController);
        List<AbstractPreferenceController> secondaryUserControllers = SecondaryUserController.getSecondaryUserControllers(context, (UserManager) context.getSystemService(UserManager.class));
        this.mSecondaryUsers = secondaryUserControllers;
        arrayList.addAll(secondaryUserControllers);
        return arrayList;
    }

    protected void setVolume(VolumeInfo volumeInfo) {
        this.mVolume = volumeInfo;
    }

    private void updateSecondaryUserControllers(List<AbstractPreferenceController> list, SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = (AbstractPreferenceController) list.get(i);
            if (obj instanceof StorageAsyncLoader.ResultHandler) {
                ((StorageAsyncLoader.ResultHandler) obj).handleResult(sparseArray);
            }
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> onCreateLoader(int i, Bundle bundle) {
        Context context = getContext();
        return new StorageAsyncLoader(context, (UserManager) context.getSystemService(UserManager.class), this.mVolume.fsUuid, new StorageStatsSource(context), context.getPackageManager());
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader, SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        this.mAppsResult = sparseArray;
        maybeCacheFreshValues();
        onReceivedSizes();
    }

    public void setCachedStorageValuesHelper(CachedStorageValuesHelper cachedStorageValuesHelper) {
        this.mCachedStorageValuesHelper = cachedStorageValuesHelper;
    }

    public PrivateStorageInfo getPrivateStorageInfo() {
        return this.mStorageInfo;
    }

    public void setPrivateStorageInfo(PrivateStorageInfo privateStorageInfo) {
        this.mStorageInfo = privateStorageInfo;
    }

    public SparseArray<StorageAsyncLoader.AppsStorageResult> getAppsStorageResult() {
        return this.mAppsResult;
    }

    public void setAppsStorageResult(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        this.mAppsResult = sparseArray;
    }

    public void initializeCachedValues() {
        PrivateStorageInfo cachedPrivateStorageInfo = this.mCachedStorageValuesHelper.getCachedPrivateStorageInfo();
        SparseArray<StorageAsyncLoader.AppsStorageResult> cachedAppsStorageResult = this.mCachedStorageValuesHelper.getCachedAppsStorageResult();
        if (cachedPrivateStorageInfo == null || cachedAppsStorageResult == null) {
            return;
        }
        this.mStorageInfo = cachedPrivateStorageInfo;
        this.mAppsResult = cachedAppsStorageResult;
    }

    public void maybeSetLoading(boolean z) {
        if (!(z && (this.mStorageInfo == null || this.mAppsResult == null)) && (z || this.mStorageInfo != null)) {
            return;
        }
        setLoading(true, false);
    }

    private void initializeCacheProvider() {
        this.mCachedStorageValuesHelper = new CachedStorageValuesHelper(getContext(), UserHandle.myUserId());
        initializeCachedValues();
        onReceivedSizes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeCacheFreshValues() {
        SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray;
        PrivateStorageInfo privateStorageInfo = this.mStorageInfo;
        if (privateStorageInfo == null || (sparseArray = this.mAppsResult) == null) {
            return;
        }
        this.mCachedStorageValuesHelper.cacheResult(privateStorageInfo, sparseArray.get(UserHandle.myUserId()));
    }

    private boolean isQuotaSupported() {
        return ((StorageStatsManager) getActivity().getSystemService(StorageStatsManager.class)).isQuotaSupported(this.mVolume.fsUuid);
    }

    public final class IconLoaderCallbacks implements LoaderManager.LoaderCallbacks<SparseArray<Drawable>> {
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<SparseArray<Drawable>> loader) {
        }

        public IconLoaderCallbacks() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<SparseArray<Drawable>> onCreateLoader(int i, Bundle bundle) {
            return new UserIconLoader(StorageDashboardFragment.this.getContext(), new UserIconLoader.FetchUserIconTask() { // from class: com.android.car.developeroptions.deviceinfo.-$$Lambda$StorageDashboardFragment$IconLoaderCallbacks$bvvdK-fmkM_bEO6yEFRkjt4ohao
                @Override // com.android.car.developeroptions.deviceinfo.storage.UserIconLoader.FetchUserIconTask
                public final SparseArray getUserIcons() {
                    return this.f$0.lambda$onCreateLoader$0$StorageDashboardFragment$IconLoaderCallbacks();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onCreateLoader$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ SparseArray lambda$onCreateLoader$0$StorageDashboardFragment$IconLoaderCallbacks() {
            return UserIconLoader.loadUserIconsWithContext(StorageDashboardFragment.this.getContext());
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<SparseArray<Drawable>> loader, final SparseArray<Drawable> sparseArray) {
            StorageDashboardFragment.this.mSecondaryUsers.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.deviceinfo.-$$Lambda$StorageDashboardFragment$IconLoaderCallbacks$80cY_6BaRE6dYFbFsY_-Hb0S5uc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return StorageDashboardFragment.IconLoaderCallbacks.lambda$onLoadFinished$1((AbstractPreferenceController) obj);
                }
            }).forEach(new Consumer() { // from class: com.android.car.developeroptions.deviceinfo.-$$Lambda$StorageDashboardFragment$IconLoaderCallbacks$bFLbPq26RaMPaaA0R3utmDDKGbA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UserIconLoader.UserIconHandler) ((AbstractPreferenceController) obj)).handleUserIcons(sparseArray);
                }
            });
        }

        static /* synthetic */ boolean lambda$onLoadFinished$1(AbstractPreferenceController abstractPreferenceController) {
            return abstractPreferenceController instanceof UserIconLoader.UserIconHandler;
        }
    }

    public final class VolumeSizeCallbacks implements LoaderManager.LoaderCallbacks<PrivateStorageInfo> {
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<PrivateStorageInfo> loader) {
        }

        public VolumeSizeCallbacks() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<PrivateStorageInfo> onCreateLoader(int i, Bundle bundle) {
            Context context = StorageDashboardFragment.this.getContext();
            return new VolumeSizesLoader(context, new StorageManagerVolumeProvider((StorageManager) context.getSystemService(StorageManager.class)), (StorageStatsManager) context.getSystemService(StorageStatsManager.class), StorageDashboardFragment.this.mVolume);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<PrivateStorageInfo> loader, PrivateStorageInfo privateStorageInfo) {
            if (privateStorageInfo != null) {
                StorageDashboardFragment.this.mStorageInfo = privateStorageInfo;
                StorageDashboardFragment.this.maybeCacheFreshValues();
                StorageDashboardFragment.this.onReceivedSizes();
                return;
            }
            StorageDashboardFragment.this.getActivity().finish();
        }
    }
}
