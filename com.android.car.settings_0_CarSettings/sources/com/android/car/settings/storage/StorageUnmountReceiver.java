package com.android.car.settings.storage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.android.car.settings.common.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class StorageUnmountReceiver extends BroadcastReceiver {
    private static final Logger LOG = new Logger((Class<?>) StorageUnmountReceiver.class);

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        StorageManager storage = (StorageManager) context.getSystemService(StorageManager.class);
        String volId = intent.getStringExtra("android.os.storage.extra.VOLUME_ID");
        VolumeInfo vol = storage.findVolumeById(volId);
        if (vol != null) {
            new UnmountTask(context, vol).execute(new Void[0]);
            return;
        }
        LOG.w("Missing volume " + volId);
    }
}
