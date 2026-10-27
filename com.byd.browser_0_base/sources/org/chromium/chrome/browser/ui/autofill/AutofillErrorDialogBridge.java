package org.chromium.chrome.browser.ui.autofill;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.rb2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class AutofillErrorDialogBridge {
    public final long a;
    public final bi1 b;
    public final Context c;
    public PropertyModel d;
    public final a e = new a();

    public class a implements di1.a {
        public a() {
        }

        @Override // di1.a
        public final void b(int i) {
            N.MBDxxdbe(AutofillErrorDialogBridge.this.a);
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            AutofillErrorDialogBridge autofillErrorDialogBridge = AutofillErrorDialogBridge.this;
            autofillErrorDialogBridge.b.b(1, autofillErrorDialogBridge.d);
        }
    }

    public AutofillErrorDialogBridge(long j, Context context, bi1 bi1Var) {
        this.a = j;
        this.b = bi1Var;
        this.c = context;
    }

    @CalledByNative
    public static AutofillErrorDialogBridge create(long j, WindowAndroid windowAndroid) {
        return new AutofillErrorDialogBridge(j, (Context) windowAndroid.k().get(), windowAndroid.n());
    }

    @CalledByNative
    public void dismiss() {
        this.b.b(4, this.d);
    }

    @CalledByNative
    public void show(String str, String str2, String str3, int i) {
        Context context = this.c;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.autofill_error_dialog, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R$id.error_message)).setText(str2);
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this.e);
        bVar.e(di1.c, str);
        bVar.e(di1.h, viewInflate);
        bVar.b(di1.o, true);
        bVar.e(di1.j, str3);
        if (i != 0) {
            PropertyModel.r<Drawable> rVar = di1.e;
            Resources resources = context.getResources();
            Resources.Theme theme = context.getTheme();
            ThreadLocal<TypedValue> threadLocal = rb2.a;
            bVar.e(rVar, resources.getDrawable(i, theme));
        }
        PropertyModel propertyModelA = bVar.a();
        this.d = propertyModelA;
        this.b.j(propertyModelA, 0, false);
    }
}
