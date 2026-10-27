package com.android.car.developeroptions.nfc;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.CustomDialogPreferenceCompat;

/* JADX INFO: loaded from: classes.dex */
public class NfcPaymentPreference extends CustomDialogPreferenceCompat {
    private Listener mListener;

    interface Listener {
        void onBindViewHolder(PreferenceViewHolder preferenceViewHolder);

        void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener);
    }

    public NfcPaymentPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public NfcPaymentPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public NfcPaymentPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    void initialize(Listener listener) {
        this.mListener = listener;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onBindViewHolder(preferenceViewHolder);
        }
    }

    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        super.onPrepareDialogBuilder(builder, onClickListener);
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onPrepareDialogBuilder(builder, onClickListener);
        }
    }
}
