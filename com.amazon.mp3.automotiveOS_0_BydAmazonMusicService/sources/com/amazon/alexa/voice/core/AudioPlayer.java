package com.amazon.alexa.voice.core;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioPlayer {

    public interface Callback {
        void onPlaybackEnded();

        void onPlaybackError(Throwable th);

        void onPlaybackIdle();

        void onPlaybackPaused();

        void onPlaybackReady();

        void onPlaybackStarted();

        void onPlayerBuffering();
    }

    public static class SimpleCallback implements Callback {
        @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackEnded() {
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackError(Throwable th) {
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackIdle() {
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackPaused() {
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackReady() {
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackStarted() {
        }

        @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlayerBuffering() {
        }
    }

    void addCallback(Callback callback);

    void fadeVolume(float f);

    long getCurrentPosition();

    long getDuration();

    float getMaximumVolume();

    float getMinimumVolume();

    float getVolume();

    boolean isBuffering();

    boolean isEnded();

    boolean isIdle();

    boolean isPaused();

    boolean isPlaying();

    void pause();

    void play();

    void prepare(AudioSource audioSource);

    void prepare(String str);

    void seek(long j);

    void setVolume(float f);

    void stop();
}
