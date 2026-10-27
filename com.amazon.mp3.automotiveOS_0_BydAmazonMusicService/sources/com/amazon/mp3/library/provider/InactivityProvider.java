package com.amazon.mp3.library.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* JADX INFO: loaded from: classes3.dex */
public final class InactivityProvider {
    private static long mLastActivityTimestamp = -1;

    public static boolean isInactive(Context context) {
        return isInactive(context, 259200000L);
    }

    public static boolean isInactive(Context context, long inactivityThreshold) {
        if (mLastActivityTimestamp == -1) {
            readSharedPreferences(context);
        }
        return inactivityThreshold + mLastActivityTimestamp < System.currentTimeMillis();
    }

    private static void readSharedPreferences(Context context) {
        mLastActivityTimestamp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getLong("com.amazon.mp3_key_last_activity", 0L);
    }

    public static void updateActivity(Context context) {
        update(context, System.currentTimeMillis());
    }

    public static void reset(Context context) {
        update(context, -1L);
    }

    private static void update(Context context, long lastActivityTimestamp) {
        mLastActivityTimestamp = lastActivityTimestamp;
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        editorEdit.putLong("com.amazon.mp3_key_last_activity", mLastActivityTimestamp);
        editorEdit.apply();
    }
}
