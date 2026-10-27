package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class CapabilitySet {
    private final List<QueueEntityCapability> capabilities;
    private final String capabilitySetId;

    public interface BuildStep {
    }

    public static class Builder implements CapabilitySetIdStep, CapabilitiesStep, BuildStep {
    }

    public interface CapabilitiesStep {
    }

    public interface CapabilitySetIdStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public String getCapabilitySetId() {
        return this.capabilitySetId;
    }

    public List<QueueEntityCapability> getCapabilities() {
        return this.capabilities;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CapabilitySet.class != obj.getClass()) {
            return false;
        }
        CapabilitySet capabilitySet = (CapabilitySet) obj;
        return ObjectsCompat.equals(getCapabilitySetId(), capabilitySet.getCapabilitySetId()) && ObjectsCompat.equals(getCapabilities(), capabilitySet.getCapabilities());
    }

    public int hashCode() {
        return (getCapabilitySetId() + getCapabilities()).hashCode();
    }
}
