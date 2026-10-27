package com.byd.bluetoothcall.f;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.text.TextUtils;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

/* JADX INFO: compiled from: PhoneNumber_Util.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a */
    private static PhoneNumberUtil f3275a = PhoneNumberUtil.k();

    /* JADX INFO: renamed from: b */
    private static com.google.i18n.phonenumbers.h.a f3276b = com.google.i18n.phonenumbers.h.a.d();

    public static String a() {
        String strI = f.i(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        return (TextUtils.isEmpty(strI) || strI.equals("unknown")) ? "CN" : a.a(strI);
    }

    public static String b(Context context, String str) {
        return (TextUtils.isEmpty(str) || context == null) ? "" : c(context, str.replace(" ", ""));
    }

    public static String c(Context context, String str) {
        try {
            String strB = com.google.i18n.phonenumbers.h.a.d().b(PhoneNumberUtil.k().J(str, a()), context.getResources().getConfiguration().getLocales().get(0));
            String strReplace = !TextUtils.isEmpty(strB) ? strB.replace("省", "  ").replace("市", "") : "";
            return strReplace.equals("Turkey") ? "Türkiye" : strReplace;
        } catch (NumberParseException unused) {
            return "";
        }
    }
}
