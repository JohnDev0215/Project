package com.android.car.developeroptions.deviceinfo.storage;

import android.R;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.storage.VolumeInfo;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.Settings$GamesStorageActivity;
import com.android.car.developeroptions.Settings$MoviesStorageActivity;
import com.android.car.developeroptions.Settings$PhotosStorageActivity;
import com.android.car.developeroptions.Settings$StorageUseActivity;
import com.android.car.developeroptions.applications.manageapplications.ManageApplications;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.deviceinfo.PrivateVolumeSettings;
import com.android.car.developeroptions.deviceinfo.StorageItemPreference;
import com.android.car.developeroptions.deviceinfo.storage.StorageAsyncLoader;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.deviceinfo.StorageVolumeProvider;

/* JADX INFO: loaded from: classes.dex */
public class StorageItemPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    static final String AUDIO_KEY = "pref_music_audio";
    static final String FILES_KEY = "pref_files";
    static final String GAME_KEY = "pref_games";
    static final String MOVIES_KEY = "pref_movies";
    static final String OTHER_APPS_KEY = "pref_other_apps";
    static final String PHOTO_KEY = "pref_photos_videos";
    static final String SYSTEM_KEY = "pref_system";
    private StorageItemPreference mAppPreference;
    private StorageItemPreference mAudioPreference;
    private StorageItemPreference mFilePreference;
    private final Fragment mFragment;
    private StorageItemPreference mGamePreference;
    private boolean mIsWorkProfile;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private StorageItemPreference mMoviesPreference;
    private StorageItemPreference mPhotoPreference;
    private PreferenceScreen mScreen;
    private final StorageVolumeProvider mSvp;
    private StorageItemPreference mSystemPreference;
    private long mTotalSize;
    private long mUsedBytes;
    private int mUserId;
    private VolumeInfo mVolume;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public StorageItemPreferenceController(Context context, Fragment fragment, VolumeInfo volumeInfo, StorageVolumeProvider storageVolumeProvider) {
        super(context);
        this.mFragment = fragment;
        this.mVolume = volumeInfo;
        this.mSvp = storageVolumeProvider;
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        this.mUserId = UserHandle.myUserId();
    }

    public StorageItemPreferenceController(Context context, Fragment fragment, VolumeInfo volumeInfo, StorageVolumeProvider storageVolumeProvider, boolean z) {
        this(context, fragment, volumeInfo, storageVolumeProvider);
        this.mIsWorkProfile = z;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (preference == null) {
            return false;
        }
        Intent photosIntent = null;
        if (preference.getKey() == null) {
            return false;
        }
        switch (preference.getKey()) {
            case "pref_photos_videos":
                photosIntent = getPhotosIntent();
                break;
            case "pref_music_audio":
                photosIntent = getAudioIntent();
                break;
            case "pref_games":
                photosIntent = getGamesIntent();
                break;
            case "pref_movies":
                photosIntent = getMoviesIntent();
                break;
            case "pref_other_apps":
                if (this.mVolume != null) {
                    photosIntent = getAppsIntent();
                    break;
                }
                break;
            case "pref_files":
                photosIntent = getFilesIntent();
                FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 841, new Pair[0]);
                break;
            case "pref_system":
                PrivateVolumeSettings.SystemInfoFragment systemInfoFragment = new PrivateVolumeSettings.SystemInfoFragment();
                systemInfoFragment.setTargetFragment(this.mFragment, 0);
                systemInfoFragment.show(this.mFragment.getFragmentManager(), "SystemInfo");
                return true;
        }
        if (photosIntent != null) {
            photosIntent.putExtra("android.intent.extra.USER_ID", this.mUserId);
            launchIntent(photosIntent);
            return true;
        }
        return super.handlePreferenceTreeClick(preference);
    }

    public void setVolume(VolumeInfo volumeInfo) {
        this.mVolume = volumeInfo;
        setFilesPreferenceVisibility();
    }

    private void setFilesPreferenceVisibility() {
        if (this.mScreen != null) {
            VolumeInfo volumeInfoFindEmulatedForPrivate = this.mSvp.findEmulatedForPrivate(this.mVolume);
            if (volumeInfoFindEmulatedForPrivate == null || !volumeInfoFindEmulatedForPrivate.isMountedReadable()) {
                this.mScreen.removePreference(this.mFilePreference);
            } else {
                this.mScreen.addPreference(this.mFilePreference);
            }
        }
    }

    public void setUserId(UserHandle userHandle) {
        this.mUserId = userHandle.getIdentifier();
        tintPreference(this.mPhotoPreference);
        tintPreference(this.mMoviesPreference);
        tintPreference(this.mAudioPreference);
        tintPreference(this.mGamePreference);
        tintPreference(this.mAppPreference);
        tintPreference(this.mSystemPreference);
        tintPreference(this.mFilePreference);
    }

    private void tintPreference(Preference preference) {
        if (preference != null) {
            preference.setIcon(applyTint(this.mContext, preference.getIcon()));
        }
    }

    private static Drawable applyTint(Context context, Drawable drawable) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.colorControlNormal});
        Drawable drawableMutate = drawable.mutate();
        drawableMutate.setTint(typedArrayObtainStyledAttributes.getColor(0, 0));
        typedArrayObtainStyledAttributes.recycle();
        return drawableMutate;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mScreen = preferenceScreen;
        this.mPhotoPreference = (StorageItemPreference) preferenceScreen.findPreference(PHOTO_KEY);
        this.mAudioPreference = (StorageItemPreference) preferenceScreen.findPreference(AUDIO_KEY);
        this.mGamePreference = (StorageItemPreference) preferenceScreen.findPreference(GAME_KEY);
        this.mMoviesPreference = (StorageItemPreference) preferenceScreen.findPreference(MOVIES_KEY);
        this.mAppPreference = (StorageItemPreference) preferenceScreen.findPreference(OTHER_APPS_KEY);
        this.mSystemPreference = (StorageItemPreference) preferenceScreen.findPreference(SYSTEM_KEY);
        this.mFilePreference = (StorageItemPreference) preferenceScreen.findPreference(FILES_KEY);
        setFilesPreferenceVisibility();
    }

    public void onLoadFinished(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray, int i) {
        StorageAsyncLoader.AppsStorageResult appsStorageResult = sparseArray.get(i);
        StorageItemPreference storageItemPreference = this.mPhotoPreference;
        long j = appsStorageResult.photosAppsSize;
        StorageStatsSource.ExternalStorageStats externalStorageStats = appsStorageResult.externalStats;
        storageItemPreference.setStorageSize(j + externalStorageStats.imageBytes + externalStorageStats.videoBytes, this.mTotalSize);
        this.mAudioPreference.setStorageSize(appsStorageResult.musicAppsSize + appsStorageResult.externalStats.audioBytes, this.mTotalSize);
        this.mGamePreference.setStorageSize(appsStorageResult.gamesSize, this.mTotalSize);
        this.mMoviesPreference.setStorageSize(appsStorageResult.videoAppsSize, this.mTotalSize);
        this.mAppPreference.setStorageSize(appsStorageResult.otherAppsSize, this.mTotalSize);
        StorageStatsSource.ExternalStorageStats externalStorageStats2 = appsStorageResult.externalStats;
        this.mFilePreference.setStorageSize((((externalStorageStats2.totalBytes - externalStorageStats2.audioBytes) - externalStorageStats2.videoBytes) - externalStorageStats2.imageBytes) - externalStorageStats2.appBytes, this.mTotalSize);
        if (this.mSystemPreference != null) {
            long j2 = 0;
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                StorageAsyncLoader.AppsStorageResult appsStorageResultValueAt = sparseArray.valueAt(i2);
                long j3 = j2 + appsStorageResultValueAt.gamesSize + appsStorageResultValueAt.musicAppsSize + appsStorageResultValueAt.videoAppsSize + appsStorageResultValueAt.photosAppsSize + appsStorageResultValueAt.otherAppsSize;
                StorageStatsSource.ExternalStorageStats externalStorageStats3 = appsStorageResultValueAt.externalStats;
                j2 = j3 + (externalStorageStats3.totalBytes - externalStorageStats3.appBytes);
            }
            this.mSystemPreference.setStorageSize(Math.max(1073741824L, this.mUsedBytes - j2), this.mTotalSize);
        }
    }

    public void setUsedSize(long j) {
        this.mUsedBytes = j;
    }

    public void setTotalSize(long j) {
        this.mTotalSize = j;
    }

    private Intent getPhotosIntent() {
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(2);
        workAnnotatedBundle.putString("classname", Settings$PhotosStorageActivity.class.getName());
        workAnnotatedBundle.putInt("storageType", 3);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ManageApplications.class.getName());
        subSettingLauncher.setTitleRes(com.android.car.developeroptions.R.string.storage_photos_videos);
        subSettingLauncher.setArguments(workAnnotatedBundle);
        subSettingLauncher.setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment));
        return subSettingLauncher.toIntent();
    }

    private Intent getAudioIntent() {
        if (this.mVolume == null) {
            return null;
        }
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(4);
        workAnnotatedBundle.putString("classname", Settings$StorageUseActivity.class.getName());
        workAnnotatedBundle.putString("volumeUuid", this.mVolume.getFsUuid());
        workAnnotatedBundle.putString("volumeName", this.mVolume.getDescription());
        workAnnotatedBundle.putInt("storageType", 1);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ManageApplications.class.getName());
        subSettingLauncher.setTitleRes(com.android.car.developeroptions.R.string.storage_music_audio);
        subSettingLauncher.setArguments(workAnnotatedBundle);
        subSettingLauncher.setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment));
        return subSettingLauncher.toIntent();
    }

    private Intent getAppsIntent() {
        if (this.mVolume == null) {
            return null;
        }
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(3);
        workAnnotatedBundle.putString("classname", Settings$StorageUseActivity.class.getName());
        workAnnotatedBundle.putString("volumeUuid", this.mVolume.getFsUuid());
        workAnnotatedBundle.putString("volumeName", this.mVolume.getDescription());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ManageApplications.class.getName());
        subSettingLauncher.setTitleRes(com.android.car.developeroptions.R.string.apps_storage);
        subSettingLauncher.setArguments(workAnnotatedBundle);
        subSettingLauncher.setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment));
        return subSettingLauncher.toIntent();
    }

    private Intent getGamesIntent() {
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(1);
        workAnnotatedBundle.putString("classname", Settings$GamesStorageActivity.class.getName());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ManageApplications.class.getName());
        subSettingLauncher.setTitleRes(com.android.car.developeroptions.R.string.game_storage_settings);
        subSettingLauncher.setArguments(workAnnotatedBundle);
        subSettingLauncher.setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment));
        return subSettingLauncher.toIntent();
    }

    private Intent getMoviesIntent() {
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(1);
        workAnnotatedBundle.putString("classname", Settings$MoviesStorageActivity.class.getName());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ManageApplications.class.getName());
        subSettingLauncher.setTitleRes(com.android.car.developeroptions.R.string.storage_movies_tv);
        subSettingLauncher.setArguments(workAnnotatedBundle);
        subSettingLauncher.setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment));
        return subSettingLauncher.toIntent();
    }

    private Bundle getWorkAnnotatedBundle(int i) {
        Bundle bundle = new Bundle(i + 2);
        bundle.putBoolean("workProfileOnly", this.mIsWorkProfile);
        bundle.putInt("workId", this.mUserId);
        return bundle;
    }

    private Intent getFilesIntent() {
        return this.mSvp.findEmulatedForPrivate(this.mVolume).buildBrowseIntent();
    }

    private void launchIntent(Intent intent) {
        try {
            int intExtra = intent.getIntExtra("android.intent.extra.USER_ID", -1);
            if (intExtra == -1) {
                this.mFragment.startActivityForResult(intent, 0);
            } else {
                this.mFragment.getActivity().startActivityForResultAsUser(intent, 0, new UserHandle(intExtra));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("StorageItemPreference", "No activity found for " + intent);
        }
    }
}
