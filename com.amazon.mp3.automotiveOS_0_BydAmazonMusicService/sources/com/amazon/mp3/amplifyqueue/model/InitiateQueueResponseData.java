package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class InitiateQueueResponseData {
    private final InitiateQueueResponse initiateQueue;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public InitiateQueueResponse getInitiateQueue() {
        return this.initiateQueue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || InitiateQueueResponseData.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getInitiateQueue(), ((InitiateQueueResponseData) obj).getInitiateQueue());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getInitiateQueue());
        return sb.toString().hashCode();
    }
}
