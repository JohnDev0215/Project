package com.amazon.mp3.view.stage;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageOverfloeMenuSecondaryIconStateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconState;", "stageOverflowMenuSecondaryIconState", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconState;", "getStageOverflowMenuSecondaryIconState", "()Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconState;", "setStageOverflowMenuSecondaryIconState", "(Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconState;)V", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class StageOverflowMenuSecondaryIconStateProvider {
    private StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState;

    public StageOverflowMenuSecondaryIconStateProvider() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof StageOverflowMenuSecondaryIconStateProvider) && this.stageOverflowMenuSecondaryIconState == ((StageOverflowMenuSecondaryIconStateProvider) other).stageOverflowMenuSecondaryIconState;
    }

    public int hashCode() {
        return this.stageOverflowMenuSecondaryIconState.hashCode();
    }

    public String toString() {
        return "StageOverflowMenuSecondaryIconStateProvider(stageOverflowMenuSecondaryIconState=" + this.stageOverflowMenuSecondaryIconState + ')';
    }

    public StageOverflowMenuSecondaryIconStateProvider(StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState) {
        Intrinsics.checkNotNullParameter(stageOverflowMenuSecondaryIconState, "stageOverflowMenuSecondaryIconState");
        this.stageOverflowMenuSecondaryIconState = stageOverflowMenuSecondaryIconState;
    }

    public /* synthetic */ StageOverflowMenuSecondaryIconStateProvider(StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? StageOverflowMenuSecondaryIconState.NONE : stageOverflowMenuSecondaryIconState);
    }

    public final StageOverflowMenuSecondaryIconState getStageOverflowMenuSecondaryIconState() {
        return this.stageOverflowMenuSecondaryIconState;
    }

    public final void setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState) {
        Intrinsics.checkNotNullParameter(stageOverflowMenuSecondaryIconState, "<set-?>");
        this.stageOverflowMenuSecondaryIconState = stageOverflowMenuSecondaryIconState;
    }
}
