package com.amazon.video.sdk.player.error;

import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackError.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005¨\u0006\u000e"}, d2 = {"Lcom/amazon/video/sdk/player/error/PlaybackError;", "", "errorCode", "", "getErrorCode", "()I", "errorDescription", "getErrorDescription", "errorDomain", "Lcom/amazon/video/sdk/player/error/ErrorDomain;", "getErrorDomain", "()Lcom/amazon/video/sdk/player/error/ErrorDomain;", "errorMessage", "getErrorMessage", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaybackError {
    int getErrorCode();

    int getErrorDescription();

    ErrorDomain getErrorDomain();

    int getErrorMessage();
}
