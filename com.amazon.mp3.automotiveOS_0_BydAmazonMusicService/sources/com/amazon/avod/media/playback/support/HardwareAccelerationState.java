package com.amazon.avod.media.playback.support;

import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum HardwareAccelerationState {
    ENABLED_USER_TOGGLE,
    DISABLED_USER_TOGGLE,
    DISABLED_FATAL,
    DISABLED_CANARY,
    DISABLED_PERFORMANCE;

    private static final Set<String> ALL_STATES = new HashSet();

    static {
        for (HardwareAccelerationState hardwareAccelerationState : values()) {
            ALL_STATES.add(hardwareAccelerationState.name());
        }
    }

    @Nonnull
    public static ReportableString toReportableString(@Nonnull HardwareAccelerationState hardwareAccelerationState) {
        Preconditions.checkNotNull(hardwareAccelerationState, "state");
        return new ReportableString(hardwareAccelerationState.name(), ALL_STATES, "UNKNOWN");
    }
}
