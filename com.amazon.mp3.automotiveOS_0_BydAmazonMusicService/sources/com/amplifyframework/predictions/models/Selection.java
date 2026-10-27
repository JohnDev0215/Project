package com.amplifyframework.predictions.models;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class Selection {
    private final RectF box;
    private final Polygon polygon;
    private final boolean selected;

    private Selection(RectF rectF, Polygon polygon, boolean z) {
        this.box = rectF;
        this.polygon = polygon;
        this.selected = z;
    }

    @Nullable
    public RectF getBox() {
        return this.box;
    }

    @Nullable
    public Polygon getPolygon() {
        return this.polygon;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private RectF box;
        private Polygon polygon;
        private boolean selected;

        @NonNull
        public Builder box(@Nullable RectF rectF) {
            this.box = rectF;
            return this;
        }

        @NonNull
        public Builder polygon(@Nullable Polygon polygon) {
            this.polygon = polygon;
            return this;
        }

        @NonNull
        public Builder selected(boolean z) {
            this.selected = z;
            return this;
        }

        public Selection build() {
            return new Selection(this.box, this.polygon, this.selected);
        }

        @Nullable
        RectF getBox() {
            return this.box;
        }

        @Nullable
        Polygon getPolygon() {
            return this.polygon;
        }

        boolean isSelected() {
            return this.selected;
        }
    }
}
