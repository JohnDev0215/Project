package org.chromium.ui.picker;

import android.content.Context;
import org.chromium.ui.R;
import org.chromium.ui.picker.TwoFieldDatePickerDialog;

/* JADX INFO: loaded from: classes4.dex */
public class WeekPickerDialog extends TwoFieldDatePickerDialog {
    public WeekPickerDialog(Context context, TwoFieldDatePickerDialog.OnValueSetListener onValueSetListener, int i, int i2, double d, double d2) {
        this(context, 0, onValueSetListener, i, i2, d, d2);
    }

    public WeekPickerDialog(Context context, int i, TwoFieldDatePickerDialog.OnValueSetListener onValueSetListener, int i2, int i3, double d, double d2) {
        super(context, i, onValueSetListener, i2, i3, d, d2);
        setTitle(R.string.week_picker_dialog_title);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePickerDialog
    protected TwoFieldDatePicker createPicker(Context context, double d, double d2) {
        return new WeekPicker(context, d, d2);
    }

    public WeekPicker getWeekPicker() {
        return (WeekPicker) this.mPicker;
    }
}
