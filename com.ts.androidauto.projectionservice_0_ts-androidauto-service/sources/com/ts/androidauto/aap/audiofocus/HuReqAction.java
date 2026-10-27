package com.ts.androidauto.aap.audiofocus;

import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes.dex */
public class HuReqAction {
    ResourceConst.AudioId mCauseAudioId;
    ResourceConst.AudioFocusNotify mEvent;
    FocusState mLastState;
    ResourceConst.AudioId mSelfAudioId;

    public FocusState getLastState() {
        return this.mLastState;
    }

    public void setLastState(FocusState lastState) {
        this.mLastState = lastState;
    }

    public ResourceConst.AudioFocusNotify getEvent() {
        return this.mEvent;
    }

    public void setEvent(ResourceConst.AudioFocusNotify event) {
        this.mEvent = event;
    }

    public ResourceConst.AudioId getSelfAudioId() {
        return this.mSelfAudioId;
    }

    public void setSelfAudioId(ResourceConst.AudioId selfAudioId) {
        this.mSelfAudioId = selfAudioId;
    }

    public ResourceConst.AudioId getCauseAudioId() {
        return this.mCauseAudioId;
    }

    public void setCauseAudioId(ResourceConst.AudioId causeAudioId) {
        this.mCauseAudioId = causeAudioId;
    }
}
