package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.util.DataUnit;

/* JADX INFO: loaded from: classes2.dex */
public class DiskStorageConfig extends MediaConfigBase {
    public static final DiskStorageConfig INSTANCE = new DiskStorageConfig();
    private final ConfigurationValue<Integer> gzipBufferSizeBytes = newIntConfigValue("diskStorage_gzipBufferSizeBytes", (int) DataUnit.MEGABYTES.toBytes(2.0f));

    private DiskStorageConfig() {
    }

    public int getGzipBufferSizeBytes() {
        return this.gzipBufferSizeBytes.getValue().intValue();
    }
}
