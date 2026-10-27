package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaybackOptionsInput {
    private final LoopModeEnum loopMode;
    private final String playbackContinuationMode;
    private final String sequencingRuleEnumValue;
    private final ShuffleModeEnum shuffleMode;

    public interface BuildStep {
        PlaybackOptionsInput build();
    }

    public interface LoopModeStep {
        ShuffleModeStep loopMode(LoopModeEnum loopModeEnum);
    }

    public interface ShuffleModeStep {
        BuildStep shuffleMode(ShuffleModeEnum shuffleModeEnum);
    }

    public LoopModeEnum getLoopMode() {
        return this.loopMode;
    }

    public ShuffleModeEnum getShuffleMode() {
        return this.shuffleMode;
    }

    public String getPlaybackContinuationMode() {
        return this.playbackContinuationMode;
    }

    public String getSequencingRuleEnumValue() {
        return this.sequencingRuleEnumValue;
    }

    private PlaybackOptionsInput(LoopModeEnum loopModeEnum, ShuffleModeEnum shuffleModeEnum, String str, String str2) {
        this.loopMode = loopModeEnum;
        this.shuffleMode = shuffleModeEnum;
        this.playbackContinuationMode = str;
        this.sequencingRuleEnumValue = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlaybackOptionsInput.class != obj.getClass()) {
            return false;
        }
        PlaybackOptionsInput playbackOptionsInput = (PlaybackOptionsInput) obj;
        return ObjectsCompat.equals(getLoopMode(), playbackOptionsInput.getLoopMode()) && ObjectsCompat.equals(getShuffleMode(), playbackOptionsInput.getShuffleMode()) && ObjectsCompat.equals(getPlaybackContinuationMode(), playbackOptionsInput.getPlaybackContinuationMode()) && ObjectsCompat.equals(getSequencingRuleEnumValue(), playbackOptionsInput.getSequencingRuleEnumValue());
    }

    public int hashCode() {
        return (getLoopMode() + getShuffleMode() + getPlaybackContinuationMode() + getSequencingRuleEnumValue()).hashCode();
    }

    public static LoopModeStep builder() {
        return new Builder();
    }

    public static class Builder implements LoopModeStep, ShuffleModeStep, BuildStep {
        private LoopModeEnum loopMode;
        private String playbackContinuationMode;
        private String sequencingRuleEnumValue;
        private ShuffleModeEnum shuffleMode;

        @Override // com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.BuildStep
        public PlaybackOptionsInput build() {
            return new PlaybackOptionsInput(this.loopMode, this.shuffleMode, this.playbackContinuationMode, this.sequencingRuleEnumValue);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.LoopModeStep
        public ShuffleModeStep loopMode(LoopModeEnum loopModeEnum) {
            Objects.requireNonNull(loopModeEnum);
            this.loopMode = loopModeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.ShuffleModeStep
        public BuildStep shuffleMode(ShuffleModeEnum shuffleModeEnum) {
            Objects.requireNonNull(shuffleModeEnum);
            this.shuffleMode = shuffleModeEnum;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.Builder, com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.LoopModeStep
        public CopyOfBuilder loopMode(LoopModeEnum loopModeEnum) {
            return (CopyOfBuilder) super.loopMode(loopModeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.Builder, com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.ShuffleModeStep
        public CopyOfBuilder shuffleMode(ShuffleModeEnum shuffleModeEnum) {
            return (CopyOfBuilder) super.shuffleMode(shuffleModeEnum);
        }
    }
}
