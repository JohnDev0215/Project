package com.android.server.telecom;

import android.media.IAudioService;
import android.os.Bundle;
import android.telecom.CallAudioState;
import android.telecom.Log;
import android.telecom.VideoProfile;
import android.util.SparseArray;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.CallAudioModeStateMachine;
import com.android.server.telecom.InCallTonePlayer;
import com.android.server.telecom.bluetooth.BluetoothStateReceiver;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class CallAudioManager extends CallsManagerListenerBase {
    private final BluetoothStateReceiver mBluetoothStateReceiver;
    private final CallAudioModeStateMachine mCallAudioModeStateMachine;
    private final CallAudioRouteStateMachine mCallAudioRouteStateMachine;
    private final CallsManager mCallsManager;
    private final DtmfLocalTonePlayer mDtmfLocalTonePlayer;
    private Call mForegroundCall;
    private InCallTonePlayer mHoldTonePlayer;
    private final InCallTonePlayer.Factory mPlayerFactory;
    private final RingbackPlayer mRingbackPlayer;
    private final Ringer mRinger;
    private final String LOG_TAG = CallAudioManager.class.getSimpleName();
    private boolean mIsTonePlaying = false;
    private boolean mIsDisconnectedTonePlaying = false;
    private final LinkedHashSet<Call> mActiveDialingOrConnectingCalls = new LinkedHashSet<>(1);
    private final LinkedHashSet<Call> mRingingCalls = new LinkedHashSet<>(1);
    private final LinkedHashSet<Call> mHoldingCalls = new LinkedHashSet<>(1);
    private final LinkedHashSet<Call> mAudioProcessingCalls = new LinkedHashSet<>(1);
    private final Set<Call> mCalls = new HashSet();
    private final SparseArray<LinkedHashSet<Call>> mCallStateToCalls = new SparseArray<LinkedHashSet<Call>>() { // from class: com.android.server.telecom.CallAudioManager.1
        {
            put(1, CallAudioManager.this.mActiveDialingOrConnectingCalls);
            put(5, CallAudioManager.this.mActiveDialingOrConnectingCalls);
            put(3, CallAudioManager.this.mActiveDialingOrConnectingCalls);
            put(10, CallAudioManager.this.mActiveDialingOrConnectingCalls);
            put(4, CallAudioManager.this.mRingingCalls);
            put(6, CallAudioManager.this.mHoldingCalls);
            put(13, CallAudioManager.this.mRingingCalls);
            put(12, CallAudioManager.this.mAudioProcessingCalls);
        }
    };

    public interface AudioServiceFactory {
        IAudioService getAudioService();
    }

    private boolean shouldPlayDisconnectTone(int i, int i2) {
        if (i2 != 7) {
            return false;
        }
        return i == 5 || i == 3 || i == 6;
    }

    public CallAudioManager(CallAudioRouteStateMachine callAudioRouteStateMachine, CallsManager callsManager, CallAudioModeStateMachine callAudioModeStateMachine, InCallTonePlayer.Factory factory, Ringer ringer, RingbackPlayer ringbackPlayer, BluetoothStateReceiver bluetoothStateReceiver, DtmfLocalTonePlayer dtmfLocalTonePlayer) {
        this.mCallAudioRouteStateMachine = callAudioRouteStateMachine;
        this.mCallAudioModeStateMachine = callAudioModeStateMachine;
        this.mCallsManager = callsManager;
        this.mPlayerFactory = factory;
        this.mRinger = ringer;
        this.mRingbackPlayer = ringbackPlayer;
        this.mBluetoothStateReceiver = bluetoothStateReceiver;
        this.mDtmfLocalTonePlayer = dtmfLocalTonePlayer;
        factory.setCallAudioManager(this);
        this.mCallAudioModeStateMachine.setCallAudioManager(this);
        this.mCallAudioRouteStateMachine.setCallAudioManager(this);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        if (shouldIgnoreCallForAudio(call)) {
            return;
        }
        Log.d(this.LOG_TAG, "Call state changed for TC@%s: %s -> %s", new Object[]{call.getId(), CallState.toString(i), CallState.toString(i2)});
        removeCallFromAllBins(call);
        HashSet<Call> binForCall = getBinForCall(call);
        if (binForCall != null) {
            binForCall.add(call);
        }
        sendCallStatusToBluetoothStateReceiver();
        updateForegroundCall();
        if (shouldPlayDisconnectTone(i, i2)) {
            playToneForDisconnectedCall(call);
        }
        onCallLeavingState(call, i);
        onCallEnteringState(call, i2);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAdded(Call call) {
        if (shouldIgnoreCallForAudio(call)) {
            return;
        }
        addCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        if (shouldIgnoreCallForAudio(call)) {
            return;
        }
        removeCall(call);
    }

    private void addCall(Call call) {
        if (this.mCalls.contains(call)) {
            Log.w(this.LOG_TAG, "Call TC@%s is being added twice.", new Object[]{call.getId()});
            return;
        }
        Log.d(this.LOG_TAG, "Call added with id TC@%s in state %s", new Object[]{call.getId(), CallState.toString(call.getState())});
        HashSet<Call> binForCall = getBinForCall(call);
        if (binForCall != null) {
            binForCall.add(call);
        }
        updateForegroundCall();
        this.mCalls.add(call);
        sendCallStatusToBluetoothStateReceiver();
        onCallEnteringState(call, call.getState());
    }

    private void removeCall(Call call) {
        if (this.mCalls.contains(call)) {
            Log.d(this.LOG_TAG, "Call removed with id TC@%s in state %s", new Object[]{call.getId(), CallState.toString(call.getState())});
            removeCallFromAllBins(call);
            updateForegroundCall();
            this.mCalls.remove(call);
            sendCallStatusToBluetoothStateReceiver();
            onCallLeavingState(call, call.getState());
        }
    }

    private void sendCallStatusToBluetoothStateReceiver() {
        this.mBluetoothStateReceiver.setIsInCall(!this.mAudioProcessingCalls.containsAll(this.mCalls));
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onExternalCallChanged(Call call, boolean z) {
        if (z) {
            Log.d(this.LOG_TAG, "Removing call which became external ID %s", new Object[]{call.getId()});
            removeCall(call);
            return;
        }
        if (z) {
            return;
        }
        Log.d(this.LOG_TAG, "Adding external call which was pulled with ID %s", new Object[]{call.getId()});
        addCall(call);
        if (this.mCallsManager.isSpeakerphoneAutoEnabledForVideoCalls(call.getVideoState())) {
            Log.d(this.LOG_TAG, "Switching to speaker because external video call %s was pulled." + call.getId(), new Object[0]);
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1004);
        }
    }

    private boolean shouldIgnoreCallForAudio(Call call) {
        return call.getParentCall() != null || call.isExternalCall();
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIncomingCallAnswered(Call call) {
        if (this.mCalls.contains(call)) {
            if (!call.isHandoverInProgress()) {
                mute(false);
            }
            maybeStopRingingAndCallWaitingForAnsweredOrRejectedCall(call);
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onSessionModifyRequestReceived(Call call, VideoProfile videoProfile) {
        if (videoProfile != null && call == this.mForegroundCall) {
            int videoState = call.getVideoState();
            int videoState2 = videoProfile.getVideoState();
            boolean z = false;
            Log.v(this, "onSessionModifyRequestReceived : videoProfile = " + VideoProfile.videoStateToString(videoState2), new Object[0]);
            if (!VideoProfile.isReceptionEnabled(videoState) && VideoProfile.isReceptionEnabled(videoState2)) {
                z = true;
            }
            if (z) {
                this.mPlayerFactory.createPlayer(14).startTone();
            }
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onHoldToneRequested(Call call) {
        maybePlayHoldTone();
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIsVoipAudioModeChanged(Call call) {
        if (call != this.mForegroundCall) {
            return;
        }
        this.mCallAudioModeStateMachine.sendMessageWithArgs(4001, makeArgsForModeStateMachine());
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onRingbackRequested(Call call, boolean z) {
        if (call == this.mForegroundCall && z) {
            this.mRingbackPlayer.startRingbackForCall(call);
        } else {
            this.mRingbackPlayer.stopRingbackForCall(call);
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIncomingCallRejected(Call call, boolean z, String str) {
        maybeStopRingingAndCallWaitingForAnsweredOrRejectedCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIsConferencedChanged(Call call) {
        if (call.getParentCall() == null) {
            Log.i(this.LOG_TAG, "Call TC@" + call.getId() + " left conference and will now be tracked by CallAudioManager.", new Object[0]);
            onCallAdded(call);
            return;
        }
        removeCallFromAllBins(call);
        updateForegroundCall();
        this.mCalls.remove(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onConnectionServiceChanged(Call call, ConnectionServiceWrapper connectionServiceWrapper, ConnectionServiceWrapper connectionServiceWrapper2) {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1201);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onVideoStateChanged(Call call, int i, int i2) {
        if (call != getForegroundCall()) {
            Log.d(this.LOG_TAG, "Ignoring video state change from %s to %s for call %s -- not foreground.", new Object[]{VideoProfile.videoStateToString(i), VideoProfile.videoStateToString(i2), call.getId()});
        } else {
            if (VideoProfile.isVideo(i) || !this.mCallsManager.isSpeakerphoneAutoEnabledForVideoCalls(i2)) {
                return;
            }
            Log.d(this.LOG_TAG, "Switching to speaker because call %s transitioned video state from %s to %s", new Object[]{call.getId(), VideoProfile.videoStateToString(i), VideoProfile.videoStateToString(i2)});
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1004);
        }
    }

    public CallAudioState getCallAudioState() {
        return this.mCallAudioRouteStateMachine.getCurrentCallAudioState();
    }

    public Call getPossiblyHeldForegroundCall() {
        return this.mForegroundCall;
    }

    public Call getForegroundCall() {
        Call call = this.mForegroundCall;
        if (call == null || call.getState() == 6) {
            return null;
        }
        return this.mForegroundCall;
    }

    @VisibleForTesting
    public void toggleMute() {
        if (this.mCallsManager.isInEmergencyCall()) {
            Log.v(this, "ignoring toggleMute for emergency call", new Object[0]);
        } else {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(3003);
        }
    }

    @VisibleForTesting(visibility = VisibleForTesting.Visibility.PACKAGE)
    public void onRingerModeChange() {
        this.mCallAudioModeStateMachine.sendMessageWithArgs(5001, makeArgsForModeStateMachine());
    }

    @VisibleForTesting
    public void mute(boolean z) {
        Log.v(this, "mute, shouldMute: %b", new Object[]{Boolean.valueOf(z)});
        if (this.mCallsManager.isInEmergencyCall()) {
            Log.v(this, "ignoring mute for emergency call", new Object[0]);
            z = false;
        }
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(z ? 3001 : 3002);
    }

    void setAudioRoute(int i, String str) {
        Log.v(this, "setAudioRoute, route: %s", new Object[]{CallAudioState.audioRouteToString(i)});
        if (i == 1) {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1101);
            return;
        }
        if (i == 2) {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1102, 0, str);
            return;
        }
        if (i == 4) {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1103);
            return;
        }
        if (i == 5) {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1105, 0);
        } else if (i != 8) {
            Log.w(this, "InCallService requested an invalid audio route: %d", new Object[]{Integer.valueOf(i)});
        } else {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1104);
        }
    }

    void switchBaseline() {
        Log.i(this, "switchBaseline", new Object[0]);
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1105, 1);
    }

    void silenceRingers() {
        synchronized (this.mCallsManager.getLock()) {
            Iterator<Call> it = this.mRingingCalls.iterator();
            while (it.hasNext()) {
                it.next().silence();
            }
            this.mRinger.stopRinging();
            this.mRinger.stopCallWaiting();
        }
    }

    public boolean isRingtonePlaying() {
        return this.mRinger.isRinging();
    }

    @VisibleForTesting
    public boolean startRinging() {
        boolean zStartRinging;
        synchronized (this.mCallsManager.getLock()) {
            zStartRinging = this.mRinger.startRinging(this.mForegroundCall, this.mCallAudioRouteStateMachine.isHfpDeviceAvailable());
        }
        return zStartRinging;
    }

    @VisibleForTesting
    public void startCallWaiting(String str) {
        synchronized (this.mCallsManager.getLock()) {
            if (this.mRingingCalls.size() == 1) {
                this.mRinger.startCallWaiting(this.mRingingCalls.iterator().next(), str);
            }
        }
    }

    @VisibleForTesting
    public void stopRinging() {
        synchronized (this.mCallsManager.getLock()) {
            this.mRinger.stopRinging();
        }
    }

    @VisibleForTesting
    public void stopCallWaiting() {
        synchronized (this.mCallsManager.getLock()) {
            this.mRinger.stopCallWaiting();
        }
    }

    @VisibleForTesting
    public void setCallAudioRouteFocusState(int i) {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(4001, i);
    }

    public void notifyAudioOperationsComplete() {
        this.mCallAudioModeStateMachine.sendMessageWithArgs(6001, makeArgsForModeStateMachine());
    }

    @VisibleForTesting
    public CallAudioRouteStateMachine getCallAudioRouteStateMachine() {
        return this.mCallAudioRouteStateMachine;
    }

    @VisibleForTesting
    public CallAudioModeStateMachine getCallAudioModeStateMachine() {
        return this.mCallAudioModeStateMachine;
    }

    public boolean isBluetoothCall() {
        Bundle intentExtras;
        Call call = this.mForegroundCall;
        if (call == null || (intentExtras = call.getIntentExtras()) == null) {
            return false;
        }
        return intentExtras.getBoolean("android.telecom.extra.IS_BLUETOOTH_CALL", false);
    }

    void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("All calls:");
        indentingPrintWriter.increaseIndent();
        dumpCallsInCollection(indentingPrintWriter, this.mCalls);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Active dialing, or connecting calls:");
        indentingPrintWriter.increaseIndent();
        dumpCallsInCollection(indentingPrintWriter, this.mActiveDialingOrConnectingCalls);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Ringing calls:");
        indentingPrintWriter.increaseIndent();
        dumpCallsInCollection(indentingPrintWriter, this.mRingingCalls);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Holding calls:");
        indentingPrintWriter.increaseIndent();
        dumpCallsInCollection(indentingPrintWriter, this.mHoldingCalls);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Foreground call:");
        indentingPrintWriter.println(this.mForegroundCall);
        indentingPrintWriter.println("CallAudioModeStateMachine pending messages:");
        indentingPrintWriter.increaseIndent();
        this.mCallAudioModeStateMachine.dumpPendingMessages(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("CallAudioRouteStateMachine pending messages:");
        indentingPrintWriter.increaseIndent();
        this.mCallAudioRouteStateMachine.dumpPendingMessages(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("BluetoothDeviceManager:");
        indentingPrintWriter.increaseIndent();
        if (this.mBluetoothStateReceiver.getBluetoothDeviceManager() != null) {
            this.mBluetoothStateReceiver.getBluetoothDeviceManager().dump(indentingPrintWriter);
        }
        indentingPrintWriter.decreaseIndent();
    }

    @VisibleForTesting
    public void setIsTonePlaying(boolean z) {
        this.mIsTonePlaying = z;
        this.mCallAudioModeStateMachine.sendMessageWithArgs(z ? 3001 : 3002, makeArgsForModeStateMachine());
        if (z || !this.mIsDisconnectedTonePlaying) {
            return;
        }
        this.mCallsManager.onDisconnectedTonePlaying(false);
        this.mIsDisconnectedTonePlaying = false;
    }

    private void onCallLeavingState(Call call, int i) {
        if (i != 1) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i == 6) {
                            onCallLeavingHold();
                            return;
                        }
                        switch (i) {
                            case 10:
                                onCallLeavingActiveDialingOrConnecting();
                                break;
                            case 12:
                                onCallLeavingAudioProcessing();
                                break;
                        }
                    }
                }
                onCallLeavingRinging();
                return;
            }
            stopRingbackForCall(call);
            onCallLeavingActiveDialingOrConnecting();
            return;
        }
        onCallLeavingActiveDialingOrConnecting();
    }

    private void onCallEnteringState(Call call, int i) {
        if (i != 1) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i == 6) {
                            onCallEnteringHold();
                            return;
                        }
                        switch (i) {
                            case 10:
                                onCallEnteringActiveDialingOrConnecting();
                                break;
                            case 11:
                                if (call.can(262144)) {
                                    onCallEnteringActiveDialingOrConnecting();
                                }
                                break;
                            case 12:
                                onCallEnteringAudioProcessing();
                                break;
                        }
                    }
                }
                onCallEnteringRinging();
                return;
            }
            onCallEnteringActiveDialingOrConnecting();
            playRingbackForCall(call);
            return;
        }
        onCallEnteringActiveDialingOrConnecting();
    }

    private void onCallLeavingAudioProcessing() {
        if (this.mAudioProcessingCalls.size() == 0) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(1004, makeArgsForModeStateMachine());
        }
    }

    private void onCallEnteringAudioProcessing() {
        if (this.mAudioProcessingCalls.size() == 1) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(2004, makeArgsForModeStateMachine());
        }
    }

    private void onCallLeavingActiveDialingOrConnecting() {
        if (this.mActiveDialingOrConnectingCalls.size() == 0) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(1001, makeArgsForModeStateMachine());
        }
    }

    private void onCallLeavingRinging() {
        if (this.mRingingCalls.size() == 0) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(1002, makeArgsForModeStateMachine());
        }
    }

    private void onCallLeavingHold() {
        if (this.mHoldingCalls.size() == 0) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(1003, makeArgsForModeStateMachine());
        }
    }

    private void onCallEnteringActiveDialingOrConnecting() {
        if (this.mActiveDialingOrConnectingCalls.size() == 1) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(2001, makeArgsForModeStateMachine());
        }
    }

    private void onCallEnteringRinging() {
        if (this.mRingingCalls.size() == 1) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(2002, makeArgsForModeStateMachine());
        }
    }

    private void onCallEnteringHold() {
        if (this.mHoldingCalls.size() == 1) {
            this.mCallAudioModeStateMachine.sendMessageWithArgs(2003, makeArgsForModeStateMachine());
        }
    }

    private void updateForegroundCall() {
        Call call = this.mForegroundCall;
        Call next = null;
        if (this.mActiveDialingOrConnectingCalls.size() > 0) {
            for (Call call2 : this.mActiveDialingOrConnectingCalls) {
                if (call2.getState() == 1) {
                    next = call2;
                }
            }
            if (next == null) {
                next = this.mActiveDialingOrConnectingCalls.iterator().next();
            }
            this.mForegroundCall = next;
        } else if (this.mRingingCalls.size() > 0) {
            this.mForegroundCall = this.mRingingCalls.iterator().next();
        } else if (this.mHoldingCalls.size() > 0) {
            this.mForegroundCall = this.mHoldingCalls.iterator().next();
        } else {
            this.mForegroundCall = null;
        }
        if (this.mForegroundCall != call) {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1201);
            this.mDtmfLocalTonePlayer.onForegroundCallChanged(call, this.mForegroundCall);
            maybePlayHoldTone();
        }
    }

    private CallAudioModeStateMachine.MessageArgs makeArgsForModeStateMachine() {
        CallAudioModeStateMachine.MessageArgs.Builder builder = new CallAudioModeStateMachine.MessageArgs.Builder();
        builder.setHasActiveOrDialingCalls(this.mActiveDialingOrConnectingCalls.size() > 0);
        builder.setHasRingingCalls(this.mRingingCalls.size() > 0);
        builder.setHasHoldingCalls(this.mHoldingCalls.size() > 0);
        builder.setHasAudioProcessingCalls(this.mAudioProcessingCalls.size() > 0);
        builder.setIsTonePlaying(this.mIsTonePlaying);
        Call call = this.mForegroundCall;
        builder.setForegroundCallIsVoip(call != null && call.getIsVoipAudioMode());
        builder.setSession(Log.createSubsession());
        return builder.build();
    }

    private HashSet<Call> getBinForCall(Call call) {
        if (call.getState() == 11) {
            if (call.can(262144)) {
                return this.mActiveDialingOrConnectingCalls;
            }
            return this.mRingingCalls;
        }
        return this.mCallStateToCalls.get(call.getState());
    }

    private void removeCallFromAllBins(Call call) {
        for (int i = 0; i < this.mCallStateToCalls.size(); i++) {
            this.mCallStateToCalls.valueAt(i).remove(call);
        }
    }

    private void playToneForDisconnectedCall(Call call) {
        if (call.isHandoverInProgress()) {
            Log.i(this.LOG_TAG, "Omitting tone because %s is being handed over.", new Object[]{call});
            return;
        }
        Call call2 = this.mForegroundCall;
        if (call2 != null && call != call2 && this.mCalls.size() > 1) {
            Log.v(this.LOG_TAG, "Omitting tone because we are not foreground and there is another call.", new Object[0]);
            return;
        }
        if (call.getDisconnectCause() != null) {
            Log.v(this, "Disconnect cause: %s.", new Object[]{call.getDisconnectCause()});
            int tone = call.getDisconnectCause().getTone();
            int i = tone != 17 ? tone != 18 ? tone != 21 ? tone != 27 ? tone != 95 ? tone != 37 ? tone != 38 ? 0 : 10 : 7 : 5 : 2 : 12 : 6 : 1;
            Log.d(this, "Found a disconnected call with tone to play %d.", new Object[]{Integer.valueOf(i)});
            if (i == 0 || !this.mPlayerFactory.createPlayer(i).startTone()) {
                return;
            }
            this.mCallsManager.onDisconnectedTonePlaying(true);
            this.mIsDisconnectedTonePlaying = true;
        }
    }

    private void playRingbackForCall(Call call) {
        if (call == this.mForegroundCall && call.isRingbackRequested()) {
            this.mRingbackPlayer.startRingbackForCall(call);
        }
    }

    private void stopRingbackForCall(Call call) {
        this.mRingbackPlayer.stopRingbackForCall(call);
    }

    private void maybePlayHoldTone() {
        if (shouldPlayHoldTone()) {
            if (this.mHoldTonePlayer == null) {
                InCallTonePlayer inCallTonePlayerCreatePlayer = this.mPlayerFactory.createPlayer(4);
                this.mHoldTonePlayer = inCallTonePlayerCreatePlayer;
                inCallTonePlayerCreatePlayer.startTone();
                return;
            }
            return;
        }
        InCallTonePlayer inCallTonePlayer = this.mHoldTonePlayer;
        if (inCallTonePlayer != null) {
            inCallTonePlayer.stopTone();
            this.mHoldTonePlayer = null;
        }
    }

    private boolean shouldPlayHoldTone() {
        Call foregroundCall = getForegroundCall();
        if (foregroundCall == null || this.mCallsManager.hasRingingCall() || !foregroundCall.isActive()) {
            return false;
        }
        return foregroundCall.isRemotelyHeld();
    }

    private void dumpCallsInCollection(IndentingPrintWriter indentingPrintWriter, Collection<Call> collection) {
        for (Call call : collection) {
            if (call != null) {
                indentingPrintWriter.println(call.getId());
            }
        }
    }

    private void maybeStopRingingAndCallWaitingForAnsweredOrRejectedCall(Call call) {
        synchronized (this.mCallsManager.getLock()) {
            if (this.mRingingCalls.size() == 0 || (this.mRingingCalls.size() == 1 && call == this.mRingingCalls.iterator().next())) {
                this.mRinger.stopRinging();
                this.mRinger.stopCallWaiting();
            }
        }
    }

    @VisibleForTesting
    public Set<Call> getTrackedCalls() {
        return this.mCalls;
    }

    @VisibleForTesting
    public SparseArray<LinkedHashSet<Call>> getCallStateToCalls() {
        return this.mCallStateToCalls;
    }
}
