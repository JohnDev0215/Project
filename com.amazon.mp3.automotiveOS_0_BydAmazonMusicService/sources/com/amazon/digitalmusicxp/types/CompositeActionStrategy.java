package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.CompositeActionStrategyActionTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompositeActionStrategy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/digitalmusicxp/types/CompositeActionStrategy;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/CompositeActionStrategyActionTypeEnum;", "actionType", "Lcom/amazon/digitalmusicxp/enums/CompositeActionStrategyActionTypeEnum;", "getActionType", "()Lcom/amazon/digitalmusicxp/enums/CompositeActionStrategyActionTypeEnum;", "Lcom/amazon/digitalmusicxp/types/SkipLimitIOTAction;", "skipLimitIOTAction", "Lcom/amazon/digitalmusicxp/types/SkipLimitIOTAction;", "getSkipLimitIOTAction", "()Lcom/amazon/digitalmusicxp/types/SkipLimitIOTAction;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/CompositeActionStrategyActionTypeEnum;Lcom/amazon/digitalmusicxp/types/SkipLimitIOTAction;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class CompositeActionStrategy {
    private final CompositeActionStrategyActionTypeEnum actionType;
    private final SkipLimitIOTAction skipLimitIOTAction;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CompositeActionStrategy)) {
            return false;
        }
        CompositeActionStrategy compositeActionStrategy = (CompositeActionStrategy) other;
        return this.actionType == compositeActionStrategy.actionType && Intrinsics.areEqual(this.skipLimitIOTAction, compositeActionStrategy.skipLimitIOTAction);
    }

    public int hashCode() {
        int iHashCode = this.actionType.hashCode() * 31;
        SkipLimitIOTAction skipLimitIOTAction = this.skipLimitIOTAction;
        return iHashCode + (skipLimitIOTAction == null ? 0 : skipLimitIOTAction.hashCode());
    }

    public String toString() {
        return "CompositeActionStrategy(actionType=" + this.actionType + ", skipLimitIOTAction=" + this.skipLimitIOTAction + ')';
    }

    public CompositeActionStrategy(CompositeActionStrategyActionTypeEnum actionType, SkipLimitIOTAction skipLimitIOTAction) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        this.actionType = actionType;
        this.skipLimitIOTAction = skipLimitIOTAction;
        ExpectedFunctionsKt.freeze(this);
    }

    public final CompositeActionStrategyActionTypeEnum getActionType() {
        return this.actionType;
    }

    public final SkipLimitIOTAction getSkipLimitIOTAction() {
        return this.skipLimitIOTAction;
    }
}
