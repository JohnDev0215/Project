package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class DevicePlaybackStateProposal {
    private final DevicePlaybackStateEnum devicePlaybackState;
    private final String queueId;
    private final DevicePlaybackStateProposalReasonEnum reason;
    private final DevicePlaybackStateProposalSourceInformation source;

    public interface BuildStep {
    }

    public static class Builder implements QueueIdStep, DevicePlaybackStateStep, ReasonStep, SourceStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface DevicePlaybackStateStep {
    }

    public interface QueueIdStep {
    }

    public interface ReasonStep {
    }

    public interface SourceStep {
    }

    public String getQueueId() {
        return this.queueId;
    }

    public DevicePlaybackStateEnum getDevicePlaybackState() {
        return this.devicePlaybackState;
    }

    public DevicePlaybackStateProposalReasonEnum getReason() {
        return this.reason;
    }

    public DevicePlaybackStateProposalSourceInformation getSource() {
        return this.source;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DevicePlaybackStateProposal.class != obj.getClass()) {
            return false;
        }
        DevicePlaybackStateProposal devicePlaybackStateProposal = (DevicePlaybackStateProposal) obj;
        return ObjectsCompat.equals(getQueueId(), devicePlaybackStateProposal.getQueueId()) && ObjectsCompat.equals(getDevicePlaybackState(), devicePlaybackStateProposal.getDevicePlaybackState()) && ObjectsCompat.equals(getReason(), devicePlaybackStateProposal.getReason()) && ObjectsCompat.equals(getSource(), devicePlaybackStateProposal.getSource());
    }

    public int hashCode() {
        return (getQueueId() + getDevicePlaybackState() + getReason() + getSource()).hashCode();
    }
}
