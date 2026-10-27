package com.amazon.avod.media.download.plugin;

import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.PluginLoadStatusImpl;
import com.amazon.avod.media.download.plugin.reporting.PluginErrorType;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class PluginResponseHolder {
    public static final Set<PluginLoadStatus.Status> ACCEPTED_EXIT_STATUSES;
    public static final Set<PluginLoadStatus.Status> ACCEPTED_LOAD_STATUSES;
    private PluginLoadStatus.Availability mAvailability;
    private PluginLoadStatus.CancellationReason mCancellationReason;
    private PluginErrorType mErrorType;
    private PluginLoadStatus.Source mLoadSource;
    private PluginLoadStatus.Status mLoadStatus;

    static {
        PluginLoadStatus.Status status = PluginLoadStatus.Status.UNKNOWN;
        PluginLoadStatus.Status status2 = PluginLoadStatus.Status.LOADING;
        PluginLoadStatus.Status status3 = PluginLoadStatus.Status.LOADED;
        PluginLoadStatus.Status status4 = PluginLoadStatus.Status.ERRORED;
        PluginLoadStatus.Status status5 = PluginLoadStatus.Status.CANCELLED;
        ACCEPTED_LOAD_STATUSES = ImmutableSet.of(status, status2, status3, status4, status5);
        ACCEPTED_EXIT_STATUSES = ImmutableSet.of(status3, status4, status5);
    }

    public PluginResponseHolder() {
        reset();
    }

    public void reset() {
        this.mLoadStatus = PluginLoadStatus.Status.UNKNOWN;
        this.mLoadSource = PluginLoadStatus.Source.UNKNOWN;
        this.mAvailability = PluginLoadStatus.Availability.UNKNOWN;
        this.mErrorType = null;
        this.mCancellationReason = null;
    }

    public void setPluginAvailability(PluginLoadStatus.Availability availability) {
        Preconditions.checkNotNull(availability);
        this.mAvailability = availability;
    }

    public void setPluginSource(PluginLoadStatus.Source source) {
        Preconditions.checkNotNull(source);
        this.mLoadSource = source;
    }

    public void transitionToStatus(PluginLoadStatus.Status status) {
        Preconditions.checkArgument(PluginLoadStatus.Status.ERRORED != status, "Cannot transition to Errored state with this method. Use transitionToError instead");
        Preconditions.checkArgument(PluginLoadStatus.Status.CANCELLED != status, "Cannot transition to Cancelled state with this method. Use transitionToCancelled instead");
        transitionToStatusInner(status);
    }

    public void transitionToCancelled(PluginLoadStatus.CancellationReason cancellationReason) {
        Preconditions.checkNotNull(cancellationReason);
        this.mCancellationReason = cancellationReason;
        transitionToStatusInner(PluginLoadStatus.Status.CANCELLED);
    }

    public void transitionToError(PluginErrorType pluginErrorType) {
        Preconditions.checkNotNull(pluginErrorType);
        this.mErrorType = pluginErrorType;
        transitionToStatusInner(PluginLoadStatus.Status.ERRORED);
    }

    private void transitionToStatusInner(PluginLoadStatus.Status status) {
        Preconditions.checkArgument(ACCEPTED_LOAD_STATUSES.contains(status), "Unexpected load status encountered %s", status);
        validateTransition(status);
        this.mLoadStatus = status;
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.download.plugin.PluginResponseHolder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status;

        static {
            int[] iArr = new int[PluginLoadStatus.Status.values().length];
            $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status = iArr;
            try {
                iArr[PluginLoadStatus.Status.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status[PluginLoadStatus.Status.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status[PluginLoadStatus.Status.LOADED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status[PluginLoadStatus.Status.ERRORED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status[PluginLoadStatus.Status.CANCELLED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void validateTransition(PluginLoadStatus.Status status) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status[this.mLoadStatus.ordinal()];
        if (i == 1) {
            Preconditions.checkState(PluginLoadStatus.Status.LOADING == status, "Unknown state must transition to loading state, can't transition to %s", status);
            return;
        }
        if (i == 2) {
            Preconditions.checkState(ACCEPTED_EXIT_STATUSES.contains(status), "Loading must transition to an exit state, can't transition to %s", status);
        } else if (i == 3 || i == 4 || i == 5) {
            Preconditions.checkState(false, "Can't transition away from %s", (Object) this.mLoadStatus);
        }
    }

    public PluginLoadStatus.Availability getAvailability() {
        return this.mAvailability;
    }

    public PluginLoadStatus.Status getLoadStatus() {
        return this.mLoadStatus;
    }

    public PluginLoadStatus.Source getLoadSource() {
        return this.mLoadSource;
    }

    public PluginErrorType getErrorType() {
        return this.mErrorType;
    }

    public PluginLoadStatus.CancellationReason getCancellationReason() {
        return this.mCancellationReason;
    }

    protected final PluginLoadStatus deriveFinishedResult() {
        return deriveResult(true);
    }

    private PluginLoadStatus deriveResult(boolean z) {
        return deriveLoadStatus(getLoadStatus(), getLoadSource(), getAvailability(), getErrorType(), getCancellationReason(), z);
    }

    private PluginLoadStatus deriveLoadStatus(PluginLoadStatus.Status status, PluginLoadStatus.Source source, PluginLoadStatus.Availability availability, PluginErrorType pluginErrorType, PluginLoadStatus.CancellationReason cancellationReason, boolean z) {
        boolean z2 = z && !PluginLoadStatus.Status.isCompletionStatus(status);
        if (z2) {
            status = PluginLoadStatus.Status.ERRORED;
        }
        if (z2) {
            pluginErrorType = PluginErrorType.RUNTIME;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$Status[status.ordinal()];
        if (i == 4) {
            return PluginLoadStatusImpl.Factory.buildErrorStatus(availability, source, pluginErrorType);
        }
        if (i == 5) {
            return PluginLoadStatusImpl.Factory.buildCancelledStatus(availability, source, cancellationReason);
        }
        return PluginLoadStatusImpl.Factory.buildGenericStatus(availability, status, source);
    }
}
