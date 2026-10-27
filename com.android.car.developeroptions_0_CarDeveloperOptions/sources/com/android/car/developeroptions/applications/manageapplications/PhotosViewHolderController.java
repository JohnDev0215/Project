package com.android.car.developeroptions.applications.manageapplications;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.format.Formatter;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.applications.StorageStatsSource;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class PhotosViewHolderController implements FileViewHolderController {
    private Context mContext;
    private long mFilesSize;
    private StorageStatsSource mSource;
    private UserHandle mUser;
    private String mVolumeUuid;

    @Override // com.android.car.developeroptions.applications.manageapplications.FileViewHolderController
    public boolean shouldShow() {
        return true;
    }

    public PhotosViewHolderController(Context context, StorageStatsSource storageStatsSource, String str, UserHandle userHandle) {
        this.mContext = context;
        this.mSource = storageStatsSource;
        this.mVolumeUuid = str;
        this.mUser = userHandle;
    }

    @Override // com.android.car.developeroptions.applications.manageapplications.FileViewHolderController
    public void queryStats() {
        try {
            StorageStatsSource.ExternalStorageStats externalStorageStats = this.mSource.getExternalStorageStats(this.mVolumeUuid, this.mUser);
            this.mFilesSize = externalStorageStats.imageBytes + externalStorageStats.videoBytes;
        } catch (IOException e) {
            this.mFilesSize = 0L;
            Log.w("PhotosViewHolderCtrl", e);
        }
    }

    @Override // com.android.car.developeroptions.applications.manageapplications.FileViewHolderController
    public void setupView(ApplicationViewHolder applicationViewHolder) {
        applicationViewHolder.setIcon(R.drawable.ic_photo_library);
        applicationViewHolder.setTitle(this.mContext.getText(R.string.storage_detail_images));
        applicationViewHolder.setSummary(Formatter.formatFileSize(this.mContext, this.mFilesSize));
    }

    @Override // com.android.car.developeroptions.applications.manageapplications.FileViewHolderController
    public void onClick(Fragment fragment) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(524288);
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.FROM_STORAGE", true);
        Utils.launchIntent(fragment, intent);
    }
}
