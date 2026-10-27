package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoShow {
    private final String showId;
    private final String sourceId;
    private final VideoShowSourceTypeEnum sourceType;

    public interface BuildStep {
    }

    public static class Builder implements SourceTypeStep, SourceIdStep, ShowIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface ShowIdStep {
    }

    public interface SourceIdStep {
    }

    public interface SourceTypeStep {
    }

    public VideoShowSourceTypeEnum getSourceType() {
        return this.sourceType;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public String getShowId() {
        return this.showId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || VideoShow.class != obj.getClass()) {
            return false;
        }
        VideoShow videoShow = (VideoShow) obj;
        return ObjectsCompat.equals(getSourceType(), videoShow.getSourceType()) && ObjectsCompat.equals(getSourceId(), videoShow.getSourceId()) && ObjectsCompat.equals(getShowId(), videoShow.getShowId());
    }

    public int hashCode() {
        return (getSourceType() + getSourceId() + getShowId()).hashCode();
    }
}
