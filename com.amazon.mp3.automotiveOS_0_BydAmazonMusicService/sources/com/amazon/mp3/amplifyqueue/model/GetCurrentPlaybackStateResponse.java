package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetCurrentPlaybackStateResponse {
    private final DevicePlaybackState devicePlaybackState;
    private final EntityReference entityReference;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public DevicePlaybackState getDevicePlaybackState() {
        return this.devicePlaybackState;
    }

    public EntityReference getEntityReference() {
        return this.entityReference;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetCurrentPlaybackStateResponse.class != obj.getClass()) {
            return false;
        }
        GetCurrentPlaybackStateResponse getCurrentPlaybackStateResponse = (GetCurrentPlaybackStateResponse) obj;
        return ObjectsCompat.equals(getDevicePlaybackState(), getCurrentPlaybackStateResponse.getDevicePlaybackState()) && ObjectsCompat.equals(getEntityReference(), getCurrentPlaybackStateResponse.getEntityReference());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDevicePlaybackState());
        sb.append(getEntityReference());
        return sb.toString().hashCode();
    }
}
