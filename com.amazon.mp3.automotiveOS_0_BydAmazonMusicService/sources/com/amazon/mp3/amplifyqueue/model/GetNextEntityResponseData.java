package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetNextEntityResponseData {
    private final GetNextEntityResponse getNextEntity;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public GetNextEntityResponse getGetNextEntity() {
        return this.getNextEntity;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetNextEntityResponseData.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getGetNextEntity(), ((GetNextEntityResponseData) obj).getGetNextEntity());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getGetNextEntity());
        return sb.toString().hashCode();
    }
}
