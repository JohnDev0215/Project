package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import androidx.appcompat.app.AlertDialog;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.android.settingslib.notification.ZenDurationDialog;

/* JADX INFO: loaded from: classes.dex */
public class ZenDurationDialogPreference extends CustomDialogPreferenceCompat {
    public ZenDurationDialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public ZenDurationDialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ZenDurationDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        super.onPrepareDialogBuilder(builder, onClickListener);
        new ZenDurationDialog(getContext()).setupDialog(builder);
    }
}
