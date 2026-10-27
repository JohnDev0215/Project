package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SourceDeviceTransferSeed {
    private final DeviceIdentifier sourceDevice;

    public interface BuildStep {
    }

    public static class Builder implements SourceDeviceStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface SourceDeviceStep {
    }

    public DeviceIdentifier getSourceDevice() {
        return this.sourceDevice;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SourceDeviceTransferSeed.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getSourceDevice(), ((SourceDeviceTransferSeed) obj).getSourceDevice());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSourceDevice());
        return sb.toString().hashCode();
    }
}
