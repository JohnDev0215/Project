package com.android.car.settings.storage;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.android.car.settings.R;
import com.android.car.settings.applications.AppListFragment;
import com.android.car.settings.applications.ApplicationListItemManager;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes5.dex */
public class StorageMediaCategoryDetailFragment extends AppListFragment {
    private ApplicationListItemManager mAppListItemManager;

    public static StorageMediaCategoryDetailFragment getInstance() {
        StorageMediaCategoryDetailFragment storageMedia = new StorageMediaCategoryDetailFragment();
        return storageMedia;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.storage_media_category_detail_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        long externalAudioBytes = bundle.getLong(StorageMediaCategoryPreferenceController.EXTRA_AUDIO_BYTES);
        StorageManager sm = (StorageManager) context.getSystemService(StorageManager.class);
        VolumeInfo volume = StorageUtils.maybeInitializeVolume(sm, getArguments());
        Application application = requireActivity().getApplication();
        this.mAppListItemManager = new ApplicationListItemManager(volume, getLifecycle(), ApplicationsState.getInstance(application), getContext().getResources().getInteger(R.integer.millisecond_app_data_update_interval), getContext().getResources().getInteger(R.integer.millisecond_max_app_load_wait_interval));
        StorageMediaCategoryDetailPreferenceController pc = (StorageMediaCategoryDetailPreferenceController) use(StorageMediaCategoryDetailPreferenceController.class, R.string.pk_storage_music_audio_details);
        this.mAppListItemManager.registerListener(pc);
        pc.setExternalAudioBytes(externalAudioBytes);
    }

    @Override // com.android.car.settings.applications.AppListFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAppListItemManager.startLoading(getAppFilter(), ApplicationsState.SIZE_COMPARATOR);
    }

    @Override // com.android.car.settings.applications.AppListFragment, com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mAppListItemManager.onFragmentStart();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mAppListItemManager.onFragmentStop();
    }

    @Override // com.android.car.settings.applications.AppListFragment
    protected void onToggleShowSystemApps(boolean showSystem) {
        this.mAppListItemManager.rebuildWithFilter(getAppFilter());
    }

    private ApplicationsState.AppFilter getAppFilter() {
        ApplicationsState.AppFilter filter = ApplicationsState.FILTER_AUDIO;
        if (!shouldShowSystemApps()) {
            return new ApplicationsState.CompoundFilter(filter, ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER_AND_INSTANT);
        }
        return filter;
    }
}
