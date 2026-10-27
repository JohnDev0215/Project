package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageSizeUrlTuple {
    private final String size;
    private final String url;

    public interface BuildStep {
        ImageSizeUrlTuple build();
    }

    public interface SizeStep {
        UrlStep size(String str);
    }

    public interface UrlStep {
        BuildStep url(String str);
    }

    public String getSize() {
        return this.size;
    }

    public String getUrl() {
        return this.url;
    }

    private ImageSizeUrlTuple(String str, String str2) {
        this.size = str;
        this.url = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageSizeUrlTuple.class != obj.getClass()) {
            return false;
        }
        ImageSizeUrlTuple imageSizeUrlTuple = (ImageSizeUrlTuple) obj;
        return ObjectsCompat.equals(getSize(), imageSizeUrlTuple.getSize()) && ObjectsCompat.equals(getUrl(), imageSizeUrlTuple.getUrl());
    }

    public int hashCode() {
        return (getSize() + getUrl()).hashCode();
    }

    public static SizeStep builder() {
        return new Builder();
    }

    public static class Builder implements SizeStep, UrlStep, BuildStep {
        private String size;
        private String url;

        @Override // com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple.BuildStep
        public ImageSizeUrlTuple build() {
            return new ImageSizeUrlTuple(this.size, this.url);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple.SizeStep
        public UrlStep size(String str) {
            Objects.requireNonNull(str);
            this.size = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple.UrlStep
        public BuildStep url(String str) {
            Objects.requireNonNull(str);
            this.url = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple.Builder, com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple.SizeStep
        public CopyOfBuilder size(String str) {
            return (CopyOfBuilder) super.size(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple.Builder, com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple.UrlStep
        public CopyOfBuilder url(String str) {
            return (CopyOfBuilder) super.url(str);
        }
    }
}
