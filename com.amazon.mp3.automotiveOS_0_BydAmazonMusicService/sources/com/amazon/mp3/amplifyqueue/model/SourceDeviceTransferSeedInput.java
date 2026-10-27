package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SourceDeviceTransferSeedInput {
    private final DeviceIdentifierInput sourceDevice;

    public interface BuildStep {
    }

    public static class Builder implements SourceDeviceStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface SourceDeviceStep {
    }

    public DeviceIdentifierInput getSourceDevice() {
        return this.sourceDevice;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SourceDeviceTransferSeedInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getSourceDevice(), ((SourceDeviceTransferSeedInput) obj).getSourceDevice());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSourceDevice());
        return sb.toString().hashCode();
    }
}
