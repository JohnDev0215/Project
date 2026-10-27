package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageManifest {
    private final List<ImageManifestTuple> urls;

    public interface BuildStep {
        ImageManifest build();
    }

    public interface UrlsStep {
        BuildStep urls(List<ImageManifestTuple> list);
    }

    public List<ImageManifestTuple> getUrls() {
        return this.urls;
    }

    private ImageManifest(List<ImageManifestTuple> list) {
        this.urls = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageManifest.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getUrls(), ((ImageManifest) obj).getUrls());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getUrls());
        return sb.toString().hashCode();
    }

    public static UrlsStep builder() {
        return new Builder();
    }

    public static class Builder implements UrlsStep, BuildStep {
        private List<ImageManifestTuple> urls;

        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifest.BuildStep
        public ImageManifest build() {
            return new ImageManifest(this.urls);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifest.UrlsStep
        public BuildStep urls(List<ImageManifestTuple> list) {
            Objects.requireNonNull(list);
            this.urls = list;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifest.Builder, com.amazon.mp3.amplifyqueue.model.ImageManifest.UrlsStep
        public /* bridge */ /* synthetic */ BuildStep urls(List list) {
            return urls((List<ImageManifestTuple>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ImageManifest.Builder, com.amazon.mp3.amplifyqueue.model.ImageManifest.UrlsStep
        public CopyOfBuilder urls(List<ImageManifestTuple> list) {
            return (CopyOfBuilder) super.urls(list);
        }
    }
}
