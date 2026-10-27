package com.amazon.avod.playback;

import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class PlaybackEventContext {
    private final long mPlayHeadPositionInMillis;
    private final PlayerType mPlayerType;

    public enum PlayerType {
        MAIN_PLAYER,
        ANDROID_PLAYER,
        IMAGE_PLAYER
    }

    public PlaybackEventContext(@Nonnegative long j) {
        this(j, PlayerType.MAIN_PLAYER);
    }

    public PlaybackEventContext(@Nonnegative long j, @Nonnull PlayerType playerType) {
        this.mPlayHeadPositionInMillis = Preconditions2.checkNonNegative(j, "playHeadPositionInMillis");
        this.mPlayerType = (PlayerType) Preconditions.checkNotNull(playerType, "playerType");
    }

    public long getPlayHeadPositionInMillis() {
        return this.mPlayHeadPositionInMillis;
    }

    public PlayerType getPlayerType() {
        return this.mPlayerType;
    }
}
