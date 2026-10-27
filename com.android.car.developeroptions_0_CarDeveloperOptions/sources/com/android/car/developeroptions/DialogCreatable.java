package com.android.car.developeroptions;

import android.app.Dialog;

/* JADX INFO: loaded from: classes.dex */
public interface DialogCreatable {
    int getDialogMetricsCategory(int i);

    Dialog onCreateDialog(int i);
}
