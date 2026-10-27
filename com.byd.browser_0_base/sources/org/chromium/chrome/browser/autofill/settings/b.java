package org.chromium.chrome.browser.autofill.settings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.C0175b;
import defpackage.bi1;
import defpackage.c3;
import defpackage.di1;
import defpackage.rb2;
import defpackage.so;
import defpackage.xr;
import defpackage.ym2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.d;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AutofillVirtualCardEnrollmentDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final Context a;
    public final bi1 b;
    public final VirtualCardEnrollmentFields c;
    public final String d;
    public final String e;
    public final a f;
    public final Callback<Integer> g;
    public PropertyModel h;

    /* JADX INFO: compiled from: AutofillVirtualCardEnrollmentDialog.java */
    @FunctionalInterface
    public interface a {
        void j(int i, String str);
    }

    public b(Context context, bi1 bi1Var, VirtualCardEnrollmentFields virtualCardEnrollmentFields, String str, String str2, a aVar, Callback<Integer> callback) {
        this.a = context;
        this.b = bi1Var;
        this.c = virtualCardEnrollmentFields;
        this.d = str;
        this.e = str2;
        this.f = aVar;
        this.g = callback;
    }

    public final void a() {
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.b(di1.q, false);
        PropertyModel.r<View> rVar = di1.h;
        Context context = this.a;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.virtual_card_enrollment_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.dialog_title);
        String string = context.getString(R$string.autofill_virtual_card_enrollment_dialog_title_label);
        int i = R$drawable.google_pay_with_divider;
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        Drawable drawable = resources.getDrawable(i, theme);
        SpannableString spannableString = new SpannableString(C0175b.j("   ", string));
        float textSize = textView.getTextSize() / drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * textSize), (int) (textSize * drawable.getIntrinsicHeight()));
        spannableString.setSpan(new ImageSpan(drawable, 2), 0, 1, 17);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        TextView textView2 = (TextView) viewInflate.findViewById(R$id.virtual_card_education);
        textView2.setText(d.f(context, R$string.autofill_virtual_card_enrollment_dialog_education_text, new c3(8, this)));
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView3 = (TextView) viewInflate.findViewById(R$id.google_legal_message);
        VirtualCardEnrollmentFields virtualCardEnrollmentFields = this.c;
        textView3.setText(d.e(context, virtualCardEnrollmentFields.a, false, new xr(3, this)));
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView4 = (TextView) viewInflate.findViewById(R$id.issuer_legal_message);
        textView4.setText(d.e(context, virtualCardEnrollmentFields.b, false, new so(9, this)));
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) viewInflate.findViewById(R$id.credit_card_identifier)).setText(context.getString(R$string.autofill_virtual_card_enrollment_dialog_card_label, virtualCardEnrollmentFields.d));
        ((ImageView) viewInflate.findViewById(R$id.credit_card_issuer_icon)).setImageBitmap(virtualCardEnrollmentFields.c);
        bVar.e(rVar, viewInflate);
        bVar.e(di1.j, this.d);
        bVar.c(di1.u, 1);
        bVar.e(di1.m, this.e);
        PropertyModel.l<di1.a> lVar = di1.a;
        bi1 bi1Var = this.b;
        bVar.e(lVar, new ym2(bi1Var, this.g));
        PropertyModel propertyModelA = bVar.a();
        this.h = propertyModelA;
        bi1Var.j(propertyModelA, 1, false);
    }
}
