package org.chromium.chrome.browser.ui.autofill;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.f52;
import defpackage.rb2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class AutofillProgressDialogBridge {
    public final long a;
    public final bi1 b;
    public final Context c;
    public PropertyModel d;
    public View e;
    public final a f = new a();

    public class a implements di1.a {
        public a() {
        }

        @Override // di1.a
        public final void b(int i) {
            N.MRKCaYxO(AutofillProgressDialogBridge.this.a);
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            AutofillProgressDialogBridge autofillProgressDialogBridge = AutofillProgressDialogBridge.this;
            autofillProgressDialogBridge.b.b(2, autofillProgressDialogBridge.d);
        }
    }

    public AutofillProgressDialogBridge(long j, Context context, bi1 bi1Var) {
        this.a = j;
        this.b = bi1Var;
        this.c = context;
    }

    @CalledByNative
    public static AutofillProgressDialogBridge create(long j, WindowAndroid windowAndroid) {
        return new AutofillProgressDialogBridge(j, (Context) windowAndroid.k().get(), windowAndroid.n());
    }

    @CalledByNative
    public void dismiss() {
        this.b.b(4, this.d);
    }

    @CalledByNative
    public void showConfirmation(String str) {
        View view = this.e;
        if (view != null) {
            view.findViewById(R$id.progress_bar).setVisibility(8);
            this.e.findViewById(R$id.confirmation_icon).setVisibility(0);
            ((TextView) this.e.findViewById(R$id.message)).setText(str);
        }
        new Handler().postDelayed(new f52(19, this), 500L);
    }

    @CalledByNative
    public void showDialog(String str, String str2, String str3, int i) {
        Context context = this.c;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.autofill_progress_dialog, (ViewGroup) null);
        this.e = viewInflate;
        ((TextView) viewInflate.findViewById(R$id.message)).setText(str2);
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this.f);
        bVar.e(di1.c, str);
        bVar.e(di1.h, this.e);
        bVar.b(di1.l, true);
        bVar.e(di1.m, str3);
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
