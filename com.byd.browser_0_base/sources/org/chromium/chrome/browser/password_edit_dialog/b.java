package org.chromium.chrome.browser.password_edit_dialog;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bi1;
import defpackage.bq;
import defpackage.di1;
import defpackage.sk;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordEditDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public final Context a;
    public final bi1 b;
    public final PasswordEditDialogView c;
    public PropertyModel d;
    public PropertyModel e;
    public final c f;
    public final boolean g;

    /* JADX INFO: compiled from: PasswordEditDialogCoordinator.java */
    public interface a {
    }

    public b(Context context, bi1 bi1Var, PasswordEditDialogView passwordEditDialogView, a aVar) {
        this.a = context;
        this.b = bi1Var;
        this.c = passwordEditDialogView;
        sk skVar = bq.a;
        this.g = N.M09VlOh_("PasswordEditDialogWithDetails");
        this.f = new c(bi1Var, context.getResources(), aVar);
    }

    public final PropertyModel a(int i, int i2) {
        Context context = this.a;
        Resources resources = context.getResources();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this.f);
        bVar.d(di1.c, resources, i);
        bVar.d(di1.j, resources, i2);
        bVar.d(di1.m, resources, R$string.password_generation_dialog_cancel_button);
        bVar.c(di1.u, 1);
        bVar.e(di1.h, this.c);
        if (this.g) {
            PropertyModel.r<Drawable> rVar = di1.e;
            int i3 = R$drawable.ic_vpn_key_blue;
            if (i3 != 0) {
                bVar.e(rVar, AppCompatResources.a(context, i3));
            }
        }
        return bVar.a();
    }
}
