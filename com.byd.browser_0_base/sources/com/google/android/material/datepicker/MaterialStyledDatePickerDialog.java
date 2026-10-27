package com.google.android.material.datepicker;

import android.R;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import defpackage.cc1;
import defpackage.l01;
import defpackage.mc1;
import defpackage.sc1;

/* JADX INFO: loaded from: classes.dex */
public class MaterialStyledDatePickerDialog extends DatePickerDialog {
    public static final int g = R$style.MaterialAlertDialog_MaterialComponents_Picker_Date_Spinner;
    public final InsetDrawable e;
    public final Rect f;

    public MaterialStyledDatePickerDialog(Context context) {
        super(context, 0, null, -1, -1, -1);
        Context context2 = getContext();
        int i = cc1.b(R$attr.colorSurface, getContext(), getClass().getCanonicalName()).data;
        int i2 = g;
        sc1 sc1Var = new sc1(context2, null, R.attr.datePickerStyle, i2);
        sc1Var.n(ColorStateList.valueOf(i));
        Rect rectA = mc1.a(context2, R.attr.datePickerStyle, i2);
        this.f = rectA;
        this.e = new InsetDrawable((Drawable) sc1Var, rectA.left, rectA.top, rectA.right, rectA.bottom);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(this.e);
        getWindow().getDecorView().setOnTouchListener(new l01(this, this.f));
    }
}
