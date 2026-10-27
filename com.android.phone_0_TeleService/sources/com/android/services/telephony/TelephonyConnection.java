package com.android.services.telephony;

import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.telecom.CallAudioState;
import android.telecom.Conference;
import android.telecom.Connection;
import android.telecom.PhoneAccountHandle;
import android.telecom.StatusHints;
import android.telecom.VideoProfile;
import android.telephony.DisconnectCause;
import android.telephony.PhoneNumberUtils;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Pair;
import com.android.ims.ImsCall;
import com.android.ims.ImsException;
import com.android.ims.internal.ConferenceParticipant;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.internal.telephony.Connection;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.SomeArgs;
import com.android.internal.telephony.gsm.SuppServiceNotification;
import com.android.internal.telephony.imsphone.ImsPhone;
import com.android.internal.telephony.imsphone.ImsPhoneCall;
import com.android.internal.telephony.imsphone.ImsPhoneConnection;
import com.android.phone.ImsUtil;
import com.android.phone.PhoneGlobals;
import com.android.phone.PhoneUtils;
import com.android.phone.R;
import com.android.telephony.Rlog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
abstract class TelephonyConnection extends Connection implements Holdable {
    private static final Map<String, String> sExtrasMap = createExtrasMap();
    private Call.State mConnectionOverriddenState;
    private int mHangupDisconnectCause;
    private boolean mHasHighDefAudio;
    private boolean mIsAdhocConferenceCall;
    private boolean mIsCarrierVideoConferencingSupported;
    private boolean mIsCdmaVoicePrivacyEnabled;
    private boolean mIsConferenceSupported;
    private boolean mIsHoldable;
    private boolean mIsManageImsConferenceCallSupported;
    private boolean mIsMultiParty;
    private boolean mIsNetworkIdentifiedEmergencyCall;
    private boolean mIsTtyEnabled;
    private boolean mIsUsingAssistedDialing;
    private boolean mIsVideoPauseSupported;
    protected com.android.internal.telephony.Connection mOriginalConnection;
    private int mOriginalConnectionCapabilities;
    private Call.State mOriginalConnectionState;
    private List<Uri> mParticipants;
    private Connection.RttTextStream mRttTextStream;
    private boolean mShowPreciseFailedCause;
    private TelephonyConnectionService mTelephonyConnectionService;
    private final Set<TelephonyConnectionListener> mTelephonyListeners;
    private boolean mTreatAsEmergencyCall;
    private boolean mWasImsConnection;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.services.telephony.TelephonyConnection.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            boolean z = true;
            switch (i) {
                case 1:
                    Log.v(TelephonyConnection.this, "MSG_PRECISE_CALL_STATE_CHANGED", new Object[0]);
                    TelephonyConnection.this.updateState();
                    return;
                case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                    Log.v(TelephonyConnection.this, "MSG_RINGBACK_TONE", new Object[0]);
                    if (TelephonyConnection.this.getOriginalConnection() != TelephonyConnection.this.getForegroundConnection()) {
                        Log.v(TelephonyConnection.this, "handleMessage, original connection is not foreground connection, skipping", new Object[0]);
                        return;
                    }
                    boolean zBooleanValue = ((Boolean) ((AsyncResult) message.obj).result).booleanValue();
                    TelephonyConnection.this.setRingbackRequested(zBooleanValue);
                    TelephonyConnection.this.notifyRingbackRequested(zBooleanValue);
                    return;
                case 3:
                case 20:
                    String str = i == 3 ? "MSG_HANDOVER_STATE_CHANGED" : "MSG_REDIAL_CONNECTION_CHANGED";
                    Log.v(TelephonyConnection.this, str, new Object[0]);
                    com.android.internal.telephony.Connection connection = (com.android.internal.telephony.Connection) ((AsyncResult) message.obj).result;
                    if (connection == null) {
                        TelephonyConnection.this.setDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(11, "handover failure, no connection"));
                        TelephonyConnection.this.close();
                        return;
                    }
                    TelephonyConnection telephonyConnection = TelephonyConnection.this;
                    if (telephonyConnection.mOriginalConnection == null) {
                        Log.w(telephonyConnection, str + ": mOriginalConnection==null - invalid state (not cleaned up)", new Object[0]);
                        return;
                    }
                    if (connection != null) {
                        if ((connection.getAddress() == null || TelephonyConnection.this.mOriginalConnection.getAddress() == null || !TelephonyConnection.this.mOriginalConnection.getAddress().equals(connection.getAddress())) && connection.getState() != TelephonyConnection.this.mOriginalConnection.getStateBeforeHandover()) {
                            return;
                        }
                        Log.d(TelephonyConnection.this, "SettingOriginalConnection " + TelephonyConnection.this.mOriginalConnection.toString() + " with " + connection.toString(), new Object[0]);
                        TelephonyConnection.this.setOriginalConnection(connection);
                        TelephonyConnection.this.mWasImsConnection = false;
                        return;
                    }
                    return;
                case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                    TelephonyConnection.this.updateState();
                    return;
                case 5:
                    boolean zBooleanValue2 = ((Boolean) message.obj).booleanValue();
                    Object[] objArr = new Object[1];
                    objArr[0] = zBooleanValue2 ? "Y" : "N";
                    Log.i(this, "Update multiparty state to %s", objArr);
                    TelephonyConnection.this.mIsMultiParty = zBooleanValue2;
                    if (zBooleanValue2) {
                        TelephonyConnection.this.notifyConferenceStarted();
                        return;
                    }
                    return;
                case 6:
                    TelephonyConnection.this.notifyConferenceMergeFailed();
                    return;
                case 7:
                    Phone phone = TelephonyConnection.this.getPhone();
                    TelephonyConnection telephonyConnection2 = TelephonyConnection.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("MSG_SUPP_SERVICE_NOTIFY on phoneId : ");
                    sb.append(phone != null ? Integer.toString(phone.getPhoneId()) : "null");
                    Log.v(telephonyConnection2, sb.toString(), new Object[0]);
                    Object obj = message.obj;
                    if (obj == null || ((AsyncResult) obj).result == null) {
                        return;
                    }
                    SuppServiceNotification suppServiceNotification = (SuppServiceNotification) ((AsyncResult) obj).result;
                    TelephonyConnection telephonyConnection3 = TelephonyConnection.this;
                    if (telephonyConnection3.mOriginalConnection != null) {
                        telephonyConnection3.handleSuppServiceNotification(suppServiceNotification);
                        return;
                    }
                    return;
                case 8:
                    TelephonyConnection.this.setTelephonyVideoState(((Integer) message.obj).intValue());
                    TelephonyConnection.this.refreshConferenceSupported();
                    TelephonyConnection.this.refreshDisableAddCall();
                    TelephonyConnection.this.refreshHoldSupported();
                    TelephonyConnection.this.updateConnectionProperties();
                    return;
                case 9:
                    TelephonyConnection.this.setTelephonyVideoProvider((Connection.VideoProvider) message.obj);
                    return;
                case 10:
                    TelephonyConnection.this.setAudioQuality(((Integer) message.obj).intValue());
                    return;
                case 11:
                    TelephonyConnection.this.updateConferenceParticipants((List) message.obj);
                    return;
                case 12:
                    TelephonyConnection.this.updateExtras((Bundle) message.obj);
                    return;
                case 13:
                    TelephonyConnection.this.setOriginalConnectionCapabilities(message.arg1);
                    return;
                case 14:
                    Pair pair = (Pair) ((AsyncResult) message.obj).result;
                    boolean zBooleanValue3 = ((Boolean) pair.second).booleanValue();
                    com.android.internal.telephony.Connection connection2 = (com.android.internal.telephony.Connection) pair.first;
                    TelephonyConnection telephonyConnection4 = TelephonyConnection.this;
                    if (connection2 == telephonyConnection4.mOriginalConnection) {
                        if (zBooleanValue3) {
                            telephonyConnection4.sendTelephonyConnectionEvent("android.telecom.event.ON_HOLD_TONE_START", null);
                            return;
                        } else {
                            telephonyConnection4.sendTelephonyConnectionEvent("android.telecom.event.ON_HOLD_TONE_END", null);
                            return;
                        }
                    }
                    return;
                case 15:
                    Log.d(this, "MSG_CDMA_VOICE_PRIVACY_ON received", new Object[0]);
                    TelephonyConnection.this.setCdmaVoicePrivacy(true);
                    return;
                case 16:
                    Log.d(this, "MSG_CDMA_VOICE_PRIVACY_OFF received", new Object[0]);
                    TelephonyConnection.this.setCdmaVoicePrivacy(false);
                    return;
                case 17:
                    TelephonyConnection.this.hangup(((Integer) message.obj).intValue());
                    return;
                case 18:
                    int iIntValue = ((Integer) message.obj).intValue();
                    if (TelephonyConnection.this.getCallRadioTech() == iIntValue || (!TelephonyConnection.this.isWifi() && iIntValue != 18)) {
                        z = false;
                    }
                    TelephonyConnection.this.setCallRadioTech(iIntValue);
                    if (z) {
                        TelephonyConnection.this.updateConnectionProperties();
                        TelephonyConnection.this.updateStatusHints();
                        TelephonyConnection.this.refreshDisableAddCall();
                        return;
                    }
                    return;
                case 19:
                    SomeArgs someArgs = (SomeArgs) message.obj;
                    try {
                        TelephonyConnection.this.sendTelephonyConnectionEvent((String) someArgs.arg1, (Bundle) someArgs.arg2);
                        return;
                    } finally {
                        someArgs.recycle();
                    }
                case 21:
                    TelephonyConnection.this.reject(((Integer) message.obj).intValue());
                    return;
                default:
                    return;
            }
        }
    };
    private final Connection.PostDialListener mPostDialListener = new Connection.PostDialListener() { // from class: com.android.services.telephony.TelephonyConnection.2
        public void onPostDialWait() {
            Log.v(TelephonyConnection.this, "onPostDialWait", new Object[0]);
            TelephonyConnection telephonyConnection = TelephonyConnection.this;
            com.android.internal.telephony.Connection connection = telephonyConnection.mOriginalConnection;
            if (connection != null) {
                telephonyConnection.setPostDialWait(connection.getRemainingPostDialString());
            }
        }

        public void onPostDialChar(char c) {
            Log.v(TelephonyConnection.this, "onPostDialChar: %s", Character.valueOf(c));
            TelephonyConnection telephonyConnection = TelephonyConnection.this;
            if (telephonyConnection.mOriginalConnection != null) {
                telephonyConnection.setNextPostDialChar(c);
            }
        }
    };
    private final Connection.Listener mOriginalConnectionListener = new Connection.ListenerBase() { // from class: com.android.services.telephony.TelephonyConnection.3
        public void onVideoStateChanged(int i) {
            TelephonyConnection.this.mHandler.obtainMessage(8, Integer.valueOf(i)).sendToTarget();
        }

        public void onConnectionCapabilitiesChanged(int i) {
            TelephonyConnection.this.mHandler.obtainMessage(13, i, 0).sendToTarget();
        }

        public void onVideoProviderChanged(Connection.VideoProvider videoProvider) {
            TelephonyConnection.this.mHandler.obtainMessage(9, videoProvider).sendToTarget();
        }

        public void onCallRadioTechChanged(int i) {
            TelephonyConnection.this.mHandler.obtainMessage(18, Integer.valueOf(i)).sendToTarget();
        }

        public void onAudioQualityChanged(int i) {
            TelephonyConnection.this.mHandler.obtainMessage(10, Integer.valueOf(i)).sendToTarget();
        }

        public void onConferenceParticipantsChanged(List<ConferenceParticipant> list) {
            TelephonyConnection.this.mHandler.obtainMessage(11, list).sendToTarget();
        }

        public void onMultipartyStateChanged(boolean z) {
            TelephonyConnection.this.handleMultipartyStateChange(z);
        }

        public void onConferenceMergedFailed() {
            TelephonyConnection.this.handleConferenceMergeFailed();
        }

        public void onExtrasChanged(Bundle bundle) {
            TelephonyConnection.this.mHandler.obtainMessage(12, bundle).sendToTarget();
        }

        public void onExitedEcmMode() {
            TelephonyConnection.this.handleExitedEcmMode();
        }

        public void onCallPullFailed(com.android.internal.telephony.Connection connection) {
            if (connection == null) {
                return;
            }
            Log.i(this, "onCallPullFailed - pull failed; swapping back to call: %s", connection);
            TelephonyConnection.this.sendTelephonyConnectionEvent("android.telecom.event.CALL_PULL_FAILED", null);
            TelephonyConnection.this.setOriginalConnection(connection);
            TelephonyConnection.this.setActiveInternal();
        }

        public void onHandoverToWifiFailed() {
            TelephonyConnection.this.sendTelephonyConnectionEvent("android.telephony.event.EVENT_HANDOVER_TO_WIFI_FAILED", null);
        }

        public void onConnectionEvent(String str, Bundle bundle) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = str;
            someArgsObtain.arg2 = bundle;
            TelephonyConnection.this.mHandler.obtainMessage(19, someArgsObtain).sendToTarget();
        }

        public void onRttModifyRequestReceived() {
            TelephonyConnection.this.sendRemoteRttRequest();
        }

        public void onRttModifyResponseReceived(int i) {
            TelephonyConnection.this.updateConnectionProperties();
            TelephonyConnection.this.refreshConferenceSupported();
            if (i == 1) {
                TelephonyConnection.this.sendRttInitiationSuccess();
            } else {
                TelephonyConnection.this.sendRttInitiationFailure(i);
            }
        }

        public void onDisconnect(int i) {
            Log.i(this, "onDisconnect: callId=%s, cause=%s", TelephonyConnection.this.getTelecomCallId(), DisconnectCause.toString(i));
            TelephonyConnection.this.mHandler.obtainMessage(4).sendToTarget();
        }

        public void onRttInitiated() {
            TelephonyConnection telephonyConnection = TelephonyConnection.this;
            if (telephonyConnection.mOriginalConnection != null) {
                telephonyConnection.updateConnectionProperties();
                TelephonyConnection.this.refreshConferenceSupported();
            }
            TelephonyConnection.this.sendRttInitiationSuccess();
        }

        public void onRttTerminated() {
            TelephonyConnection.this.updateConnectionProperties();
            TelephonyConnection.this.refreshConferenceSupported();
            TelephonyConnection.this.sendRttSessionRemotelyTerminated();
        }

        public void onOriginalConnectionReplaced(com.android.internal.telephony.Connection connection) {
            TelephonyConnection.this.setOriginalConnection(connection);
        }

        public void onIsNetworkEmergencyCallChanged(boolean z) {
            TelephonyConnection.this.setIsNetworkIdentifiedEmergencyCall(z);
        }
    };
    private Call.State mConnectionState = Call.State.IDLE;
    private Bundle mOriginalConnectionExtras = new Bundle();
    private boolean mIsStateOverridden = false;

    public static abstract class TelephonyConnectionListener {
        public void onConferenceParticipantsChanged(android.telecom.Connection connection, List<ConferenceParticipant> list) {
        }

        public void onConferenceStarted() {
        }

        public void onConferenceSupportedChanged(android.telecom.Connection connection, boolean z) {
        }

        public void onConnectionCapabilitiesChanged(android.telecom.Connection connection, int i) {
        }

        public void onConnectionEvent(android.telecom.Connection connection, String str, Bundle bundle) {
        }

        public void onConnectionPropertiesChanged(android.telecom.Connection connection, int i) {
        }

        public void onDestroyed(android.telecom.Connection connection) {
        }

        public void onDisconnected(android.telecom.Connection connection, android.telecom.DisconnectCause disconnectCause) {
        }

        public void onExtrasChanged(android.telecom.Connection connection, Bundle bundle) {
        }

        public void onExtrasRemoved(android.telecom.Connection connection, List<String> list) {
        }

        public void onOriginalConnectionConfigured(TelephonyConnection telephonyConnection) {
        }

        public void onOriginalConnectionRetry(TelephonyConnection telephonyConnection, boolean z) {
        }

        public void onRingbackRequested(android.telecom.Connection connection, boolean z) {
        }

        public void onStateChanged(android.telecom.Connection connection, int i) {
        }

        public void onStatusHintsChanged(android.telecom.Connection connection, StatusHints statusHints) {
        }

        public void onVideoProviderChanged(android.telecom.Connection connection, Connection.VideoProvider videoProvider) {
        }

        public void onVideoStateChanged(android.telecom.Connection connection, int i) {
        }
    }

    private int changeBitmask(int i, int i2, boolean z) {
        return z ? i | i2 : (~i2) & i;
    }

    private int transformCodec(int i) {
        switch (i) {
            case 1:
                return 1;
            case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                return 2;
            case 3:
                return 3;
            case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;
            case 12:
                return 12;
            case 13:
                return 13;
            case 14:
                return 14;
            case 15:
                return 15;
            case 16:
                return 16;
            case 17:
                return 17;
            case 18:
                return 18;
            case 19:
                return 19;
            case 20:
                return 20;
            default:
                return 0;
        }
    }

    public abstract TelephonyConnection cloneConnection();

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSuppServiceNotification(SuppServiceNotification suppServiceNotification) {
        Log.i(this, "handleSuppServiceNotification: type=%d, code=%d", Integer.valueOf(suppServiceNotification.notificationType), Integer.valueOf(suppServiceNotification.code));
        if (suppServiceNotification.notificationType == 0 && suppServiceNotification.code == 2) {
            sendTelephonyConnectionEvent("android.telephony.event.EVENT_CALL_FORWARDED", null);
        }
        sendSuppServiceNotificationEvent(suppServiceNotification.notificationType, suppServiceNotification.code);
    }

    private void sendSuppServiceNotificationEvent(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("android.telephony.extra.NOTIFICATION_TYPE", i);
        bundle.putInt("android.telephony.extra.NOTIFICATION_CODE", i2);
        bundle.putCharSequence("android.telephony.extra.NOTIFICATION_MESSAGE", getSuppServiceMessage(i, i2));
        sendTelephonyConnectionEvent("android.telephony.event.EVENT_SUPPLEMENTARY_SERVICE_NOTIFICATION", bundle);
    }

    private CharSequence getSuppServiceMessage(int i, int i2) {
        int i3 = R.string.supp_service_closed_user_group_call;
        if (i == 0) {
            switch (i2) {
                case 0:
                case 1:
                    i3 = R.string.supp_service_call_forwarding_active;
                    break;
                case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                    i3 = R.string.supp_service_notification_call_forwarded;
                    break;
                case 3:
                    i3 = R.string.supp_service_notification_call_waiting;
                    break;
                case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                    break;
                case 5:
                    i3 = R.string.supp_service_outgoing_calls_barred;
                    break;
                case 6:
                    i3 = R.string.supp_service_incoming_calls_barred;
                    break;
                case 7:
                    i3 = R.string.supp_service_clir_suppression_rejected;
                    break;
                case 8:
                    i3 = R.string.supp_service_notification_call_deflected;
                    break;
                default:
                    i3 = -1;
                    break;
            }
        } else if (i == 1) {
            switch (i2) {
                case 0:
                    i3 = R.string.supp_service_forwarded_call;
                    break;
                case 1:
                    break;
                case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                    i3 = R.string.supp_service_call_on_hold;
                    break;
                case 3:
                    i3 = R.string.supp_service_call_resumed;
                    break;
                case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                    i3 = R.string.supp_service_conference_call;
                    break;
                case 5:
                    i3 = R.string.supp_service_held_call_released;
                    break;
                case 6:
                default:
                    i3 = -1;
                    break;
                case 7:
                    i3 = R.string.supp_service_additional_ect_connecting;
                    break;
                case 8:
                    i3 = R.string.supp_service_additional_ect_connected;
                    break;
                case 9:
                    i3 = R.string.supp_service_deflected_call;
                    break;
                case 10:
                    i3 = R.string.supp_service_additional_call_forwarded;
                    break;
            }
        } else {
            i3 = -1;
        }
        if (i3 == -1 || getPhone() == null || getPhone().getContext() == null) {
            return null;
        }
        return getResourceText(i3);
    }

    @VisibleForTesting
    public CharSequence getResourceText(int i) {
        return SubscriptionManager.getResourcesForSubId(getPhone().getContext(), getPhone().getSubId()).getText(i);
    }

    @VisibleForTesting
    public String getResourceString(int i) {
        return SubscriptionManager.getResourcesForSubId(getPhone().getContext(), getPhone().getSubId()).getString(i);
    }

    public boolean isCarrierVideoConferencingSupported() {
        return this.mIsCarrierVideoConferencingSupported;
    }

    protected TelephonyConnection(com.android.internal.telephony.Connection connection, String str, int i) {
        Call.State state = Call.State.IDLE;
        this.mOriginalConnectionState = state;
        this.mConnectionOverriddenState = state;
        this.mRttTextStream = null;
        this.mIsMultiParty = false;
        this.mHangupDisconnectCause = -1;
        this.mTelephonyListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
        setCallDirection(i);
        setTelecomCallId(str);
        if (connection != null) {
            setOriginalConnection(connection);
        }
    }

    @Override // android.telecom.Connection
    public void onCallAudioStateChanged(CallAudioState callAudioState) {
        if (getPhone() != null) {
            getPhone().setEchoSuppressionEnabled();
        }
    }

    @Override // android.telecom.Connection
    public void onStateChanged(int i) {
        Log.v(this, "onStateChanged, state: " + android.telecom.Connection.stateToString(i), new Object[0]);
        updateStatusHints();
    }

    @Override // android.telecom.Connection
    public void onDisconnect() {
        Log.v(this, "onDisconnect", new Object[0]);
        this.mHandler.obtainMessage(17, 3).sendToTarget();
    }

    public void onDisconnectConferenceParticipant(Uri uri) {
        Log.v(this, "onDisconnectConferenceParticipant %s", uri);
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection == null) {
            return;
        }
        connection.onDisconnectConferenceParticipant(uri);
    }

    @Override // android.telecom.Connection
    public void onSeparate() {
        Log.v(this, "onSeparate", new Object[0]);
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null) {
            try {
                connection.separate();
            } catch (CallStateException e) {
                Log.e(this, e, "Call to Connection.separate failed with exception", new Object[0]);
            }
        }
    }

    @Override // android.telecom.Connection
    public void onAddConferenceParticipants(List<Uri> list) {
        performAddConferenceParticipants(list);
    }

    @Override // android.telecom.Connection
    public void onAbort() {
        Log.v(this, "onAbort", new Object[0]);
        this.mHandler.obtainMessage(17, 3).sendToTarget();
    }

    @Override // android.telecom.Connection
    public void onHold() {
        performHold();
    }

    @Override // android.telecom.Connection
    public void onUnhold() {
        performUnhold();
    }

    @Override // android.telecom.Connection
    public void onAnswer(int i) {
        performAnswer(i);
    }

    @Override // android.telecom.Connection
    public void onDeflect(Uri uri) {
        Log.v(this, "onDeflect", new Object[0]);
        if (this.mOriginalConnection == null || !isValidRingingCall()) {
            return;
        }
        if (uri == null) {
            Log.w(this, "call deflect address uri is null", new Object[0]);
            return;
        }
        String scheme = uri.getScheme();
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        if (!"voicemail".equals(scheme)) {
            if (!"tel".equals(scheme)) {
                Log.w(this, "onDeflect, address scheme is not of type tel instead: " + scheme, new Object[0]);
                return;
            }
            if (PhoneNumberUtils.isUriNumber(schemeSpecificPart)) {
                Log.w(this, "Invalid deflect address. Not a legal PSTN number.", new Object[0]);
                return;
            }
            String strConvertAndStrip = PhoneNumberUtils.convertAndStrip(schemeSpecificPart);
            if (TextUtils.isEmpty(strConvertAndStrip)) {
                Log.w(this, "Empty deflect number obtained from address uri", new Object[0]);
                return;
            }
            try {
                this.mOriginalConnection.deflect(strConvertAndStrip);
                return;
            } catch (CallStateException e) {
                Log.e(this, e, "Failed to deflect call.", new Object[0]);
                return;
            }
        }
        Log.w(this, "Cannot deflect to voicemail uri", new Object[0]);
    }

    @Override // android.telecom.Connection
    public void onReject() {
        performReject(1);
    }

    @Override // android.telecom.Connection
    public void onReject(int i) {
        performReject(i);
    }

    public void performReject(int i) {
        Log.v(this, "performReject", new Object[0]);
        if (isValidRingingCall()) {
            this.mHandler.obtainMessage(21, Integer.valueOf(i)).sendToTarget();
        }
        super.onReject();
    }

    public void onTransfer(Uri uri, boolean z) {
        Log.v(this, "onTransfer", new Object[0]);
        if (this.mOriginalConnection != null) {
            if (uri == null) {
                Log.w(this, "call transfer uri is null", new Object[0]);
                return;
            }
            String scheme = uri.getScheme();
            String schemeSpecificPart = uri.getSchemeSpecificPart();
            if (!"voicemail".equals(scheme)) {
                if (!"tel".equals(scheme)) {
                    Log.w(this, "onTransfer, number scheme is not of type tel instead: " + scheme, new Object[0]);
                    return;
                }
                if (PhoneNumberUtils.isUriNumber(schemeSpecificPart)) {
                    Log.w(this, "Invalid transfer address. Not a legal PSTN number.", new Object[0]);
                    return;
                }
                String strConvertAndStrip = PhoneNumberUtils.convertAndStrip(schemeSpecificPart);
                if (TextUtils.isEmpty(strConvertAndStrip)) {
                    Log.w(this, "Empty transfer number obtained from uri", new Object[0]);
                    return;
                }
                try {
                    this.mOriginalConnection.transfer(strConvertAndStrip, z);
                    return;
                } catch (CallStateException e) {
                    Log.e(this, e, "Failed to transfer call.", new Object[0]);
                    return;
                }
            }
            Log.w(this, "Cannot transfer to voicemail uri", new Object[0]);
        }
    }

    public void onTransfer(android.telecom.Connection connection) {
        Log.v(this, "onConsultativeTransfer", new Object[0]);
        com.android.internal.telephony.Connection connection2 = this.mOriginalConnection;
        if (connection2 == null || !(connection instanceof TelephonyConnection)) {
            return;
        }
        try {
            connection2.consultativeTransfer(((TelephonyConnection) connection).getOriginalConnection());
        } catch (CallStateException e) {
            Log.e(this, e, "Failed to transfer call.", new Object[0]);
        }
    }

    @Override // android.telecom.Connection
    public void onPostDialContinue(boolean z) {
        Log.v(this, "onPostDialContinue, proceed: " + z, new Object[0]);
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null) {
            if (z) {
                connection.proceedAfterWaitChar();
            } else {
                connection.cancelPostDial();
            }
        }
    }

    @Override // android.telecom.Connection
    public void onPullExternalCall() {
        if ((getConnectionProperties() & 16) != 16) {
            Log.w(this, "onPullExternalCall - cannot pull non-external call", new Object[0]);
            return;
        }
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null) {
            connection.pullExternalCall();
        }
    }

    @Override // android.telecom.Connection
    public void onStartRtt(Connection.RttTextStream rttTextStream) {
        if (isImsConnection()) {
            ImsPhoneConnection imsPhoneConnection = this.mOriginalConnection;
            if (imsPhoneConnection.isRttEnabledForCall()) {
                imsPhoneConnection.setCurrentRttTextStream(rttTextStream);
                return;
            } else {
                imsPhoneConnection.startRtt(rttTextStream);
                return;
            }
        }
        Log.w(this, "onStartRtt - not in IMS, so RTT cannot be enabled.", new Object[0]);
    }

    @Override // android.telecom.Connection
    public void onStopRtt() {
        if (isImsConnection()) {
            ImsPhoneConnection imsPhoneConnection = this.mOriginalConnection;
            if (imsPhoneConnection.isRttEnabledForCall()) {
                imsPhoneConnection.stopRtt();
                return;
            } else {
                Log.w(this, "onStopRtt - not in RTT call, ignoring", new Object[0]);
                return;
            }
        }
        Log.w(this, "onStopRtt - not in IMS, ignoring", new Object[0]);
    }

    @Override // android.telecom.Connection
    public void handleRttUpgradeResponse(Connection.RttTextStream rttTextStream) {
        if (!isImsConnection()) {
            Log.w(this, "handleRttUpgradeResponse - not in IMS, so RTT cannot be enabled.", new Object[0]);
        } else {
            this.mOriginalConnection.sendRttModifyResponse(rttTextStream);
        }
    }

    public void performAnswer(int i) {
        Log.v(this, "performAnswer", new Object[0]);
        if (!isValidRingingCall() || getPhone() == null) {
            return;
        }
        try {
            getPhone().acceptCall(i);
        } catch (CallStateException e) {
            Log.e(this, e, "Failed to accept call.", new Object[0]);
        }
    }

    public void performHold() {
        Log.v(this, "performHold", new Object[0]);
        if (Call.State.ACTIVE == this.mConnectionState) {
            Log.v(this, "Holding active call", new Object[0]);
            try {
                ImsPhone phone = this.mOriginalConnection.getCall().getPhone();
                if (phone.getRingingCall().getState() != Call.State.WAITING) {
                    if (phone.getPhoneType() == 5) {
                        phone.holdActiveCall();
                        return;
                    } else {
                        phone.switchHoldingAndActive();
                        return;
                    }
                }
                return;
            } catch (CallStateException e) {
                Log.e(this, e, "Exception occurred while trying to put call on hold.", new Object[0]);
                return;
            }
        }
        Log.w(this, "Cannot put a call that is not currently active on hold.", new Object[0]);
    }

    public void performUnhold() {
        Log.v(this, "performUnhold", new Object[0]);
        if (Call.State.HOLDING == this.mConnectionState) {
            try {
                ImsPhone phone = this.mOriginalConnection.getCall().getPhone();
                if (phone.getPhoneType() == 5) {
                    phone.unholdHeldCall();
                    return;
                } else if (!hasMultipleTopLevelCalls()) {
                    this.mOriginalConnection.getCall().getPhone().switchHoldingAndActive();
                    return;
                } else {
                    Log.i(this, "Skipping unhold command for %s", this);
                    return;
                }
            } catch (CallStateException e) {
                Log.e(this, e, "Exception occurred while trying to release call from hold.", new Object[0]);
                return;
            }
        }
        Log.w(this, "Cannot release a call that is not already on hold from hold.", new Object[0]);
    }

    public void performConference(android.telecom.Connection connection) {
        Log.d(this, "performConference - %s", this);
        if (getPhone() != null) {
            try {
                getPhone().conference();
            } catch (CallStateException e) {
                Log.e(this, e, "Failed to conference call.", new Object[0]);
            }
        }
    }

    private String[] getAddConferenceParticipants(List<Uri> list) {
        String[] strArr = new String[list.size()];
        Iterator<Uri> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next().getSchemeSpecificPart();
            i++;
        }
        return strArr;
    }

    public void performAddConferenceParticipants(List<Uri> list) {
        Log.v(this, "performAddConferenceParticipants", new Object[0]);
        if (this.mOriginalConnection.getCall() instanceof ImsPhoneCall) {
            try {
                this.mOriginalConnection.getCall().getImsCall().inviteParticipants(getAddConferenceParticipants(list));
            } catch (ImsException e) {
                Log.e(this, e, "failed to add conference participants", new Object[0]);
            }
        }
    }

    protected int buildConnectionCapabilities() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        int i = (connection == null || !connection.isIncoming()) ? 0 : 262144;
        if (!shouldTreatAsEmergencyCall() && isImsConnection() && canHoldImsCalls()) {
            i |= 2;
            if (this.mIsHoldable && (getState() == 4 || getState() == 5)) {
                i |= 1;
            }
        }
        Log.d(this, "buildConnectionCapabilities: isHoldable = " + this.mIsHoldable + " State = " + getState() + " capabilities = " + i, new Object[0]);
        return i;
    }

    protected final void updateConnectionCapabilities() {
        int iChangeBitmask = changeBitmask(changeBitmask(applyAddParticipantCapabilities(changeBitmask(applyConferenceTerminationCapabilities(changeBitmask(changeBitmask(applyOriginalConnectionCapabilities(buildConnectionCapabilities()), 1048576, this.mIsVideoPauseSupported && isVideoCapable()), 16777216, isExternalConnection() && isPullable())), 33554432, isImsConnection() && canDeflectImsCalls())), 268435456, isImsConnection() && canConsultativeTransfer()), 134217728, isImsConnection() && canTransferToNumber());
        if (getConnectionCapabilities() != iChangeBitmask) {
            setConnectionCapabilities(iChangeBitmask);
            notifyConnectionCapabilitiesChanged(iChangeBitmask);
        }
    }

    protected int buildConnectionProperties() {
        Phone phone = getPhone();
        return (phone == null || !phone.isInEcm()) ? 0 : 1;
    }

    protected final void updateConnectionProperties() {
        int iChangeBitmask = changeBitmask(changeBitmask(changeBitmask(changeBitmask(changeBitmask(changeBitmask(changeBitmask(changeBitmask(buildConnectionProperties(), 4, hasHighDefAudioProperty()), 8, isWifi()), 16, isExternalConnection()), 32, this.mIsCdmaVoicePrivacyEnabled), 512, this.mIsUsingAssistedDialing), 256, isRtt()), 1024, isNetworkIdentifiedEmergencyCall()), 4096, isAdhocConferenceCall());
        if (getConnectionProperties() != iChangeBitmask) {
            setTelephonyConnectionProperties(iChangeBitmask);
        }
    }

    public void setTelephonyConnectionProperties(int i) {
        setConnectionProperties(i);
        notifyConnectionPropertiesChanged(i);
    }

    protected final void updateAddress() {
        Uri addressFromNumber;
        updateConnectionCapabilities();
        updateConnectionProperties();
        if (this.mOriginalConnection != null) {
            if (isShowingOriginalDialString() && this.mOriginalConnection.getOrigDialString() != null) {
                addressFromNumber = getAddressFromNumber(this.mOriginalConnection.getOrigDialString());
            } else {
                addressFromNumber = getAddressFromNumber(this.mOriginalConnection.getAddress());
            }
            int numberPresentation = this.mOriginalConnection.getNumberPresentation();
            if (!Objects.equals(addressFromNumber, getAddress()) || numberPresentation != getAddressPresentation()) {
                Log.v(this, "updateAddress, address changed", new Object[0]);
                if ((getConnectionProperties() & 64) != 0) {
                    addressFromNumber = null;
                }
                setAddress(addressFromNumber, numberPresentation);
            }
            String strFilterCnapName = filterCnapName(this.mOriginalConnection.getCnapName());
            int cnapNamePresentation = this.mOriginalConnection.getCnapNamePresentation();
            if (!Objects.equals(strFilterCnapName, getCallerDisplayName()) || cnapNamePresentation != getCallerDisplayNamePresentation()) {
                Log.v(this, "updateAddress, caller display name changed", new Object[0]);
                setCallerDisplayName(strFilterCnapName, cnapNamePresentation);
            }
            if (PhoneNumberUtils.isEmergencyNumber(this.mOriginalConnection.getAddress())) {
                this.mTreatAsEmergencyCall = true;
            }
            refreshConferenceSupported();
        }
    }

    void setOriginalConnection(com.android.internal.telephony.Connection connection) {
        Log.v(this, "new TelephonyConnection, originalConnection: " + connection, new Object[0]);
        if (this.mOriginalConnection != null && connection != null && !connection.isIncoming() && connection.getOrigDialString() == null && isShowingOriginalDialString()) {
            Log.i(this, "new original dial string is null, convert to: " + this.mOriginalConnection.getOrigDialString(), new Object[0]);
            connection.restoreDialedNumberAfterConversion(this.mOriginalConnection.getOrigDialString());
        }
        clearOriginalConnection();
        this.mOriginalConnectionExtras.clear();
        this.mOriginalConnection = connection;
        connection.setTelecomCallId(getTelecomCallId());
        getPhone().registerForPreciseCallStateChanged(this.mHandler, 1, (Object) null);
        getPhone().registerForHandoverStateChanged(this.mHandler, 3, (Object) null);
        getPhone().registerForRedialConnectionChanged(this.mHandler, 20, (Object) null);
        getPhone().registerForRingbackTone(this.mHandler, 2, (Object) null);
        getPhone().registerForSuppServiceNotification(this.mHandler, 7, (Object) null);
        getPhone().registerForOnHoldTone(this.mHandler, 14, (Object) null);
        getPhone().registerForInCallVoicePrivacyOn(this.mHandler, 15, (Object) null);
        getPhone().registerForInCallVoicePrivacyOff(this.mHandler, 16, (Object) null);
        this.mOriginalConnection.addPostDialListener(this.mPostDialListener);
        this.mOriginalConnection.addListener(this.mOriginalConnectionListener);
        setTelephonyVideoState(this.mOriginalConnection.getVideoState());
        setOriginalConnectionCapabilities(this.mOriginalConnection.getConnectionCapabilities());
        setIsNetworkIdentifiedEmergencyCall(this.mOriginalConnection.isNetworkIdentifiedEmergencyCall());
        setIsAdhocConferenceCall(this.mOriginalConnection.isAdhocConference());
        setAudioModeIsVoip(this.mOriginalConnection.getAudioModeIsVoip());
        setTelephonyVideoProvider(this.mOriginalConnection.getVideoProvider());
        setAudioQuality(this.mOriginalConnection.getAudioQuality());
        setTechnologyTypeExtra();
        setCallRadioTech(this.mOriginalConnection.getCallRadioTech());
        Bundle connectionExtras = this.mOriginalConnection.getConnectionExtras();
        this.mHandler.obtainMessage(12, connectionExtras != null ? new Bundle(connectionExtras) : null).sendToTarget();
        if (PhoneNumberUtils.isEmergencyNumber(this.mOriginalConnection.getAddress())) {
            this.mTreatAsEmergencyCall = true;
        }
        setCallerNumberVerificationStatus(this.mOriginalConnection.getNumberVerificationStatus());
        if (isImsConnection()) {
            this.mWasImsConnection = true;
        }
        this.mIsMultiParty = this.mOriginalConnection.isMultiparty();
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        if (this.mOriginalConnection.isActiveCallDisconnectedOnAnswer()) {
            bundle.putBoolean("android.telecom.extra.ANSWERING_DROPS_FG_CALL", true);
        } else {
            arrayList.add("android.telecom.extra.ANSWERING_DROPS_FG_CALL");
        }
        if (shouldSetDisableAddCallExtra()) {
            bundle.putBoolean("android.telecom.extra.DISABLE_ADD_CALL", true);
        } else {
            arrayList.add("android.telecom.extra.DISABLE_ADD_CALL");
        }
        putTelephonyExtras(bundle);
        removeTelephonyExtras(arrayList);
        updateState();
        if (this.mOriginalConnection == null) {
            Log.w(this, "original Connection was nulled out as part of setOriginalConnection. " + connection, new Object[0]);
        }
        fireOnOriginalConnectionConfigured();
    }

    private String filterCnapName(final String str) {
        if (str == null) {
            return null;
        }
        PersistableBundle carrierConfig = getCarrierConfig();
        String[] stringArray = carrierConfig != null ? carrierConfig.getStringArray("filtered_cnap_names_string_array") : null;
        if (stringArray == null || Arrays.asList(stringArray).stream().filter(new Predicate() { // from class: com.android.services.telephony.-$$Lambda$TelephonyConnection$3fV8sdVX_D-X8iUSJHq8qeqawGM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((String) obj).equals(str.toUpperCase());
            }
        }).count() <= 0) {
            return str;
        }
        Log.i(this, "filterCnapName: Filtered CNAP Name: " + str, new Object[0]);
        return "";
    }

    private void setTechnologyTypeExtra() {
        if (getPhone() != null) {
            Bundle extras = getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putInt("android.telecom.extra.CALL_TECHNOLOGY_TYPE", getPhone().getPhoneType());
            putTelephonyExtras(extras);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshHoldSupported() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection == null) {
            Log.w(this, "refreshHoldSupported org conn is null", new Object[0]);
        } else {
            if (connection.shouldAllowHoldingVideoCall()) {
                return;
            }
            if (canHoldImsCalls() != ((getConnectionCapabilities() & 3) != 0)) {
                updateConnectionCapabilities();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDisableAddCall() {
        if (shouldSetDisableAddCallExtra()) {
            Bundle extras = getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putBoolean("android.telecom.extra.DISABLE_ADD_CALL", true);
            putTelephonyExtras(extras);
            return;
        }
        removeExtras("android.telecom.extra.DISABLE_ADD_CALL");
    }

    private void refreshCodecType() {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        int iTransformCodec = isImsConnection() ? transformCodec(getOriginalConnection().getAudioCodec()) : 0;
        if (iTransformCodec != extras.getInt("android.telecom.extra.AUDIO_CODEC", 0)) {
            extras.putInt("android.telecom.extra.AUDIO_CODEC", iTransformCodec);
            putTelephonyExtras(extras);
        }
    }

    private boolean shouldSetDisableAddCallExtra() {
        ImsPhone phone;
        boolean zIsWfcEnabled;
        boolean zWasVideoCall;
        boolean zIsVideoCall;
        ImsCall imsCall;
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection == null || connection.shouldAllowAddCallDuringVideoCall() || (phone = getPhone()) == null) {
            return false;
        }
        if (phone instanceof ImsPhone) {
            ImsPhoneCall foregroundCall = phone.getForegroundCall();
            if (foregroundCall == null || (imsCall = foregroundCall.getImsCall()) == null) {
                zWasVideoCall = false;
                zIsVideoCall = false;
            } else {
                zIsVideoCall = imsCall.isVideoCall();
                zWasVideoCall = imsCall.wasVideoCall();
            }
            zIsWfcEnabled = ImsUtil.isWfcEnabled(phone.getContext(), phone.getPhoneId());
        } else {
            zIsWfcEnabled = false;
            zWasVideoCall = false;
            zIsVideoCall = false;
        }
        if (zIsVideoCall) {
            return true;
        }
        return zWasVideoCall && isWifi() && !zIsWfcEnabled;
    }

    private boolean hasHighDefAudioProperty() {
        if (!this.mHasHighDefAudio) {
            return false;
        }
        boolean zIsVideo = VideoProfile.isVideo(getVideoState());
        PersistableBundle carrierConfig = getCarrierConfig();
        boolean z = carrierConfig != null && carrierConfig.getBoolean("wifi_calls_can_be_hd_audio");
        boolean z2 = carrierConfig != null && carrierConfig.getBoolean("video_calls_can_be_hd_audio");
        boolean z3 = carrierConfig != null && carrierConfig.getBoolean("gsm_cdma_calls_can_be_hd_audio");
        if (!(carrierConfig != null && carrierConfig.getBoolean("display_hd_audio_property_bool"))) {
            return false;
        }
        if (isGsmCdmaConnection() && !z3) {
            return false;
        }
        if (!zIsVideo || z2) {
            return !isWifi() || z;
        }
        return false;
    }

    public final List<Uri> getParticipants() {
        return this.mParticipants;
    }

    public final void setParticipants(List<Uri> list) {
        this.mParticipants = list;
    }

    public final boolean isAdhocConferenceCall() {
        return this.mIsAdhocConferenceCall;
    }

    public void setIsAdhocConferenceCall(boolean z) {
        this.mIsAdhocConferenceCall = z;
        updateConnectionProperties();
    }

    private boolean canHoldImsCalls() {
        com.android.internal.telephony.Connection connection;
        PersistableBundle carrierConfig = getCarrierConfig();
        return (!doesDeviceRespectHoldCarrierConfig() || carrierConfig == null || carrierConfig.getBoolean("allow_hold_in_ims_call")) && (((connection = this.mOriginalConnection) != null && connection.shouldAllowHoldingVideoCall()) || !VideoProfile.isVideo(getVideoState()));
    }

    private boolean isConferenceHosted() {
        if (getTelephonyConnectionService() != null) {
            for (Conference conference : getTelephonyConnectionService().getAllConferences()) {
                if (conference instanceof ImsConference) {
                    ImsConference imsConference = (ImsConference) conference;
                    if (getState() != conference.getState() && imsConference.isConferenceHost()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isAddParticipantCapable() {
        com.android.internal.telephony.Connection connection;
        Call.State state;
        boolean z = false;
        if (getPhone() == null || getPhone().getPhoneType() != 5 || !getCarrierConfig().getBoolean("support_add_conference_participants_bool")) {
            return false;
        }
        if ((!this.mTreatAsEmergencyCall && ((state = this.mConnectionState) == Call.State.ACTIVE || state == Call.State.HOLDING)) && (((connection = this.mOriginalConnection) != null && connection.isConferenceHost()) || !isConferenceHosted())) {
            z = true;
        }
        return (!z || this.mOriginalConnection == null || this.mIsMultiParty) ? z : this.mOriginalConnectionExtras.getBoolean("conference_avail", z);
    }

    private int applyAddParticipantCapabilities(int i) {
        if (isAddParticipantCapable()) {
            return changeBitmask(i, 67108864, true);
        }
        return changeBitmask(i, 67108864, false);
    }

    @VisibleForTesting
    public PersistableBundle getCarrierConfig() {
        Phone phone = getPhone();
        if (phone == null) {
            return null;
        }
        return PhoneGlobals.getInstance().getCarrierConfigForSubId(phone.getSubId());
    }

    private boolean canDeflectImsCalls() {
        PersistableBundle carrierConfig = getCarrierConfig();
        return carrierConfig != null && carrierConfig.getBoolean("carrier_allow_deflect_ims_call_bool") && isValidRingingCall();
    }

    private boolean isCallTransferSupported() {
        PersistableBundle carrierConfig = getCarrierConfig();
        if (carrierConfig != null) {
            return carrierConfig.getBoolean("carrier_allow_transfer_ims_call_bool");
        }
        return false;
    }

    private boolean canTransfer(TelephonyConnection telephonyConnection) {
        com.android.internal.telephony.Connection originalConnection = telephonyConnection.getOriginalConnection();
        return (originalConnection == null || originalConnection.isMultiparty() || (telephonyConnection.getState() != 4 && telephonyConnection.getState() != 5)) ? false : true;
    }

    private boolean canTransferToNumber() {
        if (isCallTransferSupported()) {
            return canTransfer(this);
        }
        return false;
    }

    private boolean canConsultativeTransfer() {
        if (!isCallTransferSupported() || !canTransfer(this) || getTelephonyConnectionService() == null) {
            return false;
        }
        for (android.telecom.Connection connection : getTelephonyConnectionService().getAllConnections()) {
            if (connection != this && (connection instanceof TelephonyConnection) && canTransfer((TelephonyConnection) connection)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesDeviceRespectHoldCarrierConfig() {
        Phone phone = getPhone();
        if (phone == null) {
            return true;
        }
        return phone.getContext().getResources().getBoolean(android.R.bool.config_batterySaver_full_enableDataSaver);
    }

    protected boolean shouldTreatAsEmergencyCall() {
        return this.mTreatAsEmergencyCall;
    }

    void clearOriginalConnection() {
        if (this.mOriginalConnection != null) {
            if (getPhone() != null) {
                getPhone().unregisterForPreciseCallStateChanged(this.mHandler);
                getPhone().unregisterForRingbackTone(this.mHandler);
                getPhone().unregisterForHandoverStateChanged(this.mHandler);
                getPhone().unregisterForRedialConnectionChanged(this.mHandler);
                getPhone().unregisterForDisconnect(this.mHandler);
                getPhone().unregisterForSuppServiceNotification(this.mHandler);
                getPhone().unregisterForOnHoldTone(this.mHandler);
                getPhone().unregisterForInCallVoicePrivacyOn(this.mHandler);
                getPhone().unregisterForInCallVoicePrivacyOff(this.mHandler);
            }
            this.mOriginalConnection.removePostDialListener(this.mPostDialListener);
            this.mOriginalConnection.removeListener(this.mOriginalConnectionListener);
            this.mOriginalConnection = null;
        }
    }

    protected void hangup(int i) {
        if (this.mOriginalConnection != null) {
            this.mHangupDisconnectCause = i;
            try {
                if (isValidRingingCall()) {
                    Call call = getCall();
                    if (call != null) {
                        call.hangup();
                    } else {
                        Log.w(this, "Attempting to hangup a connection without backing call.", new Object[0]);
                    }
                } else {
                    this.mOriginalConnection.hangup();
                }
                return;
            } catch (CallStateException e) {
                Log.e(this, e, "Call to Connection.hangup failed with exception", new Object[0]);
                return;
            }
        }
        if (getState() == 6) {
            Log.i(this, "hangup called on an already disconnected call!", new Object[0]);
            close();
        } else {
            setTelephonyConnectionDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(3, "Local Disconnect before connection established."));
            close();
        }
    }

    protected void reject(int i) {
        if (this.mOriginalConnection != null) {
            this.mHangupDisconnectCause = 16;
            try {
                if (isValidRingingCall()) {
                    Call call = getCall();
                    if (call != null) {
                        call.hangup(i);
                    } else {
                        Log.w(this, "Attempting to hangup a connection without backing call.", new Object[0]);
                    }
                } else {
                    this.mOriginalConnection.hangup();
                }
                return;
            } catch (CallStateException e) {
                Log.e(this, e, "Call to Connection.hangup failed with exception", new Object[0]);
                return;
            }
        }
        if (getState() == 6) {
            Log.i(this, "hangup called on an already disconnected call!", new Object[0]);
            close();
        } else {
            setTelephonyConnectionDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(3, "Local Disconnect before connection established."));
            close();
        }
    }

    com.android.internal.telephony.Connection getOriginalConnection() {
        return this.mOriginalConnection;
    }

    protected Call getCall() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null) {
            return connection.getCall();
        }
        return null;
    }

    Phone getPhone() {
        Call call = getCall();
        if (call != null) {
            return call.getPhone();
        }
        return null;
    }

    private boolean hasMultipleTopLevelCalls() {
        int i;
        Phone phone = getPhone();
        if (phone != null) {
            i = !phone.getRingingCall().isIdle() ? 1 : 0;
            if (!phone.getForegroundCall().isIdle()) {
                i++;
            }
            if (!phone.getBackgroundCall().isIdle()) {
                i++;
            }
        } else {
            i = 0;
        }
        return i > 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.android.internal.telephony.Connection getForegroundConnection() {
        if (getPhone() != null) {
            return getPhone().getForegroundCall().getEarliestConnection();
        }
        return null;
    }

    public List<ConferenceParticipant> getConferenceParticipants() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection == null) {
            Log.w(this, "Null mOriginalConnection, cannot get conf participants.", new Object[0]);
            return null;
        }
        return connection.getConferenceParticipants();
    }

    private boolean isValidRingingCall() {
        if (getPhone() == null) {
            Log.v(this, "isValidRingingCall, phone is null", new Object[0]);
            return false;
        }
        Call ringingCall = getPhone().getRingingCall();
        if (!ringingCall.getState().isRinging()) {
            Log.v(this, "isValidRingingCall, ringing call is not in ringing state", new Object[0]);
            return false;
        }
        if (ringingCall.getEarliestConnection() != this.mOriginalConnection) {
            Log.v(this, "isValidRingingCall, ringing call connection does not match", new Object[0]);
            return false;
        }
        Log.v(this, "isValidRingingCall, returning true", new Object[0]);
        return true;
    }

    protected void updateExtras(Bundle bundle) {
        if (this.mOriginalConnection != null) {
            if (bundle != null) {
                if (!areBundlesEqual(this.mOriginalConnectionExtras, bundle)) {
                    if (Log.DEBUG) {
                        Log.d(this, "Updating extras:", new Object[0]);
                        for (String str : bundle.keySet()) {
                            Object obj = bundle.get(str);
                            if (obj instanceof String) {
                                Log.d(this, "updateExtras Key=" + Rlog.pii("TelephonyConnection", str) + " value=" + Rlog.pii("TelephonyConnection", obj), new Object[0]);
                            }
                        }
                    }
                    this.mOriginalConnectionExtras.clear();
                    this.mOriginalConnectionExtras.putAll(bundle);
                    for (String str2 : this.mOriginalConnectionExtras.keySet()) {
                        if (sExtrasMap.containsKey(str2)) {
                            this.mOriginalConnectionExtras.putString(sExtrasMap.get(str2), bundle.getString(str2));
                            this.mOriginalConnectionExtras.remove(str2);
                        }
                    }
                    putTelephonyExtras(this.mOriginalConnectionExtras);
                    if (this.mOriginalConnectionExtras.containsKey("conference_avail")) {
                        updateConnectionCapabilities();
                        return;
                    }
                    return;
                }
                Log.d(this, "Extras update not required", new Object[0]);
                return;
            }
            Log.d(this, "updateExtras extras: " + Rlog.pii("TelephonyConnection", bundle), new Object[0]);
        }
    }

    private static boolean areBundlesEqual(Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return bundle == bundle2;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (str != null && !Objects.equals(bundle.get(str), bundle2.get(str))) {
                return false;
            }
        }
        return true;
    }

    void setStateOverride(Call.State state) {
        this.mIsStateOverridden = true;
        this.mConnectionOverriddenState = state;
        this.mOriginalConnectionState = this.mOriginalConnection.getState();
        updateStateInternal();
    }

    void resetStateOverride() {
        this.mIsStateOverridden = false;
        updateStateInternal();
    }

    void updateStateInternal() {
        Call.State state;
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection == null) {
        }
        if (this.mIsStateOverridden && this.mOriginalConnectionState == connection.getState()) {
            state = this.mConnectionOverriddenState;
        } else {
            state = this.mOriginalConnection.getState();
        }
        int disconnectCause = this.mOriginalConnection.getDisconnectCause();
        Log.v(this, "Update state from %s to %s for %s", this.mConnectionState, state, getTelecomCallId());
        if (this.mConnectionState != state) {
            this.mConnectionState = state;
            switch (AnonymousClass4.$SwitchMap$com$android$internal$telephony$Call$State[state.ordinal()]) {
                case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                    setActiveInternal();
                    break;
                case 3:
                    setTelephonyConnectionOnHold();
                    break;
                case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                case 5:
                    com.android.internal.telephony.Connection connection2 = this.mOriginalConnection;
                    if (connection2 != null && connection2.isPulledCall()) {
                        setTelephonyConnectionPulling();
                    } else {
                        setTelephonyConnectionDialing();
                    }
                    break;
                case 6:
                case 7:
                    setTelephonyConnectionRinging();
                    break;
                case 8:
                    if (shouldTreatAsEmergencyCall() && (disconnectCause == 63 || disconnectCause == 64)) {
                        fireOnOriginalConnectionRetryDial(disconnectCause == 64);
                    } else {
                        int preciseDisconnectCause = this.mShowPreciseFailedCause ? this.mOriginalConnection.getPreciseDisconnectCause() : -1;
                        int disconnectCause2 = this.mOriginalConnection.getDisconnectCause();
                        int i = this.mHangupDisconnectCause;
                        if (i != -1 && i != disconnectCause2) {
                            Log.i("TelephonyConnection", "setDisconnected: override cause: " + disconnectCause2 + " -> " + this.mHangupDisconnectCause, new Object[0]);
                            disconnectCause2 = this.mHangupDisconnectCause;
                        }
                        setTelephonyConnectionDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(disconnectCause2, preciseDisconnectCause, this.mOriginalConnection.getVendorDisconnectCause(), getPhone().getPhoneId()));
                        close();
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.android.services.telephony.TelephonyConnection$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
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

    void updateState() {
        if (this.mOriginalConnection == null) {
            return;
        }
        updateStateInternal();
        updateStatusHints();
        updateConnectionCapabilities();
        updateConnectionProperties();
        updateAddress();
        updateMultiparty();
        refreshDisableAddCall();
        refreshCodecType();
    }

    private void updateMultiparty() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection == null || this.mIsMultiParty == connection.isMultiparty()) {
            return;
        }
        boolean zIsMultiparty = this.mOriginalConnection.isMultiparty();
        this.mIsMultiParty = zIsMultiparty;
        if (zIsMultiparty) {
            notifyConferenceStarted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConferenceMergeFailed() {
        this.mHandler.obtainMessage(6).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMultipartyStateChange(boolean z) {
        Object[] objArr = new Object[1];
        objArr[0] = z ? "Y" : "N";
        Log.i(this, "Update multiparty state to %s", objArr);
        this.mHandler.obtainMessage(5, Boolean.valueOf(z)).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActiveInternal() {
        if (getState() == 4) {
            Log.w(this, "Should not be called if this is already ACTIVE", new Object[0]);
            return;
        }
        if (getTelephonyConnectionService() != null) {
            for (android.telecom.Connection connection : getTelephonyConnectionService().getAllConnections()) {
                if (connection != this && (connection instanceof TelephonyConnection)) {
                    TelephonyConnection telephonyConnection = (TelephonyConnection) connection;
                    if (telephonyConnection.getState() == 4) {
                        telephonyConnection.updateState();
                    }
                }
            }
        }
        setTelephonyConnectionActive();
    }

    public void close() {
        Log.v(this, "close", new Object[0]);
        clearOriginalConnection();
        destroy();
        TelephonyConnectionService telephonyConnectionService = this.mTelephonyConnectionService;
        if (telephonyConnectionService != null) {
            removeTelephonyConnectionListener(telephonyConnectionService.getTelephonyConnectionListener());
        }
        notifyDestroyed();
    }

    private boolean isVideoCapable() {
        int i = this.mOriginalConnectionCapabilities;
        return (i & 4) == 4 && (i & 8) == 8;
    }

    private boolean isExternalConnection() {
        return (this.mOriginalConnectionCapabilities & 16) == 16;
    }

    private boolean isRtt() {
        com.android.internal.telephony.Connection connection = this.mOriginalConnection;
        if (connection != null && connection.getPhoneType() == 5) {
            ImsPhoneConnection imsPhoneConnection = this.mOriginalConnection;
            if ((imsPhoneConnection instanceof ImsPhoneConnection) && imsPhoneConnection.isRttEnabledForCall()) {
                return true;
            }
        }
        return false;
    }

    private boolean isPullable() {
        int i = this.mOriginalConnectionCapabilities;
        return (i & 16) == 16 && (i & 32) == 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCdmaVoicePrivacy(boolean z) {
        if (this.mIsCdmaVoicePrivacyEnabled != z) {
            this.mIsCdmaVoicePrivacyEnabled = z;
            updateConnectionProperties();
        }
    }

    private int applyConferenceTerminationCapabilities(int i) {
        return !this.mWasImsConnection ? i | 8192 | 4096 : i;
    }

    public void setOriginalConnectionCapabilities(int i) {
        this.mOriginalConnectionCapabilities = i;
        updateConnectionCapabilities();
        updateConnectionProperties();
    }

    public int applyOriginalConnectionCapabilities(int i) {
        boolean z = false;
        int iChangeBitmask = changeBitmask(changeBitmask(i, 8388608, !((this.mOriginalConnectionCapabilities & 3) == 3)), 3072, (this.mOriginalConnectionCapabilities & 8) == 8);
        if ((this.mOriginalConnectionCapabilities & 4) == 4 && !this.mIsTtyEnabled) {
            z = true;
        }
        return changeBitmask(iChangeBitmask, 768, z);
    }

    boolean isWifi() {
        return getCallRadioTech() == 18;
    }

    public void setIsNetworkIdentifiedEmergencyCall(boolean z) {
        Log.d(this, "setIsNetworkIdentifiedEmergencyCall; callId=%s, isNetworkIdentifiedEmergencyCall=%b", getTelecomCallId(), Boolean.valueOf(z));
        this.mIsNetworkIdentifiedEmergencyCall = z;
        updateConnectionProperties();
    }

    public boolean isNetworkIdentifiedEmergencyCall() {
        return this.mIsNetworkIdentifiedEmergencyCall;
    }

    public boolean isOutgoingCall() {
        return getCallDirection() == 1;
    }

    public void setAudioQuality(int i) {
        this.mHasHighDefAudio = i == 2;
        updateConnectionProperties();
    }

    void resetStateForConference() {
        if (getState() == 5) {
            resetStateOverride();
        }
    }

    boolean setHoldingForConference() {
        if (getState() != 4) {
            return false;
        }
        setStateOverride(Call.State.HOLDING);
        return true;
    }

    public void setRttTextStream(Connection.RttTextStream rttTextStream) {
        this.mRttTextStream = rttTextStream;
    }

    public Connection.RttTextStream getRttTextStream() {
        return this.mRttTextStream;
    }

    public void setVideoPauseSupported(boolean z) {
        this.mIsVideoPauseSupported = z;
    }

    public boolean getVideoPauseSupported() {
        return this.mIsVideoPauseSupported;
    }

    public void setConferenceSupported(boolean z) {
        this.mIsConferenceSupported = z;
    }

    public boolean isConferenceSupported() {
        return this.mIsConferenceSupported;
    }

    public void setManageImsConferenceCallSupported(boolean z) {
        this.mIsManageImsConferenceCallSupported = z;
    }

    public boolean isManageImsConferenceCallSupported() {
        return this.mIsManageImsConferenceCallSupported;
    }

    public void setShowPreciseFailedCause(boolean z) {
        this.mShowPreciseFailedCause = z;
    }

    public void setTtyEnabled(boolean z) {
        this.mIsTtyEnabled = z;
        updateConnectionCapabilities();
    }

    protected boolean isImsConnection() {
        com.android.internal.telephony.Connection originalConnection = getOriginalConnection();
        return originalConnection != null && originalConnection.getPhoneType() == 5;
    }

    protected boolean isGsmCdmaConnection() {
        Phone phone = getPhone();
        if (phone == null) {
            return false;
        }
        int phoneType = phone.getPhoneType();
        return phoneType == 1 || phoneType == 2;
    }

    public boolean wasImsConnection() {
        return this.mWasImsConnection;
    }

    void setIsUsingAssistedDialing(Boolean bool) {
        this.mIsUsingAssistedDialing = bool.booleanValue();
        updateConnectionProperties();
    }

    private static Uri getAddressFromNumber(String str) {
        if (str == null) {
            str = "";
        }
        return Uri.fromParts("tel", str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatusHints() {
        if (isWifi() && getPhone() != null) {
            setTelephonyStatusHints(new StatusHints(getResourceString(isValidRingingCall() ? R.string.status_hint_label_incoming_wifi_call : R.string.status_hint_label_wifi_call), Icon.createWithResource(getPhone().getContext(), R.drawable.ic_signal_wifi_4_bar_24dp), null));
        } else {
            setTelephonyStatusHints(null);
        }
    }

    public final TelephonyConnection addTelephonyConnectionListener(TelephonyConnectionListener telephonyConnectionListener) {
        this.mTelephonyListeners.add(telephonyConnectionListener);
        if (this.mOriginalConnection != null) {
            fireOnOriginalConnectionConfigured();
        }
        return this;
    }

    public final TelephonyConnection removeTelephonyConnectionListener(TelephonyConnectionListener telephonyConnectionListener) {
        if (telephonyConnectionListener != null) {
            this.mTelephonyListeners.remove(telephonyConnectionListener);
        }
        return this;
    }

    @Override // com.android.services.telephony.Holdable
    public void setHoldable(boolean z) {
        this.mIsHoldable = z;
        updateConnectionCapabilities();
    }

    @Override // com.android.services.telephony.Holdable
    public boolean isChildHoldable() {
        return getConference() != null;
    }

    public boolean isHoldable() {
        return this.mIsHoldable;
    }

    private final void fireOnOriginalConnectionConfigured() {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onOriginalConnectionConfigured(this);
        }
    }

    private final void fireOnOriginalConnectionRetryDial(boolean z) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onOriginalConnectionRetry(this, z);
        }
    }

    protected void handleExitedEcmMode() {
        updateConnectionProperties();
    }

    @VisibleForTesting
    void refreshConferenceSupported() {
        PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandle;
        boolean zIsVideo = VideoProfile.isVideo(getVideoState());
        Phone phone = getPhone();
        boolean z = false;
        if (phone == null) {
            Log.w(this, "refreshConferenceSupported = false; phone is null", new Object[0]);
            if (isConferenceSupported()) {
                setConferenceSupported(false);
                notifyConferenceSupportedChanged(false);
                return;
            }
            return;
        }
        boolean z2 = phone.getPhoneType() == 5;
        boolean zIsWfcEnabled = z2 ? ImsUtil.isWfcEnabled(phone.getContext(), phone.getPhoneId()) : false;
        boolean z3 = getCarrierConfig().getBoolean("allow_merging_rtt_calls_bool");
        if (z2) {
            phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(phone.getDefaultPhone());
        } else {
            phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(phone);
        }
        TelecomAccountRegistry telecomAccountRegistry = TelecomAccountRegistry.getInstance(getPhone().getContext());
        boolean zIsMergeCallSupported = telecomAccountRegistry.isMergeCallSupported(phoneAccountHandleMakePstnPhoneAccountHandle);
        boolean zIsMergeImsCallSupported = telecomAccountRegistry.isMergeImsCallSupported(phoneAccountHandleMakePstnPhoneAccountHandle);
        this.mIsCarrierVideoConferencingSupported = telecomAccountRegistry.isVideoConferencingSupported(phoneAccountHandleMakePstnPhoneAccountHandle);
        boolean zIsMergeOfWifiCallsAllowedWhenVoWifiOff = telecomAccountRegistry.isMergeOfWifiCallsAllowedWhenVoWifiOff(phoneAccountHandleMakePstnPhoneAccountHandle);
        Log.v(this, "refreshConferenceSupported : isConfSupp=%b, isImsConfSupp=%b, isVidConfSupp=%b, isMergeOfWifiAllowed=%b, isWifi=%b, isVoWifiEnabled=%b", Boolean.valueOf(zIsMergeCallSupported), Boolean.valueOf(zIsMergeImsCallSupported), Boolean.valueOf(this.mIsCarrierVideoConferencingSupported), Boolean.valueOf(zIsMergeOfWifiCallsAllowedWhenVoWifiOff), Boolean.valueOf(isWifi()), Boolean.valueOf(zIsWfcEnabled));
        if (this.mTreatAsEmergencyCall) {
            Log.d(this, "refreshConferenceSupported = false; emergency call", new Object[0]);
        } else if (isRtt() && !z3) {
            Log.d(this, "refreshConferenceSupported = false; rtt call", new Object[0]);
        } else if (!zIsMergeCallSupported || (z2 && !zIsMergeImsCallSupported)) {
            Log.d(this, "refreshConferenceSupported = false; carrier doesn't support conf.", new Object[0]);
        } else if (zIsVideo && !this.mIsCarrierVideoConferencingSupported) {
            Log.d(this, "refreshConferenceSupported = false; video conf not supported.", new Object[0]);
        } else if (!zIsMergeOfWifiCallsAllowedWhenVoWifiOff && isWifi() && !zIsWfcEnabled) {
            Log.d(this, "refreshConferenceSupported = false; can't merge wifi calls when voWifi off.", new Object[0]);
        } else {
            Log.d(this, "refreshConferenceSupported = true.", new Object[0]);
            z = true;
        }
        if (z != isConferenceSupported()) {
            setConferenceSupported(z);
            notifyConferenceSupportedChanged(z);
        }
    }

    private static Map<String, String> createExtrasMap() {
        HashMap map = new HashMap();
        map.put("ChildNum", "android.telecom.extra.CHILD_ADDRESS");
        map.put("DisplayText", "android.telecom.extra.CALL_SUBJECT");
        map.put("android.telephony.ims.extra.ADDITIONAL_SIP_INVITE_FIELDS", "android.telecom.extra.SIP_INVITE");
        return Collections.unmodifiableMap(map);
    }

    private boolean isShowingOriginalDialString() {
        PersistableBundle carrierConfig;
        Phone phone = getPhone();
        if (phone == null || phone.getPhoneType() != 2 || this.mOriginalConnection.isIncoming() || (carrierConfig = getCarrierConfig()) == null) {
            return false;
        }
        boolean z = carrierConfig.getBoolean("config_show_orig_dial_string_for_cdma");
        Log.d(this, "showOrigDialString: " + z, new Object[0]);
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TelephonyConnection objId:");
        sb.append(System.identityHashCode(this));
        sb.append(" telecomCallID:");
        sb.append(getTelecomCallId());
        sb.append(" type:");
        if (isImsConnection()) {
            sb.append("ims");
        } else if (this instanceof GsmConnection) {
            sb.append("gsm");
        } else if (this instanceof CdmaConnection) {
            sb.append("cdma");
        }
        sb.append(" state:");
        sb.append(android.telecom.Connection.stateToString(getState()));
        sb.append(" capabilities:");
        sb.append(android.telecom.Connection.capabilitiesToString(getConnectionCapabilities()));
        sb.append(" properties:");
        sb.append(android.telecom.Connection.propertiesToString(getConnectionProperties()));
        sb.append(" address:");
        sb.append(Rlog.pii("TelephonyConnection", getAddress()));
        sb.append(" originalConnection:");
        sb.append(this.mOriginalConnection);
        sb.append(" partOfConf:");
        if (getConference() == null) {
            sb.append("N");
        } else {
            sb.append("Y");
        }
        sb.append(" confSupported:");
        sb.append(this.mIsConferenceSupported ? "Y" : "N");
        sb.append(" isAdhocConf:");
        sb.append(isAdhocConferenceCall() ? "Y" : "N");
        sb.append("]");
        return sb.toString();
    }

    public final void setTelephonyConnectionService(TelephonyConnectionService telephonyConnectionService) {
        this.mTelephonyConnectionService = telephonyConnectionService;
    }

    public final TelephonyConnectionService getTelephonyConnectionService() {
        return this.mTelephonyConnectionService;
    }

    public void setTelephonyConnectionActive() {
        setActive();
        notifyStateChanged(getState());
    }

    public void setTelephonyConnectionRinging() {
        setRinging();
        notifyStateChanged(getState());
    }

    public void setTelephonyConnectionInitializing() {
        setInitializing();
        notifyStateChanged(getState());
    }

    public void setTelephonyConnectionDialing() {
        setDialing();
        notifyStateChanged(getState());
    }

    public void setTelephonyConnectionPulling() {
        setPulling();
        notifyStateChanged(getState());
    }

    public void setTelephonyConnectionOnHold() {
        setOnHold();
        notifyStateChanged(getState());
    }

    public void setTelephonyConnectionDisconnected(android.telecom.DisconnectCause disconnectCause) {
        setDisconnected(disconnectCause);
        notifyDisconnected(disconnectCause);
        notifyStateChanged(getState());
    }

    public void sendTelephonyConnectionEvent(String str, Bundle bundle) {
        sendConnectionEvent(str, bundle);
        notifyTelephonyConnectionEvent(str, bundle);
    }

    public void putTelephonyExtras(Bundle bundle) {
        putExtras(bundle);
        notifyPutExtras(bundle);
    }

    public void removeTelephonyExtras(List<String> list) {
        removeExtras(list);
        notifyRemoveExtras(list);
    }

    public void setTelephonyVideoState(int i) {
        setVideoState(i);
        notifyVideoStateChanged(i);
    }

    public void setTelephonyVideoProvider(Connection.VideoProvider videoProvider) {
        setVideoProvider(videoProvider);
        notifyVideoProviderChanged(videoProvider);
    }

    public void setTelephonyStatusHints(StatusHints statusHints) {
        setStatusHints(statusHints);
        notifyStatusHintsChanged(statusHints);
    }

    public final void setCallRadioTech(int i) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putInt("android.telecom.extra.CALL_NETWORK_TYPE", ServiceState.rilRadioTechnologyToNetworkType(i));
        putExtras(extras);
        if (getConference() != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.telecom.extra.CALL_NETWORK_TYPE", ServiceState.rilRadioTechnologyToNetworkType(i));
            getConference().putExtras(bundle);
        }
    }

    public final int getCallRadioTech() {
        Bundle extras = getExtras();
        return ServiceState.networkTypeToRilRadioTechnology(extras != null ? extras.getInt("android.telecom.extra.CALL_NETWORK_TYPE", 0) : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConferenceParticipants(List<ConferenceParticipant> list) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onConferenceParticipantsChanged(this, list);
        }
    }

    protected void notifyConferenceStarted() {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onConferenceStarted();
        }
    }

    private void notifyConferenceSupportedChanged(boolean z) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onConferenceSupportedChanged(this, z);
        }
    }

    private void notifyConnectionCapabilitiesChanged(int i) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onConnectionCapabilitiesChanged(this, i);
        }
    }

    private void notifyConnectionPropertiesChanged(int i) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onConnectionPropertiesChanged(this, i);
        }
    }

    private void notifyDestroyed() {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onDestroyed(this);
        }
    }

    private void notifyDisconnected(android.telecom.DisconnectCause disconnectCause) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onDisconnected(this, disconnectCause);
        }
    }

    private void notifyStateChanged(int i) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(this, i);
        }
    }

    private void notifyTelephonyConnectionEvent(String str, Bundle bundle) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onConnectionEvent(this, str, bundle);
        }
    }

    private void notifyPutExtras(Bundle bundle) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onExtrasChanged(this, bundle);
        }
    }

    private void notifyRemoveExtras(List<String> list) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onExtrasRemoved(this, list);
        }
    }

    private void notifyVideoStateChanged(int i) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoStateChanged(this, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRingbackRequested(boolean z) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onRingbackRequested(this, z);
        }
    }

    private void notifyVideoProviderChanged(Connection.VideoProvider videoProvider) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoProviderChanged(this, videoProvider);
        }
    }

    private void notifyStatusHintsChanged(StatusHints statusHints) {
        Iterator<TelephonyConnectionListener> it = this.mTelephonyListeners.iterator();
        while (it.hasNext()) {
            it.next().onStatusHintsChanged(this, statusHints);
        }
    }
}
