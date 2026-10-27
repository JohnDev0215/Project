package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultQueueCapabilityIds.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/amazon/digitalmusicxp/types/DefaultQueueCapabilityIds;", "", "", "toString", "", "hashCode", "other", "", "equals", "unlimitedQueueCapabilityId", "Ljava/lang/String;", "getUnlimitedQueueCapabilityId", "()Ljava/lang/String;", "primeQueueCapabilityId", "getPrimeQueueCapabilityId", "libraryQueueCapabilityId", "getLibraryQueueCapabilityId", "nightwingQueueCapabilityId", "getNightwingQueueCapabilityId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class DefaultQueueCapabilityIds {
    private final String libraryQueueCapabilityId;
    private final String nightwingQueueCapabilityId;
    private final String primeQueueCapabilityId;
    private final String unlimitedQueueCapabilityId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultQueueCapabilityIds)) {
            return false;
        }
        DefaultQueueCapabilityIds defaultQueueCapabilityIds = (DefaultQueueCapabilityIds) other;
        return Intrinsics.areEqual(this.unlimitedQueueCapabilityId, defaultQueueCapabilityIds.unlimitedQueueCapabilityId) && Intrinsics.areEqual(this.primeQueueCapabilityId, defaultQueueCapabilityIds.primeQueueCapabilityId) && Intrinsics.areEqual(this.libraryQueueCapabilityId, defaultQueueCapabilityIds.libraryQueueCapabilityId) && Intrinsics.areEqual(this.nightwingQueueCapabilityId, defaultQueueCapabilityIds.nightwingQueueCapabilityId);
    }

    public int hashCode() {
        return (((((this.unlimitedQueueCapabilityId.hashCode() * 31) + this.primeQueueCapabilityId.hashCode()) * 31) + this.libraryQueueCapabilityId.hashCode()) * 31) + this.nightwingQueueCapabilityId.hashCode();
    }

    public String toString() {
        return "DefaultQueueCapabilityIds(unlimitedQueueCapabilityId=" + this.unlimitedQueueCapabilityId + ", primeQueueCapabilityId=" + this.primeQueueCapabilityId + ", libraryQueueCapabilityId=" + this.libraryQueueCapabilityId + ", nightwingQueueCapabilityId=" + this.nightwingQueueCapabilityId + ')';
    }

    public DefaultQueueCapabilityIds(String unlimitedQueueCapabilityId, String primeQueueCapabilityId, String libraryQueueCapabilityId, String nightwingQueueCapabilityId) {
        Intrinsics.checkNotNullParameter(unlimitedQueueCapabilityId, "unlimitedQueueCapabilityId");
        Intrinsics.checkNotNullParameter(primeQueueCapabilityId, "primeQueueCapabilityId");
        Intrinsics.checkNotNullParameter(libraryQueueCapabilityId, "libraryQueueCapabilityId");
        Intrinsics.checkNotNullParameter(nightwingQueueCapabilityId, "nightwingQueueCapabilityId");
        this.unlimitedQueueCapabilityId = unlimitedQueueCapabilityId;
        this.primeQueueCapabilityId = primeQueueCapabilityId;
        this.libraryQueueCapabilityId = libraryQueueCapabilityId;
        this.nightwingQueueCapabilityId = nightwingQueueCapabilityId;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getUnlimitedQueueCapabilityId() {
        return this.unlimitedQueueCapabilityId;
    }

    public final String getPrimeQueueCapabilityId() {
        return this.primeQueueCapabilityId;
    }

    public final String getLibraryQueueCapabilityId() {
        return this.libraryQueueCapabilityId;
    }
}
