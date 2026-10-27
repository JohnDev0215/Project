package com.amazon.music.media.playback.player;

import UpsellInterface.v1_0.PlaybackMode;
import android.content.Context;
import android.net.Uri;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpPlayer extends AbstractPlayer {
    @Override // com.amazon.music.media.playback.player.Player
    public boolean canPlay(Uri uri) {
        return false;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPause(boolean z, ChangeReason changeReason, ControlSource controlSource) {
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConnect() {
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onCurrentVolumeChanged(float f) {
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onDisconnect() {
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onQueueChanged(int i, int i2, int i3, int i4, int i5, ChangeReason changeReason, ControlSource controlSource) {
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void terminate(PlayRequest playRequest, ChangeReason changeReason, ControlSource controlSource, long j) {
    }

    public NoOpPlayer(Context context) {
        super(context, PlaybackMode.NONE, null, true, true, true);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void seek(long j, ChangeReason changeReason, ControlSource controlSource) {
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest != null) {
            currentPlayRequest.getPositionTracker().setPosition(j);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPlay(ChangeReason changeReason, ControlSource controlSource, long j) {
        setPlayStatus(PlayStatus.SWITCHING_PLAYERS, null, changeReason, controlSource, j);
    }
}
