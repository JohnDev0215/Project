package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.SparseArray;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.ProgressBarPreference;
import com.android.car.settings.storage.StorageAsyncLoader;

/* JADX INFO: loaded from: classes5.dex */
public class StorageMediaCategoryPreferenceController extends StorageUsageBasePreferenceController {
    public static final String EXTRA_AUDIO_BYTES = "extra_audio_bytes";
    private long mExternalAudioBytes;

    public StorageMediaCategoryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.storage.StorageUsageBasePreferenceController
    protected long calculateCategoryUsage(SparseArray<StorageAsyncLoader.AppsStorageResult> result, long usedSizeBytes) {
        StorageAsyncLoader.AppsStorageResult data = result.get(UserHandle.myUserId());
        this.mExternalAudioBytes = data.getExternalStats().audioBytes;
        return data.getMusicAppsSize() + this.mExternalAudioBytes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceClicked(ProgressBarPreference preference) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_AUDIO_BYTES, this.mExternalAudioBytes);
        StorageMediaCategoryDetailFragment storageMediaCategoryDetailFragment = StorageMediaCategoryDetailFragment.getInstance();
        storageMediaCategoryDetailFragment.setArguments(bundle);
        getFragmentController().launchFragment(storageMediaCategoryDetailFragment);
        return true;
    }
}
