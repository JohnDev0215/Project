package com.amazon.music.platform.playback.commands;

import com.amazon.music.platform.playback.data.PlaybackRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackCommandEvents.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents;", "", "<init>", "()V", "InitiateCommandEvent", "NextCommandEvent", "PauseCommandEvent", "PlayCommandEvent", "PreviousCommandEvent", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class PlaybackCommandEvents {

    /* JADX INFO: compiled from: PlaybackCommandEvents.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents$InitiateCommandEvent;", "", "Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "playbackRequest", "Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "getPlaybackRequest", "()Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "<init>", "(Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents;Lcom/amazon/music/platform/playback/data/PlaybackRequest;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public final class InitiateCommandEvent implements PlaybackCommandEvent {
        private final PlaybackRequest playbackRequest;
        final /* synthetic */ PlaybackCommandEvents this$0;

        public InitiateCommandEvent(PlaybackCommandEvents playbackCommandEvents, PlaybackRequest playbackRequest) {
            Intrinsics.checkNotNullParameter(playbackRequest, "playbackRequest");
            this.this$0 = playbackCommandEvents;
            this.playbackRequest = playbackRequest;
        }

        public PlaybackRequest getPlaybackRequest() {
            return this.playbackRequest;
        }
    }

    /* JADX INFO: compiled from: PlaybackCommandEvents.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents$PlayCommandEvent;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "(Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class PlayCommandEvent implements PlaybackCommandEvent {
        public PlayCommandEvent() {
        }
    }

    /* JADX INFO: compiled from: PlaybackCommandEvents.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents$PauseCommandEvent;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "(Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class PauseCommandEvent implements PlaybackCommandEvent {
        public PauseCommandEvent() {
        }
    }

    /* JADX INFO: compiled from: PlaybackCommandEvents.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents$PreviousCommandEvent;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "(Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class PreviousCommandEvent implements PlaybackCommandEvent {
        public PreviousCommandEvent() {
        }
    }

    /* JADX INFO: compiled from: PlaybackCommandEvents.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents$NextCommandEvent;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "(Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvents;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class NextCommandEvent implements PlaybackCommandEvent {
        public NextCommandEvent() {
        }
    }
}
