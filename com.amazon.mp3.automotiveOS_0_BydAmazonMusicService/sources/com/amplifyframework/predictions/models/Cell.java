package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.ImageFeature;

/* JADX INFO: loaded from: classes5.dex */
public final class Cell extends ImageFeature<String> {
    private final int column;
    private final int row;
    private final boolean selected;

    private Cell(Builder builder) {
        super(builder);
        this.selected = builder.getSelected();
        this.row = builder.getRow();
        this.column = builder.getColumn();
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.CELL.getAlias();
    }

    @NonNull
    public String getText() {
        return getValue();
    }

    public boolean isSelected() {
        return this.selected;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImageFeature.Builder<Builder, Cell, String> {
        private int column;
        private int row;
        private boolean selected;

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public Builder text(@NonNull String str) {
            return (Builder) value(str);
        }

        @NonNull
        public Builder selected(boolean z) {
            this.selected = z;
            return this;
        }

        @NonNull
        public Builder row(int i) {
            this.row = i;
            return this;
        }

        @NonNull
        public Builder column(int i) {
            this.column = i;
            return this;
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Cell build() {
            return new Cell(this);
        }

        boolean getSelected() {
            return this.selected;
        }

        int getRow() {
            return this.row;
        }

        int getColumn() {
            return this.column;
        }
    }
}
