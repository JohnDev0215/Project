package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackInstruction.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/digitalmusicxp/types/PlaybackInstruction;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/types/StartOffsetPlaybackInstruction;", "startOffsetInstruction", "Lcom/amazon/digitalmusicxp/types/StartOffsetPlaybackInstruction;", "getStartOffsetInstruction", "()Lcom/amazon/digitalmusicxp/types/StartOffsetPlaybackInstruction;", "<init>", "(Lcom/amazon/digitalmusicxp/types/StartOffsetPlaybackInstruction;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaybackInstruction {
    private final StartOffsetPlaybackInstruction startOffsetInstruction;

    public PlaybackInstruction() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PlaybackInstruction) && Intrinsics.areEqual(this.startOffsetInstruction, ((PlaybackInstruction) other).startOffsetInstruction);
    }

    public int hashCode() {
        StartOffsetPlaybackInstruction startOffsetPlaybackInstruction = this.startOffsetInstruction;
        if (startOffsetPlaybackInstruction == null) {
            return 0;
        }
        return startOffsetPlaybackInstruction.hashCode();
    }

    public String toString() {
        return "PlaybackInstruction(startOffsetInstruction=" + this.startOffsetInstruction + ')';
    }

    public PlaybackInstruction(StartOffsetPlaybackInstruction startOffsetPlaybackInstruction) {
        this.startOffsetInstruction = startOffsetPlaybackInstruction;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ PlaybackInstruction(StartOffsetPlaybackInstruction startOffsetPlaybackInstruction, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : startOffsetPlaybackInstruction);
    }
}
