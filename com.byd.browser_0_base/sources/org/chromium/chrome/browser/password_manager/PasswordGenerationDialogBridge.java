package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.di1;
import defpackage.gx1;
import defpackage.hx1;
import defpackage.ix1;
import defpackage.ko0;
import defpackage.qx1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordGenerationDialogBridge {
    public long a;
    public final gx1 b;
    public String c;

    public PasswordGenerationDialogBridge(long j, WindowAndroid windowAndroid) {
        this.a = j;
        this.b = new gx1(windowAndroid);
    }

    @CalledByNative
    public static PasswordGenerationDialogBridge create(WindowAndroid windowAndroid, long j) {
        return new PasswordGenerationDialogBridge(j, windowAndroid);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
        gx1 gx1Var = this.b;
        gx1Var.a.b(4, gx1Var.d);
    }

    @CalledByNative
    public void showDialog(String str, String str2) {
        this.c = str;
        ko0 ko0Var = new ko0(27, this);
        gx1 gx1Var = this.b;
        gx1Var.getClass();
        PropertyModel.r<String> rVar = ix1.c;
        ix1 ix1Var = gx1Var.b;
        ix1Var.p(rVar, str);
        PropertyModel.r<String> rVar2 = ix1.d;
        ix1Var.p(rVar2, str2);
        String str3 = (String) ix1Var.j(rVar);
        PasswordGenerationDialogCustomView passwordGenerationDialogCustomView = gx1Var.c;
        passwordGenerationDialogCustomView.setGeneratedPassword(str3);
        passwordGenerationDialogCustomView.setSaveExplanationText((String) ix1Var.j(rVar2));
        Resources resources = passwordGenerationDialogCustomView.getResources();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, new hx1(ko0Var));
        bVar.e(di1.h, passwordGenerationDialogCustomView);
        bVar.d(di1.j, resources, R$string.password_generation_dialog_use_password_button);
        bVar.d(di1.m, resources, R$string.password_generation_dialog_cancel_button);
        if (qx1.c()) {
            PropertyModel.r<Drawable> rVar3 = di1.e;
            Context context = passwordGenerationDialogCustomView.getContext();
            int i = R$drawable.ic_vpn_key_blue;
            if (i != 0) {
                bVar.e(rVar3, AppCompatResources.a(context, i));
            }
            bVar.d(di1.c, resources, R$string.password_generation_dialog_title_upm_branded);
        } else {
            bVar.d(di1.c, resources, R$string.password_generation_dialog_title);
        }
        PropertyModel propertyModelA = bVar.a();
        gx1Var.d = propertyModelA;
        gx1Var.a.j(propertyModelA, 1, false);
    }
}
