package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import com.amplifyframework.predictions.models.ImageFeature;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class BoundedKeyValue extends ImageFeature<Pair<String, String>> {
    private BoundedKeyValue(Builder builder) {
        super(builder);
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.BOUNDED_KEY_VALUE.getAlias();
    }

    @NonNull
    public String getKey() {
        return getValue().first;
    }

    @NonNull
    public String getKeyValue() {
        return getValue().second;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImageFeature.Builder<Builder, BoundedKeyValue, Pair<String, String>> {
        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public Builder keyValuePair(@NonNull String str, @NonNull String str2) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(str2);
            return (Builder) value(Pair.create(str, str2));
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public BoundedKeyValue build() {
            Objects.requireNonNull(getValue());
            Objects.requireNonNull(getValue().first);
            Objects.requireNonNull(getValue().second);
            return new BoundedKeyValue(this);
        }
    }
}
