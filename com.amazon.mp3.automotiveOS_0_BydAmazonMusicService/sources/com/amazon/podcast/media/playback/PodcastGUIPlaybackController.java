package com.amazon.podcast.media.playback;

import com.amazon.music.voice.GUIPlaybackController;
import com.amazon.podcast.Podcast;

/* JADX INFO: loaded from: classes5.dex */
final class PodcastGUIPlaybackController implements GUIPlaybackController {
    PodcastGUIPlaybackController() {
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void play() {
        Podcast.getPlayback().playPause();
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void pause() {
        Podcast.getPlayback().pause();
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void stop() {
        Podcast.getPlayback().stop();
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void next() {
        Podcast.getPlayback().next();
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void previous() {
        Podcast.getPlayback().previous();
    }
}
