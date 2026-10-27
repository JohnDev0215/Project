package com.blankj.utilcode.util;

import com.android.volley.BuildConfig;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: compiled from: ResourceUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class q {
    public static boolean a(String str, String str2) {
        try {
            String[] list = w.a().getAssets().list(str);
            if (list == null || list.length <= 0) {
                return y.G(str2, w.a().getAssets().open(str));
            }
            boolean zA = true;
            for (String str3 : list) {
                zA &= a(str + InternalZipConstants.ZIP_FILE_SEPARATOR + str3, str2 + InternalZipConstants.ZIP_FILE_SEPARATOR + str3);
            }
            return zA;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String b(String str) {
        return c(str, null);
    }

    public static String c(String str, String str2) {
        try {
            byte[] bArrR = y.r(w.a().getAssets().open(str));
            if (bArrR == null) {
                return BuildConfig.FLAVOR;
            }
            if (y.x(str2)) {
                return new String(bArrR);
            }
            try {
                return new String(bArrR, str2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return BuildConfig.FLAVOR;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }
}
