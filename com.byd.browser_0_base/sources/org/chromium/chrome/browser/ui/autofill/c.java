package org.chromium.chrome.browser.ui.autofill;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import defpackage.bi1;
import defpackage.di1;
import defpackage.rb2;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: OtpVerificationDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public final d a;
    public final Context b;
    public final OtpVerificationDialogView c;

    /* JADX INFO: compiled from: OtpVerificationDialogCoordinator.java */
    public interface a {
    }

    public c(Context context, bi1 bi1Var, OtpVerificationDialogView otpVerificationDialogView, a aVar) {
        this.b = context;
        this.c = otpVerificationDialogView;
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.h, otpVerificationDialogView);
        bVar.e(di1.c, context.getResources().getString(R$string.autofill_payments_otp_verification_dialog_title));
        PropertyModel.r<Drawable> rVar = di1.e;
        Resources resources = context.getResources();
        int i = R$drawable.google_pay_with_divider;
        Resources.Theme theme = context.getTheme();
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        bVar.e(rVar, resources.getDrawable(i, theme));
        bVar.e(di1.m, context.getResources().getString(R$string.autofill_payments_otp_verification_dialog_negative_button_label));
        bVar.e(di1.j, context.getResources().getString(R$string.autofill_payments_otp_verification_dialog_positive_button_label));
        bVar.b(di1.l, true);
        this.a = new d(bi1Var, bVar, aVar);
    }
}
