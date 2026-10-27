package com.amplifyframework.predictions.models;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.ImageFeature;
import com.amplifyframework.util.Immutable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class Label extends ImageFeature<String> {
    public static final String FEATURE_TYPE = Label.class.getSimpleName();
    private final List<RectF> boxes;
    private final List<String> parentLabels;

    private Label(Builder builder) {
        super(builder);
        this.parentLabels = builder.getParentLabels();
        this.boxes = builder.getBoxes();
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FEATURE_TYPE;
    }

    @NonNull
    public String getName() {
        return getValue();
    }

    @NonNull
    public List<String> getParentLabels() {
        return this.parentLabels;
    }

    @NonNull
    public List<RectF> getBoxes() {
        return this.boxes;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImageFeature.Builder<Builder, Label, String> {
        private List<RectF> boxes;
        private List<String> parentLabels;

        private Builder() {
            this.parentLabels = new ArrayList();
            this.boxes = new ArrayList();
        }

        @NonNull
        public Builder name(@NonNull String str) {
            return (Builder) super.value(str);
        }

        @NonNull
        public Builder parentLabels(@NonNull List<String> list) {
            Objects.requireNonNull(list);
            this.parentLabels = list;
            return this;
        }

        @NonNull
        public Builder boxes(@NonNull List<RectF> list) {
            Objects.requireNonNull(list);
            this.boxes = list;
            return this;
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Label build() {
            return new Label(this);
        }

        @NonNull
        List<String> getParentLabels() {
            List<String> listOf = Immutable.of(this.parentLabels);
            Objects.requireNonNull(listOf);
            return listOf;
        }

        @NonNull
        List<RectF> getBoxes() {
            List<RectF> listOf = Immutable.of(this.boxes);
            Objects.requireNonNull(listOf);
            return listOf;
        }
    }
}
