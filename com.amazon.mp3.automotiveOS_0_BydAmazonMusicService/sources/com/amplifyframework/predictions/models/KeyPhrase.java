package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.TextFeature;

/* JADX INFO: loaded from: classes5.dex */
public final class KeyPhrase extends TextFeature<String> {
    private KeyPhrase(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.KEY_PHRASE.getAlias();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends TextFeature.Builder<Builder, KeyPhrase, String> {
        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public KeyPhrase build() {
            return new KeyPhrase(this);
        }
    }
}
