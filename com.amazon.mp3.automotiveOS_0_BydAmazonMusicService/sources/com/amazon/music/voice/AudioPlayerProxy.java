package com.amazon.music.voice;

import com.amazon.alexa.voice.ExoAudioPlayer;
import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.AudioSource;

/* JADX INFO: loaded from: classes4.dex */
public class AudioPlayerProxy implements AudioPlayer {
    private final AudioPlayer alexaSpeechMessagesPlayer;
    private final AudioPlayerDelegate audioTracksPlayer;
    private AudioPlayer currentPlayer;

    public AudioPlayerProxy(AudioPlayerDelegate audioPlayerDelegate, AudioPlayer audioPlayer) {
        this.audioTracksPlayer = audioPlayerDelegate;
        this.alexaSpeechMessagesPlayer = audioPlayer;
        this.currentPlayer = audioPlayerDelegate;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void prepare(AudioSource audioSource) {
        AudioPlayer audioPlayer = this.alexaSpeechMessagesPlayer;
        this.currentPlayer = audioPlayer;
        audioPlayer.prepare(audioSource);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    @Deprecated
    public void prepare(String str) {
        AudioPlayerDelegate audioPlayerDelegate = this.audioTracksPlayer;
        this.currentPlayer = audioPlayerDelegate;
        audioPlayerDelegate.prepare(str);
    }

    public void prepare(String str, String str2) {
        AudioPlayerDelegate audioPlayerDelegate = this.audioTracksPlayer;
        this.currentPlayer = audioPlayerDelegate;
        audioPlayerDelegate.prepare(str, str2);
    }

    public void prepareWithAlexaSpeechMessagesPlayer(String str) {
        AudioPlayer audioPlayer = this.alexaSpeechMessagesPlayer;
        this.currentPlayer = audioPlayer;
        audioPlayer.prepare(str);
        this.currentPlayer.play();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isIdle() {
        return this.currentPlayer.isIdle();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isPlaying() {
        return this.currentPlayer.isPlaying();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isPaused() {
        return this.currentPlayer.isPaused();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isEnded() {
        return this.currentPlayer.isEnded();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isBuffering() {
        return this.currentPlayer.isBuffering();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void stop() {
        this.currentPlayer.stop();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void play() {
        this.currentPlayer.play();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void seek(long j) {
        this.currentPlayer.seek(j);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public long getCurrentPosition() {
        return this.currentPlayer.getCurrentPosition();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void pause() {
        this.currentPlayer.pause();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void setVolume(float f) {
        this.currentPlayer.setVolume(f);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getVolume() {
        return this.currentPlayer.getVolume();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getMinimumVolume() {
        return this.currentPlayer.getMinimumVolume();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getMaximumVolume() {
        return this.currentPlayer.getMaximumVolume();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void fadeVolume(float f) {
        this.currentPlayer.fadeVolume(f);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public long getDuration() {
        return this.currentPlayer.getDuration();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void addCallback(AudioPlayer.Callback callback) {
        this.audioTracksPlayer.addCallback(callback);
        this.alexaSpeechMessagesPlayer.addCallback(callback);
    }

    public void stopAlexaSpeechMessagesPlayer() {
        AudioPlayer audioPlayer = this.currentPlayer;
        if (audioPlayer instanceof ExoAudioPlayer) {
            audioPlayer.stop();
        }
    }
}
