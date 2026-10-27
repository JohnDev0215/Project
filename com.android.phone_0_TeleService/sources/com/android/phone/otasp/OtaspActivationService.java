package com.android.phone.otasp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.ServiceStateTracker;
import com.android.phone.PhoneGlobals;
import com.android.phone.PhoneUtils;

/* JADX INFO: loaded from: classes.dex */
public class OtaspActivationService extends Service {
    private Phone mPhone;
    private static final String TAG = OtaspActivationService.class.getSimpleName();
    private static int sOtaspCallRetries = 0;
    private static String sIccId = null;
    private boolean mIsOtaspCallCommitted = false;
    private Handler mHandler = new Handler() { // from class: com.android.phone.otasp.OtaspActivationService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                OtaspActivationService.logd("OTASP_CALL_STATE_CHANGED");
                OtaspActivationService.this.onOtaspCallStateChanged();
                return;
            }
            if (i == 1) {
                OtaspActivationService.logd("EVENT_CDMA_OTASP_CALL_RETRY");
                OtaspActivationService.this.onStartOtaspCall();
                return;
            }
            if (i == 2) {
                OtaspActivationService.logd("OTASP_ACTIVATION_STATUS_UPDATE_EVENT");
                OtaspActivationService.this.onCdmaProvisionStatusUpdate((AsyncResult) message.obj);
                return;
            }
            if (i == 3) {
                OtaspActivationService.logd("EVENT_SERVICE_STATE_CHANGED");
                OtaspActivationService.this.onStartOtaspCall();
            } else {
                if (i == 4) {
                    OtaspActivationService.logd("EVENT_START_OTASP_CALL");
                    OtaspActivationService.this.onStartOtaspCall();
                    return;
                }
                OtaspActivationService.loge("invalid msg: " + message.what + " not handled.");
            }
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 3;
    }

    @Override // android.app.Service
    public void onCreate() {
        logd("otasp service onCreate");
        Phone phone = PhoneGlobals.getPhone();
        this.mPhone = phone;
        ServiceStateTracker serviceStateTracker = phone.getServiceStateTracker();
        if (serviceStateTracker != null && serviceStateTracker.getOtasp() != 2) {
            logd("OTASP is not needed.");
            return;
        }
        String str = sIccId;
        if (str == null || !str.equals(this.mPhone.getIccSerialNumber())) {
            sIccId = this.mPhone.getIccSerialNumber();
            sOtaspCallRetries = 0;
        }
        sOtaspCallRetries++;
        logd("OTASP call tried " + sOtaspCallRetries + " times");
        if (sOtaspCallRetries > 3) {
            logd("OTASP call exceeds max retries => activation failed");
            updateActivationState(this, false);
            onComplete();
            return;
        }
        this.mHandler.sendEmptyMessage(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartOtaspCall() {
        unregisterAll();
        if (this.mPhone.getServiceState().getState() != 0) {
            loge("OTASP call failure, wait for network available.");
            this.mPhone.registerForServiceStateChanged(this.mHandler, 3, (Object) null);
            return;
        }
        this.mPhone.registerForCdmaOtaStatusChange(this.mHandler, 2, (Object) null);
        this.mPhone.registerForPreciseCallStateChanged(this.mHandler, 0, (Object) null);
        logd("startNonInteractiveOtasp: placing call to '*22899'...");
        int iPlaceOtaspCall = PhoneUtils.placeOtaspCall(this, PhoneGlobals.getPhone(), "*22899");
        if (iPlaceOtaspCall == 0) {
            logd("  ==> success return from placeCall(): callStatus = " + iPlaceOtaspCall);
            return;
        }
        loge(" ==> failure return from placeCall(): callStatus = " + iPlaceOtaspCall);
        this.mHandler.sendEmptyMessageDelayed(1, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCdmaProvisionStatusUpdate(AsyncResult asyncResult) {
        int[] iArr = (int[]) asyncResult.result;
        logd("onCdmaProvisionStatusUpdate: " + iArr[0]);
        if (8 == iArr[0]) {
            this.mIsOtaspCallCommitted = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOtaspCallStateChanged() {
        logd("onOtaspCallStateChanged: " + this.mPhone.getState());
        if (this.mPhone.getState().equals(PhoneConstants.State.IDLE)) {
            if (this.mIsOtaspCallCommitted) {
                logd("Otasp activation succeed");
                updateActivationState(this, true);
            } else {
                logd("Otasp activation failed");
                updateActivationState(this, false);
            }
            onComplete();
        }
    }

    private void onComplete() {
        logd("otasp service onComplete");
        unregisterAll();
        stopSelf();
    }

    private void unregisterAll() {
        this.mPhone.unregisterForCdmaOtaStatusChange(this.mHandler);
        this.mPhone.unregisterForSubscriptionInfoReady(this.mHandler);
        this.mPhone.unregisterForServiceStateChanged(this.mHandler);
        this.mPhone.unregisterForPreciseCallStateChanged(this.mHandler);
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public static void updateActivationState(Context context, boolean z) {
        TelephonyManager telephonyManagerFrom = TelephonyManager.from(context);
        int i = z ? 2 : 3;
        int defaultSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
        telephonyManagerFrom.setVoiceActivationState(defaultSubscriptionId, i);
        telephonyManagerFrom.setDataActivationState(defaultSubscriptionId, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logd(String str) {
        Log.d(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loge(String str) {
        Log.e(TAG, str);
    }
}
