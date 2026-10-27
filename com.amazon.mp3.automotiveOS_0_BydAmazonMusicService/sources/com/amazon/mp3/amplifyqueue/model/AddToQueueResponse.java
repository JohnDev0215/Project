package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class AddToQueueResponse {
    private final AddToQueueResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public AddToQueueResponseStatusEnum getStatus() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AddToQueueResponse.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getStatus(), ((AddToQueueResponse) obj).getStatus());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatus());
        return sb.toString().hashCode();
    }
}
