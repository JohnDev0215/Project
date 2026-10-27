package com.bydux.api.common.tools.save;

import android.content.Context;
import android.content.SharedPreferences;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BydXmlHelper {
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;
    private final String TAG = getClass().getSimpleName();
    private final String FILE_NAME = "bydconfigs";

    public BydXmlHelper(Context context) {
        this.mContext = null;
        this.mPreferences = null;
        this.mEditor = null;
        this.mContext = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("bydconfigs", 4);
        this.mPreferences = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
    }

    public boolean contains(String str) {
        return this.mPreferences.contains(str);
    }

    public String getValue(String str, String str2) {
        return this.mPreferences.getString(str, str2);
    }

    public void putValue(String str, String str2) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + str2);
        this.mEditor.putString(str, str2);
        this.mEditor.commit();
    }

    public int getValue(String str, int i8) {
        return this.mPreferences.getInt(str, i8);
    }

    public float getValue(String str, float f8) {
        return this.mPreferences.getFloat(str, f8);
    }

    public long getValue(String str, long j8) {
        return this.mPreferences.getLong(str, j8);
    }

    public void putValue(String str, int i8) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + i8);
        this.mEditor.putInt(str, i8);
        this.mEditor.commit();
    }

    public boolean getValue(String str, boolean z7) {
        return this.mPreferences.getBoolean(str, z7);
    }

    public void putValue(String str, float f8) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + f8);
        this.mEditor.putFloat(str, f8);
        this.mEditor.commit();
    }

    public BydXmlHelper(Context context, String str) {
        this.mContext = null;
        this.mPreferences = null;
        this.mEditor = null;
        this.mContext = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 4);
        this.mPreferences = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
    }

    public void putValue(String str, long j8) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + j8);
        this.mEditor.putLong(str, j8);
        this.mEditor.commit();
    }

    public void putValue(String str, boolean z7) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + z7);
        this.mEditor.putBoolean(str, z7);
        this.mEditor.commit();
    }
}
