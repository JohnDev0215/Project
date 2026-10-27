package org.chromium.chrome.browser.ui.autofill;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.C0175b;
import defpackage.bi1;
import defpackage.cg;
import defpackage.di1;
import defpackage.zj;
import defpackage.zx1;
import java.util.HashMap;
import java.util.Optional;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ui.autofill.c;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes2.dex */
public class OtpVerificationDialogBridge implements c.a {
    public final long a;
    public final c b;

    public OtpVerificationDialogBridge(long j, Context context, bi1 bi1Var) {
        this.a = j;
        this.b = new c(context, bi1Var, (OtpVerificationDialogView) LayoutInflater.from(context).inflate(R$layout.otp_verification_dialog, (ViewGroup) null), this);
    }

    @CalledByNative
    public static OtpVerificationDialogBridge create(long j, WindowAndroid windowAndroid) {
        Context context = (Context) windowAndroid.k().get();
        bi1 bi1VarN = windowAndroid.n();
        if (context == null || bi1VarN == null) {
            return null;
        }
        return new OtpVerificationDialogBridge(j, context, bi1VarN);
    }

    @CalledByNative
    public void dismissDialog() {
        d dVar = this.b.a;
        dVar.e.b(4, dVar.f);
    }

    @CalledByNative
    public void showConfirmationAndDismissDialog(String str) {
        d dVar = this.b.a;
        dVar.h.p(e.f, str);
        new Handler().postDelayed(new zx1(24, dVar), 250L);
    }

    /* JADX WARN: Type inference failed for: r7v2, types: [T, java.lang.String] */
    @CalledByNative
    public void showDialog(int i) {
        c cVar = this.b;
        cVar.getClass();
        HashMap mapE = PropertyModel.e(e.h);
        PropertyModel.j jVar = e.a;
        int i2 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(jVar, dVar);
        PropertyModel.l<String> lVar = e.b;
        ?? string = cVar.b.getResources().getString(R$string.autofill_payments_otp_verification_dialog_otp_input_hint, Integer.valueOf(i));
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar, gVar, mapE);
        j.b(propertyModelP, cVar.c, new cg(21));
        d dVar2 = cVar.a;
        dVar2.h = propertyModelP;
        propertyModelP.p(e.c, dVar2);
        dVar2.e.j(dVar2.f, 0, false);
    }

    @CalledByNative
    public void showOtpErrorMessage(String str) {
        c cVar = this.b;
        cVar.getClass();
        Optional optionalOf = Optional.of(str);
        d dVar = cVar.a;
        dVar.h.l(e.g, false);
        dVar.h.p(e.e, optionalOf);
        dVar.f.l(di1.l, true);
    }
}
