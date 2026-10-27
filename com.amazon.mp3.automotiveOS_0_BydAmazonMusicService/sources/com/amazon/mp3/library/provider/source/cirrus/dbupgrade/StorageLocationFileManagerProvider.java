package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.os.Environment;
import com.amazon.music.externalstorage.StorageLocationFileManager;

/* JADX INFO: loaded from: classes3.dex */
class StorageLocationFileManagerProvider {
    private static StorageLocationFileManagerProvider sInstance;
    private StorageLocationFileManager mStorageLocationFileManager;

    private StorageLocationFileManagerProvider() {
    }

    private void init(final Context context) {
        this.mStorageLocationFileManager = StorageLocationFileManager.create(context, Environment.DIRECTORY_MUSIC);
    }

    public static synchronized StorageLocationFileManagerProvider getInstance(final Context context) {
        if (sInstance == null) {
            StorageLocationFileManagerProvider storageLocationFileManagerProvider = new StorageLocationFileManagerProvider();
            sInstance = storageLocationFileManagerProvider;
            storageLocationFileManagerProvider.init(context);
        }
        return sInstance;
    }

    public StorageLocationFileManager getFileManager() {
        return this.mStorageLocationFileManager;
    }
}
