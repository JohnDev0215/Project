package com.amazon.avod.playbackresource;

import com.amazon.atvplaybackresource.TimeShift;
import com.amazon.atvplaybackresource.TimeShiftChild;
import com.amazon.avod.content.urlvending.TimeShiftPolicy;
import com.amazon.avod.media.framework.config.TimeShiftPolicyConfig;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class TimeShiftPolicyTransformer {
    private boolean mIsPlayPauseControlEnabled;
    private boolean mIsPlayerControlEnabled;
    private boolean mIsSeekBackwardEnabled;
    private boolean mIsSeekForwardEnabled;

    public TimeShiftPolicyTransformer() {
        this(TimeShiftPolicyConfig.getInstance());
    }

    TimeShiftPolicyTransformer(@Nonnull TimeShiftPolicyConfig timeShiftPolicyConfig) {
        this.mIsPlayerControlEnabled = timeShiftPolicyConfig.isPlayerControlEnabled();
        this.mIsPlayPauseControlEnabled = timeShiftPolicyConfig.isPlayPauseEnabled();
        this.mIsSeekBackwardEnabled = timeShiftPolicyConfig.isSeekBackwardEnabled();
        this.mIsSeekForwardEnabled = timeShiftPolicyConfig.isSeekForwardEnabled();
    }

    @Nonnull
    public TimeShiftPolicy transform(@Nonnull Optional<TimeShift> optional) {
        if (optional.isPresent()) {
            this.mIsPlayPauseControlEnabled = transformPolicy(optional.get().pause.orNull()).isEnabled();
            this.mIsSeekForwardEnabled = transformPolicy(optional.get().forward.orNull()).isEnabled();
            this.mIsSeekBackwardEnabled = transformPolicy(optional.get().backward.orNull()).isEnabled();
        }
        return new TimeShiftPolicy(isPlayerControlEnabled(), isPlayPauseControlEnabled(), isSeekBackwardEnabled(), isSeekForwardEnabled());
    }

    private static class ControlPolicy {
        private final boolean mEnabled;

        ControlPolicy() {
            this.mEnabled = true;
        }

        ControlPolicy(boolean z) {
            this.mEnabled = z;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }
    }

    @Nonnull
    private ControlPolicy transformPolicy(@Nullable TimeShiftChild timeShiftChild) {
        if (timeShiftChild != null) {
            return new ControlPolicy(timeShiftChild.enabled.or(Boolean.TRUE).booleanValue());
        }
        return new ControlPolicy();
    }

    private boolean isPlayerControlEnabled() {
        return this.mIsPlayerControlEnabled;
    }

    boolean isPlayPauseControlEnabled() {
        return isPlayerControlEnabled() && this.mIsPlayPauseControlEnabled;
    }

    boolean isSeekBackwardEnabled() {
        return isPlayerControlEnabled() && this.mIsSeekBackwardEnabled;
    }

    boolean isSeekForwardEnabled() {
        return isPlayerControlEnabled() && this.mIsSeekForwardEnabled;
    }
}
