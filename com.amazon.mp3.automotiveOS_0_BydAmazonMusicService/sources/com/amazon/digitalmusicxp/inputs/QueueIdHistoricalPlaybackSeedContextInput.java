package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueIdHistoricalPlaybackSeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/QueueIdHistoricalPlaybackSeedContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "queueId", "Ljava/lang/String;", "getQueueId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "metricsContext", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "getMetricsContext", "()Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueIdHistoricalPlaybackSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String queueId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueIdHistoricalPlaybackSeedContextInput)) {
            return false;
        }
        QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput = (QueueIdHistoricalPlaybackSeedContextInput) other;
        return Intrinsics.areEqual(this.queueId, queueIdHistoricalPlaybackSeedContextInput.queueId) && Intrinsics.areEqual(this.metricsContext, queueIdHistoricalPlaybackSeedContextInput.metricsContext);
    }

    public int hashCode() {
        int iHashCode = this.queueId.hashCode() * 31;
        MetricsContextInput metricsContextInput = this.metricsContext;
        return iHashCode + (metricsContextInput == null ? 0 : metricsContextInput.hashCode());
    }

    public String toString() {
        return "QueueIdHistoricalPlaybackSeedContextInput(queueId=" + this.queueId + ", metricsContext=" + this.metricsContext + ')';
    }

    public QueueIdHistoricalPlaybackSeedContextInput(String queueId, MetricsContextInput metricsContextInput) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        this.queueId = queueId;
        this.metricsContext = metricsContextInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ QueueIdHistoricalPlaybackSeedContextInput(String str, MetricsContextInput metricsContextInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : metricsContextInput);
    }

    public final String getQueueId() {
        return this.queueId;
    }
}
