package com.amazon.avod.playback.sye.trickplay;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.BetaConfigProvider;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeTrickplayConfig extends MediaConfigBase {
    private final TimeConfigurationValue mDurationSafetyThreshold;
    private final ConfigurationValue<Boolean> mIsSyeTrickplayBetaEnabled;
    private final ConfigurationValue<Boolean> mIsSyeTrickplayEnabled;
    private final TimeConfigurationValue mMinImageFidelityThreshold;
    private final TimeConfigurationValue mSyeTrickplayMaxDuration;

    public static SyeTrickplayConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        public static final SyeTrickplayConfig INSTANCE = new SyeTrickplayConfig();

        private SingletonHolder() {
        }
    }

    private SyeTrickplayConfig() {
        this.mIsSyeTrickplayEnabled = newBooleanConfigValue("sye_isTrickplayEnabled", true);
        this.mIsSyeTrickplayBetaEnabled = newBooleanConfigValue("sye_isSyeTrickplayBetaEnabled", true);
        this.mMinImageFidelityThreshold = newTimeConfigurationValue("sye_minImageFidelityThresholdMillis", TimeSpan.fromMilliseconds(10000L), TimeUnit.MILLISECONDS);
        this.mDurationSafetyThreshold = newTimeConfigurationValue("sye_durationSafetyThresholdSeconds", TimeSpan.fromHours(2L), TimeUnit.SECONDS);
        this.mSyeTrickplayMaxDuration = newTimeConfigurationValue("sye_trickplayMaxDurationHours", TimeSpan.fromHours(6L), TimeUnit.HOURS);
    }

    public boolean isSyeTrickplayEnabled() {
        if (BetaConfigProvider.getInstance().provideBetaConfig().isBeta()) {
            return this.mIsSyeTrickplayBetaEnabled.getValue().booleanValue();
        }
        return this.mIsSyeTrickplayEnabled.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getMinImageFidelityThreshold() {
        return this.mMinImageFidelityThreshold.getValue();
    }

    @Nonnull
    public TimeSpan getDurationSafetyThreshold() {
        return this.mDurationSafetyThreshold.getValue();
    }

    @Nonnull
    public TimeSpan getMaxDuration() {
        return this.mSyeTrickplayMaxDuration.getValue();
    }
}
