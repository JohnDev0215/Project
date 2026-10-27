package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Feature;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public abstract class TextFeature<T> extends Feature<T> {
    private final int length;
    private final int startIndex;
    private final String targetText;

    TextFeature(Builder<?, ? extends TextFeature<T>, T> builder) {
        super(builder);
        String targetText = builder.getTargetText();
        this.targetText = targetText;
        this.startIndex = builder.getStartIndex();
        this.length = targetText.length();
    }

    @NonNull
    public final String getTargetText() {
        return this.targetText;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getLength() {
        return this.length;
    }

    static abstract class Builder<B extends Builder<B, R, T>, R extends TextFeature<T>, T> extends Feature.Builder<B, R, T> {
        private int startIndex;
        private String targetText;

        Builder() {
        }

        @NonNull
        public final B targetText(@NonNull String str) {
            Objects.requireNonNull(str);
            this.targetText = str;
            return this;
        }

        @NonNull
        public final B startIndex(int i) {
            this.startIndex = i;
            return this;
        }

        @NonNull
        final String getTargetText() {
            String str = this.targetText;
            Objects.requireNonNull(str);
            return str;
        }

        final int getStartIndex() {
            return this.startIndex;
        }
    }
}
