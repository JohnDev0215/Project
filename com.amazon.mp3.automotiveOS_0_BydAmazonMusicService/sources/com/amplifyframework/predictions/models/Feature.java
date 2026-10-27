package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Feature<T> implements Comparable<Feature<T>> {
    private final float confidence;
    private final String id;
    private final T value;

    @NonNull
    public abstract String getTypeAlias();

    Feature(Builder<?, ? extends Feature<T>, T> builder) {
        this.id = builder.getId();
        this.value = builder.getValue();
        this.confidence = builder.getConfidence();
    }

    @NonNull
    public final String getId() {
        return this.id;
    }

    @NonNull
    public final T getValue() {
        return this.value;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    @Override // java.lang.Comparable
    public int compareTo(@Nullable Feature<T> feature) {
        if (feature == null) {
            return -1;
        }
        int iCompareToIgnoreCase = getTypeAlias().compareToIgnoreCase(feature.getTypeAlias());
        return iCompareToIgnoreCase != 0 ? iCompareToIgnoreCase : (int) Math.signum(getConfidence() - feature.getConfidence());
    }

    static abstract class Builder<B extends Builder<B, R, T>, R extends Feature<T>, T> {
        private float confidence;
        private String id = UUID.randomUUID().toString();
        private T value;

        @NonNull
        public abstract R build();

        Builder() {
        }

        @NonNull
        public final B id(@NonNull String str) {
            Objects.requireNonNull(str);
            this.id = str;
            return this;
        }

        @NonNull
        public final B value(@NonNull T t) {
            Objects.requireNonNull(t);
            this.value = t;
            return this;
        }

        public final B confidence(float f) {
            this.confidence = f;
            return this;
        }

        @NonNull
        final String getId() {
            String str = this.id;
            Objects.requireNonNull(str);
            return str;
        }

        @NonNull
        final T getValue() {
            T t = this.value;
            Objects.requireNonNull(t);
            return t;
        }

        final float getConfidence() {
            return this.confidence;
        }
    }
}
