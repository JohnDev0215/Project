package com.amplifyframework.predictions.models;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.util.Immutable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class EntityDetails {
    private final AgeRange ageRange;
    private final RectF box;
    private final List<Emotion> emotions;
    private final List<BinaryFeature> features;
    private final Gender gender;
    private final List<Landmark> landmarks;
    private final Polygon polygon;
    private final Pose pose;

    private EntityDetails(Builder builder) {
        this.box = builder.getBox();
        this.polygon = builder.getPolygon();
        this.ageRange = builder.getAgeRange();
        this.pose = builder.getPose();
        this.gender = builder.getGender();
        this.landmarks = builder.getLandmarks();
        this.emotions = builder.getEmotions();
        this.features = builder.getValues();
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
    public AgeRange getAgeRange() {
        return this.ageRange;
    }

    @Nullable
    public Pose getPose() {
        return this.pose;
    }

    @Nullable
    public Gender getGender() {
        return this.gender;
    }

    @NonNull
    public List<Landmark> getLandmarks() {
        return Immutable.of(this.landmarks);
    }

    @NonNull
    public List<Emotion> getEmotions() {
        return Immutable.of(this.emotions);
    }

    @NonNull
    public List<BinaryFeature> getValues() {
        return Immutable.of(this.features);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private AgeRange ageRange;
        private RectF box;
        private List<Emotion> emotions;
        private List<BinaryFeature> features;
        private Gender gender;
        private List<Landmark> landmarks;
        private Polygon polygon;
        private Pose pose;

        private Builder() {
            this.landmarks = Collections.emptyList();
            this.emotions = Collections.emptyList();
            this.features = Collections.emptyList();
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
        public Builder ageRange(@Nullable AgeRange ageRange) {
            this.ageRange = ageRange;
            return this;
        }

        @NonNull
        public Builder pose(@Nullable Pose pose) {
            this.pose = pose;
            return this;
        }

        @NonNull
        public Builder gender(@Nullable Gender gender) {
            this.gender = gender;
            return this;
        }

        @NonNull
        public Builder landmarks(@NonNull List<Landmark> list) {
            Objects.requireNonNull(list);
            this.landmarks = list;
            return this;
        }

        @NonNull
        public Builder emotions(@NonNull List<Emotion> list) {
            Objects.requireNonNull(list);
            this.emotions = list;
            return this;
        }

        @NonNull
        public Builder features(@NonNull List<BinaryFeature> list) {
            Objects.requireNonNull(list);
            this.features = list;
            return this;
        }

        @NonNull
        public EntityDetails build() {
            return new EntityDetails(this);
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
        AgeRange getAgeRange() {
            return this.ageRange;
        }

        @Nullable
        Pose getPose() {
            return this.pose;
        }

        @Nullable
        Gender getGender() {
            return this.gender;
        }

        @NonNull
        List<Landmark> getLandmarks() {
            List<Landmark> list = this.landmarks;
            Objects.requireNonNull(list);
            return list;
        }

        @NonNull
        List<Emotion> getEmotions() {
            List<Emotion> list = this.emotions;
            Objects.requireNonNull(list);
            return list;
        }

        @NonNull
        List<BinaryFeature> getValues() {
            List<BinaryFeature> list = this.features;
            Objects.requireNonNull(list);
            return list;
        }
    }
}
