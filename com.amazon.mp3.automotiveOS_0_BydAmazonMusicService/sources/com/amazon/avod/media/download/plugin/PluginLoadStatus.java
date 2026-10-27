package com.amazon.avod.media.download.plugin;

import com.amazon.avod.media.download.plugin.reporting.PluginErrorType;
import com.google.common.collect.Sets;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public interface PluginLoadStatus {

    public enum Availability {
        UNKNOWN,
        AVAILABLE,
        UNAVAILABLE
    }

    public enum CancellationReason {
        NOT_APPLICABLE,
        NO_DATA_CONNECTION,
        CONTENT_UNAVAILABLE,
        DISABLED_FROM_SERVER,
        DISABLED_BY_USER
    }

    public enum Source {
        UNKNOWN,
        DISK,
        NETWORK
    }

    Availability getAvailability();

    CancellationReason getCancellationReason();

    PluginErrorType getErrorType();

    Source getSource();

    Status getStatus();

    public enum Status {
        UNKNOWN,
        LOADING,
        LOADED,
        CANCELLED,
        ERRORED;

        private static final Set<Status> COMPLETED_STATUSES;

        static {
            Status status = LOADED;
            Status status2 = CANCELLED;
            COMPLETED_STATUSES = Sets.newHashSet(status, ERRORED, status2);
        }

        public static boolean isCompletionStatus(Status status) {
            return COMPLETED_STATUSES.contains(status);
        }
    }
}
