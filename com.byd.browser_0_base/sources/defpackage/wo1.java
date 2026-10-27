package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.di1;
import defpackage.to1;
import org.chromium.chrome.browser.notifications.R$layout;
import org.chromium.chrome.browser.notifications.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: NotificationPermissionRationaleDialogController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wo1 implements to1.a {
    public final bi1 e;
    public final Context f;

    public wo1(AppCompatActivity appCompatActivity, bi1 bi1Var) {
        this.e = bi1Var;
        this.f = appCompatActivity;
    }

    @Override // to1.a
    public final void b(ko0 ko0Var) {
        Context context = this.f;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Resources resources = context.getResources();
        View viewInflate = layoutInflaterFrom.inflate(R$layout.notification_permission_rationale_dialog, (ViewGroup) null);
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        PropertyModel.l<di1.a> lVar = di1.a;
        wy2 wy2Var = new wy2(ko0Var, 3);
        bi1 bi1Var = this.e;
        bVar.e(lVar, new ym2(bi1Var, wy2Var));
        bVar.e(di1.h, viewInflate);
        bVar.c(di1.u, 1);
        bVar.b(di1.q, true);
        bVar.d(di1.j, resources, R$string.notification_permission_rationale_accept_button_text);
        bVar.d(di1.m, resources, R$string.notification_permission_rationale_reject_button_text);
        bi1Var.j(bVar.a(), 1, false);
    }
}
