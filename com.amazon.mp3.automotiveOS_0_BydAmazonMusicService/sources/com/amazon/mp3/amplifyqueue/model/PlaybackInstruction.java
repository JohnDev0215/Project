package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaybackInstruction {
    private final StartOffsetPlaybackInstruction startOffsetInstruction;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public StartOffsetPlaybackInstruction getStartOffsetInstruction() {
        return this.startOffsetInstruction;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlaybackInstruction.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getStartOffsetInstruction(), ((PlaybackInstruction) obj).getStartOffsetInstruction());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStartOffsetInstruction());
        return sb.toString().hashCode();
    }
}
