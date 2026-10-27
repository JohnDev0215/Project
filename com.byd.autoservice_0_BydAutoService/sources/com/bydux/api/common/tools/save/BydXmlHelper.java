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

    public int getValue(String str, int i3) {
        return this.mPreferences.getInt(str, i3);
    }

    public float getValue(String str, float f3) {
        return this.mPreferences.getFloat(str, f3);
    }

    public long getValue(String str, long j3) {
        return this.mPreferences.getLong(str, j3);
    }

    public void putValue(String str, int i3) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + i3);
        this.mEditor.putInt(str, i3);
        this.mEditor.commit();
    }

    public boolean getValue(String str, boolean z3) {
        return this.mPreferences.getBoolean(str, z3);
    }

    public void putValue(String str, float f3) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + f3);
        this.mEditor.putFloat(str, f3);
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

    public void putValue(String str, long j3) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + j3);
        this.mEditor.putLong(str, j3);
        this.mEditor.commit();
    }

    public void putValue(String str, boolean z3) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + str + ", value=" + z3);
        this.mEditor.putBoolean(str, z3);
        this.mEditor.commit();
    }
}
