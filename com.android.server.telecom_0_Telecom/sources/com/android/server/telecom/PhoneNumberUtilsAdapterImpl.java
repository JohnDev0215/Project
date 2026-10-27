package com.android.server.telecom;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;

/* JADX INFO: loaded from: classes.dex */
public class PhoneNumberUtilsAdapterImpl implements PhoneNumberUtilsAdapter {
    @Override // com.android.server.telecom.PhoneNumberUtilsAdapter
    public boolean isUriNumber(String str) {
        return PhoneNumberUtils.isUriNumber(str);
    }

    @Override // com.android.server.telecom.PhoneNumberUtilsAdapter
    public boolean isSamePhoneNumber(String str, String str2) {
        return PhoneNumberUtils.compare(str, str2);
    }

    @Override // com.android.server.telecom.PhoneNumberUtilsAdapter
    public String getNumberFromIntent(Intent intent, Context context) {
        return PhoneNumberUtils.getNumberFromIntent(intent, context);
    }

    @Override // com.android.server.telecom.PhoneNumberUtilsAdapter
    public String convertKeypadLettersToDigits(String str) {
        return PhoneNumberUtils.convertKeypadLettersToDigits(str);
    }

    @Override // com.android.server.telecom.PhoneNumberUtilsAdapter
    public String stripSeparators(String str) {
        return PhoneNumberUtils.stripSeparators(str);
    }
}
