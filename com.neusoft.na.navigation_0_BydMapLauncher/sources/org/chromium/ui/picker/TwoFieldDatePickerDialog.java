package org.chromium.ui.picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import org.chromium.ui.R;
import org.chromium.ui.picker.TwoFieldDatePicker;

/* JADX INFO: loaded from: classes4.dex */
public abstract class TwoFieldDatePickerDialog extends AlertDialog implements DialogInterface.OnClickListener, TwoFieldDatePicker.OnMonthOrWeekChangedListener {
    private static final String POSITION_IN_YEAR = "position_in_year";
    private static final String YEAR = "year";
    protected final OnValueSetListener mCallBack;
    protected final TwoFieldDatePicker mPicker;

    public interface OnValueSetListener {
        void onValueSet(int i, int i2);
    }

    protected TwoFieldDatePicker createPicker(Context context, double d, double d2) {
        return null;
    }

    public TwoFieldDatePickerDialog(Context context, OnValueSetListener onValueSetListener, int i, int i2, double d, double d2) {
        this(context, 0, onValueSetListener, i, i2, d, d2);
    }

    public TwoFieldDatePickerDialog(Context context, int i, OnValueSetListener onValueSetListener, int i2, int i3, double d, double d2) {
        super(context, i);
        this.mCallBack = onValueSetListener;
        setButton(-1, context.getText(R.string.date_picker_dialog_set), this);
        setButton(-2, context.getText(android.R.string.cancel), (DialogInterface.OnClickListener) null);
        setIcon(0);
        TwoFieldDatePicker twoFieldDatePickerCreatePicker = createPicker(context, d, d2);
        this.mPicker = twoFieldDatePickerCreatePicker;
        setView(twoFieldDatePickerCreatePicker);
        twoFieldDatePickerCreatePicker.init(i2, i3, this);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        tryNotifyDateSet();
    }

    protected void tryNotifyDateSet() {
        if (this.mCallBack != null) {
            this.mPicker.clearFocus();
            this.mCallBack.onValueSet(this.mPicker.getYear(), this.mPicker.getPositionInYear());
        }
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker.OnMonthOrWeekChangedListener
    public void onMonthOrWeekChanged(TwoFieldDatePicker twoFieldDatePicker, int i, int i2) {
        this.mPicker.init(i, i2, null);
    }

    public void updateDate(int i, int i2) {
        this.mPicker.updateDate(i, i2);
    }
}
