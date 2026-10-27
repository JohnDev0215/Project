package com.android.services.telephony.sip;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telecom.AudioState;
import android.telecom.Connection;
import android.util.EventLog;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.internal.telephony.sip.SipPhone;
import com.android.services.telephony.DisconnectCauseUtil;
import com.android.services.telephony.Log;
import com.android.services.telephony.RadioOnStateListener;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class SipConnection extends Connection {
    private com.android.internal.telephony.Connection mOriginalConnection;
    private final Handler mHandler = new Handler() { // from class: com.android.services.telephony.sip.SipConnection.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            SipConnection.this.updateState(false);
        }
    };
    private Call.State mOriginalConnectionState = Call.State.IDLE;

    @Override // android.telecom.Connection
    public void onPostDialContinue(boolean z) {
    }

    @Override // android.telecom.Connection
    public void onStateChanged(int i) {
    }

    SipConnection() {
        setInitializing();
    }

    void initialize(com.android.internal.telephony.Connection connection) {
        this.mOriginalConnection = connection;
        if (getPhone() != null) {
            getPhone().registerForPreciseCallStateChanged(this.mHandler, 1, (Object) null);
        }
        updateAddress();
        setTechnologyTypeExtra();
        setInitialized();
    }

    public void onAudioStateChanged(AudioState audioState) {
        if (getPhone() != null) {
            getPhone().setEchoSuppressionEnabled();
        }
    }

    @Override // android.telecom.Connection
    public void onPlayDtmfTone(char c) {
        if (getPhone() != null) {
            getPhone().startDtmf(c);
        }
    }

    @Override // android.telecom.Connection
    public void onStopDtmfTone() {
        if (getPhone() != null) {
            getPhone().stopDtmf();
        }
    }

    @Override // android.telecom.Connection
    public void onDisconnect() {
        try {
            if (getCall() != null && !getCall().isMultiparty()) {
                getCall().hangup();
            } else if (this.mOriginalConnection != null) {
                this.mOriginalConnection.hangup();
            }
        } catch (CallStateException e) {
            log("onDisconnect, exception: " + e);
        }
    }

    @Override // android.telecom.Connection
    public void onSeparate() {
        try {
            if (this.mOriginalConnection != null) {
                this.mOriginalConnection.separate();
            }
        } catch (CallStateException e) {
            log("onSeparate, exception: " + e);
        }
    }

    @Override // android.telecom.Connection
    public void onAbort() {
        onDisconnect();
    }

    @Override // android.telecom.Connection
    public void onHold() {
        try {
            if (getPhone() != null && getState() == 4 && getPhone().getRingingCall().getState() != Call.State.WAITING) {
                if (this.mOriginalConnection != null && this.mOriginalConnection.getState() == Call.State.ACTIVE) {
                    getPhone().switchHoldingAndActive();
                } else {
                    log("skipping switch from onHold due to internal state:");
                }
            }
        } catch (CallStateException e) {
            log("onHold, exception: " + e);
        }
    }

    @Override // android.telecom.Connection
    public void onUnhold() {
        try {
            if (getPhone() != null && getState() == 5 && getPhone().getForegroundCall().getState() != Call.State.DIALING) {
                if (this.mOriginalConnection != null && this.mOriginalConnection.getState() == Call.State.HOLDING) {
                    getPhone().switchHoldingAndActive();
                } else {
                    log("skipping switch from onUnHold due to internal state.");
                }
            }
        } catch (CallStateException e) {
            log("onUnhold, exception: " + e);
        }
    }

    @Override // android.telecom.Connection
    public void onAnswer(int i) {
        try {
            if (!isValidRingingCall() || getPhone() == null) {
                return;
            }
            getPhone().acceptCall(i);
        } catch (IllegalStateException e) {
            log("onAnswer, IllegalStateException: " + e);
            EventLog.writeEvent(1397638484, "31752213", -1, "Invalid codec.");
            onReject();
        } catch (CallStateException e2) {
            log("onAnswer, exception: " + e2);
        } catch (IllegalArgumentException e3) {
            log("onAnswer, IllegalArgumentException: " + e3);
            EventLog.writeEvent(1397638484, "31752213", -1, "Invalid SDP.");
            onReject();
        }
    }

    @Override // android.telecom.Connection
    public void onReject() {
        try {
            if (!isValidRingingCall() || getPhone() == null) {
                return;
            }
            getPhone().rejectCall();
        } catch (CallStateException e) {
            log("onReject, exception: " + e);
        }
    }

    private Call getCall() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null) {
            return connection.getCall();
        }
        return null;
    }

    SipPhone getPhone() {
        Call call = getCall();
        if (call != null) {
            return call.getPhone();
        }
        return null;
    }

    private boolean isValidRingingCall() {
        Call call = getCall();
        return call != null && call.getState().isRinging() && call.getEarliestConnection() == this.mOriginalConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState(boolean z) {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection == null) {
            return;
        }
        Call.State state = connection.getState();
        if (z || this.mOriginalConnectionState != state) {
            this.mOriginalConnectionState = state;
            switch (AnonymousClass2.$SwitchMap$com$android$internal$telephony$Call$State[state.ordinal()]) {
                case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                    setActive();
                    break;
                case 3:
                    setOnHold();
                    break;
                case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                case 5:
                    setDialing();
                    setRingbackRequested(true);
                    break;
                case 6:
                case 7:
                    setRinging();
                    break;
                case 8:
                    setDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(this.mOriginalConnection.getDisconnectCause()));
                    close();
                    break;
            }
            updateCallCapabilities(z);
        }
    }

    /* JADX INFO: renamed from: com.android.services.telephony.sip.SipConnection$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$Call$State;

        static {
            int[] iArr = new int[Call.State.values().length];
            $SwitchMap$com$android$internal$telephony$Call$State = iArr;
            try {
                iArr[Call.State.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.HOLDING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.DIALING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.ALERTING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.INCOMING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.WAITING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.DISCONNECTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.DISCONNECTING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private int buildCallCapabilities() {
        return (getState() == 4 || getState() == 5) ? 67 : 66;
    }

    void updateCallCapabilities(boolean z) {
        int iBuildCallCapabilities = buildCallCapabilities();
        if (z || getConnectionCapabilities() != iBuildCallCapabilities) {
            setConnectionCapabilities(iBuildCallCapabilities);
        }
    }

    void onAddedToCallService() {
        updateState(true);
        updateCallCapabilities(true);
        setAudioModeIsVoip(true);
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null) {
            setCallerDisplayName(connection.getCnapName(), this.mOriginalConnection.getCnapNamePresentation());
        }
    }

    private void updateAddress() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null) {
            Uri addressFromNumber = getAddressFromNumber(connection.getAddress());
            int numberPresentation = this.mOriginalConnection.getNumberPresentation();
            if (!Objects.equals(addressFromNumber, getAddress()) || numberPresentation != getAddressPresentation()) {
                Log.v(this, "updateAddress, address changed", new Object[0]);
                setAddress(addressFromNumber, numberPresentation);
            }
            String cnapName = this.mOriginalConnection.getCnapName();
            int cnapNamePresentation = this.mOriginalConnection.getCnapNamePresentation();
            if (Objects.equals(cnapName, getCallerDisplayName()) && cnapNamePresentation == getCallerDisplayNamePresentation()) {
                return;
            }
            Log.v(this, "updateAddress, caller display name changed", new Object[0]);
            setCallerDisplayName(cnapName, cnapNamePresentation);
        }
    }

    private void setTechnologyTypeExtra() {
        if (getExtras() == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.telecom.extra.CALL_TECHNOLOGY_TYPE", 3);
            setExtras(bundle);
            return;
        }
        getExtras().putInt("android.telecom.extra.CALL_TECHNOLOGY_TYPE", 3);
    }

    private static Uri getAddressFromNumber(String str) {
        if (str == null) {
            str = "";
        }
        return Uri.fromParts("sip", str, null);
    }

    private void close() {
        if (getPhone() != null) {
            getPhone().unregisterForPreciseCallStateChanged(this.mHandler);
        }
        this.mOriginalConnection = null;
        destroy();
    }

    private static void log(String str) {
        android.util.Log.d("SIP", "[SipConnection] " + str);
    }
}
