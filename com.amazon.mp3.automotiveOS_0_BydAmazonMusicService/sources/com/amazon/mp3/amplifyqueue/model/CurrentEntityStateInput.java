package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.temporal.Temporal;

/* JADX INFO: loaded from: classes3.dex */
public final class CurrentEntityStateInput {
    private final String entityReferenceId;
    private final Double progressInMs;
    private final Temporal.Timestamp progressTimestamp;

    public interface BuildStep {
    }

    public static class Builder implements EntityReferenceIdStep, ProgressInMsStep, ProgressTimestampStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EntityReferenceIdStep {
    }

    public interface ProgressInMsStep {
    }

    public interface ProgressTimestampStep {
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public Double getProgressInMs() {
        return this.progressInMs;
    }

    public Temporal.Timestamp getProgressTimestamp() {
        return this.progressTimestamp;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CurrentEntityStateInput.class != obj.getClass()) {
            return false;
        }
        CurrentEntityStateInput currentEntityStateInput = (CurrentEntityStateInput) obj;
        return ObjectsCompat.equals(getEntityReferenceId(), currentEntityStateInput.getEntityReferenceId()) && ObjectsCompat.equals(getProgressInMs(), currentEntityStateInput.getProgressInMs()) && ObjectsCompat.equals(getProgressTimestamp(), currentEntityStateInput.getProgressTimestamp());
    }

    public int hashCode() {
        return (getEntityReferenceId() + getProgressInMs() + getProgressTimestamp()).hashCode();
    }
}
