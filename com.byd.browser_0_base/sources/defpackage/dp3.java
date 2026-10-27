package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: WebApkIconNameUpdateDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dp3 implements di1.a {
    public Context e;
    public bi1 f;
    public PropertyModel g;
    public String h;
    public String i;
    public Callback<Integer> j;

    @Override // di1.a
    public final void b(int i) {
        this.j.onResult(Integer.valueOf(i));
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 0) {
            this.f.b(1, propertyModel);
            return;
        }
        if (i != 1) {
            Log.i("cr_IconNameUpdateDlg", "Unexpected button pressed in dialog: " + i);
            return;
        }
        Context context = this.e;
        bi1 bi1Var = this.f;
        String str = this.i;
        String str2 = this.h;
        dq3 dq3Var = new dq3(context, bi1Var, str, str2, new ke0(28, this));
        Resources resources = xz.a.getResources();
        String string = resources.getString(R$string.webapk_report_abuse_dialog_title, str2);
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, dq3Var);
        bVar.e(di1.c, string);
        bVar.c(di1.u, 1);
        bVar.d(di1.j, resources, R$string.webapk_report_abuse_confirm);
        bVar.d(di1.m, resources, R$string.webapk_report_abuse_cancel);
        bi1Var.j(bVar.a(), 1, false);
    }
}
