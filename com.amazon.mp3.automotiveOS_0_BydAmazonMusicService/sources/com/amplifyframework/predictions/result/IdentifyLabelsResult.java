package com.amplifyframework.predictions.result;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.Label;
import com.amplifyframework.util.Immutable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class IdentifyLabelsResult implements IdentifyResult {
    private final List<Label> labels;
    private final boolean unsafeContent;

    private IdentifyLabelsResult(Builder builder) {
        this.labels = builder.getLabels();
        this.unsafeContent = builder.getUnsafeContent();
    }

    @NonNull
    public List<Label> getLabels() {
        return Immutable.of(this.labels);
    }

    public boolean isUnsafeContent() {
        return this.unsafeContent;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Label> labels;
        private boolean unsafeContent;

        private Builder() {
            this.labels = Collections.emptyList();
        }

        @NonNull
        public Builder labels(@NonNull List<Label> list) {
            Objects.requireNonNull(list);
            this.labels = list;
            return this;
        }

        @NonNull
        public Builder unsafeContent(boolean z) {
            this.unsafeContent = z;
            return this;
        }

        @NonNull
        public IdentifyLabelsResult build() {
            return new IdentifyLabelsResult(this);
        }

        @NonNull
        List<Label> getLabels() {
            List<Label> list = this.labels;
            Objects.requireNonNull(list);
            return list;
        }

        boolean getUnsafeContent() {
            return this.unsafeContent;
        }
    }
}
