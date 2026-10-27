package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultQueueCapabilityIds {
    private final String libraryQueueCapabilityId;
    private final String nightwingQueueCapabilityId;
    private final String primeQueueCapabilityId;
    private final String unlimitedQueueCapabilityId;

    public interface BuildStep {
    }

    public static class Builder implements UnlimitedQueueCapabilityIdStep, PrimeQueueCapabilityIdStep, LibraryQueueCapabilityIdStep, NightwingQueueCapabilityIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface LibraryQueueCapabilityIdStep {
    }

    public interface NightwingQueueCapabilityIdStep {
    }

    public interface PrimeQueueCapabilityIdStep {
    }

    public interface UnlimitedQueueCapabilityIdStep {
    }

    public String getUnlimitedQueueCapabilityId() {
        return this.unlimitedQueueCapabilityId;
    }

    public String getPrimeQueueCapabilityId() {
        return this.primeQueueCapabilityId;
    }

    public String getLibraryQueueCapabilityId() {
        return this.libraryQueueCapabilityId;
    }

    public String getNightwingQueueCapabilityId() {
        return this.nightwingQueueCapabilityId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DefaultQueueCapabilityIds.class != obj.getClass()) {
            return false;
        }
        DefaultQueueCapabilityIds defaultQueueCapabilityIds = (DefaultQueueCapabilityIds) obj;
        return ObjectsCompat.equals(getUnlimitedQueueCapabilityId(), defaultQueueCapabilityIds.getUnlimitedQueueCapabilityId()) && ObjectsCompat.equals(getPrimeQueueCapabilityId(), defaultQueueCapabilityIds.getPrimeQueueCapabilityId()) && ObjectsCompat.equals(getLibraryQueueCapabilityId(), defaultQueueCapabilityIds.getLibraryQueueCapabilityId()) && ObjectsCompat.equals(getNightwingQueueCapabilityId(), defaultQueueCapabilityIds.getNightwingQueueCapabilityId());
    }

    public int hashCode() {
        return (getUnlimitedQueueCapabilityId() + getPrimeQueueCapabilityId() + getLibraryQueueCapabilityId() + getNightwingQueueCapabilityId()).hashCode();
    }
}
