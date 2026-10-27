package com.amazon.music.media.playback;

import com.amazon.music.media.playback.sequencer.Sequencer;

/* JADX INFO: loaded from: classes4.dex */
public interface SequencerPlaybackProvider extends PlaybackProvider {

    public interface MediaItemVerifier {
        boolean isCorrectMediaItem(MediaItem mediaItem);
    }

    Sequencer getSequencer();

    void setSequencer(Sequencer sequencer, ControlSource controlSource);

    void setSequencerAndPosition(Sequencer sequencer, long j, MediaItemVerifier mediaItemVerifier, ControlSource controlSource);

    void startSequencer(Sequencer sequencer, ControlSource controlSource, long j);

    public static class SimpleMediaItemVerifier implements MediaItemVerifier {
        private final MediaItem mediaItem;

        public SimpleMediaItemVerifier(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
        }

        @Override // com.amazon.music.media.playback.SequencerPlaybackProvider.MediaItemVerifier
        public boolean isCorrectMediaItem(MediaItem mediaItem) {
            MediaItem mediaItem2;
            if (mediaItem == null || (mediaItem2 = this.mediaItem) == null) {
                return false;
            }
            if (mediaItem == mediaItem2) {
                return true;
            }
            return mediaItem2.hasSameId(mediaItem);
        }
    }
}
