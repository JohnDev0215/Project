package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.util.SparseArray;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.ProgressBarPreference;
import com.android.car.settings.storage.StorageAsyncLoader;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.android.settingslib.deviceinfo.StorageVolumeProvider;

/* JADX INFO: loaded from: classes5.dex */
public class StorageFileCategoryPreferenceController extends StorageUsageBasePreferenceController {
    private static final Logger LOG = new Logger((Class<?>) StorageFileCategoryPreferenceController.class);
    private StorageVolumeProvider mStorageVolumeProvider;

    public StorageFileCategoryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        StorageManager sm = (StorageManager) context.getSystemService(StorageManager.class);
        this.mStorageVolumeProvider = new StorageManagerVolumeProvider(sm);
    }

    @Override // com.android.car.settings.storage.StorageUsageBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        getPreference().setSelectable(getFilesIntent().resolveActivity(getContext().getPackageManager()) != null);
    }

    @Override // com.android.car.settings.storage.StorageUsageBasePreferenceController
    protected long calculateCategoryUsage(SparseArray<StorageAsyncLoader.AppsStorageResult> result, long usedSizeBytes) {
        StorageAsyncLoader.AppsStorageResult data = result.get(UserHandle.myUserId());
        return (((data.getExternalStats().totalBytes - data.getExternalStats().audioBytes) - data.getExternalStats().videoBytes) - data.getExternalStats().imageBytes) - data.getExternalStats().appBytes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceClicked(ProgressBarPreference preference) {
        int myUserId = UserHandle.myUserId();
        Intent intent = getFilesIntent();
        intent.putExtra("android.intent.extra.USER_ID", myUserId);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            getContext().startActivityAsUser(intent, UserHandle.of(myUserId));
            return true;
        }
        LOG.i("No activity found to handle intent: " + intent);
        return true;
    }

    private Intent getFilesIntent() {
        return this.mStorageVolumeProvider.findEmulatedForPrivate(getVolumeInfo()).buildBrowseIntent();
    }
}
