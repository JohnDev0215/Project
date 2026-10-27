package com.android.shell;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class BugreportPrefs {
    static int getWarningState(Context context, int i) {
        return context.getSharedPreferences("bugreports", 0).getInt("warning-state", i);
    }

    static void setWarningState(Context context, int i) {
        context.getSharedPreferences("bugreports", 0).edit().putInt("warning-state", i).apply();
    }
}
