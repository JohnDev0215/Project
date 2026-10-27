package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class AddToQueueInput {
    private final String customerId;
    private final LocationInput location;
    private final String queueId;
    private final List<QueueSeedContextInput> queueSeeds;

    public interface BuildStep {
    }

    public static class Builder implements QueueIdStep, CustomerIdStep, LocationStep, QueueSeedsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public interface LocationStep {
    }

    public interface QueueIdStep {
    }

    public interface QueueSeedsStep {
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public LocationInput getLocation() {
        return this.location;
    }

    public List<QueueSeedContextInput> getQueueSeeds() {
        return this.queueSeeds;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AddToQueueInput.class != obj.getClass()) {
            return false;
        }
        AddToQueueInput addToQueueInput = (AddToQueueInput) obj;
        return ObjectsCompat.equals(getQueueId(), addToQueueInput.getQueueId()) && ObjectsCompat.equals(getCustomerId(), addToQueueInput.getCustomerId()) && ObjectsCompat.equals(getLocation(), addToQueueInput.getLocation()) && ObjectsCompat.equals(getQueueSeeds(), addToQueueInput.getQueueSeeds());
    }

    public int hashCode() {
        return (getQueueId() + getCustomerId() + getLocation() + getQueueSeeds()).hashCode();
    }
}
