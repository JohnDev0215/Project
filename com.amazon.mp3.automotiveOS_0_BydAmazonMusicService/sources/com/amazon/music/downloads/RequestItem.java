package com.amazon.music.downloads;

/* JADX INFO: loaded from: classes4.dex */
public class RequestItem {
    private final boolean mIsBackground;
    private final String mRequestId;
    private final RequestType mRequestType;
    private final long mTimestamp;

    public enum RequestType {
        GROUP,
        ITEM
    }

    public RequestItem(String str, RequestType requestType, boolean z) {
        this(str, requestType, z, 0L);
    }

    public RequestItem(String str, RequestType requestType, boolean z, long j) {
        this.mRequestId = str;
        this.mRequestType = requestType;
        this.mIsBackground = z;
        this.mTimestamp = j;
    }

    public RequestType getRequestType() {
        return this.mRequestType;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public boolean isBackground() {
        return this.mIsBackground;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public int hashCode() {
        String str = this.mRequestId;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof RequestItem) && this.mRequestId.equals(((RequestItem) obj).mRequestId);
    }

    public String toString() {
        return "RequestItem{mRequestId='" + this.mRequestId + "', mRequestType=" + this.mRequestType + ", mIsBackground=" + this.mIsBackground + ", mTimestamp=" + this.mTimestamp + '}';
    }
}
