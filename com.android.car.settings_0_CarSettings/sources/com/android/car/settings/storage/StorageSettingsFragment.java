package com.android.car.settings.storage;

import android.content.Context;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import androidx.loader.app.LoaderManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StorageSettingsFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.storage_settings_fragment, "android.settings.INTERNAL_STORAGE_SETTINGS");
    private StorageSettingsManager mStorageSettingsManager;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.storage_settings_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        StorageManager sm = (StorageManager) context.getSystemService(StorageManager.class);
        VolumeInfo volume = StorageUtils.maybeInitializeVolume(sm, getArguments());
        this.mStorageSettingsManager = new StorageSettingsManager(getContext(), volume);
        List<StorageUsageBasePreferenceController> usagePreferenceControllers = Arrays.asList((StorageUsageBasePreferenceController) use(StorageMediaCategoryPreferenceController.class, R.string.pk_storage_music_audio), (StorageUsageBasePreferenceController) use(StorageOtherCategoryPreferenceController.class, R.string.pk_storage_other_apps), (StorageUsageBasePreferenceController) use(StorageFileCategoryPreferenceController.class, R.string.pk_storage_files), (StorageUsageBasePreferenceController) use(StorageSystemCategoryPreferenceController.class, R.string.pk_storage_system));
        for (StorageUsageBasePreferenceController pc : usagePreferenceControllers) {
            this.mStorageSettingsManager.registerListener(pc);
            pc.setVolumeInfo(volume);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        this.mStorageSettingsManager.startLoading(loaderManager);
    }
}
