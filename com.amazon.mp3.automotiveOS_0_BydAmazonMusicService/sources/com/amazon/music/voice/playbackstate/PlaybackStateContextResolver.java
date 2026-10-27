package com.amazon.music.voice.playbackstate;

import android.content.Context;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.music.voice.playbackstate.PlaybackStateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackStateContextResolver implements ContextResolver<PlaybackStateContext> {
    private static final Logger LOG = LoggerFactory.getLogger(PlaybackStateContextResolver.class.getSimpleName());
    private final boolean isAlexaEMPPlaybackEnabled;
    private PlayerStateProvider playerStateProvider;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$resolve$0(MediaOperation mediaOperation) {
        return false;
    }

    public PlaybackStateContextResolver(Context context, PlayerStateProvider playerStateProvider, boolean z) {
        this.playerStateProvider = playerStateProvider;
        this.isAlexaEMPPlaybackEnabled = z;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.ContextResolver
    public PlaybackStateContext resolve() {
        MediaMetadata media = this.playerStateProvider.getMedia();
        if (media == null) {
            media = new MediaMetadata("", "OTHER", new MediaOperationSupported() { // from class: com.amazon.music.voice.playbackstate.PlaybackStateContextResolver$$ExternalSyntheticLambda0
                @Override // com.amazon.music.voice.playbackstate.MediaOperationSupported
                public final boolean isSupported(MediaOperation mediaOperation) {
                    return PlaybackStateContextResolver.lambda$resolve$0(mediaOperation);
                }
            }, 0L, "", "", 0, "");
        }
        return new PlaybackStateContext.Builder().media(media).playerState(this.playerStateProvider.getPlayerState()).trackPosition(this.playerStateProvider.getTrackPositionMs()).enableAlexaEMPPlayback(this.isAlexaEMPPlaybackEnabled).shuffleMode(this.playerStateProvider.getShuffleMode()).repeatMode(this.playerStateProvider.getRepeatMode()).favoriteState(this.playerStateProvider.getFavoriteState()).build();
    }
}
