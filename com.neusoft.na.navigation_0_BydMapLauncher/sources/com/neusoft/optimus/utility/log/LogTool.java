package com.neusoft.optimus.utility.log;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes2.dex */
public class LogTool {
    private static final String _cBeginTag = " Begin --> ";
    private static final String _cEndTag = " End <---- ";
    private static final String _cFileName = " @%s@ ";
    private static final String _cMsg = " %s ";
    private static String _tag = "unInit";

    public enum eModuleTag {
        Media,
        Audio,
        Video,
        Jazz,
        Image,
        MediaLib,
        Tuner,
        BtPhone,
        BtAudio,
        Setting,
        DVR,
        VR,
        TBoxClient,
        ClearMemory,
        FileMgr,
        EngineerMode,
        MediaPlugins,
        IPKServer,
        Settings,
        PopView,
        BtPlugins,
        RadioPlugin
    }

    public static void init(eModuleTag emoduletag) {
        _tag = emoduletag.name();
    }

    public static void i(String str, String str2) {
        Log.i(_tag, String.format(" @%s@  %s ", str, str2));
    }

    public static void i(String str, String str2, Object... objArr) {
        Log.i(_tag, String.format(" @%s@  %s ", str, String.format(str2, objArr)));
    }

    public static void i_begin(String str, String str2) {
        Log.i(_tag, String.format(" @%s@  Begin -->  %s ", str, str2));
    }

    public static void i_end(String str, String str2) {
        Log.i(_tag, String.format(" @%s@  End <----  %s ", str, str2));
    }

    public static void d(String str, String str2) {
        Log.d(_tag, String.format(" @%s@  %s ", str, str2));
    }

    public static void d(String str, String str2, Object... objArr) {
        Log.d(_tag, String.format(" @%s@  %s ", str, String.format(str2, objArr)));
    }

    public static void d_begin(String str, String str2) {
        Log.d(_tag, String.format(" @%s@  Begin -->  %s ", str, str2));
    }

    public static void d_end(String str, String str2) {
        Log.d(_tag, String.format(" @%s@  End <----  %s ", str, str2));
    }

    public static void v(String str, String str2) {
        Log.v(_tag, String.format(" @%s@  %s ", str, str2));
    }

    public static void v(String str, String str2, Object... objArr) {
        Log.v(_tag, String.format(" @%s@  %s ", str, String.format(str2, objArr)));
    }

    public static void w(String str, String str2) {
        Log.w(_tag, String.format(" @%s@  %s ", str, str2));
    }

    public static void w(String str, String str2, Object... objArr) {
        Log.w(_tag, String.format(" @%s@  %s ", str, String.format(str2, objArr)));
    }

    public static void w(String str, Exception exc) {
        Log.w(_tag, String.format(" @%s@  %s ", str, getStackTrace(exc)));
    }

    public static void e(String str, String str2) {
        Log.e(_tag, String.format(" @%s@  %s ", str, str2));
    }

    public static void e(String str, Exception exc) {
        Log.e(_tag, String.format(" @%s@  %s ", str, getStackTrace(exc)));
    }

    public static void e(String str, String str2, Object... objArr) {
        Log.e(_tag, String.format(" @%s@  %s ", str, String.format(str2, objArr)));
    }

    private static String getStackTrace(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            th.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            printWriter.close();
        }
    }
}
