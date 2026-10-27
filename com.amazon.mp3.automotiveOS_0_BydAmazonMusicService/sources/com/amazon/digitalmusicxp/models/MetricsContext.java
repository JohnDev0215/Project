package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.types.MetricsContextTuple;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MetricsContext.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001c\u001a\u00020\u0017¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u001f\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001b¨\u0006 "}, d2 = {"Lcom/amazon/digitalmusicxp/models/MetricsContext;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "queueId", "getQueueId", "id", "getId", "", "Lcom/amazon/digitalmusicxp/types/MetricsContextTuple;", "metricsContext", "Ljava/util/List;", "getMetricsContext", "()Ljava/util/List;", "", "createdAt", "J", "getCreatedAt", "()J", "updatedAt", "getUpdatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;JJ)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MetricsContext {
    private final long createdAt;
    private final String customerId;
    private final String id;
    private final List<MetricsContextTuple> metricsContext;
    private final String queueId;
    private final long updatedAt;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetricsContext)) {
            return false;
        }
        MetricsContext metricsContext = (MetricsContext) other;
        return Intrinsics.areEqual(this.customerId, metricsContext.customerId) && Intrinsics.areEqual(this.queueId, metricsContext.queueId) && Intrinsics.areEqual(this.id, metricsContext.id) && Intrinsics.areEqual(this.metricsContext, metricsContext.metricsContext) && this.createdAt == metricsContext.createdAt && this.updatedAt == metricsContext.updatedAt;
    }

    public int hashCode() {
        int iHashCode = ((((this.customerId.hashCode() * 31) + this.queueId.hashCode()) * 31) + this.id.hashCode()) * 31;
        List<MetricsContextTuple> list = this.metricsContext;
        return ((((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "MetricsContext(customerId=" + this.customerId + ", queueId=" + this.queueId + ", id=" + this.id + ", metricsContext=" + this.metricsContext + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }

    public MetricsContext(String customerId, String queueId, String id, List<MetricsContextTuple> list, long j, long j2) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(id, "id");
        this.customerId = customerId;
        this.queueId = queueId;
        this.id = id;
        this.metricsContext = list;
        this.createdAt = j;
        this.updatedAt = j2;
        ExpectedFunctionsKt.freeze(this);
    }

    public final List<MetricsContextTuple> getMetricsContext() {
        return this.metricsContext;
    }
}
