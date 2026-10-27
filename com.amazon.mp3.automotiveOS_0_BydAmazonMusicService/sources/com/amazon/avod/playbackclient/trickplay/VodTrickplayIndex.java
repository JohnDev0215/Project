package com.amazon.avod.playbackclient.trickplay;

import com.amazon.avod.content.image.ImageSizeSpec;
import com.amazon.avod.content.image.TrickplayImage;
import com.amazon.avod.content.image.TrickplayIndex;
import com.amazon.avod.content.image.TrickplayManifest;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.NavigableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VodTrickplayIndex implements TrickplayIndex {
    private final ImageSizeSpec mImageSizeSpec;
    private final TrickplayManifest mRawManifest;
    private final NavigableMap<Long, TrickplayImage> mTimecodeToTrickplayImageMap;

    public VodTrickplayIndex(@Nonnull TrickplayManifest trickplayManifest) {
        this.mRawManifest = (TrickplayManifest) Preconditions.checkNotNull(trickplayManifest);
        Preconditions.checkArgument(trickplayManifest.isValid());
        this.mTimecodeToTrickplayImageMap = Maps.newTreeMap();
        for (Map.Entry<Long, String> entry : trickplayManifest.getTimecodeToFilepathMap().entrySet()) {
            Long key = entry.getKey();
            String value = entry.getValue();
            if (key.longValue() < 0) {
                DLog.warnf("Trickplay manifest had unnecessary entry in the manifest. Timecode = %s, File = %s", key, DLog.maskString(value));
            } else {
                this.mTimecodeToTrickplayImageMap.put(key, new TrickplayImage(key.longValue(), entry.getValue()));
            }
        }
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
        Map.Entry<Long, TrickplayImage> entryFloorEntry = this.mTimecodeToTrickplayImageMap.floorEntry(Long.valueOf(j));
        if (entryFloorEntry != null) {
            return entryFloorEntry.getValue();
        }
        return null;
    }

    @Override // com.amazon.avod.content.image.TrickplayIndex
    @Nonnull
    public TrickplayManifest getRawManifest() {
        return this.mRawManifest;
    }
}
