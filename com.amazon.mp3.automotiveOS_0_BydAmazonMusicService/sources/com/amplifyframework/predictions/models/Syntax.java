package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.TextFeature;

/* JADX INFO: loaded from: classes5.dex */
public final class Syntax extends TextFeature<SpeechType> {
    private Syntax(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.SYNTAX.getAlias();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends TextFeature.Builder<Builder, Syntax, SpeechType> {
        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Syntax build() {
            return new Syntax(this);
        }
    }
}
