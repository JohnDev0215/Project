package org.chromium.content.browser.picker;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import org.chromium.content.R$string;
import org.chromium.content.browser.picker.TwoFieldDatePicker;
import org.chromium.content.browser.picker.d;

/* JADX INFO: compiled from: TwoFieldDatePickerDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class g extends AlertDialog implements DialogInterface.OnClickListener, TwoFieldDatePicker.b {
    public final TwoFieldDatePicker e;
    public final a f;

    /* JADX INFO: compiled from: TwoFieldDatePickerDialog.java */
    public interface a {
    }

    public g(Context context, d.g gVar, int i, int i2, double d, double d2) {
        super(context, 0);
        this.f = gVar;
        setButton(-1, context.getText(R$string.date_picker_dialog_set), this);
        setButton(-2, context.getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        setIcon(0);
        TwoFieldDatePicker twoFieldDatePickerA = a(context, d, d2);
        this.e = twoFieldDatePickerA;
        setView(twoFieldDatePickerA);
        twoFieldDatePickerA.d(i, i2);
        twoFieldDatePickerA.e();
        twoFieldDatePickerA.g = this;
    }

    public abstract TwoFieldDatePicker a(Context context, double d, double d2);

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        a aVar = this.f;
        if (aVar != null) {
            TwoFieldDatePicker twoFieldDatePicker = this.e;
            twoFieldDatePicker.clearFocus();
            int year = twoFieldDatePicker.getYear();
            int positionInYear = twoFieldDatePicker.getPositionInYear();
            d.g gVar = (d.g) aVar;
            int i2 = gVar.a;
            if (i2 == 11) {
                d.this.b(i2, year, positionInYear, 0, 0, 0, 0, 0, 0);
            } else {
                d.this.b(i2, year, 0, 0, 0, 0, 0, 0, positionInYear);
            }
        }
    }
}
