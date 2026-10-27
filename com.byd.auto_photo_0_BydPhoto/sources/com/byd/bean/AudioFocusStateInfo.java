package com.byd.bean;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class AudioFocusStateInfo {
    private AudioFocusRequest focusRequest;
    private AudioManager.OnAudioFocusChangeListener listener;
    private int state;

    public AudioFocusStateInfo(int i, AudioFocusRequest audioFocusRequest) {
        this.state = i;
        this.focusRequest = audioFocusRequest;
    }

    public AudioFocusStateInfo(int i, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.state = i;
        this.listener = onAudioFocusChangeListener;
    }

    public void abandonAudioFocus(AudioManager audioManager) {
        AudioFocusRequest audioFocusRequest = this.focusRequest;
        if (audioFocusRequest != null && Build.VERSION.SDK_INT >= 26) {
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
        }
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.listener;
        if (onAudioFocusChangeListener != null) {
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
        this.state = 0;
    }

    public AudioFocusRequest getFocusRequest() {
        return this.focusRequest;
    }

    public AudioManager.OnAudioFocusChangeListener getListener() {
        return this.listener;
    }

    public int getState() {
        return this.state;
    }

    public void setFocusRequest(AudioFocusRequest audioFocusRequest) {
        this.focusRequest = audioFocusRequest;
    }

    public void setListener(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.listener = onAudioFocusChangeListener;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String toString() {
        return "AudioFocusState{state=" + this.state + '}';
    }
}
