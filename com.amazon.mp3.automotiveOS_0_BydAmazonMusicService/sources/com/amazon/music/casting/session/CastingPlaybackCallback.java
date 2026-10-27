package com.amazon.music.casting.session;

import com.amazon.music.casting.session.things.MetadataThingShadow;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;

/* JADX INFO: loaded from: classes4.dex */
public interface CastingPlaybackCallback {
    void onPause();

    void onPlay();

    void onSetAvailableFeatures(NowPlayingThingShadow.State.Transport[] transportArr);

    void onSetAvailableLoopModes(NowPlayingThingShadow.State.LoopMode[] loopModeArr);

    void onSetLoopMode(NowPlayingThingShadow.State.LoopMode loopMode);

    void onSetRating(NowPlayingThingShadow.State.Rating rating);

    void onSetShuffled(boolean z);

    void onStop();

    void onTracksMetadataUpdated(MetadataThingShadow.Track[] trackArr);

    void onVolumeUpdated(NowPlayingThingShadow.State.ReportedNode.Volume volume);
}
