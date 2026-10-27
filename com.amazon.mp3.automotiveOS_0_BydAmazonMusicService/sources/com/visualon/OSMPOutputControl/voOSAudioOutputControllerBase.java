package com.visualon.OSMPOutputControl;

import android.media.AudioManager;
import android.os.Build;

/* JADX INFO: loaded from: classes7.dex */
public abstract class voOSAudioOutputControllerBase {
    AudioManager audioManager;
    private audioOutputControllerListener controllerListener;
    AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.visualon.OSMPOutputControl.voOSAudioOutputControllerBase.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (voOSAudioOutputControllerBase.this.controllerListener == null) {
                return;
            }
            if (i == -1) {
                voOSAudioOutputControllerBase.this.controllerListener.SetParam(69652L, null);
            } else {
                if (i != 1) {
                    return;
                }
                voOSAudioOutputControllerBase.this.controllerListener.SetParam(69651L, null);
            }
        }
    };

    public interface audioOutputControllerListener {
        int SetParam(long j, Object obj);
    }

    public abstract int endPlayback();

    public abstract int startPlayback();

    public static voOSAudioOutputControllerBase createController(AudioManager audioManager, audioOutputControllerListener audiooutputcontrollerlistener) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new voOSAudioOutputControllerAFR(audioManager, audiooutputcontrollerlistener);
        }
        return new voOSAudioOutputControllerDeprecate(audioManager, audiooutputcontrollerlistener);
    }

    public void destroy() {
        this.audioManager = null;
        this.controllerListener = null;
    }

    voOSAudioOutputControllerBase(AudioManager audioManager, audioOutputControllerListener audiooutputcontrollerlistener) {
        this.audioManager = null;
        this.controllerListener = null;
        this.audioManager = audioManager;
        this.controllerListener = audiooutputcontrollerlistener;
    }
}
