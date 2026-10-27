package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.temporal.Temporal;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueMetadata {
    private final QueueCapability capabilities;
    private final Temporal.DateTime createdAt;
    private final String customerId;
    private final IdentityContext identityContext;
    private final List<ImageSizeUrlTuple> images;
    private final String queueId;
    private final List<QueueSeed> queueSeeds;
    private final ServiceTierEnum serviceTier;
    private final String serviceTierEnumValue;
    private final String thingShadowName;
    private final String title;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, CreatedAtStep, ServiceTierStep, QueueSeedsStep, CapabilitiesStep, BuildStep {
    }

    public interface CapabilitiesStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CreatedAtStep {
    }

    public interface CustomerIdStep {
    }

    public interface QueueIdStep {
    }

    public interface QueueSeedsStep {
    }

    public interface ServiceTierStep {
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public ServiceTierEnum getServiceTier() {
        return this.serviceTier;
    }

    public String getServiceTierEnumValue() {
        return this.serviceTierEnumValue;
    }

    public String getTitle() {
        return this.title;
    }

    public List<ImageSizeUrlTuple> getImages() {
        return this.images;
    }

    public List<QueueSeed> getQueueSeeds() {
        return this.queueSeeds;
    }

    public QueueCapability getCapabilities() {
        return this.capabilities;
    }

    public String getThingShadowName() {
        return this.thingShadowName;
    }

    public IdentityContext getIdentityContext() {
        return this.identityContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueMetadata.class != obj.getClass()) {
            return false;
        }
        QueueMetadata queueMetadata = (QueueMetadata) obj;
        return ObjectsCompat.equals(getCustomerId(), queueMetadata.getCustomerId()) && ObjectsCompat.equals(getQueueId(), queueMetadata.getQueueId()) && ObjectsCompat.equals(getCreatedAt(), queueMetadata.getCreatedAt()) && ObjectsCompat.equals(getServiceTier(), queueMetadata.getServiceTier()) && ObjectsCompat.equals(getServiceTierEnumValue(), queueMetadata.getServiceTierEnumValue()) && ObjectsCompat.equals(getTitle(), queueMetadata.getTitle()) && ObjectsCompat.equals(getImages(), queueMetadata.getImages()) && ObjectsCompat.equals(getQueueSeeds(), queueMetadata.getQueueSeeds()) && ObjectsCompat.equals(getCapabilities(), queueMetadata.getCapabilities()) && ObjectsCompat.equals(getThingShadowName(), queueMetadata.getThingShadowName()) && ObjectsCompat.equals(getIdentityContext(), queueMetadata.getIdentityContext());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getCreatedAt() + getServiceTier() + getServiceTierEnumValue() + getTitle() + getImages() + getQueueSeeds() + getCapabilities() + getThingShadowName() + getIdentityContext()).hashCode();
    }
}
