package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class a extends U4.j {
    public final /* synthetic */ ChipTextInputComboView l;

    public a(ChipTextInputComboView chipTextInputComboView) {
        this.l = chipTextInputComboView;
    }

    @Override // U4.j, android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        boolean zIsEmpty = TextUtils.isEmpty(editable);
        ChipTextInputComboView chipTextInputComboView = this.l;
        if (zIsEmpty) {
            chipTextInputComboView.l.setText(String.format(chipTextInputComboView.getResources().getConfiguration().locale, "%02d", Integer.valueOf(Integer.parseInt("00"))));
        } else {
            chipTextInputComboView.l.setText(String.format(chipTextInputComboView.getResources().getConfiguration().locale, "%02d", Integer.valueOf(Integer.parseInt(String.valueOf(editable)))));
        }
    }
}
