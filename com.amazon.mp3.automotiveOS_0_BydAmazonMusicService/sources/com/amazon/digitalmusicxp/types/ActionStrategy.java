package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.ActionStrategyTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionStrategy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/ActionStrategyTypeEnum;", "strategyType", "Lcom/amazon/digitalmusicxp/enums/ActionStrategyTypeEnum;", "getStrategyType", "()Lcom/amazon/digitalmusicxp/enums/ActionStrategyTypeEnum;", "Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "singleActionStrategy", "Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "getSingleActionStrategy", "()Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "Lcom/amazon/digitalmusicxp/types/CompositeActionStrategy;", "compositeActionStrategy", "Lcom/amazon/digitalmusicxp/types/CompositeActionStrategy;", "getCompositeActionStrategy", "()Lcom/amazon/digitalmusicxp/types/CompositeActionStrategy;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/ActionStrategyTypeEnum;Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;Lcom/amazon/digitalmusicxp/types/CompositeActionStrategy;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ActionStrategy {
    private final CompositeActionStrategy compositeActionStrategy;
    private final SingleActionStrategy singleActionStrategy;
    private final ActionStrategyTypeEnum strategyType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionStrategy)) {
            return false;
        }
        ActionStrategy actionStrategy = (ActionStrategy) other;
        return this.strategyType == actionStrategy.strategyType && Intrinsics.areEqual(this.singleActionStrategy, actionStrategy.singleActionStrategy) && Intrinsics.areEqual(this.compositeActionStrategy, actionStrategy.compositeActionStrategy);
    }

    public int hashCode() {
        int iHashCode = this.strategyType.hashCode() * 31;
        SingleActionStrategy singleActionStrategy = this.singleActionStrategy;
        int iHashCode2 = (iHashCode + (singleActionStrategy == null ? 0 : singleActionStrategy.hashCode())) * 31;
        CompositeActionStrategy compositeActionStrategy = this.compositeActionStrategy;
        return iHashCode2 + (compositeActionStrategy != null ? compositeActionStrategy.hashCode() : 0);
    }

    public String toString() {
        return "ActionStrategy(strategyType=" + this.strategyType + ", singleActionStrategy=" + this.singleActionStrategy + ", compositeActionStrategy=" + this.compositeActionStrategy + ')';
    }

    public ActionStrategy(ActionStrategyTypeEnum strategyType, SingleActionStrategy singleActionStrategy, CompositeActionStrategy compositeActionStrategy) {
        Intrinsics.checkNotNullParameter(strategyType, "strategyType");
        this.strategyType = strategyType;
        this.singleActionStrategy = singleActionStrategy;
        this.compositeActionStrategy = compositeActionStrategy;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ ActionStrategy(ActionStrategyTypeEnum actionStrategyTypeEnum, SingleActionStrategy singleActionStrategy, CompositeActionStrategy compositeActionStrategy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(actionStrategyTypeEnum, (i & 2) != 0 ? null : singleActionStrategy, (i & 4) != 0 ? null : compositeActionStrategy);
    }

    public final ActionStrategyTypeEnum getStrategyType() {
        return this.strategyType;
    }

    public final SingleActionStrategy getSingleActionStrategy() {
        return this.singleActionStrategy;
    }

    public final CompositeActionStrategy getCompositeActionStrategy() {
        return this.compositeActionStrategy;
    }
}
