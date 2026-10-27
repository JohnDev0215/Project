package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageMetadata {
    private final String adChoiceClickUrl;
    private final String clickUrl;
    private final FixedSizeImage fixedSizeImage;
    private final ImageManifest imageManifest;

    public interface BuildStep {
        BuildStep adChoiceClickUrl(String str);

        ImageMetadata build();

        BuildStep clickUrl(String str);

        BuildStep fixedSizeImage(FixedSizeImage fixedSizeImage);

        BuildStep imageManifest(ImageManifest imageManifest);
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    public FixedSizeImage getFixedSizeImage() {
        return this.fixedSizeImage;
    }

    public ImageManifest getImageManifest() {
        return this.imageManifest;
    }

    public String getAdChoiceClickUrl() {
        return this.adChoiceClickUrl;
    }

    private ImageMetadata(String str, FixedSizeImage fixedSizeImage, ImageManifest imageManifest, String str2) {
        this.clickUrl = str;
        this.fixedSizeImage = fixedSizeImage;
        this.imageManifest = imageManifest;
        this.adChoiceClickUrl = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageMetadata.class != obj.getClass()) {
            return false;
        }
        ImageMetadata imageMetadata = (ImageMetadata) obj;
        return ObjectsCompat.equals(getClickUrl(), imageMetadata.getClickUrl()) && ObjectsCompat.equals(getFixedSizeImage(), imageMetadata.getFixedSizeImage()) && ObjectsCompat.equals(getImageManifest(), imageMetadata.getImageManifest()) && ObjectsCompat.equals(getAdChoiceClickUrl(), imageMetadata.getAdChoiceClickUrl());
    }

    public int hashCode() {
        return (getClickUrl() + getFixedSizeImage() + getImageManifest() + getAdChoiceClickUrl()).hashCode();
    }

    public static BuildStep builder() {
        return new Builder();
    }

    public static class Builder implements BuildStep {
        private String adChoiceClickUrl;
        private String clickUrl;
        private FixedSizeImage fixedSizeImage;
        private ImageManifest imageManifest;

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public ImageMetadata build() {
            return new ImageMetadata(this.clickUrl, this.fixedSizeImage, this.imageManifest, this.adChoiceClickUrl);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public BuildStep clickUrl(String str) {
            this.clickUrl = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public BuildStep fixedSizeImage(FixedSizeImage fixedSizeImage) {
            this.fixedSizeImage = fixedSizeImage;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public BuildStep imageManifest(ImageManifest imageManifest) {
            this.imageManifest = imageManifest;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public BuildStep adChoiceClickUrl(String str) {
            this.adChoiceClickUrl = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.Builder, com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public CopyOfBuilder clickUrl(String str) {
            return (CopyOfBuilder) super.clickUrl(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.Builder, com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public CopyOfBuilder fixedSizeImage(FixedSizeImage fixedSizeImage) {
            return (CopyOfBuilder) super.fixedSizeImage(fixedSizeImage);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.Builder, com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public CopyOfBuilder imageManifest(ImageManifest imageManifest) {
            return (CopyOfBuilder) super.imageManifest(imageManifest);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageMetadata.Builder, com.amazon.mp3.amplifyqueue.model.ImageMetadata.BuildStep
        public CopyOfBuilder adChoiceClickUrl(String str) {
            return (CopyOfBuilder) super.adChoiceClickUrl(str);
        }
    }
}
