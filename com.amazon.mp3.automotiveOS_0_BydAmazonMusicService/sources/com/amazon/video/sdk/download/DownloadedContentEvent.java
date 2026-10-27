package com.amazon.video.sdk.download;

import com.amazon.video.sdk.player.error.PlaybackError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadedContentEvent.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadedContentEvent;", "", "<init>", "()V", "DownloadedContentError", "DownloadedContentStateChange", "Lcom/amazon/video/sdk/download/DownloadedContentEvent$DownloadedContentStateChange;", "Lcom/amazon/video/sdk/download/DownloadedContentEvent$DownloadedContentError;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public abstract class DownloadedContentEvent {
    public /* synthetic */ DownloadedContentEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private DownloadedContentEvent() {
    }

    /* JADX INFO: compiled from: DownloadedContentEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadedContentEvent$DownloadedContentStateChange;", "Lcom/amazon/video/sdk/download/DownloadedContentEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/download/DownloadedContentState;", "newState", "Lcom/amazon/video/sdk/download/DownloadedContentState;", "getNewState", "()Lcom/amazon/video/sdk/download/DownloadedContentState;", "<init>", "(Lcom/amazon/video/sdk/download/DownloadedContentState;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DownloadedContentStateChange extends DownloadedContentEvent {
        private final DownloadedContentState newState;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadedContentStateChange) && this.newState == ((DownloadedContentStateChange) other).newState;
        }

        public int hashCode() {
            return this.newState.hashCode();
        }

        public String toString() {
            return "DownloadedContentStateChange(newState=" + this.newState + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadedContentStateChange(DownloadedContentState newState) {
            super(null);
            Intrinsics.checkNotNullParameter(newState, "newState");
            this.newState = newState;
        }
    }

    /* JADX INFO: compiled from: DownloadedContentEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadedContentEvent$DownloadedContentError;", "Lcom/amazon/video/sdk/download/DownloadedContentEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "error", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "getError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "<init>", "(Lcom/amazon/video/sdk/player/error/PlaybackError;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DownloadedContentError extends DownloadedContentEvent {
        private final PlaybackError error;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadedContentError) && Intrinsics.areEqual(this.error, ((DownloadedContentError) other).error);
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        public String toString() {
            return "DownloadedContentError(error=" + this.error + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadedContentError(PlaybackError error) {
            super(null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
        }
    }
}
