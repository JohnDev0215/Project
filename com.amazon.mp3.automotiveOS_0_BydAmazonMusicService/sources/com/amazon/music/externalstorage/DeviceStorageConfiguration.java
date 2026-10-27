package com.amazon.music.externalstorage;

import android.content.Context;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceStorageConfiguration implements StorageConfigurationProvider {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) DeviceStorageConfiguration.class);
    private final Context context;
    private final String mediaType;
    private StorageLocationFileManager.StorageConfiguration storageConfiguration;

    public DeviceStorageConfiguration(Context context, String str) {
        this.context = context;
        this.mediaType = str;
    }

    @Override // com.amazon.music.externalstorage.StorageConfigurationProvider
    public StorageLocationFileManager.StorageConfiguration getStorageConfiguration() {
        if (this.storageConfiguration == null) {
            this.storageConfiguration = getDeviceStorageConfiguration();
        }
        return this.storageConfiguration;
    }

    private synchronized StorageLocationFileManager.StorageConfiguration getDeviceStorageConfiguration() {
        StorageLocationFileManager.StorageConfiguration storageConfiguration;
        if (StorageUtil.canDeviceWriteToSdCard(this.context, this.mediaType)) {
            storageConfiguration = StorageLocationFileManager.StorageConfiguration.EXTERNAL_PUBLIC_SUPPORTED;
        } else {
            storageConfiguration = StorageLocationFileManager.StorageConfiguration.EXTERNAL_PUBLIC_NOT_SUPPORTED;
        }
        LOG.debug("getDeviceStorageConfiguration: " + storageConfiguration);
        return storageConfiguration;
    }
}
