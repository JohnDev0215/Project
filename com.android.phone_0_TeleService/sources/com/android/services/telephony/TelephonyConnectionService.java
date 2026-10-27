package com.android.services.telephony;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.telecom.Conference;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.telecom.VideoProfile;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.RadioAccessFamily;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.emergency.EmergencyNumber;
import android.text.TextUtils;
import android.util.Pair;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.internal.telephony.GsmCdmaPhone;
import com.android.internal.telephony.IccCard;
import com.android.internal.telephony.IccCardConstants;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.telephony.PhoneSwitcher;
import com.android.internal.telephony.RIL;
import com.android.internal.telephony.SubscriptionController;
import com.android.internal.telephony.imsphone.ImsExternalCallTracker;
import com.android.internal.telephony.imsphone.ImsPhone;
import com.android.internal.telephony.imsphone.ImsPhoneConnection;
import com.android.phone.MMIDialogActivity;
import com.android.phone.PhoneUtils;
import com.android.phone.R;
import com.android.services.telephony.ImsConference;
import com.android.services.telephony.TelephonyConferenceBase;
import com.android.services.telephony.TelephonyConnection;
import com.android.services.telephony.TelephonyConnectionService;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class TelephonyConnectionService extends ConnectionService {
    private static final Pattern CDMA_ACTIVATION_CODE_REGEX_PATTERN = Pattern.compile("\\*228[0-9]{0,2}");
    private Handler mDdsSwitchHandler;

    @VisibleForTesting
    public Pair<WeakReference<TelephonyConnection>, Queue<Phone>> mEmergencyRetryCache;
    private EmergencyTonePlayer mEmergencyTonePlayer;
    private HandlerThread mHandlerThread;
    private HoldTracker mHoldTracker;
    private ImsConferenceController mImsConferenceController;
    private boolean mIsTtyEnabled;
    private RadioOnHelper mRadioOnHelper;
    private TelephonyManagerProxy mTelephonyManagerProxy;
    private final TelephonyConnectionServiceProxy mTelephonyConnectionServiceProxy = new TelephonyConnectionServiceProxy() { // from class: com.android.services.telephony.TelephonyConnectionService.1
        @Override // com.android.services.telephony.TelephonyConnectionServiceProxy
        public Collection<Connection> getAllConnections() {
            return TelephonyConnectionService.this.getAllConnections();
        }

        @Override // com.android.services.telephony.TelephonyConnectionServiceProxy
        public void addConference(TelephonyConference telephonyConference) {
            TelephonyConnectionService.this.addTelephonyConference(telephonyConference);
        }

        @Override // com.android.services.telephony.TelephonyConnectionServiceProxy
        public void addConference(ImsConference imsConference) {
            TelephonyConnectionService.this.addTelephonyConference(imsConference);
        }

        @Override // com.android.services.telephony.TelephonyConnectionServiceProxy
        public void addExistingConnection(PhoneAccountHandle phoneAccountHandle, Connection connection) {
            TelephonyConnectionService.this.addExistingConnection(phoneAccountHandle, connection);
        }

        @Override // com.android.services.telephony.TelephonyConnectionServiceProxy
        public void addExistingConnection(PhoneAccountHandle phoneAccountHandle, Connection connection, Conference conference) {
            TelephonyConnectionService.this.addExistingConnection(phoneAccountHandle, connection, conference);
        }

        @Override // com.android.services.telephony.TelephonyConnectionServiceProxy
        public void addConnectionToConferenceController(TelephonyConnection telephonyConnection) {
            TelephonyConnectionService.this.addConnectionToConferenceController(telephonyConnection);
        }
    };
    private final BroadcastReceiver mTtyBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.services.telephony.TelephonyConnectionService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.v(this, "onReceive, action: %s", action);
            if (action.equals("android.telecom.action.TTY_PREFERRED_MODE_CHANGED")) {
                boolean z = intent.getIntExtra("android.telecom.extra.TTY_PREFERRED_MODE", 0) != 0;
                if (z != TelephonyConnectionService.this.mIsTtyEnabled) {
                    TelephonyConnectionService.this.handleTtyModeChange(z);
                }
            }
        }
    };
    private final TelephonyConferenceController mTelephonyConferenceController = new TelephonyConferenceController(this.mTelephonyConnectionServiceProxy);
    private final CdmaConferenceController mCdmaConferenceController = new CdmaConferenceController(this);
    private DeviceState mDeviceState = new DeviceState();
    private SubscriptionManagerProxy mSubscriptionManagerProxy = new SubscriptionManagerProxy(this) { // from class: com.android.services.telephony.TelephonyConnectionService.3
        @Override // com.android.services.telephony.TelephonyConnectionService.SubscriptionManagerProxy
        public int getDefaultVoicePhoneId() {
            return SubscriptionManager.getDefaultVoicePhoneId();
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.SubscriptionManagerProxy
        public int getSimStateForSlotIdx(int i) {
            return SubscriptionManager.getSimStateForSlotIndex(i);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.SubscriptionManagerProxy
        public int getPhoneId(int i) {
            return SubscriptionManager.getPhoneId(i);
        }
    };
    private PhoneFactoryProxy mPhoneFactoryProxy = new PhoneFactoryProxy(this) { // from class: com.android.services.telephony.TelephonyConnectionService.4
        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneFactoryProxy
        public Phone getPhone(int i) {
            return PhoneFactory.getPhone(i);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneFactoryProxy
        public Phone getDefaultPhone() {
            return PhoneFactory.getDefaultPhone();
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneFactoryProxy
        public Phone[] getPhones() {
            return PhoneFactory.getPhones();
        }
    };
    private PhoneUtilsProxy mPhoneUtilsProxy = new PhoneUtilsProxy(this) { // from class: com.android.services.telephony.TelephonyConnectionService.5
        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneUtilsProxy
        public int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
            return PhoneUtils.getSubIdForPhoneAccountHandle(phoneAccountHandle);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneUtilsProxy
        public PhoneAccountHandle makePstnPhoneAccountHandle(Phone phone) {
            return PhoneUtils.makePstnPhoneAccountHandle(phone);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneUtilsProxy
        public PhoneAccountHandle makePstnPhoneAccountHandleWithPrefix(Phone phone, String str, boolean z) {
            return PhoneUtils.makePstnPhoneAccountHandleWithPrefix(phone, str, z);
        }
    };
    private PhoneNumberUtilsProxy mPhoneNumberUtilsProxy = new PhoneNumberUtilsProxy(this) { // from class: com.android.services.telephony.TelephonyConnectionService.6
        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneNumberUtilsProxy
        public String convertToEmergencyNumber(Context context, String str) {
            return PhoneNumberUtils.convertToEmergencyNumber(context, str);
        }
    };
    private PhoneSwitcherProxy mPhoneSwitcherProxy = new PhoneSwitcherProxy(this) { // from class: com.android.services.telephony.TelephonyConnectionService.7
        @Override // com.android.services.telephony.TelephonyConnectionService.PhoneSwitcherProxy
        public PhoneSwitcher getPhoneSwitcher() {
            return PhoneSwitcher.getInstance();
        }
    };
    private HandlerFactory mHandlerFactory = new HandlerFactory(this) { // from class: com.android.services.telephony.TelephonyConnectionService.8
        @Override // com.android.services.telephony.TelephonyConnectionService.HandlerFactory
        public HandlerThread createHandlerThread(String str) {
            return new HandlerThread(str);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.HandlerFactory
        public Handler createHandler(Looper looper) {
            return new Handler(looper);
        }
    };
    private DisconnectCauseFactory mDisconnectCauseFactory = new DisconnectCauseFactory(this) { // from class: com.android.services.telephony.TelephonyConnectionService.9
        @Override // com.android.services.telephony.TelephonyConnectionService.DisconnectCauseFactory
        public DisconnectCause toTelecomDisconnectCause(int i, String str) {
            return DisconnectCauseUtil.toTelecomDisconnectCause(i, str);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.DisconnectCauseFactory
        public DisconnectCause toTelecomDisconnectCause(int i, String str, int i2) {
            return DisconnectCauseUtil.toTelecomDisconnectCause(i, str, i2);
        }
    };
    private final TelephonyConnection.TelephonyConnectionListener mTelephonyConnectionListener = new TelephonyConnection.TelephonyConnectionListener() { // from class: com.android.services.telephony.TelephonyConnectionService.10
        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onOriginalConnectionConfigured(TelephonyConnection telephonyConnection) {
            TelephonyConnectionService.this.addConnectionToConferenceController(telephonyConnection);
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onOriginalConnectionRetry(TelephonyConnection telephonyConnection, boolean z) {
            TelephonyConnectionService.this.retryOutgoingOriginalConnection(telephonyConnection, z);
        }
    };
    private final TelephonyConferenceBase.TelephonyConferenceListener mTelephonyConferenceListener = new TelephonyConferenceBase.TelephonyConferenceListener() { // from class: com.android.services.telephony.TelephonyConnectionService.11
        @Override // com.android.services.telephony.TelephonyConferenceBase.TelephonyConferenceListener
        public void onConferenceMembershipChanged(Connection connection) {
            TelephonyConnectionService.this.mHoldTracker.updateHoldCapability(connection.getPhoneAccountHandle());
        }
    };

    @VisibleForTesting
    public interface DisconnectCauseFactory {
        DisconnectCause toTelecomDisconnectCause(int i, String str);

        DisconnectCause toTelecomDisconnectCause(int i, String str, int i2);
    }

    @VisibleForTesting
    public interface HandlerFactory {
        Handler createHandler(Looper looper);

        HandlerThread createHandlerThread(String str);
    }

    @VisibleForTesting
    public interface PhoneFactoryProxy {
        Phone getDefaultPhone();

        Phone getPhone(int i);

        Phone[] getPhones();
    }

    @VisibleForTesting
    public interface PhoneNumberUtilsProxy {
        String convertToEmergencyNumber(Context context, String str);
    }

    @VisibleForTesting
    public interface PhoneSwitcherProxy {
        PhoneSwitcher getPhoneSwitcher();
    }

    @VisibleForTesting
    public interface PhoneUtilsProxy {
        int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle);

        PhoneAccountHandle makePstnPhoneAccountHandle(Phone phone);

        PhoneAccountHandle makePstnPhoneAccountHandleWithPrefix(Phone phone, String str, boolean z);
    }

    @VisibleForTesting
    public interface SubscriptionManagerProxy {
        int getDefaultVoicePhoneId();

        int getPhoneId(int i);

        int getSimStateForSlotIdx(int i);
    }

    @VisibleForTesting
    public interface TelephonyManagerProxy {
        Map<Integer, List<EmergencyNumber>> getCurrentEmergencyNumberList();

        int getPhoneCount();

        boolean hasIccCard(int i);

        boolean isCurrentEmergencyNumber(String str);
    }

    static /* synthetic */ boolean lambda$onCreate$0() {
        return true;
    }

    static /* synthetic */ boolean lambda$prepareConference$1() {
        return true;
    }

    static /* synthetic */ Connection access$500(TelephonyConnectionService telephonyConnectionService, ConnectionRequest connectionRequest, Connection connection, Phone phone) {
        telephonyConnectionService.placeOutgoingConnection(connectionRequest, connection, phone);
        return connection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SlotStatus {
        public int capabilities;
        public int simState;
        public int slotId;
        public boolean isLocked = false;
        public boolean hasDialedEmergencyNumber = false;

        public SlotStatus(int i, int i2) {
            this.slotId = i;
            this.capabilities = i2;
        }
    }

    private class TelephonyManagerProxyImpl implements TelephonyManagerProxy {
        private final TelephonyManager mTelephonyManager;

        TelephonyManagerProxyImpl(TelephonyConnectionService telephonyConnectionService, Context context) {
            this.mTelephonyManager = new TelephonyManager(context);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.TelephonyManagerProxy
        public int getPhoneCount() {
            return this.mTelephonyManager.getPhoneCount();
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.TelephonyManagerProxy
        public boolean hasIccCard(int i) {
            return this.mTelephonyManager.hasIccCard(i);
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.TelephonyManagerProxy
        public boolean isCurrentEmergencyNumber(String str) {
            try {
                return this.mTelephonyManager.isEmergencyNumber(str);
            } catch (IllegalStateException unused) {
                return false;
            }
        }

        @Override // com.android.services.telephony.TelephonyConnectionService.TelephonyManagerProxy
        public Map<Integer, List<EmergencyNumber>> getCurrentEmergencyNumberList() {
            try {
                return this.mTelephonyManager.getEmergencyNumberList();
            } catch (IllegalStateException unused) {
                return new HashMap();
            }
        }
    }

    @VisibleForTesting
    public void setSubscriptionManagerProxy(SubscriptionManagerProxy subscriptionManagerProxy) {
        this.mSubscriptionManagerProxy = subscriptionManagerProxy;
    }

    @VisibleForTesting
    public void setTelephonyManagerProxy(TelephonyManagerProxy telephonyManagerProxy) {
        this.mTelephonyManagerProxy = telephonyManagerProxy;
    }

    @VisibleForTesting
    public void setPhoneFactoryProxy(PhoneFactoryProxy phoneFactoryProxy) {
        this.mPhoneFactoryProxy = phoneFactoryProxy;
    }

    @VisibleForTesting
    public void setDeviceState(DeviceState deviceState) {
        this.mDeviceState = deviceState;
    }

    @VisibleForTesting
    public void setRadioOnHelper(RadioOnHelper radioOnHelper) {
        this.mRadioOnHelper = radioOnHelper;
    }

    @VisibleForTesting
    public void setPhoneSwitcherProxy(PhoneSwitcherProxy phoneSwitcherProxy) {
        this.mPhoneSwitcherProxy = phoneSwitcherProxy;
    }

    @VisibleForTesting
    public void setPhoneNumberUtilsProxy(PhoneNumberUtilsProxy phoneNumberUtilsProxy) {
        this.mPhoneNumberUtilsProxy = phoneNumberUtilsProxy;
    }

    @VisibleForTesting
    public void setPhoneUtilsProxy(PhoneUtilsProxy phoneUtilsProxy) {
        this.mPhoneUtilsProxy = phoneUtilsProxy;
    }

    @VisibleForTesting
    public void setHandlerFactory(HandlerFactory handlerFactory) {
        this.mHandlerFactory = handlerFactory;
    }

    @VisibleForTesting
    public void setDisconnectCauseFactory(DisconnectCauseFactory disconnectCauseFactory) {
        this.mDisconnectCauseFactory = disconnectCauseFactory;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mImsConferenceController = new ImsConferenceController(TelecomAccountRegistry.getInstance(this), this.mTelephonyConnectionServiceProxy, new ImsConference.FeatureFlagProxy() { // from class: com.android.services.telephony.-$$Lambda$TelephonyConnectionService$T79LtWv4-UrAnre63SyTHVKa5K4
            @Override // com.android.services.telephony.ImsConference.FeatureFlagProxy
            public final boolean isUsingSinglePartyCallEmulation() {
                return TelephonyConnectionService.lambda$onCreate$0();
            }
        });
        setTelephonyManagerProxy(new TelephonyManagerProxyImpl(this, getApplicationContext()));
        new ComponentName(this, (Class<?>) TelephonyConnectionService.class);
        this.mEmergencyTonePlayer = new EmergencyTonePlayer(this);
        TelecomAccountRegistry.getInstance(this).setTelephonyConnectionService(this);
        this.mHoldTracker = new HoldTracker();
        this.mIsTtyEnabled = this.mDeviceState.isTtyModeEnabled(this);
        registerReceiver(this.mTtyBroadcastReceiver, new IntentFilter("android.telecom.action.TTY_PREFERRED_MODE_CHANGED"));
        HandlerThread handlerThreadCreateHandlerThread = this.mHandlerFactory.createHandlerThread("DdsSwitchHandlerThread");
        this.mHandlerThread = handlerThreadCreateHandlerThread;
        handlerThreadCreateHandlerThread.start();
        this.mDdsSwitchHandler = this.mHandlerFactory.createHandler(this.mHandlerThread.getLooper());
    }

    @Override // android.telecom.ConnectionService, android.app.Service
    public boolean onUnbind(Intent intent) {
        unregisterReceiver(this.mTtyBroadcastReceiver);
        this.mHandlerThread.quitSafely();
        return super.onUnbind(intent);
    }

    private Conference placeOutgoingConference(ConnectionRequest connectionRequest, Connection connection, Phone phone) {
        if (connection instanceof TelephonyConnection) {
            return placeOutgoingConference((TelephonyConnection) connection, phone, connectionRequest);
        }
        return null;
    }

    private Conference placeOutgoingConference(TelephonyConnection telephonyConnection, Phone phone, ConnectionRequest connectionRequest) {
        updatePhoneAccount(telephonyConnection, phone);
        try {
            com.android.internal.telephony.Connection connectionStartConference = phone.startConference(getParticipantsToDial(connectionRequest.getParticipants()), new ImsPhone.ImsDialArgs.Builder().setVideoState(connectionRequest.getVideoState()).setRttTextStream(telephonyConnection.getRttTextStream()).build());
            if (connectionStartConference == null) {
                Log.d(this, "placeOutgoingConference, phone.startConference returned null", new Object[0]);
                telephonyConnection.setDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(43, "conferenceHostConnection is null", phone.getPhoneId()));
                telephonyConnection.clearOriginalConnection();
                telephonyConnection.destroy();
            } else {
                telephonyConnection.setOriginalConnection(connectionStartConference);
            }
            return prepareConference(telephonyConnection, connectionRequest.getAccountHandle());
        } catch (CallStateException e) {
            Log.e(this, e, "placeOutgoingConference, phone.startConference exception: " + e, new Object[0]);
            handleCallStateException(e, telephonyConnection, phone);
            return null;
        }
    }

    Conference prepareConference(Connection connection, PhoneAccountHandle phoneAccountHandle) {
        if (!(connection instanceof TelephonyConnection)) {
            Log.w(this, "prepareConference returning NULL conference", new Object[0]);
            return null;
        }
        TelephonyConnection telephonyConnection = (TelephonyConnection) connection;
        ImsConference imsConference = new ImsConference(TelecomAccountRegistry.getInstance(this), this.mTelephonyConnectionServiceProxy, telephonyConnection, phoneAccountHandle, new ImsConference.FeatureFlagProxy() { // from class: com.android.services.telephony.-$$Lambda$TelephonyConnectionService$g0Or7d_mHOmfsDyXz6Gr6ZcRClo
            @Override // com.android.services.telephony.ImsConference.FeatureFlagProxy
            public final boolean isUsingSinglePartyCallEmulation() {
                return TelephonyConnectionService.lambda$prepareConference$1();
            }
        }, ImsConferenceController.getCarrierConfig(telephonyConnection.getPhone()));
        this.mImsConferenceController.addConference(imsConference);
        imsConference.setVideoState(telephonyConnection, telephonyConnection.getVideoState());
        imsConference.setVideoProvider(telephonyConnection, telephonyConnection.getVideoProvider());
        imsConference.setStatusHints(telephonyConnection.getStatusHints());
        imsConference.setAddress(telephonyConnection.getAddress(), telephonyConnection.getAddressPresentation());
        imsConference.setCallerDisplayName(telephonyConnection.getCallerDisplayName(), telephonyConnection.getCallerDisplayNamePresentation());
        imsConference.setParticipants(telephonyConnection.getParticipants());
        return imsConference;
    }

    @Override // android.telecom.ConnectionService
    public Conference onCreateIncomingConference(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        Log.i(this, "onCreateIncomingConference, request: " + connectionRequest, new Object[0]);
        Connection connectionOnCreateIncomingConnection = onCreateIncomingConnection(phoneAccountHandle, connectionRequest);
        Log.d(this, "onCreateIncomingConference, connection: %s", connectionOnCreateIncomingConnection);
        if (connectionOnCreateIncomingConnection == null) {
            Log.i(this, "onCreateIncomingConference, implementation returned null connection.", new Object[0]);
            return Conference.createFailedConference(new DisconnectCause(1, "IMPL_RETURNED_NULL_CONNECTION"), connectionRequest.getAccountHandle());
        }
        if (getPhoneForAccount(connectionRequest.getAccountHandle(), false, null) == null) {
            Log.d(this, "onCreateIncomingConference, phone is null", new Object[0]);
            return Conference.createFailedConference(DisconnectCauseUtil.toTelecomDisconnectCause(18, "Phone is null"), connectionRequest.getAccountHandle());
        }
        return prepareConference(connectionOnCreateIncomingConnection, connectionRequest.getAccountHandle());
    }

    @Override // android.telecom.ConnectionService
    public Conference onCreateOutgoingConference(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        Log.i(this, "onCreateOutgoingConference, request: " + connectionRequest, new Object[0]);
        Connection connectionOnCreateOutgoingConnection = onCreateOutgoingConnection(phoneAccountHandle, connectionRequest);
        Log.d(this, "onCreateOutgoingConference, connection: %s", connectionOnCreateOutgoingConnection);
        if (connectionOnCreateOutgoingConnection == null) {
            Log.i(this, "onCreateOutgoingConference, implementation returned null connection.", new Object[0]);
            return Conference.createFailedConference(new DisconnectCause(1, "IMPL_RETURNED_NULL_CONNECTION"), connectionRequest.getAccountHandle());
        }
        Phone phoneForAccount = getPhoneForAccount(connectionRequest.getAccountHandle(), false, null);
        if (phoneForAccount == null) {
            Log.d(this, "onCreateOutgoingConference, phone is null", new Object[0]);
            return Conference.createFailedConference(DisconnectCauseUtil.toTelecomDisconnectCause(18, "Phone is null"), connectionRequest.getAccountHandle());
        }
        return placeOutgoingConference(connectionRequest, connectionOnCreateOutgoingConnection, phoneForAccount);
    }

    private String[] getParticipantsToDial(List<Uri> list) {
        String[] strArr = new String[list.size()];
        Iterator<Uri> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next().getSchemeSpecificPart();
            i++;
        }
        return strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0159  */
    @Override // android.telecom.ConnectionService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.telecom.Connection onCreateOutgoingConnection(android.telecom.PhoneAccountHandle r18, final android.telecom.ConnectionRequest r19) {
        /*
            Method dump skipped, instruction units count: 555
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.services.telephony.TelephonyConnectionService.onCreateOutgoingConnection(android.telecom.PhoneAccountHandle, android.telecom.ConnectionRequest):android.telecom.Connection");
    }

    private Connection placeOutgoingConnection(ConnectionRequest connectionRequest, Connection connection, Phone phone) {
        if (connection instanceof TelephonyConnection) {
            if (connectionRequest.getExtras() != null && connectionRequest.getExtras().getBoolean("android.telecom.extra.USE_ASSISTED_DIALING", false)) {
                ((TelephonyConnection) connection).setIsUsingAssistedDialing(Boolean.TRUE);
            }
            placeOutgoingConnection((TelephonyConnection) connection, phone, connectionRequest);
        }
        return connection;
    }

    private boolean isEmergencyNumberTestNumber(String str) {
        String strStripSeparators = PhoneNumberUtils.stripSeparators(str);
        Map<Integer, List<EmergencyNumber>> currentEmergencyNumberList = this.mTelephonyManagerProxy.getCurrentEmergencyNumberList();
        Iterator<Integer> it = currentEmergencyNumberList.keySet().iterator();
        while (it.hasNext()) {
            for (EmergencyNumber emergencyNumber : currentEmergencyNumberList.get(it.next())) {
                if (strStripSeparators.equals(emergencyNumber.getNumber()) && emergencyNumber.isFromSources(32)) {
                    Log.i(this, "isEmergencyNumberTestNumber: " + strStripSeparators + " has been detected as a test emergency number.,", new Object[0]);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRadioPowerDownOnBluetooth() {
        return this.mDeviceState.isRadioPowerDownAllowedOnBluetooth(this) && this.mDeviceState.getCellOnStatus(this) == 1 && !isRadioOn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnComplete(boolean z, boolean z2, final Connection connection, final ConnectionRequest connectionRequest, final String str, final Uri uri, final int i, final Phone phone) {
        if (connection.getState() != 6) {
            if (!z) {
                Log.w(this, "onCreateOutgoingConnection, failed to turn on radio", new Object[0]);
                closeOrDestroyConnection(connection, this.mDisconnectCauseFactory.toTelecomDisconnectCause(17, "Failed to turn on radio."));
                return;
            } else if (!z2) {
                adjustAndPlaceOutgoingConnection(phone, connection, connectionRequest, str, uri, i, false);
                return;
            } else {
                this.mDdsSwitchHandler.post(new Runnable() { // from class: com.android.services.telephony.TelephonyConnectionService.14
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.i(this, "handleOnComplete - delayDialForDdsSwitch result = " + TelephonyConnectionService.this.delayDialForDdsSwitch(phone), new Object[0]);
                        TelephonyConnectionService.this.adjustAndPlaceOutgoingConnection(phone, connection, connectionRequest, str, uri, i, true);
                    }
                });
                return;
            }
        }
        Log.i(this, "Call disconnected before the outgoing call was placed. Skipping call placement.", new Object[0]);
        if (z2) {
            for (Phone phone2 : this.mPhoneFactoryProxy.getPhones()) {
                phone2.setRadioPower(true, false, false, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustAndPlaceOutgoingConnection(Phone phone, Connection connection, ConnectionRequest connectionRequest, String str, Uri uri, int i, boolean z) {
        if (phone.getPhoneType() != i) {
            Connection telephonyConnection = getTelephonyConnection(connectionRequest, str, z, uri, phone);
            if (telephonyConnection instanceof TelephonyConnection) {
                placeOutgoingConnection((TelephonyConnection) telephonyConnection, phone, connectionRequest);
            }
            addExistingConnection(this.mPhoneUtilsProxy.makePstnPhoneAccountHandleWithPrefix(phone, "", z && (SubscriptionController.getInstance().getActiveSubInfoCount(phone.getContext().getOpPackageName(), phone.getContext().getAttributionTag()) == 0)), telephonyConnection);
            closeOrDestroyConnection(connection, this.mDisconnectCauseFactory.toTelecomDisconnectCause(44, "Reconnecting outgoing Emergency Call.", phone.getPhoneId()));
            return;
        }
        placeOutgoingConnection((TelephonyConnection) connection, phone, connectionRequest);
    }

    private boolean canAddCall() {
        for (Connection connection : getAllConnections()) {
            if (connection.getExtras() != null && connection.getExtras().getBoolean("android.telecom.extra.DISABLE_ADD_CALL", false)) {
                return false;
            }
        }
        return true;
    }

    private Connection getTelephonyConnection(ConnectionRequest connectionRequest, String str, boolean z, Uri uri, Phone phone) {
        int dataNetworkType;
        if (phone == null) {
            Context applicationContext = getApplicationContext();
            if (this.mDeviceState.shouldCheckSimStateBeforeOutgoingCall(this)) {
                IccCard iccCard = this.mPhoneFactoryProxy.getDefaultPhone().getIccCard();
                IccCardConstants.State state = IccCardConstants.State.UNKNOWN;
                if (iccCard != null) {
                    state = iccCard.getState();
                }
                if (state == IccCardConstants.State.PIN_REQUIRED) {
                    String string = applicationContext.getResources().getString(R.string.config_simUnlockUiPackage);
                    String string2 = applicationContext.getResources().getString(R.string.config_simUnlockUiClass);
                    if (string != null && string2 != null) {
                        Intent component = new Intent().setComponent(new ComponentName(string, string2));
                        component.addFlags(268435456);
                        try {
                            applicationContext.startActivity(component);
                        } catch (ActivityNotFoundException e) {
                            Log.e(this, e, "Unable to find SIM unlock UI activity.", new Object[0]);
                        }
                    }
                    return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(18, "SIM_STATE_PIN_REQUIRED"));
                }
            }
            Log.d(this, "onCreateOutgoingConnection, phone is null", new Object[0]);
            return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(18, "Phone is null"));
        }
        int state2 = phone.getServiceState().getState();
        if (state2 == 1 && ((dataNetworkType = phone.getServiceState().getDataNetworkType()) == 13 || dataNetworkType == 19)) {
            state2 = phone.getServiceState().getDataRegistrationState();
        }
        if (!z && phone.isInEcm()) {
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) phone.getContext().getSystemService("carrier_config");
            if (!(carrierConfigManager != null ? carrierConfigManager.getConfigForSubId(phone.getSubId()).getBoolean("allow_non_emergency_calls_in_ecm_bool") : true)) {
                return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(34, "Cannot make non-emergency call in ECM mode.", phone.getPhoneId()));
            }
        }
        if (!z && state2 != 0) {
            if (state2 != 1) {
                if (state2 != 2) {
                    if (state2 == 3) {
                        if (!isRadioPowerDownOnBluetooth()) {
                            return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(17, "ServiceState.STATE_POWER_OFF", phone.getPhoneId()));
                        }
                    } else {
                        Log.d(this, "onCreateOutgoingConnection, unknown service state: %d", Integer.valueOf(state2));
                        return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(43, "Unknown service state " + state2, phone.getPhoneId()));
                    }
                }
            } else if (phone.isUtEnabled() && str.endsWith("#")) {
                Log.d(this, "onCreateOutgoingConnection dial for UT", new Object[0]);
            } else {
                return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(18, "ServiceState.STATE_OUT_OF_SERVICE", phone.getPhoneId()));
            }
        }
        boolean zIsTtyModeEnabled = this.mDeviceState.isTtyModeEnabled(this);
        if (VideoProfile.isVideo(connectionRequest.getVideoState()) && zIsTtyModeEnabled && !z) {
            return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(50, null, phone.getPhoneId()));
        }
        Connection connectionCheckAdditionalOutgoingCallLimits = checkAdditionalOutgoingCallLimits(phone);
        if (connectionCheckAdditionalOutgoingCallLimits != null) {
            return connectionCheckAdditionalOutgoingCallLimits;
        }
        if (blockCallForwardingNumberWhileRoaming(phone, str)) {
            return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(57, "Call forwarding while roaming", phone.getPhoneId()));
        }
        TelephonyConnection telephonyConnectionCreateConnectionFor = createConnectionFor(phone, null, true, connectionRequest.getAccountHandle(), connectionRequest.getTelecomCallId(), connectionRequest.isAdhocConferenceCall());
        if (telephonyConnectionCreateConnectionFor == null) {
            return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(43, "Invalid phone type", phone.getPhoneId()));
        }
        telephonyConnectionCreateConnectionFor.setAddress(uri, 1);
        telephonyConnectionCreateConnectionFor.setTelephonyConnectionInitializing();
        telephonyConnectionCreateConnectionFor.setTelephonyVideoState(connectionRequest.getVideoState());
        telephonyConnectionCreateConnectionFor.setRttTextStream(connectionRequest.getRttTextStream());
        telephonyConnectionCreateConnectionFor.setTtyEnabled(zIsTtyModeEnabled);
        telephonyConnectionCreateConnectionFor.setIsAdhocConferenceCall(connectionRequest.isAdhocConferenceCall());
        telephonyConnectionCreateConnectionFor.setParticipants(connectionRequest.getParticipants());
        return telephonyConnectionCreateConnectionFor;
    }

    @Override // android.telecom.ConnectionService
    public Connection onCreateIncomingConnection(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        boolean z;
        Log.i(this, "onCreateIncomingConnection, request: " + connectionRequest, new Object[0]);
        PhoneAccountHandle accountHandle = connectionRequest.getAccountHandle();
        if (accountHandle == null || !"E".equals(accountHandle.getId())) {
            z = false;
        } else {
            Log.i(this, "Emergency PhoneAccountHandle is being used for incoming call... Treat as an Emergency Call.", new Object[0]);
            z = true;
        }
        String string = null;
        Phone phoneForAccount = getPhoneForAccount(accountHandle, z, connectionRequest.getAddress() == null ? null : connectionRequest.getAddress().getSchemeSpecificPart());
        if (phoneForAccount == null) {
            return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(36, "Phone is null"));
        }
        Bundle extras = connectionRequest.getExtras();
        if (extras.containsKey("android.telecom.extra.CALL_DISCONNECT_MESSAGE")) {
            string = extras.getString("android.telecom.extra.CALL_DISCONNECT_MESSAGE");
            Log.i(this, "onCreateIncomingConnection Disconnect message " + string, new Object[0]);
        }
        Call ringingCall = phoneForAccount.getRingingCall();
        if (!ringingCall.getState().isRinging() || (string != null && string.equals("Call dropped by lower layers"))) {
            Log.i(this, "onCreateIncomingConnection, no ringing call", new Object[0]);
            Connection connectionCreateFailedConnection = Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(1, "Found no ringing call", phoneForAccount.getPhoneId()));
            long j = extras.getLong("android.telecom.extra.CALL_CREATED_EPOCH_TIME_MILLIS");
            if (j != 0) {
                Log.i(this, "onCreateIncomingConnection. Set connect time info.", new Object[0]);
                connectionCreateFailedConnection.setConnectTimeMillis(j);
            }
            Uri uri = (Uri) extras.getParcelable("android.telecom.extra.INCOMING_CALL_ADDRESS");
            if (uri != null) {
                Log.i(this, "onCreateIncomingConnection. Set caller id info.", new Object[0]);
                connectionCreateFailedConnection.setAddress(uri, 1);
            }
            return connectionCreateFailedConnection;
        }
        com.android.internal.telephony.Connection latestConnection = ringingCall.getState() == Call.State.WAITING ? ringingCall.getLatestConnection() : ringingCall.getEarliestConnection();
        if (isOriginalConnectionKnown(latestConnection)) {
            Log.i(this, "onCreateIncomingConnection, original connection already registered", new Object[0]);
            return Connection.createCanceledConnection();
        }
        TelephonyConnection telephonyConnectionCreateConnectionFor = createConnectionFor(phoneForAccount, latestConnection, false, connectionRequest.getAccountHandle(), connectionRequest.getTelecomCallId(), connectionRequest.isAdhocConferenceCall());
        handleIncomingRtt(connectionRequest, latestConnection);
        if (telephonyConnectionCreateConnectionFor == null) {
            return Connection.createCanceledConnection();
        }
        telephonyConnectionCreateConnectionFor.setTtyEnabled(this.mDeviceState.isTtyModeEnabled(getApplicationContext()));
        return telephonyConnectionCreateConnectionFor;
    }

    private void handleIncomingRtt(ConnectionRequest connectionRequest, com.android.internal.telephony.Connection connection) {
        if (connection == null || connection.getPhoneType() != 5) {
            if (connectionRequest.isRequestingRtt()) {
                Log.w(this, "Requesting RTT on non-IMS call, ignoring", new Object[0]);
                return;
            }
            return;
        }
        ImsPhoneConnection imsPhoneConnection = (ImsPhoneConnection) connection;
        if (!connectionRequest.isRequestingRtt()) {
            if (imsPhoneConnection.isRttEnabledForCall()) {
                Log.w(this, "Incoming call requested RTT but we did not get a RttTextStream", new Object[0]);
                return;
            }
            return;
        }
        Log.i(this, "Setting RTT stream on ImsPhoneConnection in case we need it later", new Object[0]);
        imsPhoneConnection.setCurrentRttTextStream(connectionRequest.getRttTextStream());
        if (!imsPhoneConnection.isRttEnabledForCall()) {
            if (connectionRequest.isRequestingRtt()) {
                Log.w(this, "Incoming call processed as RTT but did not come in as one. Ignoring", new Object[0]);
            }
        } else {
            Log.i(this, "Setting the call to be answered with RTT on.", new Object[0]);
            imsPhoneConnection.getImsCall().setAnswerWithRtt();
        }
    }

    public void onCreateConnectionComplete(Connection connection) {
        if (connection instanceof TelephonyConnection) {
            maybeSendInternationalCallEvent((TelephonyConnection) connection);
        }
    }

    @Override // android.telecom.ConnectionService
    public void onCreateIncomingConnectionFailed(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        boolean z;
        Log.i(this, "onCreateIncomingConnectionFailed, request: " + connectionRequest, new Object[0]);
        PhoneAccountHandle accountHandle = connectionRequest.getAccountHandle();
        if (accountHandle == null || !"E".equals(accountHandle.getId())) {
            z = false;
        } else {
            Log.w(this, "onCreateIncomingConnectionFailed:Emergency call failed... ", new Object[0]);
            z = true;
        }
        Phone phoneForAccount = getPhoneForAccount(accountHandle, z, connectionRequest.getAddress() == null ? null : connectionRequest.getAddress().getSchemeSpecificPart());
        if (phoneForAccount == null) {
            Log.w(this, "onCreateIncomingConnectionFailed: can not find corresponding phone.", new Object[0]);
            return;
        }
        Call ringingCall = phoneForAccount.getRingingCall();
        if (!ringingCall.getState().isRinging()) {
            Log.w(this, "onCreateIncomingConnectionFailed, no ringing call found for failed call", new Object[0]);
            return;
        }
        com.android.internal.telephony.Connection latestConnection = ringingCall.getState() == Call.State.WAITING ? ringingCall.getLatestConnection() : ringingCall.getEarliestConnection();
        TelephonyConnection connectionForOriginalConnection = getConnectionForOriginalConnection(latestConnection);
        if (connectionForOriginalConnection != null) {
            Log.w(this, "onCreateIncomingConnectionFailed, original connection already registered. Hanging it up.", new Object[0]);
            connectionForOriginalConnection.onAbort();
            return;
        }
        TelephonyConnection telephonyConnectionCreateConnectionFor = createConnectionFor(phoneForAccount, latestConnection, false, connectionRequest.getAccountHandle(), connectionRequest.getTelecomCallId());
        if (telephonyConnectionCreateConnectionFor == null) {
            Log.w(this, "onCreateIncomingConnectionFailed, TelephonyConnection created as null, ignoring.", new Object[0]);
        } else {
            telephonyConnectionCreateConnectionFor.onReject();
            telephonyConnectionCreateConnectionFor.close();
        }
    }

    public void onCreateConferenceComplete(Conference conference) {
        if (conference instanceof ImsConference) {
            maybeSendInternationalCallEvent((TelephonyConnection) ((ImsConference) conference).getConferenceHost());
        }
    }

    @Override // android.telecom.ConnectionService
    public void onCreateIncomingConferenceFailed(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        Log.i(this, "onCreateIncomingConferenceFailed, request: " + connectionRequest, new Object[0]);
        onCreateIncomingConnectionFailed(phoneAccountHandle, connectionRequest);
    }

    public void triggerConferenceRecalculate() {
        if (this.mTelephonyConferenceController.shouldRecalculate()) {
            this.mTelephonyConferenceController.recalculate();
        }
    }

    public Connection onCreateUnknownConnection(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        boolean z;
        com.android.internal.telephony.Connection connection;
        com.android.internal.telephony.Connection connectionById;
        Log.i(this, "onCreateUnknownConnection, request: " + connectionRequest, new Object[0]);
        PhoneAccountHandle accountHandle = connectionRequest.getAccountHandle();
        if (accountHandle == null || !"E".equals(accountHandle.getId())) {
            z = false;
        } else {
            Log.i(this, "Emergency PhoneAccountHandle is being used for unknown call... Treat as an Emergency Call.", new Object[0]);
            z = true;
        }
        Phone phoneForAccount = getPhoneForAccount(accountHandle, z, connectionRequest.getAddress() == null ? null : connectionRequest.getAddress().getSchemeSpecificPart());
        if (phoneForAccount == null) {
            return Connection.createFailedConnection(this.mDisconnectCauseFactory.toTelecomDisconnectCause(36, "Phone is null"));
        }
        Bundle extras = connectionRequest.getExtras();
        ArrayList arrayList = new ArrayList();
        if (phoneForAccount.getImsPhone() != null && extras != null && extras.containsKey("android.telephony.ImsExternalCallTracker.extra.EXTERNAL_CALL_ID")) {
            ImsExternalCallTracker externalCallTracker = phoneForAccount.getImsPhone().getExternalCallTracker();
            int i = extras.getInt("android.telephony.ImsExternalCallTracker.extra.EXTERNAL_CALL_ID", -1);
            if (externalCallTracker != null && (connectionById = externalCallTracker.getConnectionById(i)) != null) {
                arrayList.add(connectionById);
            }
        }
        if (arrayList.isEmpty()) {
            Call ringingCall = phoneForAccount.getRingingCall();
            if (ringingCall.hasConnections()) {
                arrayList.addAll(ringingCall.getConnections());
            }
            Call foregroundCall = phoneForAccount.getForegroundCall();
            if (foregroundCall.getState() != Call.State.DISCONNECTED && foregroundCall.hasConnections()) {
                arrayList.addAll(foregroundCall.getConnections());
            }
            if (phoneForAccount.getImsPhone() != null) {
                Call foregroundCall2 = phoneForAccount.getImsPhone().getForegroundCall();
                if (foregroundCall2.getState() != Call.State.DISCONNECTED && foregroundCall2.hasConnections()) {
                    arrayList.addAll(foregroundCall2.getConnections());
                }
            }
            if (phoneForAccount.getBackgroundCall().hasConnections()) {
                arrayList.addAll(phoneForAccount.getBackgroundCall().getConnections());
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                connection = null;
                break;
            }
            com.android.internal.telephony.Connection connection2 = (com.android.internal.telephony.Connection) it.next();
            if (!isOriginalConnectionKnown(connection2)) {
                Log.d(this, "onCreateUnknownConnection: conn = " + connection2, new Object[0]);
                connection = connection2;
                break;
            }
        }
        if (connection == null) {
            Log.i(this, "onCreateUnknownConnection, did not find previously unknown connection.", new Object[0]);
            return Connection.createCanceledConnection();
        }
        if (connection != null) {
            connection.getVideoState();
        }
        TelephonyConnection telephonyConnectionCreateConnectionFor = createConnectionFor(phoneForAccount, connection, !connection.isIncoming(), connectionRequest.getAccountHandle(), connectionRequest.getTelecomCallId());
        if (telephonyConnectionCreateConnectionFor == null) {
            return Connection.createCanceledConnection();
        }
        telephonyConnectionCreateConnectionFor.updateState();
        return telephonyConnectionCreateConnectionFor;
    }

    @Override // android.telecom.ConnectionService
    public void onConference(Connection connection, Connection connection2) {
        if (connection instanceof TelephonyConnection) {
            ((TelephonyConnection) connection).performConference(connection2);
        } else if (connection2 instanceof TelephonyConnection) {
            ((TelephonyConnection) connection2).performConference(connection);
        } else {
            Log.w(this, "onConference - cannot merge connections Connection1: %s, Connection2: %2", connection, connection2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConnectionAdded(Connection connection) {
        if (!(connection instanceof Holdable) || isExternalConnection(connection)) {
            return;
        }
        this.mHoldTracker.addHoldable(connection.getPhoneAccountHandle(), (Holdable) connection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConnectionRemoved(Connection connection) {
        if (!(connection instanceof Holdable) || isExternalConnection(connection)) {
            return;
        }
        this.mHoldTracker.removeHoldable(connection.getPhoneAccountHandle(), (Holdable) connection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConferenceAdded(Conference conference) {
        if (conference instanceof Holdable) {
            this.mHoldTracker.addHoldable(conference.getPhoneAccountHandle(), (Holdable) conference);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConferenceRemoved(Conference conference) {
        if (conference instanceof Holdable) {
            this.mHoldTracker.removeHoldable(conference.getPhoneAccountHandle(), (Holdable) conference);
        }
    }

    private boolean isExternalConnection(Connection connection) {
        return (connection.getConnectionProperties() & 16) == 16;
    }

    private boolean blockCallForwardingNumberWhileRoaming(Phone phone, String str) {
        if (phone != null && !TextUtils.isEmpty(str) && phone.getServiceState().getRoaming()) {
            String[] stringArray = null;
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) phone.getContext().getSystemService("carrier_config");
            if (carrierConfigManager != null) {
                if (carrierConfigManager.getConfigForSubId(phone.getSubId()).getBoolean("support_ims_call_forwarding_while_roaming_bool", true) && useImsForAudioOnlyCall(phone)) {
                    return false;
                }
                stringArray = carrierConfigManager.getConfigForSubId(phone.getSubId()).getStringArray("call_forwarding_blocks_while_roaming_string_array");
            }
            if (stringArray != null) {
                for (String str2 : stringArray) {
                    if (str.startsWith(str2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean useImsForAudioOnlyCall(Phone phone) {
        Phone imsPhone = phone.getImsPhone();
        return imsPhone != null && (imsPhone.isVolteEnabled() || imsPhone.isWifiCallingEnabled()) && imsPhone.getServiceState().getState() == 0;
    }

    private boolean isRadioOn() {
        boolean zIsRadioOn = false;
        for (Phone phone : this.mPhoneFactoryProxy.getPhones()) {
            zIsRadioOn |= phone.isRadioOn();
        }
        return zIsRadioOn;
    }

    private Pair<WeakReference<TelephonyConnection>, Queue<Phone>> makeCachedConnectionPhonePair(TelephonyConnection telephonyConnection) {
        return new Pair<>(new WeakReference(telephonyConnection), new LinkedList(Arrays.asList(this.mPhoneFactoryProxy.getPhones())));
    }

    private void updateCachedConnectionPhonePair(TelephonyConnection telephonyConnection, boolean z) {
        Pair<WeakReference<TelephonyConnection>, Queue<Phone>> pair = this.mEmergencyRetryCache;
        if (pair == null) {
            Log.i(this, "updateCachedConnectionPhonePair, cache is null. Generating new cache", new Object[0]);
            this.mEmergencyRetryCache = makeCachedConnectionPhonePair(telephonyConnection);
        } else if (((WeakReference) pair.first).get() != telephonyConnection) {
            Log.i(this, "updateCachedConnectionPhonePair, cache is stale. Regenerating.", new Object[0]);
            this.mEmergencyRetryCache = makeCachedConnectionPhonePair(telephonyConnection);
        }
        Queue queue = (Queue) this.mEmergencyRetryCache.second;
        Phone defaultPhone = telephonyConnection.getPhone().getDefaultPhone();
        if (defaultPhone == null) {
            return;
        }
        queue.remove(defaultPhone);
        Log.i(this, "updateCachedConnectionPhonePair, isPermanentFailure:" + z, new Object[0]);
        if (z) {
            return;
        }
        queue.offer(defaultPhone);
    }

    @VisibleForTesting
    public void retryOutgoingOriginalConnection(TelephonyConnection telephonyConnection, boolean z) {
        int phoneId = telephonyConnection.getPhone() == null ? -1 : telephonyConnection.getPhone().getPhoneId();
        updateCachedConnectionPhonePair(telephonyConnection, z);
        Object obj = this.mEmergencyRetryCache.second;
        Phone phone = obj != null ? (Phone) ((Queue) obj).peek() : null;
        if (phone != null) {
            int videoState = telephonyConnection.getVideoState();
            Bundle extras = telephonyConnection.getExtras();
            Log.i(this, "retryOutgoingOriginalConnection, redialing on Phone Id: " + phone, new Object[0]);
            telephonyConnection.clearOriginalConnection();
            if (phoneId != phone.getPhoneId()) {
                updatePhoneAccount(telephonyConnection, phone);
            }
            placeOutgoingConnection(telephonyConnection, phone, videoState, extras);
            return;
        }
        Log.i(this, "retryOutgoingOriginalConnection, no more Phones to use. Disconnecting.", new Object[0]);
        closeOrDestroyConnection(telephonyConnection, new DisconnectCause(1));
    }

    private void updatePhoneAccount(TelephonyConnection telephonyConnection, Phone phone) {
        PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandle = this.mPhoneUtilsProxy.makePstnPhoneAccountHandle(phone);
        Log.i(this, "updatePhoneAccount setPhoneAccountHandle, account = " + phoneAccountHandleMakePstnPhoneAccountHandle, new Object[0]);
        telephonyConnection.setPhoneAccountHandle(phoneAccountHandleMakePstnPhoneAccountHandle);
    }

    private void placeOutgoingConnection(TelephonyConnection telephonyConnection, Phone phone, ConnectionRequest connectionRequest) {
        placeOutgoingConnection(telephonyConnection, phone, connectionRequest.getVideoState(), connectionRequest.getExtras());
    }

    private void placeOutgoingConnection(TelephonyConnection telephonyConnection, Phone phone, int i, Bundle bundle) {
        String schemeSpecificPart = telephonyConnection.getAddress() != null ? telephonyConnection.getAddress().getSchemeSpecificPart() : "";
        com.android.internal.telephony.Connection connectionDial = null;
        if (phone != null) {
            try {
                EmergencyNumber emergencyNumber = phone.getEmergencyNumberTracker().getEmergencyNumber(schemeSpecificPart);
                if (emergencyNumber != null) {
                    phone.notifyOutgoingEmergencyCall(emergencyNumber);
                    if (!getAllConnections().isEmpty()) {
                        if (!shouldHoldForEmergencyCall(phone)) {
                            for (Connection connection : getAllConnections()) {
                                if (!connection.equals(telephonyConnection) && connection.getState() != 6 && (connection instanceof TelephonyConnection)) {
                                    ((TelephonyConnection) connection).hangup(80);
                                }
                            }
                            for (Conference conference : getAllConferences()) {
                                if (conference.getState() != 6 && (conference instanceof Conference)) {
                                    conference.onDisconnect();
                                }
                            }
                        } else if (!isVideoCallHoldAllowed(phone)) {
                            Iterator<Connection> it = getAllConnections().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                Connection next = it.next();
                                if (!next.equals(telephonyConnection) && next.getState() == 4 && VideoProfile.isVideo(next.getVideoState()) && (next instanceof TelephonyConnection)) {
                                    ((TelephonyConnection) next).hangup(80);
                                    break;
                                }
                            }
                        }
                    }
                }
                connectionDial = phone.dial(schemeSpecificPart, new ImsPhone.ImsDialArgs.Builder().setVideoState(i).setIntentExtras(bundle).setRttTextStream(telephonyConnection.getRttTextStream()).build());
            } catch (CallStateException e) {
                Log.e(this, e, "placeOutgoingConnection, phone.dial exception: " + e, new Object[0]);
                handleCallStateException(e, telephonyConnection, phone);
                return;
            }
        }
        if (connectionDial == null) {
            int i2 = 43;
            if (phone.getPhoneType() == 1) {
                Log.d(this, "dialed MMI code", new Object[0]);
                int subId = phone.getSubId();
                Log.d(this, "subId: " + subId, new Object[0]);
                Intent intent = new Intent(this, (Class<?>) MMIDialogActivity.class);
                intent.setFlags(276824064);
                if (SubscriptionManager.isValidSubscriptionId(subId)) {
                    SubscriptionManager.putSubscriptionIdExtra(intent, subId);
                }
                startActivity(intent);
                i2 = 39;
            }
            Log.d(this, "placeOutgoingConnection, phone.dial returned null", new Object[0]);
            telephonyConnection.setTelephonyConnectionDisconnected(this.mDisconnectCauseFactory.toTelecomDisconnectCause(i2, "Connection is null", phone.getPhoneId()));
            telephonyConnection.close();
            return;
        }
        telephonyConnection.setOriginalConnection(connectionDial);
    }

    private boolean isVideoCallHoldAllowed(Phone phone) {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) phone.getContext().getSystemService("carrier_config");
        if (carrierConfigManager == null) {
            Log.w(this, "isVideoCallHoldAllowed: couldn't get CarrierConfigManager", new Object[0]);
            return true;
        }
        return carrierConfigManager.getConfigForSubId(phone.getSubId()).getBoolean("allow_hold_video_call_bool", true);
    }

    private boolean shouldHoldForEmergencyCall(Phone phone) {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) phone.getContext().getSystemService("carrier_config");
        if (carrierConfigManager == null) {
            Log.w(this, "shouldHoldForEmergencyCall: couldn't get CarrierConfigManager", new Object[0]);
            return true;
        }
        return carrierConfigManager.getConfigForSubId(phone.getSubId()).getBoolean("allow_hold_call_during_emergency_bool", true);
    }

    private void handleCallStateException(CallStateException callStateException, TelephonyConnection telephonyConnection, Phone phone) {
        int i;
        switch (callStateException.getError()) {
            case 1:
                i = 18;
                break;
            case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                i = 17;
                break;
            case 3:
                i = 72;
                break;
            case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                i = 73;
                break;
            case 5:
                i = 74;
                break;
            case 6:
                i = 75;
                break;
            case 7:
                i = 76;
                break;
            default:
                i = 43;
                break;
        }
        telephonyConnection.setTelephonyConnectionDisconnected(DisconnectCauseUtil.toTelecomDisconnectCause(i, callStateException.getMessage(), phone.getPhoneId()));
        telephonyConnection.close();
    }

    private TelephonyConnection createConnectionFor(Phone phone, com.android.internal.telephony.Connection connection, boolean z, PhoneAccountHandle phoneAccountHandle, String str) {
        return createConnectionFor(phone, connection, z, phoneAccountHandle, str, false);
    }

    private TelephonyConnection createConnectionFor(Phone phone, com.android.internal.telephony.Connection connection, boolean z, PhoneAccountHandle phoneAccountHandle, String str, boolean z2) {
        TelephonyConnection cdmaConnection;
        int phoneType = phone.getPhoneType();
        if (phoneType == 1) {
            cdmaConnection = new GsmConnection(connection, str, z ? 1 : 0);
        } else if (phoneType == 2) {
            cdmaConnection = new CdmaConnection(connection, this.mEmergencyTonePlayer, allowsMute(phone), z ? 1 : 0, str);
        } else {
            cdmaConnection = null;
        }
        if (cdmaConnection != null) {
            if (!z2) {
                cdmaConnection.addTelephonyConnectionListener(this.mTelephonyConnectionListener);
            }
            cdmaConnection.setVideoPauseSupported(TelecomAccountRegistry.getInstance(this).isVideoPauseSupported(phoneAccountHandle));
            cdmaConnection.setManageImsConferenceCallSupported(TelecomAccountRegistry.getInstance(this).isManageImsConferenceCallSupported(phoneAccountHandle));
            cdmaConnection.setShowPreciseFailedCause(TelecomAccountRegistry.getInstance(this).isShowPreciseFailedCause(phoneAccountHandle));
            cdmaConnection.setTelephonyConnectionService(this);
        }
        return cdmaConnection;
    }

    private boolean isOriginalConnectionKnown(com.android.internal.telephony.Connection connection) {
        return getConnectionForOriginalConnection(connection) != null;
    }

    private TelephonyConnection getConnectionForOriginalConnection(com.android.internal.telephony.Connection connection) {
        for (Connection connection2 : getAllConnections()) {
            if (connection2 instanceof TelephonyConnection) {
                TelephonyConnection telephonyConnection = (TelephonyConnection) connection2;
                if (telephonyConnection.getOriginalConnection() == connection) {
                    return telephonyConnection;
                }
            }
        }
        return null;
    }

    private Phone getPhoneForAccount(PhoneAccountHandle phoneAccountHandle, boolean z, String str) {
        int subIdForPhoneAccountHandle = this.mPhoneUtilsProxy.getSubIdForPhoneAccountHandle(phoneAccountHandle);
        Phone phone = subIdForPhoneAccountHandle != -1 ? this.mPhoneFactoryProxy.getPhone(this.mSubscriptionManagerProxy.getPhoneId(subIdForPhoneAccountHandle)) : null;
        if (!z) {
            return phone;
        }
        if (phone != null && isAvailableForEmergencyCalls(phone)) {
            return phone;
        }
        Log.d(this, "getPhoneForAccount: phone for phone acct handle %s is out of service or invalid for emergency call.", phoneAccountHandle);
        Phone phoneForEmergencyCall = getPhoneForEmergencyCall(str);
        StringBuilder sb = new StringBuilder();
        sb.append("getPhoneForAccount: using subId: ");
        sb.append(phoneForEmergencyCall == null ? "null" : Integer.valueOf(phoneForEmergencyCall.getSubId()));
        Log.d(this, sb.toString(), new Object[0]);
        return phoneForEmergencyCall;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean delayDialForDdsSwitch(Phone phone) {
        if (phone == null) {
            return true;
        }
        try {
            return possiblyOverrideDefaultDataForEmergencyCall(phone).get(1000L, TimeUnit.MILLISECONDS).booleanValue();
        } catch (Exception e) {
            Log.w(this, "delayDialForDdsSwitch - exception= " + e.getMessage(), new Object[0]);
            return false;
        }
    }

    private CompletableFuture<Boolean> possiblyOverrideDefaultDataForEmergencyCall(Phone phone) {
        int i;
        Boolean bool = Boolean.TRUE;
        if (this.mTelephonyManagerProxy.getPhoneCount() <= 1) {
            return CompletableFuture.completedFuture(bool);
        }
        if (!this.mDeviceState.isSuplDdsSwitchRequiredForEmergencyCall(this)) {
            Log.d(this, "possiblyOverrideDefaultDataForEmergencyCall: not switching DDS, does not require DDS switch.", new Object[0]);
            return CompletableFuture.completedFuture(bool);
        }
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) phone.getContext().getSystemService("carrier_config");
        if (carrierConfigManager == null) {
            Log.w(this, "possiblyOverrideDefaultDataForEmergencyCall: couldn't getCarrierConfigManager", new Object[0]);
            return CompletableFuture.completedFuture(bool);
        }
        if (!isAvailableForEmergencyCalls(phone)) {
            Log.d(this, "possiblyOverrideDefaultDataForEmergencyCall: not switching DDS", new Object[0]);
            return CompletableFuture.completedFuture(bool);
        }
        boolean voiceRoaming = phone.getServiceState().getVoiceRoaming();
        String[] stringArray = carrierConfigManager.getConfigForSubId(phone.getSubId()).getStringArray("gps.es_supl_data_plane_only_roaming_plmn_string_array");
        boolean z = stringArray == null || !Arrays.asList(stringArray).contains(phone.getServiceState().getOperatorNumeric());
        if (voiceRoaming && z) {
            Log.d(this, "possiblyOverrideDefaultDataForEmergencyCall: roaming network is assumed to support CP fallback, not switching DDS.", new Object[0]);
            return CompletableFuture.completedFuture(bool);
        }
        if ((carrierConfigManager.getConfigForSubId(phone.getSubId()).getInt("gps.es_supl_control_plane_support_int", 0) != 2) && z) {
            Log.d(this, "possiblyOverrideDefaultDataForEmergencyCall: not switching DDS, carrier supports CP fallback.", new Object[0]);
            return CompletableFuture.completedFuture(bool);
        }
        try {
            i = Integer.parseInt(carrierConfigManager.getConfigForSubId(phone.getSubId()).getString("gps.es_extension_sec", "0"));
        } catch (NumberFormatException unused) {
            i = 0;
        }
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        try {
            Log.d(this, "possiblyOverrideDefaultDataForEmergencyCall: overriding DDS for " + i + "seconds", new Object[0]);
            this.mPhoneSwitcherProxy.getPhoneSwitcher().overrideDefaultDataForEmergency(phone.getPhoneId(), i, completableFuture);
            return completableFuture;
        } catch (Exception e) {
            Log.w(this, "possiblyOverrideDefaultDataForEmergencyCall: exception = " + e.getMessage(), new Object[0]);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
    }

    public Phone getPhoneForEmergencyCall(String str) {
        ArrayList arrayList = new ArrayList();
        int defaultVoicePhoneId = this.mSubscriptionManagerProxy.getDefaultVoicePhoneId();
        for (Phone phone : this.mPhoneFactoryProxy.getPhones()) {
            if (phone.getEmergencyNumberTracker() != null && phone.getEmergencyNumberTracker().isEmergencyNumber(str, true) && (phone.getHalVersion().greaterOrEqual(RIL.RADIO_HAL_VERSION_1_4) || isAvailableForEmergencyCalls(phone))) {
                if (phone.getPhoneId() == defaultVoicePhoneId) {
                    Log.i(this, "getPhoneForEmergencyCall, Phone Id that supports emergency number: " + phone.getPhoneId(), new Object[0]);
                    return phone;
                }
                arrayList.add(phone);
            }
        }
        if (arrayList.size() > 0) {
            Log.i(this, "getPhoneForEmergencyCall, Phone Id that supports emergency number:" + arrayList.get(0).getPhoneId(), new Object[0]);
            return getFirstPhoneForEmergencyCall(arrayList);
        }
        return getFirstPhoneForEmergencyCall();
    }

    @VisibleForTesting
    public Phone getFirstPhoneForEmergencyCall() {
        return getFirstPhoneForEmergencyCall(null);
    }

    @VisibleForTesting
    public Phone getFirstPhoneForEmergencyCall(List<Phone> list) {
        Phone phone;
        int defaultVoicePhoneId = this.mSubscriptionManagerProxy.getDefaultVoicePhoneId();
        if (defaultVoicePhoneId != -1 && (phone = this.mPhoneFactoryProxy.getPhone(defaultVoicePhoneId)) != null && isAvailableForEmergencyCalls(phone) && (list == null || list.contains(phone))) {
            return phone;
        }
        final Phone phone2 = null;
        int phoneCount = this.mTelephonyManagerProxy.getPhoneCount();
        ArrayList arrayList = new ArrayList(phoneCount);
        for (int i = 0; i < phoneCount; i++) {
            Phone phone3 = this.mPhoneFactoryProxy.getPhone(i);
            if (phone3 != null) {
                if (isAvailableForEmergencyCalls(phone3) && (list == null || list.contains(phone3))) {
                    Log.i(this, "getFirstPhoneForEmergencyCall, radio on & in service, Phone Id:" + i, new Object[0]);
                    return phone3;
                }
                int radioAccessFamily = phone3.getRadioAccessFamily();
                SlotStatus slotStatus = new SlotStatus(i, radioAccessFamily);
                arrayList.add(slotStatus);
                Log.i(this, "getFirstPhoneForEmergencyCall, RAF:" + Integer.toHexString(radioAccessFamily) + " saved for Phone Id:" + i, new Object[0]);
                int simStateForSlotIdx = this.mSubscriptionManagerProxy.getSimStateForSlotIdx(i);
                slotStatus.simState = simStateForSlotIdx;
                if (simStateForSlotIdx == 2 || simStateForSlotIdx == 3) {
                    slotStatus.isLocked = true;
                }
                if (list != null) {
                    for (Phone phone4 : list) {
                        if (phone4 != null && phone4.getPhoneId() == i) {
                            slotStatus.hasDialedEmergencyNumber = true;
                        }
                    }
                }
                if (phone2 == null && this.mTelephonyManagerProxy.hasIccCard(i)) {
                    Log.i(this, "getFirstPhoneForEmergencyCall, SIM card inserted, Phone Id:" + phone3.getPhoneId(), new Object[0]);
                    phone2 = phone3;
                }
            }
        }
        if (phone2 == null && arrayList.isEmpty()) {
            if (list == null || list.isEmpty()) {
                Log.i(this, "getFirstPhoneForEmergencyCall, return default phone", new Object[0]);
                return this.mPhoneFactoryProxy.getDefaultPhone();
            }
            return list.get(0);
        }
        final int phoneId = this.mPhoneFactoryProxy.getDefaultPhone().getPhoneId();
        if (arrayList.isEmpty()) {
            return phone2;
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList, new Comparator() { // from class: com.android.services.telephony.-$$Lambda$TelephonyConnectionService$1fMRYx3WYwenL-2CSYYT8p0e-Qw
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return TelephonyConnectionService.lambda$getFirstPhoneForEmergencyCall$2(phone2, phoneId, (TelephonyConnectionService.SlotStatus) obj, (TelephonyConnectionService.SlotStatus) obj2);
                }
            });
        }
        int i2 = ((SlotStatus) arrayList.get(arrayList.size() - 1)).slotId;
        Log.i(this, "getFirstPhoneForEmergencyCall, Using Phone Id: " + i2 + "with highest capability", new Object[0]);
        return this.mPhoneFactoryProxy.getPhone(i2);
    }

    static /* synthetic */ int lambda$getFirstPhoneForEmergencyCall$2(Phone phone, int i, SlotStatus slotStatus, SlotStatus slotStatus2) {
        if (!slotStatus.hasDialedEmergencyNumber && slotStatus2.hasDialedEmergencyNumber) {
            return -1;
        }
        if (slotStatus.hasDialedEmergencyNumber && !slotStatus2.hasDialedEmergencyNumber) {
            return 1;
        }
        if (slotStatus.simState == 1 && slotStatus2.simState != 1) {
            return -1;
        }
        if (slotStatus2.simState == 1 && slotStatus.simState != 1) {
            return 1;
        }
        if (slotStatus.isLocked && !slotStatus2.isLocked) {
            return -1;
        }
        if (slotStatus2.isLocked && !slotStatus.isLocked) {
            return 1;
        }
        int iCompare = RadioAccessFamily.compare(slotStatus.capabilities, slotStatus2.capabilities);
        if (iCompare == 0) {
            if (phone != null) {
                if (slotStatus.slotId == phone.getPhoneId()) {
                    return 1;
                }
                if (slotStatus2.slotId == phone.getPhoneId()) {
                    return -1;
                }
            } else {
                if (slotStatus.slotId == i) {
                    return 1;
                }
                if (slotStatus2.slotId == i) {
                    return -1;
                }
            }
        }
        return iCompare;
    }

    private boolean isAvailableForEmergencyCalls(Phone phone) {
        return phone.getServiceState().getState() == 0 || phone.getServiceState().isEmergencyOnly();
    }

    private boolean allowsMute(Phone phone) {
        return (phone.getPhoneType() == 2 && phone.isInEcm()) ? false : true;
    }

    TelephonyConnection.TelephonyConnectionListener getTelephonyConnectionListener() {
        return this.mTelephonyConnectionListener;
    }

    public void addConnectionToConferenceController(TelephonyConnection telephonyConnection) {
        if (telephonyConnection.isImsConnection()) {
            Log.d(this, "Adding IMS connection to conference controller: " + telephonyConnection, new Object[0]);
            this.mImsConferenceController.add(telephonyConnection);
            this.mTelephonyConferenceController.remove(telephonyConnection);
            if (telephonyConnection instanceof CdmaConnection) {
                this.mCdmaConferenceController.remove((CdmaConnection) telephonyConnection);
                return;
            }
            return;
        }
        int phoneType = telephonyConnection.getCall().getPhone().getPhoneType();
        if (phoneType == 1) {
            Log.d(this, "Adding GSM connection to conference controller: " + telephonyConnection, new Object[0]);
            this.mTelephonyConferenceController.add(telephonyConnection);
            if (telephonyConnection instanceof CdmaConnection) {
                this.mCdmaConferenceController.remove((CdmaConnection) telephonyConnection);
            }
        } else if (phoneType == 2 && (telephonyConnection instanceof CdmaConnection)) {
            Log.d(this, "Adding CDMA connection to conference controller: " + telephonyConnection, new Object[0]);
            this.mCdmaConferenceController.add((CdmaConnection) telephonyConnection);
            this.mTelephonyConferenceController.remove(telephonyConnection);
        }
        Log.d(this, "Removing connection from IMS conference controller: " + telephonyConnection, new Object[0]);
        this.mImsConferenceController.remove(telephonyConnection);
    }

    private Connection checkAdditionalOutgoingCallLimits(Phone phone) {
        if (phone.getPhoneType() == 2) {
            for (Conference conference : getAllConferences()) {
                if ((conference instanceof CdmaConference) && (((CdmaConference) conference).getConnectionCapabilities() & 4) != 0) {
                    return Connection.createFailedConnection(new DisconnectCause(8, null, getResources().getString(R.string.callFailed_cdma_call_limit), "merge-capable call exists, prevent flash command."));
                }
            }
        }
        return null;
    }

    private void maybeSendInternationalCallEvent(TelephonyConnection telephonyConnection) {
        if (telephonyConnection == null || telephonyConnection.getPhone() == null || telephonyConnection.getPhone().getDefaultPhone() == null) {
            return;
        }
        GsmCdmaPhone defaultPhone = telephonyConnection.getPhone().getDefaultPhone();
        if (defaultPhone instanceof GsmCdmaPhone) {
            GsmCdmaPhone gsmCdmaPhone = defaultPhone;
            if (telephonyConnection.isOutgoingCall() && gsmCdmaPhone.isNotificationOfWfcCallRequired(telephonyConnection.getOriginalConnection().getOrigDialString())) {
                Log.i(this, "placeOutgoingConnection - sending international call on WFC confirmation event", new Object[0]);
                telephonyConnection.sendTelephonyConnectionEvent("android.telephony.event.EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC", null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTtyModeChange(boolean z) {
        Log.i(this, "handleTtyModeChange; isTtyEnabled=%b", Boolean.valueOf(z));
        this.mIsTtyEnabled = z;
        for (Connection connection : getAllConnections()) {
            if (connection instanceof TelephonyConnection) {
                ((TelephonyConnection) connection).setTtyEnabled(z);
            }
        }
    }

    private void closeOrDestroyConnection(Connection connection, DisconnectCause disconnectCause) {
        if (connection instanceof TelephonyConnection) {
            TelephonyConnection telephonyConnection = (TelephonyConnection) connection;
            telephonyConnection.setTelephonyConnectionDisconnected(disconnectCause);
            telephonyConnection.close();
        } else {
            connection.setDisconnected(disconnectCause);
            connection.destroy();
        }
    }

    public void addTelephonyConference(TelephonyConferenceBase telephonyConferenceBase) {
        addConference(telephonyConferenceBase);
        telephonyConferenceBase.addTelephonyConferenceListener(this.mTelephonyConferenceListener);
    }
}
