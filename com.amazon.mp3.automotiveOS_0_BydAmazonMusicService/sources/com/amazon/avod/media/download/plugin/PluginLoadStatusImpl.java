package com.amazon.avod.media.download.plugin;

import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.reporting.PluginErrorType;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class PluginLoadStatusImpl implements PluginLoadStatus {
    private final PluginLoadStatus.Availability mAvailability;
    private final PluginLoadStatus.CancellationReason mCancellationReason;
    private final PluginErrorType mErrorType;
    private final PluginLoadStatus.Source mSource;
    private final PluginLoadStatus.Status mStatus;

    private PluginLoadStatusImpl(PluginLoadStatus.Availability availability, PluginLoadStatus.Status status, PluginLoadStatus.Source source, PluginErrorType pluginErrorType, PluginLoadStatus.CancellationReason cancellationReason) {
        this.mAvailability = (PluginLoadStatus.Availability) Preconditions.checkNotNull(availability);
        this.mStatus = (PluginLoadStatus.Status) Preconditions.checkNotNull(status);
        this.mSource = (PluginLoadStatus.Source) Preconditions.checkNotNull(source);
        this.mErrorType = pluginErrorType;
        this.mCancellationReason = cancellationReason;
    }

    @Override // com.amazon.avod.media.download.plugin.PluginLoadStatus
    public PluginLoadStatus.Availability getAvailability() {
        return this.mAvailability;
    }

    @Override // com.amazon.avod.media.download.plugin.PluginLoadStatus
    public PluginLoadStatus.Status getStatus() {
        return this.mStatus;
    }

    @Override // com.amazon.avod.media.download.plugin.PluginLoadStatus
    public PluginLoadStatus.Source getSource() {
        return this.mSource;
    }

    @Override // com.amazon.avod.media.download.plugin.PluginLoadStatus
    public PluginErrorType getErrorType() {
        return this.mErrorType;
    }

    @Override // com.amazon.avod.media.download.plugin.PluginLoadStatus
    public PluginLoadStatus.CancellationReason getCancellationReason() {
        return this.mCancellationReason;
    }

    public String toString() {
        return String.format(Locale.US, "PluginLoadStatus(Availability=%s,Status=%s,Source=%s,Error=%s)", this.mAvailability, this.mStatus, this.mSource, this.mErrorType);
    }

    public static class Factory {
        public static PluginLoadStatus buildGenericStatus(PluginLoadStatus.Availability availability, PluginLoadStatus.Status status, PluginLoadStatus.Source source) {
            Preconditions.checkArgument(PluginLoadStatus.Status.CANCELLED != status, "Cancellation needs to be built using buildCancelledStatus");
            Preconditions.checkArgument(PluginLoadStatus.Status.ERRORED != status, "Error needs to be built using buildErroredStatus");
            return new PluginLoadStatusImpl(availability, status, source, null, null);
        }

        public static PluginLoadStatus buildErrorStatus(PluginLoadStatus.Availability availability, PluginLoadStatus.Source source, PluginErrorType pluginErrorType) {
            Preconditions.checkNotNull(pluginErrorType);
            return new PluginLoadStatusImpl(availability, PluginLoadStatus.Status.ERRORED, source, pluginErrorType, null);
        }

        public static PluginLoadStatus buildCancelledStatus(PluginLoadStatus.Availability availability, PluginLoadStatus.Source source, PluginLoadStatus.CancellationReason cancellationReason) {
            Preconditions.checkNotNull(cancellationReason);
            return new PluginLoadStatusImpl(availability, PluginLoadStatus.Status.CANCELLED, source, null, cancellationReason);
        }
    }
}
