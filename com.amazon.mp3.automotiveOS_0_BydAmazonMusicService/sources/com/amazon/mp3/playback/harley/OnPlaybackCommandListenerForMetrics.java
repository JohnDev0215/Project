package com.amazon.mp3.playback.harley;

import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnPlaybackCommandListener;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;

/* JADX INFO: loaded from: classes3.dex */
public class OnPlaybackCommandListenerForMetrics extends OnPlaybackCommandListener {
    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onFastForward(final PlaybackController controller) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onNormalSpeed(final PlaybackController controller) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onPause(final PlaybackController controller) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onPlay(final PlaybackController controller, final long requestTime) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onResolveMaxConcurrency(final PlaybackController controller, final String deviceName, final boolean shouldPlay, final long requestTime) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRestartMediaItem(final PlaybackController controller) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRewind(final PlaybackController controller) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSeek(final PlaybackController controller, final long positionMillis, final int seekMode) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSetRepeatMode(final PlaybackController controller, final RepeatMode repeatMode) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSetShuffled(final PlaybackController controller, final boolean shuffled) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSkip(final PlaybackController controller, final int numSkips, final ChangeReason changeReason) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSkipToIndex(final PlaybackController controller, final int index) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onStop(final PlaybackController controller, final ChangeReason changeReason) {
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onThumbsUp(final PlaybackController controller) {
        onTrackRated(controller, ActionType.THUMBS_UP);
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onThumbsDown(final PlaybackController controller) {
        onTrackRated(controller, ActionType.THUMBS_DOWN);
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRevertThumbsUp(final PlaybackController controller) {
        onTrackRated(controller, ActionType.THUMBS_UP_UNDO);
    }

    @Override // com.amazon.music.media.playback.OnPlaybackCommandListener, com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRevertThumbsDown(final PlaybackController controller) {
        onTrackRated(controller, ActionType.THUMBS_DOWN_UNDO);
    }

    private void onTrackRated(final PlaybackController controller, final ActionType actionType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(controller.getControlSource() == ControlSource.ALEXA ? InteractionType.VOICE : InteractionType.TAP).build());
    }
}
