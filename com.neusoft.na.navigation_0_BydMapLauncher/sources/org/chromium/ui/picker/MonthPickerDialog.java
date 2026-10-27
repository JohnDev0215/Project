package org.chromium.ui.picker;

import android.content.Context;
import org.chromium.ui.R;
import org.chromium.ui.picker.TwoFieldDatePickerDialog;

/* JADX INFO: loaded from: classes4.dex */
public class MonthPickerDialog extends TwoFieldDatePickerDialog {
    public MonthPickerDialog(Context context, TwoFieldDatePickerDialog.OnValueSetListener onValueSetListener, int i, int i2, double d, double d2) {
        super(context, onValueSetListener, i, i2, d, d2);
        setTitle(R.string.month_picker_dialog_title);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePickerDialog
    protected TwoFieldDatePicker createPicker(Context context, double d, double d2) {
        return new MonthPicker(context, d, d2);
    }

    public MonthPicker getMonthPicker() {
        return (MonthPicker) this.mPicker;
    }
}
