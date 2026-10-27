package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageManifestTuple {
    private final String imageSize;
    private final String url;

    public interface BuildStep {
        ImageManifestTuple build();
    }

    public interface ImageSizeStep {
        UrlStep imageSize(String str);
    }

    public interface UrlStep {
        BuildStep url(String str);
    }

    public String getImageSize() {
        return this.imageSize;
    }

    public String getUrl() {
        return this.url;
    }

    private ImageManifestTuple(String str, String str2) {
        this.imageSize = str;
        this.url = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageManifestTuple.class != obj.getClass()) {
            return false;
        }
        ImageManifestTuple imageManifestTuple = (ImageManifestTuple) obj;
        return ObjectsCompat.equals(getImageSize(), imageManifestTuple.getImageSize()) && ObjectsCompat.equals(getUrl(), imageManifestTuple.getUrl());
    }

    public int hashCode() {
        return (getImageSize() + getUrl()).hashCode();
    }

    public static ImageSizeStep builder() {
        return new Builder();
    }

    public static class Builder implements ImageSizeStep, UrlStep, BuildStep {
        private String imageSize;
        private String url;

        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.BuildStep
        public ImageManifestTuple build() {
            return new ImageManifestTuple(this.imageSize, this.url);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.ImageSizeStep
        public UrlStep imageSize(String str) {
            Objects.requireNonNull(str);
            this.imageSize = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.UrlStep
        public BuildStep url(String str) {
            Objects.requireNonNull(str);
            this.url = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.Builder, com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.ImageSizeStep
        public CopyOfBuilder imageSize(String str) {
            return (CopyOfBuilder) super.imageSize(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.Builder, com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.UrlStep
        public CopyOfBuilder url(String str) {
            return (CopyOfBuilder) super.url(str);
        }
    }
}
