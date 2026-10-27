package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoShowInput {
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
        if (obj == null || VideoShowInput.class != obj.getClass()) {
            return false;
        }
        VideoShowInput videoShowInput = (VideoShowInput) obj;
        return ObjectsCompat.equals(getSourceType(), videoShowInput.getSourceType()) && ObjectsCompat.equals(getSourceId(), videoShowInput.getSourceId()) && ObjectsCompat.equals(getShowId(), videoShowInput.getShowId());
    }

    public int hashCode() {
        return (getSourceType() + getSourceId() + getShowId()).hashCode();
    }
}
