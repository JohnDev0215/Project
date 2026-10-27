package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class AccountSeedContextInput {
    private final AccountContentTypeEnum contentType;
    private final MetricsContextInput metricsContext;
    private final AccountContentSortOrderEnum sortOrder;

    public interface BuildStep {
    }

    public static class Builder implements ContentTypeStep, SortOrderStep, BuildStep {
    }

    public interface ContentTypeStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface SortOrderStep {
    }

    public AccountContentTypeEnum getContentType() {
        return this.contentType;
    }

    public AccountContentSortOrderEnum getSortOrder() {
        return this.sortOrder;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccountSeedContextInput.class != obj.getClass()) {
            return false;
        }
        AccountSeedContextInput accountSeedContextInput = (AccountSeedContextInput) obj;
        return ObjectsCompat.equals(getContentType(), accountSeedContextInput.getContentType()) && ObjectsCompat.equals(getSortOrder(), accountSeedContextInput.getSortOrder()) && ObjectsCompat.equals(getMetricsContext(), accountSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getContentType());
        sb.append(getSortOrder());
        sb.append(getMetricsContext());
        return sb.toString().hashCode();
    }
}
