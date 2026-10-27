package com.amazon.music.metrics.mts;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface MTSEventUploader {
    void uploadEvents(List<MTSEvent> list, MTSEventUploaderCallback mTSEventUploaderCallback);
}
