package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueIdTransferSeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/QueueIdTransferSeedContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "queueId", "Ljava/lang/String;", "getQueueId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/inputs/DeviceIdentifierInput;", "sourceDevice", "Lcom/amazon/digitalmusicxp/inputs/DeviceIdentifierInput;", "getSourceDevice", "()Lcom/amazon/digitalmusicxp/inputs/DeviceIdentifierInput;", "shouldManagePlayback", "Ljava/lang/Boolean;", "getShouldManagePlayback", "()Ljava/lang/Boolean;", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "metricsContext", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "getMetricsContext", "()Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/DeviceIdentifierInput;Ljava/lang/Boolean;Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueIdTransferSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String queueId;
    private final Boolean shouldManagePlayback;
    private final DeviceIdentifierInput sourceDevice;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueIdTransferSeedContextInput)) {
            return false;
        }
        QueueIdTransferSeedContextInput queueIdTransferSeedContextInput = (QueueIdTransferSeedContextInput) other;
        return Intrinsics.areEqual(this.queueId, queueIdTransferSeedContextInput.queueId) && Intrinsics.areEqual(this.sourceDevice, queueIdTransferSeedContextInput.sourceDevice) && Intrinsics.areEqual(this.shouldManagePlayback, queueIdTransferSeedContextInput.shouldManagePlayback) && Intrinsics.areEqual(this.metricsContext, queueIdTransferSeedContextInput.metricsContext);
    }

    public int hashCode() {
        int iHashCode = ((this.queueId.hashCode() * 31) + this.sourceDevice.hashCode()) * 31;
        Boolean bool = this.shouldManagePlayback;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        MetricsContextInput metricsContextInput = this.metricsContext;
        return iHashCode2 + (metricsContextInput != null ? metricsContextInput.hashCode() : 0);
    }

    public String toString() {
        return "QueueIdTransferSeedContextInput(queueId=" + this.queueId + ", sourceDevice=" + this.sourceDevice + ", shouldManagePlayback=" + this.shouldManagePlayback + ", metricsContext=" + this.metricsContext + ')';
    }

    public QueueIdTransferSeedContextInput(String queueId, DeviceIdentifierInput sourceDevice, Boolean bool, MetricsContextInput metricsContextInput) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(sourceDevice, "sourceDevice");
        this.queueId = queueId;
        this.sourceDevice = sourceDevice;
        this.shouldManagePlayback = bool;
        this.metricsContext = metricsContextInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ QueueIdTransferSeedContextInput(String str, DeviceIdentifierInput deviceIdentifierInput, Boolean bool, MetricsContextInput metricsContextInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, deviceIdentifierInput, (i & 4) != 0 ? null : bool, (i & 8) != 0 ? null : metricsContextInput);
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final DeviceIdentifierInput getSourceDevice() {
        return this.sourceDevice;
    }

    public final Boolean getShouldManagePlayback() {
        return this.shouldManagePlayback;
    }
}
