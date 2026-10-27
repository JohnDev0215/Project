package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.ImageFeature;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class Table extends ImageFeature<List<Cell>> {
    private final int columnSize;
    private final int rowSize;

    private Table(Builder builder) {
        super(builder);
        this.rowSize = builder.getRowSize();
        this.columnSize = builder.getColumnSize();
    }

    @Override // com.amplifyframework.predictions.models.Feature
    @NonNull
    public String getTypeAlias() {
        return FeatureType.TABLE.getAlias();
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int getColumnSize() {
        return this.columnSize;
    }

    @NonNull
    public List<Cell> getCells() {
        return getValue();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImageFeature.Builder<Builder, Table, List<Cell>> {
        private List<Cell> cells;
        private int columnSize;
        private int rowSize;

        private Builder() {
            this.cells = Collections.emptyList();
        }

        @NonNull
        public Builder rowSize(int i) {
            this.rowSize = i;
            return this;
        }

        @NonNull
        public Builder columnSize(int i) {
            this.columnSize = i;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public Builder cells(@NonNull List<Cell> list) {
            return (Builder) value(list);
        }

        @Override // com.amplifyframework.predictions.models.Feature.Builder
        @NonNull
        public Table build() {
            return new Table(this);
        }

        int getRowSize() {
            return this.rowSize;
        }

        int getColumnSize() {
            return this.columnSize;
        }

        @NonNull
        List<Cell> getCells() {
            List<Cell> list = this.cells;
            Objects.requireNonNull(list);
            return list;
        }
    }
}
