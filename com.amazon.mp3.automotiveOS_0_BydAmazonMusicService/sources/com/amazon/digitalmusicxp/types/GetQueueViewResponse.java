package com.amazon.digitalmusicxp.types;

import androidx.core.app.NotificationCompat;
import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.GetQueueViewResponseStatusEnum;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetQueueViewResponse.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/digitalmusicxp/types/GetQueueViewResponse;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "entities", "Ljava/util/List;", "getEntities", "()Ljava/util/List;", "Lcom/amazon/digitalmusicxp/enums/GetQueueViewResponseStatusEnum;", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/digitalmusicxp/enums/GetQueueViewResponseStatusEnum;", "getStatus", "()Lcom/amazon/digitalmusicxp/enums/GetQueueViewResponseStatusEnum;", "<init>", "(Ljava/util/List;Lcom/amazon/digitalmusicxp/enums/GetQueueViewResponseStatusEnum;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class GetQueueViewResponse {
    private final List<GenericQueueEntity> entities;
    private final GetQueueViewResponseStatusEnum status;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetQueueViewResponse)) {
            return false;
        }
        GetQueueViewResponse getQueueViewResponse = (GetQueueViewResponse) other;
        return Intrinsics.areEqual(this.entities, getQueueViewResponse.entities) && this.status == getQueueViewResponse.status;
    }

    public int hashCode() {
        return (this.entities.hashCode() * 31) + this.status.hashCode();
    }

    public String toString() {
        return "GetQueueViewResponse(entities=" + this.entities + ", status=" + this.status + ')';
    }

    public GetQueueViewResponse(List<GenericQueueEntity> entities, GetQueueViewResponseStatusEnum status) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(status, "status");
        this.entities = entities;
        this.status = status;
        ExpectedFunctionsKt.freeze(this);
    }

    public final List<GenericQueueEntity> getEntities() {
        return this.entities;
    }
}
