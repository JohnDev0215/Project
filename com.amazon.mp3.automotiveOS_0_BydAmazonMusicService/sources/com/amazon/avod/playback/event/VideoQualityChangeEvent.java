package com.amazon.avod.playback.event;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;

/* JADX INFO: loaded from: classes2.dex */
public class VideoQualityChangeEvent extends QualityChangeEvent {
    private final VideoResolution[] mAvailableResolutions;
    private final VideoResolution mCurrentResolution;

    public VideoQualityChangeEvent(TimeSpan timeSpan, int i, VideoResolution videoResolution, VideoResolution[] videoResolutionArr) {
        super(timeSpan, i);
        this.mCurrentResolution = videoResolution;
        this.mAvailableResolutions = videoResolutionArr;
    }

    public VideoResolution getResolution() {
        return this.mCurrentResolution;
    }

    public VideoResolution[] getAvailableResolutions() {
        return this.mAvailableResolutions;
    }
}
