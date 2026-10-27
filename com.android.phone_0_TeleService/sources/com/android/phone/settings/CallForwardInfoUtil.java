package com.android.phone.settings;

import android.os.Message;
import com.android.internal.telephony.CallForwardInfo;
import com.android.internal.telephony.Phone;

/* JADX INFO: loaded from: classes.dex */
public class CallForwardInfoUtil {
    public static CallForwardInfo infoForReason(CallForwardInfo[] callForwardInfoArr, int i) {
        if (callForwardInfoArr == null) {
            return null;
        }
        for (int i2 = 0; i2 < callForwardInfoArr.length; i2++) {
            if (callForwardInfoArr[i2].reason == i) {
                return callForwardInfoArr[i2];
            }
        }
        return null;
    }

    public static boolean isUpdateRequired(CallForwardInfo callForwardInfo, CallForwardInfo callForwardInfo2) {
        return (callForwardInfo != null && callForwardInfo2.status == 0 && callForwardInfo.status == 0) ? false : true;
    }

    public static void setCallForwardingOption(Phone phone, CallForwardInfo callForwardInfo, Message message) {
        phone.setCallForwardingOption(callForwardInfo.status == 1 ? 3 : 0, callForwardInfo.reason, callForwardInfo.number, callForwardInfo.serviceClass, callForwardInfo.timeSeconds, message);
    }

    public static CallForwardInfo getCallForwardInfo(CallForwardInfo[] callForwardInfoArr, int i) {
        CallForwardInfo callForwardInfo;
        if (callForwardInfoArr != null) {
            for (int i2 = 0; i2 < callForwardInfoArr.length; i2++) {
                if (isServiceClassVoice(callForwardInfoArr[i2])) {
                    callForwardInfo = callForwardInfoArr[i2];
                    break;
                }
            }
            callForwardInfo = null;
        } else {
            callForwardInfo = null;
        }
        if (callForwardInfo == null) {
            CallForwardInfo callForwardInfo2 = new CallForwardInfo();
            callForwardInfo2.status = 0;
            callForwardInfo2.reason = i;
            callForwardInfo2.serviceClass = 1;
            return callForwardInfo2;
        }
        if (hasForwardingNumber(callForwardInfo)) {
            return callForwardInfo;
        }
        callForwardInfo.status = 0;
        return callForwardInfo;
    }

    private static boolean isServiceClassVoice(CallForwardInfo callForwardInfo) {
        return (callForwardInfo.serviceClass & 1) != 0;
    }

    private static boolean hasForwardingNumber(CallForwardInfo callForwardInfo) {
        String str = callForwardInfo.number;
        return str != null && str.length() > 0;
    }
}
