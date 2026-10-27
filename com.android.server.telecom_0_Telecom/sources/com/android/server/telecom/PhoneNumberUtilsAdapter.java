package com.android.server.telecom;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public interface PhoneNumberUtilsAdapter {
    String convertKeypadLettersToDigits(String str);

    String getNumberFromIntent(Intent intent, Context context);

    boolean isSamePhoneNumber(String str, String str2);

    boolean isUriNumber(String str);

    String stripSeparators(String str);
}
