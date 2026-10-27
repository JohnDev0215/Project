package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.ImageFeature;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class IdentifiedText extends ImageFeature<String> {
    private final int page;

    private IdentifiedText(Builder builder) {
        super(builder);
        this.page = builder.getPage();
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.IDENTIFIED_TEXT.getAlias();
    }

    @NonNull
    public String getText() {
        return getValue();
    }

    public int getPage() {
        return this.page;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImageFeature.Builder<Builder, IdentifiedText, String> {
        private int page;

        @NonNull
        public Builder text(@NonNull String str) {
            Objects.requireNonNull(str);
            return (Builder) super.value(str);
        }

        @NonNull
        public Builder page(int i) {
            this.page = i;
            return this;
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public IdentifiedText build() {
            return new IdentifiedText(this);
        }

        int getPage() {
            return this.page;
        }
    }
}
