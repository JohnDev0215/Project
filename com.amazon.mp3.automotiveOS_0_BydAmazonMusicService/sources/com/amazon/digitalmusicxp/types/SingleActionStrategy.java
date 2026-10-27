package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.SingleActionStrategyActionTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingleActionStrategy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/SingleActionStrategyActionTypeEnum;", "actionType", "Lcom/amazon/digitalmusicxp/enums/SingleActionStrategyActionTypeEnum;", "getActionType", "()Lcom/amazon/digitalmusicxp/enums/SingleActionStrategyActionTypeEnum;", "Lcom/amazon/digitalmusicxp/types/UpsellAction;", "upsellAction", "Lcom/amazon/digitalmusicxp/types/UpsellAction;", "getUpsellAction", "()Lcom/amazon/digitalmusicxp/types/UpsellAction;", "booleanAction", "Ljava/lang/Boolean;", "getBooleanAction", "()Ljava/lang/Boolean;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/SingleActionStrategyActionTypeEnum;Lcom/amazon/digitalmusicxp/types/UpsellAction;Ljava/lang/Boolean;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SingleActionStrategy {
    private final SingleActionStrategyActionTypeEnum actionType;
    private final Boolean booleanAction;
    private final UpsellAction upsellAction;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SingleActionStrategy)) {
            return false;
        }
        SingleActionStrategy singleActionStrategy = (SingleActionStrategy) other;
        return this.actionType == singleActionStrategy.actionType && Intrinsics.areEqual(this.upsellAction, singleActionStrategy.upsellAction) && Intrinsics.areEqual(this.booleanAction, singleActionStrategy.booleanAction);
    }

    public int hashCode() {
        int iHashCode = this.actionType.hashCode() * 31;
        UpsellAction upsellAction = this.upsellAction;
        int iHashCode2 = (iHashCode + (upsellAction == null ? 0 : upsellAction.hashCode())) * 31;
        Boolean bool = this.booleanAction;
        return iHashCode2 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "SingleActionStrategy(actionType=" + this.actionType + ", upsellAction=" + this.upsellAction + ", booleanAction=" + this.booleanAction + ')';
    }

    public SingleActionStrategy(SingleActionStrategyActionTypeEnum actionType, UpsellAction upsellAction, Boolean bool) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        this.actionType = actionType;
        this.upsellAction = upsellAction;
        this.booleanAction = bool;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ SingleActionStrategy(SingleActionStrategyActionTypeEnum singleActionStrategyActionTypeEnum, UpsellAction upsellAction, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(singleActionStrategyActionTypeEnum, (i & 2) != 0 ? null : upsellAction, (i & 4) != 0 ? null : bool);
    }

    public final SingleActionStrategyActionTypeEnum getActionType() {
        return this.actionType;
    }

    public final UpsellAction getUpsellAction() {
        return this.upsellAction;
    }

    public final Boolean getBooleanAction() {
        return this.booleanAction;
    }
}
