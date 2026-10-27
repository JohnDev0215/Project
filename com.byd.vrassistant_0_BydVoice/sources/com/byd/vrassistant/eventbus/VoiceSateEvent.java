package com.byd.vrassistant.eventbus;

/* JADX INFO: loaded from: classes.dex */
public class VoiceSateEvent {
    boolean isShowGuid;
    boolean started;

    public VoiceSateEvent(boolean z, boolean z2) {
        this.started = z;
        this.isShowGuid = z2;
    }

    public boolean isShowGuid() {
        return this.isShowGuid;
    }

    public boolean getVoiceStarted() {
        return this.started;
    }
}
