package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GetActiveDevicePlaybackViewResponse {
    private final List<ActiveDevicePlayback> activeDevicePlaybacks;
    private final GetActiveDevicePlaybackViewResponseEnum status;

    public interface ActiveDevicePlaybacksStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, ActiveDevicePlaybacksStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public GetActiveDevicePlaybackViewResponseEnum getStatus() {
        return this.status;
    }

    public List<ActiveDevicePlayback> getActiveDevicePlaybacks() {
        return this.activeDevicePlaybacks;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetActiveDevicePlaybackViewResponse.class != obj.getClass()) {
            return false;
        }
        GetActiveDevicePlaybackViewResponse getActiveDevicePlaybackViewResponse = (GetActiveDevicePlaybackViewResponse) obj;
        return ObjectsCompat.equals(getStatus(), getActiveDevicePlaybackViewResponse.getStatus()) && ObjectsCompat.equals(getActiveDevicePlaybacks(), getActiveDevicePlaybackViewResponse.getActiveDevicePlaybacks());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatus());
        sb.append(getActiveDevicePlaybacks());
        return sb.toString().hashCode();
    }
}
