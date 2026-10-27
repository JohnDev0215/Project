package com.android.car.ui.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import androidx.annotation.NonNull;
import spi.i3;

/* JADX INFO: loaded from: classes2.dex */
public class SeekbarPreferenceDialogFragment extends PreferenceDialogFragment {
    @Override // com.android.car.ui.preference.CarUiDialogFragment
    public final void c(@NonNull AlertDialog.Builder builder) {
        ((i3) getPreference()).a();
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    public final void onBindDialogView(@NonNull View view) {
        super.onBindDialogView(view);
        ((i3) getPreference()).onBindDialogView(view);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.g = i;
        ((i3) getPreference()).onClick(dialogInterface, i);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    public final void onDialogClosed(boolean z) {
        ((i3) getPreference()).onDialogClosed(z);
    }
}
