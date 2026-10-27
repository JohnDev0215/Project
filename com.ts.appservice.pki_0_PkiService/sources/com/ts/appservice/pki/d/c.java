package com.ts.appservice.pki.d;

import android.text.TextUtils;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replace("-----BEGIN CERTIFICATE-----", "").replace("-----END CERTIFICATE-----", "").trim();
    }

    public static String b(String str) {
        if (str.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            str = str.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");
        }
        if (str.contains("-----BEGIN PRIVATE KEY-----")) {
            str = str.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        }
        return str.trim();
    }
}
