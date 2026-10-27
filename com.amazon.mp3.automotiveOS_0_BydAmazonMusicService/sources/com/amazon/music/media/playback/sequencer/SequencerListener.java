package com.amazon.music.media.playback.sequencer;

import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.PlaybackException;

/* JADX INFO: loaded from: classes4.dex */
public interface SequencerListener extends OnMetadataChangedListener {
    void onErrorFetchingMediaItems(Sequencer sequencer, PlaybackException playbackException);

    void onSequencerInfoChanged(Sequencer sequencer);
}
