package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class InitiateQueueResponse {
    private final ExperienceStateEnum experienceState;
    private final GenericQueueEntity genericQueueEntity;
    private final PlaybackInstruction playbackInstruction;
    private final QueueMetadata queueMetadata;
    private final InitiateQueueResponseContextEnum responseContext;

    public interface BuildStep {
    }

    public static class Builder implements ResponseContextStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface ResponseContextStep {
    }

    public QueueMetadata getQueueMetadata() {
        return this.queueMetadata;
    }

    public GenericQueueEntity getGenericQueueEntity() {
        return this.genericQueueEntity;
    }

    public PlaybackInstruction getPlaybackInstruction() {
        return this.playbackInstruction;
    }

    public ExperienceStateEnum getExperienceState() {
        return this.experienceState;
    }

    public InitiateQueueResponseContextEnum getResponseContext() {
        return this.responseContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || InitiateQueueResponse.class != obj.getClass()) {
            return false;
        }
        InitiateQueueResponse initiateQueueResponse = (InitiateQueueResponse) obj;
        return ObjectsCompat.equals(getQueueMetadata(), initiateQueueResponse.getQueueMetadata()) && ObjectsCompat.equals(getGenericQueueEntity(), initiateQueueResponse.getGenericQueueEntity()) && ObjectsCompat.equals(getPlaybackInstruction(), initiateQueueResponse.getPlaybackInstruction()) && ObjectsCompat.equals(getExperienceState(), initiateQueueResponse.getExperienceState()) && ObjectsCompat.equals(getResponseContext(), initiateQueueResponse.getResponseContext());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getQueueMetadata());
        sb.append(getGenericQueueEntity());
        sb.append(getPlaybackInstruction());
        sb.append(getExperienceState());
        sb.append(getResponseContext());
        return sb.toString().hashCode();
    }
}
