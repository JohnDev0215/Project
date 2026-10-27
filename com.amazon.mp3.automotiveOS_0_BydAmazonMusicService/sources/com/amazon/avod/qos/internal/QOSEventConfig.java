package com.amazon.avod.qos.internal;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.events.batch.BatchedEventConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DataUnit;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class QOSEventConfig extends MediaConfigBase implements BatchedEventConfig {
    public static final QOSEventConfig INSTANCE = new QOSEventConfig();
    private boolean mShouldForceProcess = false;
    private final ConfigurationValue<Float> mMaxBatchDataSizeKb = newFloatConfigValue("qos_maximumBatchDataSizeKb", DataUnit.MEGABYTES.toKiloBytes(2.0f));
    private final ConfigurationValue<Integer> mMaxBatchChildCount = newIntConfigValue("qos_maximumBatchUploadSize", 30);
    private final ConfigurationValue<Long> mMaximumBatchAgeMillis = newLongConfigValue("qos_maximumBatchAgeMillis", TimeUnit.HOURS.toMillis(1));
    private final ConfigurationValue<Integer> mMaximumPolicyRetries = newIntConfigValue("qos_maximumBatchUploadRetries", 3);
    private final ConfigurationValue<Boolean> mSendBatchEventsOverWAN = newBooleanConfigValue("qos_sendBatchEventsOverWAN", false);

    private QOSEventConfig() {
    }

    @Override // com.amazon.avod.events.batch.BatchedEventConfig
    public float getMaxBatchDataSizeKb() {
        return this.mMaxBatchDataSizeKb.getValue().floatValue();
    }

    @Override // com.amazon.avod.events.batch.BatchedEventConfig
    public int getMaxBatchChildCount() {
        return this.mMaxBatchChildCount.getValue().intValue();
    }

    public int getMaxRetries() {
        return this.mMaximumPolicyRetries.getValue().intValue();
    }
}
