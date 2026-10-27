package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetNextEntityResponse {
    private final EntityResponseTuple naturalNext;
    private final EntityResponseTuple userNext;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public EntityResponseTuple getNaturalNext() {
        return this.naturalNext;
    }

    public EntityResponseTuple getUserNext() {
        return this.userNext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetNextEntityResponse.class != obj.getClass()) {
            return false;
        }
        GetNextEntityResponse getNextEntityResponse = (GetNextEntityResponse) obj;
        return ObjectsCompat.equals(getNaturalNext(), getNextEntityResponse.getNaturalNext()) && ObjectsCompat.equals(getUserNext(), getNextEntityResponse.getUserNext());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNaturalNext());
        sb.append(getUserNext());
        return sb.toString().hashCode();
    }
}
