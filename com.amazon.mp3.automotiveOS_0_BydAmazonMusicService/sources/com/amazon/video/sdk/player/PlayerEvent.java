package com.amazon.video.sdk.player;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.amazon.video.sdk.player.timeline.TimeData;
import com.amazon.video.sdk.player.timeline.Timeline;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Player.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent;", "", "()V", "ContentError", "ContentStateChange", "PlaybackStateChange", "PlayerError", "TimeDataChange", "TimelineChange", "TimelineEnded", "Lcom/amazon/video/sdk/player/PlayerEvent$ContentStateChange;", "Lcom/amazon/video/sdk/player/PlayerEvent$PlaybackStateChange;", "Lcom/amazon/video/sdk/player/PlayerEvent$TimeDataChange;", "Lcom/amazon/video/sdk/player/PlayerEvent$TimelineChange;", "Lcom/amazon/video/sdk/player/PlayerEvent$ContentError;", "Lcom/amazon/video/sdk/player/PlayerEvent$PlayerError;", "Lcom/amazon/video/sdk/player/PlayerEvent$TimelineEnded;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PlayerEvent {
    public /* synthetic */ PlayerEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PlayerEvent() {
    }

    /* JADX INFO: compiled from: Player.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent$ContentStateChange;", "Lcom/amazon/video/sdk/player/PlayerEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/ContentState;", "contentState", "Lcom/amazon/video/sdk/player/ContentState;", "getContentState", "()Lcom/amazon/video/sdk/player/ContentState;", "<init>", "(Lcom/amazon/video/sdk/player/ContentState;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class ContentStateChange extends PlayerEvent {
        private final ContentState contentState;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ContentStateChange) && this.contentState == ((ContentStateChange) other).contentState;
        }

        public int hashCode() {
            return this.contentState.hashCode();
        }

        public String toString() {
            return "ContentStateChange(contentState=" + this.contentState + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ContentStateChange(ContentState contentState) {
            super(null);
            Intrinsics.checkNotNullParameter(contentState, "contentState");
            this.contentState = contentState;
        }

        public final ContentState getContentState() {
            return this.contentState;
        }
    }

    /* JADX INFO: compiled from: Player.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent$PlaybackStateChange;", "Lcom/amazon/video/sdk/player/PlayerEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/PlaybackState;", TetheredMessageKey.playbackState, "Lcom/amazon/video/sdk/player/PlaybackState;", "getPlaybackState", "()Lcom/amazon/video/sdk/player/PlaybackState;", "<init>", "(Lcom/amazon/video/sdk/player/PlaybackState;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class PlaybackStateChange extends PlayerEvent {
        private final PlaybackState playbackState;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PlaybackStateChange) && this.playbackState == ((PlaybackStateChange) other).playbackState;
        }

        public int hashCode() {
            return this.playbackState.hashCode();
        }

        public String toString() {
            return "PlaybackStateChange(playbackState=" + this.playbackState + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlaybackStateChange(PlaybackState playbackState) {
            super(null);
            Intrinsics.checkNotNullParameter(playbackState, "playbackState");
            this.playbackState = playbackState;
        }

        public final PlaybackState getPlaybackState() {
            return this.playbackState;
        }
    }

    /* JADX INFO: compiled from: Player.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent$TimeDataChange;", "Lcom/amazon/video/sdk/player/PlayerEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "timeData", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "getTimeData", "()Lcom/amazon/video/sdk/player/timeline/TimeData;", "<init>", "(Lcom/amazon/video/sdk/player/timeline/TimeData;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class TimeDataChange extends PlayerEvent {
        private final TimeData timeData;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TimeDataChange) && Intrinsics.areEqual(this.timeData, ((TimeDataChange) other).timeData);
        }

        public int hashCode() {
            return this.timeData.hashCode();
        }

        public String toString() {
            return "TimeDataChange(timeData=" + this.timeData + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TimeDataChange(TimeData timeData) {
            super(null);
            Intrinsics.checkNotNullParameter(timeData, "timeData");
            this.timeData = timeData;
        }

        public final TimeData getTimeData() {
            return this.timeData;
        }
    }

    /* JADX INFO: compiled from: Player.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent$TimelineChange;", "Lcom/amazon/video/sdk/player/PlayerEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "timeline", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "getTimeline", "()Lcom/amazon/video/sdk/player/timeline/Timeline;", "<init>", "(Lcom/amazon/video/sdk/player/timeline/Timeline;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class TimelineChange extends PlayerEvent {
        private final Timeline timeline;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TimelineChange) && Intrinsics.areEqual(this.timeline, ((TimelineChange) other).timeline);
        }

        public int hashCode() {
            return this.timeline.hashCode();
        }

        public String toString() {
            return "TimelineChange(timeline=" + this.timeline + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TimelineChange(Timeline timeline) {
            super(null);
            Intrinsics.checkNotNullParameter(timeline, "timeline");
            this.timeline = timeline;
        }
    }

    /* JADX INFO: compiled from: Player.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent$ContentError;", "Lcom/amazon/video/sdk/player/PlayerEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "playbackError", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "getPlaybackError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "<init>", "(Lcom/amazon/video/sdk/player/error/PlaybackError;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class ContentError extends PlayerEvent {
        private final PlaybackError playbackError;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ContentError) && Intrinsics.areEqual(this.playbackError, ((ContentError) other).playbackError);
        }

        public int hashCode() {
            return this.playbackError.hashCode();
        }

        public String toString() {
            return "ContentError(playbackError=" + this.playbackError + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ContentError(PlaybackError playbackError) {
            super(null);
            Intrinsics.checkNotNullParameter(playbackError, "playbackError");
            this.playbackError = playbackError;
        }

        public final PlaybackError getPlaybackError() {
            return this.playbackError;
        }
    }

    /* JADX INFO: compiled from: Player.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent$PlayerError;", "Lcom/amazon/video/sdk/player/PlayerEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "playbackError", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "getPlaybackError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "<init>", "(Lcom/amazon/video/sdk/player/error/PlaybackError;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class PlayerError extends PlayerEvent {
        private final PlaybackError playbackError;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PlayerError) && Intrinsics.areEqual(this.playbackError, ((PlayerError) other).playbackError);
        }

        public int hashCode() {
            return this.playbackError.hashCode();
        }

        public String toString() {
            return "PlayerError(playbackError=" + this.playbackError + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlayerError(PlaybackError playbackError) {
            super(null);
            Intrinsics.checkNotNullParameter(playbackError, "playbackError");
            this.playbackError = playbackError;
        }

        public final PlaybackError getPlaybackError() {
            return this.playbackError;
        }
    }

    /* JADX INFO: compiled from: Player.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerEvent$TimelineEnded;", "Lcom/amazon/video/sdk/player/PlayerEvent;", "()V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TimelineEnded extends PlayerEvent {
        public static final TimelineEnded INSTANCE = new TimelineEnded();

        private TimelineEnded() {
            super(null);
        }
    }
}
