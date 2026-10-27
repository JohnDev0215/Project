package com.neusoft.optimus.utility.filename;

import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
public class FileNameSplit {
    private static final int cMaxBytes = 255;
    public static final String cTag = "FileNameSplit";

    public static String getCutFileName(String str) {
        String extension = getExtension(str);
        return getCutFileNameByEx(getFileNameNoEx(str), extension) + extension;
    }

    public static String getCutFileNameNoEx(String str) {
        return getCutFileNameByEx(getFileNameNoEx(str), getExtension(str));
    }

    public static String getCutFileName(String str, String str2) {
        String extension = getExtension(str2);
        return getCutFileNameByEx(str, extension) + extension;
    }

    public static String getCutFileNameNoEx(String str, String str2) {
        return getCutFileNameByEx(str, getExtension(str2));
    }

    public static String getCutFileNameByEx(String str, String str2) {
        int length;
        if (str2 == null) {
            length = 0;
        } else {
            try {
                length = str2.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return str;
            }
        }
        return (str == null || 255 >= str.getBytes("UTF-8").length + length) ? str : cutFileName(str, 255 - length);
    }

    private static String cutFileName(String str, int i) {
        while (str.getBytes("UTF-8").length > i) {
            try {
                str = str.substring(0, str.length() - 1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    private static String getExtension(String str) {
        int iLastIndexOf = str == null ? -1 : str.lastIndexOf(".");
        return iLastIndexOf == -1 ? "" : str.substring(iLastIndexOf);
    }

    public static String getFileNameNoEx(String str) {
        int iLastIndexOf;
        return (str == null || str.length() <= 0 || (iLastIndexOf = str.lastIndexOf(46)) <= -1 || iLastIndexOf >= str.length()) ? str : str.substring(0, iLastIndexOf);
    }
}
