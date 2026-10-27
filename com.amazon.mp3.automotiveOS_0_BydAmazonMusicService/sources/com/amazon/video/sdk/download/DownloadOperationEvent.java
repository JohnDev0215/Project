package com.amazon.video.sdk.download;

import com.amazon.video.sdk.player.error.PlaybackError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadOperationEvent.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationEvent;", "", "<init>", "()V", "DownloadOperationError", "DownloadOperationStateChange", "DownloadProgress", "DownloadedContentReadyForPlayback", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadProgress;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadOperationStateChange;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadOperationError;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadedContentReadyForPlayback;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public abstract class DownloadOperationEvent {
    public /* synthetic */ DownloadOperationEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private DownloadOperationEvent() {
    }

    /* JADX INFO: compiled from: DownloadOperationEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadProgress;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "percentComplete", "D", "getPercentComplete", "()D", "<init>", "(D)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DownloadProgress extends DownloadOperationEvent {
        private final double percentComplete;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadProgress) && Intrinsics.areEqual((Object) Double.valueOf(this.percentComplete), (Object) Double.valueOf(((DownloadProgress) other).percentComplete));
        }

        public int hashCode() {
            return Double.hashCode(this.percentComplete);
        }

        public String toString() {
            return "DownloadProgress(percentComplete=" + this.percentComplete + ')';
        }

        public DownloadProgress(double d) {
            super(null);
            this.percentComplete = d;
        }

        public final double getPercentComplete() {
            return this.percentComplete;
        }
    }

    /* JADX INFO: compiled from: DownloadOperationEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadOperationStateChange;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/download/DownloadOperationState;", "newState", "Lcom/amazon/video/sdk/download/DownloadOperationState;", "getNewState", "()Lcom/amazon/video/sdk/download/DownloadOperationState;", "<init>", "(Lcom/amazon/video/sdk/download/DownloadOperationState;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DownloadOperationStateChange extends DownloadOperationEvent {
        private final DownloadOperationState newState;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadOperationStateChange) && this.newState == ((DownloadOperationStateChange) other).newState;
        }

        public int hashCode() {
            return this.newState.hashCode();
        }

        public String toString() {
            return "DownloadOperationStateChange(newState=" + this.newState + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadOperationStateChange(DownloadOperationState newState) {
            super(null);
            Intrinsics.checkNotNullParameter(newState, "newState");
            this.newState = newState;
        }
    }

    /* JADX INFO: compiled from: DownloadOperationEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadOperationError;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "error", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "getError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "<init>", "(Lcom/amazon/video/sdk/player/error/PlaybackError;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DownloadOperationError extends DownloadOperationEvent {
        private final PlaybackError error;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadOperationError) && Intrinsics.areEqual(this.error, ((DownloadOperationError) other).error);
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        public String toString() {
            return "DownloadOperationError(error=" + this.error + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadOperationError(PlaybackError error) {
            super(null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
        }
    }

    /* JADX INFO: compiled from: DownloadOperationEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadedContentReadyForPlayback;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/download/DownloadedContent;", "downloadedContent", "Lcom/amazon/video/sdk/download/DownloadedContent;", "getDownloadedContent", "()Lcom/amazon/video/sdk/download/DownloadedContent;", "<init>", "(Lcom/amazon/video/sdk/download/DownloadedContent;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DownloadedContentReadyForPlayback extends DownloadOperationEvent {
        private final DownloadedContent downloadedContent;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadedContentReadyForPlayback) && Intrinsics.areEqual(this.downloadedContent, ((DownloadedContentReadyForPlayback) other).downloadedContent);
        }

        public int hashCode() {
            return this.downloadedContent.hashCode();
        }

        public String toString() {
            return "DownloadedContentReadyForPlayback(downloadedContent=" + this.downloadedContent + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadedContentReadyForPlayback(DownloadedContent downloadedContent) {
            super(null);
            Intrinsics.checkNotNullParameter(downloadedContent, "downloadedContent");
            this.downloadedContent = downloadedContent;
        }
    }
}
