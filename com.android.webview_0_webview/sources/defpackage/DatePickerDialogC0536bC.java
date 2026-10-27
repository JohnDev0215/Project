package defpackage;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

/* JADX INFO: renamed from: bC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DatePickerDialogC0536bC extends DatePickerDialog {
    public final DatePickerDialog.OnDateSetListener s;

    public DatePickerDialogC0536bC(Context context, DatePickerDialog.OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        super(context, onDateSetListener, i, i2, i3);
        this.s = onDateSetListener;
    }

    @Override // android.app.DatePickerDialog, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i != -1 || this.s == null) {
            return;
        }
        DatePicker datePicker = getDatePicker();
        datePicker.clearFocus();
        this.s.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle("");
    }
}
