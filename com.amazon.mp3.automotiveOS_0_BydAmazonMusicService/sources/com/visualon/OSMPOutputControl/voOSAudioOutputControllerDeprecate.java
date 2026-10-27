package com.visualon.OSMPOutputControl;

import android.media.AudioManager;
import com.visualon.OSMPOutputControl.voOSAudioOutputControllerBase;

/* JADX INFO: loaded from: classes7.dex */
public class voOSAudioOutputControllerDeprecate extends voOSAudioOutputControllerBase {
    voOSAudioOutputControllerDeprecate(AudioManager audioManager, voOSAudioOutputControllerBase.audioOutputControllerListener audiooutputcontrollerlistener) {
        super(audioManager, audiooutputcontrollerlistener);
    }

    @Override // com.visualon.OSMPOutputControl.voOSAudioOutputControllerBase
    public int startPlayback() {
        return this.audioManager.requestAudioFocus(this.focusChangeListener, 0, 1) == 1 ? 0 : 1;
    }

    @Override // com.visualon.OSMPOutputControl.voOSAudioOutputControllerBase
    public int endPlayback() {
        return this.audioManager.abandonAudioFocus(this.focusChangeListener) == 1 ? 0 : 1;
    }
}
