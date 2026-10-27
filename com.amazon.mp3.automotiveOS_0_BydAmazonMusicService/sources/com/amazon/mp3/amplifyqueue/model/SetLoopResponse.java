package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SetLoopResponse {
    private final SetLoopResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public SetLoopResponseStatusEnum getStatus() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SetLoopResponse.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getStatus(), ((SetLoopResponse) obj).getStatus());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatus());
        return sb.toString().hashCode();
    }
}
