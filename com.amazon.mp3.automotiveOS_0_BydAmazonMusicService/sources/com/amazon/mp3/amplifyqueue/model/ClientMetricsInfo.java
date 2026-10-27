package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ClientMetricsInfo {
    private final String contentBlockRef;

    public interface BuildStep {
        ClientMetricsInfo build();

        BuildStep contentBlockRef(String str);
    }

    public String getContentBlockRef() {
        return this.contentBlockRef;
    }

    private ClientMetricsInfo(String str) {
        this.contentBlockRef = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ClientMetricsInfo.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getContentBlockRef(), ((ClientMetricsInfo) obj).getContentBlockRef());
    }

    public int hashCode() {
        return (getContentBlockRef()).hashCode();
    }

    public static BuildStep builder() {
        return new Builder();
    }

    public static class Builder implements BuildStep {
        private String contentBlockRef;

        @Override // com.amazon.mp3.amplifyqueue.model.ClientMetricsInfo.BuildStep
        public ClientMetricsInfo build() {
            return new ClientMetricsInfo(this.contentBlockRef);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.ClientMetricsInfo.BuildStep
        public BuildStep contentBlockRef(String str) {
            this.contentBlockRef = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.ClientMetricsInfo.Builder, com.amazon.mp3.amplifyqueue.model.ClientMetricsInfo.BuildStep
        public CopyOfBuilder contentBlockRef(String str) {
            return (CopyOfBuilder) super.contentBlockRef(str);
        }
    }
}
