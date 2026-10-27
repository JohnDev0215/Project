package defpackage;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import org.chromium.content.browser.picker.d;

/* JADX INFO: compiled from: DatePickerDialogCompat.java */
/* JADX INFO: loaded from: classes3.dex */
public final class t60 extends DatePickerDialog {
    public final DatePickerDialog.OnDateSetListener e;

    public t60(Context context, d.c cVar, int i, int i2, int i3) {
        super(context, cVar, i, i2, i3);
        this.e = cVar;
    }

    @Override // android.app.DatePickerDialog, android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        DatePickerDialog.OnDateSetListener onDateSetListener;
        if (i != -1 || (onDateSetListener = this.e) == null) {
            return;
        }
        DatePicker datePicker = getDatePicker();
        datePicker.clearFocus();
        onDateSetListener.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle("");
    }
}
