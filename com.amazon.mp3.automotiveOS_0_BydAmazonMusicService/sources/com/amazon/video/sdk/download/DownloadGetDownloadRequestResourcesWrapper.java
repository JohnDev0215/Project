package com.amazon.video.sdk.download;

import com.amazon.video.sdk.player.error.PlaybackError;
import com.google.common.collect.ImmutableList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadGetDownloadRequestResources.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadGetDownloadRequestResourcesWrapper;", "", "audioTrackIds", "Lcom/google/common/collect/ImmutableList;", "", "error", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "(Lcom/google/common/collect/ImmutableList;Lcom/amazon/video/sdk/player/error/PlaybackError;)V", "getAudioTrackIds", "()Lcom/google/common/collect/ImmutableList;", "getError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadGetDownloadRequestResourcesWrapper {
    private final ImmutableList<String> audioTrackIds;
    private final PlaybackError error;

    public DownloadGetDownloadRequestResourcesWrapper(ImmutableList<String> audioTrackIds, PlaybackError playbackError) {
        Intrinsics.checkNotNullParameter(audioTrackIds, "audioTrackIds");
        this.audioTrackIds = audioTrackIds;
        this.error = playbackError;
    }

    public final ImmutableList<String> getAudioTrackIds() {
        return this.audioTrackIds;
    }

    public final PlaybackError getError() {
        return this.error;
    }
}
