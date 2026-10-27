package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.MediaSource;

/* JADX INFO: loaded from: classes6.dex */
public interface ExoPlayer extends Player {

    public interface ExoPlayerComponent {
        void handleMessage(int i, Object obj) throws ExoPlaybackException;
    }

    void blockingSendMessages(ExoPlayerMessage... exoPlayerMessageArr);

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getBufferedPercentage();

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getCurrentAdGroupIndex();

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getCurrentAdIndexInAdGroup();

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getCurrentPeriodIndex();

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getCurrentWindowIndex();

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getPlaybackState();

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getRendererCount();

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ int getRepeatMode();

    void prepare(MediaSource mediaSource);

    void sendMessages(ExoPlayerMessage... exoPlayerMessageArr);

    @Override // com.google.android.exoplayer2.Player
    /* synthetic */ void setRepeatMode(int i);

    public static final class ExoPlayerMessage {
        public final Object message;
        public final int messageType;
        public final ExoPlayerComponent target;

        public ExoPlayerMessage(ExoPlayerComponent exoPlayerComponent, int i, Object obj) {
            this.target = exoPlayerComponent;
            this.messageType = i;
            this.message = obj;
        }
    }
}
