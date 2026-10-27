package com.amazon.avod.playback.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.BetaConfigProvider;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerLifecycleConfig extends MediaConfigBase {
    private final ConfigurationValue<Integer> mDAGMaximumConcurrentTasks;
    private final TimeConfigurationValue mDAGTerminationTimeout;
    private final ConfigurationValue<Boolean> mEnforceWaitUntilDAGTermination;
    private final ConfigurationValue<Boolean> mIsDAGBasedPlaybackEnabled;
    private final ConfigurationValue<Boolean> mIsDAGBasedPlaybackEnabledBeta;
    private final ConfigurationValue<Boolean> mIsDAGBasedPlaybackEnabledForLive;
    private final ConfigurationValue<Boolean> mIsDAGBasedPlaybackEnabledHardOff;
    private final ConfigurationValue<Boolean> mIsDAGBasedPlaybackWeblabEnabled;

    private static final class SingletonHolder {
        private static final PlayerLifecycleConfig INSTANCE = new PlayerLifecycleConfig();

        private SingletonHolder() {
        }
    }

    public static PlayerLifecycleConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlayerLifecycleConfig() {
        this.mIsDAGBasedPlaybackEnabled = newBooleanConfigValue("playback_isDependencyGraphLifecycleEnabled", false);
        this.mIsDAGBasedPlaybackEnabledBeta = newBooleanConfigValue("playback_isDependencyGraphLifecycleEnabledBeta", true);
        this.mIsDAGBasedPlaybackEnabledHardOff = newBooleanConfigValue("playback_isDependencyGraphLifecycleEnabled_hardoff_2", false);
        this.mIsDAGBasedPlaybackWeblabEnabled = newBooleanConfigValue("playback_isDAGBasedPlaybackWeblabEnabled", false);
        this.mDAGMaximumConcurrentTasks = newIntConfigValue("playback_DAGMaximumConcurrentTasks", 10);
        this.mIsDAGBasedPlaybackEnabledForLive = newBooleanConfigValue("playback_isDependencyGraphLifecycleEnabledForLive", false);
        this.mDAGTerminationTimeout = newTimeConfigurationValue("playback_DAGTerminationTimeoutMillis", TimeSpan.fromMilliseconds(20000L), TimeUnit.MILLISECONDS);
        this.mEnforceWaitUntilDAGTermination = newBooleanConfigValue("playback_enforceWaitUntilDAGTermination", true);
    }

    public int getDAGMaximumConcurrentTasks() {
        return this.mDAGMaximumConcurrentTasks.getValue().intValue();
    }

    public boolean shouldEnforceWaitUntilDAGTermination() {
        return this.mEnforceWaitUntilDAGTermination.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getDAGTerminationTimeout() {
        return this.mDAGTerminationTimeout.getValue();
    }

    public boolean isDAGBasedPlaybackEnabled(boolean z) {
        if (this.mIsDAGBasedPlaybackEnabledHardOff.getValue().booleanValue()) {
            return false;
        }
        if (z) {
            return isDAGBasedPlaybackEnabledForLive();
        }
        return isDAGPlaybackEnabledForVOD() || isDAGPlaybackEnabledInBetaForVOD();
    }

    private boolean isDAGBasedPlaybackEnabledForLive() {
        return this.mIsDAGBasedPlaybackEnabledForLive.getValue().booleanValue();
    }

    private boolean isDAGPlaybackEnabledForVOD() {
        MobileWeblab mobileWeblab;
        if (this.mIsDAGBasedPlaybackWeblabEnabled.getValue().booleanValue() && (mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_331571")) != null) {
            mobileWeblab.trigger();
            return mobileWeblab.getCurrentTreatment().equals(WeblabTreatment.T1);
        }
        return this.mIsDAGBasedPlaybackEnabled.getValue().booleanValue();
    }

    private boolean isDAGPlaybackEnabledInBetaForVOD() {
        return this.mIsDAGBasedPlaybackEnabledBeta.getValue().booleanValue() && BetaConfigProvider.getInstance().provideBetaConfig().isBeta();
    }
}
