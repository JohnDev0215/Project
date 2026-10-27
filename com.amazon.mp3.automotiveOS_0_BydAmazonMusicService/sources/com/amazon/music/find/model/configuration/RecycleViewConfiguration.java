package com.amazon.music.find.model.configuration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecycleViewConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/find/model/configuration/RecycleViewConfiguration;", "", "invalidateDelayedDurationInMilliseconds", "", "(Ljava/lang/Long;)V", "getInvalidateDelayedDurationInMilliseconds", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "copy", "(Ljava/lang/Long;)Lcom/amazon/music/find/model/configuration/RecycleViewConfiguration;", "equals", "", "other", "hashCode", "", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class RecycleViewConfiguration {
    private final Long invalidateDelayedDurationInMilliseconds;

    public static /* synthetic */ RecycleViewConfiguration copy$default(RecycleViewConfiguration recycleViewConfiguration, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = recycleViewConfiguration.invalidateDelayedDurationInMilliseconds;
        }
        return recycleViewConfiguration.copy(l);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Long getInvalidateDelayedDurationInMilliseconds() {
        return this.invalidateDelayedDurationInMilliseconds;
    }

    public final RecycleViewConfiguration copy(Long invalidateDelayedDurationInMilliseconds) {
        return new RecycleViewConfiguration(invalidateDelayedDurationInMilliseconds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RecycleViewConfiguration) && Intrinsics.areEqual(this.invalidateDelayedDurationInMilliseconds, ((RecycleViewConfiguration) other).invalidateDelayedDurationInMilliseconds);
    }

    public int hashCode() {
        Long l = this.invalidateDelayedDurationInMilliseconds;
        if (l == null) {
            return 0;
        }
        return l.hashCode();
    }

    public String toString() {
        return "RecycleViewConfiguration(invalidateDelayedDurationInMilliseconds=" + this.invalidateDelayedDurationInMilliseconds + ')';
    }

    public RecycleViewConfiguration(Long l) {
        this.invalidateDelayedDurationInMilliseconds = l;
    }

    public final Long getInvalidateDelayedDurationInMilliseconds() {
        return this.invalidateDelayedDurationInMilliseconds;
    }
}
