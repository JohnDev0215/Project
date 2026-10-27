package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class CuePoints {
    private final Integer endOffsetInMs;
    private final Integer startOffsetInMs;

    public interface BuildStep {
    }

    public static class Builder implements StartOffsetInMsStep, EndOffsetInMsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EndOffsetInMsStep {
    }

    public interface StartOffsetInMsStep {
    }

    public Integer getStartOffsetInMs() {
        return this.startOffsetInMs;
    }

    public Integer getEndOffsetInMs() {
        return this.endOffsetInMs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CuePoints.class != obj.getClass()) {
            return false;
        }
        CuePoints cuePoints = (CuePoints) obj;
        return ObjectsCompat.equals(getStartOffsetInMs(), cuePoints.getStartOffsetInMs()) && ObjectsCompat.equals(getEndOffsetInMs(), cuePoints.getEndOffsetInMs());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStartOffsetInMs());
        sb.append(getEndOffsetInMs());
        return sb.toString().hashCode();
    }
}
