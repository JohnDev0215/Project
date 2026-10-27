package com.amazon.mp3.cloudqueue;

import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.types.EntityReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueAction;", "", "()V", "Add", "Delete", "Move", "Replace", "Shuffle", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Move;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Delete;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Replace;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Add;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Shuffle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class CloudQueueAction {
    public /* synthetic */ CloudQueueAction(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: CloudQueueAction.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Move;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction;", "fromEntityReferenceId", "", "toEntityReferenceId", "(Ljava/lang/String;Ljava/lang/String;)V", "getFromEntityReferenceId", "()Ljava/lang/String;", "getToEntityReferenceId", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Move extends CloudQueueAction {
        private final String fromEntityReferenceId;
        private final String toEntityReferenceId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Move(String fromEntityReferenceId, String toEntityReferenceId) {
            super(null);
            Intrinsics.checkNotNullParameter(fromEntityReferenceId, "fromEntityReferenceId");
            Intrinsics.checkNotNullParameter(toEntityReferenceId, "toEntityReferenceId");
            this.fromEntityReferenceId = fromEntityReferenceId;
            this.toEntityReferenceId = toEntityReferenceId;
        }

        public final String getFromEntityReferenceId() {
            return this.fromEntityReferenceId;
        }

        public final String getToEntityReferenceId() {
            return this.toEntityReferenceId;
        }
    }

    private CloudQueueAction() {
    }

    /* JADX INFO: compiled from: CloudQueueAction.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Delete;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction;", "entityReferenceId", "", "(Ljava/lang/String;)V", "getEntityReferenceId", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Delete extends CloudQueueAction {
        private final String entityReferenceId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Delete(String entityReferenceId) {
            super(null);
            Intrinsics.checkNotNullParameter(entityReferenceId, "entityReferenceId");
            this.entityReferenceId = entityReferenceId;
        }

        public final String getEntityReferenceId() {
            return this.entityReferenceId;
        }
    }

    /* JADX INFO: compiled from: CloudQueueAction.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Replace;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction;", "index", "", "queueEntity", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "(ILcom/amazon/digitalmusicxp/models/QueueEntityMetadata;)V", "getIndex", "()I", "getQueueEntity", "()Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Replace extends CloudQueueAction {
        private final int index;
        private final QueueEntityMetadata queueEntity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Replace(int i, QueueEntityMetadata queueEntity) {
            super(null);
            Intrinsics.checkNotNullParameter(queueEntity, "queueEntity");
            this.index = i;
            this.queueEntity = queueEntity;
        }

        public final int getIndex() {
            return this.index;
        }

        public final QueueEntityMetadata getQueueEntity() {
            return this.queueEntity;
        }
    }

    /* JADX INFO: compiled from: CloudQueueAction.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Add;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction;", "queueEntities", "", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "addNext", "", "(Ljava/util/List;Z)V", "getAddNext", "()Z", "getQueueEntities", "()Ljava/util/List;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Add extends CloudQueueAction {
        private final boolean addNext;
        private final List<QueueEntityMetadata> queueEntities;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Add(List<QueueEntityMetadata> queueEntities, boolean z) {
            super(null);
            Intrinsics.checkNotNullParameter(queueEntities, "queueEntities");
            this.queueEntities = queueEntities;
            this.addNext = z;
        }

        public final boolean getAddNext() {
            return this.addNext;
        }

        public final List<QueueEntityMetadata> getQueueEntities() {
            return this.queueEntities;
        }
    }

    /* JADX INFO: compiled from: CloudQueueAction.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueAction$Shuffle;", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction;", "entityReferenceList", "", "Lcom/amazon/digitalmusicxp/types/EntityReference;", "(Ljava/util/List;)V", "getEntityReferenceList", "()Ljava/util/List;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Shuffle extends CloudQueueAction {
        private final List<EntityReference> entityReferenceList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Shuffle(List<EntityReference> entityReferenceList) {
            super(null);
            Intrinsics.checkNotNullParameter(entityReferenceList, "entityReferenceList");
            this.entityReferenceList = entityReferenceList;
        }

        public final List<EntityReference> getEntityReferenceList() {
            return this.entityReferenceList;
        }
    }
}
