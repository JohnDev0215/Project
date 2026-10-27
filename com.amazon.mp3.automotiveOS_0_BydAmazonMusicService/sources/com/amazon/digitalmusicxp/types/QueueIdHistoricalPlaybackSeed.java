package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueIdHistoricalPlaybackSeed.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/digitalmusicxp/types/QueueIdHistoricalPlaybackSeed;", "", "", "toString", "", "hashCode", "other", "", "equals", "queueId", "Ljava/lang/String;", "getQueueId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueIdHistoricalPlaybackSeed {
    private final String queueId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof QueueIdHistoricalPlaybackSeed) && Intrinsics.areEqual(this.queueId, ((QueueIdHistoricalPlaybackSeed) other).queueId);
    }

    public int hashCode() {
        return this.queueId.hashCode();
    }

    public String toString() {
        return "QueueIdHistoricalPlaybackSeed(queueId=" + this.queueId + ')';
    }

    public QueueIdHistoricalPlaybackSeed(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        this.queueId = queueId;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getQueueId() {
        return this.queueId;
    }
}
