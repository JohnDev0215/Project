package com.byd.music.utils;

import android.provider.SettingsStringUtil;
import com.blankj.utilcode.util.Utils;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public class StringResource {
    private static final String[] divideChat = {"：", "｜", SettingsStringUtil.DELIMITER, "丨", "|", "—", "！", "!", ",", "，"};

    public static String getString(int i) {
        return Utils.getApp().getString(i);
    }

    public static String replaceAll(String str) {
        return str != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("") : "";
    }

    public static String subName(String str) {
        if (str == null) {
            return "";
        }
        for (String str2 : divideChat) {
            int iIndexOf = str.indexOf(str2);
            if (iIndexOf > 0) {
                return str.substring(0, iIndexOf);
            }
        }
        return str;
    }
}
