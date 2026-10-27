package org.chromium.ui.picker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.NumberPicker;

/* JADX INFO: loaded from: classes4.dex */
public class MultiFieldTimePickerDialog extends AlertDialog implements DialogInterface.OnClickListener {
    private static final int HOUR_IN_MILLIS = 3600000;
    private static final int MINUTE_IN_MILLIS = 60000;
    private static final int SECOND_IN_MILLIS = 1000;
    private final NumberPicker mAmPmSpinner;
    private final int mBaseMilli;
    private final NumberPicker mHourSpinner;
    private final boolean mIs24hourFormat;
    private final OnMultiFieldTimeSetListener mListener;
    private final NumberPicker mMilliSpinner;
    private final NumberPicker mMinuteSpinner;
    private final NumberPicker mSecSpinner;
    private final int mStep;

    public interface OnMultiFieldTimeSetListener {
        void onTimeSet(int i, int i2, int i3, int i4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x020a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0236  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MultiFieldTimePickerDialog(android.content.Context r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, boolean r29, org.chromium.ui.picker.MultiFieldTimePickerDialog.OnMultiFieldTimeSetListener r30) {
        /*
            Method dump skipped, instruction units count: 599
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.picker.MultiFieldTimePickerDialog.<init>(android.content.Context, int, int, int, int, int, int, int, int, boolean, org.chromium.ui.picker.MultiFieldTimePickerDialog$OnMultiFieldTimeSetListener):void");
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        notifyDateSet();
    }

    private void notifyDateSet() {
        int pickerValue = getPickerValue(this.mHourSpinner);
        int pickerValue2 = getPickerValue(this.mMinuteSpinner);
        int pickerValue3 = getPickerValue(this.mSecSpinner);
        int pickerValue4 = (getPickerValue(this.mMilliSpinner) * this.mStep) + this.mBaseMilli;
        if (!this.mIs24hourFormat) {
            int pickerValue5 = getPickerValue(this.mAmPmSpinner);
            if (pickerValue == 12) {
                pickerValue = 0;
            }
            pickerValue += pickerValue5 * 12;
        }
        this.mListener.onTimeSet(pickerValue, pickerValue2, pickerValue3, pickerValue4);
    }

    private int getPickerValue(NumberPicker numberPicker) {
        numberPicker.clearFocus();
        return numberPicker.getValue();
    }

    private static class NumberFormatter implements NumberPicker.Formatter {
        private final String mFormat;

        NumberFormatter(String str) {
            this.mFormat = str;
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i) {
            return String.format(this.mFormat, Integer.valueOf(i));
        }
    }
}
