package org.chromium.ui.picker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

/* JADX INFO: loaded from: classes4.dex */
class ChromeDatePickerDialog extends DatePickerDialog {
    private final DatePickerDialog.OnDateSetListener mCallBack;

    public ChromeDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        super(context, onDateSetListener, i, i2, i3);
        this.mCallBack = onDateSetListener;
    }

    @Override // android.app.DatePickerDialog, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i != -1 || this.mCallBack == null) {
            return;
        }
        DatePicker datePicker = getDatePicker();
        datePicker.clearFocus();
        this.mCallBack.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle("");
    }
}
