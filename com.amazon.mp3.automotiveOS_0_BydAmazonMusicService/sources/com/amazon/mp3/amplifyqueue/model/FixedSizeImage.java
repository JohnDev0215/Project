package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class FixedSizeImage {
    private final String fileFormat;
    private final Integer heightInPixels;
    private final String imageUrl;
    private final Integer widthInPixels;

    public interface BuildStep {
        FixedSizeImage build();

        BuildStep fileFormat(String str);

        BuildStep heightInPixels(Integer num);

        BuildStep widthInPixels(Integer num);
    }

    public interface ImageUrlStep {
        BuildStep imageUrl(String str);
    }

    public String getFileFormat() {
        return this.fileFormat;
    }

    public Integer getHeightInPixels() {
        return this.heightInPixels;
    }

    public Integer getWidthInPixels() {
        return this.widthInPixels;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    private FixedSizeImage(String str, Integer num, Integer num2, String str2) {
        this.fileFormat = str;
        this.heightInPixels = num;
        this.widthInPixels = num2;
        this.imageUrl = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FixedSizeImage.class != obj.getClass()) {
            return false;
        }
        FixedSizeImage fixedSizeImage = (FixedSizeImage) obj;
        return ObjectsCompat.equals(getFileFormat(), fixedSizeImage.getFileFormat()) && ObjectsCompat.equals(getHeightInPixels(), fixedSizeImage.getHeightInPixels()) && ObjectsCompat.equals(getWidthInPixels(), fixedSizeImage.getWidthInPixels()) && ObjectsCompat.equals(getImageUrl(), fixedSizeImage.getImageUrl());
    }

    public int hashCode() {
        return (getFileFormat() + getHeightInPixels() + getWidthInPixels() + getImageUrl()).hashCode();
    }

    public static ImageUrlStep builder() {
        return new Builder();
    }

    public static class Builder implements ImageUrlStep, BuildStep {
        private String fileFormat;
        private Integer heightInPixels;
        private String imageUrl;
        private Integer widthInPixels;

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.BuildStep
        public FixedSizeImage build() {
            return new FixedSizeImage(this.fileFormat, this.heightInPixels, this.widthInPixels, this.imageUrl);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.ImageUrlStep
        public BuildStep imageUrl(String str) {
            Objects.requireNonNull(str);
            this.imageUrl = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.BuildStep
        public BuildStep fileFormat(String str) {
            this.fileFormat = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.BuildStep
        public BuildStep heightInPixels(Integer num) {
            this.heightInPixels = num;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.BuildStep
        public BuildStep widthInPixels(Integer num) {
            this.widthInPixels = num;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.Builder, com.amazon.mp3.amplifyqueue.model.FixedSizeImage.ImageUrlStep
        public CopyOfBuilder imageUrl(String str) {
            return (CopyOfBuilder) super.imageUrl(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.Builder, com.amazon.mp3.amplifyqueue.model.FixedSizeImage.BuildStep
        public CopyOfBuilder fileFormat(String str) {
            return (CopyOfBuilder) super.fileFormat(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.Builder, com.amazon.mp3.amplifyqueue.model.FixedSizeImage.BuildStep
        public CopyOfBuilder heightInPixels(Integer num) {
            return (CopyOfBuilder) super.heightInPixels(num);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.FixedSizeImage.Builder, com.amazon.mp3.amplifyqueue.model.FixedSizeImage.BuildStep
        public CopyOfBuilder widthInPixels(Integer num) {
            return (CopyOfBuilder) super.widthInPixels(num);
        }
    }
}
