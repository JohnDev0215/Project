package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultCapability {
    private final String capabilitySetId;
    private final ServiceTierEnum serviceTier;

    public interface BuildStep {
    }

    public static class Builder implements ServiceTierStep, CapabilitySetIdStep, BuildStep {
    }

    public interface CapabilitySetIdStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface ServiceTierStep {
    }

    public ServiceTierEnum getServiceTier() {
        return this.serviceTier;
    }

    public String getCapabilitySetId() {
        return this.capabilitySetId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DefaultCapability.class != obj.getClass()) {
            return false;
        }
        DefaultCapability defaultCapability = (DefaultCapability) obj;
        return ObjectsCompat.equals(getServiceTier(), defaultCapability.getServiceTier()) && ObjectsCompat.equals(getCapabilitySetId(), defaultCapability.getCapabilitySetId());
    }

    public int hashCode() {
        return (getServiceTier() + getCapabilitySetId()).hashCode();
    }
}
