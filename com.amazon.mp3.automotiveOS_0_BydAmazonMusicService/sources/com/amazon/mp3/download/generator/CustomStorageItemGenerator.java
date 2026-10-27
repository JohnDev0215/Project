package com.amazon.mp3.download.generator;

import android.content.Context;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;

/* JADX INFO: loaded from: classes3.dex */
public class CustomStorageItemGenerator extends ItemGenerator {
    private final StorageLocation mStorageLocation;

    public CustomStorageItemGenerator(final Context context, final StorageLocationFileManager storageLocationFileManager, final StorageLocation storageLocation) {
        super(context, storageLocationFileManager);
        this.mStorageLocation = storageLocation;
    }
}
