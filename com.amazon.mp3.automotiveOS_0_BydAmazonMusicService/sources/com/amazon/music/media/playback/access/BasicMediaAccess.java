package com.amazon.music.media.playback.access;

/* JADX INFO: loaded from: classes4.dex */
public enum BasicMediaAccess implements MediaAccess {
    NONE(false, false),
    CAN_PLAY(true, false),
    CAN_PLAY_FOREVER(true, true);

    private final boolean canGhostListen;
    private final boolean canPlay;

    BasicMediaAccess(boolean z, boolean z2) {
        this.canPlay = z;
        this.canGhostListen = z2;
    }

    @Override // com.amazon.music.media.playback.access.MediaAccess
    public boolean canPlay() {
        return this.canPlay;
    }

    @Override // com.amazon.music.media.playback.access.MediaAccess
    public boolean canGhostListen() {
        return this.canGhostListen;
    }
}
