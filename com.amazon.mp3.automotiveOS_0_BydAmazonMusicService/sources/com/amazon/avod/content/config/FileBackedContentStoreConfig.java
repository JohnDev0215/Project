package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class FileBackedContentStoreConfig extends MediaConfigBase {
    public static final FileBackedContentStoreConfig INSTANCE = new FileBackedContentStoreConfig();
    private final ConfigurationValue<Boolean> mShouldStoreCompressedManifest = newBooleanConfigValue("fileBackedContentStore_storeCompressedManifest", true);
    private final ConfigurationValue<Boolean> mShouldDownloadDeleteEntireFolder = newBooleanConfigValue("fileBackedContentStore_shouldDownloadDeleteEntireFolder", true);

    private FileBackedContentStoreConfig() {
    }

    public boolean shouldStoreCompressedManifest() {
        return this.mShouldStoreCompressedManifest.getValue().booleanValue();
    }

    public boolean shouldDownloadDeleteEntireFolder() {
        return this.mShouldDownloadDeleteEntireFolder.getValue().booleanValue();
    }
}
