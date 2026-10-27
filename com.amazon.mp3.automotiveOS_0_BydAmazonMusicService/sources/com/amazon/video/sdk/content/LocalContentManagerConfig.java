package com.amazon.video.sdk.content;

import com.amazon.avod.media.TimeSpan;
import kotlin.Metadata;

/* JADX INFO: compiled from: LocalContentManagerConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "", "", "getAllocationBytes", "()J", "allocationBytes", "Lcom/amazon/avod/media/TimeSpan;", "getCacheDuration", "()Lcom/amazon/avod/media/TimeSpan;", "cacheDuration", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface LocalContentManagerConfig {
    long getAllocationBytes();

    TimeSpan getCacheDuration();
}
