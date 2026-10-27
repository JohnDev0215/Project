package org.chromium.content.browser.picker;

import android.content.Context;
import org.chromium.content.R$string;
import org.chromium.content.browser.picker.d;

/* JADX INFO: compiled from: WeekPickerDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class h extends g {
    public h(Context context, d.g gVar, int i, int i2, double d, double d2) {
        super(context, gVar, i, i2, d, d2);
        setTitle(R$string.week_picker_dialog_title);
    }

    @Override // org.chromium.content.browser.picker.g
    public final TwoFieldDatePicker a(Context context, double d, double d2) {
        return new WeekPicker(context, d, d2);
    }
}
