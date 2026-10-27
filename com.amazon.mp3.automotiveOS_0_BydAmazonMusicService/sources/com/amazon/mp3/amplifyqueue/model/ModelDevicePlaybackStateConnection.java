package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.temporal.Temporal;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class ModelDevicePlaybackStateConnection {
    private final List<DevicePlaybackState> items;
    private final String nextToken;
    private final Temporal.Timestamp startedAt;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public List<DevicePlaybackState> getItems() {
        return this.items;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Temporal.Timestamp getStartedAt() {
        return this.startedAt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ModelDevicePlaybackStateConnection.class != obj.getClass()) {
            return false;
        }
        ModelDevicePlaybackStateConnection modelDevicePlaybackStateConnection = (ModelDevicePlaybackStateConnection) obj;
        return ObjectsCompat.equals(getItems(), modelDevicePlaybackStateConnection.getItems()) && ObjectsCompat.equals(getNextToken(), modelDevicePlaybackStateConnection.getNextToken()) && ObjectsCompat.equals(getStartedAt(), modelDevicePlaybackStateConnection.getStartedAt());
    }

    public int hashCode() {
        return (getItems() + getNextToken() + getStartedAt()).hashCode();
    }
}
