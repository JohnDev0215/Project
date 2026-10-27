package com.android.services.telephony;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.internal.telephony.Connection;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.imsphone.ImsPhoneConnection;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
final class CdmaConnection extends TelephonyConnection {
    private boolean mAllowMute;
    private boolean mDtmfBurstConfirmationPending;
    private final Queue<Character> mDtmfQueue;
    private final EmergencyTonePlayer mEmergencyTonePlayer;
    private final Handler mHandler;
    private boolean mIsCallWaiting;
    private boolean mIsConnectionTimeReset;

    CdmaConnection(Connection connection, EmergencyTonePlayer emergencyTonePlayer, boolean z, int i, String str) {
        super(connection, str, i);
        this.mHandler = new Handler() { // from class: com.android.services.telephony.CdmaConnection.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    CdmaConnection.this.hangupCallWaiting(1);
                } else if (i2 == 2) {
                    CdmaConnection.this.handleBurstDtmfConfirmation();
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    CdmaConnection.this.handleCdmaConnectionTimeReset();
                }
            }
        };
        this.mDtmfQueue = new LinkedList();
        boolean z2 = false;
        this.mDtmfBurstConfirmationPending = false;
        this.mIsConnectionTimeReset = false;
        this.mEmergencyTonePlayer = emergencyTonePlayer;
        this.mAllowMute = z;
        if (connection != null && connection.getState() == Call.State.WAITING) {
            z2 = true;
        }
        this.mIsCallWaiting = z2;
        boolean z3 = getOriginalConnection() instanceof ImsPhoneConnection;
        if (!this.mIsCallWaiting || z3) {
            return;
        }
        startCallWaitingTimer();
    }

    @Override // android.telecom.Connection
    public void onPlayDtmfTone(char c) {
        if (useBurstDtmf()) {
            Log.i(this, "sending dtmf digit as burst", new Object[0]);
            sendShortDtmfToNetwork(c);
        } else {
            Log.i(this, "sending dtmf digit directly", new Object[0]);
            getPhone().startDtmf(c);
        }
    }

    @Override // android.telecom.Connection
    public void onStopDtmfTone() {
        if (useBurstDtmf()) {
            return;
        }
        getPhone().stopDtmf();
    }

    @Override // com.android.services.telephony.TelephonyConnection, android.telecom.Connection
    public void onReject() {
        Connection originalConnection = getOriginalConnection();
        if (originalConnection != null) {
            int i = AnonymousClass2.$SwitchMap$com$android$internal$telephony$Call$State[originalConnection.getState().ordinal()];
            if (i == 1) {
                super.onReject();
            } else if (i == 2) {
                hangupCallWaiting(16);
            } else {
                Log.e(this, new Exception(), "Rejecting a non-ringing call", new Object[0]);
                super.onReject();
            }
        }
    }

    /* JADX INFO: renamed from: com.android.services.telephony.CdmaConnection$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$Call$State;

        static {
            int[] iArr = new int[Call.State.values().length];
            $SwitchMap$com$android$internal$telephony$Call$State = iArr;
            try {
                iArr[Call.State.INCOMING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$Call$State[Call.State.WAITING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // android.telecom.Connection
    public void onAnswer() {
        this.mHandler.removeMessages(1);
        super.onAnswer();
    }

    @Override // com.android.services.telephony.TelephonyConnection
    public TelephonyConnection cloneConnection() {
        return new CdmaConnection(getOriginalConnection(), this.mEmergencyTonePlayer, this.mAllowMute, getCallDirection(), getTelecomCallId());
    }

    @Override // com.android.services.telephony.TelephonyConnection, android.telecom.Connection
    public void onStateChanged(int i) {
        Connection originalConnection = getOriginalConnection();
        this.mIsCallWaiting = originalConnection != null && originalConnection.getState() == Call.State.WAITING;
        EmergencyTonePlayer emergencyTonePlayer = this.mEmergencyTonePlayer;
        if (emergencyTonePlayer != null) {
            if (i == 3) {
                if (isEmergency()) {
                    this.mEmergencyTonePlayer.start();
                }
            } else {
                emergencyTonePlayer.stop();
            }
        }
        super.onStateChanged(i);
    }

    @Override // com.android.services.telephony.TelephonyConnection
    protected int buildConnectionCapabilities() {
        int iBuildConnectionCapabilities = super.buildConnectionCapabilities();
        return this.mAllowMute ? iBuildConnectionCapabilities | 64 : iBuildConnectionCapabilities;
    }

    @Override // com.android.services.telephony.TelephonyConnection
    public void performConference(android.telecom.Connection connection) {
        if (isImsConnection()) {
            super.performConference(connection);
        } else {
            Log.w(this, "Non-IMS CDMA Connection attempted to call performConference.", new Object[0]);
        }
    }

    void forceAsDialing(boolean z) {
        if (z) {
            setStateOverride(Call.State.DIALING);
        } else {
            resetStateOverride();
        }
    }

    boolean isCallWaiting() {
        return this.mIsCallWaiting;
    }

    private void startCallWaitingTimer() {
        this.mHandler.sendEmptyMessageDelayed(1, 20000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hangupCallWaiting(int i) {
        Connection originalConnection = getOriginalConnection();
        if (originalConnection != null) {
            try {
                originalConnection.hangup();
            } catch (CallStateException e) {
                Log.e(this, e, "Failed to hangup call waiting call", new Object[0]);
            }
            setTelephonyConnectionDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(i, (String) null, getPhone().getPhoneId()));
        }
    }

    private boolean useBurstDtmf() {
        if (!isImsConnection()) {
            return Settings.System.getInt(getPhone().getContext().getContentResolver(), "dtmf_tone_type", 0) == 0;
        }
        Log.d(this, "in ims call, return false", new Object[0]);
        return false;
    }

    private void sendShortDtmfToNetwork(char c) {
        synchronized (this.mDtmfQueue) {
            if (this.mDtmfBurstConfirmationPending) {
                this.mDtmfQueue.add(new Character(c));
            } else {
                sendBurstDtmfStringLocked(Character.toString(c));
            }
        }
    }

    private void sendBurstDtmfStringLocked(String str) {
        getPhone().sendBurstDtmf(str, 0, 0, this.mHandler.obtainMessage(2));
        this.mDtmfBurstConfirmationPending = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBurstDtmfConfirmation() {
        String string;
        synchronized (this.mDtmfQueue) {
            this.mDtmfBurstConfirmationPending = false;
            if (this.mDtmfQueue.isEmpty()) {
                string = null;
            } else {
                StringBuilder sb = new StringBuilder(this.mDtmfQueue.size());
                while (!this.mDtmfQueue.isEmpty()) {
                    sb.append(this.mDtmfQueue.poll());
                }
                string = sb.toString();
                Log.i(this, "%d dtmf character[s] removed from the queue", Integer.valueOf(string.length()));
            }
            if (string != null) {
                sendBurstDtmfStringLocked(string);
            }
        }
    }

    private boolean isEmergency() {
        Phone phone = getPhone();
        return (phone == null || getAddress() == null || !PhoneNumberUtils.isLocalEmergencyNumber(phone.getContext(), getAddress().getSchemeSpecificPart())) ? false : true;
    }

    @Override // com.android.services.telephony.TelephonyConnection
    protected void handleExitedEcmMode() {
        this.mAllowMute = true;
        super.handleExitedEcmMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCdmaConnectionTimeReset() {
        if ((getOriginalConnection() instanceof ImsPhoneConnection) || this.mIsConnectionTimeReset || !isOutgoingCall() || getOriginalConnection() == null || getOriginalConnection().getState() != Call.State.ACTIVE || getOriginalConnection().getDurationMillis() <= 0) {
            return;
        }
        this.mIsConnectionTimeReset = true;
        getOriginalConnection().resetConnectionTime();
        resetConnectionTime();
    }

    @Override // com.android.services.telephony.TelephonyConnection
    void setOriginalConnection(Connection connection) {
        super.setOriginalConnection(connection);
        if (getPhone() != null) {
            getPhone().registerForLineControlInfo(this.mHandler, 3, (Object) null);
        }
    }

    @Override // com.android.services.telephony.TelephonyConnection
    public void close() {
        this.mIsConnectionTimeReset = false;
        if (getPhone() != null) {
            getPhone().unregisterForLineControlInfo(this.mHandler);
        }
        super.close();
    }
}
