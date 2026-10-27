package com.amazon.digitalmusicxp.types;

import androidx.core.app.NotificationCompat;
import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.GetNextQueueEntityResponseStatusEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityResponseTuple.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/digitalmusicxp/types/EntityResponseTuple;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "entity", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "getEntity", "()Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "Lcom/amazon/digitalmusicxp/enums/GetNextQueueEntityResponseStatusEnum;", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/digitalmusicxp/enums/GetNextQueueEntityResponseStatusEnum;", "getStatus", "()Lcom/amazon/digitalmusicxp/enums/GetNextQueueEntityResponseStatusEnum;", "<init>", "(Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;Lcom/amazon/digitalmusicxp/enums/GetNextQueueEntityResponseStatusEnum;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class EntityResponseTuple {
    private final GenericQueueEntity entity;
    private final GetNextQueueEntityResponseStatusEnum status;

    public EntityResponseTuple() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityResponseTuple)) {
            return false;
        }
        EntityResponseTuple entityResponseTuple = (EntityResponseTuple) other;
        return Intrinsics.areEqual(this.entity, entityResponseTuple.entity) && this.status == entityResponseTuple.status;
    }

    public int hashCode() {
        GenericQueueEntity genericQueueEntity = this.entity;
        int iHashCode = (genericQueueEntity == null ? 0 : genericQueueEntity.hashCode()) * 31;
        GetNextQueueEntityResponseStatusEnum getNextQueueEntityResponseStatusEnum = this.status;
        return iHashCode + (getNextQueueEntityResponseStatusEnum != null ? getNextQueueEntityResponseStatusEnum.hashCode() : 0);
    }

    public String toString() {
        return "EntityResponseTuple(entity=" + this.entity + ", status=" + this.status + ')';
    }

    public EntityResponseTuple(GenericQueueEntity genericQueueEntity, GetNextQueueEntityResponseStatusEnum getNextQueueEntityResponseStatusEnum) {
        this.entity = genericQueueEntity;
        this.status = getNextQueueEntityResponseStatusEnum;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ EntityResponseTuple(GenericQueueEntity genericQueueEntity, GetNextQueueEntityResponseStatusEnum getNextQueueEntityResponseStatusEnum, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : genericQueueEntity, (i & 2) != 0 ? null : getNextQueueEntityResponseStatusEnum);
    }

    public final GenericQueueEntity getEntity() {
        return this.entity;
    }

    public final GetNextQueueEntityResponseStatusEnum getStatus() {
        return this.status;
    }
}
