package com.byd.bluetoothcall.f;

import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: PhoneUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: b */
    private static Pattern f3278b = Pattern.compile("^(010|02\\d|0[3-9]\\d{2})?\\d{6,8}$");

    /* JADX INFO: renamed from: a */
    private static Pattern f3277a = Pattern.compile("^0?1[345678]\\d{9}$");

    /* JADX INFO: renamed from: c */
    private static Pattern f3279c = Pattern.compile("^(010|02\\d|0[3-9]\\d{2})\\d{6,8}$");

    public static String a(String str) {
        String strI = f.i(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        String number = (TextUtils.isEmpty(strI) || strI.equals("unknown")) ? PhoneNumberUtils.formatNumber(str, Locale.getDefault().getCountry()) : PhoneNumberUtils.formatNumber(str, d.a());
        return TextUtils.isEmpty(number) ? str : number;
    }
}
