package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Feature;

/* JADX INFO: loaded from: classes5.dex */
public final class Sentiment extends Feature<SentimentType> {
    private Sentiment(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.SENTIMENT.getAlias();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Feature.Builder<Builder, Sentiment, SentimentType> {
        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Sentiment build() {
            return new Sentiment(this);
        }
    }
}
