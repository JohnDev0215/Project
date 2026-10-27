package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.LoopModeEnum;
import com.amazon.digitalmusicxp.enums.ShuffleModeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackOptionsInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "loopMode", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "getLoopMode", "()Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "shuffleMode", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "getShuffleMode", "()Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "playbackContinuationMode", "Ljava/lang/String;", "getPlaybackContinuationMode", "()Ljava/lang/String;", "sequencingRuleEnumValue", "getSequencingRuleEnumValue", "<init>", "(Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaybackOptionsInput {
    private final LoopModeEnum loopMode;
    private final String playbackContinuationMode;
    private final String sequencingRuleEnumValue;
    private final ShuffleModeEnum shuffleMode;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackOptionsInput)) {
            return false;
        }
        PlaybackOptionsInput playbackOptionsInput = (PlaybackOptionsInput) other;
        return this.loopMode == playbackOptionsInput.loopMode && this.shuffleMode == playbackOptionsInput.shuffleMode && Intrinsics.areEqual(this.playbackContinuationMode, playbackOptionsInput.playbackContinuationMode) && Intrinsics.areEqual(this.sequencingRuleEnumValue, playbackOptionsInput.sequencingRuleEnumValue);
    }

    public int hashCode() {
        int iHashCode = ((this.loopMode.hashCode() * 31) + this.shuffleMode.hashCode()) * 31;
        String str = this.playbackContinuationMode;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.sequencingRuleEnumValue;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "PlaybackOptionsInput(loopMode=" + this.loopMode + ", shuffleMode=" + this.shuffleMode + ", playbackContinuationMode=" + this.playbackContinuationMode + ", sequencingRuleEnumValue=" + this.sequencingRuleEnumValue + ')';
    }

    public PlaybackOptionsInput(LoopModeEnum loopMode, ShuffleModeEnum shuffleMode, String str, String str2) {
        Intrinsics.checkNotNullParameter(loopMode, "loopMode");
        Intrinsics.checkNotNullParameter(shuffleMode, "shuffleMode");
        this.loopMode = loopMode;
        this.shuffleMode = shuffleMode;
        this.playbackContinuationMode = str;
        this.sequencingRuleEnumValue = str2;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ PlaybackOptionsInput(LoopModeEnum loopModeEnum, ShuffleModeEnum shuffleModeEnum, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(loopModeEnum, shuffleModeEnum, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2);
    }

    public final LoopModeEnum getLoopMode() {
        return this.loopMode;
    }

    public final ShuffleModeEnum getShuffleMode() {
        return this.shuffleMode;
    }
}
