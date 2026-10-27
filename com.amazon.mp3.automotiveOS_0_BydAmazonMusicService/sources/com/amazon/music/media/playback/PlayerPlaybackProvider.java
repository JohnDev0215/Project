package com.amazon.music.media.playback;

import androidx.annotation.Nullable;
import com.amazon.music.media.playback.player.Player;

/* JADX INFO: loaded from: classes4.dex */
public interface PlayerPlaybackProvider extends PlaybackProvider {
    Player getPlayer();

    void setMricToken(@Nullable String str);

    void setOnFrameChangedListener(OnFrameChangedListener onFrameChangedListener);

    void setOnPrebufferChangedListener(OnPrebufferChangedListener onPrebufferChangedListener);

    void setPlaybackMetricsListener(OnPlaybackMetricsListener onPlaybackMetricsListener);

    void setPlayer(Player player, ControlSource controlSource);
}
