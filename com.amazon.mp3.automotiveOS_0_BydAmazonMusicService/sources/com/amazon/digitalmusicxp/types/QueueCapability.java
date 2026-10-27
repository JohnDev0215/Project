package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueCapability.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\t\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\rR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/amazon/digitalmusicxp/types/QueueCapability;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "hideUpcomingEntities", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "getHideUpcomingEntities", "()Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "blockQueueMutations", "getBlockQueueMutations", "shuffle", "getShuffle", "loopAll", "getLoopAll", "djMode", "getDjMode", "<init>", "(Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueCapability {
    private final ActionStrategy blockQueueMutations;
    private final ActionStrategy djMode;
    private final ActionStrategy hideUpcomingEntities;
    private final ActionStrategy loopAll;
    private final ActionStrategy shuffle;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueCapability)) {
            return false;
        }
        QueueCapability queueCapability = (QueueCapability) other;
        return Intrinsics.areEqual(this.hideUpcomingEntities, queueCapability.hideUpcomingEntities) && Intrinsics.areEqual(this.blockQueueMutations, queueCapability.blockQueueMutations) && Intrinsics.areEqual(this.shuffle, queueCapability.shuffle) && Intrinsics.areEqual(this.loopAll, queueCapability.loopAll) && Intrinsics.areEqual(this.djMode, queueCapability.djMode);
    }

    public int hashCode() {
        int iHashCode = ((((((this.hideUpcomingEntities.hashCode() * 31) + this.blockQueueMutations.hashCode()) * 31) + this.shuffle.hashCode()) * 31) + this.loopAll.hashCode()) * 31;
        ActionStrategy actionStrategy = this.djMode;
        return iHashCode + (actionStrategy == null ? 0 : actionStrategy.hashCode());
    }

    public String toString() {
        return "QueueCapability(hideUpcomingEntities=" + this.hideUpcomingEntities + ", blockQueueMutations=" + this.blockQueueMutations + ", shuffle=" + this.shuffle + ", loopAll=" + this.loopAll + ", djMode=" + this.djMode + ')';
    }

    public QueueCapability(ActionStrategy hideUpcomingEntities, ActionStrategy blockQueueMutations, ActionStrategy shuffle, ActionStrategy loopAll, ActionStrategy actionStrategy) {
        Intrinsics.checkNotNullParameter(hideUpcomingEntities, "hideUpcomingEntities");
        Intrinsics.checkNotNullParameter(blockQueueMutations, "blockQueueMutations");
        Intrinsics.checkNotNullParameter(shuffle, "shuffle");
        Intrinsics.checkNotNullParameter(loopAll, "loopAll");
        this.hideUpcomingEntities = hideUpcomingEntities;
        this.blockQueueMutations = blockQueueMutations;
        this.shuffle = shuffle;
        this.loopAll = loopAll;
        this.djMode = actionStrategy;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ QueueCapability(ActionStrategy actionStrategy, ActionStrategy actionStrategy2, ActionStrategy actionStrategy3, ActionStrategy actionStrategy4, ActionStrategy actionStrategy5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(actionStrategy, actionStrategy2, actionStrategy3, actionStrategy4, (i & 16) != 0 ? null : actionStrategy5);
    }

    public final ActionStrategy getHideUpcomingEntities() {
        return this.hideUpcomingEntities;
    }

    public final ActionStrategy getBlockQueueMutations() {
        return this.blockQueueMutations;
    }

    public final ActionStrategy getShuffle() {
        return this.shuffle;
    }

    public final ActionStrategy getLoopAll() {
        return this.loopAll;
    }
}
