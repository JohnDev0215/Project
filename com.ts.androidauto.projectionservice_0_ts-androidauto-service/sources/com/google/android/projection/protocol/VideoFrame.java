package com.google.android.projection.protocol;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class VideoFrame {
    public ByteBuffer data;
    public int sessionId;
    public long timestamp;

    public VideoFrame(int sessionId, long timestamp, ByteBuffer data) {
        this.data = data;
        this.sessionId = sessionId;
        this.timestamp = timestamp;
    }
}
