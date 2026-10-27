package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Feature;

/* JADX INFO: loaded from: classes5.dex */
public final class Celebrity extends Feature<String> {
    private Celebrity(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.CELEBRITY.getAlias();
    }

    @NonNull
    public String getName() {
        return getValue();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Feature.Builder<Builder, Celebrity, String> {
        @NonNull
        public Builder name(@NonNull String str) {
            return (Builder) super.value(str);
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Celebrity build() {
            return new Celebrity(this);
        }
    }
}
