package com.amplifyframework.predictions.models;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.util.Immutable;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class CelebrityDetails {
    private final RectF box;
    private final Celebrity celebrity;
    private final List<Landmark> landmarks;
    private final Polygon polygon;
    private final Pose pose;
    private final List<URL> urls;

    private CelebrityDetails(Builder builder) {
        this.celebrity = builder.getCelebrity();
        this.box = builder.getBox();
        this.polygon = builder.getPolygon();
        this.pose = builder.getPose();
        this.landmarks = builder.getLandmarks();
        this.urls = builder.getUrls();
    }

    @NonNull
    public Celebrity getCelebrity() {
        return this.celebrity;
    }

    @Nullable
    public RectF getBox() {
        return this.box;
    }

    @Nullable
    public Polygon getPolygon() {
        return this.polygon;
    }

    @Nullable
    public Pose getPose() {
        return this.pose;
    }

    @NonNull
    public List<Landmark> getLandmarks() {
        return Immutable.of(this.landmarks);
    }

    @NonNull
    public List<URL> getUrls() {
        return Immutable.of(this.urls);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private RectF box;
        private Celebrity celebrity;
        private List<Landmark> landmarks;
        private Polygon polygon;
        private Pose pose;
        private List<URL> urls;

        private Builder() {
            this.landmarks = Collections.emptyList();
            this.urls = Collections.emptyList();
        }

        @NonNull
        public Builder celebrity(@NonNull Celebrity celebrity) {
            Objects.requireNonNull(celebrity);
            this.celebrity = celebrity;
            return this;
        }

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
        public Builder pose(@Nullable Pose pose) {
            this.pose = pose;
            return this;
        }

        @NonNull
        public Builder landmarks(@NonNull List<Landmark> list) {
            Objects.requireNonNull(list);
            this.landmarks = list;
            return this;
        }

        @NonNull
        public Builder urls(@NonNull List<URL> list) {
            Objects.requireNonNull(list);
            this.urls = list;
            return this;
        }

        @NonNull
        public CelebrityDetails build() {
            return new CelebrityDetails(this);
        }

        @NonNull
        Celebrity getCelebrity() {
            Celebrity celebrity = this.celebrity;
            Objects.requireNonNull(celebrity);
            return celebrity;
        }

        @Nullable
        RectF getBox() {
            return this.box;
        }

        @Nullable
        Polygon getPolygon() {
            return this.polygon;
        }

        @Nullable
        Pose getPose() {
            return this.pose;
        }

        @NonNull
        List<Landmark> getLandmarks() {
            List<Landmark> list = this.landmarks;
            Objects.requireNonNull(list);
            return list;
        }

        @NonNull
        List<URL> getUrls() {
            List<URL> list = this.urls;
            Objects.requireNonNull(list);
            return list;
        }
    }
}
