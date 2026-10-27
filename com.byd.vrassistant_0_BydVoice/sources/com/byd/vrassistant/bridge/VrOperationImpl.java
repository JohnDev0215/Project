package com.byd.vrassistant.bridge;

import com.byd.vrassistant.manager.VoiceAssistantManager;

/* JADX INFO: loaded from: classes.dex */
public class VrOperationImpl implements VrOperation {
    @Override // com.byd.vrassistant.bridge.VrOperation
    public void sendPTTEvent(String str) {
        VoiceAssistantManager.get().sendPTTEvent(str);
    }

    @Override // com.byd.vrassistant.bridge.VrOperation
    public void sendCancelPTTEvent() {
        VoiceAssistantManager.get().sendCancelPTTEvent();
    }
}
