package com.byd.data.common.i;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SpUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static int a(String str) {
        Context contextF = com.byd.data.common.server.b.e().f();
        if (contextF == null) {
            return -1;
        }
        return contextF.getSharedPreferences("file_name_save", 0).getInt(str, -1);
    }

    public static String b(String str) {
        Context contextF = com.byd.data.common.server.b.e().f();
        return contextF == null ? "" : contextF.getSharedPreferences("file_name_save", 0).getString(str, "");
    }

    private static SharedPreferences.Editor c(Context context) {
        if (context != null) {
            return context.getSharedPreferences("file_name_save", 0).edit();
        }
        return null;
    }

    public static void d(String str, int i2) {
        SharedPreferences.Editor editorC = c(com.byd.data.common.server.b.e().f());
        if (editorC != null) {
            editorC.putInt(str, i2);
            editorC.commit();
        }
    }

    public static void e(String str, String str2) {
        SharedPreferences.Editor editorC = c(com.byd.data.common.server.b.e().f());
        if (editorC != null) {
            editorC.putString(str, str2);
            editorC.commit();
        }
    }

    public static void f(String str) {
        SharedPreferences.Editor editorC = c(com.byd.data.common.server.b.e().f());
        if (editorC != null) {
            editorC.remove(str);
            editorC.apply();
        }
    }
}
