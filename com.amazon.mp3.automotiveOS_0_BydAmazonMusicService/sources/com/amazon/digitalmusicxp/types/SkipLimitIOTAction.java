package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipLimitIOTAction.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/amazon/digitalmusicxp/types/SkipLimitIOTAction;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "noSkipsRemainingAction", "Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "getNoSkipsRemainingAction", "()Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "skipsRemainingActionStrategy", "getSkipsRemainingActionStrategy", "<init>", "(Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SkipLimitIOTAction {
    private final SingleActionStrategy noSkipsRemainingAction;
    private final SingleActionStrategy skipsRemainingActionStrategy;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkipLimitIOTAction)) {
            return false;
        }
        SkipLimitIOTAction skipLimitIOTAction = (SkipLimitIOTAction) other;
        return Intrinsics.areEqual(this.noSkipsRemainingAction, skipLimitIOTAction.noSkipsRemainingAction) && Intrinsics.areEqual(this.skipsRemainingActionStrategy, skipLimitIOTAction.skipsRemainingActionStrategy);
    }

    public int hashCode() {
        return (this.noSkipsRemainingAction.hashCode() * 31) + this.skipsRemainingActionStrategy.hashCode();
    }

    public String toString() {
        return "SkipLimitIOTAction(noSkipsRemainingAction=" + this.noSkipsRemainingAction + ", skipsRemainingActionStrategy=" + this.skipsRemainingActionStrategy + ')';
    }

    public SkipLimitIOTAction(SingleActionStrategy noSkipsRemainingAction, SingleActionStrategy skipsRemainingActionStrategy) {
        Intrinsics.checkNotNullParameter(noSkipsRemainingAction, "noSkipsRemainingAction");
        Intrinsics.checkNotNullParameter(skipsRemainingActionStrategy, "skipsRemainingActionStrategy");
        this.noSkipsRemainingAction = noSkipsRemainingAction;
        this.skipsRemainingActionStrategy = skipsRemainingActionStrategy;
        ExpectedFunctionsKt.freeze(this);
    }

    public final SingleActionStrategy getNoSkipsRemainingAction() {
        return this.noSkipsRemainingAction;
    }

    public final SingleActionStrategy getSkipsRemainingActionStrategy() {
        return this.skipsRemainingActionStrategy;
    }
}
