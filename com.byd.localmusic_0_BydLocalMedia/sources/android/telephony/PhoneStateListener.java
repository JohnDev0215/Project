package android.telephony;

import android.annotation.SystemApi;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.ImsReasonInfo;
import com.android.internal.telephony.IPhoneStateListener;
import com.android.internal.util.FunctionalUtils;
import dalvik.system.VMRuntime;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneStateListener {
    private static final boolean DBG = false;
    public static final int DEFAULT_PER_PID_REGISTRATION_LIMIT = 50;
    public static final String FLAG_PER_PID_REGISTRATION_LIMIT = "phone_state_listener_per_pid_registration_limit";
    public static final int LISTEN_ACTIVE_DATA_SUBSCRIPTION_ID_CHANGE = 4194304;
    public static final int LISTEN_ALWAYS_REPORTED_SIGNAL_STRENGTH = 512;
    public static final int LISTEN_BARRING_INFO = Integer.MIN_VALUE;

    @SystemApi
    public static final int LISTEN_CALL_ATTRIBUTES_CHANGED = 67108864;
    public static final int LISTEN_CALL_DISCONNECT_CAUSES = 33554432;
    public static final int LISTEN_CALL_FORWARDING_INDICATOR = 8;
    public static final int LISTEN_CALL_STATE = 32;
    public static final int LISTEN_CARRIER_NETWORK_CHANGE = 65536;
    public static final int LISTEN_CELL_INFO = 1024;
    public static final int LISTEN_CELL_LOCATION = 16;
    public static final int LISTEN_DATA_ACTIVATION_STATE = 262144;
    public static final int LISTEN_DATA_ACTIVITY = 128;

    @Deprecated
    public static final int LISTEN_DATA_CONNECTION_REAL_TIME_INFO = 8192;
    public static final int LISTEN_DATA_CONNECTION_STATE = 64;
    public static final int LISTEN_DISPLAY_INFO_CHANGED = 1048576;
    public static final int LISTEN_EMERGENCY_NUMBER_LIST = 16777216;
    public static final int LISTEN_IMS_CALL_DISCONNECT_CAUSES = 134217728;
    public static final int LISTEN_MESSAGE_WAITING_INDICATOR = 4;
    public static final int LISTEN_NONE = 0;

    @Deprecated
    public static final int LISTEN_OEM_HOOK_RAW_EVENT = 32768;

    @SystemApi
    public static final int LISTEN_OUTGOING_EMERGENCY_CALL = 268435456;

    @SystemApi
    public static final int LISTEN_OUTGOING_EMERGENCY_SMS = 536870912;
    public static final int LISTEN_PHONE_CAPABILITY_CHANGE = 2097152;

    @SystemApi
    public static final int LISTEN_PRECISE_CALL_STATE = 2048;
    public static final int LISTEN_PRECISE_DATA_CONNECTION_STATE = 4096;

    @SystemApi
    public static final int LISTEN_RADIO_POWER_STATE_CHANGED = 8388608;
    public static final int LISTEN_REGISTRATION_FAILURE = 1073741824;
    public static final int LISTEN_SERVICE_STATE = 1;

    @Deprecated
    public static final int LISTEN_SIGNAL_STRENGTH = 2;
    public static final int LISTEN_SIGNAL_STRENGTHS = 256;

    @SystemApi
    public static final int LISTEN_SRVCC_STATE_CHANGED = 16384;
    public static final int LISTEN_USER_MOBILE_DATA_STATE = 524288;

    @SystemApi
    public static final int LISTEN_VOICE_ACTIVATION_STATE = 131072;
    private static final String LOG_TAG = "PhoneStateListener";
    public static final long PHONE_STATE_LISTENER_LIMIT_CHANGE_ID = 150880553;
    public final IPhoneStateListener callback;
    protected Integer mSubId;

    public void onActiveDataSubscriptionIdChanged(int i) {
    }

    public void onBarringInfoChanged(BarringInfo barringInfo) {
    }

    @SystemApi
    public void onCallAttributesChanged(CallAttributes callAttributes) {
    }

    public void onCallDisconnectCauseChanged(int i, int i2) {
    }

    public void onCallForwardingIndicatorChanged(boolean z) {
    }

    public void onCallStateChanged(int i, String str) {
    }

    public void onCarrierNetworkChange(boolean z) {
    }

    public void onCellInfoChanged(List<CellInfo> list) {
    }

    public void onCellLocationChanged(CellLocation cellLocation) {
    }

    public void onDataActivationStateChanged(int i) {
    }

    public void onDataActivity(int i) {
    }

    public void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataConnectionRealTimeInfo) {
    }

    public void onDataConnectionStateChanged(int i) {
    }

    public void onDataConnectionStateChanged(int i, int i2) {
    }

    public void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
    }

    public void onEmergencyNumberListChanged(Map<Integer, List<EmergencyNumber>> map) {
    }

    public void onImsCallDisconnectCauseChanged(ImsReasonInfo imsReasonInfo) {
    }

    public void onMessageWaitingIndicatorChanged(boolean z) {
    }

    public void onOemHookRawEvent(byte[] bArr) {
    }

    @SystemApi
    public void onOutgoingEmergencyCall(EmergencyNumber emergencyNumber) {
    }

    @SystemApi
    public void onOutgoingEmergencySms(EmergencyNumber emergencyNumber) {
    }

    public void onPhoneCapabilityChanged(PhoneCapability phoneCapability) {
    }

    @SystemApi
    public void onPreciseCallStateChanged(PreciseCallState preciseCallState) {
    }

    public void onPreciseDataConnectionStateChanged(PreciseDataConnectionState preciseDataConnectionState) {
    }

    @SystemApi
    public void onRadioPowerStateChanged(int i) {
    }

    public void onRegistrationFailed(CellIdentity cellIdentity, String str, int i, int i2, int i3) {
    }

    public void onServiceStateChanged(ServiceState serviceState) {
    }

    @Deprecated
    public void onSignalStrengthChanged(int i) {
    }

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
    }

    @SystemApi
    public void onSrvccStateChanged(int i) {
    }

    public void onUserMobileDataStateChanged(boolean z) {
    }

    @SystemApi
    public void onVoiceActivationStateChanged(int i) {
    }

    public PhoneStateListener() {
        this((Integer) null, Looper.myLooper());
    }

    public PhoneStateListener(Looper looper) {
        this((Integer) null, looper);
    }

    public PhoneStateListener(Integer num) {
        this(num, Looper.myLooper());
        if (num == null || VMRuntime.getRuntime().getTargetSdkVersion() < 29) {
            return;
        }
        throw new IllegalArgumentException("PhoneStateListener with subId: " + num + " is not supported, use default constructor");
    }

    public PhoneStateListener(Integer num, Looper looper) {
        this(num, new HandlerExecutor(new Handler(looper)));
        if (num == null || VMRuntime.getRuntime().getTargetSdkVersion() < 29) {
            return;
        }
        throw new IllegalArgumentException("PhoneStateListener with subId: " + num + " is not supported, use default constructor");
    }

    public PhoneStateListener(Executor executor) {
        this((Integer) null, executor);
    }

    private PhoneStateListener(Integer num, Executor executor) {
        if (executor == null) {
            throw new IllegalArgumentException("PhoneStateListener Executor must be non-null");
        }
        this.mSubId = num;
        this.callback = new IPhoneStateListenerStub(this, executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class IPhoneStateListenerStub extends IPhoneStateListener.Stub {
        private Executor mExecutor;
        private WeakReference<PhoneStateListener> mPhoneStateListenerWeakRef;

        IPhoneStateListenerStub(PhoneStateListener phoneStateListener, Executor executor) {
            this.mPhoneStateListenerWeakRef = new WeakReference<>(phoneStateListener);
            this.mExecutor = executor;
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onServiceStateChanged(final ServiceState serviceState) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$uC5syhzl229gIpaK7Jfs__OCJxQ
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onServiceStateChanged$1$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, serviceState);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStateChanged$1$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final ServiceState serviceState) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$nrGqSRBJrc3_EwotCDNwfKeizIo
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onServiceStateChanged(serviceState);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onSignalStrengthChanged(final int i) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$M39is_Zyt8D7Camw2NS4EGTDn-s
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onSignalStrengthChanged$3$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onSignalStrengthChanged$3$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$5J-sdvem6pUpdVwRdm8IbDhvuv8
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onSignalStrengthChanged(i);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onMessageWaitingIndicatorChanged(final boolean z) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$okPCYOx4UxYuvUHlM2iS425QGIg
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onMessageWaitingIndicatorChanged$5$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onMessageWaitingIndicatorChanged$5$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$TqrkuLPlaG_ucU7VbLS4tnf8hG8
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onMessageWaitingIndicatorChanged(z);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallForwardingIndicatorChanged(final boolean z) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$1M3m0i6211i2YjWyTDT7l0bJm3I
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallForwardingIndicatorChanged$7$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onCallForwardingIndicatorChanged$7$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$WYWtWHdkZDxBd9anjoxyZozPWHc
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onCallForwardingIndicatorChanged(z);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCellLocationChanged(CellIdentity cellIdentity) {
            final CellLocation empty = cellIdentity == null ? CellLocation.getEmpty() : cellIdentity.asCellLocation();
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Hbn6-eZxY2p3rjOfStodI04A8E8
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCellLocationChanged$9$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, empty);
                }
            });
        }

        public /* synthetic */ void lambda$onCellLocationChanged$9$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final CellLocation cellLocation) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$2cMrwdqnKBpixpApeIX38rmRLak
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onCellLocationChanged(cellLocation);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallStateChanged(final int i, final String str) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$oDAZqs8paeefe_3k_uRKV5plQW4
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallStateChanged$11$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i, str);
                }
            });
        }

        public /* synthetic */ void lambda$onCallStateChanged$11$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i, final String str) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$6czWSGzxct0CXPVO54T0aq05qls
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onCallStateChanged(i, str);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataConnectionStateChanged(final int i, final int i2) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            if (i == 4 && VMRuntime.getRuntime().getTargetSdkVersion() < 30) {
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$NjMtWvO8dQakD688KRREWiYI4JI
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$onDataConnectionStateChanged$13$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i2);
                    }
                });
            } else {
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$pLr-IfJJu1u_YG6I5LI0iHTuBi0
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$onDataConnectionStateChanged$15$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i, i2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onDataConnectionStateChanged$13$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$VCD7izkh9A_sRz9zMUPYy-TktLo
                @Override // java.lang.Runnable
                public final void run() {
                    PhoneStateListener.IPhoneStateListenerStub.lambda$onDataConnectionStateChanged$12(phoneStateListener, i);
                }
            });
        }

        static /* synthetic */ void lambda$onDataConnectionStateChanged$12(PhoneStateListener phoneStateListener, int i) {
            phoneStateListener.onDataConnectionStateChanged(2, i);
            phoneStateListener.onDataConnectionStateChanged(2);
        }

        public /* synthetic */ void lambda$onDataConnectionStateChanged$15$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i, final int i2) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$2XBMUIj05jt4Xm08XAsE57q5gCc
                @Override // java.lang.Runnable
                public final void run() {
                    PhoneStateListener.IPhoneStateListenerStub.lambda$onDataConnectionStateChanged$14(phoneStateListener, i, i2);
                }
            });
        }

        static /* synthetic */ void lambda$onDataConnectionStateChanged$14(PhoneStateListener phoneStateListener, int i, int i2) {
            phoneStateListener.onDataConnectionStateChanged(i, i2);
            phoneStateListener.onDataConnectionStateChanged(i);
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataActivity(final int i) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$l57DgyMDrONq3sajd_dBE967ClU
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataActivity$17$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onDataActivity$17$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$FBJGFGXoSvidKfm50cEzC3i9rVk
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onDataActivity(i);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onSignalStrengthsChanged(final SignalStrength signalStrength) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$j6NpsS_PE3VHutxIDEmwFHop7Yc
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onSignalStrengthsChanged$19$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, signalStrength);
                }
            });
        }

        public /* synthetic */ void lambda$onSignalStrengthsChanged$19$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final SignalStrength signalStrength) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$lP7_Xy6P82nXGbUQ_ZUY6rZR4bI
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onSignalStrengthsChanged(signalStrength);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCellInfoChanged(final List<CellInfo> list) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$yvQnAlFGg5EWDG2vcA9X-4xnalA
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCellInfoChanged$21$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, list);
                }
            });
        }

        public /* synthetic */ void lambda$onCellInfoChanged$21$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final List list) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Q2A8FgYlU8_D6PD78tThGut_rTc
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onCellInfoChanged(list);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onPreciseCallStateChanged(final PreciseCallState preciseCallState) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$bELzxgwsPigyVKYkAXBO2BjcSm8
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onPreciseCallStateChanged$23$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, preciseCallState);
                }
            });
        }

        public /* synthetic */ void lambda$onPreciseCallStateChanged$23$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final PreciseCallState preciseCallState) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$4NHt5Shg_DHV-T1IxfcQLHP5-j0
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onPreciseCallStateChanged(preciseCallState);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallDisconnectCauseChanged(final int i, final int i2) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$icX71zgNszuMfnDaCmahcqWacFM
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallDisconnectCauseChanged$25$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i, i2);
                }
            });
        }

        public /* synthetic */ void lambda$onCallDisconnectCauseChanged$25$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i, final int i2) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$hxq77a5O_MUfoptHg15ipzFvMkI
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onCallDisconnectCauseChanged(i, i2);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onPreciseDataConnectionStateChanged(final PreciseDataConnectionState preciseDataConnectionState) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$RC2x2ijetA-pQrLa4QakzMBjh_k
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onPreciseDataConnectionStateChanged$27$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, preciseDataConnectionState);
                }
            });
        }

        public /* synthetic */ void lambda$onPreciseDataConnectionStateChanged$27$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final PreciseDataConnectionState preciseDataConnectionState) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$HEcWn-J1WRb0wLERu2qoMIZDfjY
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onPreciseDataConnectionStateChanged(preciseDataConnectionState);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataConnectionRealTimeInfoChanged(final DataConnectionRealTimeInfo dataConnectionRealTimeInfo) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$OfwFKKtcQHRmtv70FCopw6FDAAU
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataConnectionRealTimeInfoChanged$29$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, dataConnectionRealTimeInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onDataConnectionRealTimeInfoChanged$29$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final DataConnectionRealTimeInfo dataConnectionRealTimeInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$IU278K5QbmReF-mbpcNVAvVlhFI
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onDataConnectionRealTimeInfoChanged(dataConnectionRealTimeInfo);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onSrvccStateChanged(final int i) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$nR7W5ox6SCgPxtH9IRcENwKeFI4
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onSrvccStateChanged$31$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onSrvccStateChanged$31$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$ygzOWFRiY4sZQ4WYUPIefqgiGvM
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onSrvccStateChanged(i);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onVoiceActivationStateChanged(final int i) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$5rF2IFj8mrb7uZc0HMKiuCodUn0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onVoiceActivationStateChanged$33$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onVoiceActivationStateChanged$33$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$y-tK7my_uXPo_oQ7AytfnekGEbU
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onVoiceActivationStateChanged(i);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataActivationStateChanged(final int i) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$t2gWJ_jA36kAdNXSmlzw85aU-tM
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataActivationStateChanged$35$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onDataActivationStateChanged$35$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$W65ui1dCCc-JnQa7gon1I7Bz7Sk
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onDataActivationStateChanged(i);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onUserMobileDataStateChanged(final boolean z) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$5uu-05j4ojTh9mEHkN-ynQqQRGM
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onUserMobileDataStateChanged$37$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onUserMobileDataStateChanged$37$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$5Uf5OZWCyPD0lZtySzbYw18FWhU
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onUserMobileDataStateChanged(z);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDisplayInfoChanged(final TelephonyDisplayInfo telephonyDisplayInfo) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Ju-ddK8E8x5tfEZmkvwBIYJDPvE
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDisplayInfoChanged$39$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, telephonyDisplayInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onDisplayInfoChanged$39$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final TelephonyDisplayInfo telephonyDisplayInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$LUeazoo9zMnCFkFubo9xMWZDEO0
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onDisplayInfoChanged(telephonyDisplayInfo);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onOemHookRawEvent(final byte[] bArr) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$DwLKzyC4oFq0Am-zrmIKCBlAkSw
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onOemHookRawEvent$41$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, bArr);
                }
            });
        }

        public /* synthetic */ void lambda$onOemHookRawEvent$41$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final byte[] bArr) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$3c5FK_Juffs31qkHzWgSSJac9TU
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onOemHookRawEvent(bArr);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCarrierNetworkChange(final boolean z) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$SLDsZb_RTXJpIvKJwCENgXrSXcU
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCarrierNetworkChange$43$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onCarrierNetworkChange$43$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$dxRm9dBua84gnuIxdXsCrayRwig
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onCarrierNetworkChange(z);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onEmergencyNumberListChanged(final Map map) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$d9DVwzLraeX80tegF_wEzf_k2FI
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onEmergencyNumberListChanged$45$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, map);
                }
            });
        }

        public /* synthetic */ void lambda$onEmergencyNumberListChanged$45$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final Map map) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$jGj-qFMdpjbsKaUErqJEeOALEGo
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onEmergencyNumberListChanged(map);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onOutgoingEmergencyCall(final EmergencyNumber emergencyNumber) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$BsQWpotqnIoEH_U0akIHaOPMJEw
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onOutgoingEmergencyCall$47$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, emergencyNumber);
                }
            });
        }

        public /* synthetic */ void lambda$onOutgoingEmergencyCall$47$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final EmergencyNumber emergencyNumber) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$xu0QkHLAeED9Ly-ML3fa5OlPURk
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onOutgoingEmergencyCall(emergencyNumber);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onOutgoingEmergencySms(final EmergencyNumber emergencyNumber) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$T_JEoJRE8dPShC5x0Epb3dZudWU
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onOutgoingEmergencySms$49$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, emergencyNumber);
                }
            });
        }

        public /* synthetic */ void lambda$onOutgoingEmergencySms$49$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final EmergencyNumber emergencyNumber) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$85JHkw2A6rMO6fdvXlKO8s3mvuo
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onOutgoingEmergencySms(emergencyNumber);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onPhoneCapabilityChanged(final PhoneCapability phoneCapability) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$wjRj9OdX5wSkxhrYxPCG-Ew6YXQ
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onPhoneCapabilityChanged$51$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, phoneCapability);
                }
            });
        }

        public /* synthetic */ void lambda$onPhoneCapabilityChanged$51$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final PhoneCapability phoneCapability) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$tYwneWpJ9F_F7uty5T42Ut4IQgA
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onPhoneCapabilityChanged(phoneCapability);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onRadioPowerStateChanged(final int i) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$VxFLOQdMp2vINeouS7TeF9r-gG0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onRadioPowerStateChanged$53$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onRadioPowerStateChanged$53$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$1srlma37eoivpgTHDxtCv705NxM
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onRadioPowerStateChanged(i);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallAttributesChanged(final CallAttributes callAttributes) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$XUG0CXbGDJ3aeL69w-T91MxLWmQ
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallAttributesChanged$55$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, callAttributes);
                }
            });
        }

        public /* synthetic */ void lambda$onCallAttributesChanged$55$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final CallAttributes callAttributes) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Up-d_3alwM9q0g0LsqopmPRkRrc
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onCallAttributesChanged(callAttributes);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onActiveDataSubIdChanged(final int i) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$GJ2YJ4ARy5-u2bWutnqrYMAsLYA
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onActiveDataSubIdChanged$57$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onActiveDataSubIdChanged$57$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$xj3Oc59znNki36q4HkPlDthcris
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onActiveDataSubscriptionIdChanged(i);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onImsCallDisconnectCauseChanged(final ImsReasonInfo imsReasonInfo) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$kBtYmLansNh43SPn9TbXXwzfjhU
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onImsCallDisconnectCauseChanged$59$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, imsReasonInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onImsCallDisconnectCauseChanged$59$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final ImsReasonInfo imsReasonInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$McXM4TNoGqMoeW3gLTJl6vK5wTE
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onImsCallDisconnectCauseChanged(imsReasonInfo);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onRegistrationFailed(final CellIdentity cellIdentity, final String str, final int i, final int i2, final int i3) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$GgIjtfA23XjACIgpJdRWSJCTMJY
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onRegistrationFailed$61$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, cellIdentity, str, i, i2, i3);
                }
            });
        }

        public /* synthetic */ void lambda$onRegistrationFailed$61$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final CellIdentity cellIdentity, final String str, final int i, final int i2, final int i3) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$F094bbf2wiAS4Xek655AoA3XPqk
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onRegistrationFailed(cellIdentity, str, i, i2, i3);
                }
            });
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onBarringInfoChanged(final BarringInfo barringInfo) {
            final PhoneStateListener phoneStateListener = this.mPhoneStateListenerWeakRef.get();
            if (phoneStateListener == null) {
                return;
            }
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$jTiEa3GYrDHi81x7zieU4nSnaeQ
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onBarringInfoChanged$63$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, barringInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onBarringInfoChanged$63$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final BarringInfo barringInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$enPCQDzvJfQRZN3T7bDULTM8LbU
                @Override // java.lang.Runnable
                public final void run() {
                    phoneStateListener.onBarringInfoChanged(barringInfo);
                }
            });
        }
    }

    private void log(String str) {
        Rlog.d(LOG_TAG, str);
    }
}
