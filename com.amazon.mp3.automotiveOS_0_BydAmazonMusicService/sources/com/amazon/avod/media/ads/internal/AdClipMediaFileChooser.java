package com.amazon.avod.media.ads.internal;

import com.amazon.avod.ads.api.MediaFile;
import com.amazon.avod.media.MimeTypes;
import com.amazon.avod.media.ads.internal.config.AdQualityConfig;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdClipMediaFileChooser {
    private final AdQualityConfig mAdQualityConfig;
    private final DownloadService mDownloadService;
    private final boolean mIsSsaiEnabled;

    public AdClipMediaFileChooser(DownloadService downloadService, AdQualityConfig adQualityConfig) {
        this(downloadService, adQualityConfig, AdsConfig.getInstance().isSSAIEnabled());
    }

    AdClipMediaFileChooser(DownloadService downloadService, AdQualityConfig adQualityConfig, boolean z) {
        this.mDownloadService = downloadService;
        this.mAdQualityConfig = adQualityConfig;
        this.mIsSsaiEnabled = z;
    }

    public MediaFile findBestMediaFileForBandwidth(List<MediaFile> list) {
        Preconditions.checkState(!list.isEmpty(), "List of media files to choose from is empty.");
        boolean z = false;
        MediaFile mediaFile = list.get(0);
        MediaFile mediaFile2 = list.get(0);
        MediaFile mediaFile3 = mediaFile2;
        MediaFile mediaFile4 = mediaFile;
        boolean z2 = false;
        for (MediaFile mediaFile5 : list) {
            if ((mediaFile5.getBitrateKbps() == null && MimeTypes.isImageType(mediaFile5.getMimeType())) || (isPlayable(mediaFile5) && mediaFile5.getBitrateKbps().intValue() <= mediaFile4.getBitrateKbps().intValue())) {
                z2 = true;
                mediaFile4 = mediaFile5;
            } else if (isPlayable(mediaFile5) && mediaFileIsSuitable(mediaFile5) && mediaFile5.getBitrateKbps().intValue() >= mediaFile3.getBitrateKbps().intValue()) {
                z = true;
                mediaFile3 = mediaFile5;
            }
        }
        if (z) {
            return mediaFile3;
        }
        if (z2) {
            return mediaFile4;
        }
        return null;
    }

    public MediaFile findMediaFileOfQuality(@Nonnull List<MediaFile> list, int i) {
        Preconditions.checkState(!list.isEmpty(), "List of media files to choose from is empty.");
        MediaFile mediaFile = null;
        Collections.sort(list, MediaFile.getBitrateComparator());
        for (int i2 = 0; i2 < list.size(); i2++) {
            MediaFile mediaFile2 = list.get(i2);
            if (isPlayable(mediaFile2)) {
                if (mediaFile2.getBitrateKbps().intValue() >= i) {
                    return mediaFile2;
                }
                mediaFile = mediaFile2;
            }
        }
        return mediaFile;
    }

    public boolean isPlayable(MediaFile mediaFile) {
        if (MimeTypes.isImageType(mediaFile.getMimeType())) {
            return true;
        }
        if (mediaFile.getBitrateKbps() == null) {
            return false;
        }
        if (this.mIsSsaiEnabled && "application/dash+xml".equals(mediaFile.getMimeType())) {
            return true;
        }
        if (MimeTypes.isVideoType(mediaFile.getMimeType())) {
            return Range.closed(Integer.valueOf(this.mAdQualityConfig.getMinimumPlayableHorizontalResolution()), Integer.valueOf(this.mAdQualityConfig.getMaximumPlayableHorizontalResolution())).contains(Integer.valueOf(mediaFile.getWidth())) && Range.closed(Integer.valueOf(this.mAdQualityConfig.getMinimumPlayableVerticalResolution()), Integer.valueOf(this.mAdQualityConfig.getMaximumPlayableVerticalResolution())).contains(Integer.valueOf(mediaFile.getHeight())) && Range.closed(Integer.valueOf(this.mAdQualityConfig.getLowestPlayableBitrate()), Integer.valueOf(this.mAdQualityConfig.getHighestPlayableBitrate())).contains(Integer.valueOf((int) DataUnit.KILOBITS.toBits((float) mediaFile.getBitrateKbps().intValue())));
        }
        return false;
    }

    public boolean hasPlayableFile(List<MediaFile> list) {
        Iterator<MediaFile> it = list.iterator();
        while (it.hasNext()) {
            if (isPlayable(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean mediaFileIsSuitable(MediaFile mediaFile) {
        long bits = DataUnit.KILOBITS.toBits(mediaFile.getBitrateKbps().intValue());
        float average = this.mDownloadService.getBandwidthStatistics().getAverage();
        return ((float) bits) <= average - (this.mAdQualityConfig.getBitratePaddingPercentage() * average);
    }
}
