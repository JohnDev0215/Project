package com.amazon.mp3.lyrics.item;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsStatusChanges {
    private List<String> mAsinList;
    private long mLastFetchTimestamp;
    private String mPagingToken;
    private String mStatusChangeType;

    public enum StatusChangeType {
        UPDATE("UPDATE"),
        TAKEDOWN("TAKEDOWN");

        private final String mField;

        StatusChangeType(String field) {
            this.mField = field;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mField;
        }
    }

    public String getPagingToken() {
        return this.mPagingToken;
    }

    public void setPagingToken(final String pagingToken) {
        this.mPagingToken = pagingToken;
    }

    public void setStatusChangeType(final String statusChangeType) {
        this.mStatusChangeType = statusChangeType;
    }

    public List<String> getAsinList() {
        return this.mAsinList;
    }

    public void setAsinList(final List<String> asinList) {
        this.mAsinList = asinList;
    }

    public long getLastFetchTimestamp() {
        return this.mLastFetchTimestamp;
    }

    public void setLastFetchTimestamp(long listFetchTimestamp) {
        this.mLastFetchTimestamp = listFetchTimestamp;
    }
}
