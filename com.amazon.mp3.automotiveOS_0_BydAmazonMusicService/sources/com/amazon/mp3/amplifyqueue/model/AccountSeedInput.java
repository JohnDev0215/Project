package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class AccountSeedInput {
    private final AccountContentTypeEnum contentType;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccountSeedInput.class != obj.getClass()) {
            return false;
        }
        AccountSeedInput accountSeedInput = (AccountSeedInput) obj;
        return ObjectsCompat.equals(getContentType(), accountSeedInput.getContentType()) && ObjectsCompat.equals(getSortOrder(), accountSeedInput.getSortOrder());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getContentType());
        sb.append(getSortOrder());
        return sb.toString().hashCode();
    }
}
