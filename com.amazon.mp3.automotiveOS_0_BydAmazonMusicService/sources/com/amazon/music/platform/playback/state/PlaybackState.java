package com.amazon.music.platform.playback.state;

import kotlin.Metadata;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: PlaybackState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0087\u0001\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/playback/state/PlaybackState;", "", "<init>", "(Ljava/lang/String;I)V", "Companion", "WAITING_TO_PLAY", "STARTED_PLAYING", "PLAYING", "PAUSED", "ENDED", "DETACHED", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public enum PlaybackState {
    WAITING_TO_PLAY,
    STARTED_PLAYING,
    PLAYING,
    PAUSED,
    ENDED,
    DETACHED;
}
