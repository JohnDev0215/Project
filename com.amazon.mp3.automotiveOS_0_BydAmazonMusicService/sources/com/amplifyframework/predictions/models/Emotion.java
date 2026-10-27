package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Feature;

/* JADX INFO: loaded from: classes5.dex */
public final class Emotion extends Feature<EmotionType> {
    private Emotion(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.EMOTION.getAlias();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Feature.Builder<Builder, Emotion, EmotionType> {
        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Emotion build() {
            return new Emotion(this);
        }
    }
}
