package com.android.inputmethod.latin.utils;

import android.content.Context;
import com.android.inputmethod.latin.DictionaryFacilitator;
import com.android.inputmethod.latin.settings.SettingsValues;

/* JADX INFO: loaded from: classes2.dex */
public class StatsUtilsManager {
    private static final StatsUtilsManager sInstance = new StatsUtilsManager();
    private static StatsUtilsManager sTestInstance = null;

    public void onCreate(final Context context, final DictionaryFacilitator dictionaryFacilitator) {
    }

    public void onDestroy(final Context context) {
    }

    public void onFinishInputView() {
    }

    public void onLoadSettings(final Context context, final SettingsValues settingsValues) {
    }

    public void onStartInputView() {
    }

    public static StatsUtilsManager getInstance() {
        StatsUtilsManager statsUtilsManager = sTestInstance;
        return statsUtilsManager != null ? statsUtilsManager : sInstance;
    }

    public static void setTestInstance(final StatsUtilsManager testInstance) {
        sTestInstance = testInstance;
    }
}
