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

    public BydXmlHelper(Context context, String fileName) {
        this.mContext = null;
        this.mPreferences = null;
        this.mEditor = null;
        this.mContext = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, 4);
        this.mPreferences = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
    }

    public boolean contains(String field) {
        return this.mPreferences.contains(field);
    }

    public void putValue(String field, String value) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + field + ", value=" + value);
        this.mEditor.putString(field, value);
        this.mEditor.commit();
    }

    public void putValue(String field, int value) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + field + ", value=" + value);
        this.mEditor.putInt(field, value);
        this.mEditor.commit();
    }

    public void putValue(String field, float value) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + field + ", value=" + value);
        this.mEditor.putFloat(field, value);
        this.mEditor.commit();
    }

    public void putValue(String field, long value) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + field + ", value=" + value);
        this.mEditor.putLong(field, value);
        this.mEditor.commit();
    }

    public void putValue(String field, boolean value) {
        BydLogHelper.logV(this.TAG, "putValue", "field=" + field + ", value=" + value);
        this.mEditor.putBoolean(field, value);
        this.mEditor.commit();
    }

    public String getValue(String field, String dfValue) {
        return this.mPreferences.getString(field, dfValue);
    }

    public int getValue(String field, int dfValue) {
        return this.mPreferences.getInt(field, dfValue);
    }

    public float getValue(String field, float dfValue) {
        return this.mPreferences.getFloat(field, dfValue);
    }

    public long getValue(String field, long dfValue) {
        return this.mPreferences.getLong(field, dfValue);
    }

    public boolean getValue(String field, boolean dfValue) {
        return this.mPreferences.getBoolean(field, dfValue);
    }
}
