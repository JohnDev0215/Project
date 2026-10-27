package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ConnectResponseData {
    private final ConnectResponse connect;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public ConnectResponse getConnect() {
        return this.connect;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ConnectResponseData.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getConnect(), ((ConnectResponseData) obj).getConnect());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getConnect());
        return sb.toString().hashCode();
    }
}
