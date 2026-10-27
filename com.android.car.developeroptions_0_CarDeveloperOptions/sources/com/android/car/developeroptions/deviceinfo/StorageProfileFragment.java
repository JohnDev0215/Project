package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.SparseArray;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.deviceinfo.storage.StorageAsyncLoader;
import com.android.car.developeroptions.deviceinfo.storage.StorageItemPreferenceController;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StorageProfileFragment extends DashboardFragment implements LoaderManager.LoaderCallbacks<SparseArray<StorageAsyncLoader.AppsStorageResult>> {
    private StorageItemPreferenceController mPreferenceController;
    private int mUserId;
    private VolumeInfo mVolume;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "StorageProfileFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 845;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.storage_profile_fragment;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader) {
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        VolumeInfo volumeInfoMaybeInitializeVolume = Utils.maybeInitializeVolume((StorageManager) getActivity().getSystemService(StorageManager.class), arguments);
        this.mVolume = volumeInfoMaybeInitializeVolume;
        if (volumeInfoMaybeInitializeVolume == null) {
            getActivity().finish();
            return;
        }
        this.mPreferenceController.setVolume(volumeInfoMaybeInitializeVolume);
        int i = arguments.getInt("userId", UserHandle.myUserId());
        this.mUserId = i;
        this.mPreferenceController.setUserId(UserHandle.of(i));
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(0, Bundle.EMPTY, this);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        StorageItemPreferenceController storageItemPreferenceController = new StorageItemPreferenceController(context, this, this.mVolume, new StorageManagerVolumeProvider((StorageManager) context.getSystemService(StorageManager.class)), true);
        this.mPreferenceController = storageItemPreferenceController;
        arrayList.add(storageItemPreferenceController);
        return arrayList;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> onCreateLoader(int i, Bundle bundle) {
        Context context = getContext();
        return new StorageAsyncLoader(context, (UserManager) context.getSystemService(UserManager.class), this.mVolume.fsUuid, new StorageStatsSource(context), context.getPackageManager());
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader, SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        this.mPreferenceController.onLoadFinished(sparseArray, this.mUserId);
    }

    void setPreferenceController(StorageItemPreferenceController storageItemPreferenceController) {
        this.mPreferenceController = storageItemPreferenceController;
    }
}
