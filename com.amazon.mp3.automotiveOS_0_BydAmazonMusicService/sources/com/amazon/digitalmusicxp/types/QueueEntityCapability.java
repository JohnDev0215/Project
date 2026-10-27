package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: QueueEntityCapability.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\rR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\rR\u0019\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\rR\u0019\u0010\u0018\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u000b\u001a\u0004\b\u0019\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "next", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "getNext", "()Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "previous", "getPrevious", "scrubForward", "getScrubForward", "scrubBackward", "getScrubBackward", "rate", "getRate", "displayable", "getDisplayable", "loopOne", "getLoopOne", "<init>", "(Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;Lcom/amazon/digitalmusicxp/types/ActionStrategy;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueEntityCapability {
    private final ActionStrategy displayable;
    private final ActionStrategy loopOne;
    private final ActionStrategy next;
    private final ActionStrategy previous;
    private final ActionStrategy rate;
    private final ActionStrategy scrubBackward;
    private final ActionStrategy scrubForward;

    public QueueEntityCapability() {
        this(null, null, null, null, null, null, null, WorkQueueKt.MASK, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueEntityCapability)) {
            return false;
        }
        QueueEntityCapability queueEntityCapability = (QueueEntityCapability) other;
        return Intrinsics.areEqual(this.next, queueEntityCapability.next) && Intrinsics.areEqual(this.previous, queueEntityCapability.previous) && Intrinsics.areEqual(this.scrubForward, queueEntityCapability.scrubForward) && Intrinsics.areEqual(this.scrubBackward, queueEntityCapability.scrubBackward) && Intrinsics.areEqual(this.rate, queueEntityCapability.rate) && Intrinsics.areEqual(this.displayable, queueEntityCapability.displayable) && Intrinsics.areEqual(this.loopOne, queueEntityCapability.loopOne);
    }

    public int hashCode() {
        ActionStrategy actionStrategy = this.next;
        int iHashCode = (actionStrategy == null ? 0 : actionStrategy.hashCode()) * 31;
        ActionStrategy actionStrategy2 = this.previous;
        int iHashCode2 = (iHashCode + (actionStrategy2 == null ? 0 : actionStrategy2.hashCode())) * 31;
        ActionStrategy actionStrategy3 = this.scrubForward;
        int iHashCode3 = (iHashCode2 + (actionStrategy3 == null ? 0 : actionStrategy3.hashCode())) * 31;
        ActionStrategy actionStrategy4 = this.scrubBackward;
        int iHashCode4 = (iHashCode3 + (actionStrategy4 == null ? 0 : actionStrategy4.hashCode())) * 31;
        ActionStrategy actionStrategy5 = this.rate;
        int iHashCode5 = (iHashCode4 + (actionStrategy5 == null ? 0 : actionStrategy5.hashCode())) * 31;
        ActionStrategy actionStrategy6 = this.displayable;
        int iHashCode6 = (iHashCode5 + (actionStrategy6 == null ? 0 : actionStrategy6.hashCode())) * 31;
        ActionStrategy actionStrategy7 = this.loopOne;
        return iHashCode6 + (actionStrategy7 != null ? actionStrategy7.hashCode() : 0);
    }

    public String toString() {
        return "QueueEntityCapability(next=" + this.next + ", previous=" + this.previous + ", scrubForward=" + this.scrubForward + ", scrubBackward=" + this.scrubBackward + ", rate=" + this.rate + ", displayable=" + this.displayable + ", loopOne=" + this.loopOne + ')';
    }

    public QueueEntityCapability(ActionStrategy actionStrategy, ActionStrategy actionStrategy2, ActionStrategy actionStrategy3, ActionStrategy actionStrategy4, ActionStrategy actionStrategy5, ActionStrategy actionStrategy6, ActionStrategy actionStrategy7) {
        this.next = actionStrategy;
        this.previous = actionStrategy2;
        this.scrubForward = actionStrategy3;
        this.scrubBackward = actionStrategy4;
        this.rate = actionStrategy5;
        this.displayable = actionStrategy6;
        this.loopOne = actionStrategy7;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ QueueEntityCapability(ActionStrategy actionStrategy, ActionStrategy actionStrategy2, ActionStrategy actionStrategy3, ActionStrategy actionStrategy4, ActionStrategy actionStrategy5, ActionStrategy actionStrategy6, ActionStrategy actionStrategy7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : actionStrategy, (i & 2) != 0 ? null : actionStrategy2, (i & 4) != 0 ? null : actionStrategy3, (i & 8) != 0 ? null : actionStrategy4, (i & 16) != 0 ? null : actionStrategy5, (i & 32) != 0 ? null : actionStrategy6, (i & 64) != 0 ? null : actionStrategy7);
    }

    public final ActionStrategy getNext() {
        return this.next;
    }

    public final ActionStrategy getPrevious() {
        return this.previous;
    }

    public final ActionStrategy getScrubForward() {
        return this.scrubForward;
    }

    public final ActionStrategy getScrubBackward() {
        return this.scrubBackward;
    }

    public final ActionStrategy getRate() {
        return this.rate;
    }

    public final ActionStrategy getDisplayable() {
        return this.displayable;
    }

    public final ActionStrategy getLoopOne() {
        return this.loopOne;
    }
}
