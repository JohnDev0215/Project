package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Feature;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class BinaryFeature extends Feature<Boolean> {
    private final String type;

    private BinaryFeature(Builder builder) {
        super(builder);
        this.type = builder.getType();
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return this.type;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Feature.Builder<Builder, BinaryFeature, Boolean> {
        private String type;

        @NonNull
        public Builder type(@NonNull String str) {
            Objects.requireNonNull(str);
            this.type = str;
            return this;
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public BinaryFeature build() {
            return new BinaryFeature(this);
        }

        @NonNull
        String getType() {
            String str = this.type;
            Objects.requireNonNull(str);
            return str;
        }
    }
}
