package com.android.services.telephony;

import android.net.Uri;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.internal.telephony.Connection;
import com.android.internal.telephony.GsmCdmaPhone;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.cdma.CdmaCallWaitingNotification;
import com.android.internal.telephony.imsphone.ImsExternalConnection;
import com.android.internal.telephony.imsphone.ImsPhoneConnection;
import com.android.phone.NumberVerificationManager;
import com.android.phone.PhoneUtils;
import com.android.telephony.Rlog;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
final class PstnIncomingCallNotifier {
    private final Handler mHandler = new Handler() { // from class: com.android.services.telephony.PstnIncomingCallNotifier.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    PstnIncomingCallNotifier.this.handleNewRingingConnection((AsyncResult) message.obj);
                    break;
                case 101:
                    PstnIncomingCallNotifier.this.handleCdmaCallWaiting((AsyncResult) message.obj);
                    break;
                case 102:
                    PstnIncomingCallNotifier.this.handleNewUnknownConnection((AsyncResult) message.obj);
                    break;
            }
        }
    };
    private final Phone mPhone;

    PstnIncomingCallNotifier(Phone phone) {
        if (phone == null) {
            throw null;
        }
        this.mPhone = phone;
        registerForNotifications();
    }

    void teardown() {
        unregisterForNotifications();
    }

    private void registerForNotifications() {
        Phone phone = this.mPhone;
        if (phone != null) {
            Log.i(this, "Registering: %s", phone);
            this.mPhone.registerForNewRingingConnection(this.mHandler, 100, (Object) null);
            this.mPhone.registerForCallWaiting(this.mHandler, 101, (Object) null);
            this.mPhone.registerForUnknownConnection(this.mHandler, 102, (Object) null);
        }
    }

    private void unregisterForNotifications() {
        Phone phone = this.mPhone;
        if (phone != null) {
            Log.i(this, "Unregistering: %s", phone);
            this.mPhone.unregisterForNewRingingConnection(this.mHandler);
            this.mPhone.unregisterForCallWaiting(this.mHandler);
            this.mPhone.unregisterForUnknownConnection(this.mHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewRingingConnection(AsyncResult asyncResult) {
        Log.d(this, "handleNewRingingConnection", new Object[0]);
        final Connection connection = (Connection) asyncResult.result;
        if (connection != null) {
            Call call = connection.getCall();
            if (connection.getAddress() != null && NumberVerificationManager.getInstance().checkIncomingCall(connection.getAddress())) {
                this.mHandler.postDelayed(new Runnable() { // from class: com.android.services.telephony.-$$Lambda$PstnIncomingCallNotifier$6ua5ngMDY3KdEh_6zihNSsgAbj0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleNewRingingConnection$0$PstnIncomingCallNotifier(connection);
                    }
                }, 10000L);
            } else {
                if (call == null || !call.getState().isRinging()) {
                    return;
                }
                sendIncomingCallIntent(connection);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$handleNewRingingConnection$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$handleNewRingingConnection$0$PstnIncomingCallNotifier(Connection connection) {
        try {
            connection.hangup();
        } catch (CallStateException unused) {
            Log.i(this, "Remote end hung up call verification call", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCdmaCallWaiting(AsyncResult asyncResult) {
        Connection latestConnection;
        Log.d(this, "handleCdmaCallWaiting", new Object[0]);
        CdmaCallWaitingNotification cdmaCallWaitingNotification = (CdmaCallWaitingNotification) asyncResult.result;
        Call ringingCall = this.mPhone.getRingingCall();
        if (ringingCall.getState() != Call.State.WAITING || (latestConnection = ringingCall.getLatestConnection()) == null) {
            return;
        }
        String address = latestConnection.getAddress();
        int numberPresentation = latestConnection.getNumberPresentation();
        if (numberPresentation != 1 && numberPresentation == cdmaCallWaitingNotification.numberPresentation) {
            Log.i(this, "handleCdmaCallWaiting: inform telecom of waiting call; presentation = %d", Integer.valueOf(numberPresentation));
            sendIncomingCallIntent(latestConnection);
        } else if (!TextUtils.isEmpty(address) && Objects.equals(address, cdmaCallWaitingNotification.number)) {
            Log.i(this, "handleCdmaCallWaiting: inform telecom of waiting call; number = %s", Rlog.pii("PstnIncomingCallNotifier", address));
            sendIncomingCallIntent(latestConnection);
        } else {
            Log.w(this, "handleCdmaCallWaiting: presentation or number do not match, not informing telecom of call: %s", cdmaCallWaitingNotification);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewUnknownConnection(AsyncResult asyncResult) {
        Log.i(this, "handleNewUnknownConnection", new Object[0]);
        Object obj = asyncResult.result;
        if (!(obj instanceof Connection)) {
            Log.w(this, "handleNewUnknownConnection called with non-Connection object", new Object[0]);
            return;
        }
        Connection connection = (Connection) obj;
        if (connection != null) {
            Call.State state = connection.getState();
            if (state == Call.State.DISCONNECTED || state == Call.State.IDLE) {
                Log.i(this, "Skipping new unknown connection because it is idle. " + connection, new Object[0]);
                return;
            }
            Call call = connection.getCall();
            if (call == null || !call.getState().isAlive()) {
                return;
            }
            addNewUnknownCall(connection);
        }
    }

    private void addNewUnknownCall(Connection connection) {
        Log.i(this, "addNewUnknownCall, connection is: %s", connection);
        if (!maybeSwapAnyWithUnknownConnection(connection)) {
            Log.i(this, "determined new connection is: %s", connection);
            Bundle bundle = new Bundle();
            if (connection.getNumberPresentation() == 1 && !TextUtils.isEmpty(connection.getAddress())) {
                bundle.putParcelable("android.telecom.extra.UNKNOWN_CALL_HANDLE", Uri.fromParts("tel", connection.getAddress(), null));
            }
            if (connection instanceof ImsExternalConnection) {
                bundle.putInt("android.telephony.ImsExternalCallTracker.extra.EXTERNAL_CALL_ID", ((ImsExternalConnection) connection).getCallId());
            }
            bundle.putLong("android.telecom.extra.CALL_CREATED_TIME_MILLIS", SystemClock.elapsedRealtime());
            PhoneAccountHandle phoneAccountHandleFindCorrectPhoneAccountHandle = findCorrectPhoneAccountHandle();
            if (phoneAccountHandleFindCorrectPhoneAccountHandle == null) {
                try {
                    connection.hangup();
                    return;
                } catch (CallStateException unused) {
                    return;
                }
            } else {
                ((TelecomManager) this.mPhone.getContext().getSystemService(TelecomManager.class)).addNewUnknownCall(phoneAccountHandleFindCorrectPhoneAccountHandle, bundle);
                return;
            }
        }
        Log.i(this, "swapped an old connection, new one is: %s", connection);
    }

    private void sendIncomingCallIntent(Connection connection) {
        Bundle bundle = new Bundle();
        if (connection.getNumberPresentation() == 1 && !TextUtils.isEmpty(connection.getAddress())) {
            bundle.putParcelable("android.telecom.extra.INCOMING_CALL_ADDRESS", Uri.fromParts("tel", connection.getAddress(), null));
        }
        bundle.putLong("android.telecom.extra.CALL_CREATED_TIME_MILLIS", SystemClock.elapsedRealtime());
        if (connection.getPhoneType() == 5) {
            ImsPhoneConnection imsPhoneConnection = (ImsPhoneConnection) connection;
            if (imsPhoneConnection.isRttEnabledForCall()) {
                bundle.putBoolean("android.telecom.extra.START_CALL_WITH_RTT", true);
            }
            if (imsPhoneConnection.isIncomingCallAutoRejected()) {
                bundle.putString("android.telecom.extra.CALL_DISCONNECT_MESSAGE", "Call dropped by lower layers");
            }
        }
        PhoneAccountHandle phoneAccountHandleFindCorrectPhoneAccountHandle = findCorrectPhoneAccountHandle();
        if (phoneAccountHandleFindCorrectPhoneAccountHandle == null) {
            try {
                connection.hangup();
            } catch (CallStateException unused) {
            }
            Log.wtf("PstnIncomingCallNotifier", "sendIncomingCallIntent: failed to add new call because no phone account could be found for the call", new Object[0]);
            return;
        }
        TelecomManager telecomManager = (TelecomManager) this.mPhone.getContext().getSystemService(TelecomManager.class);
        try {
            if (connection.isMultiparty()) {
                telecomManager.addNewIncomingConference(phoneAccountHandleFindCorrectPhoneAccountHandle, bundle);
            } else {
                telecomManager.addNewIncomingCall(phoneAccountHandleFindCorrectPhoneAccountHandle, bundle);
            }
        } catch (SecurityException unused2) {
            String str = (String) telecomManager.getCallCapablePhoneAccounts().stream().map(new Function() { // from class: com.android.services.telephony.-$$Lambda$PstnIncomingCallNotifier$YIcKdw8_pUVaPyVVkBDUZAgxuHg
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PstnIncomingCallNotifier.lambda$sendIncomingCallIntent$1((PhoneAccountHandle) obj);
                }
            }).collect(Collectors.joining(","));
            Log.wtf("PstnIncomingCallNotifier", "sendIncomingCallIntent: failed to add new call " + connection + " because the handle " + ("[" + phoneAccountHandleFindCorrectPhoneAccountHandle.getComponentName() + " " + Rlog.pii("PstnIncomingCallNotifier", phoneAccountHandleFindCorrectPhoneAccountHandle.getId()) + "]") + " is not in the list of registered handles " + str + " - call was rejected.", new Object[0]);
            try {
                connection.hangup();
            } catch (CallStateException unused3) {
            }
        }
    }

    static /* synthetic */ String lambda$sendIncomingCallIntent$1(PhoneAccountHandle phoneAccountHandle) {
        return "[" + phoneAccountHandle.getComponentName() + " " + Rlog.pii("PstnIncomingCallNotifier", phoneAccountHandle.getId()) + "]";
    }

    private PhoneAccountHandle findCorrectPhoneAccountHandle() {
        TelecomAccountRegistry telecomAccountRegistry = TelecomAccountRegistry.getInstance(null);
        PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(this.mPhone);
        if (telecomAccountRegistry.hasAccountEntryForPhoneAccount(phoneAccountHandleMakePstnPhoneAccountHandle)) {
            return phoneAccountHandleMakePstnPhoneAccountHandle;
        }
        PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandleWithPrefix = PhoneUtils.makePstnPhoneAccountHandleWithPrefix(this.mPhone, "", true);
        if (telecomAccountRegistry.hasAccountEntryForPhoneAccount(phoneAccountHandleMakePstnPhoneAccountHandleWithPrefix)) {
            Log.i(this, "Receiving MT call in ECM. Using Emergency PhoneAccount Instead.", new Object[0]);
            return phoneAccountHandleMakePstnPhoneAccountHandleWithPrefix;
        }
        Log.w(this, "PhoneAccount not found.", new Object[0]);
        return null;
    }

    private boolean maybeSwapAnyWithUnknownConnection(Connection connection) {
        TelecomAccountRegistry telecomAccountRegistry;
        TelephonyConnectionService telephonyConnectionService;
        if (connection.isIncoming() || (telecomAccountRegistry = TelecomAccountRegistry.getInstance(null)) == null || (telephonyConnectionService = telecomAccountRegistry.getTelephonyConnectionService()) == null) {
            return false;
        }
        for (android.telecom.Connection connection2 : telephonyConnectionService.getAllConnections()) {
            if ((connection2 instanceof TelephonyConnection) && maybeSwapWithUnknownConnection((TelephonyConnection) connection2, connection)) {
                return true;
            }
        }
        return false;
    }

    private boolean maybeSwapWithUnknownConnection(TelephonyConnection telephonyConnection, Connection connection) {
        Connection originalConnection = telephonyConnection.getOriginalConnection();
        if (originalConnection == null || originalConnection.isIncoming() || !Objects.equals(originalConnection.getAddress(), connection.getAddress())) {
            return false;
        }
        if ((connection instanceof ImsExternalConnection) && !(telephonyConnection.getOriginalConnection() instanceof ImsExternalConnection)) {
            Log.v(this, "maybeSwapWithUnknownConnection - not swapping regular connection with external connection.", new Object[0]);
            return false;
        }
        telephonyConnection.setOriginalConnection(connection);
        if (!(originalConnection instanceof ImsExternalConnection) && originalConnection.getCall() != null && originalConnection.getCall().getPhone() != null && (originalConnection.getCall().getPhone() instanceof GsmCdmaPhone)) {
            originalConnection.getCall().getPhone().getCallTracker().cleanupCalls();
            Log.i(this, "maybeSwapWithUnknownConnection - Invoking call tracker cleanup for connection: " + originalConnection, new Object[0]);
        }
        return true;
    }
}
