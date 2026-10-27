package com.amplifyframework.predictions.models;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.predictions.models.Feature;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ImageFeature<T> extends Feature<T> {
    private final RectF box;
    private final Polygon polygon;

    ImageFeature(Builder<?, ? extends ImageFeature<T>, T> builder) {
        super(builder);
        this.box = builder.getBox();
        this.polygon = builder.getPolygon();
    }

    @Nullable
    public RectF getBox() {
        return this.box;
    }

    @Nullable
    public Polygon getPolygon() {
        return this.polygon;
    }

    static abstract class Builder<B extends Builder<B, R, T>, R extends ImageFeature<T>, T> extends Feature.Builder<B, R, T> {
        private RectF box;
        private Polygon polygon;

        Builder() {
        }

        @NonNull
        public B box(@Nullable RectF rectF) {
            this.box = rectF;
            return this;
        }

        @NonNull
        public B polygon(@Nullable Polygon polygon) {
            this.polygon = polygon;
            return this;
        }

        @Nullable
        RectF getBox() {
            return this.box;
        }

        @Nullable
        Polygon getPolygon() {
            return this.polygon;
        }
    }
}
