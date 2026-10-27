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
        if (num != null && VMRuntime.getRuntime().getTargetSdkVersion() >= 29) {
            throw new IllegalArgumentException("PhoneStateListener with subId: " + num + " is not supported, use default constructor");
        }
    }

    public PhoneStateListener(Integer num, Looper looper) {
        this(num, new HandlerExecutor(new Handler(looper)));
        if (num != null && VMRuntime.getRuntime().getTargetSdkVersion() >= 29) {
            throw new IllegalArgumentException("PhoneStateListener with subId: " + num + " is not supported, use default constructor");
        }
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$mALujKUw1b6f9z_33GJTbnB8WyA
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onServiceStateChanged$1$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, serviceState);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStateChanged$1$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final ServiceState serviceState) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$E8GjQnU-1eos8Rc3s5suJwHXYEE
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$b-WmNSGKZWjnzLLnFr-z4J5gY_k
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onSignalStrengthChanged$3$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onSignalStrengthChanged$3$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$l87TrQ48pJH6q_rvEdiryvHU-mI
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Ha2k1WV51VjhlYKq21E6ELCbCrM
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onMessageWaitingIndicatorChanged$5$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onMessageWaitingIndicatorChanged$5$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$HQGjWsTST5_gvh_G1bjCLND1-N4
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$bhK6zO9V2KnwyJZHwwWFExo4zg4
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallForwardingIndicatorChanged$7$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onCallForwardingIndicatorChanged$7$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$-lF0-bAQ1a6ZFcG3Qmw9_pNLXyY
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$RCQCCla428QSj3QngXloxTeoFt0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCellLocationChanged$9$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, empty);
                }
            });
        }

        public /* synthetic */ void lambda$onCellLocationChanged$9$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final CellLocation cellLocation) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$TAcL0V1-9173G17_i2V4xtjXCyg
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Gkz3vi_HBisLX9ShlgMeKImlszs
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallStateChanged$11$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i, str);
                }
            });
        }

        public /* synthetic */ void lambda$onCallStateChanged$11$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i, final String str) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$GHco6Z6WP0os1892ZVkaczMV8Mo
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
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Ls6uuYCUU1dWYvKLwyPYdUVJjcg
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$onDataConnectionStateChanged$13$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i2);
                    }
                });
            } else {
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$NEvcRwgmA-3X--D0LhNbCfDHMJg
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$onDataConnectionStateChanged$15$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i, i2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onDataConnectionStateChanged$13$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$aclTDMAoAy7ZRL9a0LUpF9aoA14
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
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$PPvGOc0fHWwnuXM6hf_14ywxqfg
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$FyYQJ7esqwsM2Fx-uDJM3XluJMM
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataActivity$17$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onDataActivity$17$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$SkH7zeCNDbrkgi-wKCqhRA-JvUc
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$jRuM8cGgcCKOU5CJfy5xNGbSA5U
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onSignalStrengthsChanged$19$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, signalStrength);
                }
            });
        }

        public /* synthetic */ void lambda$onSignalStrengthsChanged$19$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final SignalStrength signalStrength) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$p1xP4kT5RFM2Tk3Cw-OND-jgtIU
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$jDTPBnbn_k4huJzmgOX6tlOyRbY
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCellInfoChanged$21$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, list);
                }
            });
        }

        public /* synthetic */ void lambda$onCellInfoChanged$21$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final List list) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$5tGs-sYwnZx9h3i01pXznP79rxA
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$TV6_-aWbJTOeSWu4dLGFOIdy3d8
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onPreciseCallStateChanged$23$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, preciseCallState);
                }
            });
        }

        public /* synthetic */ void lambda$onPreciseCallStateChanged$23$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final PreciseCallState preciseCallState) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Rj-NsZVcTB_OiSUwAFnHPNbD974
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$hcMdxJsBPV6Xpc74gi_XlXYaS84
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallDisconnectCauseChanged$25$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i, i2);
                }
            });
        }

        public /* synthetic */ void lambda$onCallDisconnectCauseChanged$25$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i, final int i2) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$q4lh8oRDj801mang0qj_IDjqI5k
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$-_wTXLk9Umh6wDn_okY8RcBujJM
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onPreciseDataConnectionStateChanged$27$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, preciseDataConnectionState);
                }
            });
        }

        public /* synthetic */ void lambda$onPreciseDataConnectionStateChanged$27$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final PreciseDataConnectionState preciseDataConnectionState) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$5X9HifhGuzsmbqbIbE7LTB1PxYA
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$DdZ4NQh2yF8WtLYaKKTBl5xq9ro
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataConnectionRealTimeInfoChanged$29$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, dataConnectionRealTimeInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onDataConnectionRealTimeInfoChanged$29$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final DataConnectionRealTimeInfo dataConnectionRealTimeInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Wqpy9EDrp7KsNeR6QJbNImYQ1Xs
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$GEb6oJ3jMoeo6tUXBFE9m502fv8
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onSrvccStateChanged$31$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onSrvccStateChanged$31$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$EJ4RQKUAUb0-aDNZ6h9OHWFUdFc
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$BK3L_J-CcLxuMJ4G5RRGsBrO2RQ
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onVoiceActivationStateChanged$33$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onVoiceActivationStateChanged$33$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$PXDFJA4rBNRWx-nV44jNhK4d8yM
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$zRQyJVdeEtw5Fa3J8e4TuECJn3M
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataActivationStateChanged$35$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onDataActivationStateChanged$35$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$dSxJvzrhNXx7ZYpanrFcDAsWE_8
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Ds-sWjB8kmAm7A972Hvet-S3AIA
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onUserMobileDataStateChanged$37$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onUserMobileDataStateChanged$37$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$VJG72sWKH_Iu3l0HKwOPcF-eTHs
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$8CkB6u0LBoTVcuxSirXJUlwGiVs
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDisplayInfoChanged$39$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, telephonyDisplayInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onDisplayInfoChanged$39$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final TelephonyDisplayInfo telephonyDisplayInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$dGRjZtmxyWkqOCBfDlPB4aDHIIA
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$PNeuWJODqj_q7AaBo4t6wqcsVy0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onOemHookRawEvent$41$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, bArr);
                }
            });
        }

        public /* synthetic */ void lambda$onOemHookRawEvent$41$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final byte[] bArr) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$1Q8JbOrpOGFCYcCTXua5RNdvpQs
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$pA76bAD3TphxmlEooGmviVwvXlc
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCarrierNetworkChange$43$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, z);
                }
            });
        }

        public /* synthetic */ void lambda$onCarrierNetworkChange$43$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$MT39xM_t6-rFsmBGkVSf9YZcn6g
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$gErSgmknoWIPUuCfE6C8x0rkWFQ
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onEmergencyNumberListChanged$45$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, map);
                }
            });
        }

        public /* synthetic */ void lambda$onEmergencyNumberListChanged$45$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final Map map) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$FYTbwcymPdWzAcqmK5tlOqILRaA
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$OrKg88JIHYs9mGSDK9Cd2okS6nk
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onOutgoingEmergencyCall$47$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, emergencyNumber);
                }
            });
        }

        public /* synthetic */ void lambda$onOutgoingEmergencyCall$47$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final EmergencyNumber emergencyNumber) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$jbx1rCJ6YObT8OrFeeNRXS3u9BI
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$4_ClpJ6ln6LdgPSKDEPKPMZSZPw
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onOutgoingEmergencySms$49$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, emergencyNumber);
                }
            });
        }

        public /* synthetic */ void lambda$onOutgoingEmergencySms$49$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final EmergencyNumber emergencyNumber) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$ykrDxRafPEEHIKYXjSVIPxq3xRw
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$AbNiMfzOaeN7ruT4DKbdX1kRrso
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onPhoneCapabilityChanged$51$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, phoneCapability);
                }
            });
        }

        public /* synthetic */ void lambda$onPhoneCapabilityChanged$51$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final PhoneCapability phoneCapability) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$8OCgYgMeFy9-Kqom6s32fGtfLJU
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$Mq9A3WOJ1xTLdKilcPJi5kl52wI
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onRadioPowerStateChanged$53$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onRadioPowerStateChanged$53$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$zWlYPCxWv3egHoNe0Vnye8JGuFs
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$NOKrAtUpfqgskvLpiy6MpIJyjgk
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onCallAttributesChanged$55$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, callAttributes);
                }
            });
        }

        public /* synthetic */ void lambda$onCallAttributesChanged$55$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final CallAttributes callAttributes) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$sZyO7rsFCjULcVPzwFHOxvdThSU
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$JokEwFc_P00-RRacBbYeHBkMnSU
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onActiveDataSubIdChanged$57$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, i);
                }
            });
        }

        public /* synthetic */ void lambda$onActiveDataSubIdChanged$57$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final int i) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$TC_VgbnRo32z0xj_URlD971mloo
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$CjRuUkjJHG_OJXPqJIY9XehNeGU
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onImsCallDisconnectCauseChanged$59$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, imsReasonInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onImsCallDisconnectCauseChanged$59$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final ImsReasonInfo imsReasonInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$XQInBYnc-i5oW_bk_uThMd755G0
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$EOHmpEP2DqhIgm-mg-F06dZ5rKI
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onRegistrationFailed$61$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, cellIdentity, str, i, i2, i3);
                }
            });
        }

        public /* synthetic */ void lambda$onRegistrationFailed$61$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final CellIdentity cellIdentity, final String str, final int i, final int i2, final int i3) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$yrixRvYg4pk6w-CpCC5AuDLq0q8
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
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$_ZZbaBMWdyOMW8B29_4sIcs7mwk
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onBarringInfoChanged$63$PhoneStateListener$IPhoneStateListenerStub(phoneStateListener, barringInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onBarringInfoChanged$63$PhoneStateListener$IPhoneStateListenerStub(final PhoneStateListener phoneStateListener, final BarringInfo barringInfo) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$PhoneStateListener$IPhoneStateListenerStub$SMJbaX9Bf9mwDv40ohWg0f1xNcI
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
