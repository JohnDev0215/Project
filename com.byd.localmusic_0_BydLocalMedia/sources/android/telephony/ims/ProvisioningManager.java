package android.telephony.ims;

import android.annotation.SystemApi;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.ims.aidl.IImsConfigCallback;
import com.android.internal.telephony.ITelephony;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public class ProvisioningManager {
    public static final int KEY_1X_EPDG_TIMER_SEC = 64;
    public static final int KEY_1X_THRESHOLD = 59;
    public static final int KEY_AMR_BANDWIDTH_EFFICIENT_PAYLOAD_TYPE = 50;
    public static final int KEY_AMR_CODEC_MODE_SET_VALUES = 0;
    public static final int KEY_AMR_DEFAULT_ENCODING_MODE = 53;
    public static final int KEY_AMR_OCTET_ALIGNED_PAYLOAD_TYPE = 49;
    public static final int KEY_AMR_WB_BANDWIDTH_EFFICIENT_PAYLOAD_TYPE = 48;
    public static final int KEY_AMR_WB_CODEC_MODE_SET_VALUES = 1;
    public static final int KEY_AMR_WB_OCTET_ALIGNED_PAYLOAD_TYPE = 47;
    public static final int KEY_DTMF_NB_PAYLOAD_TYPE = 52;
    public static final int KEY_DTMF_WB_PAYLOAD_TYPE = 51;
    public static final int KEY_EAB_PROVISIONING_STATUS = 25;
    public static final int KEY_ENABLE_SILENT_REDIAL = 6;
    public static final int KEY_LOCAL_BREAKOUT_PCSCF_ADDRESS = 31;
    public static final int KEY_LTE_EPDG_TIMER_SEC = 62;
    public static final int KEY_LTE_THRESHOLD_1 = 56;
    public static final int KEY_LTE_THRESHOLD_2 = 57;
    public static final int KEY_LTE_THRESHOLD_3 = 58;
    public static final int KEY_MINIMUM_SIP_SESSION_EXPIRATION_TIMER_SEC = 3;
    public static final int KEY_MOBILE_DATA_ENABLED = 29;
    public static final int KEY_MULTIENDPOINT_ENABLED = 65;
    public static final int KEY_RCS_AVAILABILITY_CACHE_EXPIRATION_SEC = 19;
    public static final int KEY_RCS_CAPABILITIES_CACHE_EXPIRATION_SEC = 18;
    public static final int KEY_RCS_CAPABILITIES_POLL_INTERVAL_SEC = 20;
    public static final int KEY_RCS_CAPABILITY_DISCOVERY_ENABLED = 17;
    public static final int KEY_RCS_CAPABILITY_POLL_LIST_SUB_EXP_SEC = 23;
    public static final int KEY_RCS_MAX_NUM_ENTRIES_IN_RCL = 22;
    public static final int KEY_RCS_PUBLISH_OFFLINE_AVAILABILITY_TIMER_SEC = 16;
    public static final int KEY_RCS_PUBLISH_SOURCE_THROTTLE_MS = 21;
    public static final int KEY_RCS_PUBLISH_TIMER_SEC = 15;
    public static final int KEY_REGISTRATION_DOMAIN_NAME = 12;
    public static final int KEY_REGISTRATION_RETRY_BASE_TIME_SEC = 33;
    public static final int KEY_REGISTRATION_RETRY_MAX_TIME_SEC = 34;
    public static final int KEY_RTP_SPEECH_END_PORT = 36;
    public static final int KEY_RTP_SPEECH_START_PORT = 35;
    public static final int KEY_RTT_ENABLED = 66;
    public static final int KEY_SIP_ACK_RECEIPT_WAIT_TIME_MS = 43;
    public static final int KEY_SIP_ACK_RETRANSMIT_WAIT_TIME_MS = 44;
    public static final int KEY_SIP_INVITE_ACK_WAIT_TIME_MS = 38;
    public static final int KEY_SIP_INVITE_CANCELLATION_TIMER_MS = 4;
    public static final int KEY_SIP_INVITE_REQUEST_TRANSMIT_INTERVAL_MS = 37;
    public static final int KEY_SIP_INVITE_RESPONSE_RETRANSMIT_INTERVAL_MS = 42;
    public static final int KEY_SIP_INVITE_RESPONSE_RETRANSMIT_WAIT_TIME_MS = 39;
    public static final int KEY_SIP_KEEP_ALIVE_ENABLED = 32;
    public static final int KEY_SIP_NON_INVITE_REQUEST_RETRANSMISSION_WAIT_TIME_MS = 45;
    public static final int KEY_SIP_NON_INVITE_REQUEST_RETRANSMIT_INTERVAL_MS = 40;
    public static final int KEY_SIP_NON_INVITE_RESPONSE_RETRANSMISSION_WAIT_TIME_MS = 46;
    public static final int KEY_SIP_NON_INVITE_TRANSACTION_TIMEOUT_TIMER_MS = 41;
    public static final int KEY_SIP_SESSION_TIMER_SEC = 2;
    public static final int KEY_SMS_FORMAT = 13;
    public static final int KEY_SMS_OVER_IP_ENABLED = 14;
    public static final int KEY_SMS_PUBLIC_SERVICE_IDENTITY = 54;
    public static final int KEY_T1_TIMER_VALUE_MS = 7;
    public static final int KEY_T2_TIMER_VALUE_MS = 8;
    public static final int KEY_TF_TIMER_VALUE_MS = 9;
    public static final int KEY_TRANSITION_TO_LTE_DELAY_MS = 5;
    public static final int KEY_USE_GZIP_FOR_LIST_SUBSCRIPTION = 24;
    public static final int KEY_VIDEO_QUALITY = 55;
    public static final int KEY_VOICE_OVER_WIFI_ENABLED_OVERRIDE = 28;
    public static final int KEY_VOICE_OVER_WIFI_MODE_OVERRIDE = 27;
    public static final int KEY_VOICE_OVER_WIFI_ROAMING_ENABLED_OVERRIDE = 26;
    public static final int KEY_VOLTE_PROVISIONING_STATUS = 10;
    public static final int KEY_VOLTE_USER_OPT_IN_STATUS = 30;
    public static final int KEY_VT_PROVISIONING_STATUS = 11;
    public static final int KEY_WIFI_EPDG_TIMER_SEC = 63;
    public static final int KEY_WIFI_THRESHOLD_A = 60;
    public static final int KEY_WIFI_THRESHOLD_B = 61;
    public static final int PROVISIONING_RESULT_UNKNOWN = -1;
    public static final int PROVISIONING_VALUE_DISABLED = 0;
    public static final int PROVISIONING_VALUE_ENABLED = 1;
    public static final int SMS_FORMAT_3GPP = 1;
    public static final int SMS_FORMAT_3GPP2 = 0;
    public static final String STRING_QUERY_RESULT_ERROR_GENERIC = "STRING_QUERY_RESULT_ERROR_GENERIC";
    public static final String STRING_QUERY_RESULT_ERROR_NOT_READY = "STRING_QUERY_RESULT_ERROR_NOT_READY";
    public static final int VIDEO_QUALITY_HIGH = 1;
    public static final int VIDEO_QUALITY_LOW = 0;
    private int mSubId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface StringResultError {
    }

    public static class Callback {
        private final CallbackBinder mBinder = new CallbackBinder();

        public void onProvisioningIntChanged(int i, int i2) {
        }

        public void onProvisioningStringChanged(int i, String str) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        static class CallbackBinder extends IImsConfigCallback.Stub {
            private Executor mExecutor;
            private final Callback mLocalConfigurationCallback;

            private CallbackBinder(Callback callback) {
                this.mLocalConfigurationCallback = callback;
            }

            @Override // android.telephony.ims.aidl.IImsConfigCallback
            public final void onIntConfigChanged(final int i, final int i2) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$ProvisioningManager$Callback$CallbackBinder$R_8jXQuOM7aV7dIwYBzcWwV-YpM
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onIntConfigChanged$0$ProvisioningManager$Callback$CallbackBinder(i, i2);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onIntConfigChanged$0$ProvisioningManager$Callback$CallbackBinder(int i, int i2) {
                this.mLocalConfigurationCallback.onProvisioningIntChanged(i, i2);
            }

            @Override // android.telephony.ims.aidl.IImsConfigCallback
            public final void onStringConfigChanged(final int i, final String str) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$ProvisioningManager$Callback$CallbackBinder$Jpca2nAZetlBE8jSLFKlsbgUVeI
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onStringConfigChanged$1$ProvisioningManager$Callback$CallbackBinder(i, str);
                        }
                    });
                } finally {
                    restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$onStringConfigChanged$1$ProvisioningManager$Callback$CallbackBinder(int i, String str) {
                this.mLocalConfigurationCallback.onProvisioningStringChanged(i, str);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setExecutor(Executor executor) {
                this.mExecutor = executor;
            }
        }

        public final IImsConfigCallback getBinder() {
            return this.mBinder;
        }

        public void setExecutor(Executor executor) {
            this.mBinder.setExecutor(executor);
        }
    }

    public static ProvisioningManager createForSubscriptionId(int i) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid subscription ID");
        }
        return new ProvisioningManager(i);
    }

    private ProvisioningManager(int i) {
        this.mSubId = i;
    }

    public void registerProvisioningChangedCallback(Executor executor, Callback callback) throws ImsException {
        callback.setExecutor(executor);
        try {
            getITelephony().registerImsProvisioningChangedCallback(this.mSubId, callback.getBinder());
        } catch (RemoteException e) {
            e = e;
            throw new ImsException(e.getMessage(), 1);
        } catch (ServiceSpecificException e2) {
            throw new ImsException(e2.getMessage(), e2.errorCode);
        } catch (IllegalStateException e3) {
            e = e3;
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public void unregisterProvisioningChangedCallback(Callback callback) {
        try {
            getITelephony().unregisterImsProvisioningChangedCallback(this.mSubId, callback.getBinder());
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public int getProvisioningIntValue(int i) {
        try {
            return getITelephony().getImsProvisioningInt(this.mSubId, i);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public String getProvisioningStringValue(int i) {
        try {
            return getITelephony().getImsProvisioningString(this.mSubId, i);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public int setProvisioningIntValue(int i, int i2) {
        try {
            return getITelephony().setImsProvisioningInt(this.mSubId, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public int setProvisioningStringValue(int i, String str) {
        try {
            return getITelephony().setImsProvisioningString(this.mSubId, i, str);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public void setProvisioningStatusForCapability(int i, int i2, boolean z) {
        try {
            getITelephony().setImsProvisioningStatusForCapability(this.mSubId, i, i2, z);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public boolean getProvisioningStatusForCapability(int i, int i2) {
        try {
            return getITelephony().getImsProvisioningStatusForCapability(this.mSubId, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public boolean getRcsProvisioningStatusForCapability(int i) {
        try {
            return getITelephony().getRcsProvisioningStatusForCapability(this.mSubId, i);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public void setRcsProvisioningStatusForCapability(int i, boolean z) {
        try {
            getITelephony().setRcsProvisioningStatusForCapability(this.mSubId, i, z);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    public void notifyRcsAutoConfigurationReceived(byte[] bArr, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("Must include a non-null config XML file.");
        }
        try {
            getITelephony().notifyRcsAutoConfigurationReceived(this.mSubId, bArr, z);
        } catch (RemoteException e) {
            throw e.rethrowAsRuntimeException();
        }
    }

    private static ITelephony getITelephony() {
        ITelephony iTelephonyAsInterface = ITelephony.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().get());
        if (iTelephonyAsInterface != null) {
            return iTelephonyAsInterface;
        }
        throw new RuntimeException("Could not find Telephony Service.");
    }
}
