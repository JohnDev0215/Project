package com.amazon.digitalmusicxp.types;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Setting.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\n\u001a\u0004\b\u001b\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/amazon/digitalmusicxp/types/Setting;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "maxEntitiesPerQueueSequenceSlice", "I", "getMaxEntitiesPerQueueSequenceSlice", "()I", "maxQueueSize", "getMaxQueueSize", "queueTtlInSeconds", "getQueueTtlInSeconds", "Lcom/amazon/digitalmusicxp/types/DefaultQueueCapabilityIds;", "defaultQueueCapabilityIds", "Lcom/amazon/digitalmusicxp/types/DefaultQueueCapabilityIds;", "getDefaultQueueCapabilityIds", "()Lcom/amazon/digitalmusicxp/types/DefaultQueueCapabilityIds;", "defaultQueueEntityCapabilitiesId", "getDefaultQueueEntityCapabilitiesId", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Setting {
    private final DefaultQueueCapabilityIds defaultQueueCapabilityIds;
    private final String defaultQueueEntityCapabilitiesId;
    private final String id;
    private final int maxEntitiesPerQueueSequenceSlice;
    private final int maxQueueSize;
    private final int queueTtlInSeconds;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Setting)) {
            return false;
        }
        Setting setting = (Setting) other;
        return Intrinsics.areEqual(this.id, setting.id) && this.maxEntitiesPerQueueSequenceSlice == setting.maxEntitiesPerQueueSequenceSlice && this.maxQueueSize == setting.maxQueueSize && this.queueTtlInSeconds == setting.queueTtlInSeconds && Intrinsics.areEqual(this.defaultQueueCapabilityIds, setting.defaultQueueCapabilityIds) && Intrinsics.areEqual(this.defaultQueueEntityCapabilitiesId, setting.defaultQueueEntityCapabilitiesId);
    }

    public int hashCode() {
        return (((((((((this.id.hashCode() * 31) + Integer.hashCode(this.maxEntitiesPerQueueSequenceSlice)) * 31) + Integer.hashCode(this.maxQueueSize)) * 31) + Integer.hashCode(this.queueTtlInSeconds)) * 31) + this.defaultQueueCapabilityIds.hashCode()) * 31) + this.defaultQueueEntityCapabilitiesId.hashCode();
    }

    public String toString() {
        return "Setting(id=" + this.id + ", maxEntitiesPerQueueSequenceSlice=" + this.maxEntitiesPerQueueSequenceSlice + ", maxQueueSize=" + this.maxQueueSize + ", queueTtlInSeconds=" + this.queueTtlInSeconds + ", defaultQueueCapabilityIds=" + this.defaultQueueCapabilityIds + ", defaultQueueEntityCapabilitiesId=" + this.defaultQueueEntityCapabilitiesId + ')';
    }

    public final int getMaxEntitiesPerQueueSequenceSlice() {
        return this.maxEntitiesPerQueueSequenceSlice;
    }

    public final int getMaxQueueSize() {
        return this.maxQueueSize;
    }

    public final int getQueueTtlInSeconds() {
        return this.queueTtlInSeconds;
    }

    public final DefaultQueueCapabilityIds getDefaultQueueCapabilityIds() {
        return this.defaultQueueCapabilityIds;
    }

    public final String getDefaultQueueEntityCapabilitiesId() {
        return this.defaultQueueEntityCapabilitiesId;
    }
}
