package org.chromium.content.browser.picker;

import android.content.Context;
import org.chromium.content.R$string;
import org.chromium.content.browser.picker.d;

/* JADX INFO: compiled from: MonthPickerDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e extends g {
    public e(Context context, d.g gVar, int i, int i2, double d, double d2) {
        super(context, gVar, i, i2, d, d2);
        setTitle(R$string.month_picker_dialog_title);
    }

    @Override // org.chromium.content.browser.picker.g
    public final TwoFieldDatePicker a(Context context, double d, double d2) {
        return new MonthPicker(context, d, d2);
    }
}
