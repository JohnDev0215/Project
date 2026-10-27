package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import p.hk2;

/* JADX INFO: loaded from: classes.dex */
public final class a extends hk2 {
    public final /* synthetic */ ChipTextInputComboView c;

    public a(ChipTextInputComboView chipTextInputComboView) {
        this.c = chipTextInputComboView;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        boolean zIsEmpty = TextUtils.isEmpty(editable);
        ChipTextInputComboView chipTextInputComboView = this.c;
        if (zIsEmpty) {
            chipTextInputComboView.c.setText(String.format(chipTextInputComboView.getResources().getConfiguration().locale, "%02d", Integer.valueOf(Integer.parseInt("00"))));
        } else {
            chipTextInputComboView.c.setText(String.format(chipTextInputComboView.getResources().getConfiguration().locale, "%02d", Integer.valueOf(Integer.parseInt(String.valueOf(editable)))));
        }
    }
}
