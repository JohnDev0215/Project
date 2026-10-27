package com.byd.vrassistant.utils;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
public class Base64Utils {
    public static String getBase64(String str) {
        if (str != null) {
            try {
                return new String(Base64.encode(str.getBytes("utf-8"), 2), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getFromBase64(String str) {
        if (str != null) {
            try {
                return new String(Base64.decode(str, 2), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
