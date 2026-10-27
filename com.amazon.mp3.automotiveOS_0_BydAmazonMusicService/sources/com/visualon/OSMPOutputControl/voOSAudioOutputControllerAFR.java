package com.visualon.OSMPOutputControl;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import com.visualon.OSMPOutputControl.voOSAudioOutputControllerBase;

/* JADX INFO: loaded from: classes7.dex */
public class voOSAudioOutputControllerAFR extends voOSAudioOutputControllerBase {
    private AudioFocusRequest.Builder afrBuilder;
    private AudioFocusRequest currentAFR;

    voOSAudioOutputControllerAFR(AudioManager audioManager, voOSAudioOutputControllerBase.audioOutputControllerListener audiooutputcontrollerlistener) {
        super(audioManager, audiooutputcontrollerlistener);
        this.currentAFR = null;
        this.afrBuilder = null;
        AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(1);
        this.afrBuilder = builder;
        builder.setOnAudioFocusChangeListener(this.focusChangeListener);
    }

    @Override // com.visualon.OSMPOutputControl.voOSAudioOutputControllerBase
    public int startPlayback() {
        AudioFocusRequest audioFocusRequest = this.currentAFR;
        if (audioFocusRequest != null) {
            this.audioManager.abandonAudioFocusRequest(audioFocusRequest);
        }
        AudioFocusRequest audioFocusRequestBuild = this.afrBuilder.build();
        this.currentAFR = audioFocusRequestBuild;
        return this.audioManager.requestAudioFocus(audioFocusRequestBuild) == 1 ? 0 : 1;
    }

    @Override // com.visualon.OSMPOutputControl.voOSAudioOutputControllerBase
    public int endPlayback() {
        AudioFocusRequest audioFocusRequest = this.currentAFR;
        if (audioFocusRequest != null) {
            i = this.audioManager.abandonAudioFocusRequest(audioFocusRequest) != 1 ? 1 : 0;
            this.currentAFR = null;
        }
        return i;
    }
}
