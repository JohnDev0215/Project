package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class StartOffsetPlaybackInstruction {
    private final Integer startOffsetMs;

    public interface BuildStep {
    }

    public static class Builder implements StartOffsetMsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StartOffsetMsStep {
    }

    public Integer getStartOffsetMs() {
        return this.startOffsetMs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StartOffsetPlaybackInstruction.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getStartOffsetMs(), ((StartOffsetPlaybackInstruction) obj).getStartOffsetMs());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStartOffsetMs());
        return sb.toString().hashCode();
    }
}
