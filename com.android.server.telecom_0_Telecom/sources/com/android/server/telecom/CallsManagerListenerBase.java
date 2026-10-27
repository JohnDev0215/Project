package com.android.server.telecom;

import android.telecom.CallAudioState;
import android.telecom.VideoProfile;
import com.android.server.telecom.CallsManager;

/* JADX INFO: loaded from: classes.dex */
public class CallsManagerListenerBase implements CallsManager.CallsManagerListener {
    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAdded(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAudioStateChanged(CallAudioState callAudioState, CallAudioState callAudioState2) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCanAddCallChanged(boolean z) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCdmaConferenceSwap(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onConferenceStateChanged(Call call, boolean z) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onConnectionServiceChanged(Call call, ConnectionServiceWrapper connectionServiceWrapper, ConnectionServiceWrapper connectionServiceWrapper2) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onConnectionTimeChanged(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onDisconnectedTonePlaying(boolean z) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onExternalCallChanged(Call call, boolean z) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onHoldToneRequested(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIncomingCallAnswered(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIncomingCallRejected(Call call, boolean z, String str) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIsConferencedChanged(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIsVoipAudioModeChanged(Call call) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onRingbackRequested(Call call, boolean z) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onSessionModifyRequestReceived(Call call, VideoProfile videoProfile) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onSetCamera(Call call, String str) {
    }

    @Override // com.android.server.telecom.CallsManager.CallsManagerListener
    public void onVideoStateChanged(Call call, int i, int i2) {
    }
}
