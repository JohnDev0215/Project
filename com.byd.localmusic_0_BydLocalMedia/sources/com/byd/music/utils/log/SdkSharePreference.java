package com.byd.music.utils.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* JADX INFO: loaded from: classes5.dex */
public class SdkSharePreference {
    public static final int DEFAULT_INI_VALUE = -1;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSp;

    public enum SharePreferenceKeyEnum {
    }

    public enum SharePreferenceName {
        locationInfoStorage,
        logInfoStorage
    }

    public SdkSharePreference(Context context, SharePreferenceName sharePreferenceName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName.toString(), 0);
        this.mSp = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
    }

    public void clear() {
        this.mEditor.clear();
    }

    public int getIntValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, int i) {
        return this.mSp.getInt(sharePreferenceKeyEnum.toString(), i);
    }

    public void putIntValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, int i) {
        this.mEditor.putInt(sharePreferenceKeyEnum.toString(), i);
        commit();
    }

    public boolean getBooleanValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, boolean z) {
        return this.mSp.getBoolean(sharePreferenceKeyEnum.toString(), z);
    }

    public void putBooleanValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, boolean z) {
        this.mEditor.putBoolean(sharePreferenceKeyEnum.toString(), z);
        commit();
    }

    public float getFloatValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, float f) {
        return this.mSp.getFloat(sharePreferenceKeyEnum.toString(), f);
    }

    public void putFloatValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, float f) {
        this.mEditor.putFloat(sharePreferenceKeyEnum.toString(), f);
        commit();
    }

    public long getLongValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, long j) {
        return this.mSp.getLong(sharePreferenceKeyEnum.toString(), j);
    }

    public void putLongValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, long j) {
        this.mEditor.putLong(sharePreferenceKeyEnum.toString(), j);
        commit();
    }

    public String getStringValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, String str) {
        return this.mSp.getString(sharePreferenceKeyEnum.toString(), str);
    }

    public void putStringValue(SharePreferenceKeyEnum sharePreferenceKeyEnum, String str) {
        this.mEditor.putString(sharePreferenceKeyEnum.toString(), str);
        commit();
    }

    public void put(SharePreferenceKeyEnum sharePreferenceKeyEnum, String str) {
        this.mEditor.putString(sharePreferenceKeyEnum.toString(), str);
        commit();
    }

    public void remove(SharePreferenceKeyEnum sharePreferenceKeyEnum) {
        this.mEditor.remove(sharePreferenceKeyEnum.toString());
        commit();
    }

    public SharedPreferences.Editor edit() {
        return this.mEditor;
    }

    public SharedPreferences sharedPrefs() {
        return this.mSp;
    }

    public void commit() {
        if (Build.VERSION.SDK_INT >= 9) {
            this.mEditor.apply();
        } else {
            this.mEditor.commit();
        }
    }
}
