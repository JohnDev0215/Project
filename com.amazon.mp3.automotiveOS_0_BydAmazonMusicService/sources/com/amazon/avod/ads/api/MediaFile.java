package com.amazon.avod.ads.api;

import com.amazon.avod.ads.parser.vast.VastMediaFile;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.Comparator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaFile {
    private static final Comparator<MediaFile> COMPARE_BY_BITRATE = new Comparator<MediaFile>() { // from class: com.amazon.avod.ads.api.MediaFile.1
        @Override // java.util.Comparator
        public int compare(MediaFile mediaFile, MediaFile mediaFile2) {
            if (mediaFile == null && mediaFile2 == null) {
                return 0;
            }
            if (mediaFile == null) {
                return -1;
            }
            if (mediaFile2 == null) {
                return 1;
            }
            return mediaFile.getBitrateKbps().compareTo(mediaFile2.getBitrateKbps());
        }
    };
    private final VastMediaFile mInnerMediaFile;

    public static Comparator<MediaFile> getBitrateComparator() {
        return COMPARE_BY_BITRATE;
    }

    public MediaFile(VastMediaFile vastMediaFile) {
        this.mInnerMediaFile = (VastMediaFile) Preconditions.checkNotNull(vastMediaFile);
    }

    @Nonnull
    public String getMimeType() {
        return this.mInnerMediaFile.getMimeType();
    }

    @Nullable
    public Integer getBitrateKbps() {
        return this.mInnerMediaFile.getBitrate();
    }

    @Nonnull
    public int getWidth() {
        return this.mInnerMediaFile.getWidth();
    }

    @Nonnull
    public int getHeight() {
        return this.mInnerMediaFile.getHeight();
    }

    @Nullable
    public Boolean isScalable() {
        return this.mInnerMediaFile.isScalable();
    }

    @Nullable
    public String getCodec() {
        return this.mInnerMediaFile.getCodec();
    }

    @Nonnull
    public URI getUri() {
        return this.mInnerMediaFile.getUri();
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add(Contexts.OpenSeeMore.uri, getUri()).add("Codec", getCodec()).add("Width", getWidth()).add("Height", getHeight()).add("Scalable", isScalable()).toString();
    }
}
