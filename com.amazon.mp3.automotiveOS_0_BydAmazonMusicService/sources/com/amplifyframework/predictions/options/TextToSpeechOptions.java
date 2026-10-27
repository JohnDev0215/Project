package com.amplifyframework.predictions.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.predictions.models.VoiceType;

/* JADX INFO: loaded from: classes5.dex */
public final class TextToSpeechOptions {
    private final VoiceType voiceType;

    private TextToSpeechOptions(Builder builder) {
        this.voiceType = builder.getVoiceType();
    }

    @Nullable
    public VoiceType getVoiceType() {
        return this.voiceType;
    }

    @NonNull
    public static TextToSpeechOptions defaults() {
        return new TextToSpeechOptions(builder());
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private VoiceType voiceType;

        @NonNull
        public Builder voiceType(@Nullable VoiceType voiceType) {
            this.voiceType = voiceType;
            return this;
        }

        @NonNull
        public TextToSpeechOptions build() {
            return new TextToSpeechOptions(this);
        }

        VoiceType getVoiceType() {
            return this.voiceType;
        }
    }
}
