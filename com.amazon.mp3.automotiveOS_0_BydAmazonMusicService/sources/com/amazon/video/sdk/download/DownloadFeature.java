package com.amazon.video.sdk.download;

import com.amazon.video.sdk.Feature;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u001c\u0010\u0007\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadFeature;", "Lcom/amazon/video/sdk/Feature;", "", "getMinimumDownloadProgressForPlayback", "()D", "setMinimumDownloadProgressForPlayback", "(D)V", "minimumDownloadProgressForPlayback", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface DownloadFeature extends Feature {
    double getMinimumDownloadProgressForPlayback();

    void setMinimumDownloadProgressForPlayback(double d);
}
