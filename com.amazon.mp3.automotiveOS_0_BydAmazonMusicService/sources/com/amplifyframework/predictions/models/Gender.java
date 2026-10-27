package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Feature;

/* JADX INFO: loaded from: classes5.dex */
public final class Gender extends Feature<GenderBinaryType> {
    private Gender(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.GENDER.getAlias();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Feature.Builder<Builder, Gender, GenderBinaryType> {
        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Gender build() {
            return new Gender(this);
        }
    }
}
