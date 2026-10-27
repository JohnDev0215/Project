package com.amazon.music.externalstorage;

import android.content.Context;
import android.content.SharedPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class StorageLocationPreference {
    private final Logger LOG = LoggerFactory.getLogger((Class<?>) StorageLocationPreference.class);
    private final Context context;

    public StorageLocationPreference(Context context) {
        this.context = context;
    }

    public StorageLocation getPreferredStorageLocation() {
        return StorageLocation.lookupByCode(this.context.getSharedPreferences("ExternalStoragePrefs", 0).getString("ExternalStoragePreferredStorage", StorageLocation.DEVICE.getCode()));
    }

    public void setPreferredStorageLocation(StorageLocation storageLocation) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("ExternalStoragePrefs", 0);
        this.LOG.debug("setPreferredStorageLocation() {}", storageLocation);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("ExternalStoragePreferredStorage", storageLocation.getCode());
        editorEdit.commit();
    }
}
