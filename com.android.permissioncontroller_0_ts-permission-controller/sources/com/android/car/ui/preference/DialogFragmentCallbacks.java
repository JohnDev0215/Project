package com.android.car.ui.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

/* JADX INFO: loaded from: classes7.dex */
public interface DialogFragmentCallbacks extends DialogInterface.OnClickListener {
    default void onPrepareDialogBuilder(AlertDialog.Builder builder) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    default void onClick(DialogInterface dialog, int which) {
    }

    default void onDialogClosed(boolean positiveResult) {
    }

    default void onBindDialogView(View view) {
    }
}
