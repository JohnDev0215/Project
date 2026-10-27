package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.ImageFeature;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class EntityMatch extends ImageFeature<String> {
    private EntityMatch(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.ENTITY_MATCH.getAlias();
    }

    @NonNull
    public String getExternalImageId() {
        return getValue();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImageFeature.Builder<Builder, EntityMatch, String> {
        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public Builder externalImageId(@NonNull String str) {
            Objects.requireNonNull(str);
            return (Builder) value(str);
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public EntityMatch build() {
            return new EntityMatch(this);
        }
    }
}
