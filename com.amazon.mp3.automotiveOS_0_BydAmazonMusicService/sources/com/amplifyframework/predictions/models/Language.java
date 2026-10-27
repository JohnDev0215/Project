package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Feature;

/* JADX INFO: loaded from: classes5.dex */
public final class Language extends Feature<LanguageType> {
    private Language(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.LANGUAGE.getAlias();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Feature.Builder<Builder, Language, LanguageType> {
        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Language build() {
            return new Language(this);
        }
    }
}
