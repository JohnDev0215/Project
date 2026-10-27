package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class StreamingStatusResponse {
    final String deviceId;
    final String deviceName;
    final long elapsedTime;
    final String streamingStatus;

    public StreamingStatusResponse(String str, String str2, String str3, long j) {
        this.deviceId = str;
        this.deviceName = str2;
        this.streamingStatus = str3;
        this.elapsedTime = j;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getStreamingStatus() {
        return this.streamingStatus;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public String toString() {
        return "StreamingStatusResponse{deviceId=" + this.deviceId + ",deviceName=" + this.deviceName + ",streamingStatus=" + this.streamingStatus + ",elapsedTime=" + this.elapsedTime + "}";
    }
}
