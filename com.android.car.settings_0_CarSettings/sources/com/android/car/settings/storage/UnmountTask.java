package com.android.car.settings.storage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.android.car.settings.common.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class UnmountTask extends AsyncTask<Void, Void, Exception> {
    private static final Logger LOG = new Logger((Class<?>) UnmountTask.class);
    private final Context mContext;
    private final String mDescription;
    private final StorageManager mStorageManager;
    private final String mVolumeId;

    public UnmountTask(Context context, VolumeInfo volume) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mStorageManager = (StorageManager) applicationContext.getSystemService(StorageManager.class);
        this.mVolumeId = volume.getId();
        this.mDescription = this.mStorageManager.getBestVolumeDescription(volume);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Exception doInBackground(Void... params) {
        try {
            this.mStorageManager.unmount(this.mVolumeId);
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Exception e) {
    }
}
