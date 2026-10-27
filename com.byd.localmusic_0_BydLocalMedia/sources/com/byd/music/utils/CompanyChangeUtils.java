package com.byd.music.utils;

import android.media.AudioSystem;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes5.dex */
public class CompanyChangeUtils {
    public static String get(int i, int i2) {
        int iAbs = Math.abs(i);
        if (iAbs > 100000000) {
            String str = new DecimalFormat("#.0").format(((double) iAbs) / 1.0E8d);
            String[] strArrSplit = str.split("\\.");
            if (strArrSplit[1].equals(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS)) {
                return strArrSplit[0] + "亿";
            }
            return str + "亿";
        }
        if (iAbs > 10000) {
            String str2 = new DecimalFormat("#.0").format(((double) iAbs) / 10000.0d);
            String[] strArrSplit2 = str2.split("\\.");
            if (strArrSplit2[1].equals(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS)) {
                return strArrSplit2[0] + "万";
            }
            return str2 + "万";
        }
        return String.valueOf(iAbs);
    }
}
