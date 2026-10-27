package com.android.phone;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.telephony.CallManager;
import com.android.internal.telephony.PhoneInternalInterface;
import com.android.internal.telephony.SubscriptionController;
import com.android.internal.telephony.cdma.CdmaInformationRecords;
import com.android.internal.telephony.cdma.SignalToneUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CallNotifier extends Handler {
    private static final boolean DBG = false;
    private static CallNotifier sInstance;
    private PhoneGlobals mApplication;
    private AudioManager mAudioManager;
    private BluetoothHeadset mBluetoothHeadset;
    private CallManager mCM;
    private ToneGenerator mSignalInfoToneGenerator;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;
    private Map<Integer, CallNotifierPhoneStateListener> mPhoneStateListeners = new ArrayMap();
    private Map<Integer, Boolean> mCFIStatus = new ArrayMap();
    private Map<Integer, Boolean> mMWIStatus = new ArrayMap();
    private boolean mVoicePrivacyState = false;
    private BluetoothProfile.ServiceListener mBluetoothProfileServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.phone.CallNotifier.3
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            CallNotifier.this.mBluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            CallNotifier.this.mBluetoothHeadset = null;
        }
    };

    static CallNotifier init(PhoneGlobals phoneGlobals) {
        CallNotifier callNotifier;
        synchronized (CallNotifier.class) {
            if (sInstance == null) {
                sInstance = new CallNotifier(phoneGlobals);
            } else {
                Log.wtf("CallNotifier", "init() called multiple times!  sInstance = " + sInstance);
            }
            callNotifier = sInstance;
        }
        return callNotifier;
    }

    private CallNotifier(PhoneGlobals phoneGlobals) {
        this.mApplication = phoneGlobals;
        this.mCM = phoneGlobals.mCM;
        this.mAudioManager = (AudioManager) phoneGlobals.getSystemService("audio");
        this.mTelephonyManager = (TelephonyManager) this.mApplication.getSystemService("phone");
        this.mSubscriptionManager = (SubscriptionManager) this.mApplication.getSystemService("telephony_subscription_service");
        registerForNotifications();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.getProfileProxy(this.mApplication.getApplicationContext(), this.mBluetoothProfileServiceListener, 1);
        }
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.phone.CallNotifier.1
            @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
            public void onSubscriptionsChanged() {
                CallNotifier.this.updatePhoneStateListeners(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSignalInfoToneGenerator() {
        if (this.mSignalInfoToneGenerator == null) {
            try {
                this.mSignalInfoToneGenerator = new ToneGenerator(0, 80);
                Log.d("CallNotifier", "CallNotifier: mSignalInfoToneGenerator created when toneplay");
                return;
            } catch (RuntimeException e) {
                Log.w("CallNotifier", "CallNotifier: Exception caught while creating mSignalInfoToneGenerator: " + e);
                this.mSignalInfoToneGenerator = null;
                return;
            }
        }
        Log.d("CallNotifier", "mSignalInfoToneGenerator created already, hence skipping");
    }

    private void registerForNotifications() {
        this.mCM.registerForDisconnect(this, 3, (Object) null);
        this.mCM.registerForDisplayInfo(this, 6, (Object) null);
        this.mCM.registerForSignalInfo(this, 7, (Object) null);
        this.mCM.registerForInCallVoicePrivacyOn(this, 9, (Object) null);
        this.mCM.registerForInCallVoicePrivacyOff(this, 10, (Object) null);
        this.mCM.registerForSuppServiceFailed(this, 14, (Object) null);
        this.mCM.registerForTtyModeReceived(this, 15, (Object) null);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (DBG) {
            Log.d("CallNotifier", "handleMessage(" + message.what + ")");
        }
        int i = message.what;
        if (i == 3) {
            if (DBG) {
                log("DISCONNECT");
            }
            stopSignalInfoTone();
            return;
        }
        if (i == 22) {
            if (DBG) {
                log("Received Display Info notification done event ...");
            }
            PhoneDisplayMessage.dismissMessage();
            return;
        }
        if (i == 6) {
            if (DBG) {
                log("Received PHONE_STATE_DISPLAYINFO event");
            }
            onDisplayInfo((AsyncResult) message.obj);
            return;
        }
        if (i == 7) {
            if (DBG) {
                log("Received PHONE_STATE_SIGNALINFO event");
            }
            onSignalInfo((AsyncResult) message.obj);
            return;
        }
        if (i == 9) {
            if (DBG) {
                log("PHONE_ENHANCED_VP_ON...");
            }
            if (this.mVoicePrivacyState) {
                return;
            }
            new InCallTonePlayer(5).start();
            this.mVoicePrivacyState = true;
            return;
        }
        if (i == 10) {
            if (DBG) {
                log("PHONE_ENHANCED_VP_OFF...");
            }
            if (this.mVoicePrivacyState) {
                new InCallTonePlayer(5).start();
                this.mVoicePrivacyState = false;
                return;
            }
            return;
        }
        if (i == 14) {
            if (DBG) {
                log("PHONE_SUPP_SERVICE_FAILED...");
            }
            onSuppServiceFailed((AsyncResult) message.obj);
        } else {
            if (i != 15) {
                return;
            }
            if (DBG) {
                log("Received PHONE_TTY_MODE_RECEIVED event");
            }
            onTtyModeReceived((AsyncResult) message.obj);
        }
    }

    void updateCallNotifierRegistrationsAfterRadioTechnologyChange() {
        if (DBG) {
            Log.d("CallNotifier", "updateCallNotifierRegistrationsAfterRadioTechnologyChange...");
        }
        createSignalInfoToneGenerator();
    }

    private class InCallTonePlayer extends Thread {
        private int mState = 0;
        private int mToneId;

        InCallTonePlayer(int i) {
            this.mToneId = i;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(1:SW:4)(1:30)|14|29|31|96|32|(0)(0)|(0)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00ca, code lost:
        
            r11 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00cb, code lost:
        
            android.util.Log.w("CallNotifier", "InCallTonePlayer: Exception caught while creating ToneGenerator: " + r11);
            r13 = null;
         */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00b9 A[Catch: RuntimeException -> 0x00ca, TryCatch #2 {RuntimeException -> 0x00ca, blocks: (B:32:0x00b1, B:34:0x00b9, B:38:0x00c4), top: B:96:0x00b1 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x019c A[Catch: all -> 0x01a3, TryCatch #0 {, blocks: (B:75:0x0170, B:77:0x0174, B:79:0x017c, B:84:0x0199, B:82:0x0181, B:85:0x019c, B:86:0x01a1), top: B:92:0x0170, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:91:0x01a6 A[ORIG_RETURN, RETURN] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 448
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.phone.CallNotifier.InCallTonePlayer.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBluetoothAudioOn() {
        return this.mBluetoothHeadset.getConnectedDevices().size() > 0;
    }

    private void onDisplayInfo(AsyncResult asyncResult) {
        CdmaInformationRecords.CdmaDisplayInfoRec cdmaDisplayInfoRec = (CdmaInformationRecords.CdmaDisplayInfoRec) asyncResult.result;
        if (cdmaDisplayInfoRec != null) {
            String str = cdmaDisplayInfoRec.alpha;
            if (DBG) {
                log("onDisplayInfo: displayInfo=" + str);
            }
            PhoneDisplayMessage.displayNetworkMessage(this.mApplication, str);
            sendEmptyMessageDelayed(22, 3000L);
        }
    }

    private void onSuppServiceFailed(AsyncResult asyncResult) {
        String string;
        Object obj = asyncResult.result;
        if (obj == PhoneInternalInterface.SuppService.CONFERENCE) {
            if (DBG) {
                log("onSuppServiceFailed: displaying merge failure message");
            }
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_conference);
        } else if (obj == PhoneInternalInterface.SuppService.RESUME) {
            if (DBG) {
                log("onSuppServiceFailed: displaying resume failure message");
            }
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_resume);
        } else if (obj == PhoneInternalInterface.SuppService.HOLD) {
            if (DBG) {
                log("onSuppServiceFailed: displaying hold failure message");
            }
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_hold);
        } else if (obj == PhoneInternalInterface.SuppService.TRANSFER) {
            if (DBG) {
                log("onSuppServiceFailed: displaying transfer failure message");
            }
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_transfer);
        } else if (obj == PhoneInternalInterface.SuppService.SEPARATE) {
            if (DBG) {
                log("onSuppServiceFailed: displaying separate failure message");
            }
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_separate);
        } else if (obj == PhoneInternalInterface.SuppService.SWITCH) {
            if (DBG) {
                log("onSuppServiceFailed: displaying switch failure message");
            }
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_switch);
        } else if (obj == PhoneInternalInterface.SuppService.REJECT) {
            if (DBG) {
                log("onSuppServiceFailed: displaying reject failure message");
            }
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_reject);
        } else if (obj == PhoneInternalInterface.SuppService.HANGUP) {
            string = this.mApplication.getResources().getString(R.string.incall_error_supp_service_hangup);
        } else {
            if (DBG) {
                log("onSuppServiceFailed: unknown failure");
                return;
            }
            return;
        }
        PhoneDisplayMessage.displayErrorMessage(this.mApplication, string);
        sendEmptyMessageDelayed(22, 3000L);
    }

    public void updatePhoneStateListeners(boolean z) {
        updatePhoneStateListeners(z, 2, -1);
    }

    public void updatePhoneStateListeners(boolean z, int i, int i2) {
        int i3;
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionController.getInstance().getActiveSubscriptionInfoList(this.mApplication.getOpPackageName(), this.mApplication.getAttributionTag());
        ArrayList arrayList = new ArrayList(this.mPhoneStateListeners.keySet());
        Collections.sort(arrayList, new Comparator<Integer>(this) { // from class: com.android.phone.CallNotifier.2
            @Override // java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return SubscriptionController.getInstance().getSlotIndex(num.intValue()) > SubscriptionController.getInstance().getSlotIndex(num2.intValue()) ? 0 : -1;
            }
        });
        int size = arrayList.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            int iIntValue = ((Integer) arrayList.get(size)).intValue();
            if (activeSubscriptionInfoList == null || !containsSubId(activeSubscriptionInfoList, iIntValue)) {
                Log.d("CallNotifier", "updatePhoneStateListeners: Hide the outstanding notifications.");
                this.mApplication.notificationMgr.updateMwi(iIntValue, false);
                this.mApplication.notificationMgr.updateCfi(iIntValue, false);
                this.mTelephonyManager.listen(this.mPhoneStateListeners.get(Integer.valueOf(iIntValue)), 0);
                this.mPhoneStateListeners.remove(Integer.valueOf(iIntValue));
            } else {
                Log.d("CallNotifier", "updatePhoneStateListeners: update CF notifications.");
                if (this.mCFIStatus.containsKey(Integer.valueOf(iIntValue))) {
                    if (i == 1 && iIntValue == i2) {
                        this.mApplication.notificationMgr.updateCfi(iIntValue, this.mCFIStatus.get(Integer.valueOf(iIntValue)).booleanValue(), z);
                    } else {
                        this.mApplication.notificationMgr.updateCfi(iIntValue, this.mCFIStatus.get(Integer.valueOf(iIntValue)).booleanValue(), true);
                    }
                }
                if (this.mMWIStatus.containsKey(Integer.valueOf(iIntValue))) {
                    if (i == 0 && iIntValue == i2) {
                        this.mApplication.notificationMgr.updateMwi(iIntValue, this.mMWIStatus.get(Integer.valueOf(iIntValue)).booleanValue(), z);
                    } else {
                        this.mApplication.notificationMgr.updateMwi(iIntValue, this.mMWIStatus.get(Integer.valueOf(iIntValue)).booleanValue(), true);
                    }
                }
            }
            size--;
        }
        if (activeSubscriptionInfoList == null) {
            return;
        }
        for (i3 = 0; i3 < activeSubscriptionInfoList.size(); i3++) {
            int subscriptionId = activeSubscriptionInfoList.get(i3).getSubscriptionId();
            if (!this.mPhoneStateListeners.containsKey(Integer.valueOf(subscriptionId))) {
                CallNotifierPhoneStateListener callNotifierPhoneStateListener = new CallNotifierPhoneStateListener(subscriptionId);
                this.mTelephonyManager.createForSubscriptionId(subscriptionId).listen(callNotifierPhoneStateListener, 12);
                this.mPhoneStateListeners.put(Integer.valueOf(subscriptionId), callNotifierPhoneStateListener);
            }
        }
    }

    private boolean containsSubId(List<SubscriptionInfo> list, int i) {
        if (list == null) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).getSubscriptionId() == i) {
                return true;
            }
        }
        return false;
    }

    private void onTtyModeReceived(AsyncResult asyncResult) {
        if (DBG) {
            log("TtyModeReceived: displaying notification message");
        }
        int i = 0;
        int iIntValue = ((Integer) asyncResult.result).intValue();
        if (iIntValue == 0) {
            i = android.R.string.kg_login_account_recovery_hint;
        } else if (iIntValue == 1) {
            i = android.R.string.kg_invalid_sim_pin_hint;
        } else if (iIntValue == 2) {
            i = android.R.string.kg_invalid_sim_puk_hint;
        } else if (iIntValue != 3) {
            Log.e("CallNotifier", "Unsupported TTY mode: " + asyncResult.result);
        } else {
            i = android.R.string.kg_login_checking_password;
        }
        if (i != 0) {
            PhoneGlobals phoneGlobals = this.mApplication;
            PhoneDisplayMessage.displayNetworkMessage(phoneGlobals, phoneGlobals.getResources().getString(i));
            sendEmptyMessageDelayed(22, 3000L);
        }
    }

    private class SignalInfoTonePlayer extends Thread {
        private int mToneId;

        SignalInfoTonePlayer(int i) {
            this.mToneId = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            CallNotifier.this.log("SignalInfoTonePlayer.run(toneId = " + this.mToneId + ")...");
            CallNotifier.this.createSignalInfoToneGenerator();
            if (CallNotifier.this.mSignalInfoToneGenerator != null) {
                CallNotifier.this.mSignalInfoToneGenerator.stopTone();
                CallNotifier.this.mSignalInfoToneGenerator.startTone(this.mToneId);
            }
        }
    }

    private void onSignalInfo(AsyncResult asyncResult) {
        if (!PhoneGlobals.sVoiceCapable) {
            Log.w("CallNotifier", "Got onSignalInfo() on non-voice-capable device! Ignoring...");
            return;
        }
        if (PhoneUtils.isRealIncomingCall(this.mCM.getFirstActiveRingingCall().getState())) {
            stopSignalInfoTone();
            return;
        }
        CdmaInformationRecords.CdmaSignalInfoRec cdmaSignalInfoRec = (CdmaInformationRecords.CdmaSignalInfoRec) asyncResult.result;
        if (cdmaSignalInfoRec != null) {
            boolean z = cdmaSignalInfoRec.isPresent;
            if (DBG) {
                log("onSignalInfo: isPresent=" + z);
            }
            if (z) {
                int i = cdmaSignalInfoRec.signalType;
                int i2 = cdmaSignalInfoRec.alertPitch;
                int i3 = cdmaSignalInfoRec.signal;
                if (DBG) {
                    log("onSignalInfo: uSignalType=" + i + ", uAlertPitch=" + i2 + ", uSignal=" + i3);
                }
                new SignalInfoTonePlayer(SignalToneUtil.getAudioToneFromSignalInfo(i, i2, i3)).start();
            }
        }
    }

    void stopSignalInfoTone() {
        if (DBG) {
            log("stopSignalInfoTone: Stopping SignalInfo tone player");
        }
        new SignalInfoTonePlayer(98).start();
    }

    private class CallNotifierPhoneStateListener extends PhoneStateListener {
        private final int mSubId;

        CallNotifierPhoneStateListener(int i) {
            this.mSubId = i;
        }

        @Override // android.telephony.PhoneStateListener
        public void onMessageWaitingIndicatorChanged(boolean z) {
            CallNotifier.this.mMWIStatus.put(Integer.valueOf(this.mSubId), Boolean.valueOf(z));
            CallNotifier.this.updatePhoneStateListeners(false, 0, this.mSubId);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallForwardingIndicatorChanged(boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append("onCallForwardingIndicatorChanged(): subId=");
            sb.append(this.mSubId);
            sb.append(", visible=");
            sb.append(z ? "Y" : "N");
            Log.i("CallNotifier", sb.toString());
            CallNotifier.this.mCFIStatus.put(Integer.valueOf(this.mSubId), Boolean.valueOf(z));
            CallNotifier.this.updatePhoneStateListeners(false, 1, this.mSubId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d("CallNotifier", str);
    }
}
