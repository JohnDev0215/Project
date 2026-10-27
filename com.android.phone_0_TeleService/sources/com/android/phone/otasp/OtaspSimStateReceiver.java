package com.android.phone.otasp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import com.android.phone.PhoneGlobals;

/* JADX INFO: loaded from: classes.dex */
public class OtaspSimStateReceiver extends BroadcastReceiver {
    private static final String TAG = OtaspSimStateReceiver.class.getSimpleName();
    private Context mContext;
    private Handler mOtaspHandler = new Handler() { // from class: com.android.phone.otasp.OtaspSimStateReceiver.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object obj;
            if (message.what == 1) {
                AsyncResult asyncResult = (AsyncResult) message.obj;
                if (asyncResult.exception == null && (obj = asyncResult.result) != null) {
                    int iIntValue = ((Integer) obj).intValue();
                    OtaspSimStateReceiver.logd("EVENT_OTASP_CHANGED: otaspMode=" + iIntValue);
                    if (iIntValue == 2) {
                        OtaspSimStateReceiver.logd("otasp activation required, start otaspActivationService");
                        OtaspSimStateReceiver.this.mContext.startService(new Intent(OtaspSimStateReceiver.this.mContext, (Class<?>) OtaspActivationService.class));
                        return;
                    } else {
                        if (iIntValue == 3) {
                            OtaspActivationService.updateActivationState(OtaspSimStateReceiver.this.mContext, true);
                            return;
                        }
                        return;
                    }
                }
                OtaspSimStateReceiver.logd("EVENT_OTASP_CHANGED: exception=" + asyncResult.exception);
                return;
            }
            super.handleMessage(message);
        }
    };

    private static boolean isCarrierSupported() {
        Context context = PhoneGlobals.getPhone().getContext();
        if (context != null) {
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
            PersistableBundle config = carrierConfigManager != null ? carrierConfigManager.getConfig() : null;
            if (config != null && config.getBoolean("use_otasp_for_provisioning_bool")) {
                return true;
            }
        }
        logd("otasp activation not needed: no supported carrier");
        return false;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        if ("android.telephony.action.CARRIER_CONFIG_CHANGED".equals(intent.getAction())) {
            logd("Received intent: " + intent.getAction());
            if (PhoneGlobals.getPhone().getIccRecordsLoaded() && isCarrierSupported()) {
                registerOtaspChangedHandler();
            }
        }
    }

    private void registerOtaspChangedHandler() {
        PhoneGlobals.getPhone().registerForOtaspChange(this.mOtaspHandler, 1, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logd(String str) {
        Log.d(TAG, str);
    }
}
