package com.bydux.api.common.tools.save;

import android.content.Context;
import android.content.SharedPreferences;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
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

    public int getValue(String str, int i2) {
        return this.mPreferences.getInt(str, i2);
    }

    public float getValue(String str, float f2) {
        return this.mPreferences.getFloat(str, f2);
    }

    public long getValue(String str, long j) {
        return this.mPreferences.getLong(str, j);
    }

    public void putValue(String str, int i2) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + i2);
        this.mEditor.putInt(str, i2);
        this.mEditor.commit();
    }

    public boolean getValue(String str, boolean z) {
        return this.mPreferences.getBoolean(str, z);
    }

    public void putValue(String str, float f2) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + f2);
        this.mEditor.putFloat(str, f2);
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

    public void putValue(String str, long j) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + j);
        this.mEditor.putLong(str, j);
        this.mEditor.commit();
    }

    public void putValue(String str, boolean z) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + z);
        this.mEditor.putBoolean(str, z);
        this.mEditor.commit();
    }
}
