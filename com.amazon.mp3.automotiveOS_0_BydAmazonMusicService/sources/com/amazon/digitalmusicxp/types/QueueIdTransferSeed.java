package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueIdTransferSeed.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/digitalmusicxp/types/QueueIdTransferSeed;", "", "", "toString", "", "hashCode", "other", "", "equals", "queueId", "Ljava/lang/String;", "getQueueId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/types/DeviceIdentifier;", "sourceDevice", "Lcom/amazon/digitalmusicxp/types/DeviceIdentifier;", "getSourceDevice", "()Lcom/amazon/digitalmusicxp/types/DeviceIdentifier;", "shouldManagePlayback", "Ljava/lang/Boolean;", "getShouldManagePlayback", "()Ljava/lang/Boolean;", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/DeviceIdentifier;Ljava/lang/Boolean;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueIdTransferSeed {
    private final String queueId;
    private final Boolean shouldManagePlayback;
    private final DeviceIdentifier sourceDevice;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueIdTransferSeed)) {
            return false;
        }
        QueueIdTransferSeed queueIdTransferSeed = (QueueIdTransferSeed) other;
        return Intrinsics.areEqual(this.queueId, queueIdTransferSeed.queueId) && Intrinsics.areEqual(this.sourceDevice, queueIdTransferSeed.sourceDevice) && Intrinsics.areEqual(this.shouldManagePlayback, queueIdTransferSeed.shouldManagePlayback);
    }

    public int hashCode() {
        int iHashCode = ((this.queueId.hashCode() * 31) + this.sourceDevice.hashCode()) * 31;
        Boolean bool = this.shouldManagePlayback;
        return iHashCode + (bool == null ? 0 : bool.hashCode());
    }

    public String toString() {
        return "QueueIdTransferSeed(queueId=" + this.queueId + ", sourceDevice=" + this.sourceDevice + ", shouldManagePlayback=" + this.shouldManagePlayback + ')';
    }

    public QueueIdTransferSeed(String queueId, DeviceIdentifier sourceDevice, Boolean bool) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(sourceDevice, "sourceDevice");
        this.queueId = queueId;
        this.sourceDevice = sourceDevice;
        this.shouldManagePlayback = bool;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final DeviceIdentifier getSourceDevice() {
        return this.sourceDevice;
    }

    public final Boolean getShouldManagePlayback() {
        return this.shouldManagePlayback;
    }
}
