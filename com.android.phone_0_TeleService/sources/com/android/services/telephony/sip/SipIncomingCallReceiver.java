package com.android.services.telephony.sip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SipIncomingCallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            log("SipIncomingCallReceiver called with no action");
        } else if (isRunningInSystemUser() && SipUtil.isVoipSupported(context) && action.equals("android.net.sip.action.SIP_INCOMING_CALL")) {
            takeCall(context, intent);
        }
    }

    private void takeCall(Context context, Intent intent) {
        try {
            PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("com.android.services.telephony.sip.phone_account");
            if (phoneAccountHandle != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("com.android.services.telephony.sip.incoming_call_intent", intent);
                TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
                PhoneAccount phoneAccount = telecomManager.getPhoneAccount(phoneAccountHandle);
                if (phoneAccount != null && phoneAccount.isEnabled()) {
                    telecomManager.addNewIncomingCall(phoneAccountHandle, bundle);
                } else {
                    log("takeCall, PhoneAccount is disabled. Not accepting incoming call...");
                }
            }
        } catch (ClassCastException unused) {
            log("takeCall, Bad account handle detected. Bailing!");
        }
    }

    private boolean isRunningInSystemUser() {
        return UserHandle.myUserId() == UserHandle.SYSTEM.getIdentifier();
    }

    private static void log(String str) {
        Log.d("SIP", "[SipIncomingCallReceiver] " + str);
    }
}
