package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.TextFeature;

/* JADX INFO: loaded from: classes5.dex */
public final class Entity extends TextFeature<EntityType> {
    private Entity(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.ENTITY.getAlias();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends TextFeature.Builder<Builder, Entity, EntityType> {
        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Entity build() {
            return new Entity(this);
        }
    }
}
