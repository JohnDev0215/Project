package com.amazon.video.sdk.download;

import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.google.common.base.Preconditions;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadEnqueueUserDownload.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadEnqueueUserDownloadWrapper;", "", "download", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "error", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "(Lcom/amazon/avod/userdownload/PlaybackDownload;Lcom/amazon/video/sdk/player/error/PlaybackError;)V", "getDownload", "()Lcom/amazon/avod/userdownload/PlaybackDownload;", "getError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadEnqueueUserDownloadWrapper {
    private final PlaybackDownload download;
    private final PlaybackError error;

    public DownloadEnqueueUserDownloadWrapper(PlaybackDownload playbackDownload, PlaybackError playbackError) {
        this.download = playbackDownload;
        this.error = playbackError;
        Preconditions.checkArgument((playbackDownload == null && playbackError == null) ? false : true, "Wrapper contains all null values", new Object[0]);
    }

    public final PlaybackDownload getDownload() {
        return this.download;
    }

    public final PlaybackError getError() {
        return this.error;
    }
}
