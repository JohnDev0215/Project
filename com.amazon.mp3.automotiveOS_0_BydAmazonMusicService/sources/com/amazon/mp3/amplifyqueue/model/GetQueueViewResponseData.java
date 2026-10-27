package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public class GetQueueViewResponseData {
    private final GetQueueViewResponse getQueueView;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public GetQueueViewResponse getQueueView() {
        return this.getQueueView;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getQueueView(), ((GetQueueViewResponseData) obj).getQueueView());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getQueueView());
        return sb.toString().hashCode();
    }
}
