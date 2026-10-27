package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetActiveDevicePlaybackViewResponseData {
    private final GetActiveDevicePlaybackViewResponse getActiveDevicePlaybackView;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public GetActiveDevicePlaybackViewResponse getGetActiveDevicePlaybackView() {
        return this.getActiveDevicePlaybackView;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetActiveDevicePlaybackViewResponseData.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getGetActiveDevicePlaybackView(), ((GetActiveDevicePlaybackViewResponseData) obj).getGetActiveDevicePlaybackView());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getGetActiveDevicePlaybackView());
        return sb.toString().hashCode();
    }
}
