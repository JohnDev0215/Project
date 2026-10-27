package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.ProgressBarPreference;
import com.android.car.settings.storage.StorageAsyncLoader;

/* JADX INFO: loaded from: classes5.dex */
public class StorageSystemCategoryPreferenceController extends StorageUsageBasePreferenceController {
    public StorageSystemCategoryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.storage.StorageUsageBasePreferenceController
    protected long calculateCategoryUsage(SparseArray<StorageAsyncLoader.AppsStorageResult> result, long usedSizeBytes) {
        long attributedSize = 0;
        for (int i = 0; i < result.size(); i++) {
            StorageAsyncLoader.AppsStorageResult otherData = result.valueAt(i);
            attributedSize = attributedSize + otherData.getGamesSize() + otherData.getMusicAppsSize() + otherData.getVideoAppsSize() + otherData.getPhotosAppsSize() + otherData.getOtherAppsSize() + (otherData.getExternalStats().totalBytes - otherData.getExternalStats().appBytes);
        }
        return Math.max(1073741824L, usedSizeBytes - attributedSize);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceClicked(ProgressBarPreference preference) {
        getFragmentController().showDialog(getStorageDetailDialogFragment(), ConfirmationDialogFragment.TAG);
        return true;
    }

    private ConfirmationDialogFragment getStorageDetailDialogFragment() {
        return new ConfirmationDialogFragment.Builder(getContext()).setMessage(getContext().getString(R.string.storage_detail_dialog_system, Build.VERSION.RELEASE)).setPositiveButton(android.R.string.ok, (ConfirmationDialogFragment.ConfirmListener) null).build();
    }
}
