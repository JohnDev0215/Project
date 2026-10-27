package com.google.android.material.dialog;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import defpackage.cc1;
import defpackage.in3;
import defpackage.l01;
import defpackage.mc1;
import defpackage.oj2;
import defpackage.om3;
import defpackage.qj1;
import defpackage.s81;
import defpackage.sc1;
import defpackage.uc1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialAlertDialogBuilder extends AlertDialog.Builder {
    public static final int e = R$attr.alertDialogStyle;
    public static final int f = R$style.MaterialAlertDialog_MaterialComponents;
    public static final int g = R$attr.materialAlertDialogTheme;
    public final sc1 c;
    public final Rect d;

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialAlertDialogBuilder(Context context) {
        int i = g;
        TypedValue typedValueA = cc1.a(context, i);
        int i2 = typedValueA == null ? 0 : typedValueA.data;
        int i3 = e;
        int i4 = f;
        Context contextA = uc1.a(context, null, i3, i4);
        contextA = i2 != 0 ? new ContextThemeWrapper(contextA, i2) : contextA;
        TypedValue typedValueA2 = cc1.a(context, i);
        super(contextA, typedValueA2 != null ? typedValueA2.data : 0);
        Context context2 = this.a.a;
        Resources.Theme theme = context2.getTheme();
        this.d = mc1.a(context2, i3, i4);
        int iN = oj2.n(R$attr.colorSurface, context2, getClass().getCanonicalName());
        sc1 sc1Var = new sc1(context2, null, i3, i4);
        sc1Var.k(context2);
        sc1Var.n(ColorStateList.valueOf(iN));
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.dialogCornerRadius, typedValue, true);
        float dimension = typedValue.getDimension(this.a.a.getResources().getDisplayMetrics());
        if (typedValue.type == 5 && dimension >= 0.0f) {
            sc1Var.setShapeAppearanceModel(sc1Var.e.a.f(dimension));
        }
        this.c = sc1Var;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final AlertDialog a() {
        AlertDialog alertDialogA = super.a();
        Window window = alertDialogA.getWindow();
        View decorView = window.getDecorView();
        sc1 sc1Var = this.c;
        if (sc1Var instanceof sc1) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            sc1Var.m(om3.i.e(decorView));
        }
        Rect rect = this.d;
        window.setBackgroundDrawable(new InsetDrawable((Drawable) sc1Var, rect.left, rect.top, rect.right, rect.bottom));
        decorView.setOnTouchListener(new l01(alertDialogA, rect));
        return alertDialogA;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final AlertDialog.Builder b(int i) {
        throw null;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final AlertDialog.Builder c(CharSequence charSequence) {
        throw null;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final void d(CharSequence[] charSequenceArr, boolean[] zArr, qj1.a aVar) {
        super.d(charSequenceArr, zArr, aVar);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final AlertDialog.Builder e(int i, DialogInterface.OnClickListener onClickListener) {
        throw null;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final AlertDialog.Builder f(int i, DialogInterface.OnClickListener onClickListener) {
        throw null;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final void g(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        super.g(null, null);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final void h(CharSequence[] charSequenceArr, int i, s81.a aVar) {
        super.h(charSequenceArr, i, aVar);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final AlertDialog.Builder i(int i) {
        throw null;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public final AlertDialog.Builder j(View view) {
        throw null;
    }
}
