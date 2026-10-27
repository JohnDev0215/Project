package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.VideoResolution;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class VideoResolutionFilter {
    private final QualityLevelClamper mClamper;
    private final StreamIndex mStreamIndex;

    public VideoResolutionFilter(QualityLevelClamper qualityLevelClamper, StreamIndex streamIndex) {
        this.mClamper = qualityLevelClamper;
        this.mStreamIndex = streamIndex;
    }

    public VideoResolution[] getResolutions(int i) {
        return getResolutions(this.mStreamIndex.getSortedQualityLevels(i));
    }

    public VideoResolution[] getResolutions(QualityLevel[] qualityLevelArr) {
        LinkedList linkedList = new LinkedList();
        for (QualityLevel qualityLevel : qualityLevelArr) {
            VideoQualityLevel videoQualityLevel = (VideoQualityLevel) qualityLevel;
            if (this.mClamper.isValidQualityLevel(videoQualityLevel) && (linkedList.isEmpty() || ((VideoResolution) linkedList.getLast()).getWidth() != videoQualityLevel.getMaxWidth() || ((VideoResolution) linkedList.getLast()).getHeight() != videoQualityLevel.getMaxHeight())) {
                linkedList.addLast(new VideoResolution(videoQualityLevel.getMaxWidth(), videoQualityLevel.getMaxHeight()));
            }
        }
        return (VideoResolution[]) linkedList.toArray(new VideoResolution[0]);
    }
}
