package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import defpackage.di1;
import org.chromium.components.javascript_dialogs.JavascriptDialogCustomView;
import org.chromium.components.javascript_dialogs.R$layout;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: JavascriptModalDialog.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l31 implements di1.a {
    public final String e;
    public final String f;
    public final int g;
    public final int h;
    public final String i;
    public final boolean j;
    public bi1 k;
    public PropertyModel l;
    public JavascriptDialogCustomView m;

    public l31(String str, String str2, String str3, boolean z, int i, int i2) {
        this.e = str;
        this.f = str2;
        this.g = i;
        this.h = i2;
        this.i = str3;
        this.j = z;
    }

    public abstract void a(String str, boolean z);

    @Override // di1.a
    public final void b(int i) {
        JavascriptDialogCustomView javascriptDialogCustomView = this.m;
        if (javascriptDialogCustomView == null) {
            return;
        }
        if (i == 1) {
            a(javascriptDialogCustomView.getPromptText(), this.m.f.isChecked());
        } else if (i == 2) {
            d(true, javascriptDialogCustomView.f.isChecked());
        } else if (i != 4) {
            d(false, javascriptDialogCustomView.f.isChecked());
        }
        this.l = null;
        this.m = null;
        this.k = null;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        bi1 bi1Var = this.k;
        if (bi1Var == null) {
            return;
        }
        if (i == 0) {
            bi1Var.b(1, propertyModel);
        } else {
            if (i == 1) {
                bi1Var.b(2, propertyModel);
                return;
            }
            Log.e("cr_JSModalDialog", "Unexpected button pressed in dialog: " + i);
        }
    }

    public abstract void d(boolean z, boolean z2);

    public final void e(Context context, bi1 bi1Var, int i) {
        JavascriptDialogCustomView javascriptDialogCustomView = (JavascriptDialogCustomView) z7.J(R$layout.js_modal_dialog, context);
        this.m = javascriptDialogCustomView;
        javascriptDialogCustomView.setPromptText(this.i);
        this.m.setSuppressCheckBoxVisibility(this.j);
        Resources resources = context.getResources();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this);
        bVar.e(di1.c, this.e);
        bVar.e(di1.f, this.f);
        bVar.e(di1.h, this.m);
        bVar.d(di1.j, resources, this.g);
        bVar.d(di1.m, resources, this.h);
        bVar.b(di1.t, true);
        PropertyModel propertyModelA = bVar.a();
        this.l = propertyModelA;
        this.k = bi1Var;
        bi1Var.j(propertyModelA, i, false);
    }
}
