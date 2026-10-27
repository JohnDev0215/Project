package com.byd.foundation.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public final class SpUtils {
    private static SpUtils instance;
    private final SharedPreferences mSharePreferences;

    private SpUtils(Context context) {
        this.mSharePreferences = Build.VERSION.SDK_INT >= 24 ? context.createDeviceProtectedStorageContext().getSharedPreferences(context.getPackageName(), 0) : context.getSharedPreferences(context.getPackageName(), 0);
    }

    public static SpUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SpUtils.class) {
                if (instance == null) {
                    instance = new SpUtils(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void clear() {
        SharedPreferences sharedPreferences = this.mSharePreferences;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().clear().apply();
    }

    public boolean contains(String str) {
        SharedPreferences sharedPreferences = this.mSharePreferences;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains(str);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mSharePreferences == null ? z : ((Boolean) getParam(str, Boolean.valueOf(z))).booleanValue();
    }

    public int getInt(String str, int i) {
        return this.mSharePreferences == null ? i : ((Integer) getParam(str, Integer.valueOf(i))).intValue();
    }

    public Object getParam(String str, Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        SharedPreferences sharedPreferences = this.mSharePreferences;
        if ("String".equals(simpleName)) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if ("Integer".equals(simpleName)) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if ("Boolean".equals(simpleName)) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if ("Float".equals(simpleName)) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if ("Long".equals(simpleName)) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        return null;
    }

    public String getString(String str, String str2) {
        return this.mSharePreferences == null ? str2 : (String) getParam(str, str2);
    }

    public void remove(String str) {
        SharedPreferences sharedPreferences = this.mSharePreferences;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().remove(str).apply();
    }

    public void saveBoolean(String str, boolean z) {
        if (this.mSharePreferences == null) {
            return;
        }
        setParam(str, Boolean.valueOf(z));
    }

    public void saveInt(String str, int i) {
        if (this.mSharePreferences == null) {
            return;
        }
        setParam(str, Integer.valueOf(i));
    }

    public void saveString(String str, String str2) {
        if (this.mSharePreferences == null) {
            return;
        }
        setParam(str, str2);
    }

    public void setParam(String str, Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        SharedPreferences.Editor editorEdit = this.mSharePreferences.edit();
        if ("String".equals(simpleName)) {
            editorEdit.putString(str, (String) obj);
        } else if ("Integer".equals(simpleName)) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if ("Boolean".equals(simpleName)) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if ("Float".equals(simpleName)) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if ("Long".equals(simpleName)) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        }
        editorEdit.apply();
    }
}
