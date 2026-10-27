package com.amazon.video.sdk.player.playlist;

import com.amazon.mp3.environment.Environment;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.error.PlaybackError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistEvent;", "", "()V", "CurrentItemChange", "PlaylistChange", "PlaylistError", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$PlaylistChange;", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$CurrentItemChange;", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$PlaylistError;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PlaylistEvent {
    public /* synthetic */ PlaylistEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PlaylistEvent() {
    }

    /* JADX INFO: compiled from: PlaylistFeature.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$PlaylistChange;", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/playlist/Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/video/sdk/player/playlist/Playlist;", "getPlaylist", "()Lcom/amazon/video/sdk/player/playlist/Playlist;", "<init>", "(Lcom/amazon/video/sdk/player/playlist/Playlist;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class PlaylistChange extends PlaylistEvent {
        private final Playlist playlist;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PlaylistChange) && Intrinsics.areEqual(this.playlist, ((PlaylistChange) other).playlist);
        }

        public int hashCode() {
            return this.playlist.hashCode();
        }

        public String toString() {
            return "PlaylistChange(playlist=" + this.playlist + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlaylistChange(Playlist playlist) {
            super(null);
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            this.playlist = playlist;
        }
    }

    /* JADX INFO: compiled from: PlaylistFeature.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B'\b\u0000\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u0017"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$CurrentItemChange;", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "currentItemIndex", "Ljava/lang/Integer;", "getCurrentItemIndex", "()Ljava/lang/Integer;", "Lcom/amazon/video/sdk/player/ContentConfig;", "currentItem", "Lcom/amazon/video/sdk/player/ContentConfig;", "getCurrentItem", "()Lcom/amazon/video/sdk/player/ContentConfig;", "prevItem", "getPrevItem", "<init>", "(Ljava/lang/Integer;Lcom/amazon/video/sdk/player/ContentConfig;Lcom/amazon/video/sdk/player/ContentConfig;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class CurrentItemChange extends PlaylistEvent {
        private final ContentConfig currentItem;
        private final Integer currentItemIndex;
        private final ContentConfig prevItem;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CurrentItemChange)) {
                return false;
            }
            CurrentItemChange currentItemChange = (CurrentItemChange) other;
            return Intrinsics.areEqual(this.currentItemIndex, currentItemChange.currentItemIndex) && Intrinsics.areEqual(this.currentItem, currentItemChange.currentItem) && Intrinsics.areEqual(this.prevItem, currentItemChange.prevItem);
        }

        public int hashCode() {
            Integer num = this.currentItemIndex;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            ContentConfig contentConfig = this.currentItem;
            int iHashCode2 = (iHashCode + (contentConfig == null ? 0 : contentConfig.hashCode())) * 31;
            ContentConfig contentConfig2 = this.prevItem;
            return iHashCode2 + (contentConfig2 != null ? contentConfig2.hashCode() : 0);
        }

        public String toString() {
            return "CurrentItemChange(currentItemIndex=" + this.currentItemIndex + ", currentItem=" + this.currentItem + ", prevItem=" + this.prevItem + ')';
        }

        public CurrentItemChange(Integer num, ContentConfig contentConfig, ContentConfig contentConfig2) {
            super(null);
            this.currentItemIndex = num;
            this.currentItem = contentConfig;
            this.prevItem = contentConfig2;
        }
    }

    /* JADX INFO: compiled from: PlaylistFeature.kt */
    @Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$PlaylistError;", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/ContentConfig;", "content", "Lcom/amazon/video/sdk/player/ContentConfig;", "getContent", "()Lcom/amazon/video/sdk/player/ContentConfig;", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "error", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "getError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "<init>", "(Lcom/amazon/video/sdk/player/ContentConfig;Lcom/amazon/video/sdk/player/error/PlaybackError;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class PlaylistError extends PlaylistEvent {
        private final ContentConfig content;
        private final PlaybackError error;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PlaylistError)) {
                return false;
            }
            PlaylistError playlistError = (PlaylistError) other;
            return Intrinsics.areEqual(this.content, playlistError.content) && Intrinsics.areEqual(this.error, playlistError.error);
        }

        public int hashCode() {
            ContentConfig contentConfig = this.content;
            return ((contentConfig == null ? 0 : contentConfig.hashCode()) * 31) + this.error.hashCode();
        }

        public String toString() {
            return "PlaylistError(content=" + this.content + ", error=" + this.error + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlaylistError(ContentConfig contentConfig, PlaybackError error) {
            super(null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.content = contentConfig;
            this.error = error;
        }
    }
}
