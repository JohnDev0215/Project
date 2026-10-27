package com.amazon.avod.content.image;

import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LiveTrickplayIndex implements TrickplayIndex {
    private final ImageSizeSpec mImageSizeSpec;
    private final TrickplayManifest mRawManifest;

    public LiveTrickplayIndex(@Nonnull TrickplayManifest trickplayManifest) {
        TrickplayManifest trickplayManifest2 = (TrickplayManifest) Preconditions.checkNotNull(trickplayManifest, "manifest");
        this.mRawManifest = trickplayManifest2;
        Preconditions.checkState(trickplayManifest2.isValid(), "manifest is not valid");
        Preconditions.checkState(trickplayManifest2.isLive(), "only live manifests can be used in a live index");
        this.mImageSizeSpec = new ImageSizeSpec(trickplayManifest.getImageWidth(), trickplayManifest.getImageHeight());
    }

    @Override // com.amazon.avod.content.image.TrickplayIndex
    @Nullable
    public String getImageFileForTimecode(long j) {
        TrickplayImage trickplayImageFindClosestImage = findClosestImage(j);
        if (trickplayImageFindClosestImage != null) {
            return trickplayImageFindClosestImage.getImageFilepath();
        }
        return null;
    }

    @Override // com.amazon.avod.content.image.TrickplayIndex
    @Nullable
    public TrickplayImage findClosestImage(long j) {
        Map.Entry<Long, String> closest = this.mRawManifest.getClosest(j);
        if (j < 0 || closest == null) {
            return null;
        }
        return new TrickplayImage(closest.getKey().longValue(), closest.getValue());
    }

    @Override // com.amazon.avod.content.image.TrickplayIndex
    @Nonnull
    public TrickplayManifest getRawManifest() {
        return this.mRawManifest;
    }
}
