package com.amazon.avod.media.playback.monitoring;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TimelineMonitoringTask {
    private final boolean mIsStrictMode;
    private final boolean mShouldRemoveAfterExecutingTask;
    private final long mStrictModeTrustIntervalInMillis;
    private final Runnable mTask;
    private final TimeSpan mVideoTime;

    private boolean isInRange(long j, long j2, long j3) {
        return j3 >= j && j3 < j2;
    }

    public TimelineMonitoringTask(@Nonnull Runnable runnable, @Nonnull TimeSpan timeSpan) {
        this(runnable, timeSpan, false, false, TimelineMonitoringTaskConfigs.getInstance().getStrictModeTrustIntervalInMillis());
    }

    public TimelineMonitoringTask(@Nonnull Runnable runnable, @Nonnull TimeSpan timeSpan, boolean z, boolean z2) {
        this(runnable, timeSpan, z, z2, TimelineMonitoringTaskConfigs.getInstance().getStrictModeTrustIntervalInMillis());
    }

    public TimelineMonitoringTask(@Nonnull Runnable runnable, @Nonnull TimeSpan timeSpan, boolean z, boolean z2, long j) {
        this.mTask = (Runnable) Preconditions.checkNotNull(runnable);
        this.mVideoTime = (TimeSpan) Preconditions.checkNotNull(timeSpan);
        this.mIsStrictMode = z;
        this.mShouldRemoveAfterExecutingTask = z2;
        this.mStrictModeTrustIntervalInMillis = j;
    }

    @Nonnull
    public Runnable getTask() {
        return this.mTask;
    }

    public boolean shouldRemoveAfterExecutingTask() {
        return this.mShouldRemoveAfterExecutingTask;
    }

    public boolean isExpired(long j, long j2) {
        long totalMilliseconds = this.mVideoTime.getTotalMilliseconds();
        if (this.mIsStrictMode) {
            return isInRange(j2 - this.mStrictModeTrustIntervalInMillis, j2, totalMilliseconds);
        }
        return isInRange(j, j2, totalMilliseconds);
    }

    public static class TimelineMonitoringTaskConfigs extends MediaConfigBase {
        private final ConfigurationValue<Integer> mStrictModeTrustIntervalInMillis;

        private static class InstanceHolder {
            private static final TimelineMonitoringTaskConfigs mInstance = new TimelineMonitoringTaskConfigs();

            private InstanceHolder() {
            }
        }

        public static TimelineMonitoringTaskConfigs getInstance() {
            return InstanceHolder.mInstance;
        }

        private TimelineMonitoringTaskConfigs() {
            this.mStrictModeTrustIntervalInMillis = newIntConfigValue("playback.timelinemonitor.strictmode_trustIntervalInMillis", 200);
        }

        public int getStrictModeTrustIntervalInMillis() {
            return this.mStrictModeTrustIntervalInMillis.getValue().intValue();
        }
    }
}
