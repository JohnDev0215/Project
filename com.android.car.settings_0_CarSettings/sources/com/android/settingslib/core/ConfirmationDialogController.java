package com.android.settingslib.core;

import androidx.preference.Preference;

/* JADX INFO: loaded from: classes6.dex */
public interface ConfirmationDialogController {
    void dismissConfirmationDialog();

    String getPreferenceKey();

    boolean isConfirmationDialogShowing();

    void showConfirmationDialog(Preference preference);
}
