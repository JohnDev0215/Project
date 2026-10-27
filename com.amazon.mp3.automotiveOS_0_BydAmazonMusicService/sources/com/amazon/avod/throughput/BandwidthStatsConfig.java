package com.amazon.avod.throughput;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
class BandwidthStatsConfig extends MediaConfigBase {
    private final ConfigurationValue<Integer> mNumberOfSamples = newIntConfigValue("playback_BandwidthStatsNumberOfSamples", 20);
    private final ConfigurationValue<Integer> mTimeStepToCollectSampleMills = newIntConfigValue("playback_BandwidthStatsTimeStepToCollectSampleMillis", 100);
    private final ConfigurationValue<Boolean> mMonitorAllApplicationsThroughput = newBooleanConfigValue("playback_BandwidthStatsMonitorAllApplicationThroughput", false);
    private final ConfigurationValue<Integer> mSampleBottomThresholdBps = newIntConfigValue("playback_BandwidthStatsMonitorSampleBottomThresholdBps", 25000);

    static BandwidthStatsConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final BandwidthStatsConfig INSTANCE = new BandwidthStatsConfig();

        private SingletonHolder() {
        }
    }

    public int getNumberOfSamples() {
        return this.mNumberOfSamples.getValue().intValue();
    }

    public int getTimeStepToCollectSampleMills() {
        return this.mTimeStepToCollectSampleMills.getValue().intValue();
    }

    public boolean monitorAllApplicationsThroughput() {
        return this.mMonitorAllApplicationsThroughput.getValue().booleanValue();
    }

    public int getSampleBottomThresholdBps() {
        return this.mSampleBottomThresholdBps.getValue().intValue();
    }
}
